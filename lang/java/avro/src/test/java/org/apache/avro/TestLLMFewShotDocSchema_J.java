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
import static org.junit.Assert.assertNotEquals;
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
import java.util.Set;

import org.junit.Test;

/**
 * Additional black-box tests for Schema.java based on the public Avro API
 * documentation. These tests intentionally focus on documented factory methods,
 * accessors, mutators, nested Field/Parser behavior, and negative cases rather
 * than on implementation details.
 */
@SuppressWarnings("deprecation")
public class TestLLMFewShotDocSchema_J {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void primitiveSchemasShouldExposeDocumentedNamesFullNamesAndNullability() {
    for (Schema.Type type : Arrays.asList(
        Schema.Type.NULL,
        Schema.Type.BOOLEAN,
        Schema.Type.INT,
        Schema.Type.LONG,
        Schema.Type.FLOAT,
        Schema.Type.DOUBLE,
        Schema.Type.BYTES,
        Schema.Type.STRING)) {
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
  public void createShouldRejectComplexTypesWhenPrimitiveFactoryIsUsed() {
    for (Schema.Type type : Arrays.asList(
        Schema.Type.RECORD,
        Schema.Type.ENUM,
        Schema.Type.ARRAY,
        Schema.Type.MAP,
        Schema.Type.UNION,
        Schema.Type.FIXED)) {
      expectThrows(AvroRuntimeException.class, () -> Schema.create(type));
    }
  }

  @Test
  public void namedRecordShouldSupportUnsetThenSetFieldsExactlyOnce() {
    Schema record = Schema.createRecord("LateRecord", "record doc", "org.example", false);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("LateRecord", record.getName());
    assertEquals("record doc", record.getDoc());
    assertEquals("org.example", record.getNamespace());
    assertEquals("org.example.LateRecord", record.getFullName());
    assertFalse(record.isError());
    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, record::getFields);

    Schema.Field id = newField("id", Schema.create(Schema.Type.INT), "identifier", 0);
    Schema.Field label = newField("label", Schema.create(Schema.Type.STRING), "label", "none");
    record.setFields(Arrays.asList(id, label));

    assertTrue(record.hasFields());
    assertEquals(2, record.getFields().size());
    assertSame(id, record.getField("id"));
    assertSame(label, record.getField("label"));
    assertNull(record.getField("missing"));
    assertEquals(0, record.getField("id").pos());
    assertEquals(1, record.getField("label").pos());

    expectThrows(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(newField("other", Schema.create(Schema.Type.LONG), null))));
  }

  @Test
  public void recordWithFieldsFactoryShouldAssignPositionsAndExposeDefaults() {
    Schema.Field required = newField("required", Schema.create(Schema.Type.INT), "required doc");
    Schema.Field optional = newField("optional", nullable(Schema.create(Schema.Type.STRING)), "optional doc",
        Schema.Field.NULL_DEFAULT_VALUE);
    Schema record = Schema.createRecord("DefaultsRecord", null, "org.example", false,
        Arrays.asList(required, optional));

    assertEquals(0, record.getField("required").pos());
    assertEquals(1, record.getField("optional").pos());
    assertFalse(record.getField("required").hasDefaultValue());
    assertTrue(record.getField("optional").hasDefaultValue());
    assertSame(JsonProperties.NULL_VALUE, record.getField("optional").defaultVal());
    assertTrue(record.getField("optional").schema().isNullable());
  }

  @Test
  public void anonymousRecordFactoryShouldCreateRecordButWithoutStableName() {
    Schema anonymous = Schema.createRecord(Collections.singletonList(
        newField("value", Schema.create(Schema.Type.STRING), "value doc")));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertTrue(anonymous.hasFields());
    assertEquals("value", anonymous.getField("value").name());
    assertEquals(0, anonymous.getField("value").pos());
    assertNotNull(anonymous.toString());
  }

  @Test
  public void setFieldsShouldRejectDuplicateNamesAndReusedFieldInstances() {
    Schema duplicateNames = Schema.createRecord("DuplicateNames", null, "org.example", false);
    expectThrows(AvroRuntimeException.class, () -> duplicateNames.setFields(Arrays.asList(
        newField("id", Schema.create(Schema.Type.INT), null),
        newField("id", Schema.create(Schema.Type.LONG), null))));

    Schema.Field shared = newField("shared", Schema.create(Schema.Type.STRING), null);
    Schema first = Schema.createRecord("FirstOwner", null, "org.example", false);
    Schema second = Schema.createRecord("SecondOwner", null, "org.example", false);

    first.setFields(Collections.singletonList(shared));
    expectThrows(AvroRuntimeException.class, () -> second.setFields(Collections.singletonList(shared)));
  }

