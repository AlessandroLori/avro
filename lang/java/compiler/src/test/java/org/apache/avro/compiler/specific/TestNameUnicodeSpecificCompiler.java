package org.apache.avro.compiler.specific;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.compiler.idl.Idl;
import org.apache.avro.generic.IndexedRecord;
import org.junit.Test;

public class TestNameUnicodeSpecificCompiler {

  private static final String UNICODE_NAME = "用户";
  private static final String UNICODE_FIELD_NAME = "名称";
  private static final String ASCII_NAMESPACE = "org.apache.avro.test";
  private static final String UNICODE_NAMESPACE = "组织.apache";

  private static final String UNICODE_NAME_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}";

  private static final String UNICODE_NAME_AND_NAMESPACE_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"namespace\":\"" + UNICODE_NAMESPACE + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}";

  private static final String UNICODE_ENUM_SCHEMA_JSON = "{"
      + "\"type\":\"enum\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"symbols\":[\"CREATED\",\"RUNNING\",\"DONE\"]"
      + "}";

  private static final String UNICODE_FIXED_SCHEMA_JSON = "{"
      + "\"type\":\"fixed\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"size\":16"
      + "}";

  private static final String NESTED_UNICODE_NAME_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"Container\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"fields\":["
      + "{"
      + "\"name\":\"profile\","
      + "\"type\":{"
      + "\"type\":\"record\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}"
      + "}"
      + "]"
      + "}";

  private static final String UNICODE_NAMED_TYPE_REFERENCE_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"ContainerWithReference\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"fields\":["
      + "{"
      + "\"name\":\"profile\","
      + "\"type\":{"
      + "\"type\":\"record\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}"
      + "},"
      + "{"
      + "\"name\":\"profileRef\","
      + "\"type\":\"" + UNICODE_NAME + "\""
      + "}"
      + "]"
      + "}";

  private static final String UNICODE_FIELD_NAME_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"UserWithUnicodeField\","
      + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
      + "\"fields\":["
      + "{\"name\":\"" + UNICODE_FIELD_NAME + "\",\"type\":\"string\"}"
      + "]"
      + "}";

  /*
   * Test 1:
   * Verifica che SpecificCompiler generi un file Java quando il name Avro
   * del record contiene caratteri Unicode non ASCII.
   *
   * Qui il name Avro "用户" dovrebbe diventare una classe Java "用户".
   */
  @Test
  public void specificCompilerShouldGenerateJavaClassForUnicodeRecordName() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_NAME_RECORD_SCHEMA_JSON);

    Path generatedFile = generatedDir
        .resolve("org")
        .resolve("apache")
        .resolve("avro")
        .resolve("test")
        .resolve(UNICODE_NAME + ".java");

    assertTrue("Expected generated file does not exist: " + generatedFile, Files.exists(generatedFile));

    String generatedCode = new String(Files.readAllBytes(generatedFile), StandardCharsets.UTF_8);

    assertTrue(generatedCode.contains("class " + UNICODE_NAME));
  }

  /*
   * Test 2:
   * Verifica che il codice Java generato da SpecificCompiler con name Unicode
   * sia effettivamente compilabile dal compilatore Java.
   */
  @Test
  public void generatedUnicodeRecordNameClassShouldCompile() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_NAME_RECORD_SCHEMA_JSON);

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(generatedClass);
    assertEquals(UNICODE_NAME, generatedClass.getSimpleName());
  }

  /*
   * Test 3:
   * Verifica che la classe generata con name Unicode non sia solo compilabile,
   * ma anche istanziabile e utilizzabile come IndexedRecord.
   */
  @Test
  public void generatedUnicodeRecordNameClassShouldBeInstantiableAndUsable() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_NAME_RECORD_SCHEMA_JSON);

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    Object instance = generatedClass.getDeclaredConstructor().newInstance();

    assertTrue(instance instanceof IndexedRecord);

    IndexedRecord record = (IndexedRecord) instance;
    record.put(0, 123);

    assertEquals(123, record.get(0));
  }

  /*
   * Test 4:
   * Verifica il caso più critico:
   * name Unicode + namespace Unicode.
   *
   * In questo caso Avro deve generare sia un package/path Unicode sia una
   * classe Java Unicode.
   */
  @Test
  public void specificCompilerShouldHandleUnicodeNameAndUnicodeNamespaceTogether() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_NAME_AND_NAMESPACE_RECORD_SCHEMA_JSON);

    Path generatedFile = generatedDir
        .resolve("组织")
        .resolve("apache")
        .resolve(UNICODE_NAME + ".java");

    assertTrue("Expected generated file does not exist: " + generatedFile, Files.exists(generatedFile));

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        UNICODE_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(generatedClass);
    assertEquals(UNICODE_NAME, generatedClass.getSimpleName());
  }

  /*
   * Test 5:
   * Verifica che SpecificCompiler gestisca anche enum con name Unicode.
   *
   * Il name Avro "用户" dovrebbe diventare una enum Java "用户".
   */
  @Test
  public void specificCompilerShouldGenerateAndCompileEnumWithUnicodeName() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_ENUM_SCHEMA_JSON);

    Path generatedFile = generatedDir
        .resolve("org")
        .resolve("apache")
        .resolve("avro")
        .resolve("test")
        .resolve(UNICODE_NAME + ".java");

    assertTrue("Expected generated enum file does not exist: " + generatedFile, Files.exists(generatedFile));

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(generatedClass);
    assertTrue(generatedClass.isEnum());
  }

  /*
   * Test 6:
   * Verifica che SpecificCompiler gestisca anche fixed con name Unicode.
   *
   * Il name Avro "用户" dovrebbe diventare una classe Java specifica.
   */
  @Test
  public void specificCompilerShouldGenerateAndCompileFixedWithUnicodeName() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_FIXED_SCHEMA_JSON);

    Path generatedFile = generatedDir
        .resolve("org")
        .resolve("apache")
        .resolve("avro")
        .resolve("test")
        .resolve(UNICODE_NAME + ".java");

    assertTrue("Expected generated fixed file does not exist: " + generatedFile, Files.exists(generatedFile));

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(generatedClass);
    assertEquals(UNICODE_NAME, generatedClass.getSimpleName());
  }

  /*
   * Test 7:
   * Verifica record annidato con name Unicode.
   *
   * Qui il record esterno è ASCII, ma contiene un record interno con name
   * Unicode. Il compilatore deve generare entrambe le classi.
   */
  @Test
  public void specificCompilerShouldHandleNestedRecordWithUnicodeName() throws Exception {
    Path generatedDir = generateSpecificJavaFromSchemaJson(NESTED_UNICODE_NAME_RECORD_SCHEMA_JSON);

    Path outerFile = generatedDir
        .resolve("org")
        .resolve("apache")
        .resolve("avro")
        .resolve("test")
        .resolve("Container.java");

    Path nestedFile = generatedDir
        .resolve("org")
        .resolve("apache")
        .resolve("avro")
        .resolve("test")
        .resolve(UNICODE_NAME + ".java");

    assertTrue("Expected outer generated file does not exist: " + outerFile, Files.exists(outerFile));
    assertTrue("Expected nested generated file does not exist: " + nestedFile, Files.exists(nestedFile));

    Class<?> outerClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + ".Container");

    Class<?> nestedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(outerClass);
    assertNotNull(nestedClass);
  }

  /*
   * Test 8:
   * Verifica riferimento a named type Unicode.
   *
   * Lo schema definisce un record Unicode "用户" e poi lo riusa tramite:
   *
   * "type": "用户"
   *
   * Questo test controlla sia la risoluzione del riferimento nello Schema,
   * sia la generazione/compilazione del codice specifico.
   */
  @Test
  public void parserAndSpecificCompilerShouldHandleUnicodeNamedTypeReference() throws Exception {
    Schema schema = new Schema.Parser().parse(UNICODE_NAMED_TYPE_REFERENCE_SCHEMA_JSON);

    Schema profileSchema = schema.getField("profile").schema();
    Schema profileRefSchema = schema.getField("profileRef").schema();

    assertEquals(UNICODE_NAME, profileSchema.getName());
    assertEquals(profileSchema.getFullName(), profileRefSchema.getFullName());

    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_NAMED_TYPE_REFERENCE_SCHEMA_JSON);

    Class<?> outerClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + ".ContainerWithReference");

    Class<?> referencedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + "." + UNICODE_NAME);

    assertNotNull(outerClass);
    assertNotNull(referencedClass);
  }

  /*
   * Test 9:
   * Verifica field name Unicode.
   *
   * Non è il name del record, ma è comunque un identificatore Avro.
   * Questo test serve a capire se la permissività Unicode si propaga anche
   * ai nomi dei campi e se la code generation resta compilabile.
   */
  @Test
  public void specificCompilerShouldHandleUnicodeFieldName() throws Exception {
    Schema schema = new Schema.Parser().parse(UNICODE_FIELD_NAME_SCHEMA_JSON);

    assertNotNull(schema.getField(UNICODE_FIELD_NAME));
    assertEquals(UNICODE_FIELD_NAME, schema.getFields().get(0).name());

    Path generatedDir = generateSpecificJavaFromSchemaJson(UNICODE_FIELD_NAME_SCHEMA_JSON);

    Class<?> generatedClass = compileAndLoadGeneratedClass(
        generatedDir,
        ASCII_NAMESPACE + ".UserWithUnicodeField");

    Object instance = generatedClass.getDeclaredConstructor().newInstance();

    assertTrue(instance instanceof IndexedRecord);

    IndexedRecord record = (IndexedRecord) instance;
    record.put(0, "unicode-field-value");

    assertEquals("unicode-field-value", record.get(0).toString());
  }

  /*
   * Test 10:
   * Verifica parsing di Protocol JSON con type name Unicode.
   *
   * Qui non usiamo direttamente SpecificCompiler, ma controlliamo un altro
   * ingresso della toolchain Avro: i protocolli JSON.
   */
  @Test
  public void protocolJsonParserShouldHandleUnicodeTypeName() {
    String protocolJson = "{"
        + "\"protocol\":\"UnicodeNameProtocol\","
        + "\"namespace\":\"" + ASCII_NAMESPACE + "\","
        + "\"types\":["
        + "{"
        + "\"type\":\"record\","
        + "\"name\":\"" + UNICODE_NAME + "\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}"
        + "],"
        + "\"messages\":{"
        + "\"echo\":{"
        + "\"request\":[{\"name\":\"input\",\"type\":\"" + UNICODE_NAME + "\"}],"
        + "\"response\":\"" + UNICODE_NAME + "\""
        + "}"
        + "}"
        + "}";

    Protocol protocol = Protocol.parse(protocolJson);

    assertNotNull(protocol);
    assertEquals("UnicodeNameProtocol", protocol.getName());
    assertEquals(ASCII_NAMESPACE, protocol.getNamespace());
    assertEquals(1, protocol.getTypes().size());
    assertEquals(UNICODE_NAME, protocol.getTypes().iterator().next().getName());
    assertNotNull(protocol.getMessages().get("echo"));
  }

  /*
   * Test 11:
   * Verifica parsing IDL con record name Unicode.
   *
   * Questo è utile perché l'IDL passa da una grammatica testuale diversa dal
   * parser JSON. Se qualche punto della toolchain è più restrittivo, potrebbe
   * emergere qui.
   */
  @SuppressWarnings("deprecation")
  @Test
  public void idlParserShouldHandleUnicodeRecordName() throws Exception {
    String idlText = "@namespace(\"" + ASCII_NAMESPACE + "\")\n"
        + "protocol UnicodeNameProtocol {\n"
        + "  record " + UNICODE_NAME + " {\n"
        + "    int id;\n"
        + "  }\n"
        + "\n"
        + "  " + UNICODE_NAME + " echo(" + UNICODE_NAME + " input);\n"
        + "}\n";

    try (Idl idl = new Idl(
        new ByteArrayInputStream(idlText.getBytes(StandardCharsets.UTF_8)),
        "UTF-8")) {

      Protocol protocol = idl.CompilationUnit();

      assertNotNull(protocol);
      assertEquals("UnicodeNameProtocol", protocol.getName());
      assertEquals(ASCII_NAMESPACE, protocol.getNamespace());
      assertEquals(1, protocol.getTypes().size());
      assertEquals(UNICODE_NAME, protocol.getTypes().iterator().next().getName());
      assertNotNull(protocol.getMessages().get("echo"));
    }
  }

  private static Path generateSpecificJavaFromSchemaJson(String schemaJson) throws Exception {
    Path workDir = Files.createTempDirectory("avro-name-unicode-test-");
    Path schemaFile = workDir.resolve("schema.avsc");
    Path generatedDir = workDir.resolve("generated");

    Files.createDirectories(generatedDir);
    Files.write(schemaFile, schemaJson.getBytes(StandardCharsets.UTF_8));

    SpecificCompiler.compileSchema(schemaFile.toFile(), generatedDir.toFile());

    return generatedDir;
  }

  private static Class<?> compileAndLoadGeneratedClass(Path generatedDir, String fullClassName)
      throws Exception {
    JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

    assertNotNull(
        "JDK compiler is not available. Run the test with a JDK, not with a JRE.",
        javaCompiler);

    Path classesDir = Files.createTempDirectory("avro-name-unicode-classes-");

    List<Path> javaFiles = findJavaFiles(generatedDir);

    assertTrue("No generated Java files found in " + generatedDir, !javaFiles.isEmpty());

    List<String> compilerArguments = new ArrayList<>();
    compilerArguments.add("-encoding");
    compilerArguments.add("UTF-8");
    compilerArguments.add("-classpath");
    compilerArguments.add(System.getProperty("java.class.path"));
    compilerArguments.add("-d");
    compilerArguments.add(classesDir.toString());

    for (Path javaFile : javaFiles) {
      compilerArguments.add(javaFile.toString());
    }

    int compilationResult = javaCompiler.run(
        null,
        null,
        null,
        compilerArguments.toArray(new String[0]));

    assertEquals("Generated Java code did not compile.", 0, compilationResult);

    URLClassLoader classLoader = new URLClassLoader(
        new URL[] {classesDir.toUri().toURL()},
        Thread.currentThread().getContextClassLoader());

    return Class.forName(fullClassName, true, classLoader);
  }

  private static List<Path> findJavaFiles(Path root) throws Exception {
    try (Stream<Path> stream = Files.walk(root)) {
      return stream
          .filter(path -> path.toString().endsWith(".java"))
          .collect(Collectors.toList());
    }
  }
}
