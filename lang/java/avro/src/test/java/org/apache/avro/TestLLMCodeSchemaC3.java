/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.apache.avro;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.avro.path.TracingAvroTypeException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Comprehensive black-box and boundary tests for the refactored Schema_C3.java
 * source. The production type is still org.apache.avro.Schema, so this test is
 * intended to run after Schema_C3.java has replaced Schema.java in the Avro
 * source tree.
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class TestLLMCodeSchemaC3 {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @After
  public void restoreThreadLocalConfiguration() {
    Schema.setValidateDefaults(true);
    Schema.setNameValidator(NameValidator.UTF_VALIDATOR);
  }

  @FunctionalInterface
  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static <T extends Throwable> T expectException(Class<T> expectedType, ThrowingRunnable action) {
    try {
      action.run();
    } catch (Throwable thrown) {
      if (expectedType.isInstance(thrown)) {
        return expectedType.cast(thrown);
      }
      AssertionError assertionError = new AssertionError(
          "Expected " + expectedType.getName() + " but got " + thrown.getClass().getName());
      assertionError.initCause(thrown);
      throw assertionError;
    }
    fail("Expected exception " + expectedType.getName());
    return null;
  }

  private static JsonNode json(String raw) {
    return Schema.parseJson(raw);
  }

  private static Schema primitive(Schema.Type type) {
    return Schema.create(type);
  }

  private static Schema record(String name, String namespace, Schema.Field... fields) {
    return Schema.createRecord(name, "doc-" + name, namespace, false, Arrays.asList(fields));
  }

  private static Schema.Field field(String name, Schema schema) {
    return new Schema.Field(name, schema, "doc-" + name);
  }

  @Test
  public void typeNamesAreLowercaseAndPrimitiveSchemasExposeTypeAndName() {
    for (Schema.Type type : Schema.Type.values()) {
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), type.getName());
    }

    for (Schema.Type type : Arrays.asList(Schema.Type.STRING, Schema.Type.BYTES, Schema.Type.INT, Schema.Type.LONG,
        Schema.Type.FLOAT, Schema.Type.DOUBLE, Schema.Type.BOOLEAN, Schema.Type.NULL)) {
      Schema schema = Schema.create(type);
      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
      assertNull(schema.getDoc());
    }
  }

  @Test
  public void createRejectsNonPrimitiveTypes() {
    for (Schema.Type type : Arrays.asList(Schema.Type.RECORD, Schema.Type.ENUM, Schema.Type.ARRAY, Schema.Type.MAP,
        Schema.Type.UNION, Schema.Type.FIXED)) {
      AvroRuntimeException ex = expectException(AvroRuntimeException.class, () -> Schema.create(type));
      assertTrue(ex.getMessage().contains("Can't create"));
    }
  }

  @Test
  public void primitiveAccessorsRejectIncompatibleSchemaKinds() {
    Schema string = primitive(Schema.Type.STRING);

    expectException(AvroRuntimeException.class, () -> string.getField("x"));
    expectException(AvroRuntimeException.class, string::getFields);
    expectException(AvroRuntimeException.class, string::hasFields);
    expectException(AvroRuntimeException.class, () -> string.setFields(Collections.<Schema.Field>emptyList()));
    expectException(AvroRuntimeException.class, string::getEnumSymbols);
    expectException(AvroRuntimeException.class, string::getEnumDefault);
    expectException(AvroRuntimeException.class, () -> string.getEnumOrdinal("A"));
    expectException(AvroRuntimeException.class, () -> string.hasEnumSymbol("A"));
    expectException(AvroRuntimeException.class, string::getNamespace);
    expectException(AvroRuntimeException.class, () -> string.addAlias("Alias"));
    expectException(AvroRuntimeException.class, () -> string.addAlias("Alias", "space"));
    expectException(AvroRuntimeException.class, string::getAliases);
    expectException(AvroRuntimeException.class, string::isError);
    expectException(AvroRuntimeException.class, string::getElementType);
    expectException(AvroRuntimeException.class, string::getValueType);
    expectException(AvroRuntimeException.class, string::getTypes);
    expectException(AvroRuntimeException.class, () -> string.getIndexNamed("anything"));
    expectException(AvroRuntimeException.class, string::getFixedSize);
  }

  @Test
  public void addPropStoresStringAndObjectValuesAndInvalidatesEqualityHashCache() {
    Schema left = primitive(Schema.Type.STRING);
    Schema right = primitive(Schema.Type.STRING);
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    left.addProp("stringProp", "value");
    left.addProp("booleanProp", Boolean.TRUE);

    assertEquals("value", left.getProp("stringProp"));
    assertEquals(Boolean.TRUE, left.getObjectProp("booleanProp"));
    assertNotEquals(left, right);

    right.addProp("stringProp", "value");
    right.addProp("booleanProp", Boolean.TRUE);
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
  }

  @Test
  public void addPropRejectsReservedAndDuplicateProperties() {
    Schema schema = primitive(Schema.Type.STRING);

    expectException(AvroRuntimeException.class, () -> schema.addProp("type", "notAllowed"));

    schema.addProp("custom", "first");
    expectException(AvroRuntimeException.class, () -> schema.addProp("custom", "second"));
  }

  @Test
  public void logicalTypeIsPopulatedWhenSchemaIsParsedWithValidLogicalType() {
    Schema date = new Schema.Parser().parse("{\"type\":\"int\",\"logicalType\":\"date\"}");

    assertNotNull(date.getLogicalType());
    assertEquals("date", date.getLogicalType().getName());
  }

  @Test
  public void recordWithoutFieldsReportsUnsetStateAndThrowsForFieldAccess() {
    Schema record = Schema.createRecord("Pending", "doc", "org.example", false);

    assertFalse(record.hasFields());
    expectException(AvroRuntimeException.class, record::getFields);
    expectException(AvroRuntimeException.class, () -> record.getField("id"));
  }

  @Test
  public void recordShouldExposeFieldsPositionsLookupMetadataAndErrorFlag() {
    Schema.Field id = new Schema.Field("id", primitive(Schema.Type.INT), "identifier", 0);
    Schema.Field name = new Schema.Field("name", primitive(Schema.Type.STRING), "person name", "anonymous",
        Schema.Field.Order.DESCENDING);
    name.addAlias("legacy_name");

    Schema record = Schema.createRecord("Person", "A person record", "org.example", true, Arrays.asList(id, name));

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("Person", record.getName());
    assertEquals("org.example", record.getNamespace());
    assertEquals("org.example.Person", record.getFullName());
    assertEquals("A person record", record.getDoc());
    assertTrue(record.isError());
    assertTrue(record.hasFields());
    assertEquals(2, record.getFields().size());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertEquals(Schema.Field.Order.DESCENDING, name.order());
    assertTrue(name.aliases().contains("legacy_name"));
  }

  @Test
  public void deprecatedAnonymousRecordFactorySetsFields() {
    Schema.Field value = field("value", primitive(Schema.Type.LONG));

    Schema anonymous = Schema.createRecord(Collections.singletonList(value));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertNull(anonymous.getName());
    assertTrue(anonymous.hasFields());
    assertSame(value, anonymous.getField("value"));
    assertTrue(anonymous.toString().contains("\"fields\""));
  }

  @Test
  public void setFieldsCanBeCalledOnlyOnce() {
    Schema record = Schema.createRecord("Once", null, "org.example", false);
    record.setFields(Collections.singletonList(field("id", primitive(Schema.Type.INT))));

    AvroRuntimeException ex = expectException(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(field("name", primitive(Schema.Type.STRING)))));
    assertTrue(ex.getMessage().contains("already set"));
  }

  @Test
  public void setFieldsRejectsDuplicateFieldNames() {
    Schema record = Schema.createRecord("DuplicateFields", null, "org.example", false);

    AvroRuntimeException ex = expectException(AvroRuntimeException.class, () -> record.setFields(Arrays.asList(
        new Schema.Field("same", primitive(Schema.Type.INT)), new Schema.Field("same", primitive(Schema.Type.LONG)))));
    assertTrue(ex.getMessage().contains("Duplicate field same"));
  }

  @Test
  public void setFieldsRejectsFieldAlreadyAssignedToAnotherRecord() {
    Schema.Field reused = field("id", primitive(Schema.Type.INT));
    Schema first = Schema.createRecord("First", null, "org.example", false);
    first.setFields(Collections.singletonList(reused));
    Schema second = Schema.createRecord("Second", null, "org.example", false);

    AvroRuntimeException ex = expectException(AvroRuntimeException.class,
        () -> second.setFields(Collections.singletonList(reused)));
    assertTrue(ex.getMessage().contains("Field already used"));
  }

  @Test
  public void returnedRecordFieldsListIsLockedAgainstMutation() {
    Schema record = record("LockedFields", "org.example", field("id", primitive(Schema.Type.INT)));

    expectException(IllegalStateException.class, () -> record.getFields().add(field("extra", primitive(Schema.Type.INT))));
    assertEquals(1, record.getFields().size());
  }

  @Test
  public void namedSchemaAliasesAreQualifiedAndRendered() {
    Schema record = Schema.createRecord("Person", "doc", "org.example", false, Collections.<Schema.Field>emptyList());
    record.addAlias("Human");
    record.addAlias("ExternalHuman", "org.external");

    Set<String> aliases = record.getAliases();
    assertTrue(aliases.contains("org.example.Human"));
    assertTrue(aliases.contains("org.external.ExternalHuman"));

    String json = record.toString();
    assertTrue(json.contains("\"aliases\""));
    assertTrue(json.contains("Human"));
    assertTrue(json.contains("ExternalHuman"));
  }

  @Test
  public void enumSchemaExposesSymbolsOrdinalsDefaultAndMembership() {
    Schema schema = Schema.createEnum("Suit", "card suit", "cards", Arrays.asList("SPADES", "HEARTS"), "HEARTS");

    assertEquals(Schema.Type.ENUM, schema.getType());
    assertEquals("Suit", schema.getName());
    assertEquals("cards", schema.getNamespace());
    assertEquals("cards.Suit", schema.getFullName());
    assertEquals("card suit", schema.getDoc());
    assertEquals(Arrays.asList("SPADES", "HEARTS"), schema.getEnumSymbols());
    assertEquals("HEARTS", schema.getEnumDefault());
    assertTrue(schema.hasEnumSymbol("SPADES"));
    assertFalse(schema.hasEnumSymbol("CLUBS"));
    assertEquals(0, schema.getEnumOrdinal("SPADES"));
    assertEquals(1, schema.getEnumOrdinal("HEARTS"));
  }

  @Test
  public void enumSymbolsListIsLockedAgainstMutation() {
    Schema schema = Schema.createEnum("Direction", null, "org.example", Arrays.asList("NORTH", "SOUTH"));

    expectException(IllegalStateException.class, () -> schema.getEnumSymbols().add("EAST"));
  }

  @Test
  public void enumRejectsDuplicateSymbolsInvalidNamesAndInvalidDefault() {
    expectException(SchemaParseException.class,
        () -> Schema.createEnum("Dup", null, "org.example", Arrays.asList("A", "A")));

    expectException(SchemaParseException.class,
        () -> Schema.createEnum("BadSymbol", null, "org.example", Arrays.asList("not-valid")));

    expectException(SchemaParseException.class,
        () -> Schema.createEnum("BadDefault", null, "org.example", Arrays.asList("A", "B"), "C"));
  }

  @Test
  public void enumOrdinalThrowsTracingExceptionForMissingSymbol() {
    Schema schema = Schema.createEnum("Letter", null, "org.example", Arrays.asList("A", "B"));

    TracingAvroTypeException ex = expectException(TracingAvroTypeException.class, () -> schema.getEnumOrdinal("C"));
    assertTrue(ex.getMessage().contains("enum value 'C'"));
  }

  @Test
  public void arraySchemaExposesElementTypeAndJsonRepresentation() {
    Schema element = primitive(Schema.Type.STRING);
    Schema array = Schema.createArray(element);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(element, array.getElementType());
    assertTrue(array.toString().contains("\"array\""));
    assertTrue(array.toString().contains("\"items\""));
  }

  @Test
  public void mapSchemaExposesValueTypeAndJsonRepresentation() {
    Schema value = primitive(Schema.Type.LONG);
    Schema map = Schema.createMap(value);

    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(value, map.getValueType());
    assertTrue(map.toString().contains("\"map\""));
    assertTrue(map.toString().contains("\"values\""));
  }

  @Test
  public void unionSchemaExposesBranchesIndexesNameAndNullableState() {
    Schema nullSchema = primitive(Schema.Type.NULL);
    Schema stringSchema = primitive(Schema.Type.STRING);
    Schema record = record("Payload", "org.example", field("value", primitive(Schema.Type.INT)));

    Schema union = Schema.createUnion(nullSchema, stringSchema, record);

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(Arrays.asList(nullSchema, stringSchema, record), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("org.example.Payload"));
    assertNull(union.getIndexNamed("missing"));
    assertTrue(union.getName().contains("null"));
    assertTrue(union.getName().contains("string"));
    assertTrue(union.getName().contains("Payload"));
  }

  @Test
  public void unionTypesListIsLockedAndUnionRejectsProperties() {
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));

    expectException(IllegalStateException.class, () -> union.getTypes().add(primitive(Schema.Type.INT)));
    expectException(AvroRuntimeException.class, () -> union.addProp("custom", "value"));
  }

  @Test
  public void unionRejectsNestedUnionsDuplicateBranchesAndNamelessBranches() {
    Schema nullableString = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));

    expectException(AvroRuntimeException.class, () -> Schema.createUnion(primitive(Schema.Type.INT), nullableString));

    expectException(AvroRuntimeException.class, () -> Schema.createUnion(primitive(Schema.Type.STRING),
        primitive(Schema.Type.STRING)));

    Schema anonymous = Schema.createRecord(Collections.<Schema.Field>emptyList());
    expectException(AvroRuntimeException.class, () -> Schema.createUnion(anonymous, primitive(Schema.Type.INT)));
  }

  @Test
  public void fixedSchemaExposesNameNamespaceDocSizeAndJsonRepresentation() {
    Schema fixed = Schema.createFixed("Hash", "sixteen bytes", "org.example", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Hash", fixed.getName());
    assertEquals("org.example", fixed.getNamespace());
    assertEquals("org.example.Hash", fixed.getFullName());
    assertEquals("sixteen bytes", fixed.getDoc());
    assertEquals(16, fixed.getFixedSize());
    assertTrue(fixed.toString().contains("\"fixed\""));
    assertTrue(fixed.toString().contains("\"size\":16"));
  }

  @Test
  public void equalsAndHashCodeIncludeNamesFieldsSymbolsNestedTypesAndProps() {
    Schema left = record("Person", "org.example", field("id", primitive(Schema.Type.INT)));
    Schema right = record("Person", "org.example", field("id", primitive(Schema.Type.INT)));
    Schema otherField = record("Person", "org.example", field("name", primitive(Schema.Type.STRING)));

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertNotEquals(left, otherField);

    right.addProp("custom", "value");
    assertNotEquals(left, right);

    assertEquals(Schema.createArray(primitive(Schema.Type.STRING)), Schema.createArray(primitive(Schema.Type.STRING)));
    assertNotEquals(Schema.createArray(primitive(Schema.Type.STRING)), Schema.createArray(primitive(Schema.Type.INT)));
    assertEquals(Schema.createMap(primitive(Schema.Type.LONG)), Schema.createMap(primitive(Schema.Type.LONG)));
    assertEquals(Schema.createFixed("F", null, "org.example", 3), Schema.createFixed("F", null, "org.example", 3));
    assertNotEquals(Schema.createFixed("F", null, "org.example", 3), Schema.createFixed("F", null, "org.example", 4));
  }

  @Test
  public void recursiveRecordsCanBeComparedAndHashedWithoutStackOverflow() {
    Schema first = Schema.createRecord("Node", null, "recursive", false);
    Schema second = Schema.createRecord("Node", null, "recursive", false);

    first.setFields(Collections.singletonList(
        new Schema.Field("next", Schema.createUnion(primitive(Schema.Type.NULL), first), null,
            Schema.Field.NULL_DEFAULT_VALUE)));
    second.setFields(Collections.singletonList(
        new Schema.Field("next", Schema.createUnion(primitive(Schema.Type.NULL), second), null,
            Schema.Field.NULL_DEFAULT_VALUE)));

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void toStringInlineAndPrettyRenderValidJson() {
    Schema schema = record("Pretty", "org.example", field("id", primitive(Schema.Type.INT)));

    String compact = schema.toString();
    String pretty = schema.toString(true);

    assertTrue(compact.startsWith("{"));
    assertFalse(compact.contains(System.lineSeparator() + "  "));
    assertTrue(pretty.contains("\n"));
    assertEquals("Pretty", new Schema.Parser().parse(compact).getName());
    assertEquals("Pretty", new Schema.Parser().parse(pretty).getName());
  }

  @Test
  public void toStringWithReferencedSchemasMayEmitNameReferenceInsteadOfInlining() {
    Schema address = record("Address", "org.example", field("street", primitive(Schema.Type.STRING)));
    Schema user = record("User", "org.example", new Schema.Field("address", address));

    String rendered = user.toString(Collections.singleton(address), false);

    assertTrue(rendered.contains("\"User\""));
    assertTrue(rendered.contains("Address"));
    assertFalse(rendered.contains("\"street\""));
  }

  @Test
  public void fieldConstructorsExposeNameSchemaDocDefaultOrderAndPosition() {
    Schema.Field noDefault = new Schema.Field("id", primitive(Schema.Type.INT));
    Schema.Field withDoc = new Schema.Field("name", primitive(Schema.Type.STRING), "doc");
    Schema.Field withDefault = new Schema.Field("enabled", primitive(Schema.Type.BOOLEAN), "doc", true,
        Schema.Field.Order.IGNORE);

    assertEquals("id", noDefault.name());
    assertEquals(-1, noDefault.pos());
    assertEquals(Schema.Type.INT, noDefault.schema().getType());
    assertNull(noDefault.doc());
    assertFalse(noDefault.hasDefaultValue());

    assertEquals("doc", withDoc.doc());

    assertEquals("enabled", withDefault.name());
    assertTrue(withDefault.hasDefaultValue());
    assertEquals(Boolean.TRUE, withDefault.defaultVal());
    assertEquals(Schema.Field.Order.IGNORE, withDefault.order());
    assertTrue(withDefault.toString().contains("enabled"));
  }

  @Test
  public void fieldNullDefaultSentinelRepresentsExplicitNullDefault() {
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));

    Schema.Field field = new Schema.Field("maybe", union, "nullable field", Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());
    Object defaultValue = field.defaultVal();
    assertNotNull(defaultValue);
    assertEquals("org.apache.avro.JsonProperties$Null", defaultValue.getClass().getName());
  }

  @Test
  public void fieldCopyConstructorCopiesPropsAliasesAndChangesSchema() {
    Schema.Field original = new Schema.Field("value", primitive(Schema.Type.INT), "doc", 1);
    original.addAlias("legacy_value");
    original.addProp("custom", "prop");

    Schema.Field copy = new Schema.Field(original, primitive(Schema.Type.LONG));

    assertEquals(original.name(), copy.name());
    assertEquals(original.doc(), copy.doc());
    assertEquals(((Number) original.defaultVal()).longValue(), ((Number) copy.defaultVal()).longValue());
    assertEquals(Schema.Type.LONG, copy.schema().getType());
    assertEquals("prop", copy.getProp("custom"));
    assertTrue(copy.aliases().contains("legacy_value"));
  }

  @Test
  public void fieldRejectsNullSchemaNullOrderInvalidNameAndInvalidDefault() {
    expectException(NullPointerException.class, () -> new Schema.Field("x", null));

    expectException(NullPointerException.class,
        () -> new Schema.Field("x", primitive(Schema.Type.INT), null, 1, null));

    expectException(SchemaParseException.class, () -> new Schema.Field("bad-name", primitive(Schema.Type.INT)));

    expectException(AvroTypeException.class,
        () -> new Schema.Field("badDefault", primitive(Schema.Type.INT), null, "not an int"));
  }

  @Test
  public void fieldEqualsHashCodeIgnorePositionAndHandleNanDefaults() {
    Schema.Field first = new Schema.Field("amount", primitive(Schema.Type.DOUBLE), null, Double.NaN);
    Schema.Field second = new Schema.Field("amount", primitive(Schema.Type.DOUBLE), null, Double.NaN);

    Schema one = record("One", "org.example", first);
    Schema two = record("Two", "org.example", field("other", primitive(Schema.Type.INT)), second);

    assertEquals(0, first.pos());
    assertEquals(1, second.pos());
    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertNotEquals(one, two);
  }

  @Test
  public void fieldAliasesAreReturnedAsUnmodifiableSet() {
    Schema.Field field = field("name", primitive(Schema.Type.STRING));
    assertTrue(field.aliases().isEmpty());

    field.addAlias("old_name");
    Set<String> aliases = field.aliases();

    assertTrue(aliases.contains("old_name"));
    expectException(UnsupportedOperationException.class, () -> aliases.add("another"));
  }

  @Test
  public void parserParsesStringVarargsFileInputStreamAndComments() throws Exception {
    Schema fromString = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"fields\":[]}");
    Schema fromVarargs = new Schema.Parser().parse("{\"type\":\"", "string", "\"}");

    File schemaFile = temporaryFolder.newFile("schema.avsc");
    try (FileWriter writer = new FileWriter(schemaFile)) {
      writer.write("{\"type\":\"fixed\",\"name\":\"F\",\"size\":4}");
    }
    Schema fromFile = new Schema.Parser().parse(schemaFile);

    NonClosingInputStream input = new NonClosingInputStream("\"long\"".getBytes(StandardCharsets.UTF_8));
    Schema fromStream = new Schema.Parser().parse(input);

    Schema withComment = new Schema.Parser().parse("/* comment */ \"boolean\"");

    assertEquals("R", fromString.getName());
    assertEquals(Schema.Type.STRING, fromVarargs.getType());
    assertEquals(Schema.Type.FIXED, fromFile.getType());
    assertEquals(4, fromFile.getFixedSize());
    assertEquals(Schema.Type.LONG, fromStream.getType());
    assertFalse("Parser.parse(InputStream) must not close the caller-owned stream", input.wasClosed());
    assertEquals(Schema.Type.BOOLEAN, withComment.getType());
  }

  @Test
  public void staticDeprecatedParseMethodsDelegateToParser() throws Exception {
    File schemaFile = temporaryFolder.newFile("static-schema.avsc");
    try (FileWriter writer = new FileWriter(schemaFile)) {
      writer.write("\"int\"");
    }

    assertEquals(Schema.Type.STRING, Schema.parse("\"string\"").getType());
    assertEquals(Schema.Type.INT, Schema.parse(schemaFile).getType());

    ByteArrayInputStream stream = new ByteArrayInputStream("\"long\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.LONG, Schema.parse((InputStream) stream).getType());

    Schema invalidNameAccepted = Schema.parse("{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}", false);
    assertEquals("bad-name", invalidNameAccepted.getName());
  }

  @Test
  public void parserRejectsDanglingContentForStringAndFileButAllowsItForInputStream() throws Exception {
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("\"string\" \"int\""));

    File schemaFile = temporaryFolder.newFile("dangling.avsc");
    try (FileWriter writer = new FileWriter(schemaFile)) {
      writer.write("\"string\" \"int\"");
    }
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse(schemaFile));

    ByteArrayInputStream stream = new ByteArrayInputStream("\"string\" \"int\"".getBytes(StandardCharsets.UTF_8));
    assertEquals(Schema.Type.STRING, new Schema.Parser().parse(stream).getType());
  }

  @Test
  public void parserWrapsMalformedJsonInSchemaParseException() {
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"record\""));
  }

  @Test
  public void parserRejectsSchemasMissingRequiredStructure() {
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\"}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"E\"}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"F\"}"));
    expectException(SchemaParseException.class, () -> new Schema.Parser().parse("true"));
  }

  @Test
  public void parserRejectsInvalidAliases() {
    expectException(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"aliases\":\"Old\",\"fields\":[]}"));

    expectException(SchemaParseException.class,
        () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"aliases\":[1],\"fields\":[]}"));
  }

  @Test
  public void parserAddsNamedTypesAndResolvesLaterReferences() {
    Schema address = record("Address", "org.example", field("street", primitive(Schema.Type.STRING)));

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(Collections.singleton(address)));

    Schema user = parser.parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"org.example\",\"fields\":["
        + "{\"name\":\"address\",\"type\":\"Address\"}]}");

    assertEquals("org.example.Address", user.getField("address").schema().getFullName());
    assertTrue(parser.getTypes().containsKey("org.example.Address"));
    assertTrue(parser.getTypes().containsKey("org.example.User"));
  }

  @Test
  public void parserDeprecatedAddTypesMapAlsoRegistersNamedTypes() {
    Schema status = Schema.createEnum("Status", null, "org.example", Arrays.asList("ON", "OFF"));
    Map<String, Schema> types = new LinkedHashMap<>();
    types.put(status.getFullName(), status);

    Schema.Parser parser = new Schema.Parser();
    parser.addTypes(types);

    Schema parsed = parser.parse("{\"type\":\"record\",\"name\":\"Device\",\"namespace\":\"org.example\",\"fields\":["
        + "{\"name\":\"status\",\"type\":\"Status\"}]}");

    assertEquals("org.example.Status", parsed.getField("status").schema().getFullName());
  }

  @Test
  public void parserValidateDefaultsCanBeDisabledAndReenabled() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"R\",\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"default\":\"not an int\"}]}";

    expectException(AvroTypeException.class, () -> new Schema.Parser().parse(invalidDefault));

    Schema.Parser parser = new Schema.Parser();
    assertTrue(parser.getValidateDefaults());
    assertSame(parser, parser.setValidateDefaults(false));
    assertFalse(parser.getValidateDefaults());

    Schema parsed = parser.parse(invalidDefault);
    assertEquals("id", parsed.getFields().get(0).name());

    parser.setValidateDefaults(true);
    assertTrue(parser.getValidateDefaults());
  }

  @Test
  public void parserParseInternalParsesAndLeavesTypeVisibleInParserContext() {
    Schema.Parser parser = new Schema.Parser();

    Schema parsed = parser.parseInternal("{\"type\":\"record\",\"name\":\"Internal\",\"fields\":[]}");

    assertEquals("Internal", parsed.getName());
    assertTrue(parser.getTypes().containsKey("Internal"));
    assertSame(parsed, parser.getTypes().get("Internal"));
  }

  @Test
  public void parserConstructorWithNullValidatorDisablesNameValidation() {
    Schema parsed = new Schema.Parser((NameValidator) null)
        .parse("{\"type\":\"record\",\"name\":\"not-a-valid-java-name\",\"fields\":[]}");

    assertEquals("not-a-valid-java-name", parsed.getName());
  }

  @Test
  public void staticNameValidatorCanBeChangedAndReadBack() {
    Schema.setNameValidator(NameValidator.NO_VALIDATION);

    assertSame(NameValidator.NO_VALIDATION, Schema.getNameValidator());
    Schema created = Schema.createRecord("bad-name", null, "org.example", false, Collections.<Schema.Field>emptyList());

    assertEquals("bad-name", created.getName());
  }

  @Test
  public void staticValidateDefaultsControlsFieldConstruction() {
    Schema.setValidateDefaults(false);
    assertFalse(Schema.getValidateDefaults());

    Schema.Field invalidButAccepted = new Schema.Field("id", primitive(Schema.Type.INT), null, "not an int");

    assertTrue(invalidButAccepted.hasDefaultValue());

    Schema.setValidateDefaults(true);
    assertTrue(Schema.getValidateDefaults());
    expectException(AvroTypeException.class,
        () -> new Schema.Field("id", primitive(Schema.Type.INT), null, "not an int"));
  }

  @Test
  public void isValidDefaultAcceptsAndRejectsPrimitiveValues() {
    assertTrue(primitive(Schema.Type.STRING).isValidDefault(json("\"abc\"")));
    assertFalse(primitive(Schema.Type.STRING).isValidDefault(json("1")));

    assertTrue(primitive(Schema.Type.BYTES).isValidDefault(json("\"abc\"")));
    assertTrue(primitive(Schema.Type.INT).isValidDefault(json("2147483647")));
    assertFalse(primitive(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertTrue(primitive(Schema.Type.LONG).isValidDefault(json("9223372036854775807")));
    assertFalse(primitive(Schema.Type.LONG).isValidDefault(json("9223372036854775808")));
    assertTrue(primitive(Schema.Type.FLOAT).isValidDefault(json("1.25")));
    assertTrue(primitive(Schema.Type.DOUBLE).isValidDefault(json("1.25")));
    assertTrue(primitive(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertFalse(primitive(Schema.Type.BOOLEAN).isValidDefault(json("\"true\"")));
    assertTrue(primitive(Schema.Type.NULL).isValidDefault(json("null")));
    assertFalse(primitive(Schema.Type.NULL).isValidDefault(json("0")));
    assertFalse(primitive(Schema.Type.STRING).isValidDefault(null));
  }

  @Test
  public void isValidDefaultHandlesEnumFixedArrayMapUnionAndRecord() {
    Schema enumSchema = Schema.createEnum("Color", null, "org.example", Arrays.asList("RED", "BLUE"));
    Schema fixed = Schema.createFixed("Md5", null, "org.example", 16);
    Schema array = Schema.createArray(primitive(Schema.Type.INT));
    Schema map = Schema.createMap(primitive(Schema.Type.BOOLEAN));
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    Schema record = record("Defaults", "org.example", new Schema.Field("id", primitive(Schema.Type.INT), null, 1),
        new Schema.Field("name", primitive(Schema.Type.STRING), null, "unknown"));

    assertTrue(enumSchema.isValidDefault(json("\"RED\"")));
    assertFalse(enumSchema.isValidDefault(json("1")));
    assertTrue(fixed.isValidDefault(json("\"0123456789abcdef\"")));
    assertTrue(array.isValidDefault(json("[1,2,3]")));
    assertFalse(array.isValidDefault(json("[1,\"bad\"]")));
    assertTrue(map.isValidDefault(json("{\"a\":true,\"b\":false}")));
    assertFalse(map.isValidDefault(json("{\"a\":\"true\"}")));
    assertTrue(union.isValidDefault(json("null")));
    assertTrue(union.isValidDefault(json("\"value\"")));
    assertFalse(union.isValidDefault(json("1")));
    assertTrue(record.isValidDefault(json("{}")));
    assertTrue(record.isValidDefault(json("{\"id\":2,\"name\":\"set\"}")));
    assertFalse(record.isValidDefault(json("{\"id\":\"bad\"}")));
  }

  @Test
  public void parseJsonToObjectConvertsJsonToJavaCollections() {
    Object converted = Schema.parseJsonToObject("{\"number\":1,\"text\":\"abc\",\"array\":[true,null]}");

    assertTrue(converted instanceof Map);
    Map<String, Object> map = (Map<String, Object>) converted;
    assertEquals(1, ((Number) map.get("number")).intValue());
    assertEquals("abc", map.get("text"));
    assertTrue(map.get("array") instanceof List);
  }

  @Test
  public void parsedFieldOrderAliasesDocsPropsAndFloatingDefaultsAreExposed() {
    Schema parsed = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"R\",\"fields\":["
        + "{\"name\":\"d\",\"type\":\"double\",\"doc\":\"double doc\",\"default\":\"NaN\",\"order\":\"descending\","
        + "\"aliases\":[\"old_d\"],\"custom\":\"field-prop\"}]}");

    Schema.Field field = parsed.getField("d");

    assertEquals("double doc", field.doc());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertTrue(Double.isNaN((Double) field.defaultVal()));
    assertTrue(field.aliases().contains("old_d"));
    assertEquals("field-prop", field.getProp("custom"));
  }

  @Test
  public void applyAliasesReturnsSameWriterWhenSchemasEqualOrReaderHasNoAliases() {
    Schema writer = record("R", "org.example", field("id", primitive(Schema.Type.INT)));
    Schema same = record("R", "org.example", field("id", primitive(Schema.Type.INT)));
    Schema noAliases = record("Different", "org.example", field("id", primitive(Schema.Type.INT)));

    assertSame(writer, Schema.applyAliases(writer, same));
    assertSame(writer, Schema.applyAliases(writer, noAliases));
  }

  @Test
  public void applyAliasesRenamesRecordsAndFieldsUsingReaderAliases() {
    Schema writer = record("OldPerson", "org.example", field("old_id", primitive(Schema.Type.INT)));

    Schema.Field readerField = field("id", primitive(Schema.Type.INT));
    readerField.addAlias("old_id");
    Schema reader = record("Person", "org.example", readerField);
    reader.addAlias("OldPerson");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("org.example.Person", rewritten.getFullName());
    assertNotNull(rewritten.getField("id"));
    assertNull(rewritten.getField("old_id"));
    assertEquals(Schema.Type.INT, rewritten.getField("id").schema().getType());
  }

  @Test
  public void applyAliasesRenamesEnumFixedArrayMapAndUnionBranches() {
    Schema writerEnum = Schema.createEnum("OldEnum", null, "org.example", Arrays.asList("A", "B"), "A");
    Schema readerEnum = Schema.createEnum("NewEnum", null, "org.example", Arrays.asList("A", "B"), "A");
    readerEnum.addAlias("OldEnum");

    Schema writerFixed = Schema.createFixed("OldFixed", null, "org.example", 4);
    Schema readerFixed = Schema.createFixed("NewFixed", null, "org.example", 4);
    readerFixed.addAlias("OldFixed");

    Schema rewrittenEnum = Schema.applyAliases(writerEnum, readerEnum);
    Schema rewrittenFixed = Schema.applyAliases(writerFixed, readerFixed);
    Schema rewrittenArray = Schema.applyAliases(Schema.createArray(writerEnum), Schema.createArray(readerEnum));
    Schema rewrittenMap = Schema.applyAliases(Schema.createMap(writerFixed), Schema.createMap(readerFixed));
    Schema rewrittenUnion = Schema.applyAliases(Schema.createUnion(writerEnum, writerFixed),
        Schema.createUnion(readerEnum, readerFixed));

    assertEquals("org.example.NewEnum", rewrittenEnum.getFullName());
    assertEquals("org.example.NewFixed", rewrittenFixed.getFullName());
    assertEquals("org.example.NewEnum", rewrittenArray.getElementType().getFullName());
    assertEquals("org.example.NewFixed", rewrittenMap.getValueType().getFullName());
    assertEquals("org.example.NewEnum", rewrittenUnion.getTypes().get(0).getFullName());
    assertEquals("org.example.NewFixed", rewrittenUnion.getTypes().get(1).getFullName());
  }

  @Test
  public void applyAliasesHandlesRecursiveRecordAliases() {
    Schema writer = Schema.createRecord("OldNode", null, "recursive", false);
    writer.setFields(Collections.singletonList(new Schema.Field("old_next",
        Schema.createUnion(primitive(Schema.Type.NULL), writer), null, Schema.Field.NULL_DEFAULT_VALUE)));

    Schema reader = Schema.createRecord("Node", null, "recursive", false);
    reader.addAlias("OldNode");
    Schema.Field next = new Schema.Field("next", Schema.createUnion(primitive(Schema.Type.NULL), reader), null,
        Schema.Field.NULL_DEFAULT_VALUE);
    next.addAlias("old_next");
    reader.setFields(Collections.singletonList(next));

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("recursive.Node", rewritten.getFullName());
    assertNotNull(rewritten.getField("next"));
    Schema recursiveBranch = rewritten.getField("next").schema().getTypes().get(1);
    assertSame(rewritten, recursiveBranch);
  }

  @Test
  public void writeReplaceSupportsJavaSerializationRoundTrip() throws Exception {
    Schema schema = record("SerializableRecord", "org.example", field("id", primitive(Schema.Type.INT)));
    Object replacement = schema.writeReplace();

    assertNotNull(replacement);
    assertTrue(replacement instanceof Serializable);

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
      out.writeObject(schema);
    }

    Object restored;
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
      restored = in.readObject();
    }

    assertEquals(schema, restored);
    assertTrue(restored instanceof Schema);
  }

  @Test
  public void seenPairUsesIdentityEqualityAndIdentityBasedHashCode() {
    Object left = new String("left");
    Object right = new String("right");
    Object equalButDifferentLeft = new String("left");

    Schema.SeenPair pair = new Schema.SeenPair(left, right);
    Schema.SeenPair sameIdentities = new Schema.SeenPair(left, right);
    Schema.SeenPair differentIdentity = new Schema.SeenPair(equalButDifferentLeft, right);

    assertEquals(pair, sameIdentities);
    assertEquals(pair.hashCode(), sameIdentities.hashCode());
    assertNotEquals(pair, differentIdentity);
    assertNotEquals(pair, "not a pair");
  }

  @Test
  public void nameHandlesAnonymousUnqualifiedQualifiedNamespacesAndPrimitiveCollisions() {
    Schema.Name anonymous = new Schema.Name(null, "ignored");
    Schema.Name unqualified = new Schema.Name("User", "org.example");
    Schema.Name qualified = new Schema.Name("org.other.Account", "ignored");
    Schema.Name primitiveLike = new Schema.Name("int", "org.example");

    assertNull(anonymous.toString());
    assertEquals("org.example.User", unqualified.toString());
    assertEquals("org.other.Account", qualified.toString());
    assertEquals("User", unqualified.getQualified("org.example"));
    assertEquals("org.example.User", unqualified.getQualified("another"));
    assertEquals("org.example.int", primitiveLike.getQualified("org.example"));
    assertEquals(new Schema.Name("User", "org.example"), unqualified);
    assertEquals(new Schema.Name("User", "org.example").hashCode(), unqualified.hashCode());
  }

  @Test
  public void nameWritesJsonNameAndNamespaceFields() throws Exception {
    Schema.Name name = new Schema.Name("org.example.User", null);
    StringWriter writer = new StringWriter();

    JsonFactory factory = new JsonFactory();
    try (JsonGenerator generator = factory.createGenerator(writer)) {
      generator.writeStartObject();
      name.writeName("another.namespace", generator);
      generator.writeEndObject();
    }

    String json = writer.toString();
    assertTrue(json.contains("\"name\":\"User\""));
    assertTrue(json.contains("\"namespace\":\"org.example\""));
  }

  @Test
  public void namesRegistryHandlesPrimitiveLookupNamespaceLookupDuplicateDetectionAndSpaceMutation() {
    Schema.Names names = new Schema.Names("org.example");
    assertEquals("org.example", names.space());
    names.space("org.changed");
    assertEquals("org.changed", names.space());

    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema first = record("Registered", "org.changed", field("id", primitive(Schema.Type.INT)));
    names.add(first);

    assertTrue(names.contains(first));
    assertSame(first, names.get("Registered"));
    assertSame(first, names.get("org.changed.Registered"));

    Schema same = record("Registered", "org.changed", field("id", primitive(Schema.Type.INT)));
    assertSame(same, names.put(new Schema.Name("Registered", "org.changed"), same));

    Schema different = record("Registered", "org.changed", field("name", primitive(Schema.Type.STRING)));
    expectException(SchemaParseException.class, () -> names.add(different));
  }

  @Test
  public void lockableArrayListLocksAllMutatingOperations() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>("a", "b");
    assertEquals(Arrays.asList("a", "b"), list);
    assertSame(list, list.lock());
    assertSame(list, list.lock());

    expectException(IllegalStateException.class, () -> list.add("c"));
    expectException(IllegalStateException.class, () -> list.remove("a"));
    expectException(IllegalStateException.class, () -> list.remove(0));
    expectException(IllegalStateException.class, () -> list.addAll(Collections.singletonList("c")));
    expectException(IllegalStateException.class, () -> list.addAll(0, Collections.singletonList("c")));
    expectException(IllegalStateException.class, () -> list.removeAll(Collections.singleton("a")));
    expectException(IllegalStateException.class, () -> list.retainAll(Collections.singleton("a")));
    expectException(IllegalStateException.class, list::clear);
  }

  @Test
  public void lockableArrayListConstructorsCopyInputsBeforeLocking() {
    List<String> input = new ArrayList<>(Arrays.asList("x", "y"));
    Schema.LockableArrayList<String> fromList = new Schema.LockableArrayList<>(input);
    Schema.LockableArrayList<String> fromSize = new Schema.LockableArrayList<>(2);
    Schema.LockableArrayList<String> empty = new Schema.LockableArrayList<>();

    input.add("z");
    fromSize.add("a");
    empty.add("empty");

    assertEquals(Arrays.asList("x", "y"), fromList);
    assertEquals(Collections.singletonList("a"), fromSize);
    assertEquals(Collections.singletonList("empty"), empty);
  }

  private static final class NonClosingInputStream extends ByteArrayInputStream implements Closeable {
    private boolean closed;

    private NonClosingInputStream(byte[] bytes) {
      super(bytes);
    }

    @Override
    public void close() {
      closed = true;
    }

    private boolean wasClosed() {
      return closed;
    }
  }
}