  @Test
  public void fieldConstructorsShouldExposeOrderDefaultAliasPropertiesAndCopyBehavior() {
    Schema.Field original = new Schema.Field("count", Schema.create(Schema.Type.INT), "count doc", 10,
        Schema.Field.Order.DESCENDING);
    original.addAlias("old_count");
    original.addProp("source", "manual");
    original.addProp("rank", Integer.valueOf(3));

    assertEquals("count", original.name());
    assertEquals(Schema.Type.INT, original.schema().getType());
    assertEquals("count doc", original.doc());
    assertTrue(original.hasDefaultValue());
    assertEquals(Integer.valueOf(10), original.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, original.order());
    assertTrue(original.aliases().contains("old_count"));
    assertEquals("manual", original.getProp("source"));
    assertEquals(3, ((Number) original.getObjectProp("rank")).intValue());
    assertTrue(original.toString().contains("count"));

    Schema.Field copyWithDifferentSchema = new Schema.Field(original, Schema.create(Schema.Type.LONG));
    assertEquals("count", copyWithDifferentSchema.name());
    assertEquals(Schema.Type.LONG, copyWithDifferentSchema.schema().getType());
    assertEquals(original.doc(), copyWithDifferentSchema.doc());
    assertEquals(((Number) original.defaultVal()).longValue(), ((Number) copyWithDifferentSchema.defaultVal()).longValue());
    assertEquals(original.order(), copyWithDifferentSchema.order());
    assertTrue(copyWithDifferentSchema.aliases().contains("old_count"));
    assertEquals("manual", copyWithDifferentSchema.getProp("source"));
  }

  @Test
  public void fieldAliasesShouldBeReturnedAsUnmodifiableSet() {
    Schema.Field field = newField("newName", Schema.create(Schema.Type.STRING), null);
    field.addAlias("oldName");

    Set<String> aliases = field.aliases();
    assertTrue(aliases.contains("oldName"));
    expectThrows(UnsupportedOperationException.class, () -> aliases.add("anotherOldName"));
  }

  @Test
  public void enumFactoryShouldExposeSymbolsOrdinalsDefaultsAndRejectDuplicateSymbols() {
    Schema state = Schema.createEnum("State", "state doc", "org.example",
        Arrays.asList("CREATED", "RUNNING", "DONE"), "RUNNING");

    assertEquals(Schema.Type.ENUM, state.getType());
    assertEquals("State", state.getName());
    assertEquals("state doc", state.getDoc());
    assertEquals("org.example", state.getNamespace());
    assertEquals("org.example.State", state.getFullName());
    assertEquals(Arrays.asList("CREATED", "RUNNING", "DONE"), state.getEnumSymbols());
    assertEquals("RUNNING", state.getEnumDefault());
    assertEquals(0, state.getEnumOrdinal("CREATED"));
    assertEquals(1, state.getEnumOrdinal("RUNNING"));
    assertEquals(2, state.getEnumOrdinal("DONE"));
    assertTrue(state.hasEnumSymbol("DONE"));
    assertFalse(state.hasEnumSymbol("MISSING"));

    expectThrows(RuntimeException.class, () -> Schema.createEnum("BadEnum", null, "org.example",
        Arrays.asList("A", "A")));
  }

  @Test
  public void enumSymbolsShouldBeUnmodifiableAndDefaultMayBeAbsent() {
    Schema priority = Schema.createEnum("Priority", null, "org.example", Arrays.asList("LOW", "HIGH"));

    assertNull(priority.getEnumDefault());
    assertEquals(Arrays.asList("LOW", "HIGH"), priority.getEnumSymbols());
    expectThrows(RuntimeException.class, () -> priority.getEnumSymbols().add("MEDIUM"));
  }

