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
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.path.TracingAvroTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Consolidated JUnit 4 tests for Schema.java.
 *
 * The suite covers the public Schema factory/accessor API, Schema.Field,
 * Schema.Parser, deprecated parse helpers, aliases, default validation,
 * equality/hash behavior, JSON rendering, parser error paths, and package-level
 * helper types that are relevant when testing Schema from the org.apache.avro
 * package.
 */
public class TestToTSchema {

  @Rule
  public TemporaryFolder tmp = new TemporaryFolder();

  private static final ObjectMapper JSON = new ObjectMapper();

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
      AssertionError error = new AssertionError(
          "Expected " + expectedType.getName() + " but caught " + actual.getClass().getName());
      error.initCause(actual);
      throw error;
    }
    fail("Expected exception of type " + expectedType.getName());
    return null;
  }

  private static JsonNode json(String rawJson) throws IOException {
    return JSON.readTree(rawJson);
  }

  private static Schema intSchema() {
    return Schema.create(Schema.Type.INT);
  }

  private static Schema stringSchema() {
    return Schema.create(Schema.Type.STRING);
  }

  private static Schema record(String name, Schema.Field... fields) {
    return Schema.createRecord(name, "doc for " + name, "example.avro", false, Arrays.asList(fields));
  }

  private static void assertRoundTripsThroughJson(Schema schema) {
    Schema reparsed = new Schema.Parser().parse(schema.toString());
    assertEquals(schema, reparsed);
  }

  @Test
  public void typeGetNameReturnsLowerCaseAvroTypeNames() {
    for (Schema.Type type : Schema.Type.values()) {
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), type.getName());
    }
  }

  @Test
  public void createPrimitiveSchemasAndRejectsNamedOrComplexTypes() {
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
    expectThrows(NullPointerException.class, () -> Schema.create(null));
  }

  @Test
  public void primitiveAccessorsUsePrimitiveDefaultsAndRejectWrongKindAccessors() {
    Schema primitive = stringSchema();

    assertEquals("string", primitive.getName());
    assertEquals("string", primitive.getFullName());
    assertNull(primitive.getDoc());
    assertFalse(primitive.isUnion());
    assertFalse(primitive.isNullable());

    expectThrows(AvroRuntimeException.class, () -> primitive.getField("x"));
    expectThrows(AvroRuntimeException.class, primitive::getFields);
    expectThrows(AvroRuntimeException.class, primitive::hasFields);
    expectThrows(AvroRuntimeException.class, () -> primitive.setFields(Collections.<Schema.Field>emptyList()));
    expectThrows(AvroRuntimeException.class, primitive::getEnumSymbols);
    expectThrows(AvroRuntimeException.class, primitive::getEnumDefault);
    expectThrows(AvroRuntimeException.class, () -> primitive.getEnumOrdinal("A"));
    expectThrows(AvroRuntimeException.class, () -> primitive.hasEnumSymbol("A"));
    expectThrows(AvroRuntimeException.class, primitive::getNamespace);
    expectThrows(AvroRuntimeException.class, () -> primitive.addAlias("Alias"));
    expectThrows(AvroRuntimeException.class, () -> primitive.addAlias("Alias", "ns"));
    expectThrows(AvroRuntimeException.class, primitive::getAliases);
    expectThrows(AvroRuntimeException.class, primitive::isError);
    expectThrows(AvroRuntimeException.class, primitive::getElementType);
    expectThrows(AvroRuntimeException.class, primitive::getValueType);
    expectThrows(AvroRuntimeException.class, primitive::getTypes);
    expectThrows(AvroRuntimeException.class, () -> primitive.getIndexNamed("string"));
    expectThrows(AvroRuntimeException.class, primitive::getFixedSize);
  }

  @Test
  public void addPropStoresStringAndObjectPropertiesAndInvalidatesHashCodeCache() {
    Schema before = intSchema();
    Schema after = intSchema();

    int cachedHash = after.hashCode();
    after.addProp("customString", "abc");
    after.addProp("customNumber", Integer.valueOf(7));

    assertNotEquals(before, after);
    assertNotEquals(cachedHash, after.hashCode());
    assertEquals("abc", after.getProp("customString"));
    assertEquals(Integer.valueOf(7), after.getObjectProp("customNumber"));
    assertTrue(after.toString().contains("customString"));
    assertTrue(after.toString().contains("customNumber"));
  }

  @Test
  public void parsedLogicalTypeIsAvailableFromSchema() {
    Schema date = new Schema.Parser().parse("{\"type\":\"int\",\"logicalType\":\"date\"}");

    assertNotNull(date.getLogicalType());
    assertEquals("date", date.getLogicalType().getName());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void anonymousRecordCanBeCreatedFromFields() {
    Schema.Field field = new Schema.Field("id", intSchema());

    Schema anonymous = Schema.createRecord(Collections.singletonList(field));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertNull(anonymous.getName());
    assertTrue(anonymous.hasFields());
    assertSame(field, anonymous.getField("id"));
    assertEquals(0, field.pos());
  }

  @Test
  public void namedRecordReportsNameDocNamespaceFieldsAndErrorFlag() {
    Schema.Field id = new Schema.Field("id", intSchema(), "identifier", 1);
    Schema.Field label = new Schema.Field("label", stringSchema(), "human label", "unknown");

    Schema record = Schema.createRecord("User", "user doc", "example.avro", true, Arrays.asList(id, label));

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("user doc", record.getDoc());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertTrue(record.isError());
    assertTrue(record.hasFields());
    assertEquals(Arrays.asList(id, label), record.getFields());
    assertSame(label, record.getField("label"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, label.pos());
    assertRoundTripsThroughJson(record);
  }

  @Test
  public void recordCreatedWithoutFieldsRejectsFieldAccessUntilSetFieldsIsCalled() {
    Schema record = Schema.createRecord("Delayed", "doc", "example.avro", false);

    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, record::getFields);
    expectThrows(AvroRuntimeException.class, () -> record.getField("id"));

    Schema.Field id = new Schema.Field("id", intSchema());
    record.setFields(Collections.singletonList(id));

    assertTrue(record.hasFields());
    assertSame(id, record.getField("id"));
  }

  @Test
  public void setFieldsMayOnlyBeCalledOnceAndRejectsDuplicateNamesAndReusedFields() {
    Schema record = Schema.createRecord("Once", null, "example.avro", false);
    record.setFields(Collections.singletonList(new Schema.Field("id", intSchema())));
    expectThrows(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(new Schema.Field("again", intSchema()))));

    Schema duplicate = Schema.createRecord("Duplicate", null, "example.avro", false);
    expectThrows(AvroRuntimeException.class, () -> duplicate.setFields(Arrays.asList(new Schema.Field("x", intSchema()),
        new Schema.Field("x", stringSchema()))));

    Schema.Field reused = new Schema.Field("reused", intSchema());
    Schema first = Schema.createRecord("First", null, "example.avro", false,
        Collections.singletonList(reused));
    assertSame(reused, first.getField("reused"));
    expectThrows(AvroRuntimeException.class, () -> Schema.createRecord("Second", null, "example.avro", false,
        Collections.singletonList(reused)));
  }

  @Test
  public void recordFieldsListIsLockedAfterAssignment() {
    Schema record = record("LockedFields", new Schema.Field("id", intSchema()));

    expectThrows(IllegalStateException.class, () -> record.getFields().add(new Schema.Field("other", intSchema())));
    expectThrows(IllegalStateException.class, () -> record.getFields().clear());
  }

  @Test
  public void namedSchemasSupportAliasesWithDefaultAndExplicitNamespaces() {
    Schema record = Schema.createRecord("User", null, "example.avro", false, Collections.<Schema.Field>emptyList());

    record.addAlias("OldUser");
    record.addAlias("VeryOldUser", "legacy.avro");
    record.addAlias("RootUser", "");

    assertTrue(record.getAliases().contains("example.avro.OldUser"));
    assertTrue(record.getAliases().contains("legacy.avro.VeryOldUser"));
    assertTrue(record.getAliases().contains(".RootUser"));
  }

  @Test
  public void enumSchemaReportsSymbolsOrdinalsAndDefault() {
    Schema color = Schema.createEnum("Color", "color doc", "example.avro", Arrays.asList("RED", "GREEN", "BLUE"),
        "GREEN");

    assertEquals(Schema.Type.ENUM, color.getType());
    assertEquals("Color", color.getName());
    assertEquals("color doc", color.getDoc());
    assertEquals("example.avro", color.getNamespace());
    assertEquals("example.avro.Color", color.getFullName());
    assertEquals(Arrays.asList("RED", "GREEN", "BLUE"), color.getEnumSymbols());
    assertEquals("GREEN", color.getEnumDefault());
    assertTrue(color.hasEnumSymbol("RED"));
    assertFalse(color.hasEnumSymbol("PURPLE"));
    assertEquals(0, color.getEnumOrdinal("RED"));
    assertEquals(2, color.getEnumOrdinal("BLUE"));
    assertRoundTripsThroughJson(color);
  }

  @Test
  public void enumRejectsDuplicateSymbolsInvalidDefaultsAndUnknownOrdinalLookups() {
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadDuplicate", null, "example.avro", Arrays.asList("A", "A")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("BadDefault", null, "example.avro", Arrays.asList("A", "B"), "C"));

    Schema good = Schema.createEnum("Good", null, "example.avro", Arrays.asList("A", "B"));
    expectThrows(TracingAvroTypeException.class, () -> good.getEnumOrdinal("C"));
    expectThrows(IllegalStateException.class, () -> good.getEnumSymbols().add("C"));
  }

  @Test
  public void arrayMapUnionAndFixedExposeTheirSpecificTypes() {
    Schema array = Schema.createArray(stringSchema());
    Schema map = Schema.createMap(intSchema());
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema());
    Schema fixed = Schema.createFixed("Md5", "fixed doc", "example.avro", 16);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertEquals(stringSchema(), array.getElementType());
    assertEquals(Schema.Type.MAP, map.getType());
    assertEquals(intSchema(), map.getValueType());
    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(2, union.getTypes().size());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertNull(union.getIndexNamed("int"));
    assertTrue(union.getName().contains("null"));
    assertTrue(union.getName().contains("string"));
    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals(16, fixed.getFixedSize());
    assertRoundTripsThroughJson(array);
    assertRoundTripsThroughJson(map);
    assertRoundTripsThroughJson(union);
    assertRoundTripsThroughJson(fixed);
  }

  @Test
  public void unionRejectsNestedUnionsDuplicateBranchesAndProperties() {
    Schema nested = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema());

    expectThrows(AvroRuntimeException.class, () -> Schema.createUnion(stringSchema(), stringSchema()));
    expectThrows(AvroRuntimeException.class, () -> Schema.createUnion(stringSchema(), nested));

    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema());
    expectThrows(AvroRuntimeException.class, () -> union.addProp("x", "y"));
    expectThrows(IllegalStateException.class, () -> union.getTypes().add(intSchema()));
  }

  @Test
  public void emptyUnionIsRepresentedAsAUnionWithNoBranches() {
    Schema union = Schema.createUnion(Collections.<Schema>emptyList());

    assertTrue(union.isUnion());
    assertFalse(union.isNullable());
    assertTrue(union.getTypes().isEmpty());
    assertNull(union.getIndexNamed("anything"));
  }

  @Test
  public void nullableRecognizesNullSchemaAndUnionsContainingNull() {
    assertTrue(Schema.create(Schema.Type.NULL).isNullable());
    assertFalse(stringSchema().isNullable());
    assertTrue(Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema()).isNullable());
    assertFalse(Schema.createUnion(stringSchema(), intSchema()).isNullable());
  }

  @Test
  public void toStringPrettyAndReferencedSchemaRenderingAreParseableOrReferenceOnlyAsDocumented() {
    Schema child = record("Child", new Schema.Field("name", stringSchema()));
    Schema parent = record("Parent", new Schema.Field("child", child));

    String compact = parent.toString();
    String pretty = parent.toString(true);
    String withKnownChild = parent.toString(Collections.singleton(child), false);

    assertEquals(parent, new Schema.Parser().parse(compact));
    assertEquals(parent, new Schema.Parser().parse(pretty));
    assertTrue(pretty.contains(System.lineSeparator()) || pretty.contains("\n"));
    assertTrue(withKnownChild.contains("\"Child\""));
  }

  @Test
  public void equalsAndHashCodeRespectTypeNameFieldsSymbolsSizeAndProperties() {
    Schema one = record("Comparable", new Schema.Field("id", intSchema()));
    Schema same = record("Comparable", new Schema.Field("id", intSchema()));
    Schema differentField = record("Comparable", new Schema.Field("name", stringSchema()));
    Schema differentName = record("ComparableOther", new Schema.Field("id", intSchema()));

    assertEquals(one, same);
    assertEquals(one.hashCode(), same.hashCode());
    assertNotEquals(one, differentField);
    assertNotEquals(one, differentName);
    assertNotEquals(one, null);
    assertNotEquals(one, "not a schema");

    same.addProp("p", "v");
    assertNotEquals(one, same);

    assertEquals(Schema.createEnum("E", null, "example.avro", Arrays.asList("A")),
        Schema.createEnum("E", null, "example.avro", Arrays.asList("A")));
    assertNotEquals(Schema.createFixed("F", null, "example.avro", 1),
        Schema.createFixed("F", null, "example.avro", 2));
  }

  @Test
  public void fieldConstructorsAndAccessorsExposeNameSchemaDocDefaultOrderAndPosition() {
    Schema.Field field = new Schema.Field("count", intSchema(), "count doc", 3, Schema.Field.Order.DESCENDING);

    assertEquals("count", field.name());
    assertEquals(intSchema(), field.schema());
    assertEquals("count doc", field.doc());
    assertEquals(Integer.valueOf(3), field.defaultVal());
    assertTrue(field.hasDefaultValue());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertEquals(-1, field.pos());

    Schema owner = record("Owner", field);
    assertEquals(0, field.pos());
    assertSame(field, owner.getField("count"));
    assertTrue(field.toString().contains("count"));
    assertTrue(field.toString().contains("pos:0"));
  }

  @Test
  public void fieldWithNoDefaultCanBeDistinguishedFromExplicitNullDefault() {
    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema());
    Schema.Field absent = new Schema.Field("absent", nullableString);
    Schema.Field explicitNull = new Schema.Field("explicitNull", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertFalse(absent.hasDefaultValue());
    assertNull(absent.defaultVal());

    assertTrue(explicitNull.hasDefaultValue());
    assertSame(JsonProperties.NULL_VALUE, explicitNull.defaultVal());
  }

  @Test
  public void fieldCopyConstructorCopiesMetadataAliasesAndPropertiesWhileChangingSchema() {
    Schema.Field original = new Schema.Field("label", stringSchema(), "label doc", "unknown", Schema.Field.Order.IGNORE);
    original.addAlias("old_label");
    original.addProp("fieldProp", "fieldValue");

    Schema.Field copy = new Schema.Field(original, stringSchema());

    assertEquals(original.name(), copy.name());
    assertEquals(original.doc(), copy.doc());
    assertEquals(original.defaultVal(), copy.defaultVal());
    assertEquals(original.order(), copy.order());
    assertEquals(original.aliases(), copy.aliases());
    assertEquals("fieldValue", copy.getProp("fieldProp"));
    assertEquals(original, copy);
  }

  @Test
  public void fieldAliasesAreUnmodifiableToCallers() {
    Schema.Field field = new Schema.Field("name", stringSchema());
    assertTrue(field.aliases().isEmpty());

    field.addAlias("old_name");

    assertTrue(field.aliases().contains("old_name"));
    expectThrows(UnsupportedOperationException.class, () -> field.aliases().add("another"));
  }

  @Test
  public void fieldRejectsInvalidNamesNullSchemaNullOrderAndInvalidDefaultValues() {
    expectThrows(SchemaParseException.class, () -> new Schema.Field("1bad", intSchema()));
    expectThrows(NullPointerException.class, () -> new Schema.Field("badSchema", null));
    expectThrows(NullPointerException.class, () -> new Schema.Field("badOrder", intSchema(), null, 1, null));
    expectThrows(AvroTypeException.class, () -> new Schema.Field("badDefault", intSchema(), null, "not an int"));
  }

  @Test
  public void fieldEqualityHandlesNaNDefaultsAndIgnoresDocAndAliases() {
    Schema.Field left = new Schema.Field("value", Schema.create(Schema.Type.DOUBLE), "left doc", Double.NaN);
    Schema.Field right = new Schema.Field("value", Schema.create(Schema.Type.DOUBLE), "right doc", Double.NaN);
    right.addAlias("ignored_alias");

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertTrue(Double.isNaN(((Double) left.defaultVal()).doubleValue()));
  }

  @Test
  public void parserDefaultConfigurationValidatesDefaultsAndCollectsNamedTypes() {
    Schema.Parser parser = new Schema.Parser();

    assertTrue(parser.getValidateDefaults());
    Schema schema = parser.parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"default\":0}]}");

    assertEquals("example.avro.User", schema.getFullName());
    assertTrue(parser.getTypes().containsKey("example.avro.User"));
    expectThrows(AvroTypeException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadDefault\",\"fields\":["
            + "{\"name\":\"id\",\"type\":\"int\",\"default\":\"bad\"}]}"));
  }

  @Test
  public void parserCanDisableDefaultValidation() {
    Schema.Parser parser = new Schema.Parser().setValidateDefaults(false);

    Schema schema = parser.parse("{\"type\":\"record\",\"name\":\"Lenient\",\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"default\":\"bad\"}]}");

    Schema.Field field = schema.getField("id");

    assertFalse(parser.getValidateDefaults());
    assertTrue(field.hasDefaultValue());
    assertEquals("bad", field.defaultValue().asText());
  }

  @Test
  public void parserWithNullValidatorDisablesNameValidation() {
    Schema schema = new Schema.Parser((NameValidator) null)
        .parse("{\"type\":\"record\",\"name\":\"1invalid\",\"fields\":[]}");

    assertEquals("1invalid", schema.getName());
  }

  @Test
  public void parserAddTypesAllowsLaterSchemasToReferenceKnownNamedTypes() {
    Schema known = Schema.createRecord("Known", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", intSchema())));
    Schema.Parser iterableParser = new Schema.Parser().addTypes(Collections.singleton(known));

    Schema holder = iterableParser.parse("{\"type\":\"record\",\"name\":\"Holder\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"known\",\"type\":\"Known\"}]}");

    assertEquals(known, holder.getField("known").schema());
    assertEquals("example.avro.Known", holder.getField("known").schema().getFullName());

    Map<String, Schema> typeMap = new LinkedHashMap<>();
    typeMap.put(known.getFullName(), known);
    Schema.Parser mapParser = new Schema.Parser().addTypes(typeMap);

    Schema another = mapParser.parse("{\"type\":\"record\",\"name\":\"AnotherHolder\",\"namespace\":\"example.avro\","
        + "\"fields\":[{\"name\":\"known\",\"type\":\"example.avro.Known\"}]}");

    assertEquals(known, another.getField("known").schema());
    assertEquals("example.avro.Known", another.getField("known").schema().getFullName());
  }

  @Test
  public void parserParsesFromStringFragmentsFileAndInputStream() throws Exception {
    Schema fromFragments = new Schema.Parser().parse("{\"type\":\"record\",", "\"name\":\"Fragmented\",",
        "\"fields\":[]}");
    assertEquals("Fragmented", fromFragments.getName());

    File schemaFile = tmp.newFile("schema.avsc");
    java.nio.file.Files.write(schemaFile.toPath(), "\"string\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.STRING, new Schema.Parser().parse(schemaFile).getType());

    TrackingInputStream stream = new TrackingInputStream(new ByteArrayInputStream("\"int\"".getBytes(StandardCharsets.UTF_8)));
    assertEquals(Schema.Type.INT, new Schema.Parser().parse(stream).getType());
    assertFalse("Parser.parse(InputStream) must not close the caller-owned stream", stream.closed);
  }

  @Test
  public void parserRejectsDanglingContentForStringsButAllowsItForStreams() throws Exception {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"string\" \"int\""));

    ByteArrayInputStream stream = new ByteArrayInputStream("\"string\" \"int\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.STRING, new Schema.Parser().parse(stream).getType());
  }

  @Test
  public void parserParseInternalParsesJsonIntoASchema() {
    Schema schema = new Schema.Parser().parseInternal("\"string\"");

    assertEquals(Schema.Type.STRING, schema.getType());
  }

  @Test
  public void parserAcceptsJsonCommentsBecauseSchemaFactoryEnablesComments() {
    Schema schema = new Schema.Parser().parse("/* leading comment */ \"long\"");

    assertEquals(Schema.Type.LONG, schema.getType());
  }

  @Test
  public void parserRejectsMalformedOrIncompleteSchemas() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{not valid json"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFields\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFieldType\",\"fields\":[{\"name\":\"x\"}]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"NoSymbols\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"BadFixed\",\"size\":\"16\"}"));
  }

  @Test
  public void parserRejectsInvalidAliasesAndInvalidFieldOrder() {
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":\"Old\",\"fields\":[]}"));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliasItem\",\"aliases\":[7],\"fields\":[]}"));
    expectThrows(IllegalArgumentException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadOrder\",\"fields\":["
            + "{\"name\":\"x\",\"type\":\"int\",\"order\":\"sideways\"}]}"));
  }

  @Test
  public void parserConvertsTextualFloatAndDoubleDefaults() {
    Schema schema = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FloatingDefaults\",\"fields\":["
        + "{\"name\":\"f\",\"type\":\"float\",\"default\":\"1.5\"},"
        + "{\"name\":\"d\",\"type\":\"double\",\"default\":\"NaN\"}]}" );

    assertEquals(1.5d, ((Number) schema.getField("f").defaultVal()).doubleValue(), 0.0d);
    assertTrue(Double.isNaN(((Double) schema.getField("d").defaultVal()).doubleValue()));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void deprecatedStaticParseMethodsDelegateToParserBehavior() throws Exception {
    assertEquals(Schema.Type.STRING, Schema.parse("\"string\"").getType());

    File schemaFile = tmp.newFile("staticParse.avsc");
    java.nio.file.Files.write(schemaFile.toPath(), "\"boolean\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.BOOLEAN, Schema.parse(schemaFile).getType());

    ByteArrayInputStream in = new ByteArrayInputStream("\"bytes\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.BYTES, Schema.parse(in).getType());

    assertEquals("1invalid", Schema.parse("{\"type\":\"record\",\"name\":\"1invalid\",\"fields\":[]}", false).getName());
    expectThrows(SchemaParseException.class,
        () -> Schema.parse("{\"type\":\"record\",\"name\":\"1invalid\",\"fields\":[]}", true));
  }

  @SuppressWarnings("deprecation")
  @Test
  public void deprecatedThreadLocalValidationControlsAffectManualConstructionAndAreRestored() {
    NameValidator previousValidator = Schema.getNameValidator();
    boolean previousDefaultValidation = Schema.getValidateDefaults();

    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      Schema unusualName = Schema.createRecord("1record", null, null, false, Collections.<Schema.Field>emptyList());
      assertEquals("1record", unusualName.getName());

      Schema.setValidateDefaults(false);
      Schema.Field badDefault = new Schema.Field("id", intSchema(), null, "not an int");

      assertTrue(badDefault.hasDefaultValue());
      assertEquals("not an int", badDefault.defaultValue().asText());
    } finally {
      Schema.setNameValidator(previousValidator);
      Schema.setValidateDefaults(previousDefaultValidation);
    }
  }

  @Test
  public void isValidDefaultAcceptsAndRejectsValuesForPrimitiveSchemas() throws Exception {
    assertTrue(stringSchema().isValidDefault(json("\"abc\"")));
    assertTrue(Schema.create(Schema.Type.BYTES).isValidDefault(json("\"abc\"")));
    assertTrue(intSchema().isValidDefault(json("2147483647")));
    assertFalse(intSchema().isValidDefault(json("2147483648")));
    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(json("9223372036854775807")));
    assertFalse(Schema.create(Schema.Type.LONG).isValidDefault(json("9223372036854775808")));
    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(json("1.25")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("1.25")));
    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(json("null")));
    assertFalse(stringSchema().isValidDefault(null));
    assertFalse(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("\"true\"")));
  }

  @Test
  public void isValidDefaultAcceptsAndRejectsValuesForComplexSchemas() throws Exception {
    Schema enumSchema = Schema.createEnum("ColorDefault", null, "example.avro", Arrays.asList("RED", "GREEN"));
    Schema fixed = Schema.createFixed("FixedDefault", null, "example.avro", 4);
    Schema array = Schema.createArray(intSchema());
    Schema map = Schema.createMap(stringSchema());
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), stringSchema());
    Schema record = record("DefaultRecord", new Schema.Field("id", intSchema(), null, 0),
        new Schema.Field("label", stringSchema(), null, "unknown"));

    assertTrue(enumSchema.isValidDefault(json("\"RED\"")));
    assertTrue(fixed.isValidDefault(json("\"abcd\"")));
    assertTrue(array.isValidDefault(json("[1,2,3]")));
    assertFalse(array.isValidDefault(json("[1,\"bad\"]")));
    assertTrue(map.isValidDefault(json("{\"a\":\"x\",\"b\":\"y\"}")));
    assertFalse(map.isValidDefault(json("{\"a\":1}")));
    assertTrue(union.isValidDefault(json("null")));
    assertTrue(union.isValidDefault(json("\"ok\"")));
    assertFalse(union.isValidDefault(json("5")));
    assertTrue(record.isValidDefault(json("{\"id\":1,\"label\":\"ok\"}")));
    assertTrue(record.isValidDefault(json("{\"id\":1}")));
    assertFalse(record.isValidDefault(json("{\"id\":\"bad\"}")));
  }

  @Test
  public void parseJsonToObjectConvertsJsonIntoJavaCollectionsAndScalars() {
    Object parsed = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,null,\"x\"]}");

    assertTrue(parsed instanceof Map);
    Map<?, ?> map = (Map<?, ?>) parsed;

    assertEquals(Integer.valueOf(1), map.get("a"));
    assertTrue(map.get("b") instanceof List);

    List<?> values = (List<?>) map.get("b");
    assertEquals(Boolean.TRUE, values.get(0));
    assertSame(JsonProperties.NULL_VALUE, values.get(1));
    assertEquals("x", values.get(2));

    assertEquals("text", Schema.parseJsonToObject("\"text\""));
  }

  @Test
  public void applyAliasesReturnsOriginalWhenNoAliasesAreApplicable() {
    Schema writer = record("NoAliasWriter", new Schema.Field("id", intSchema()));
    Schema reader = record("NoAliasReader", new Schema.Field("id", intSchema()));

    assertSame(writer, Schema.applyAliases(writer, reader));
    assertSame(writer, Schema.applyAliases(writer, writer));
  }

  @Test
  public void applyAliasesRewritesRecordAndFieldNamesFromReaderAliases() {
    Schema writer = record("OldUser", new Schema.Field("old_id", intSchema()),
        new Schema.Field("name", stringSchema()));

    Schema.Field id = new Schema.Field("id", intSchema());
    id.addAlias("old_id");
    Schema reader = record("User", id, new Schema.Field("name", stringSchema()));
    reader.addAlias("OldUser");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("example.avro.User", rewritten.getFullName());
    assertNotNull(rewritten.getField("id"));
    assertNull(rewritten.getField("old_id"));
    assertEquals(intSchema(), rewritten.getField("id").schema());
  }

  @Test
  public void applyAliasesRewritesNestedArrayMapUnionEnumAndFixedBranches() {
    Schema oldEnum = Schema.createEnum("OldStatus", null, "example.avro", Arrays.asList("ON", "OFF"));
    Schema oldFixed = Schema.createFixed("OldHash", null, "example.avro", 8);
    Schema writer = Schema.createUnion(Schema.createArray(oldEnum), Schema.createMap(oldFixed));

    Schema newEnum = Schema.createEnum("Status", null, "example.avro", Arrays.asList("ON", "OFF"));
    newEnum.addAlias("OldStatus");
    Schema newFixed = Schema.createFixed("Hash", null, "example.avro", 8);
    newFixed.addAlias("OldHash");
    Schema reader = Schema.createUnion(Schema.createArray(newEnum), Schema.createMap(newFixed));

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("example.avro.Status", rewritten.getTypes().get(0).getElementType().getFullName());
    assertEquals("example.avro.Hash", rewritten.getTypes().get(1).getValueType().getFullName());
  }

  @Test
  public void seenPairUsesObjectIdentityRatherThanEqualsForEquality() {
    String first = new String("same");
    String second = new String("same");

    Schema.SeenPair sameIdentities = new Schema.SeenPair(first, second);
    Schema.SeenPair sameAgain = new Schema.SeenPair(first, second);
    Schema.SeenPair equalButDifferentIdentity = new Schema.SeenPair(new String("same"), new String("same"));

    assertEquals(sameIdentities, sameAgain);
    assertEquals(sameIdentities.hashCode(), sameAgain.hashCode());
    assertNotEquals(sameIdentities, equalButDifferentIdentity);
    assertNotEquals(sameIdentities, "not a pair");
  }

  @Test
  public void nameParsesQualifiedNamesAndComputesQualifiedOutput() {
    Schema.Name qualified = new Schema.Name("outer.Inner", "ignored.namespace");
    Schema.Name unqualified = new Schema.Name("Plain", "example.avro");
    Schema.Name anonymous = new Schema.Name(null, "example.avro");

    assertEquals("outer.Inner", qualified.toString());
    assertEquals("Inner", qualified.getQualified("outer"));
    assertEquals("outer.Inner", qualified.getQualified("other"));
    assertEquals("Plain", unqualified.getQualified("example.avro"));
    assertEquals("example.avro.Plain", unqualified.getQualified(null));
    assertNull(anonymous.toString());
    assertEquals(new Schema.Name("outer.Inner", null), qualified);
    assertEquals(new Schema.Name("outer.Inner", null).hashCode(), qualified.hashCode());
  }

  @Test
  public void namesRegistryFindsPrimitivesNamedSchemasAndRejectsIncompatibleRedefinitions() {
    Schema.Names names = new Schema.Names("example.avro");
    assertEquals("example.avro", names.space());
    names.space("other.avro");
    assertEquals("other.avro", names.space());

    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema record = Schema.createRecord("Known", null, "other.avro", false, Collections.<Schema.Field>emptyList());
    names.add(record);
    assertTrue(names.contains(record));
    assertSame(record, names.get("Known"));
    assertSame(record, names.put(new Schema.Name("Known", "other.avro"), record));

    Schema incompatible = Schema.createRecord("Known", null, "other.avro", false,
        Collections.singletonList(new Schema.Field("id", intSchema())));
    expectThrows(SchemaParseException.class, () -> names.put(new Schema.Name("Known", "other.avro"), incompatible));
  }

  @Test
  public void lockableArrayListAllowsMutationBeforeLockAndRejectsMutationAfterLock() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>();
    assertTrue(list.add("a"));
    assertTrue(list.addAll(Collections.singleton("b")));
    assertEquals(Arrays.asList("a", "b"), list);
    assertSame(list, list.lock());
    assertSame(list, list.lock());

    expectThrows(IllegalStateException.class, () -> list.add("c"));
    expectThrows(IllegalStateException.class, () -> list.remove("a"));
    expectThrows(IllegalStateException.class, () -> list.remove(0));
    expectThrows(IllegalStateException.class, () -> list.addAll(Collections.singleton("d")));
    expectThrows(IllegalStateException.class, () -> list.addAll(0, Collections.singleton("e")));
    expectThrows(IllegalStateException.class, () -> list.removeAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, () -> list.retainAll(Collections.singleton("a")));
    expectThrows(IllegalStateException.class, list::clear);
  }

  @Test
  public void lockableArrayListConstructorsPopulateInitialContent() {
    Schema.LockableArrayList<String> sized = new Schema.LockableArrayList<>(2);
    sized.add("x");
    assertEquals(Collections.singletonList("x"), sized);

    Schema.LockableArrayList<String> fromList = new Schema.LockableArrayList<>(Arrays.asList("a", "b"));
    assertEquals(Arrays.asList("a", "b"), fromList);

    Schema.LockableArrayList<String> fromVarargs = new Schema.LockableArrayList<>("c", "d");
    assertEquals(Arrays.asList("c", "d"), fromVarargs);
  }

  @Test
  public void namedSchemaCannotUsePrimitiveTypeNameAsItsFullName() {
    expectThrows(AvroTypeException.class,
        () -> Schema.createRecord("int", null, null, false, Collections.<Schema.Field>emptyList()));
    expectThrows(AvroTypeException.class,
        () -> Schema.createEnum("string", null, null, Collections.singletonList("A")));
    expectThrows(AvroTypeException.class, () -> Schema.createFixed("bytes", null, null, 1));
  }

  private static final class TrackingInputStream extends FilterInputStream {
    private boolean closed;

    private TrackingInputStream(InputStream in) {
      super(in);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
