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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Black-box and contract-oriented tests for the Schema implementation used by
 * Schema_C1.java.
 *
 * Keep this test in package {@code org.apache.avro}. Some members, such as
 * writeReplace(), are intentionally protected/package-visible in Schema and are
 * not accessible from subpackages.
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class TestLLMCodeSchemaC1 {

  private static final ObjectMapper JSON = new ObjectMapper();

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      AssertionError assertionError = new AssertionError(
          "Expected exception " + expectedType.getName() + " but got " + actual.getClass().getName());
      assertionError.initCause(actual);
      throw assertionError;
    }
    fail("Expected exception " + expectedType.getName());
    return null;
  }

  private static JsonNode json(String value) throws IOException {
    return JSON.readTree(value);
  }

  private static Schema.Field field(String name, Schema.Type type) {
    return new Schema.Field(name, Schema.create(type));
  }

  private static Schema record(String name, String namespace, Schema.Field... fields) {
    return Schema.createRecord(name, null, namespace, false, Arrays.asList(fields));
  }

  @Test
  public void createShouldBuildEveryPrimitiveTypeAndRejectComplexTypes() {
    Map<Schema.Type, String> primitiveNames = new LinkedHashMap<>();
    primitiveNames.put(Schema.Type.STRING, "string");
    primitiveNames.put(Schema.Type.BYTES, "bytes");
    primitiveNames.put(Schema.Type.INT, "int");
    primitiveNames.put(Schema.Type.LONG, "long");
    primitiveNames.put(Schema.Type.FLOAT, "float");
    primitiveNames.put(Schema.Type.DOUBLE, "double");
    primitiveNames.put(Schema.Type.BOOLEAN, "boolean");
    primitiveNames.put(Schema.Type.NULL, "null");

    for (Map.Entry<Schema.Type, String> entry : primitiveNames.entrySet()) {
      Schema schema = Schema.create(entry.getKey());

      assertEquals(entry.getKey(), schema.getType());
      assertEquals(entry.getValue(), schema.getName());
      assertEquals(entry.getValue(), schema.getFullName());
      assertEquals("\"" + entry.getValue() + "\"", schema.toString());
      assertFalse(schema.isUnion());
    }

    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.RECORD));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ENUM));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ARRAY));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.MAP));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.UNION));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.FIXED));
  }

  @Test
  public void primitivePropertiesShouldAffectEqualityHashCodeAndJsonRendering() {
    Schema plain = Schema.create(Schema.Type.STRING);
    Schema withProperty = Schema.create(Schema.Type.STRING);

    assertEquals(plain, withProperty);
    assertEquals(plain.hashCode(), withProperty.hashCode());

    int hashBeforeProperty = withProperty.hashCode();
    withProperty.addProp("custom", "value");

    assertFalse(plain.equals(withProperty));
    assertNotEquals(hashBeforeProperty, withProperty.hashCode());
    assertEquals("value", withProperty.getProp("custom"));
    assertTrue(withProperty.toString().contains("\"custom\":\"value\""));
  }

  @Test
  public void baseAccessorsShouldRejectIncompatibleSchemaTypes() {
    Schema primitive = Schema.create(Schema.Type.STRING);

    expectThrows(AvroRuntimeException.class, () -> primitive.getField("missing"));
    expectThrows(AvroRuntimeException.class, primitive::getFields);
    expectThrows(AvroRuntimeException.class, primitive::hasFields);
    expectThrows(AvroRuntimeException.class, () -> primitive.setFields(Collections.singletonList(field("f", Schema.Type.INT))));
    expectThrows(AvroRuntimeException.class, primitive::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, primitive::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> primitive.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> primitive.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, primitive::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> primitive.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> primitive.addAlias("Alias", "space"));
    expectThrows(AvroRuntimeException.class, primitive::getAliases);
    expectThrows(AvroRuntimeException.class, primitive::isError);
    expectThrows(AvroRuntimeException.class, primitive::getElementType);
    expectThrows(AvroRuntimeException.class, primitive::getValueType);
    expectThrows(AvroRuntimeException.class, primitive::getTypes);
    expectThrows(AvroRuntimeException.class, () -> primitive.getIndexNamed("string"));
    expectThrows(AvroRuntimeException.class, primitive::getFixedSize);
  }

  @Test
  public void recordCreationShouldExposeNamesDocsFieldsPositionsAndErrorFlag() {
    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT), "identifier", 0);
    Schema.Field name = new Schema.Field("name", Schema.create(Schema.Type.STRING), "display name", "unknown",
        Schema.Field.Order.DESCENDING);

    Schema record = Schema.createRecord("User", "user documentation", "example.avro", true, Arrays.asList(id, name));

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertEquals("user documentation", record.getDoc());
    assertTrue(record.isError());
    assertTrue(record.hasFields());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertEquals(Arrays.asList(id, name), record.getFields());
    assertTrue(record.toString().contains("\"type\":\"error\""));
    assertTrue(record.toString().contains("\"order\":\"descending\""));

    expectThrows(IllegalStateException.class, () -> record.getFields().add(field("extra", Schema.Type.LONG)));
  }

  @Test
  public void recordSetFieldsShouldBeSingleUseAndRejectDuplicatesOrReusedFields() {
    Schema record = Schema.createRecord("SingleUse", null, "example.avro", false);

    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, record::getFields);
    expectThrows(AvroRuntimeException.class, () -> record.getField("id"));

    record.setFields(Collections.singletonList(field("id", Schema.Type.INT)));
    assertTrue(record.hasFields());

    expectThrows(AvroRuntimeException.class, () -> record.setFields(Collections.singletonList(field("other", Schema.Type.INT))));

    Schema duplicate = Schema.createRecord("Duplicate", null, "example.avro", false);
    expectThrows(AvroRuntimeException.class,
        () -> duplicate.setFields(Arrays.asList(field("same", Schema.Type.INT), field("same", Schema.Type.LONG))));

    Schema.Field reused = field("reused", Schema.Type.STRING);
    Schema first = Schema.createRecord("First", null, "example.avro", false);
    first.setFields(Collections.singletonList(reused));

    Schema second = Schema.createRecord("Second", null, "example.avro", false);
    expectThrows(AvroRuntimeException.class, () -> second.setFields(Collections.singletonList(reused)));
  }

  @Test
  public void anonymousRecordFactoryShouldCreateARecordWithProvidedFields() {
    Schema.Field value = field("value", Schema.Type.LONG);

    Schema anonymous = Schema.createRecord(Collections.singletonList(value));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertTrue(anonymous.hasFields());
    assertSame(value, anonymous.getField("value"));
    assertEquals(0, value.pos());
    assertEquals(null, anonymous.getName());
    assertEquals(null, anonymous.getFullName());
  }

  @Test
  public void fieldConstructorsAccessorsDefaultsAliasesAndCopyShouldWork() {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema.Field field = new Schema.Field("age", intSchema, "age in years", 10, Schema.Field.Order.DESCENDING);

    assertEquals("age", field.name());
    assertEquals(-1, field.pos());
    assertSame(intSchema, field.schema());
    assertEquals("age in years", field.doc());
    assertTrue(field.hasDefaultValue());
    assertEquals(Integer.valueOf(10), field.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());

    field.addAlias("years");
    assertTrue(field.aliases().contains("years"));
    expectThrows(UnsupportedOperationException.class, () -> field.aliases().add("cannotMutate"));

    field.addProp("field-prop", "field-value");

    Schema.Field copyWithDifferentSchema = new Schema.Field(field, Schema.create(Schema.Type.LONG));
    assertEquals("age", copyWithDifferentSchema.name());
    assertEquals("age in years", copyWithDifferentSchema.doc());
    assertEquals(Long.valueOf(10L), copyWithDifferentSchema.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, copyWithDifferentSchema.order());
    assertEquals("field-value", copyWithDifferentSchema.getProp("field-prop"));
    assertTrue(copyWithDifferentSchema.aliases().contains("years"));
    assertEquals("age type:INT pos:-1", field.toString());
  }

  @Test
  public void fieldShouldValidateInputsNamesDefaultsAndOrdering() {
    expectThrows(NullPointerException.class, () -> new Schema.Field("missingSchema", null));
    expectThrows(NullPointerException.class,
        () -> new Schema.Field("missingOrder", Schema.create(Schema.Type.INT), null, 1, null));
    expectThrows(SchemaParseException.class, () -> new Schema.Field("bad-name", Schema.create(Schema.Type.INT)));
    expectThrows(AvroTypeException.class, () -> new Schema.Field("badDefault", Schema.create(Schema.Type.INT), null, "not an int"));

    boolean saved = Schema.getValidateDefaults();
    try {
      Schema.setValidateDefaults(false);
      Schema.Field unchecked = new Schema.Field("unchecked", Schema.create(Schema.Type.INT), null, "not an int");
      assertTrue(unchecked.hasDefaultValue());
    } finally {
      Schema.setValidateDefaults(saved);
    }
  }

  @Test
  public void fieldsWithNanDefaultsShouldCompareEqual() {
    Schema.Field left = new Schema.Field("value", Schema.create(Schema.Type.DOUBLE), null, Double.NaN);
    Schema.Field right = new Schema.Field("value", Schema.create(Schema.Type.DOUBLE), null, Double.NaN);

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
  }

  @Test
  public void enumSchemaShouldExposeSymbolsDefaultsOrdinalsAndValidationErrors() {
    Schema color = Schema.createEnum("Color", "color documentation", "example.avro",
        Arrays.asList("RED", "GREEN", "BLUE"), "GREEN");

    assertEquals(Schema.Type.ENUM, color.getType());
    assertEquals("Color", color.getName());
    assertEquals("example.avro", color.getNamespace());
    assertEquals("example.avro.Color", color.getFullName());
    assertEquals("color documentation", color.getDoc());
    assertEquals(Arrays.asList("RED", "GREEN", "BLUE"), color.getEnumSymbols());
    assertEquals("GREEN", color.getEnumDefault());
    assertTrue(color.hasEnumSymbol("RED"));
    assertFalse(color.hasEnumSymbol("MISSING"));
    assertEquals(1, color.getEnumOrdinal("GREEN"));
    assertTrue(color.toString().contains("\"default\":\"GREEN\""));

    expectThrows(IllegalStateException.class, () -> color.getEnumSymbols().add("YELLOW"));
    expectThrows(RuntimeException.class, () -> color.getEnumOrdinal("MISSING"));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadEnum", null, "example.avro", Arrays.asList("A", "A")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadDefaultEnum", null, "example.avro", Arrays.asList("A", "B"), "C"));
  }

  @Test
  public void namedSchemasShouldHandleAliasesAndRejectPrimitiveNames() {
    Schema record = record("Modern", "example.avro", field("id", Schema.Type.INT));
    record.addAlias("Legacy");
    record.addAlias("Other", "legacy.space");

    assertTrue(record.getAliases().contains("example.avro.Legacy"));
    assertTrue(record.getAliases().contains("legacy.space.Other"));

    Schema fixed = Schema.createFixed("Digest", "digest documentation", "example.avro", 16);
    fixed.addAlias("OldDigest");
    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals(16, fixed.getFixedSize());
    assertEquals("digest documentation", fixed.getDoc());
    assertTrue(fixed.getAliases().contains("example.avro.OldDigest"));

    Schema enumSchema = Schema.createEnum("Status", null, "example.avro", Arrays.asList("OK", "KO"));
    enumSchema.addAlias("OldStatus", "legacy.space");
    assertTrue(enumSchema.getAliases().contains("legacy.space.OldStatus"));

    expectThrows(AvroTypeException.class, () -> Schema.createRecord("int", null, null, false));
    expectThrows(AvroTypeException.class,
        () -> Schema.createEnum("string", null, null, Collections.singletonList("A")));
    expectThrows(AvroTypeException.class, () -> Schema.createFixed("boolean", null, null, 1));
  }

  @Test
  public void arrayAndMapSchemasShouldExposeElementAndValueTypes() {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema array = Schema.createArray(intSchema);
    Schema map = Schema.createMap(Schema.create(Schema.Type.STRING));

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(intSchema, array.getElementType());
    assertTrue(array.toString().contains("\"items\":\"int\""));
    assertEquals(array, Schema.createArray(Schema.create(Schema.Type.INT)));

    assertEquals(Schema.Type.MAP, map.getType());
    assertEquals(Schema.Type.STRING, map.getValueType().getType());
    assertTrue(map.toString().contains("\"values\":\"string\""));
    assertEquals(map, Schema.createMap(Schema.create(Schema.Type.STRING)));
  }

  @Test
  public void unionSchemasShouldExposeBranchesIndexesNamesAndRestrictions() {
    Schema user = record("User", "example.avro", field("id", Schema.Type.INT));
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING), user);

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(3, union.getTypes().size());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("example.avro.User"));
    assertEquals(null, union.getIndexNamed("missing"));
    assertTrue(union.getName().contains("union[null, string, User]"));

    expectThrows(IllegalStateException.class, () -> union.getTypes().add(Schema.create(Schema.Type.INT)));
    expectThrows(AvroRuntimeException.class, () -> union.addProp("notAllowed", "value"));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.INT))));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.STRING)));
  }

  @Test
  public void isNullableShouldWorkForNullPrimitiveAndNestedUnionBranches() {
    assertTrue(Schema.create(Schema.Type.NULL).isNullable());
    assertFalse(Schema.create(Schema.Type.INT).isNullable());

    Schema nullableUnion = Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.NULL));
    Schema nonNullableUnion = Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.INT));

    assertTrue(nullableUnion.isNullable());
    assertFalse(nonNullableUnion.isNullable());
  }

  @Test
  public void fixedSchemasShouldExposeSizeNamesAndEquality() {
    Schema first = Schema.createFixed("MD5", "checksum", "example.avro", 16);
    Schema second = Schema.createFixed("MD5", "different docs do not affect schema equality", "example.avro", 16);
    Schema differentSize = Schema.createFixed("MD5", "checksum", "example.avro", 8);

    assertEquals(Schema.Type.FIXED, first.getType());
    assertEquals("MD5", first.getName());
    assertEquals("example.avro.MD5", first.getFullName());
    assertEquals(16, first.getFixedSize());
    assertEquals(first, second);
    assertFalse(first.equals(differentSize));
    assertTrue(first.toString().contains("\"size\":16"));
  }

  @Test
  public void toStringShouldSupportPrettyPrintingAndReferencedSchemas() {
    Schema address = record("Address", "example.avro", field("street", Schema.Type.STRING));
    Schema person = record("Person", "example.avro", new Schema.Field("home", address));

    String compact = person.toString();
    String pretty = person.toString(true);
    String withReference = person.toString(Collections.singleton(address), false);

    assertTrue(compact.contains("\"name\":\"Person\""));
    assertTrue(compact.contains("\"street\""));
    assertTrue(pretty.contains("\n"));
    assertTrue(withReference.contains("\"type\":\"Address\""));
    assertFalse(withReference.contains("\"street\""));
  }

  @Test
  public void parserShouldParseStringsPiecesFilesStreamsAndComments() throws Exception {
    String recordJson = "{\"type\":\"record\",\"name\":\"Person\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"long\"}]}";

    Schema.Parser parser = new Schema.Parser();
    Schema parsed = parser.parse(recordJson);

    assertEquals(Schema.Type.RECORD, parsed.getType());
    assertEquals("example.avro.Person", parsed.getFullName());
    assertEquals(Schema.Type.LONG, parsed.getField("id").schema().getType());

    Schema parsedFromPieces = new Schema.Parser().parse("{\"type\":\"array\",", "\"items\":\"string\"}");
    assertEquals(Schema.Type.ARRAY, parsedFromPieces.getType());
    assertEquals(Schema.Type.STRING, parsedFromPieces.getElementType().getType());

    File temp = File.createTempFile("schema-c1-test", ".avsc");
    try {
      Files.write(temp.toPath(), recordJson.getBytes(StandardCharsets.UTF_8));
      assertEquals(parsed, Schema.parse(temp));
    } finally {
      Files.deleteIfExists(temp.toPath());
    }

    CloseAwareInputStream input = new CloseAwareInputStream("\"int\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.INT, Schema.parse((InputStream) input).getType());
    assertFalse(input.wasClosed());

    Schema withComment = new Schema.Parser().parse("/* a permitted JSON comment */ \"boolean\"");
    assertEquals(Schema.Type.BOOLEAN, withComment.getType());

    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"int\" \"long\""));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{ this is not json }"));
  }

  @Test
  public void parserShouldTrackTypesAddTypesAndResolveReferences() {
    Schema external = record("External", "example.avro", field("value", Schema.Type.STRING));

    Map<String, Schema> typeMap = new HashMap<>();
    typeMap.put(external.getFullName(), external);

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(typeMap));
    assertTrue(parser.getTypes().containsKey("example.avro.External"));

    Schema holder = parser.parse("{\"type\":\"record\",\"name\":\"Holder\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"external\",\"type\":\"External\"}]}");

    assertEquals(external, holder.getField("external").schema());

    Schema another = record("Another", "example.avro", field("id", Schema.Type.INT));
    Schema.Parser iterableParser = new Schema.Parser().addTypes(Collections.singleton(another));
    Schema parsed = iterableParser.parse("{\"type\":\"record\",\"name\":\"UseAnother\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"another\",\"type\":\"Another\"}]}");

    assertEquals(another, parsed.getField("another").schema());
  }

  @Test
  public void parserShouldExposeAndHonorDefaultValidationFlag() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\",\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"default\":\"not an int\"}]}";

    Schema.Parser strictParser = new Schema.Parser();
    assertTrue(strictParser.getValidateDefaults());
    expectThrows(AvroTypeException.class, () -> strictParser.parse(invalidDefault));

    Schema.Parser lenientParser = new Schema.Parser().setValidateDefaults(false);
    assertFalse(lenientParser.getValidateDefaults());

    Schema parsed = lenientParser.parse(invalidDefault);
    Schema.Field idField = parsed.getField("id");
    assertTrue(idField.hasDefaultValue());
    assertNull(idField.defaultVal());
    assertTrue(parsed.toString().contains("\"default\":\"not an int\""));
  }

  @Test
  public void parseShouldHonorNameValidatorSettingAndDeprecatedValidationFlag() {
    String invalidName = "{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}";

    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse(invalidName));

    Schema parsedWithoutValidation = Schema.parse(invalidName, false);
    assertEquals("bad-name", parsedWithoutValidation.getName());

    NameValidator saved = Schema.getNameValidator();
    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      Schema created = Schema.createRecord("also-bad-name", null, null, false);
      assertEquals("also-bad-name", created.getName());
      assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());
    } finally {
      Schema.setNameValidator(saved);
    }
  }

  @Test
  public void parseShouldRejectMalformedObjectFormsAndInvalidAliases() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFields\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"NoSymbols\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"NoSize\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"BadSize\",\"size\":\"16\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":\"Legacy\","
            + "\"fields\":[]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliasValue\",\"aliases\":[1],"
            + "\"fields\":[]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadField\",\"fields\":[{\"type\":\"int\"}]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadField\",\"fields\":[{\"name\":\"x\"}]}"));
  }

  @Test
  public void parseShouldPreserveCustomPropertiesFieldPropertiesAndLogicalTypes() {
    Schema uuid = new Schema.Parser().parse("{\"type\":\"string\",\"logicalType\":\"uuid\",\"custom\":\"value\"}");

    assertEquals(Schema.Type.STRING, uuid.getType());
    assertEquals("value", uuid.getProp("custom"));
    assertNotNull(uuid.getLogicalType());

    Schema record = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"WithProps\",\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"fieldProp\":\"fieldValue\"}],\"recordProp\":\"recordValue\"}");

    assertEquals("recordValue", record.getProp("recordProp"));
    assertEquals("fieldValue", record.getField("id").getProp("fieldProp"));
  }

  @Test
  public void isValidDefaultShouldValidatePrimitiveAndCompositeSchemas() throws Exception {
    assertTrue(Schema.create(Schema.Type.INT).isValidDefault(json("1")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(json("2147483648")));
    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(json("1.5")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("1.5")));
    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertFalse(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("\"true\"")));
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(json("null")));
    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault(json("0")));
    assertTrue(Schema.create(Schema.Type.STRING).isValidDefault(json("\"text\"")));
    assertTrue(Schema.create(Schema.Type.BYTES).isValidDefault(json("\"bytes\"")));

    Schema array = Schema.createArray(Schema.create(Schema.Type.INT));
    assertTrue(array.isValidDefault(json("[1,2,3]")));
    assertFalse(array.isValidDefault(json("[1,\"bad\"]")));

    Schema map = Schema.createMap(Schema.create(Schema.Type.INT));
    assertTrue(map.isValidDefault(json("{\"a\":1,\"b\":2}")));
    assertFalse(map.isValidDefault(json("{\"a\":\"bad\"}")));

    Schema recordWithDefaults = record("WithDefaults", "example.avro",
        new Schema.Field("id", Schema.create(Schema.Type.INT), null, 1),
        new Schema.Field("name", Schema.create(Schema.Type.STRING), null, "anonymous"));
    assertTrue(recordWithDefaults.isValidDefault(json("{\"id\":2,\"name\":\"Alice\"}")));
    assertTrue(recordWithDefaults.isValidDefault(json("{\"id\":2}")));
    assertFalse(recordWithDefaults.isValidDefault(json("{\"id\":\"bad\"}")));

    Schema recordWithoutDefault = record("WithoutDefault", "example.avro", field("id", Schema.Type.INT));
    assertFalse(recordWithoutDefault.isValidDefault(json("{}")));

    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.INT));
    assertTrue(union.isValidDefault(json("null")));
    assertTrue(union.isValidDefault(json("7")));
    assertFalse(union.isValidDefault(json("\"bad\"")));
  }

  @Test
  public void parseJsonHelpersShouldReturnJacksonNodesAndPlainJavaObjects() {
    JsonNode parsed = Schema.parseJson("{\"a\":1,\"b\":[true,\"x\"]}");
    assertEquals(1, parsed.get("a").intValue());
    assertTrue(parsed.get("b").isArray());

    Object object = Schema.parseJsonToObject("[1,true,\"x\"]");
    assertTrue(object instanceof List);
    assertEquals(3, ((List<Object>) object).size());

    expectThrows(RuntimeException.class, () -> Schema.parseJson("{ bad json }"));
  }

  @Test
  public void applyAliasesShouldReturnWriterWhenSchemasAreEqualOrReaderHasNoAliases() {
    Schema writer = record("Writer", "example.avro", field("id", Schema.Type.INT));
    Schema equalReader = record("Writer", "example.avro", field("id", Schema.Type.INT));
    Schema unrelatedReader = record("Reader", "example.avro", field("id", Schema.Type.INT));

    assertSame(writer, Schema.applyAliases(writer, equalReader));
    assertSame(writer, Schema.applyAliases(writer, unrelatedReader));
  }

  @Test
  public void applyAliasesShouldRewriteRecordNamesAndFieldNames() {
    Schema writer = record("OldUser", "example.avro", field("oldId", Schema.Type.INT));

    Schema.Field readerField = field("newId", Schema.Type.INT);
    readerField.addAlias("oldId");
    Schema reader = record("NewUser", "example.avro", readerField);
    reader.addAlias("OldUser");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("example.avro.NewUser", rewritten.getFullName());
    assertNotNull(rewritten.getField("newId"));
    assertEquals(Schema.Type.INT, rewritten.getField("newId").schema().getType());
    assertEquals(null, rewritten.getField("oldId"));
  }

  @Test
  public void applyAliasesShouldRewriteEnumFixedArrayMapAndUnionBranches() {
    Schema writerEnum = Schema.createEnum("OldEnum", null, "example.avro", Arrays.asList("A", "B"), "A");
    Schema readerEnum = Schema.createEnum("NewEnum", null, "example.avro", Arrays.asList("A", "B"), "A");
    readerEnum.addAlias("OldEnum");
    assertEquals("example.avro.NewEnum", Schema.applyAliases(writerEnum, readerEnum).getFullName());

    Schema writerFixed = Schema.createFixed("OldFixed", null, "example.avro", 4);
    Schema readerFixed = Schema.createFixed("NewFixed", null, "example.avro", 4);
    readerFixed.addAlias("OldFixed");
    assertEquals("example.avro.NewFixed", Schema.applyAliases(writerFixed, readerFixed).getFullName());

    Schema writerRecord = record("OldNested", "example.avro", field("id", Schema.Type.INT));
    Schema readerRecord = record("NewNested", "example.avro", field("id", Schema.Type.INT));
    readerRecord.addAlias("OldNested");

    Schema rewrittenArray = Schema.applyAliases(Schema.createArray(writerRecord), Schema.createArray(readerRecord));
    assertEquals("example.avro.NewNested", rewrittenArray.getElementType().getFullName());

    Schema rewrittenMap = Schema.applyAliases(Schema.createMap(writerRecord), Schema.createMap(readerRecord));
    assertEquals("example.avro.NewNested", rewrittenMap.getValueType().getFullName());

    Schema rewrittenUnion = Schema.applyAliases(
        Schema.createUnion(Schema.create(Schema.Type.NULL), writerRecord),
        Schema.createUnion(Schema.create(Schema.Type.NULL), readerRecord));
    assertEquals("example.avro.NewNested", rewrittenUnion.getTypes().get(1).getFullName());
  }

  @Test
  public void javaSerializationShouldUseWriteReplaceAndResolveBackToEquivalentSchema() throws Exception {
    Schema schema = record("SerializableRecord", "example.avro", field("id", Schema.Type.INT));

    Object replacement = schema.writeReplace();

    assertNotNull(replacement);
    assertTrue(replacement instanceof Serializable);

    Object restored = deserialize(serialize(schema));

    assertTrue(restored instanceof Schema);
    assertEquals(schema, restored);
    assertNotSame(schema, restored);
    assertEquals(schema.toString(), restored.toString());
  }

  @Test
  public void recursiveRecordShouldAvoidInfiniteEqualsHashCodeAndStringRendering() {
    Schema node = Schema.createRecord("Node", null, "example.avro", false);
    Schema nullableNode = Schema.createUnion(Schema.create(Schema.Type.NULL), node);
    node.setFields(Collections.singletonList(
        new Schema.Field("next", nullableNode, null, Schema.Field.NULL_DEFAULT_VALUE)));

    assertTrue(node.getField("next").schema().isNullable());
    assertEquals(node, node);
    assertEquals(node.hashCode(), node.hashCode());
    assertTrue(node.toString().contains("\"Node\""));
  }

  @Test
  public void seenPairShouldCompareObjectIdentityNotObjectEquality() {
    Object left = new String("same");
    Object right = new String("same");

    Schema.SeenPair pair = new Schema.SeenPair(left, right);
    Schema.SeenPair sameReferences = new Schema.SeenPair(left, right);
    Schema.SeenPair equalButDifferentReferences = new Schema.SeenPair(new String("same"), right);

    assertEquals(pair, sameReferences);
    assertEquals(pair.hashCode(), sameReferences.hashCode());
    assertFalse(pair.equals(equalButDifferentReferences));
  }

  @Test
  public void namesShouldResolvePrimitivesNamedSchemasNamespacesAndRedefinition() {
    Schema.Names names = new Schema.Names("example.avro");

    assertEquals("example.avro", names.space());
    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema record = record("Named", "example.avro", field("id", Schema.Type.INT));
    names.add(record);

    assertSame(record, names.get("Named"));
    assertSame(record, names.get("example.avro.Named"));
    assertTrue(names.contains(record));

    names.space("other.space");
    assertEquals("other.space", names.space());

    Schema incompatibleRedefinition = record("Named", "example.avro", field("other", Schema.Type.STRING));
    expectThrows(SchemaParseException.class, () -> names.add(incompatibleRedefinition));
  }

  @Test
  public void lockableArrayListShouldAllowMutationBeforeLockAndRejectMutationAfterLock() {
    Schema.LockableArrayList<String> values = new Schema.LockableArrayList<>("a", "b");

    assertTrue(values.add("c"));
    assertTrue(values.remove("b"));
    assertTrue(values.addAll(Collections.singletonList("d")));
    values.lock();

    expectThrows(IllegalStateException.class, () -> values.add("e"));
    expectThrows(IllegalStateException.class, () -> values.remove("a"));
    expectThrows(IllegalStateException.class, () -> values.remove(0));
    expectThrows(IllegalStateException.class, () -> values.addAll(Collections.singletonList("f")));
    expectThrows(IllegalStateException.class, () -> values.addAll(0, Collections.singletonList("g")));
    expectThrows(IllegalStateException.class, () -> values.removeAll(Collections.singleton("d")));
    expectThrows(IllegalStateException.class, () -> values.retainAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, values::clear);
  }

  private static byte[] serialize(Object value) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(output)) {
      objectOutputStream.writeObject(value);
    }
    return output.toByteArray();
  }

  private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
      return objectInputStream.readObject();
    }
  }

  private static final class CloseAwareInputStream extends ByteArrayInputStream implements Closeable {
    private boolean closed;

    private CloseAwareInputStream(byte[] buffer) {
      super(buffer);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }

    private boolean wasClosed() {
      return closed;
    }
  }
}
