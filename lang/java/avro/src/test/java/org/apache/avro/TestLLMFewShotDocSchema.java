/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.avro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Black-box tests for the public Schema API documented in the Avro Java API.
 *
 * <p>The test design intentionally complements parser-heavy tests: schemas are
 * mainly constructed through the documented factory methods and then verified
 * through the documented accessors, mutators and serialization helpers.</p>
 */
@SuppressWarnings("deprecation")
public class TestLLMFewShotDocSchema {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void createShouldBuildEveryPrimitiveSchemaAndExposePrimitiveAccessors() {
    Schema.Type[] primitiveTypes = {
        Schema.Type.NULL,
        Schema.Type.BOOLEAN,
        Schema.Type.INT,
        Schema.Type.LONG,
        Schema.Type.FLOAT,
        Schema.Type.DOUBLE,
        Schema.Type.BYTES,
        Schema.Type.STRING
    };

    for (Schema.Type type : primitiveTypes) {
      Schema schema = Schema.create(type);

      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
      assertNull(schema.getDoc());
      assertNull(schema.getLogicalType());
      assertFalse(schema.isUnion());
      assertEquals(type == Schema.Type.NULL, schema.isNullable());
    }
  }

  @Test
  public void createShouldRejectNonPrimitiveTypes() {
    Schema.Type[] nonPrimitiveTypes = {
        Schema.Type.RECORD,
        Schema.Type.ENUM,
        Schema.Type.ARRAY,
        Schema.Type.MAP,
        Schema.Type.UNION,
        Schema.Type.FIXED
    };

    for (Schema.Type type : nonPrimitiveTypes) {
      expectThrows(AvroRuntimeException.class, () -> Schema.create(type));
    }
  }

  @Test
  public void recordFactoryShouldExposeNameDocNamespaceErrorAndFields() {
    List<Schema.Field> fields = Arrays.asList(
        field("id", Schema.create(Schema.Type.INT), "identifier", 0),
        field("label", Schema.create(Schema.Type.STRING), "label doc"));

    Schema record = Schema.createRecord("User", "user doc", "org.example", true, fields);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("user doc", record.getDoc());
    assertEquals("org.example", record.getNamespace());
    assertEquals("org.example.User", record.getFullName());
    assertTrue(record.isError());
    assertTrue(record.hasFields());
    assertEquals(2, record.getFields().size());
    assertEquals("id", record.getField("id").name());
    assertEquals(0, record.getField("id").pos());
    assertEquals(Schema.Type.INT, record.getField("id").schema().getType());
    assertEquals("identifier", record.getField("id").doc());
    assertTrue(record.getField("id").hasDefaultValue());
    assertEquals(Integer.valueOf(0), record.getField("id").defaultVal());
    assertEquals(1, record.getField("label").pos());
    assertNull(record.getField("missing"));
  }

  @Test
  public void recordFieldsShouldBeUnsetUntilSetAndCanOnlyBeSetOnce() {
    Schema record = Schema.createRecord("LateBound", "doc", "org.example", false);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, () -> record.getFields());
    expectThrows(AvroRuntimeException.class, () -> record.getField("id"));

    record.setFields(Collections.singletonList(field("id", Schema.create(Schema.Type.INT), "doc")));

    assertTrue(record.hasFields());
    assertEquals(1, record.getFields().size());
    assertEquals("id", record.getField("id").name());