  @Test
  public void fixedFactoryShouldExposeQualifiedNameSizeAliasesAndRejectInvalidSize() {
    Schema fixed = Schema.createFixed("Digest", "digest doc", "org.example", 16);
    fixed.addAlias("LegacyDigest");
    fixed.addAlias("ExternalDigest", "legacy.example");

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Digest", fixed.getName());
    assertEquals("digest doc", fixed.getDoc());
    assertEquals("org.example", fixed.getNamespace());
    assertEquals("org.example.Digest", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
    assertTrue(fixed.getAliases().contains("org.example.LegacyDigest"));
    assertTrue(fixed.getAliases().contains("legacy.example.ExternalDigest"));

    expectThrows(RuntimeException.class, () -> Schema.createFixed("BadFixed", null, "org.example", -1));
  }

  @Test
  public void arraysMapsAndUnionsShouldExposeContainedSchemasAndIndexes() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema longSchema = Schema.create(Schema.Type.LONG);
    Schema record = record("User", "org.example", newField("id", longSchema, null));
    Schema fixed = Schema.createFixed("Hash", null, "org.example", 8);

    Schema array = Schema.createArray(stringSchema);
    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(stringSchema, array.getElementType());
    assertFalse(array.isUnion());
    assertFalse(array.isNullable());

    Schema map = Schema.createMap(longSchema);
    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(longSchema, map.getValueType());
    assertFalse(map.isUnion());
    assertFalse(map.isNullable());

    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema, record, fixed);
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(4, union.getTypes().size());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("org.example.User"));
    assertEquals(Integer.valueOf(3), union.getIndexNamed("org.example.Hash"));
    assertNull(union.getIndexNamed("org.example.Missing"));
  }

  @Test
  public void unionShouldRejectNestedUnionAndDuplicateUnnamedBranches() {
    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));

    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.INT), Schema.create(Schema.Type.INT)));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.NULL), nullableString));
  }

  @Test
  public void collectionViewsForFieldsAndUnionTypesShouldBeUnmodifiableAndAliasesShouldBeExposed() {
    Schema record = record("ImmutableRecord", "org.example",
        newField("id", Schema.create(Schema.Type.INT), null));
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));

    record.addAlias("LegacyImmutableRecord");

    expectThrows(RuntimeException.class,
        () -> record.getFields().add(newField("extra", Schema.create(Schema.Type.STRING), null)));
    expectThrows(RuntimeException.class,
        () -> union.getTypes().add(Schema.create(Schema.Type.BOOLEAN)));

    assertTrue(record.getAliases().contains("org.example.LegacyImmutableRecord"));
  }

  @Test
  public void schemaPropertiesShouldSupportStringObjectCopyIterationAndIllegalUpdates() {
    Schema source = Schema.create(Schema.Type.STRING);
    Map<String, Object> nested = new LinkedHashMap<>();
    nested.put("min", Integer.valueOf(1));
    nested.put("max", Integer.valueOf(5));

    assertFalse(source.hasProps());
    source.addProp("owner", "qa-team");
    source.addProp("nested", nested);
    source.addProp("enabled", Boolean.TRUE);

    assertTrue(source.hasProps());
    assertTrue(source.propsContainsKey("owner"));
    assertTrue(source.propsContainsKey("nested"));
    assertEquals("qa-team", source.getProp("owner"));
    assertEquals("qa-team", source.getObjectProp("owner"));
    assertNull(source.getProp("nested"));
    assertEquals(Boolean.TRUE, source.getObjectProp("enabled"));
    assertEquals("fallback", source.getObjectProp("missing", "fallback"));

    Schema copy = Schema.create(Schema.Type.STRING);
    copy.addAllProps(source);
    assertEquals("qa-team", copy.getProp("owner"));
    assertTrue(copy.getObjectProp("nested") instanceof Map);

    Map<String, Object> visited = new LinkedHashMap<>();
    copy.forEachProperty(visited::put);
    assertEquals("qa-team", visited.get("owner"));
    assertEquals(Boolean.TRUE, visited.get("enabled"));

    source.addProp("owner", "qa-team");
    expectThrows(AvroRuntimeException.class, () -> source.addProp("owner", "another-team"));
    expectThrows(RuntimeException.class, () -> source.addProp(null, "value"));
    expectThrows(RuntimeException.class, () -> source.addProp("null-value", (String) null));
    expectThrows(UnsupportedOperationException.class, () -> source.getObjectProps().put("new", "value"));
  }

  @Test
  public void logicalTypesShouldBeExposedWhenTheyAreCompatibleAndIgnoredWhenIncompatible() {
    Schema uuid = LogicalTypes.uuid().addToSchema(Schema.create(Schema.Type.STRING));
    Schema date = LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));
    Schema decimalBytes = LogicalTypes.decimal(6, 2).addToSchema(Schema.create(Schema.Type.BYTES));
    Schema decimalFixed = LogicalTypes.decimal(4, 1).addToSchema(Schema.createFixed("Amount", null, "org.example", 8));

    assertEquals("uuid", uuid.getLogicalType().getName());
    assertEquals("date", date.getLogicalType().getName());
    assertEquals("decimal", decimalBytes.getLogicalType().getName());
    assertEquals("decimal", decimalFixed.getLogicalType().getName());
    assertEquals("uuid", uuid.getProp("logicalType"));
    assertEquals("decimal", decimalFixed.getProp("logicalType"));

    Schema incompatible = new Schema.Parser().parse("{\"type\":\"string\",\"logicalType\":\"date\"}");
    assertNull(incompatible.getLogicalType());
    assertEquals("date", incompatible.getProp("logicalType"));
  }

  @Test
  public void recursiveRecordShouldRenderAsJsonAndRemainUsableThroughAccessors() {
    Schema node = Schema.createRecord("Node", "recursive node", "org.example", false);
    Schema nullableNode = Schema.createUnion(Schema.create(Schema.Type.NULL), node);
    node.setFields(Arrays.asList(
        newField("value", Schema.create(Schema.Type.INT), "node value"),
        newField("next", nullableNode, "next node", Schema.Field.NULL_DEFAULT_VALUE)));

    assertTrue(node.hasFields());
    assertEquals("org.example.Node", node.getFullName());
    assertTrue(node.getField("next").schema().isNullable());
    assertEquals(Integer.valueOf(1), node.getField("next").schema().getIndexNamed("org.example.Node"));
    assertTrue(node.toString().contains("Node"));
    assertTrue(node.toString(true).contains("\n"));
  }

  @Test
  public void toStringVariantsShouldRoundTripAndRespectReferencedSchemasArgument() {
    Schema address = record("Address", "org.example",
        newField("street", Schema.create(Schema.Type.STRING), "street doc"));
    Schema person = record("Person", "org.example",
        newField("address", address, "address doc"));
    person.addProp("domain", "people");

    String compact = person.toString();
    String pretty = person.toString(true);
    String withReference = person.toString(Collections.singleton(address), true);

    assertEquals(person, new Schema.Parser().parse(compact));
    assertEquals(person, new Schema.Parser().parse(pretty));
    assertTrue(compact.contains("\"type\":\"record\""));
    assertTrue(pretty.contains("\n"));
    assertNotNull(withReference);
    assertTrue(withReference.contains("Person"));
  }

  @Test
  public void equalsHashCodeAndNotEqualsShouldReflectNameFieldsAndProperties() {
    Schema left = record("User", "org.example", newField("id", Schema.create(Schema.Type.INT), null));
    Schema same = record("User", "org.example", newField("id", Schema.create(Schema.Type.INT), null));
    Schema differentFieldType = record("User", "org.example", newField("id", Schema.create(Schema.Type.LONG), null));
    Schema differentName = record("Account", "org.example", newField("id", Schema.create(Schema.Type.INT), null));

    assertEquals(left, same);
    assertEquals(left.hashCode(), same.hashCode());
    assertNotEquals(left, differentFieldType);
    assertNotEquals(left, differentName);
    assertNotEquals(left, null);
    assertNotEquals(left, "not a schema");

    same.addProp("owner", "qa-team");
    assertNotEquals(left, same);
  }

  @Test
  public void deprecatedStaticParseMethodsShouldReadStringStreamAndFileInputs() throws IOException {
    String schemaJson = recordJson("ParsedUser", "org.example", "id", "int");

    Schema fromString = Schema.parse(schemaJson);
    Schema fromStringValidated = Schema.parse(schemaJson, true);
    Schema fromStringNotValidated = Schema.parse(schemaJson, false);
    Schema fromStream = Schema.parse(new ByteArrayInputStream(schemaJson.getBytes(StandardCharsets.UTF_8)));

    File temp = File.createTempFile("schema-doc-blackbox", ".avsc");
    try {
      Files.write(temp.toPath(), schemaJson.getBytes(StandardCharsets.UTF_8));
      Schema fromFile = Schema.parse(temp);

      assertEquals(fromString, fromStringValidated);
      assertEquals(fromString, fromStringNotValidated);
      assertEquals(fromString, fromStream);
      assertEquals(fromString, fromFile);
      assertEquals("org.example.ParsedUser", fromFile.getFullName());
    } finally {
      Files.deleteIfExists(temp.toPath());
    }
  }

  @Test
  public void parseJsonToObjectShouldConvertJsonPrimitivesCollectionsAndRejectInvalidJson() {
    assertSame(JsonProperties.NULL_VALUE, Schema.parseJsonToObject("null"));
    assertEquals(Boolean.TRUE, Schema.parseJsonToObject("true"));
    assertEquals("text", Schema.parseJsonToObject("\"text\""));
    assertEquals(42, ((Number) Schema.parseJsonToObject("42")).intValue());

    Object list = Schema.parseJsonToObject("[1,\"two\",false]");
    assertTrue(list instanceof List);
    assertEquals(3, ((List<?>) list).size());

    Object object = Schema.parseJsonToObject("{\"answer\":42,\"enabled\":true}");
    assertTrue(object instanceof Map);
    assertEquals(42, ((Number) ((Map<?, ?>) object).get("answer")).intValue());
    assertEquals(Boolean.TRUE, ((Map<?, ?>) object).get("enabled"));

    expectThrows(RuntimeException.class, () -> Schema.parseJsonToObject("{not-json"));
  }

  @Test
  public void isValidDefaultShouldExercisePrimitiveCollectionRecordAndEnumDefaults() throws IOException {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema longSchema = Schema.create(Schema.Type.LONG);
    Schema floatSchema = Schema.create(Schema.Type.FLOAT);
    Schema doubleSchema = Schema.create(Schema.Type.DOUBLE);
    Schema booleanSchema = Schema.create(Schema.Type.BOOLEAN);
    Schema bytesSchema = Schema.create(Schema.Type.BYTES);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema enumSchema = Schema.createEnum("Color", null, "org.example", Arrays.asList("RED", "GREEN"));
    Schema arraySchema = Schema.createArray(intSchema);
    Schema mapSchema = Schema.createMap(stringSchema);
    Schema recordSchema = record("DefaultRecord", "org.example",
        newField("id", intSchema, null),
        newField("name", stringSchema, null));

    assertTrue(intSchema.isValidDefault(json("7")));
    assertFalse(intSchema.isValidDefault(json("\"7\"")));
    assertTrue(longSchema.isValidDefault(json("7")));
    assertTrue(floatSchema.isValidDefault(json("7.5")));
    assertTrue(doubleSchema.isValidDefault(json("7.5")));
    assertTrue(booleanSchema.isValidDefault(json("true")));
    assertFalse(booleanSchema.isValidDefault(json("\"true\"")));
    assertTrue(bytesSchema.isValidDefault(json("\"abc\"")));
    assertFalse(bytesSchema.isValidDefault(json("123")));
    assertTrue(stringSchema.isValidDefault(json("\"abc\"")));
    assertFalse(stringSchema.isValidDefault(json("123")));
    assertTrue(nullSchema.isValidDefault(json("null")));
    assertFalse(nullSchema.isValidDefault(json("false")));

    assertTrue(enumSchema.isValidDefault(json("\"RED\"")));
    assertTrue(enumSchema.isValidDefault(json("\"BLUE\"")));
    assertFalse(enumSchema.isValidDefault(json("123")));
    assertTrue(enumSchema.hasEnumSymbol("RED"));
    assertFalse(enumSchema.hasEnumSymbol("BLUE"));

    assertTrue(arraySchema.isValidDefault(json("[1,2,3]")));
    assertFalse(arraySchema.isValidDefault(json("[1,\"bad\"]")));
    assertTrue(mapSchema.isValidDefault(json("{\"a\":\"one\"}")));
    assertFalse(mapSchema.isValidDefault(json("{\"a\":1}")));
    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"name\":\"alice\"}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":\"bad\",\"name\":\"alice\"}")));
  }

  @Test
  public void applyAliasesShouldRewriteRecordFieldEnumAndFixedNames() {
    Schema writerEnum = Schema.createEnum("OldState", null, "legacy.example", Arrays.asList("ON", "OFF"));
    Schema writerFixed = Schema.createFixed("OldHash", null, "legacy.example", 8);
    Schema writer = record("OldUser", "legacy.example",
        newField("oldId", Schema.create(Schema.Type.INT), "old id"),
        newField("state", writerEnum, "state"),
        newField("hash", writerFixed, "hash"));

    Schema readerEnum = Schema.createEnum("State", null, "org.example", Arrays.asList("ON", "OFF"));
    readerEnum.addAlias("OldState", "legacy.example");
    Schema readerFixed = Schema.createFixed("Hash", null, "org.example", 8);
    readerFixed.addAlias("OldHash", "legacy.example");
    Schema.Field id = newField("id", Schema.create(Schema.Type.INT), "new id");
    id.addAlias("oldId");
    Schema reader = record("User", "org.example", id,
        newField("state", readerEnum, "state"),
        newField("hash", readerFixed, "hash"));
    reader.addAlias("OldUser", "legacy.example");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("org.example.User", rewritten.getFullName());
    assertNotNull(rewritten.getField("id"));
    assertNull(rewritten.getField("oldId"));
    assertEquals("org.example.State", rewritten.getField("state").schema().getFullName());
    assertEquals("org.example.Hash", rewritten.getField("hash").schema().getFullName());
  }

  @Test
  public void parserShouldTrackNamedTypesParseMultipleChunksAndKeepInputStreamOpen() throws IOException {
    String addressJson = recordJson("Address", "org.example", "street", "string");
    String personPrefix = "{\"type\":\"record\",\"name\":\"Person\",\"namespace\":\"org.example\",\"fields\":[";
    String personSuffix = "{\"name\":\"address\",\"type\":\"Address\"}]}";

    Schema.Parser parser = new Schema.Parser();
    Schema address = parser.parse(addressJson);
    assertEquals("org.example.Address", address.getFullName());
    assertTrue(parser.getTypes().containsKey("org.example.Address"));

    Schema person = parser.parse(personPrefix, personSuffix);
    assertEquals("org.example.Person", person.getFullName());
    assertEquals("org.example.Address", person.getField("address").schema().getFullName());

    CloseTrackingInputStream input = new CloseTrackingInputStream(recordJson("StreamRecord", "org.example", "id", "long"));
    Schema streamRecord = new Schema.Parser().parse(input);
    assertEquals("org.example.StreamRecord", streamRecord.getFullName());
    assertFalse(input.wasClosed());
  }

  @Test
  public void parserAddTypesShouldAllowExternalNamedSchemaReferences() {
    Schema external = record("External", "org.example", newField("id", Schema.create(Schema.Type.INT), null));
    Schema.Parser parser = new Schema.Parser();

    assertSame(parser, parser.addTypes(Collections.singletonList(external)));
    assertTrue(parser.getTypes().containsKey("org.example.External"));

    Schema holder = parser.parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"Holder\","
        + "\"namespace\":\"org.example\","
        + "\"fields\":[{\"name\":\"external\",\"type\":\"External\"}]"
        + "}");

    assertEquals("org.example.Holder", holder.getFullName());
    assertEquals("org.example.External", holder.getField("external").schema().getFullName());
  }

  @Test
  public void deprecatedParserAddTypesMapShouldAllowNamedSchemaReferences() {
    Schema external = record("MappedExternal", "org.example", newField("id", Schema.create(Schema.Type.INT), null));
    Map<String, Schema> knownTypes = new LinkedHashMap<>();
    knownTypes.put(external.getFullName(), external);

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(knownTypes));

    Schema holder = parser.parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"MappedHolder\","
        + "\"namespace\":\"org.example\","
        + "\"fields\":[{\"name\":\"external\",\"type\":\"MappedExternal\"}]"
        + "}");

    assertEquals("org.example.MappedExternal", holder.getField("external").schema().getFullName());
  }

  @Test
  public void parserValidateDefaultsShouldBeConfigurable() {
    String invalidDefault = "{"
        + "\"type\":\"record\","
        + "\"name\":\"BadDefault\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"default\":\"bad\"}]"
        + "}";

    Schema.Parser permissive = new Schema.Parser();
    assertSame(permissive, permissive.setValidateDefaults(false));
    assertFalse(permissive.getValidateDefaults());
    assertEquals("BadDefault", permissive.parse(invalidDefault).getName());

    Schema.Parser validating = new Schema.Parser().setValidateDefaults(true);
    assertTrue(validating.getValidateDefaults());
    expectThrows(RuntimeException.class, () -> validating.parse(invalidDefault));
  }

  @Test
  public void parserConstructedWithNoValidationShouldAcceptOtherwiseInvalidNames() {
    String invalidByStrictRules = "{"
        + "\"type\":\"record\","
        + "\"name\":\"1InvalidButAllowed\","
        + "\"fields\":[]"
        + "}";

    expectThrows(RuntimeException.class, () -> new Schema.Parser().parse(invalidByStrictRules));

    Schema parsedWithoutValidation = new Schema.Parser(NameValidator.NO_VALIDATION).parse(invalidByStrictRules);
    assertEquals("1InvalidButAllowed", parsedWithoutValidation.getName());
  }

  @Test
  public void parserShouldRejectInvalidJsonAndUnknownTypeNames() {
    expectThrows(RuntimeException.class, () -> new Schema.Parser().parse("{not-json"));
    expectThrows(RuntimeException.class, () -> new Schema.Parser().parse("{\"type\":\"integer\"}"));
    expectThrows(RuntimeException.class, () -> new Schema.Parser().parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"UsesMissing\","
        + "\"fields\":[{\"name\":\"missing\",\"type\":\"MissingType\"}]"
        + "}"));
  }

  @Test
  public void staticConfigurationMethodsShouldRoundTripAndBeRestored() {
    boolean originalValidateDefaults = Schema.getValidateDefaults();
    NameValidator originalNameValidator = Schema.getNameValidator();

    try {
      Schema.setValidateDefaults(!originalValidateDefaults);
      assertEquals(!originalValidateDefaults, Schema.getValidateDefaults());

      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());

      Schema.setNameValidator(NameValidator.STRICT_VALIDATOR);
      assertSame(NameValidator.STRICT_VALIDATOR, Schema.getNameValidator());
    } finally {
      Schema.setValidateDefaults(originalValidateDefaults);
      Schema.setNameValidator(originalNameValidator);
    }
  }

  @Test
  public void unsupportedAccessorsShouldThrowOnWrongSchemaType() {
    Schema string = Schema.create(Schema.Type.STRING);

    expectThrows(AvroRuntimeException.class, () -> string.getField("id"));
    expectThrows(AvroRuntimeException.class, string::getFields);
    expectThrows(AvroRuntimeException.class, string::hasFields);
    expectThrows(AvroRuntimeException.class, () -> string.setFields(Collections.emptyList()));
    expectThrows(AvroRuntimeException.class, string::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, string::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> string.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> string.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, string::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> string.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> string.addAlias("Alias", "org.example"));
    expectThrows(AvroRuntimeException.class, string::getAliases);
    expectThrows(AvroRuntimeException.class, string::isError);
    expectThrows(AvroRuntimeException.class, string::getElementType);
    expectThrows(AvroRuntimeException.class, string::getValueType);
    expectThrows(AvroRuntimeException.class, string::getTypes);
    expectThrows(AvroRuntimeException.class, () -> string.getIndexNamed("string"));
    expectThrows(AvroRuntimeException.class, string::getFixedSize);
  }

  @Test
  public void writeReplaceShouldReturnSerializableReplacement() {
    Schema schema = record("SerializableRecord", "org.example",
        newField("id", Schema.create(Schema.Type.INT), null));

    Object replacement = schema.writeReplace();

    assertNotNull(replacement);
    assertTrue(replacement instanceof Serializable);
  }

  private static Schema nullable(Schema schema) {
    return Schema.createUnion(Schema.create(Schema.Type.NULL), schema);
  }

  private static Schema record(String name, String namespace, Schema.Field... fields) {
    return Schema.createRecord(name, null, namespace, false, Arrays.asList(fields));
  }

  private static Schema.Field newField(String name, Schema schema, String doc) {
    return new Schema.Field(name, schema, doc);
  }

  private static Schema.Field newField(String name, Schema schema, String doc, Object defaultValue) {
    return new Schema.Field(name, schema, doc, defaultValue);
  }

  private static JsonNode json(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  private static String recordJson(String name, String namespace, String fieldName, String fieldType) {
    return "{"
        + "\"type\":\"record\","
        + "\"name\":\"" + name + "\","
        + "\"namespace\":\"" + namespace + "\","
        + "\"fields\":[{\"name\":\"" + fieldName + "\",\"type\":\"" + fieldType + "\"}]"
        + "}";
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

  private static final class CloseTrackingInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseTrackingInputStream(String json) {
      super(json.getBytes(StandardCharsets.UTF_8));
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
