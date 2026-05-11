package org.apache.avro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;

public class TestNonASCII {

  private static final String NON_ASCII_NAMESPACE = "组织.apache";
  private static final String ASCII_NAMESPACE = "org.apache";
  private static final String RECORD_NAME = "User";
  private static final String DIFFERENT_RECORD_NAME = "DifferentUser";
  private static final String FIELD_NAME = "id";

  private static final String NON_ASCII_SCHEMA_JSON =
      "{"
          + "\"type\":\"record\","
          + "\"namespace\":\"" + NON_ASCII_NAMESPACE + "\","
          + "\"name\":\"" + RECORD_NAME + "\","
          + "\"fields\":["
          + "{\"name\":\"" + FIELD_NAME + "\",\"type\":\"int\"}"
          + "]"
          + "}";

  private static final String SAME_NON_ASCII_SCHEMA_JSON =
      "{"
          + "\"type\":\"record\","
          + "\"namespace\":\"" + NON_ASCII_NAMESPACE + "\","
          + "\"name\":\"" + RECORD_NAME + "\","
          + "\"fields\":["
          + "{\"name\":\"" + FIELD_NAME + "\",\"type\":\"int\"}"
          + "]"
          + "}";

  private static final String ASCII_SCHEMA_JSON =
      "{"
          + "\"type\":\"record\","
          + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
          + "\"name\":\"" + RECORD_NAME + "\","
          + "\"fields\":["
          + "{\"name\":\"" + FIELD_NAME + "\",\"type\":\"int\"}"
          + "]"
          + "}";

  private static final String NON_ASCII_SCHEMA_WITH_DIFFERENT_RECORD_NAME_JSON =
      "{"
          + "\"type\":\"record\","
          + "\"namespace\":\"" + NON_ASCII_NAMESPACE + "\","
          + "\"name\":\"" + DIFFERENT_RECORD_NAME + "\","
          + "\"fields\":["
          + "{\"name\":\"" + FIELD_NAME + "\",\"type\":\"int\"}"
          + "]"
          + "}";

  /*
   * Questo test verifica il primo punto della propagazione.
   *
   * Obiettivo:
   * capire se il parser si limita ad accettare il namespace non-ASCII oppure
   * se lo conserva realmente dentro l'oggetto Schema.
   *
   * Se namespace e fullname contengono "组织.apache", allora il valore non-ASCII
   * non è stato ignorato dal parser e viene portato dentro la rappresentazione
   * interna dello schema.
   */
  @Test
  public void parserShouldExposeNonAsciiNamespaceNameAndFullName() {
    Schema schema = parseNonAsciiSchema();

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals(RECORD_NAME, schema.getName());
    assertEquals(NON_ASCII_NAMESPACE, schema.getNamespace());
    assertEquals(NON_ASCII_NAMESPACE + "." + RECORD_NAME, schema.getFullName());
    assertEquals(1, schema.getFields().size());
    assertNotNull(schema.getField(FIELD_NAME));
  }

  /*
   * Questo test verifica il round-trip:
   *
   * JSON iniziale -> Schema -> JSON serializzato -> Schema riparsato.
   *
   * Obiettivo:
   * verificare se il namespace non-ASCII sopravvive anche dopo la
   * serializzazione dello Schema in JSON e il successivo parsing.
   *
   * Se il round-trip conserva namespace e fullname, allora il valore Unicode
   * non è solo accettato in input, ma rimane stabile nella rappresentazione
   * JSON prodotta da Avro.
   */
  @Test
  public void serializedSchemaShouldPreserveNonAsciiNamespaceAfterRoundTrip() {
    Schema originalSchema = parseNonAsciiSchema();

    String serializedSchemaJson = originalSchema.toString();
    Schema reparsedSchema = new Schema.Parser().parse(serializedSchemaJson);

    assertEquals(RECORD_NAME, reparsedSchema.getName());
    assertEquals(NON_ASCII_NAMESPACE, reparsedSchema.getNamespace());
    assertEquals(NON_ASCII_NAMESPACE + "." + RECORD_NAME, reparsedSchema.getFullName());
    assertEquals(originalSchema, reparsedSchema);
  }

  /*
   * Questo test verifica la canonical form.
   *
   * Obiettivo:
   * capire se il namespace non-ASCII entra nella rappresentazione canonica
   * dello schema.
   *
   * La canonical form viene usata per rappresentare uno schema in forma
   * normalizzata. Se contiene il namespace non-ASCII, allora quel namespace
   * influenza una rappresentazione usata per identificare lo schema.
   */
  @Test
  public void canonicalFormShouldContainNonAsciiFullName() {
    Schema schema = parseNonAsciiSchema();

    String canonicalForm = SchemaNormalization.toParsingForm(schema);

    assertTrue(
        "Expected canonical form to contain the non-ASCII namespace or its escaped form. Canonical form: "
            + canonicalForm,
        containsRawOrEscaped(canonicalForm, NON_ASCII_NAMESPACE));

    assertTrue(
        "Expected canonical form to contain the record name. Canonical form: " + canonicalForm,
        canonicalForm.contains(RECORD_NAME));
  }

  /*
   * Questo test verifica il fingerprint.
   *
   * Obiettivo:
   * verificare che lo schema con namespace non-ASCII produca un fingerprint
   * stabile dopo il round-trip Schema -> JSON -> Schema.
   *
   * Se i fingerprint coincidono, Avro è internamente stabile rispetto a questa
   * rappresentazione non-ASCII almeno nel core schema/fingerprint.
   */
  @Test
  public void fingerprintShouldBeStableAfterSerializationRoundTrip() {
    Schema originalSchema = parseNonAsciiSchema();
    Schema reparsedSchema = new Schema.Parser().parse(originalSchema.toString());

    long originalFingerprint = SchemaNormalization.parsingFingerprint64(originalSchema);
    long reparsedFingerprint = SchemaNormalization.parsingFingerprint64(reparsedSchema);

    assertEquals(originalFingerprint, reparsedFingerprint);
  }

