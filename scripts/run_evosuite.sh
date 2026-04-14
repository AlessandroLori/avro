#!/usr/bin/env bash
set -euo pipefail

REPO_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
MODULE_DIR="$REPO_ROOT/lang/java/avro"

JDK11_HOME="/usr/lib/jvm/java-11-openjdk-amd64"
JDK17_HOME="/usr/lib/jvm/java-17-openjdk-amd64"
JDK21_HOME="/usr/lib/jvm/java-21-openjdk-amd64"
EVOSUITE_JAR="$REPO_ROOT/tools/evosuite/evosuite-1.2.0.jar"

ORIGINAL_PATH="$PATH"

use_jdk() {
  local jdk_home="$1"
  if [[ ! -d "$jdk_home" ]]; then
    echo "Errore: JDK non trovato in $jdk_home" >&2
    exit 1
  fi
  export JAVA_HOME="$jdk_home"
  export PATH="$JAVA_HOME/bin:$ORIGINAL_PATH"
}

prepare_module_cp() {
  use_jdk "$JDK17_HOME"

  echo "==> Preparing Avro classes and classpath with JDK 17"
  java -version
  mvn -version

  mvn -pl lang/java/avro -Pevosuite \
    -DskipTests \
    -Denforcer.skip=true \
    -Dspotless.check.skip=true \
    -Dcheckstyle.skip=true \
    clean test-compile \
    org.apache.maven.plugins:maven-dependency-plugin:3.8.1:build-classpath \
    -Dmdep.outputFile=target/evosuite.classpath \
    -Dmdep.includeScope=test
}

filter_classpath_for_evosuite() {
  cd "$MODULE_DIR"

  python3 <<'PY'
from pathlib import Path
import os
import struct
import zipfile

cp_file = Path("target/evosuite.classpath")
if not cp_file.exists():
    raise SystemExit("Errore: target/evosuite.classpath non trovato")

raw_cp = "target/classes:target/test-classes:" + cp_file.read_text().strip()
entries = [e for e in raw_cp.split(":") if e]

kept = []
dropped = []

MAX_MAJOR = 55  # Java 11

def class_major_from_bytes(data: bytes):
    if len(data) < 8:
        return None
    if data[0:4] != b'\xca\xfe\xba\xbe':
        return None
    # magic(4) minor(2) major(2)
    return struct.unpack(">H", data[6:8])[0]

def jar_contains_too_new_classes(jar_path: str) -> bool:
    try:
        with zipfile.ZipFile(jar_path) as zf:
            for name in zf.namelist():
                if not name.endswith(".class"):
                    continue
                try:
                    with zf.open(name) as fh:
                        major = class_major_from_bytes(fh.read(8))
                    if major is not None and major > MAX_MAJOR:
                        return True
                except Exception:
                    continue
    except Exception:
        return False
    return False

def jar_contains_too_new_multi_release_entries(jar_path: str) -> bool:
    try:
        with zipfile.ZipFile(jar_path) as zf:
            for name in zf.namelist():
                if not name.startswith("META-INF/versions/"):
                    continue
                parts = name.split("/")
                if len(parts) >= 3:
                    try:
                        version_dir = int(parts[2])
                        if version_dir > 11:
                            return True
                    except ValueError:
                        pass
    except Exception:
        return False
    return False

for entry in entries:
    if entry in ("target/classes", "target/test-classes"):
        kept.append(entry)
        continue

    if entry.endswith(".jar") and os.path.isfile(entry):
        drop_reason = None

        name = os.path.basename(entry).lower()
        if "byte-buddy" in name:
            drop_reason = "byte-buddy jar"
        elif jar_contains_too_new_multi_release_entries(entry):
            drop_reason = "multi-release > 11"
        elif jar_contains_too_new_classes(entry):
            drop_reason = "class major > 55"

        if drop_reason is not None:
            dropped.append(f"{entry}    [{drop_reason}]")
            continue

    kept.append(entry)

Path("target/evosuite.filtered.classpath").write_text(":".join(kept))
Path("target/evosuite.dropped-jars.txt").write_text("\n".join(dropped))
print(":".join(kept), end="")
PY
}

