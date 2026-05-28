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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NullNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Comprehensive JUnit 4 tests for Schema_C4.java's Schema implementation.
 */
public class TestLLMCodeSchemaC4 {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private static final ObjectMapper MAPPER = new ObjectMapper();

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
      if (actual instanceof AssertionError) {
        throw (AssertionError) actual;
      }
      fail("Expected " + expectedType.getName() + " but got " + actual.getClass().getName() + ": " + actual.getMessage());
    }
    fail("Expected " + expectedType.getName() + " to be thrown");
    return null;
  }

  private static Schema primitive(Schema.Type type) {
    return Schema.create(type);
  }

  private static Schema.Field field(String name, Schema schema) {
    return new Schema.Field(name, schema);
  }

  private static List<Schema.Field> fields(Schema.Field... fields) {
    return new ArrayList<>(Arrays.asList(fields));
  }

  private static JsonNode json(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  private static void assertAvroNullSentinel(Object value) {
    assertNotNull(value);
    assertEquals("org.apache.avro.JsonProperties$Null", value.getClass().getName());
  }

  @Test
  public void typeGetNameReturnsLowerCaseNamesForAllTypes() {
    for (Schema.Type type : Schema.Type.values()) {
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), type.getName());
    }
  }

  @Test
  public void createPrimitiveSchemasAndRejectNonPrimitiveTypes() {
    List<Schema.Type> primitives = Arrays.asList(Schema.Type.STRING, Schema.Type.BYTES, Schema.Type.INT,
        Schema.Type.LONG, Schema.Type.FLOAT, Schema.Type.DOUBLE, Schema.Type.BOOLEAN, Schema.Type.NULL);
    for (Schema.Type type : primitives) {
      Schema schema = Schema.create(type);
      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
      assertNull(schema.getDoc());
    }

    assertEquals("\"int\"", Schema.create(Schema.Type.INT).toString());
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.RECORD));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ENUM));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ARRAY));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.MAP));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.UNION));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.FIXED));
  }

  @Test
  public void primitiveSchemasRejectAccessorsForIncompatibleKinds() {
    Schema intSchema = primitive(Schema.Type.INT);

    expectThrows(AvroRuntimeException.class, () -> intSchema.getField("x"));
    expectThrows(AvroRuntimeException.class, intSchema::getFields);
    expectThrows(AvroRuntimeException.class, intSchema::hasFields);
    expectThrows(AvroRuntimeException.class, () -> intSchema.setFields(Collections.emptyList()));
    expectThrows(AvroRuntimeException.class, intSchema::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, intSchema::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> intSchema.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> intSchema.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, intSchema::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> intSchema.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> intSchema.addAlias("Alias", "ns"));
    expectThrows(AvroRuntimeException.class, intSchema::getAliases);
    expectThrows(AvroRuntimeException.class, intSchema::isError);
    expectThrows(AvroRuntimeException.class, intSchema::getElementType);
    expectThrows(AvroRuntimeException.class, intSchema::getValueType);
    expectThrows(AvroRuntimeException.class, intSchema::getTypes);
    expectThrows(AvroRuntimeException.class, () -> intSchema.getIndexNamed("int"));
    expectThrows(AvroRuntimeException.class, intSchema::getFixedSize);
  }

  @Test
  public void recordLifecycleSupportsDelayedFieldSettingAndLookup() {
    Schema record = Schema.createRecord("User", "user doc", "example.avro", true);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertEquals("user doc", record.getDoc());
    assertTrue(record.isError());
    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, record::getFields);
    expectThrows(AvroRuntimeException.class, () -> record.getField("id"));

    Schema.Field id = new Schema.Field("id", primitive(Schema.Type.INT), "identifier");
    Schema.Field name = new Schema.Field("name", primitive(Schema.Type.STRING), "display name", "anonymous",
        Schema.Field.Order.DESCENDING);
    name.addAlias("displayName");
    record.setFields(fields(id, name));

    assertTrue(record.hasFields());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertEquals(Arrays.asList(id, name), record.getFields());
    assertEquals("id type:INT pos:0", id.toString());
    assertTrue(record.toString().contains("\"aliases\":[\"displayName\"]"));
    assertTrue(record.toString().contains("\"order\":\"descending\""));

    expectThrows(IllegalStateException.class, () -> record.getFields().add(new Schema.Field("other", primitive(Schema.Type.INT))));
    expectThrows(AvroRuntimeException.class, () -> record.setFields(Collections.emptyList()));
  }

  @Test
  public void createRecordOverloadsSetFieldsAndRejectDuplicateOrReusedFields() {
    Schema.Field id = field("id", primitive(Schema.Type.INT));
    Schema.Field active = field("active", primitive(Schema.Type.BOOLEAN));
    Schema record = Schema.createRecord("Account", null, "example.avro", false, fields(id, active));

    assertFalse(record.isError());
    assertEquals(2, record.getFields().size());
    assertEquals("Account", record.getName());
    assertEquals("example.avro.Account", record.getFullName());

    Schema anonymous = Schema.createRecord(fields(field("value", primitive(Schema.Type.STRING))));
    assertNull(anonymous.getName());
    assertEquals(1, anonymous.getFields().size());

    expectThrows(AvroRuntimeException.class,
        () -> Schema.createRecord("Duplicate", null, null, false,
            fields(field("same", primitive(Schema.Type.INT)), field("same", primitive(Schema.Type.LONG)))));

    Schema.Field reused = field("reused", primitive(Schema.Type.INT));
    Schema first = Schema.createRecord("First", null, null, false);
    first.setFields(fields(reused));
    Schema second = Schema.createRecord("Second", null, null, false);
    expectThrows(AvroRuntimeException.class, () -> second.setFields(fields(reused)));
  }

  @Test
  public void namedSchemasExposeAliasesWithExpectedQualification() {
    Schema record = Schema.createRecord("User", null, "example.avro", false, Collections.emptyList());
    record.addAlias("LegacyUser");
    record.addAlias("ExternalUser", "legacy.avro");

    assertTrue(record.getAliases().contains("example.avro.LegacyUser"));
    assertTrue(record.getAliases().contains("legacy.avro.ExternalUser"));

    Schema fixed = Schema.createFixed("Hash", "hash doc", "example.avro", 16);
    fixed.addAlias("OldHash");
    assertTrue(fixed.getAliases().contains("example.avro.OldHash"));
    assertEquals("hash doc", fixed.getDoc());
  }

  @Test
  public void enumSchemaSupportsSymbolsOrdinalsDefaultAndValidation() {
    Schema suit = Schema.createEnum("Suit", "card suit", "cards", Arrays.asList("SPADES", "HEARTS", "DIAMONDS"),
        "SPADES");

    assertEquals(Schema.Type.ENUM, suit.getType());
    assertEquals("Suit", suit.getName());
    assertEquals("cards", suit.getNamespace());
    assertEquals("cards.Suit", suit.getFullName());
    assertEquals("card suit", suit.getDoc());
    assertEquals(Arrays.asList("SPADES", "HEARTS", "DIAMONDS"), suit.getEnumSymbols());
    assertEquals("SPADES", suit.getEnumDefault());
    assertTrue(suit.hasEnumSymbol("HEARTS"));
    assertFalse(suit.hasEnumSymbol("CLUBS"));
    assertEquals(1, suit.getEnumOrdinal("HEARTS"));
    expectThrows(AvroTypeException.class, () -> suit.getEnumOrdinal("CLUBS"));
    expectThrows(IllegalStateException.class, () -> suit.getEnumSymbols().add("CLUBS"));

    Schema noDefault = Schema.createEnum("NoDefault", null, null, Arrays.asList("A", "B"));
    assertNull(noDefault.getEnumDefault());

    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Bad", null, null, Arrays.asList("A", "A")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadDefault", null, null, Arrays.asList("A", "B"), "C"));
  }

  @Test
  public void arrayMapFixedAndUnionSchemasExposeTheirSpecificState() {
    Schema intSchema = primitive(Schema.Type.INT);
    Schema array = Schema.createArray(intSchema);
    Schema map = Schema.createMap(array);
    Schema fixed = Schema.createFixed("Md5", "digest", "crypto", 16);
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), fixed, map);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(intSchema, array.getElementType());
    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(array, map.getValueType());
    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals(16, fixed.getFixedSize());
    assertEquals("crypto.Md5", fixed.getFullName());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(Arrays.asList(primitive(Schema.Type.NULL), fixed, map), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("crypto.Md5"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("map"));
    assertNull(union.getIndexNamed("missing"));
    assertTrue(union.getName().startsWith("union[null, Md5, map]"));
    expectThrows(IllegalStateException.class, () -> union.getTypes().add(primitive(Schema.Type.STRING)));
  }

  @Test
  public void unionCreationRejectsNestedDuplicateAndNamelessBranchesAndProperties() {
    Schema nested = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    expectThrows(AvroRuntimeException.class, () -> Schema.createUnion(primitive(Schema.Type.INT), nested));
    expectThrows(AvroRuntimeException.class, () -> Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.INT)));
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(primitive(Schema.Type.INT), Schema.createRecord(Collections.emptyList())));

    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    expectThrows(AvroRuntimeException.class, () -> union.addProp("custom", "value"));
  }

  @Test
  public void nullableDetectionHandlesNullPrimitiveAndNestedUnionBranches() {
    assertTrue(primitive(Schema.Type.NULL).isNullable());
    assertFalse(primitive(Schema.Type.STRING).isNullable());
    assertTrue(Schema.createUnion(primitive(Schema.Type.STRING), primitive(Schema.Type.NULL)).isNullable());
    assertFalse(Schema.createUnion(primitive(Schema.Type.STRING), primitive(Schema.Type.INT)).isNullable());
  }

  @Test
  public void toStringPrettyPrintAndReferencedSchemasAreParseable() {
    Schema child = Schema.createRecord("Child", null, "example.avro", false,
        fields(field("name", primitive(Schema.Type.STRING))));
    Schema parent = Schema.createRecord("Parent", "parent doc", "example.avro", false,
        fields(new Schema.Field("child", child)));

    String compact = parent.toString();
    String pretty = parent.toString(true);
    assertTrue(compact.contains("\"name\":\"Parent\""));
    assertTrue(pretty.contains("\n"));
    assertEquals(parent, new Schema.Parser().parse(compact));

    String withReference = parent.toString(Collections.singleton(child), false);
    assertTrue(withReference.contains("\"type\":\"Child\""));
    assertFalse(withReference.contains("\"fields\":[{\"name\":\"name\""));
  }

  @Test
  public void equalsAndHashCodeIncludeTypeNamesFieldsSymbolsSizesAndProps() {
    Schema left = Schema.createRecord("Same", null, "ns", false, fields(field("id", primitive(Schema.Type.INT))));
    Schema right = Schema.createRecord("Same", null, "ns", false, fields(field("id", primitive(Schema.Type.INT))));
    Schema differentName = Schema.createRecord("Different", null, "ns", false, fields(field("id", primitive(Schema.Type.INT))));
    Schema differentField = Schema.createRecord("Same", null, "ns", false, fields(field("id", primitive(Schema.Type.LONG))));

    assertEquals(left, left);
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertNotEquals(left, differentName);
    assertNotEquals(left, differentField);
    assertNotEquals(left, "not a schema");

    right.addProp("custom", "value");
    assertNotEquals(left, right);
    assertEquals("value", right.getProp("custom"));

    assertEquals(Schema.createEnum("E", null, null, Arrays.asList("A", "B")),
        Schema.createEnum("E", null, null, Arrays.asList("A", "B")));
    assertNotEquals(Schema.createFixed("F", null, null, 4), Schema.createFixed("F", null, null, 8));
  }

  @Test
  public void recursiveRecordsCanBeHashedAndComparedWithoutStackOverflow() {
    Schema left = Schema.createRecord("Node", null, "tree", false);
    left.setFields(fields(new Schema.Field("next", Schema.createUnion(primitive(Schema.Type.NULL), left), null,
        Schema.Field.NULL_DEFAULT_VALUE)));

    Schema right = Schema.createRecord("Node", null, "tree", false);
    right.setFields(fields(new Schema.Field("next", Schema.createUnion(primitive(Schema.Type.NULL), right), null,
        Schema.Field.NULL_DEFAULT_VALUE)));

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    String renderedRecursiveSchema = left.toString();
    assertTrue(renderedRecursiveSchema.contains("\"name\":\"Node\""));
    assertTrue(renderedRecursiveSchema.contains("\"namespace\":\"tree\""));
    assertTrue(renderedRecursiveSchema.contains("\"type\":[\"null\",\"Node\"]"));
  }

  @Test
  public void fieldConstructorsExposeNameSchemaDocDefaultOrderPositionAndAliases() {
    Schema stringSchema = primitive(Schema.Type.STRING);
    Schema.Field simple = new Schema.Field("simple", stringSchema);
    assertEquals("simple", simple.name());
    assertEquals(-1, simple.pos());
    assertSame(stringSchema, simple.schema());
    assertNull(simple.doc());
    assertFalse(simple.hasDefaultValue());
    assertNull(simple.defaultVal());
    assertEquals(Schema.Field.Order.ASCENDING, simple.order());
    assertTrue(simple.aliases().isEmpty());
    expectThrows(UnsupportedOperationException.class, () -> simple.aliases().add("x"));

    Schema.Field documented = new Schema.Field("documented", stringSchema, "doc");
    assertEquals("doc", documented.doc());

    Schema.Field withDefault = new Schema.Field("withDefault", stringSchema, "doc", "abc",
        Schema.Field.Order.IGNORE);
    withDefault.addAlias("oldWithDefault");
    assertTrue(withDefault.hasDefaultValue());
    assertEquals("abc", withDefault.defaultVal());
    assertEquals(Schema.Field.Order.IGNORE, withDefault.order());
    assertTrue(withDefault.aliases().contains("oldWithDefault"));
    expectThrows(UnsupportedOperationException.class, () -> withDefault.aliases().add("another"));
  }

  @Test
  public void fieldNullDefaultSentinelRepresentsExplicitNullDefault() {
    Schema nullableString = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    Schema.Field field = new Schema.Field("maybe", nullableString, "nullable", Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());
    assertAvroNullSentinel(field.defaultVal());
    assertEquals(Schema.Field.Order.ASCENDING, field.order());
  }

  @Test
  public void fieldCopyConstructorChangesSchemaAndCopiesMetadataAliasesAndProperties() {
    Schema.Field original = new Schema.Field("amount", primitive(Schema.Type.INT), "amount doc", 7,
        Schema.Field.Order.DESCENDING);
    original.addAlias("oldAmount");
    original.addProp("unit", "cents");

    Schema.Field copy = new Schema.Field(original, primitive(Schema.Type.LONG));

    assertEquals("amount", copy.name());
    assertEquals(primitive(Schema.Type.LONG), copy.schema());
    assertEquals("amount doc", copy.doc());
    assertEquals(7L, ((Number) copy.defaultVal()).longValue());
    assertEquals(Schema.Field.Order.DESCENDING, copy.order());
    assertTrue(copy.aliases().contains("oldAmount"));
    assertEquals("cents", copy.getProp("unit"));
  }

  @Test
  public void fieldEqualsHandlesPropertiesOrderDefaultsAndNanDefaults() {
    Schema.Field a = new Schema.Field("score", primitive(Schema.Type.DOUBLE), null, Double.NaN);
    Schema.Field b = new Schema.Field("score", primitive(Schema.Type.DOUBLE), null, Double.NaN);
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());

    Schema.Field descending = new Schema.Field("score", primitive(Schema.Type.DOUBLE), null, Double.NaN,
        Schema.Field.Order.DESCENDING);
    assertNotEquals(a, descending);

    b.addProp("p", "v");
    assertNotEquals(a, b);
    assertNotEquals(a, "not a field");
  }

  @Test
  public void fieldConstructorRejectsInvalidNamesNullSchemaNullOrderAndInvalidDefaults() {
    expectThrows(SchemaParseException.class, () -> new Schema.Field("1bad", primitive(Schema.Type.INT)));
    expectThrows(NullPointerException.class, () -> new Schema.Field("missingSchema", null));
    expectThrows(NullPointerException.class,
        () -> new Schema.Field("badOrder", primitive(Schema.Type.INT), null, 1, null));
    expectThrows(AvroTypeException.class,
        () -> new Schema.Field("badDefault", primitive(Schema.Type.INT), null, "not an int"));
  }

  @Test
  public void parserReadsSchemasFromStringPiecesFileAndInputStream() throws Exception {
    String recordJson = "{\"type\":\"record\",\"name\":\"Piece\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}";
    Schema parsedFromPieces = new Schema.Parser().parse("{\"type\":\"record\",", "\"name\":\"Piece\",",
        "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    assertEquals("Piece", parsedFromPieces.getName());

    File file = temporaryFolder.newFile("schema.avsc");
    try (FileOutputStream out = new FileOutputStream(file)) {
      out.write(recordJson.getBytes(StandardCharsets.UTF_8));
    }
    assertEquals(parsedFromPieces, new Schema.Parser().parse(file));
    assertEquals(parsedFromPieces, Schema.parse(file));

    NonClosingInputStream input = new NonClosingInputStream(recordJson.getBytes(StandardCharsets.UTF_8));
    assertEquals(parsedFromPieces, new Schema.Parser().parse(input));
    assertFalse("Parser.parse(InputStream) must not close caller-owned input streams", input.wasClosed());
    input.close();
    assertTrue(input.wasClosed());

    assertEquals(parsedFromPieces, Schema.parse(new ByteArrayInputStream(recordJson.getBytes(StandardCharsets.UTF_8))));
  }

  @Test
  public void parserRejectsInvalidJsonDanglingContentAndMissingRequiredStructure() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{not valid json}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"int\" \"long\""));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("null"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"F\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"E\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("42"));
  }

  @Test
  public void parserTracksNamedTypesAndAddTypesSupportsForwardReferencesAcrossParses() {
    Schema known = Schema.createRecord("Known", null, "example.avro", false, Collections.emptyList());
    Map<String, Schema> byName = new LinkedHashMap<>();
    byName.put(known.getFullName(), known);

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(byName));
    assertTrue(parser.getTypes().containsKey("example.avro.Known"));

    Schema holder = parser.parse("{\"type\":\"record\",\"name\":\"Holder\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"known\",\"type\":\"Known\"}]}");
    assertEquals("example.avro.Known", holder.getField("known").schema().getFullName());

    Schema.Parser iterableParser = new Schema.Parser();
    iterableParser.addTypes(Collections.singletonList(known));
    assertTrue(iterableParser.getTypes().containsKey("example.avro.Known"));
  }

  @Test
  public void parserValidateDefaultsFlagControlsDefaultValidation() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\","
        + "\"fields\":[{\"name\":\"i\",\"type\":\"int\",\"default\":\"bad\"}]}";

    Schema.Parser validating = new Schema.Parser();
    assertTrue(validating.getValidateDefaults());
    expectThrows(AvroTypeException.class, () -> validating.parse(invalidDefault));

    Schema.Parser nonValidating = new Schema.Parser().setValidateDefaults(false);
    assertFalse(nonValidating.getValidateDefaults());
    assertEquals("BadDefault", nonValidating.parse(invalidDefault).getName());
  }

  @Test
  public void parseInternalParsesSchemasWithoutRequiringPublicParserCommit() {
    Schema.Parser parser = new Schema.Parser();
    Schema primitive = parser.parseInternal("\"int\"");
    Schema record = parser.parseInternal("{\"type\":\"record\",\"name\":\"InternalOnly\",\"fields\":[]}");

    assertEquals(Schema.Type.INT, primitive.getType());
    assertEquals("InternalOnly", record.getName());
  }

  @Test
  public void staticParseOverloadsAndNameValidationFlagWork() throws Exception {
    assertEquals(Schema.Type.INT, Schema.parse("\"int\"").getType());
    assertEquals("bad name", Schema.parse("{\"type\":\"record\",\"name\":\"bad name\",\"fields\":[]}", false).getName());
    expectThrows(SchemaParseException.class,
        () -> Schema.parse("{\"type\":\"record\",\"name\":\"bad name\",\"fields\":[]}", true));
  }

  @Test
  public void parserAcceptsCommentsAndPrimitiveObjectsWithPropertiesAndLogicalTypes() {
    Schema schema = new Schema.Parser().parse("/* comment */ {\"type\":\"int\",\"logicalType\":\"date\",\"custom\":\"v\"}");

    assertEquals(Schema.Type.INT, schema.getType());
    assertEquals("v", schema.getProp("custom"));
    assertNotNull(schema.getLogicalType());
    assertEquals("date", schema.getLogicalType().getName());
    assertTrue(schema.toString().contains("\"custom\":\"v\""));
  }

  @Test
  public void parserCapturesDocsAliasesFieldOrdersDefaultsAndFloatingTextDefaults() {
    Schema schema = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"WithMeta\",\"doc\":\"doc\","
        + "\"aliases\":[\"OldWithMeta\"],\"fields\":["
        + "{\"name\":\"d\",\"type\":\"double\",\"default\":\"NaN\",\"order\":\"descending\","
        + "\"doc\":\"double doc\",\"aliases\":[\"oldD\"],\"fieldCustom\":\"fv\"}]}" );

    assertEquals("doc", schema.getDoc());
    assertTrue(schema.getAliases().contains("OldWithMeta"));
    Schema.Field field = schema.getField("d");
    assertEquals("double doc", field.doc());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertTrue(Double.isNaN(((Number) field.defaultVal()).doubleValue()));
    assertTrue(field.aliases().contains("oldD"));
    assertEquals("fv", field.getProp("fieldCustom"));
  }

  @Test
  public void parserRejectsBadAliasesAndBadEnumOrFieldOrderMetadata() {
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"aliases\":\"bad\",\"fields\":[]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"aliases\":[1],\"fields\":[]}"));
    expectThrows(IllegalArgumentException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"fields\":[{\"name\":\"f\",\"type\":\"int\",\"order\":\"sideways\"}]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"E\",\"symbols\":[\"A\",\"A\"]}"));
  }

  @Test
  public void globalNameValidatorAndValidateDefaultsThreadLocalsCanBeChangedAndRestored() {
    NameValidator originalValidator = Schema.getNameValidator();
    boolean originalValidateDefaults = Schema.getValidateDefaults();
    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());
      assertEquals("not valid", new Schema.Field("not valid", primitive(Schema.Type.INT)).name());

      Schema.setValidateDefaults(false);
      assertFalse(Schema.getValidateDefaults());
      Schema.Field invalidButAllowed = new Schema.Field("bad", primitive(Schema.Type.INT), null, "not an int");
      assertTrue(invalidButAllowed.hasDefaultValue());
    } finally {
      Schema.setNameValidator(originalValidator);
      Schema.setValidateDefaults(originalValidateDefaults);
    }
  }

  @Test
  public void parserNullNameValidatorUsesNoValidation() {
    Schema schema = new Schema.Parser((NameValidator) null)
        .parse("{\"type\":\"record\",\"name\":\"name with spaces\",\"fields\":[]}");
    assertEquals("name with spaces", schema.getName());
  }

  @Test
  public void isValidDefaultCoversPrimitiveArrayMapUnionAndRecordDefaults() throws Exception {
    assertTrue(primitive(Schema.Type.STRING).isValidDefault(json("\"s\"")));
    assertTrue(primitive(Schema.Type.BYTES).isValidDefault(json("\"bytes\"")));
    assertTrue(primitive(Schema.Type.INT).isValidDefault(json("1")));
    assertFalse(primitive(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertTrue(primitive(Schema.Type.LONG).isValidDefault(json("9223372036854775807")));
    assertTrue(primitive(Schema.Type.FLOAT).isValidDefault(json("1.5")));
    assertTrue(primitive(Schema.Type.DOUBLE).isValidDefault(new DoubleNode(Double.NaN)));
    assertTrue(primitive(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertTrue(primitive(Schema.Type.NULL).isValidDefault(NullNode.getInstance()));
    assertFalse(primitive(Schema.Type.NULL).isValidDefault(new IntNode(1)));
    assertFalse(primitive(Schema.Type.STRING).isValidDefault(null));

    Schema enumSchema = Schema.createEnum("E", null, null, Arrays.asList("A", "B"));
    assertTrue(enumSchema.isValidDefault(json("\"A\"")));
    assertFalse(enumSchema.isValidDefault(json("1")));

    Schema fixed = Schema.createFixed("F", null, null, 2);
    assertTrue(fixed.isValidDefault(json("\"xx\"")));
    assertFalse(fixed.isValidDefault(json("2")));

    Schema intArray = Schema.createArray(primitive(Schema.Type.INT));
    assertTrue(intArray.isValidDefault(json("[1,2,3]")));
    assertFalse(intArray.isValidDefault(json("[1,\"bad\"]")));
    assertFalse(intArray.isValidDefault(json("{}")));

    Schema intMap = Schema.createMap(primitive(Schema.Type.INT));
    assertTrue(intMap.isValidDefault(json("{\"a\":1,\"b\":2}")));
    assertFalse(intMap.isValidDefault(json("{\"a\":\"bad\"}")));
    assertFalse(intMap.isValidDefault(json("[]")));

    Schema intOrString = Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.STRING));
    assertTrue(intOrString.isValidDefault(json("1")));
    assertTrue(intOrString.isValidDefault(json("\"s\"")));
    assertFalse(intOrString.isValidDefault(json("true")));

    Schema recordWithFieldDefault = Schema.createRecord("R", null, null, false,
        fields(new Schema.Field("i", primitive(Schema.Type.INT), null, 1)));
    assertTrue(recordWithFieldDefault.isValidDefault(json("{}")));
    assertTrue(recordWithFieldDefault.isValidDefault(json("{\"i\":2}")));
    assertFalse(recordWithFieldDefault.isValidDefault(json("{\"i\":\"bad\"}")));
    assertFalse(recordWithFieldDefault.isValidDefault(json("[]")));

    Schema recordWithoutFieldDefault = Schema.createRecord("R2", null, null, false,
        fields(field("i", primitive(Schema.Type.INT))));
    assertFalse(recordWithoutFieldDefault.isValidDefault(json("{}")));
  }

  @Test
  public void parseJsonToObjectConvertsJsonIntoJavaCollectionsAndScalars() {
    Object object = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,null],\"c\":\"text\"}");

    assertTrue(object instanceof Map);
    Map<?, ?> map = (Map<?, ?>) object;
    assertEquals(1, ((Number) map.get("a")).intValue());
    assertEquals("text", map.get("c"));
    assertTrue(map.get("b") instanceof List);
    assertEquals(Boolean.TRUE, ((List<?>) map.get("b")).get(0));
    assertAvroNullSentinel(((List<?>) map.get("b")).get(1));

    assertEquals("x", Schema.parseJsonToObject("\"x\""));
    assertEquals(Boolean.FALSE, Schema.parseJsonToObject("false"));
    expectThrows(RuntimeException.class, () -> Schema.parseJsonToObject("{bad json}"));
  }

  @Test
  public void applyAliasesRenamesRecordsAndFieldsAccordingToReaderAliases() {
    Schema writer = Schema.createRecord("User", "writer doc", "old.avro", false,
        fields(new Schema.Field("oldName", primitive(Schema.Type.STRING), "name doc", "n")));

    Schema reader = Schema.createRecord("User", null, "new.avro", false);
    reader.addAlias("old.avro.User");
    Schema.Field readerField = new Schema.Field("newName", primitive(Schema.Type.STRING), null, "n");
    readerField.addAlias("oldName");
    reader.setFields(fields(readerField));

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("new.avro.User", rewritten.getFullName());
    assertNotNull(rewritten.getField("newName"));
    assertNull(rewritten.getField("oldName"));
    assertEquals("writer doc", rewritten.getDoc());
    assertEquals("name doc", rewritten.getField("newName").doc());
    assertEquals("n", rewritten.getField("newName").defaultVal());
  }

  @Test
  public void applyAliasesHandlesEnumFixedArrayMapUnionAndNoAliasShortcuts() {
    Schema writerEnum = Schema.createEnum("Color", null, "old.avro", Arrays.asList("RED", "BLUE"), "RED");
    Schema readerEnum = Schema.createEnum("Color", null, "new.avro", Arrays.asList("RED", "BLUE"), "RED");
    readerEnum.addAlias("old.avro.Color");
    assertEquals("new.avro.Color", Schema.applyAliases(writerEnum, readerEnum).getFullName());

    Schema writerFixed = Schema.createFixed("Hash", null, "old.avro", 8);
    Schema readerFixed = Schema.createFixed("Hash", null, "new.avro", 8);
    readerFixed.addAlias("old.avro.Hash");
    assertEquals("new.avro.Hash", Schema.applyAliases(writerFixed, readerFixed).getFullName());

    Schema writerArray = Schema.createArray(writerFixed);
    Schema readerArray = Schema.createArray(readerFixed);
    assertEquals("new.avro.Hash", Schema.applyAliases(writerArray, readerArray).getElementType().getFullName());

    Schema writerMap = Schema.createMap(writerFixed);
    Schema readerMap = Schema.createMap(readerFixed);
    assertEquals("new.avro.Hash", Schema.applyAliases(writerMap, readerMap).getValueType().getFullName());

    Schema writerUnion = Schema.createUnion(primitive(Schema.Type.NULL), writerFixed);
    Schema readerUnion = Schema.createUnion(primitive(Schema.Type.NULL), readerFixed);
    assertEquals("new.avro.Hash", Schema.applyAliases(writerUnion, readerUnion).getTypes().get(1).getFullName());

    Schema noAliasReader = Schema.createRecord("NoAlias", null, "old.avro", false, Collections.emptyList());
    Schema noAliasWriter = Schema.createRecord("NoAliasWriter", null, "old.avro", false, Collections.emptyList());
    assertSame(noAliasWriter, Schema.applyAliases(noAliasWriter, noAliasReader));
    assertSame(writerEnum, Schema.applyAliases(writerEnum, writerEnum));
  }

  @Test
  public void seenPairUsesObjectIdentityRatherThanObjectEquality() {
    String left1 = new String("left");
    String left2 = new String("left");
    Object right = new Object();

    Schema.SeenPair pair = new Schema.SeenPair(left1, right);
    assertEquals(pair, new Schema.SeenPair(left1, right));
    assertEquals(pair.hashCode(), new Schema.SeenPair(left1, right).hashCode());
    assertNotEquals(pair, new Schema.SeenPair(left2, right));
    assertNotEquals(pair, new Schema.SeenPair(right, left1));
    assertNotEquals(pair, "not a pair");
  }

  @Test
  public void namesMapHandlesPrimitiveLookupNamespaceLookupDuplicatesAndSpaceMutation() {
    Schema.Names names = new Schema.Names("example.avro");
    assertEquals("example.avro", names.space());
    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema record = Schema.createRecord("Known", null, "example.avro", false, Collections.emptyList());
    names.add(record);
    assertSame(record, names.get("Known"));
    assertSame(record, names.get("example.avro.Known"));
    assertTrue(names.contains(record));
    assertEquals(names, names);
    assertEquals(names.hashCode(), names.hashCode());

    names.space("other.avro");
    assertEquals("other.avro", names.space());
    assertSame(record, names.get("example.avro.Known"));

    Schema same = Schema.createRecord("Known", null, "example.avro", false, Collections.emptyList());
    assertSame(same, names.put(new Schema.Name("Known", "example.avro"), same));

    Schema conflicting = Schema.createRecord("Known", null, "example.avro", false,
        fields(field("id", primitive(Schema.Type.INT))));
    expectThrows(SchemaParseException.class, () -> names.add(conflicting));
  }

  @Test
  public void lockableArrayListAllowsMutationBeforeLockAndRejectsEveryMutationAfterLock() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>("a", "b");
    assertTrue(list.add("c"));
    assertTrue(list.remove("b"));
    list.addAll(Arrays.asList("d", "e"));
    list.addAll(1, Collections.singleton("inserted"));
    assertEquals(Arrays.asList("a", "inserted", "c", "d", "e"), list);

    List<String> locked = list.lock();
    assertSame(list, locked);
    assertEquals(list, new Schema.LockableArrayList<>(list));
    assertEquals(list.hashCode(), new Schema.LockableArrayList<>(list).hashCode());

    expectThrows(IllegalStateException.class, () -> list.add("x"));
    expectThrows(IllegalStateException.class, () -> list.remove("a"));
    expectThrows(IllegalStateException.class, () -> list.remove(0));
    expectThrows(IllegalStateException.class, () -> list.addAll(Collections.singleton("x")));
    expectThrows(IllegalStateException.class, () -> list.addAll(0, Collections.singleton("x")));
    expectThrows(IllegalStateException.class, () -> list.removeAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, () -> list.retainAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, list::clear);

    assertSame(list, list.lock());
    assertEquals(Arrays.asList("a", "inserted", "c", "d", "e"), list);
  }

  @Test
  public void schemaSerializationUsesSerializableReplacementAndRoundTripsByParsingStringForm() throws Exception {
    Schema original = Schema.createRecord("SerializableRecord", null, "example.avro", false,
        fields(new Schema.Field("id", primitive(Schema.Type.INT), null, 1)));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
      out.writeObject(original);
    }

    Object roundTripped;
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
      roundTripped = in.readObject();
    }

    assertTrue(roundTripped instanceof Schema);
    assertEquals(original, roundTripped);
    assertEquals("SerializableRecord", ((Schema) roundTripped).getName());
  }

  @Test
  public void parseContextConstructorCanBeUsedByParser() {
    ParseContext context = new ParseContext(NameValidator.UTF_VALIDATOR);
    Schema.Parser parser = new Schema.Parser(context);
    Schema parsed = parser.parse("{\"type\":\"record\",\"name\":\"FromContext\",\"fields\":[]}");

    assertEquals("FromContext", parsed.getName());
    assertTrue(parser.getTypes().containsKey("FromContext"));
  }

  @Test
  public void parsedRecordLogicalTypeOnFieldMustBeNestedInsideTypeToBeApplied() {
    Schema misplaced = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"fields\":["
        + "{\"name\":\"d\",\"type\":\"int\",\"logicalType\":\"date\"}]}");
    assertNull(misplaced.getField("d").schema().getLogicalType());

    Schema nested = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R2\",\"fields\":["
        + "{\"name\":\"d\",\"type\":{\"type\":\"int\",\"logicalType\":\"date\"}}]}");
    assertNotNull(nested.getField("d").schema().getLogicalType());
    assertEquals("date", nested.getField("d").schema().getLogicalType().getName());
  }

  @Test
  public void fixedSchemaRejectsInvalidSizeFromParser() {
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"F\",\"size\":\"4\"}"));
  }

  @Test
  public void schemaPropertiesChangeEqualityAndStringRepresentation() {
    Schema withStringProp = primitive(Schema.Type.STRING);
    Schema withObjectProp = primitive(Schema.Type.STRING);

    withStringProp.addProp("custom", "value");
    withObjectProp.addProp("custom", jsonUnchecked("{\"nested\":true}"));

    assertEquals("value", withStringProp.getProp("custom"));
    assertTrue(withStringProp.toString().contains("\"type\":\"string\""));
    assertTrue(withStringProp.toString().contains("\"custom\":\"value\""));
    assertTrue(withObjectProp.toString().contains("\"nested\":true"));
    assertNotEquals(withStringProp, withObjectProp);
  }

  private static JsonNode jsonUnchecked(String json) {
    try {
      return json(json);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  private static final class NonClosingInputStream extends InputStream {
    private final ByteArrayInputStream delegate;
    private boolean closed;

    private NonClosingInputStream(byte[] bytes) {
      this.delegate = new ByteArrayInputStream(bytes);
    }

    @Override
    public int read() {
      return delegate.read();
    }

    @Override
    public int read(byte[] b, int off, int len) {
      return delegate.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      delegate.close();
    }

    private boolean wasClosed() {
      return closed;
    }
  }
}
