/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.avro;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.avro.path.TracingAvroTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tree-of-thought style black-box tests for Schema_C2.java. The class under
 * test still declares the production type {@link Schema}, so this file is meant
 * to be executed after Schema_C2.java replaces Schema.java in the Avro module.
 */
@SuppressWarnings({ "deprecation", "serial" })
public class TestToTSchemaC2 {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private static final JsonNodeFactory JSON = JsonNodeFactory.instance;

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable action) {
    try {
      action.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      AssertionError failure = new AssertionError(
          "Expected " + expectedType.getName() + " but caught " + actual.getClass().getName());
      failure.initCause(actual);
      throw failure;
    }
    fail("Expected exception of type " + expectedType.getName());
    return null;
  }

  private static Schema primitive(Schema.Type type) {
    return Schema.create(type);
  }

  private static Schema.Field field(String name, Schema schema) {
    return new Schema.Field(name, schema, null, null);
  }

  private static Schema.Field fieldWithDefault(String name, Schema schema, Object defaultValue) {
    return new Schema.Field(name, schema, "doc-" + name, defaultValue);
  }

  private static Schema record(String name, Schema.Field... fields) {
    Schema schema = Schema.createRecord(name, "doc-" + name, "org.example", false);
    schema.setFields(Arrays.asList(fields));
    return schema;
  }

  private static byte[] utf8(String value) {
    return value.getBytes(StandardCharsets.UTF_8);
  }

  @Test
  public void typeGetNameShouldReturnLowercaseAvroNames() {
    assertEquals("record", Schema.Type.RECORD.getName());
    assertEquals("enum", Schema.Type.ENUM.getName());
    assertEquals("array", Schema.Type.ARRAY.getName());
    assertEquals("map", Schema.Type.MAP.getName());
    assertEquals("union", Schema.Type.UNION.getName());
    assertEquals("fixed", Schema.Type.FIXED.getName());
    assertEquals("string", Schema.Type.STRING.getName());
    assertEquals("bytes", Schema.Type.BYTES.getName());
    assertEquals("int", Schema.Type.INT.getName());
    assertEquals("long", Schema.Type.LONG.getName());
    assertEquals("float", Schema.Type.FLOAT.getName());
    assertEquals("double", Schema.Type.DOUBLE.getName());
    assertEquals("boolean", Schema.Type.BOOLEAN.getName());
    assertEquals("null", Schema.Type.NULL.getName());
  }

  @Test
  public void createShouldBuildEveryPrimitiveSchema() {
    for (Schema.Type type : Arrays.asList(Schema.Type.STRING, Schema.Type.BYTES, Schema.Type.INT, Schema.Type.LONG,
        Schema.Type.FLOAT, Schema.Type.DOUBLE, Schema.Type.BOOLEAN, Schema.Type.NULL)) {
      Schema schema = Schema.create(type);
      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
    }
  }

  @Test
  public void createShouldRejectNonPrimitiveSchemaTypes() {
    for (Schema.Type type : Arrays.asList(Schema.Type.RECORD, Schema.Type.ENUM, Schema.Type.ARRAY, Schema.Type.MAP,
        Schema.Type.UNION, Schema.Type.FIXED)) {
      expectThrows(AvroRuntimeException.class, () -> Schema.create(type));
    }
  }

  @Test
  public void primitiveSchemasShouldExposeDefaultMetadataAndUnsupportedOperations() {
    Schema intSchema = primitive(Schema.Type.INT);

    assertEquals(Schema.Type.INT, intSchema.getType());
    assertEquals("int", intSchema.getName());
    assertEquals("int", intSchema.getFullName());
    assertNull(intSchema.getDoc());
    assertFalse(intSchema.isUnion());
    assertFalse(intSchema.isNullable());

    expectThrows(AvroRuntimeException.class, () -> intSchema.getField("missing"));
    expectThrows(AvroRuntimeException.class, intSchema::getFields);
    expectThrows(AvroRuntimeException.class, intSchema::hasFields);
    expectThrows(AvroRuntimeException.class, () -> intSchema.setFields(Collections.emptyList()));
    expectThrows(AvroRuntimeException.class, intSchema::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, intSchema::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> intSchema.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> intSchema.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, intSchema::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> intSchema.addAlias("alias"));
    expectThrows(AvroRuntimeException.class, () -> intSchema.addAlias("alias", "space"));
    expectThrows(AvroRuntimeException.class, intSchema::getAliases);
    expectThrows(AvroRuntimeException.class, intSchema::isError);
    expectThrows(AvroRuntimeException.class, intSchema::getElementType);
    expectThrows(AvroRuntimeException.class, intSchema::getValueType);
    expectThrows(AvroRuntimeException.class, intSchema::getTypes);
    expectThrows(AvroRuntimeException.class, () -> intSchema.getIndexNamed("int"));
    expectThrows(AvroRuntimeException.class, intSchema::getFixedSize);
  }

  @Test
  public void addPropShouldStoreStringAndObjectPropertiesAndInvalidateHashCode() {
    Schema schema = primitive(Schema.Type.STRING);
    int originalHash = schema.hashCode();

    schema.addProp("stringProp", "value");
    schema.addProp("numericProp", Integer.valueOf(7));

    assertEquals("value", schema.getProp("stringProp"));
    assertTrue(schema.toString().contains("\"numericProp\":7"));
    assertNotEquals(originalHash, schema.hashCode());
  }

  @Test
  public void createRecordWithFieldsShouldExposeNameDocNamespaceAndFieldLookup() {
    Schema.Field id = field("id", primitive(Schema.Type.INT));
    Schema.Field name = fieldWithDefault("name", primitive(Schema.Type.STRING), "unknown");
    Schema schema = Schema.createRecord("User", "user doc", "org.example", false, Arrays.asList(id, name));

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals("User", schema.getName());
    assertEquals("org.example", schema.getNamespace());
    assertEquals("org.example.User", schema.getFullName());
    assertEquals("user doc", schema.getDoc());
    assertFalse(schema.isError());
    assertTrue(schema.hasFields());
    assertEquals(Arrays.asList(id, name), schema.getFields());
    assertSame(id, schema.getField("id"));
    assertSame(name, schema.getField("name"));
    assertNull(schema.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
  }

  @Test
  public void createRecordWithoutFieldsShouldAllowOneSetFieldsCall() {
    Schema schema = Schema.createRecord("Delayed", null, "org.example", true);
    assertEquals("Delayed", schema.getName());
    assertTrue(schema.isError());
    assertFalse(schema.hasFields());
    expectThrows(AvroRuntimeException.class, schema::getFields);
    expectThrows(AvroRuntimeException.class, () -> schema.getField("x"));

    Schema.Field field = field("x", primitive(Schema.Type.LONG));
    schema.setFields(Collections.singletonList(field));

    assertTrue(schema.hasFields());
    assertSame(field, schema.getField("x"));
    expectThrows(AvroRuntimeException.class, () -> schema.setFields(Collections.emptyList()));
  }

  @Test
  public void deprecatedAnonymousRecordFactoryShouldSetFieldsImmediately() {
    Schema.Field value = field("value", primitive(Schema.Type.STRING));
    Schema schema = Schema.createRecord(Collections.singletonList(value));

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertTrue(schema.hasFields());
    assertSame(value, schema.getField("value"));
    assertNull(schema.getName());
    assertNull(schema.getFullName());
  }

  @Test
  public void setFieldsShouldRejectDuplicateOrReusedFields() {
    Schema duplicateRecord = Schema.createRecord("Duplicate", null, "org.example", false);
    expectThrows(AvroRuntimeException.class, () -> duplicateRecord.setFields(Arrays.asList(
        field("same", primitive(Schema.Type.INT)), field("same", primitive(Schema.Type.STRING)))));

    Schema.Field reused = field("id", primitive(Schema.Type.INT));
    Schema first = Schema.createRecord("First", null, "org.example", false);
    first.setFields(Collections.singletonList(reused));
    Schema second = Schema.createRecord("Second", null, "org.example", false);
    expectThrows(AvroRuntimeException.class, () -> second.setFields(Collections.singletonList(reused)));
  }

  @Test
  public void recordFieldsListShouldBeLockedAfterSetFields() {
    Schema schema = record("LockedFields", field("id", primitive(Schema.Type.INT)));
    expectThrows(IllegalStateException.class, () -> schema.getFields().add(field("extra", primitive(Schema.Type.STRING))));
  }

  @Test
  public void namedSchemasShouldSupportAliasesWithAndWithoutExplicitNamespace() {
    Schema schema = record("AliasRecord", field("id", primitive(Schema.Type.INT)));

    schema.addAlias("LegacyRecord");
    schema.addAlias("ExternalRecord", "external.ns");

    assertTrue(schema.getAliases().contains("org.example.LegacyRecord"));
    assertTrue(schema.getAliases().contains("external.ns.ExternalRecord"));
  }

  @Test
  public void createEnumShouldExposeSymbolsOrdinalsDefaultAndMembership() {
    Schema schema = Schema.createEnum("Color", "color doc", "org.example", Arrays.asList("RED", "GREEN", "BLUE"),
        "GREEN");

    assertEquals(Schema.Type.ENUM, schema.getType());
    assertEquals("Color", schema.getName());
    assertEquals("color doc", schema.getDoc());
    assertEquals("org.example.Color", schema.getFullName());
    assertEquals(Arrays.asList("RED", "GREEN", "BLUE"), schema.getEnumSymbols());
    assertEquals("GREEN", schema.getEnumDefault());
    assertTrue(schema.hasEnumSymbol("RED"));
    assertFalse(schema.hasEnumSymbol("PURPLE"));
    assertEquals(1, schema.getEnumOrdinal("GREEN"));
    expectThrows(TracingAvroTypeException.class, () -> schema.getEnumOrdinal("PURPLE"));
  }

  @Test
  public void createEnumShouldRejectDuplicateSymbolsAndInvalidDefault() {
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadEnum", null, "org.example", Arrays.asList("A", "A")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadDefault", null, "org.example", Arrays.asList("A", "B"), "C"));
  }

  @Test
  public void enumSymbolsShouldBeLocked() {
    Schema schema = Schema.createEnum("LockedEnum", null, "org.example", Arrays.asList("A", "B"));
    expectThrows(IllegalStateException.class, () -> schema.getEnumSymbols().add("C"));
  }

  @Test
  public void createArrayAndMapShouldExposeContainedSchemas() {
    Schema item = primitive(Schema.Type.STRING);
    Schema array = Schema.createArray(item);
    Schema map = Schema.createMap(item);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(item, array.getElementType());
    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(item, map.getValueType());
  }

  @Test
  public void createUnionShouldExposeTypesAndIndexesAndCompositeName() {
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(2, union.getTypes().size());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertNull(union.getIndexNamed("int"));
    assertEquals("union[null, string]", union.getName());
  }

  @Test
  public void createUnionShouldRejectNestedNamelessOrDuplicateBranchesAndLockedTypes() {
    Schema union = Schema.createUnion(Arrays.asList(primitive(Schema.Type.NULL), primitive(Schema.Type.INT)));

    expectThrows(IllegalStateException.class, () -> union.getTypes().add(primitive(Schema.Type.LONG)));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.INT)));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(primitive(Schema.Type.NULL), Schema.createUnion(primitive(Schema.Type.INT))));
    expectThrows(AvroRuntimeException.class, () -> union.addProp("p", "v"));
  }

  @Test
  public void isNullableShouldWorkForNullPrimitiveAndUnions() {
    assertTrue(primitive(Schema.Type.NULL).isNullable());
    assertFalse(primitive(Schema.Type.STRING).isNullable());
    assertTrue(Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING)).isNullable());
    assertFalse(Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.STRING)).isNullable());
  }

  @Test
  public void createFixedShouldExposeSizeAndNamedMetadata() {
    Schema fixed = Schema.createFixed("Md5", "hash", "org.example", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Md5", fixed.getName());
    assertEquals("hash", fixed.getDoc());
    assertEquals("org.example", fixed.getNamespace());
    assertEquals("org.example.Md5", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
  }

  @Test
  public void schemasShouldCompareByStructureNameAndProperties() {
    Schema first = record("Comparable", field("id", primitive(Schema.Type.INT)));
    Schema second = record("Comparable", field("id", primitive(Schema.Type.INT)));
    Schema different = record("Different", field("id", primitive(Schema.Type.INT)));

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertNotEquals(first, different);

    second.addProp("custom", "value");
    assertNotEquals(first, second);
  }

  @Test
  public void recursiveRecordsShouldNotOverflowEqualsHashCodeOrToString() {
    Schema node = Schema.createRecord("Node", null, "org.example", false);
    Schema next = Schema.createUnion(primitive(Schema.Type.NULL), node);
    node.setFields(Collections.singletonList(new Schema.Field("next", next, null, Schema.Field.NULL_DEFAULT_VALUE)));

    assertEquals(node, node);
    assertEquals(node.hashCode(), node.hashCode());
    String rendered = node.toString();
    assertTrue(rendered.contains("\"name\":\"Node\""));
    assertTrue(rendered.contains("\"next\""));
  }

  @Test
  public void toStringShouldRenderInlinePrettyAndReferencedSchemas() {
    Schema child = record("Child", field("value", primitive(Schema.Type.STRING)));
    Schema parent = record("Parent", field("child", child));

    String compact = parent.toString();
    String pretty = parent.toString(true);
    String withReference = parent.toString(Collections.singleton(child), false);

    assertTrue(compact.startsWith("{"));
    assertTrue(pretty.contains(System.lineSeparator()) || pretty.contains("\n"));
    assertTrue(withReference.contains("\"type\":\"Child\""));
  }

  @Test
  public void logicalTypeShouldBeStoredWhenAddedToSchema() {
    Schema dateBackedByInt = primitive(Schema.Type.INT);
    LogicalTypes.date().addToSchema(dateBackedByInt);

    assertNotNull(dateBackedByInt.getLogicalType());
    assertEquals("date", dateBackedByInt.getLogicalType().getName());
  }

  @Test
  public void fieldConstructorsShouldExposeCoreStateDefaultOrderAndAliases() {
    Schema.Field field = new Schema.Field("name", primitive(Schema.Type.STRING), "field doc", "anonymous",
        Schema.Field.Order.DESCENDING);

    assertEquals("name", field.name());
    assertEquals(-1, field.pos());
    assertEquals(Schema.Type.STRING, field.schema().getType());
    assertEquals("field doc", field.doc());
    assertTrue(field.hasDefaultValue());
    assertEquals("anonymous", field.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertTrue(field.aliases().isEmpty());

    field.addAlias("fullName");
    assertTrue(field.aliases().contains("fullName"));
    expectThrows(UnsupportedOperationException.class, () -> field.aliases().add("other"));
  }

  @Test
  public void fieldConstructorsShouldRejectNullSchemaInvalidDefaultAndNullOrder() {
    expectThrows(NullPointerException.class, () -> new Schema.Field("bad", null));
    expectThrows(NullPointerException.class,
        () -> new Schema.Field("bad", primitive(Schema.Type.STRING), null, null, null));
    expectThrows(AvroTypeException.class,
        () -> new Schema.Field("bad", primitive(Schema.Type.INT), null, "not-an-int"));
  }

  @Test
  public void fieldNullDefaultSentinelShouldRepresentExplicitNullDefault() {
    Schema nullableString = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    Schema.Field field = new Schema.Field("optional", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());
    assertSame(JsonProperties.NULL_VALUE, field.defaultVal());
  }

  @Test
  public void fieldCopyConstructorShouldCopyPropsAndAliasesWhileReplacingSchema() {
    Schema.Field original = new Schema.Field("value", primitive(Schema.Type.STRING), "doc", "x");
    original.addProp("custom", "prop");
    original.addAlias("legacyValue");

    Schema.Field copy = new Schema.Field(original, primitive(Schema.Type.BYTES));

    assertEquals(original.name(), copy.name());
    assertEquals("doc", copy.doc());
    assertEquals(Schema.Type.BYTES, copy.schema().getType());
    assertEquals("prop", copy.getProp("custom"));
    assertTrue(copy.aliases().contains("legacyValue"));
  }

  @Test
  public void fieldEqualsHashCodeAndToStringShouldUseObservableFieldState() {
    Schema.Field first = fieldWithDefault("count", primitive(Schema.Type.INT), 1);
    Schema.Field second = fieldWithDefault("count", primitive(Schema.Type.INT), 1);
    Schema.Field different = fieldWithDefault("count", primitive(Schema.Type.LONG), 1L);

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertNotEquals(first, different);
    assertTrue(first.toString().contains("count"));
    assertTrue(first.toString().contains("type:INT"));
  }

  @Test
  public void parserShouldParseStringFragmentsAndExposeKnownTypes() {
    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse("{\"type\":\"record\",", "\"name\":\"Split\",", "\"fields\":[]}");

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals("Split", schema.getName());
    assertTrue(parser.getTypes().containsKey("Split"));
  }

  @Test
  public void parserAddTypesShouldAllowReferencesToKnownNamedTypes() {
    Schema known = record("Known", field("id", primitive(Schema.Type.INT)));
    Schema.Parser parser = new Schema.Parser();
    parser.addTypes(Collections.singleton(known));

    Schema holder = parser.parse(
        "{\"type\":\"record\",\"name\":\"Holder\",\"fields\":[{\"name\":\"known\",\"type\":\"org.example.Known\"}]}");

    assertEquals(known, holder.getField("known").schema());
  }

  @Test
  public void deprecatedParserAddTypesMapShouldUseMapValues() {
    Schema known = record("KnownFromMap", field("id", primitive(Schema.Type.INT)));
    Map<String, Schema> types = new LinkedHashMap<>();
    types.put("ignored-key", known);

    Schema.Parser parser = new Schema.Parser().addTypes(types);
    Schema holder = parser.parse(
        "{\"type\":\"record\",\"name\":\"HolderFromMap\",\"fields\":[{\"name\":\"known\",\"type\":\"org.example.KnownFromMap\"}]}");

    assertEquals(known, holder.getField("known").schema());
  }

  @Test
  public void parserShouldExposeAndHonorDefaultValidation() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\",\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"default\":\"not-an-int\"}] }";

    Schema.Parser validatingParser = new Schema.Parser();
    assertTrue(validatingParser.getValidateDefaults());
    expectThrows(AvroTypeException.class, () -> validatingParser.parse(invalidDefault));

    Schema.Parser nonValidatingParser = new Schema.Parser().setValidateDefaults(false);
    assertFalse(nonValidatingParser.getValidateDefaults());
    Schema parsed = nonValidatingParser.parse(invalidDefault);
    Schema.Field id = parsed.getField("id");
    assertTrue(id.hasDefaultValue());
    assertEquals("not-an-int", id.defaultValue().textValue());
    assertNull(id.defaultVal());
  }

  @Test
  public void parserShouldParseFromFileAndInputStreamWithoutClosingSourceStream() throws IOException {
    File schemaFile = temporaryFolder.newFile("schema.avsc");
    Files.write(schemaFile.toPath(), utf8("\"long\""));
    assertEquals(Schema.Type.LONG, new Schema.Parser().parse(schemaFile).getType());

    CloseAwareInputStream stream = new CloseAwareInputStream(utf8("\"boolean\""));
    assertEquals(Schema.Type.BOOLEAN, new Schema.Parser().parse(stream).getType());
    assertFalse(stream.closed);
  }

  @Test
  public void parserShouldRejectDanglingContentForStringInputs() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"int\" \"long\""));
  }

  @Test
  public void parserShouldSupportCommentsAndParseInternalForUnresolvedContext() {
    Schema commented = new Schema.Parser().parse("/* allowed comment */ \"string\"");
    Schema internal = new Schema.Parser().parseInternal(
        "{\"type\":\"record\",\"name\":\"InternalOnly\",\"fields\":[]}");

    assertEquals(Schema.Type.STRING, commented.getType());
    assertEquals("InternalOnly", internal.getName());
  }

  @Test
  public void deprecatedStaticParseMethodsShouldDelegateToParser() throws IOException {
    assertEquals(Schema.Type.INT, Schema.parse("\"int\"").getType());
    assertEquals(Schema.Type.STRING, Schema.parse(new ByteArrayInputStream(utf8("\"string\""))).getType());

    File schemaFile = temporaryFolder.newFile("static-schema.avsc");
    Files.write(schemaFile.toPath(), utf8("\"double\""));
    assertEquals(Schema.Type.DOUBLE, Schema.parse(schemaFile).getType());
  }

  @Test
  public void deprecatedStaticParseShouldHonorNameValidationFlag() {
    String invalidName = "{\"type\":\"record\",\"name\":\"1Invalid\",\"fields\":[]}";

    expectThrows(SchemaParseException.class, () -> Schema.parse(invalidName, true));
    assertEquals("1Invalid", Schema.parse(invalidName, false).getName());
  }

  @Test
  public void staticNameValidatorThreadLocalShouldBeConfigurableAndRestorable() {
    NameValidator saved = Schema.getNameValidator();
    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      Schema schema = Schema.createRecord("1InvalidButAllowed", null, null, false, Collections.emptyList());
      assertEquals("1InvalidButAllowed", schema.getName());
    } finally {
      Schema.setNameValidator(saved);
    }
  }

  @Test
  public void staticDefaultValidationThreadLocalShouldControlFieldConstruction() {
    boolean saved = Schema.getValidateDefaults();
    try {
      Schema.setValidateDefaults(false);
      Schema.Field invalidButAllowed = new Schema.Field("id", primitive(Schema.Type.INT), null, "not-an-int");
      assertTrue(invalidButAllowed.hasDefaultValue());
      assertEquals("not-an-int", invalidButAllowed.defaultValue().textValue());
      assertNull(invalidButAllowed.defaultVal());
    } finally {
      Schema.setValidateDefaults(saved);
    }
  }

  @Test
  public void isValidDefaultShouldAcceptAndRejectPrimitiveValues() {
    assertTrue(primitive(Schema.Type.STRING).isValidDefault(JSON.textNode("x")));
    assertTrue(primitive(Schema.Type.BYTES).isValidDefault(JSON.textNode("abc")));
    assertTrue(Schema.createEnum("E", null, "org.example", Arrays.asList("A")).isValidDefault(JSON.textNode("A")));
    assertTrue(Schema.createFixed("F", null, "org.example", 2).isValidDefault(JSON.textNode("aa")));
    assertTrue(primitive(Schema.Type.INT).isValidDefault(JSON.numberNode(12)));
    assertFalse(primitive(Schema.Type.INT).isValidDefault(JSON.numberNode(2147483648L)));
    assertTrue(primitive(Schema.Type.LONG).isValidDefault(JSON.numberNode(12L)));
    assertTrue(primitive(Schema.Type.FLOAT).isValidDefault(JSON.numberNode(1.5D)));
    assertTrue(primitive(Schema.Type.DOUBLE).isValidDefault(JSON.numberNode(1.5D)));
    assertTrue(primitive(Schema.Type.BOOLEAN).isValidDefault(JSON.booleanNode(true)));
    assertTrue(primitive(Schema.Type.NULL).isValidDefault(JSON.nullNode()));
    assertFalse(primitive(Schema.Type.STRING).isValidDefault(null));
    assertFalse(primitive(Schema.Type.STRING).isValidDefault(JSON.numberNode(1)));
  }

  @Test
  public void isValidDefaultShouldValidateArraysMapsUnionsAndRecordsRecursively() {
    Schema intArray = Schema.createArray(primitive(Schema.Type.INT));
    assertTrue(intArray.isValidDefault(JSON.arrayNode().add(1).add(2)));
    assertFalse(intArray.isValidDefault(JSON.arrayNode().add("bad")));

    ObjectNode validMap = JSON.objectNode().put("a", true).put("b", false);
    ObjectNode invalidMap = JSON.objectNode().put("a", "bad");
    Schema booleanMap = Schema.createMap(primitive(Schema.Type.BOOLEAN));
    assertTrue(booleanMap.isValidDefault(validMap));
    assertFalse(booleanMap.isValidDefault(invalidMap));

    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    assertTrue(union.isValidDefault(JSON.nullNode()));
    assertTrue(union.isValidDefault(JSON.textNode("x")));
    assertFalse(union.isValidDefault(JSON.numberNode(1)));

    Schema record = record("DefaultRecord", field("required", primitive(Schema.Type.INT)),
        fieldWithDefault("optional", primitive(Schema.Type.STRING), "fallback"));
    assertTrue(record.isValidDefault(JSON.objectNode().put("required", 1)));
    assertFalse(record.isValidDefault(JSON.objectNode().put("optional", "x")));
  }

  @Test
  public void parseJsonToObjectShouldReturnJavaCollectionsAndPrimitiveValues() {
    Object parsed = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,null]}");

    assertTrue(parsed instanceof Map);
    Map<?, ?> map = (Map<?, ?>) parsed;
    assertTrue(map.get("a") instanceof Number);
    assertEquals(1, ((Number) map.get("a")).intValue());
    assertTrue(map.get("b") instanceof List);
  }

  @Test
  public void applyAliasesShouldReturnWriterWhenSchemasAreEqualOrReaderHasNoAliases() {
    Schema writer = record("NoAlias", field("id", primitive(Schema.Type.INT)));
    Schema structurallySame = record("NoAlias", field("id", primitive(Schema.Type.INT)));
    Schema readerWithoutAliases = record("ReaderWithoutAliases", field("id", primitive(Schema.Type.INT)));

    assertSame(writer, Schema.applyAliases(writer, writer));
    assertSame(writer, Schema.applyAliases(writer, structurallySame));
    assertSame(writer, Schema.applyAliases(writer, readerWithoutAliases));
  }

  @Test
  public void applyAliasesShouldRewriteRecordAndFieldNamesFromReaderAliases() {
    Schema writer = Schema.createRecord("OldUser", null, "writer.ns", false,
        Collections.singletonList(field("oldId", primitive(Schema.Type.INT))));
    Schema.Field newField = field("newId", primitive(Schema.Type.INT));
    newField.addAlias("oldId");
    Schema reader = Schema.createRecord("NewUser", null, "reader.ns", false, Collections.singletonList(newField));
    reader.addAlias("writer.ns.OldUser");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("reader.ns.NewUser", rewritten.getFullName());
    assertNotNull(rewritten.getField("newId"));
    assertNull(rewritten.getField("oldId"));
  }

  @Test
  public void applyAliasesShouldRewriteEnumFixedArrayMapAndUnionBranches() {
    Schema writerRecord = Schema.createRecord("OldNested", null, "writer.ns", false,
        Collections.singletonList(field("id", primitive(Schema.Type.INT))));
    Schema readerRecord = Schema.createRecord("NewNested", null, "reader.ns", false,
        Collections.singletonList(field("id", primitive(Schema.Type.INT))));
    readerRecord.addAlias("writer.ns.OldNested");

    Schema rewrittenArray = Schema.applyAliases(Schema.createArray(writerRecord), Schema.createArray(readerRecord));
    assertEquals("reader.ns.NewNested", rewrittenArray.getElementType().getFullName());

    Schema rewrittenMap = Schema.applyAliases(Schema.createMap(writerRecord), Schema.createMap(readerRecord));
    assertEquals("reader.ns.NewNested", rewrittenMap.getValueType().getFullName());

    Schema rewrittenUnion = Schema.applyAliases(Schema.createUnion(primitive(Schema.Type.NULL), writerRecord),
        Schema.createUnion(primitive(Schema.Type.NULL), readerRecord));
    assertEquals("reader.ns.NewNested", rewrittenUnion.getTypes().get(1).getFullName());

    Schema writerEnum = Schema.createEnum("OldEnum", null, "writer.ns", Arrays.asList("A", "B"));
    Schema readerEnum = Schema.createEnum("NewEnum", null, "reader.ns", Arrays.asList("A", "B"));
    readerEnum.addAlias("writer.ns.OldEnum");
    assertEquals("reader.ns.NewEnum", Schema.applyAliases(writerEnum, readerEnum).getFullName());

    Schema writerFixed = Schema.createFixed("OldFixed", null, "writer.ns", 4);
    Schema readerFixed = Schema.createFixed("NewFixed", null, "reader.ns", 4);
    readerFixed.addAlias("writer.ns.OldFixed");
    assertEquals("reader.ns.NewFixed", Schema.applyAliases(writerFixed, readerFixed).getFullName());
  }

  @Test
  public void seenPairShouldUseIdentityEqualityAndIdentityBasedHashCode() {
    Object first = new String("same-text");
    Object second = new String("same-text");
    Schema.SeenPair pair = new Schema.SeenPair(first, second);
    Schema.SeenPair sameIdentities = new Schema.SeenPair(first, second);
    Schema.SeenPair differentFirstIdentity = new Schema.SeenPair(new String("same-text"), second);

    assertEquals(pair, sameIdentities);
    assertEquals(pair.hashCode(), sameIdentities.hashCode());
    assertNotEquals(pair, differentFirstIdentity);
    assertNotEquals(pair, "not-a-pair");
  }

  @Test
  public void namesShouldTrackDefaultNamespacePrimitivesAndRedefinitionRules() {
    Schema.Names names = new Schema.Names("org.example");
    assertEquals("org.example", names.space());
    names.space("changed.ns");
    assertEquals("changed.ns", names.space());
    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema record = Schema.createRecord("Named", null, "changed.ns", false, Collections.emptyList());
    names.add(record);
    assertSame(record, names.get("Named"));
    assertTrue(names.contains(record));

    Schema conflicting = Schema.createRecord("Named", null, "changed.ns", false,
        Collections.singletonList(field("id", primitive(Schema.Type.INT))));
    expectThrows(SchemaParseException.class, () -> names.add(conflicting));
  }

  @Test
  public void lockableArrayListShouldAllowMutationBeforeLockAndRejectMutationAfterLock() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>("a", "b");
    assertTrue(list.add("c"));
    assertTrue(list.remove("c"));
    List<String> locked = list.lock();
    assertSame(list, locked);

    expectThrows(IllegalStateException.class, () -> list.add("d"));
    expectThrows(IllegalStateException.class, () -> list.remove("a"));
    expectThrows(IllegalStateException.class, () -> list.remove(0));
    expectThrows(IllegalStateException.class, () -> list.addAll(Collections.singleton("d")));
    expectThrows(IllegalStateException.class, () -> list.addAll(0, Collections.singleton("d")));
    expectThrows(IllegalStateException.class, () -> list.removeAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, () -> list.retainAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, list::clear);
  }

  @Test
  public void parsingShouldRejectMalformedSchemasAndAliasDefinitions() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{not json}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":\"not-array\",\"fields\":[]}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"BadAliasEntry\",\"aliases\":[7],\"fields\":[]}"));
  }

  @Test
  public void parsingShouldRejectMissingRecordEnumArrayMapAndFixedRequirements() {
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFields\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"NoSymbols\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"NoSize\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"BadSize\",\"size\":\"4\"}"));
  }

  @Test
  public void parsingShouldPreserveFieldDocsOrdersAliasesPropsAndFloatingTextDefaults() {
    Schema schema = new Schema.Parser().parse("{"
        + "\"type\":\"record\",\"name\":\"ParsedRecord\",\"namespace\":\"org.example\","
        + "\"fields\":["
        + "{\"name\":\"amount\",\"type\":\"double\",\"doc\":\"amount doc\",\"default\":\"1.25\","
        + "\"order\":\"descending\",\"aliases\":[\"oldAmount\"],\"customField\":\"fieldProp\"}"
        + "],\"customSchema\":\"schemaProp\"}");

    Schema.Field amount = schema.getField("amount");
    assertEquals("schemaProp", schema.getProp("customSchema"));
    assertEquals("amount doc", amount.doc());
    assertEquals(1.25D, (Double) amount.defaultVal(), 0.0D);
    assertEquals(Schema.Field.Order.DESCENDING, amount.order());
    assertTrue(amount.aliases().contains("oldAmount"));
    assertEquals("fieldProp", amount.getProp("customField"));
  }

  @Test
  public void recordNamedAfterPrimitiveShouldBeRejected() {
    expectThrows(AvroTypeException.class,
        () -> Schema.createRecord("int", null, null, false, Collections.emptyList()));
  }

  private static final class CloseAwareInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseAwareInputStream(byte[] bytes) {
      super(bytes);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