generate_tests() {
  local cut="$1"
  shift || true

  if [[ ! -f "$EVOSUITE_JAR" ]]; then
    echo "Errore: jar EvoSuite non trovato in $EVOSUITE_JAR" >&2
    echo "Metti il jar in tools/evosuite/evosuite-1.2.0.jar" >&2
    exit 1
  fi

  prepare_module_cp
  filter_classpath_for_evosuite
  use_jdk "$JDK11_HOME"

  echo "==> Running EvoSuite with JDK 11"
  java -version

  cd "$MODULE_DIR"

  rm -rf evosuite-tests evosuite-report
  mkdir -p src/test/evosuite
  find src/test/evosuite -mindepth 1 -delete

  if [[ ! -f target/evosuite.filtered.classpath ]]; then
    echo "Errore: target/evosuite.filtered.classpath non creato" >&2
    exit 1
  fi

  local project_cp
  project_cp="$(cat target/evosuite.filtered.classpath)"

  echo "==> CUT: $cut"
  echo "==> Checking target bytecode exists"

  local class_file
  class_file="target/classes/$(echo "$cut" | tr '.' '/').class"

  if [[ ! -f "$class_file" ]]; then
    echo "Errore: classe compilata non trovata: $class_file" >&2
    exit 1
  fi

  if [[ -s target/evosuite.dropped-jars.txt ]]; then
    echo "==> JAR esclusi dal projectCP di EvoSuite:"
    cat target/evosuite.dropped-jars.txt
  fi

  local -a java_opts=(
    "--add-opens=java.base/java.util=ALL-UNNAMED"
  )

  java "${java_opts[@]}" -jar "$EVOSUITE_JAR" \
    -class "$cut" \
    -projectCP "$project_cp" \
    -criterion branch \
    -Dsearch_budget=20 \
    -Dstopping_condition=MaxTime \
    -Dassertions=false \
    -Dminimize=false \
    -Dsandbox=false \
    -Dlog.level=info \
    "$@"

  if [[ ! -d evosuite-tests ]]; then
    echo "Errore: EvoSuite non ha prodotto la cartella evosuite-tests" >&2
    exit 1
  fi

  cp -R evosuite-tests/. src/test/evosuite/

  echo
  echo "Generazione completata."
  echo "Test copiati in: lang/java/avro/src/test/evosuite"
}

run_generated_tests() {
  local pattern="${1:-*ESTest}"

  use_jdk "$JDK21_HOME"

  echo "==> Running generated tests with JDK 21"
  java -version
  mvn -version

  mvn -pl lang/java/avro -Pevosuite \
    -Dspotless.check.skip=true \
    -Dtest="$pattern" \
    -Dsurefire.failIfNoSpecifiedTests=true \
    test
}

clean_generated() {
  cd "$MODULE_DIR"
  rm -rf evosuite-tests evosuite-report
  mkdir -p src/test/evosuite
  find src/test/evosuite -mindepth 1 -delete
  rm -f target/evosuite.classpath
  rm -f target/evosuite.filtered.classpath
  rm -f target/evosuite.dropped-jars.txt
  echo "Pulizia completata."
}

show_help() {
  cat <<'EOF'
Uso:
  ./scripts/run_evosuite.sh generate <fully.qualified.ClassName>
  ./scripts/run_evosuite.sh test [pattern]
  ./scripts/run_evosuite.sh clean
  ./scripts/run_evosuite.sh help

Esempi:
  ./scripts/run_evosuite.sh generate org.apache.avro.util.Utf8
  ./scripts/run_evosuite.sh test
  ./scripts/run_evosuite.sh test '*ESTest'
EOF
}

if [[ $# -eq 0 ]]; then
  show_help
  exit 0
fi

case "$1" in
  help)
    show_help
    ;;
  generate)
    shift
    if [[ $# -lt 1 ]]; then
      echo "Errore: devi passare la classe fully-qualified." >&2
      exit 1
    fi
    generate_tests "$@"
    ;;
  test)
    shift || true
    run_generated_tests "${1:-*ESTest}"
    ;;
  clean)
    clean_generated
    ;;
  *)
    echo "Comando non riconosciuto: $1" >&2
    show_help
    exit 1
    ;;
esac