    expectThrows(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(field("other", Schema.create(Schema.Type.STRING), "doc"))));
  }

  @Test
  public void setFieldsShouldRejectDuplicateFieldNamesAndReusedFieldInstances() {
    Schema duplicateRecord = Schema.createRecord("DuplicateRecord", null, "org.example", false);

    expectThrows(AvroRuntimeException.class, () -> duplicateRecord.setFields(Arrays.asList(
        field("id", Schema.create(Schema.Type.INT), null),
        field("id", Schema.create(Schema.Type.LONG), null))));

    Schema.Field sharedField = field("shared", Schema.create(Schema.Type.STRING), null);
    Schema firstRecord = Schema.createRecord("First", null, "org.example", false);
    Schema secondRecord = Schema.createRecord("Second", null, "org.example", false);

    firstRecord.setFields(Collections.singletonList(sharedField));
    expectThrows(AvroRuntimeException.class,
        () -> secondRecord.setFields(Collections.singletonList(sharedField)));
  }

  @Test
  public void deprecatedAnonymousRecordFactoryShouldCreateRecordWithFields() {
    Schema anonymous = Schema.createRecord(Collections.singletonList(
        field("value", Schema.create(Schema.Type.STRING), "value doc")));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertTrue(anonymous.hasFields());
    assertEquals("value", anonymous.getField("value").name());
    assertEquals(0, anonymous.getField("value").pos());
  }

  @Test
  public void enumFactoryShouldExposeSymbolsOrdinalsDefaultDocAndNamespace() {
    List<String> symbols = Arrays.asList("CREATED", "RUNNING", "DONE");
    Schema enumSchema = Schema.createEnum("State", "state doc", "org.example", symbols, "RUNNING");

    assertEquals(Schema.Type.ENUM, enumSchema.getType());
    assertEquals("State", enumSchema.getName());
    assertEquals("state doc", enumSchema.getDoc());
    assertEquals("org.example", enumSchema.getNamespace());
    assertEquals("org.example.State", enumSchema.getFullName());
    assertEquals(symbols, enumSchema.getEnumSymbols());
    assertEquals("RUNNING", enumSchema.getEnumDefault());
    assertEquals(0, enumSchema.getEnumOrdinal("CREATED"));
    assertEquals(1, enumSchema.getEnumOrdinal("RUNNING"));
    assertEquals(2, enumSchema.getEnumOrdinal("DONE"));
    assertTrue(enumSchema.hasEnumSymbol("DONE"));
    assertFalse(enumSchema.hasEnumSymbol("MISSING"));
  }

  @Test
  public void enumFactoryWithoutDefaultShouldReturnNullDefault() {
    Schema enumSchema = Schema.createEnum("Priority", null, "org.example",
        Arrays.asList("LOW", "HIGH"));

    assertNull(enumSchema.getEnumDefault());
    assertEquals(Arrays.asList("LOW", "HIGH"), enumSchema.getEnumSymbols());
  }

  @Test
  public void arrayMapUnionAndNullableFactoriesShouldExposeContainedSchemas() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema userRecord = record("User", "org.example", field("id", intSchema, null));

    Schema array = Schema.createArray(stringSchema);
    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(stringSchema, array.getElementType());
    assertFalse(array.isUnion());
    assertFalse(array.isNullable());

    Schema map = Schema.createMap(intSchema);
    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(intSchema, map.getValueType());
    assertFalse(map.isUnion());
    assertFalse(map.isNullable());

    Schema unionFromList = Schema.createUnion(Arrays.asList(
        Schema.create(Schema.Type.NULL), stringSchema, userRecord));
    assertEquals(Schema.Type.UNION, unionFromList.getType());
    assertTrue(unionFromList.isUnion());
    assertTrue(unionFromList.isNullable());
    assertEquals(3, unionFromList.getTypes().size());
    assertEquals(Schema.Type.NULL, unionFromList.getTypes().get(0).getType());
    assertEquals(Integer.valueOf(1), unionFromList.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), unionFromList.getIndexNamed("org.example.User"));
    assertNull(unionFromList.getIndexNamed("org.example.Missing"));

    Schema unionFromVarargs = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema);
    assertTrue(unionFromVarargs.isUnion());
    assertTrue(unionFromVarargs.isNullable());
    assertEquals(2, unionFromVarargs.getTypes().size());
  }

  @Test
  public void fixedFactoryShouldExposeNameDocNamespaceAndSize() {
    Schema fixed = Schema.createFixed("Digest", "digest doc", "org.example", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Digest", fixed.getName());
    assertEquals("digest doc", fixed.getDoc());
    assertEquals("org.example", fixed.getNamespace());
    assertEquals("org.example.Digest", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
  }

  @Test
  public void namedSchemasShouldSupportAliases() {
    Schema record = record("User", "org.example", field("id", Schema.create(Schema.Type.INT), null));
    Schema enumSchema = Schema.createEnum("State", null, "org.example", Arrays.asList("ON", "OFF"));
    Schema fixed = Schema.createFixed("Hash", null, "org.example", 8);

    record.addAlias("LegacyUser");
    record.addAlias("ExternalUser", "legacy.example");
    enumSchema.addAlias("LegacyState");
    fixed.addAlias("LegacyHash");

    assertTrue(record.getAliases().contains("org.example.LegacyUser"));
    assertTrue(record.getAliases().contains("legacy.example.ExternalUser"));
    assertTrue(enumSchema.getAliases().contains("org.example.LegacyState"));
    assertTrue(fixed.getAliases().contains("org.example.LegacyHash"));
  }

  @Test
  public void addPropShouldStoreStringAndObjectPropertiesAndRejectIllegalChanges() {
    Schema schema = Schema.create(Schema.Type.STRING);

    assertFalse(schema.hasProps());
    schema.addProp("owner", "qa-team");
    schema.addProp("priority", Integer.valueOf(7));

    assertTrue(schema.hasProps());
    assertTrue(schema.propsContainsKey("owner"));
    assertEquals("qa-team", schema.getProp("owner"));
    assertEquals("qa-team", schema.getObjectProp("owner"));
    assertEquals(7, ((Number) schema.getObjectProp("priority")).intValue());
    assertNull(schema.getProp("priority"));
    assertEquals("fallback", schema.getObjectProp("missing", "fallback"));

    schema.addProp("owner", "qa-team");
    expectThrows(AvroRuntimeException.class, () -> schema.addProp("owner", "other-team"));
    expectThrows(RuntimeException.class, () -> schema.addProp(null, "value"));
    expectThrows(RuntimeException.class, () -> schema.addProp("null-value", (String) null));
    expectThrows(UnsupportedOperationException.class,
        () -> schema.getObjectProps().put("new", "value"));
  }

  @Test
  public void propertyCopyAndIterationShouldExposeAllDefinedProperties() {
    Schema source = Schema.create(Schema.Type.STRING);
    source.addProp("owner", "qa-team");
    source.addProp("score", Integer.valueOf(3));

    Schema copy = Schema.create(Schema.Type.STRING);
    copy.addAllProps(source);

    assertEquals("qa-team", copy.getProp("owner"));
    assertEquals(3, ((Number) copy.getObjectProp("score")).intValue());

    Map<String, Object> visited = new LinkedHashMap<>();
    copy.forEachProperty(visited::put);

    assertEquals("qa-team", visited.get("owner"));
    assertEquals(3, ((Number) visited.get("score")).intValue());
  }

  @Test
  public void logicalTypeShouldBeAppliedAndExposed() {
    Schema uuidSchema = LogicalTypes.uuid().addToSchema(Schema.create(Schema.Type.STRING));
    Schema dateSchema = LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));

    assertNotNull(uuidSchema.getLogicalType());
    assertEquals("uuid", uuidSchema.getLogicalType().getName());
    assertEquals("uuid", uuidSchema.getProp("logicalType"));

    assertNotNull(dateSchema.getLogicalType());
    assertEquals("date", dateSchema.getLogicalType().getName());
    assertEquals("date", dateSchema.getProp("logicalType"));
  }

  @Test
  public void toStringVariantsShouldRenderJsonAndSupportRoundTripParsing() {
    Schema record = record("User", "org.example",
        field("id", Schema.create(Schema.Type.INT), "identifier", 1),
        field("name", Schema.create(Schema.Type.STRING), "name doc", "anonymous"));
    record.addProp("domain", "test");

    String compactJson = record.toString();
    String prettyJson = record.toString(true);

    assertEquals(record, new Schema.Parser().parse(compactJson));
    assertEquals(record, new Schema.Parser().parse(prettyJson));
    assertTrue(compactJson.contains("\"type\":\"record\""));
    assertTrue(prettyJson.contains("\n"));

    Schema address = record("Address", "org.example",
        field("street", Schema.create(Schema.Type.STRING), "street doc"));
    Schema person = record("Person", "org.example",
        field("address", address, "address doc"));

    String withReferencedSchema = person.toString(Collections.singleton(address), true);
    assertNotNull(withReferencedSchema);
    assertTrue(withReferencedSchema.contains("Address"));
  }

  @Test
  public void equalsAndHashCodeShouldReflectSchemaStructureAndProperties() {
    Schema left = record("User", "org.example",
        field("id", Schema.create(Schema.Type.INT), null));
    Schema right = record("User", "org.example",
        field("id", Schema.create(Schema.Type.INT), null));
    Schema different = record("User", "org.example",
        field("id", Schema.create(Schema.Type.LONG), null));

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertFalse(left.equals(different));

    right.addProp("owner", "qa-team");
    assertFalse(left.equals(right));
  }

  @Test
  public void deprecatedParseMethodsShouldReadSchemasFromStringStreamAndFile() throws IOException {
    String schemaJson = "{"
        + "\"type\":\"record\","
        + "\"name\":\"ParsedUser\","
        + "\"namespace\":\"org.example\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}";

    Schema fromString = Schema.parse(schemaJson);
    Schema fromStringWithValidation = Schema.parse(schemaJson, true);
    Schema fromStringWithoutValidation = Schema.parse(schemaJson, false);
    Schema fromStream = Schema.parse(new ByteArrayInputStream(schemaJson.getBytes(StandardCharsets.UTF_8)));

    File temp = File.createTempFile("schema-api-test", ".avsc");
    try {
      Files.write(temp.toPath(), schemaJson.getBytes(StandardCharsets.UTF_8));
      Schema fromFile = Schema.parse(temp);

      assertEquals(fromString, fromStringWithValidation);
      assertEquals(fromString, fromStringWithoutValidation);
      assertEquals(fromString, fromStream);
      assertEquals(fromString, fromFile);
      assertEquals("ParsedUser", fromFile.getName());
    } finally {
      Files.deleteIfExists(temp.toPath());
    }
  }

  @Test
  public void parseJsonToObjectShouldMapJsonValuesToJavaValues() {
    assertSame(JsonProperties.NULL_VALUE, Schema.parseJsonToObject("null"));
    assertEquals(Boolean.TRUE, Schema.parseJsonToObject("true"));
    assertEquals("value", Schema.parseJsonToObject("\"value\""));
    assertEquals(42, ((Number) Schema.parseJsonToObject("42")).intValue());

    Object array = Schema.parseJsonToObject("[1,\"two\",true]");
    assertTrue(array instanceof List);
    assertEquals(3, ((List<?>) array).size());

    Object object = Schema.parseJsonToObject("{\"answer\":42,\"enabled\":true}");
    assertTrue(object instanceof Map);
    assertEquals(42, ((Number) ((Map<?, ?>) object).get("answer")).intValue());
    assertEquals(Boolean.TRUE, ((Map<?, ?>) object).get("enabled"));
  }

  @Test
  public void isValidDefaultShouldAcceptJsonValuesThatMatchTheSchema() throws IOException {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema);
    Schema enumSchema = Schema.createEnum("Color", null, "org.example", Arrays.asList("RED", "GREEN"));
    Schema arraySchema = Schema.createArray(intSchema);
    Schema mapSchema = Schema.createMap(stringSchema);

    assertTrue(intSchema.isValidDefault(json("7")));
    assertFalse(intSchema.isValidDefault(json("\"7\"")));

    assertTrue(stringSchema.isValidDefault(json("\"abc\"")));
    assertFalse(stringSchema.isValidDefault(json("123")));

    assertTrue(nullSchema.isValidDefault(json("null")));
    assertFalse(nullSchema.isValidDefault(json("false")));

    assertTrue(nullableString.isValidDefault(json("null")));
    assertTrue(nullableString.isValidDefault(json("\"abc\"")));

    assertTrue(enumSchema.isValidDefault(json("\"RED\"")));
    assertFalse(enumSchema.isValidDefault(json("\"BLUE\"")));

    assertTrue(arraySchema.isValidDefault(json("[1,2,3]")));
    assertFalse(arraySchema.isValidDefault(json("[1,\"bad\"]")));

    assertTrue(mapSchema.isValidDefault(json("{\"a\":\"one\"}")));
    assertFalse(mapSchema.isValidDefault(json("{\"a\":1}")));
  }

  @Test
  public void applyAliasesShouldRewriteWriterNamesAndFieldNamesUsingReaderAliases() {
    Schema writer = record("LegacyUser", "legacy.example",
        field("legacyId", Schema.create(Schema.Type.INT), "legacy id"),
        field("name", Schema.create(Schema.Type.STRING), "name"));

    Schema.Field readerId = field("id", Schema.create(Schema.Type.INT), "new id");
    readerId.addAlias("legacyId");
    Schema reader = record("User", "org.example", readerId,
        field("name", Schema.create(Schema.Type.STRING), "name"));
    reader.addAlias("LegacyUser", "legacy.example");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("org.example.User", rewritten.getFullName());
    assertNotNull(rewritten.getField("id"));
    assertNull(rewritten.getField("legacyId"));
    assertEquals(Schema.Type.INT, rewritten.getField("id").schema().getType());
    assertEquals("name", rewritten.getField("name").name());
  }

  @Test
  public void deprecatedStaticConfigurationMethodsShouldRoundTripAndBeRestorable() {
    boolean originalValidateDefaults = Schema.getValidateDefaults();
    NameValidator originalValidator = Schema.getNameValidator();

    try {
      Schema.setValidateDefaults(!originalValidateDefaults);
      assertEquals(!originalValidateDefaults, Schema.getValidateDefaults());

      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());

      Schema.setNameValidator(NameValidator.STRICT_VALIDATOR);
      assertSame(NameValidator.STRICT_VALIDATOR, Schema.getNameValidator());
    } finally {
      Schema.setValidateDefaults(originalValidateDefaults);
      Schema.setNameValidator(originalValidator);
    }
  }

  @Test
  public void unsupportedAccessorsShouldFailOnWrongSchemaTypes() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);

    expectThrows(AvroRuntimeException.class, () -> stringSchema.getField("id"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getFields());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.hasFields());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.setFields(Collections.emptyList()));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getEnumSymbols());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getEnumDefault());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getNamespace());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.addAlias("Alias", "org.example"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getAliases());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.isError());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getElementType());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getValueType());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getTypes());
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getIndexNamed("string"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getFixedSize());
  }

  @Test
  public void writeReplaceShouldReturnSerializableReplacement() {
    Schema schema = record("SerializableRecord", "org.example",
        field("id", Schema.create(Schema.Type.INT), null));

    Object replacement = schema.writeReplace();

    assertNotNull(replacement);
    assertTrue(replacement instanceof Serializable);
  }

  private static JsonNode json(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  private static Schema record(String name, String namespace, Schema.Field... fields) {
    return Schema.createRecord(name, null, namespace, false, Arrays.asList(fields));
  }

  private static Schema.Field field(String name, Schema schema, String doc) {
    return new Schema.Field(name, schema, doc);
  }

  private static Schema.Field field(String name, Schema schema, String doc, Object defaultValue) {
    return new Schema.Field(name, schema, doc, defaultValue);
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      fail("Expected exception of type " + expectedType.getName()
          + " but got " + actual.getClass().getName() + ": " + actual.getMessage());
    }

    fail("Expected exception of type " + expectedType.getName());
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }
}