  /*
   * Questo test verifica la compatibilità tra due schemi equivalenti,
   * entrambi con lo stesso namespace non-ASCII.
   *
   * Obiettivo:
   * verificare che Avro riesca a usare normalmente due schema con lo stesso
   * fullname Unicode nella logica di compatibilità.
   */
  @Test
  public void compatibilityShouldBeCompatibleForEquivalentNonAsciiSchemas() {
    Schema reader = new Schema.Parser().parse(NON_ASCII_SCHEMA_JSON);
    Schema writer = new Schema.Parser().parse(SAME_NON_ASCII_SCHEMA_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(reader, writer);

    assertEquals(
        SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE,
        compatibility.getResult().getCompatibility());
  }

  /*
   * Questo test verifica un comportamento importante emerso durante l'analisi.
   *
   * Reader e writer hanno stesso nome semplice "User", ma namespace diverso:
   *
   * reader = org.apache.User
   * writer = 组织.apache.User
   *
   * Obiettivo:
   * verificare che la compatibilità Avro non fallisca solo perché cambia il
   * namespace, se il nome non qualificato del record è lo stesso.
   *
   * Questo risultato è importante perché mostra che il namespace non-ASCII può
   * essere conservato nello Schema, ma non necessariamente produce un
   * NAME_MISMATCH nella schema compatibility quando il record name è uguale.
   */
  @Test
  public void compatibilityShouldRemainCompatibleWhenOnlyNamespaceDiffersButRecordNameIsSame() {
    Schema reader = new Schema.Parser().parse(ASCII_SCHEMA_JSON);
    Schema writer = new Schema.Parser().parse(NON_ASCII_SCHEMA_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(reader, writer);

    assertEquals(
        SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE,
        compatibility.getResult().getCompatibility());
  }

  /*
   * Questo test verifica il caso in cui la compatibility deve davvero rilevare
   * un mismatch di nome.
   *
   * Qui non cambia solo il namespace, ma cambia anche il nome semplice:
   *
   * reader = org.apache.User
   * writer = 组织.apache.DifferentUser
   *
   * Obiettivo:
   * verificare che Avro segnali NAME_MISMATCH quando il nome non qualificato
   * del record è diverso.
   */
  @Test
  public void compatibilityShouldDetectNameMismatchWhenUnqualifiedRecordNameIsDifferent() {
    Schema reader = new Schema.Parser().parse(ASCII_SCHEMA_JSON);
    Schema writer = new Schema.Parser().parse(NON_ASCII_SCHEMA_WITH_DIFFERENT_RECORD_NAME_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(reader, writer);

    assertEquals(
        SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE,
        compatibility.getResult().getCompatibility());

    assertEquals(
        SchemaCompatibility.SchemaIncompatibilityType.NAME_MISMATCH,
        compatibility.getResult().getIncompatibilities().get(0).getType());
  }

  /*
   * Questo test verifica un aspetto collegato alla futura code generation.
   *
   * Avro può usare il namespace dello schema come package Java.
   * Qui non usiamo ancora SpecificCompiler: verifichiamo solo se il compilatore
   * Java standard accetta un package derivato dal namespace non-ASCII.
   *
   * Obiettivo:
   * capire se, almeno per javac, un package come "组织.apache" è compilabile.
   * Questo non dimostra che la code generation Avro sia sicura, ma verifica
   * un primo vincolo della toolchain Java.
   */
  @Test
  public void javaCompilerShouldCompilePackageDerivedFromNonAsciiNamespace() throws Exception {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    assertNotNull("This test requires a JDK, not only a JRE.", compiler);

    Path tempDir = Files.createTempDirectory("avro-non-ascii-package-test");
    Path sourceDir = tempDir.resolve("src");
    Path classesDir = tempDir.resolve("classes");
    Path packageDir = sourceDir.resolve("组织").resolve("apache");

    Files.createDirectories(packageDir);
    Files.createDirectories(classesDir);

    Path javaFile = packageDir.resolve(RECORD_NAME + ".java");

    String source =
        "package " + NON_ASCII_NAMESPACE + ";\n"
            + "public class " + RECORD_NAME + " {\n"
            + "}\n";

    Files.write(javaFile, source.getBytes(StandardCharsets.UTF_8));

    int result = compiler.run(
        null,
        null,
        null,
        "-encoding",
        "UTF-8",
        "-d",
        classesDir.toString(),
        javaFile.toString());

    assertEquals(
        "Expected javac to compile a Java package derived from the non-ASCII namespace."
            + " Source file: " + javaFile,
        0,
        result);

    assertTrue(
        "Expected compiled class to exist.",
        Files.exists(classesDir.resolve("组织").resolve("apache").resolve(RECORD_NAME + ".class")));
  }

  private static Schema parseNonAsciiSchema() {
    return new Schema.Parser().parse(NON_ASCII_SCHEMA_JSON);
  }

  private static boolean containsRawOrEscaped(String text, String value) {
    return text.contains(value) || text.contains(toJsonUnicodeEscaped(value));
  }

  private static String toJsonUnicodeEscaped(String value) {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < value.length(); i++) {
      char current = value.charAt(i);

      if (current <= 0x7F) {
        builder.append(current);
      } else {
        builder.append(String.format("\\u%04X", (int) current));
      }
    }

    return builder.toString();
  }
}
