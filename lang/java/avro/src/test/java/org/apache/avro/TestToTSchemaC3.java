/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Tree-of-Thought inspired comprehensive JUnit 4 tests for the public API of
 * Schema_C3.java when it replaces org.apache.avro.Schema.
 */
public class TestToTSchemaC3 {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  /*
   * ============================================================
   * Expert 1: public factory methods and type-specific accessors
   * ============================================================
   */

  @Test
  public void typeGetNameShouldReturnLowerCaseAvroNames() {
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
  public void createShouldBuildAllPrimitiveSchemasAndRejectComplexTypes() {
    assertEquals(Schema.Type.STRING, Schema.create(Schema.Type.STRING).getType());
    assertEquals(Schema.Type.BYTES, Schema.create(Schema.Type.BYTES).getType());
    assertEquals(Schema.Type.INT, Schema.create(Schema.Type.INT).getType());
    assertEquals(Schema.Type.LONG, Schema.create(Schema.Type.LONG).getType());
    assertEquals(Schema.Type.FLOAT, Schema.create(Schema.Type.FLOAT).getType());
    assertEquals(Schema.Type.DOUBLE, Schema.create(Schema.Type.DOUBLE).getType());
    assertEquals(Schema.Type.BOOLEAN, Schema.create(Schema.Type.BOOLEAN).getType());
    assertEquals(Schema.Type.NULL, Schema.create(Schema.Type.NULL).getType());

    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.RECORD));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ENUM));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ARRAY));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.MAP));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.UNION));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.FIXED));
  }

  @Test
  public void primitiveSchemaShouldExposeGenericAccessorsAndRejectSpecificAccessors() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);

    assertEquals(Schema.Type.STRING, stringSchema.getType());
    assertEquals("string", stringSchema.getName());
    assertEquals("string", stringSchema.getFullName());
    assertNull(stringSchema.getDoc());
    assertNull(stringSchema.getLogicalType());
    assertFalse(stringSchema.isUnion());
    assertFalse(stringSchema.isNullable());

    expectThrows(AvroRuntimeException.class, () -> stringSchema.getField("x"));
    expectThrows(AvroRuntimeException.class, stringSchema::getFields);
    expectThrows(AvroRuntimeException.class, stringSchema::hasFields);
    expectThrows(AvroRuntimeException.class, () -> stringSchema.setFields(Collections.<Schema.Field>emptyList()));
    expectThrows(AvroRuntimeException.class, stringSchema::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, stringSchema::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, stringSchema::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> stringSchema.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> stringSchema.addAlias("Alias", "space"));
    expectThrows(AvroRuntimeException.class, stringSchema::getAliases);
    expectThrows(AvroRuntimeException.class, stringSchema::isError);
    expectThrows(AvroRuntimeException.class, stringSchema::getElementType);
    expectThrows(AvroRuntimeException.class, stringSchema::getValueType);
    expectThrows(AvroRuntimeException.class, stringSchema::getTypes);
    expectThrows(AvroRuntimeException.class, () -> stringSchema.getIndexNamed("string"));
    expectThrows(AvroRuntimeException.class, stringSchema::getFixedSize);
  }

  @Test
  public void createRecordWithFieldsShouldExposeFieldsPositionsAndMetadata() {
    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT), "identifier", 1);
    Schema.Field name = new Schema.Field("name", Schema.create(Schema.Type.STRING), "username");
    Schema schema = Schema.createRecord("User", "user documentation", "example.avro", false, Arrays.asList(id, name));

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals("User", schema.getName());
    assertEquals("user documentation", schema.getDoc());
    assertEquals("example.avro", schema.getNamespace());
    assertEquals("example.avro.User", schema.getFullName());
    assertFalse(schema.isError());
    assertTrue(schema.hasFields());
    assertEquals(2, schema.getFields().size());

    assertSame(id, schema.getField("id"));
    assertSame(name, schema.getField("name"));
    assertNull(schema.getField("missing"));

    assertEquals("id", id.name());
    assertEquals(0, id.pos());
    assertEquals(Schema.Type.INT, id.schema().getType());
    assertEquals("identifier", id.doc());
    assertTrue(id.hasDefaultValue());
    assertEquals(1, id.defaultVal());
    assertEquals(Schema.Field.Order.ASCENDING, id.order());

    assertEquals("name", name.name());
    assertEquals(1, name.pos());
    assertEquals(Schema.Type.STRING, name.schema().getType());
    assertEquals("username", name.doc());
    assertFalse(name.hasDefaultValue());
    assertNull(name.defaultVal());
  }

  @Test
  public void createAnonymousRecordShouldSetFieldsAndRejectDuplicateFieldNames() {
    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT));
    Schema anonymous = Schema.createRecord(Collections.singletonList(id));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertNull(anonymous.getName());
    assertTrue(anonymous.hasFields());
    assertSame(id, anonymous.getField("id"));

    expectThrows(AvroRuntimeException.class, () -> Schema.createRecord(Arrays.asList(
        new Schema.Field("dup", Schema.create(Schema.Type.INT)),
        new Schema.Field("dup", Schema.create(Schema.Type.STRING)))));
  }

  @Test
  public void recordSetFieldsShouldBeAllowedOnlyOnceAndFieldsCannotBeReused() {
    Schema record = Schema.createRecord("User", null, null, false);
    assertFalse(record.hasFields());

    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT));
    record.setFields(Collections.singletonList(id));

    assertTrue(record.hasFields());
    assertSame(id, record.getField("id"));
    expectThrows(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(new Schema.Field("other", Schema.create(Schema.Type.STRING)))));

    Schema otherRecord = Schema.createRecord("Other", null, null, false);
    expectThrows(AvroRuntimeException.class, () -> otherRecord.setFields(Collections.singletonList(id)));
  }

  @Test
  public void createEnumShouldExposeSymbolsOrdinalsAndDefault() {
    Schema schema = Schema.createEnum("Status", "status documentation", "example.avro",
        Arrays.asList("NEW", "DONE"), "NEW");

    assertEquals(Schema.Type.ENUM, schema.getType());
    assertEquals("Status", schema.getName());
    assertEquals("status documentation", schema.getDoc());
    assertEquals("example.avro", schema.getNamespace());
    assertEquals("example.avro.Status", schema.getFullName());
    assertEquals(Arrays.asList("NEW", "DONE"), schema.getEnumSymbols());
    assertEquals("NEW", schema.getEnumDefault());
    assertTrue(schema.hasEnumSymbol("NEW"));
    assertTrue(schema.hasEnumSymbol("DONE"));
    assertFalse(schema.hasEnumSymbol("MISSING"));
    assertEquals(0, schema.getEnumOrdinal("NEW"));
    assertEquals(1, schema.getEnumOrdinal("DONE"));

    expectThrows(RuntimeException.class, () -> schema.getEnumOrdinal("MISSING"));
  }

  @Test
  public void createEnumShouldRejectDuplicateSymbolsAndInvalidDefault() {
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Status", null, null, Arrays.asList("NEW", "NEW")));

    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Status", null, null, Arrays.asList("NEW", "DONE"), "MISSING"));
  }

  @Test
  public void createArrayMapUnionAndFixedShouldExposeTheirSpecificAccessors() {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema array = Schema.createArray(intSchema);
    Schema map = Schema.createMap(stringSchema);
    Schema user = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), user, stringSchema);
    Schema fixed = Schema.createFixed("Hash", "hash documentation", "example.avro", 16);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(intSchema, array.getElementType());

    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(stringSchema, map.getValueType());

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(3, union.getTypes().size());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("User"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("string"));
    assertNull(union.getIndexNamed("missing.Type"));

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Hash", fixed.getName());
    assertEquals("hash documentation", fixed.getDoc());
    assertEquals("example.avro", fixed.getNamespace());
    assertEquals("example.avro.Hash", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
  }

  @Test
  public void createUnionShouldRejectDuplicateBranchesAndNestedUnions() {
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.STRING)));

    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.NULL),
            Schema.createUnion(Schema.create(Schema.Type.INT), Schema.create(Schema.Type.LONG))));
  }

  @Test
  public void namedSchemasShouldSupportAliasesWithAndWithoutNamespace() {
    Schema record = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    record.addAlias("OldUser");
    record.addAlias("VeryOldUser", "legacy.avro");

    Set<String> aliases = record.getAliases();
    assertTrue(aliases.contains("example.avro.OldUser"));
    assertTrue(aliases.contains("legacy.avro.VeryOldUser"));
    assertEquals(2, aliases.size());
  }

  /*
   * ============================================================
   * Expert 2: Field API, JSON rendering, equality, hashCode
   * ============================================================
   */

  @Test
  public void fieldConstructorsShouldSetSchemaDocDefaultOrderAndAliases() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);

    Schema.Field simple = new Schema.Field("simple", stringSchema);
    assertEquals("simple", simple.name());
    assertSame(stringSchema, simple.schema());
    assertNull(simple.doc());
    assertFalse(simple.hasDefaultValue());
    assertEquals(Schema.Field.Order.ASCENDING, simple.order());

    Schema.Field documented = new Schema.Field("documented", stringSchema, "field documentation");
    assertEquals("field documentation", documented.doc());

    Schema.Field withDefault = new Schema.Field("withDefault", stringSchema, "doc", "abc");
    assertTrue(withDefault.hasDefaultValue());
    assertEquals("abc", withDefault.defaultVal());

    Schema.Field descending = new Schema.Field("descending", stringSchema, "doc", "abc", Schema.Field.Order.DESCENDING);
    assertEquals(Schema.Field.Order.DESCENDING, descending.order());

    descending.addAlias("oldDescending");
    assertTrue(descending.aliases().contains("oldDescending"));
    expectThrows(UnsupportedOperationException.class, () -> descending.aliases().add("anotherAlias"));
  }

  @Test
  public void fieldShouldSupportExplicitNullDefaultSentinelForNullableUnion() {
    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    Schema.Field field = new Schema.Field("maybeName", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());

    /*
     * Avro uses Schema.Field.NULL_DEFAULT_VALUE only as the constructor sentinel
     * for an explicit null default. The public defaultVal() result is an internal
     * JsonProperties.Null marker, not the same Object instance passed in above.
     * Therefore the stable behavior to test is: default exists and is represented
     * by Avro's explicit-null marker rather than Java null, which would be
     * indistinguishable from the absence of a default in older expectations.
     */
    Object defaultValue = field.defaultVal();
    assertNotNull(defaultValue);
    assertEquals("Null", defaultValue.getClass().getSimpleName());
  }

  @Test
  public void fieldCopyConstructorShouldCopyMetadataPropertiesAndAliasesButReplaceSchema() {
    Schema.Field original = new Schema.Field("value", Schema.create(Schema.Type.STRING), "doc", "a");
    original.addAlias("oldValue");
    original.addProp("x-field", "metadata");

    Schema.Field copy = new Schema.Field(original, Schema.create(Schema.Type.BYTES));

    assertEquals(original.name(), copy.name());
    assertEquals(original.doc(), copy.doc());
    assertTrue(copy.hasDefaultValue());
    assertTrue(copy.defaultVal() instanceof byte[]);
    assertEquals("a", new String((byte[]) copy.defaultVal(), StandardCharsets.UTF_8));
    assertEquals(original.order(), copy.order());
    assertEquals(Schema.Type.BYTES, copy.schema().getType());
    assertEquals("metadata", copy.getProp("x-field"));
    assertTrue(copy.aliases().contains("oldValue"));
  }

  @Test
  public void fieldShouldRejectNullSchemaNullOrderAndInvalidDefaultsWhenValidationIsEnabled() {
    boolean originalValidateDefaults = Schema.getValidateDefaults();
    try {
      Schema.setValidateDefaults(true);

      expectThrows(NullPointerException.class, () -> new Schema.Field("bad", null));
      expectThrows(NullPointerException.class,
          () -> new Schema.Field("badOrder", Schema.create(Schema.Type.STRING), null, "x", null));
      expectThrows(AvroTypeException.class,
          () -> new Schema.Field("badDefault", Schema.create(Schema.Type.INT), null, "not-an-int"));
    } finally {
      Schema.setValidateDefaults(originalValidateDefaults);
    }
  }

  @Test
  public void globalDefaultValidationFlagShouldAllowInvalidFieldDefaultsWhenDisabled() {
    boolean originalValidateDefaults = Schema.getValidateDefaults();
    try {
      Schema.setValidateDefaults(false);

      Schema.Field field = new Schema.Field("badDefault", Schema.create(Schema.Type.INT), null, "not-an-int");

      assertTrue(field.hasDefaultValue());
      assertNull(field.defaultVal());
    } finally {
      Schema.setValidateDefaults(originalValidateDefaults);
    }
  }

  @Test
  public void fieldEqualsShouldHandleNanDefaultsAndHashCodeShouldUseNameAndSchema() {
    Schema doubleSchema = Schema.create(Schema.Type.DOUBLE);
    Schema.Field left = new Schema.Field("score", doubleSchema, null, Double.NaN);
    Schema.Field right = new Schema.Field("score", doubleSchema, null, Double.NaN);
    Schema.Field different = new Schema.Field("other", doubleSchema, null, Double.NaN);

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertNotEquals(left, different);
    assertTrue(left.toString().contains("score"));
    assertTrue(left.toString().contains("DOUBLE"));
  }

  @Test
  public void schemaToStringShouldRenderPrimitiveRecordPrettyAndReferencedSchemas() {
    Schema intSchema = Schema.create(Schema.Type.INT);
    assertEquals("\"int\"", intSchema.toString());

    Schema address = Schema.createRecord("Address", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("street", Schema.create(Schema.Type.STRING))));
    Schema user = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("address", address)));

    String compact = user.toString();
    String pretty = user.toString(true);
    String withReferencedAddress = user.toString(Collections.singleton(address), false);

    assertTrue(compact.contains("\"type\":\"record\""));
    assertTrue(compact.contains("\"User\""));
    assertTrue(pretty.contains("\n"));
    assertTrue(withReferencedAddress.contains("\"Address\""));
  }

  @Test
  public void schemaEqualsAndHashCodeShouldRespectTypeNameFieldsAndProperties() {
    Schema left = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema right = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema differentName = Schema.createRecord("Account", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertNotEquals(left, differentName);

    right.addProp("x-prop", "value");
    assertNotEquals(left, right);

    left.addProp("x-prop", "value");
    assertEquals(left, right);
    assertEquals("value", left.getProp("x-prop"));
  }

  @Test
  public void schemaAddPropObjectShouldBeVisibleInJsonRendering() throws Exception {
    Schema schema = Schema.create(Schema.Type.STRING);
    schema.addProp("x-object", json("{\"enabled\":true}"));

    String rendered = schema.toString();

    assertTrue(rendered.contains("\"x-object\""));
    assertTrue(rendered.contains("\"enabled\""));
  }

  @Test
  public void seenPairShouldUseIdentitySemantics() {
    Object left = new Object();
    Object right = new Object();
    Schema.SeenPair first = new Schema.SeenPair(left, right);
    Schema.SeenPair sameIdentities = new Schema.SeenPair(left, right);
    Schema.SeenPair differentLeft = new Schema.SeenPair(new Object(), right);

    assertEquals(first, sameIdentities);
    assertEquals(first.hashCode(), sameIdentities.hashCode());
    assertNotEquals(first, differentLeft);
    assertFalse(first.equals("not-a-pair"));
  }

  /*
   * ============================================================
   * Expert 3: parser, default validation, aliases and error paths
   * ============================================================
   */

  @Test
  public void staticParseMethodsShouldParseStringStreamAndFile() throws Exception {
    String schemaJson = "{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}";

    Schema fromString = Schema.parse(schemaJson);
    Schema fromStream = Schema.parse(new ByteArrayInputStream(schemaJson.getBytes(StandardCharsets.UTF_8)));

    File schemaFile = temp.newFile("schema.avsc");
    Files.write(schemaFile.toPath(), schemaJson.getBytes(StandardCharsets.UTF_8));
    Schema fromFile = Schema.parse(schemaFile);

    assertEquals(Schema.Type.RECORD, fromString.getType());
    assertEquals(fromString, fromStream);
    assertEquals(fromString, fromFile);
  }

  @Test
  public void staticParseWithValidateFalseShouldAcceptOtherwiseInvalidNames() {
    String invalidName = "{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}";

    expectThrows(SchemaParseException.class, () -> Schema.parse(invalidName, true));

    Schema parsedWithoutValidation = Schema.parse(invalidName, false);
    assertEquals("bad-name", parsedWithoutValidation.getName());
  }

  @Test
  public void parserShouldParseConcatenatedStringPartsAndInternalSchemas() {
    Schema.Parser parser = new Schema.Parser();

    Schema parsed = parser.parse("{\"type\":\"", "record\",\"name\":\"User\",\"fields\":[]}");
    Schema internal = parser.parseInternal("{\"type\":\"int\"}");

    assertEquals(Schema.Type.RECORD, parsed.getType());
    assertEquals("User", parsed.getName());
    assertEquals(Schema.Type.INT, internal.getType());
  }

  @Test
  public void parserShouldRejectDanglingContentWhenParsingStringButNotCloseInputStreams() throws Exception {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"int\" \"long\""));

    CloseTrackingInputStream in = new CloseTrackingInputStream("\"int\" trailing");
    Schema parsed = new Schema.Parser().parse(in);

    assertEquals(Schema.Type.INT, parsed.getType());
    assertFalse("Schema.Parser.parse(InputStream) must not close the caller-owned stream", in.closed);
  }

  @Test
  public void parserAddTypesShouldAllowReferencesByPreviouslyKnownName() {
    Schema user = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    Schema.Parser iterableParser = new Schema.Parser();
    assertSame(iterableParser, iterableParser.addTypes(Collections.singletonList(user)));
    Schema wrapperFromIterable = iterableParser.parse("{\"type\":\"record\",\"name\":\"Wrapper\","
        + "\"fields\":[{\"name\":\"user\",\"type\":\"User\"}]}");

    assertEquals(Schema.Type.RECORD, wrapperFromIterable.getField("user").schema().getType());
    assertEquals("User", wrapperFromIterable.getField("user").schema().getName());
    assertTrue(iterableParser.getTypes().containsKey("User"));

    Schema.Parser mapParser = new Schema.Parser();
    Map<String, Schema> types = new LinkedHashMap<>();
    types.put(user.getFullName(), user);
    assertSame(mapParser, mapParser.addTypes(types));
    Schema wrapperFromMap = mapParser.parse("{\"type\":\"record\",\"name\":\"MapWrapper\","
        + "\"fields\":[{\"name\":\"user\",\"type\":\"User\"}]}");

    assertEquals("User", wrapperFromMap.getField("user").schema().getName());
  }

  @Test
  public void parserValidateDefaultsFlagShouldControlInvalidDefaultHandling() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"default\":\"wrong\"}]}";

    Schema.Parser strictParser = new Schema.Parser();
    assertTrue(strictParser.getValidateDefaults());
    expectThrows(AvroTypeException.class, () -> strictParser.parse(invalidDefault));

    Schema.Parser lenientParser = new Schema.Parser();
    assertSame(lenientParser, lenientParser.setValidateDefaults(false));
    assertFalse(lenientParser.getValidateDefaults());
    Schema parsed = lenientParser.parse(invalidDefault);

    assertEquals(Schema.Type.RECORD, parsed.getType());
    assertTrue(parsed.getField("id").hasDefaultValue());
    assertNull(parsed.getField("id").defaultVal());
  }

  @Test
  public void parserConstructedWithNullNameValidatorShouldDisableNameValidation() {
    Schema.Parser parser = new Schema.Parser((NameValidator) null);
    Schema schema = parser.parse("{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}");

    assertEquals("bad-name", schema.getName());
  }

  @Test
  public void globalNameValidatorShouldBeSettableAndRestorable() {
    NameValidator original = Schema.getNameValidator();
    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());

      Schema schema = Schema.createRecord("bad-name", null, null, false, Collections.<Schema.Field>emptyList());
      assertEquals("bad-name", schema.getName());
    } finally {
      Schema.setNameValidator(original);
    }
  }

  @Test
  public void parseJsonToObjectShouldConvertJsonIntoJavaObjects() {
    Object object = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,\"x\"]}");

    assertTrue(object instanceof Map);
    Map<?, ?> map = (Map<?, ?>) object;
    assertEquals(1, map.get("a"));
    assertTrue(map.get("b") instanceof Collection);
  }

  @Test
  public void parserShouldPreserveCustomPropertiesDocAndLogicalType() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"doc\":\"record documentation\","
        + "\"x-schema\":\"schema-metadata\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"doc\":\"identifier documentation\","
        + "\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"},"
        + "\"x-field\":\"field-metadata\""
        + "}]"
        + "}");

    Schema.Field field = schema.getField("id");

    assertEquals("record documentation", schema.getDoc());
    assertEquals("schema-metadata", schema.getProp("x-schema"));
    assertEquals("identifier documentation", field.doc());
    assertEquals("field-metadata", field.getProp("x-field"));
    assertNotNull(field.schema().getLogicalType());
    assertEquals("uuid", field.schema().getLogicalType().getName());
  }

  @Test
  public void parserShouldRejectMalformedRecordEnumArrayMapFixedUnionAndAliases() {
    assertParseFails("{\"type\":\"record\",\"name\":\"MissingFields\"}");
    assertParseFails("{\"type\":\"record\",\"name\":\"WrongFields\",\"fields\":\"not-array\"}");
    assertParseFails("{\"type\":\"record\",\"name\":\"MissingFieldName\",\"fields\":[{\"type\":\"int\"}]}");
    assertParseFails("{\"type\":\"record\",\"name\":\"MissingFieldType\",\"fields\":[{\"name\":\"id\"}]}");

    assertParseFails("{\"type\":\"enum\",\"name\":\"Status\"}");
    assertParseFails("{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":\"NEW\"}");
    assertParseFails("{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"NEW\",123]}");
    assertParseFails("{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"NEW\",\"DONE\"],\"default\":\"MISSING\"}");

    assertParseFails("{\"type\":\"array\"}");
    assertParseFails("{\"type\":\"array\",\"items\":null}");
    assertParseFails("{\"type\":\"map\"}");
    assertParseFails("{\"type\":\"map\",\"values\":null}");

    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\"}");
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":null}");
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":\"16\"}");
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":-1}");

    assertParseFails("[\"string\",\"string\"]");
    assertParseFails("[\"null\",[\"string\",\"int\"]]");

    assertParseFails("{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":\"Old\",\"fields\":[]}");
    assertParseFails("{\"type\":\"record\",\"name\":\"BadAliasEntry\",\"aliases\":[123],\"fields\":[]}");
  }

  @Test
  public void isNullableShouldRecognizeNullPrimitiveAndUnionsContainingNull() {
    assertTrue(Schema.create(Schema.Type.NULL).isNullable());
    assertFalse(Schema.create(Schema.Type.INT).isNullable());
    assertFalse(Schema.createUnion(Schema.create(Schema.Type.INT), Schema.create(Schema.Type.STRING)).isNullable());
    assertTrue(Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING)).isNullable());
  }

  @Test
  public void isValidDefaultShouldValidatePrimitiveDefaults() throws Exception {
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(json("null")));
    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault(json("\"x\"")));

    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertFalse(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("1")));

    assertTrue(Schema.create(Schema.Type.INT).isValidDefault(json("2147483647")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(json("\"1\"")));

    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(json("2147483648")));
    assertFalse(Schema.create(Schema.Type.LONG).isValidDefault(json("\"2147483648\"")));

    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(json("1.5")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("1.5")));
    assertFalse(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("\"1.5\"")));

    assertTrue(Schema.create(Schema.Type.STRING).isValidDefault(json("\"value\"")));
    assertFalse(Schema.create(Schema.Type.STRING).isValidDefault(json("1")));

    assertTrue(Schema.create(Schema.Type.BYTES).isValidDefault(json("\"bytes\"")));
    assertFalse(Schema.create(Schema.Type.BYTES).isValidDefault(json("[1,2]")));
  }

  @Test
  public void isValidDefaultShouldValidateNamedAndCompositeDefaultsRecursively() throws Exception {
    Schema enumSchema = parse("{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"NEW\",\"DONE\"]}");
    assertTrue(enumSchema.isValidDefault(json("\"MISSING_IS_STILL_TEXTUAL_FOR_THIS_METHOD\"")));
    assertFalse(enumSchema.isValidDefault(json("1")));

    Schema fixedSchema = parse("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":2}");
    assertTrue(fixedSchema.isValidDefault(json("\"abc\"")));
    assertFalse(fixedSchema.isValidDefault(json("{\"x\":1}")));

    Schema arraySchema = parse("{\"type\":\"array\",\"items\":\"int\"}");
    assertTrue(arraySchema.isValidDefault(json("[1,2,3]")));
    assertFalse(arraySchema.isValidDefault(json("[1,\"wrong\"]")));

    Schema mapSchema = parse("{\"type\":\"map\",\"values\":\"int\"}");
    assertTrue(mapSchema.isValidDefault(json("{\"a\":1,\"b\":2}")));
    assertFalse(mapSchema.isValidDefault(json("{\"a\":\"wrong\"}")));

    Schema recordSchema = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"label\",\"type\":\"string\"}]}");
    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"label\":\"ok\"}")));
    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"label\":\"ok\",\"extra\":true}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":1}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":\"wrong\",\"label\":\"ok\"}")));

    Schema unionSchema = parse("[\"null\",\"string\"]");
    assertTrue(unionSchema.isValidDefault(json("null")));
    assertTrue(unionSchema.isValidDefault(json("\"value\"")));
    assertFalse(unionSchema.isValidDefault(json("1")));

    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault((JsonNode) null));
  }

  @Test
  public void isValidDefaultShouldUseFieldDefaultsForMissingRecordFields() throws Exception {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"WithDefaults\","
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"default\":7},"
        + "{\"name\":\"name\",\"type\":\"string\",\"default\":\"unknown\"}"
        + "]}");

    assertTrue(schema.isValidDefault(json("{}")));
    assertTrue(schema.isValidDefault(json("{\"id\":10}")));
    assertTrue(schema.isValidDefault(json("{\"name\":\"actual\"}")));
    assertFalse(schema.isValidDefault(json("{\"id\":\"bad\"}")));
  }

  @Test
  public void applyAliasesShouldRewriteRecordNamesAndFieldNames() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"oldId\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\",\"aliases\":[\"OldUser\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"aliases\":[\"oldId\"]}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.RECORD, result.getType());
    assertEquals("User", result.getName());
    assertNotNull(result.getField("id"));
    assertNull(result.getField("oldId"));
    assertEquals(Schema.Type.INT, result.getField("id").schema().getType());
  }

  @Test
  public void applyAliasesShouldRewriteNestedArrayMapUnionEnumAndFixedSchemas() {
    Schema writerArray = parse("{\"type\":\"array\",\"items\":{"
        + "\"type\":\"record\",\"name\":\"OldItem\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}}");
    Schema readerArray = parse("{\"type\":\"array\",\"items\":{"
        + "\"type\":\"record\",\"name\":\"Item\",\"aliases\":[\"OldItem\"],\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}}");
    assertEquals("Item", Schema.applyAliases(writerArray, readerArray).getElementType().getName());

    Schema writerMap = parse("{\"type\":\"map\",\"values\":{"
        + "\"type\":\"record\",\"name\":\"OldValue\",\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]}}");
    Schema readerMap = parse("{\"type\":\"map\",\"values\":{"
        + "\"type\":\"record\",\"name\":\"Value\",\"aliases\":[\"OldValue\"],\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]}}");
    assertEquals("Value", Schema.applyAliases(writerMap, readerMap).getValueType().getName());

    Schema writerUnion = parse("[\"null\",{\"type\":\"record\",\"name\":\"OldEvent\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}]");
    Schema readerUnion = parse("[\"null\",{\"type\":\"record\",\"name\":\"Event\",\"aliases\":[\"OldEvent\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}]");
    Schema unionResult = Schema.applyAliases(writerUnion, readerUnion);
    assertEquals(Schema.Type.NULL, unionResult.getTypes().get(0).getType());
    assertEquals("Event", unionResult.getTypes().get(1).getName());

    Schema writerEnum = parse("{\"type\":\"enum\",\"name\":\"OldStatus\",\"symbols\":[\"NEW\",\"DONE\"]}");
    Schema readerEnum = parse("{\"type\":\"enum\",\"name\":\"Status\",\"aliases\":[\"OldStatus\"],"
        + "\"symbols\":[\"NEW\",\"DONE\"]}");
    Schema enumResult = Schema.applyAliases(writerEnum, readerEnum);
    assertEquals(Schema.Type.ENUM, enumResult.getType());
    assertEquals("Status", enumResult.getName());
    assertEquals(Arrays.asList("NEW", "DONE"), enumResult.getEnumSymbols());

    Schema writerFixed = parse("{\"type\":\"fixed\",\"name\":\"OldHash\",\"size\":16}");
    Schema readerFixed = parse("{\"type\":\"fixed\",\"name\":\"Hash\",\"aliases\":[\"OldHash\"],\"size\":16}");
    Schema fixedResult = Schema.applyAliases(writerFixed, readerFixed);
    assertEquals(Schema.Type.FIXED, fixedResult.getType());
    assertEquals("Hash", fixedResult.getName());
    assertEquals(16, fixedResult.getFixedSize());
  }

  @Test
  public void applyAliasesShouldReturnOriginalWriterWhenNoAliasCanApplyOrSchemasAreEqual() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema readerWithoutAlias = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    Schema unchanged = Schema.applyAliases(writer, readerWithoutAlias);
    assertSame(writer, unchanged);

    Schema same = parse("{\"type\":\"record\",\"name\":\"Same\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    assertSame(same, Schema.applyAliases(same, same));

    Schema primitive = Schema.create(Schema.Type.STRING);
    assertSame(primitive, Schema.applyAliases(primitive, Schema.create(Schema.Type.STRING)));
  }

  /*
   * ============================================================
   * Helpers
   * ============================================================
   */

  private static Schema parse(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static JsonNode json(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  private static void assertParseFails(String schemaJson) {
    expectThrows(RuntimeException.class, () -> new Schema.Parser().parse(schemaJson));
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      actual.printStackTrace();
      fail("Expected " + expectedType.getName() + " but got " + actual.getClass().getName() + ": "
          + actual.getMessage());
    }
    fail("Expected exception " + expectedType.getName());
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Throwable;
  }

  private static final class CloseTrackingInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseTrackingInputStream(String input) {
      super(input.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
