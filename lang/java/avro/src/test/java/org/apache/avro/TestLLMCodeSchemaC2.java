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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.avro.path.TracingAvroTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Broad behavioral tests for Schema_C2.java, which keeps the production type name
 * org.apache.avro.Schema so it can replace Schema.java inside the Avro module.
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class TestLLMCodeSchemaC2 {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private static Schema primitive(Schema.Type type) {
    return Schema.create(type);
  }

  private static Schema.Field field(String name, Schema schema) {
    return new Schema.Field(name, schema, "doc for " + name, null);
  }

  private static Schema record(String name, String namespace, Schema.Field... fields) {
    Schema schema = Schema.createRecord(name, "doc for " + name, namespace, false);
    schema.setFields(Arrays.asList(fields));
    return schema;
  }

  private static JsonNode json(String text) {
    return Schema.parseJson(text);
  }

  @Test
  public void primitiveTypeNamesAreLowerCaseAndPrimitiveSchemasExposeTheirType() {
    for (Schema.Type type : Arrays.asList(Schema.Type.STRING, Schema.Type.BYTES, Schema.Type.INT, Schema.Type.LONG,
        Schema.Type.FLOAT, Schema.Type.DOUBLE, Schema.Type.BOOLEAN, Schema.Type.NULL)) {
      Schema schema = Schema.create(type);
      assertSame(type, schema.getType());
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), schema.getName());
      assertEquals(schema.getName(), schema.getFullName());
      assertNull(schema.getDoc());
      assertFalse(schema.isUnion());
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void primitiveFactoryRejectsComplexTypes() {
    Schema.create(Schema.Type.RECORD);
  }

  @Test
  public void primitiveUnsupportedAccessorsReportRuntimeErrors() {
    Schema string = primitive(Schema.Type.STRING);
    assertUnsupported(() -> string.getField("x"));
    assertUnsupported(string::getFields);
    assertUnsupported(string::hasFields);
    assertUnsupported(() -> string.setFields(Collections.<Schema.Field>emptyList()));
    assertUnsupported(string::getEnumSymbols);
    assertUnsupported(string::getEnumDefault);
    assertUnsupported(() -> string.getEnumOrdinal("A"));
    assertUnsupported(() -> string.hasEnumSymbol("A"));
    assertUnsupported(string::getNamespace);
    assertUnsupported(() -> string.addAlias("Alias"));
    assertUnsupported(() -> string.addAlias("Alias", "ns"));
    assertUnsupported(string::getAliases);
    assertUnsupported(string::isError);
    assertUnsupported(string::getElementType);
    assertUnsupported(string::getValueType);
    assertUnsupported(string::getTypes);
    assertUnsupported(() -> string.getIndexNamed("int"));
    assertUnsupported(string::getFixedSize);
  }

  @Test
  public void recordCreatedWithoutFieldsTracksUnsetThenSetFieldsExactlyOnce() {
    Schema record = Schema.createRecord("Person", "record doc", "example.avro", false);
    assertSame(Schema.Type.RECORD, record.getType());
    assertEquals("Person", record.getName());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.Person", record.getFullName());
    assertEquals("record doc", record.getDoc());
    assertFalse(record.isError());
    assertFalse(record.hasFields());

    Schema.Field id = field("id", primitive(Schema.Type.INT));
    Schema.Field name = field("name", primitive(Schema.Type.STRING));
    record.setFields(Arrays.asList(id, name));

    assertTrue(record.hasFields());
    assertEquals(Arrays.asList(id, name), record.getFields());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertUnmodifiable(record.getFields());
    assertUnsupported(() -> record.setFields(Collections.<Schema.Field>emptyList()));
  }

  @Test
  public void recordCreatedWithFieldsImmediatelyExposesFieldMapAndPositions() {
    Schema.Field first = field("first", primitive(Schema.Type.STRING));
    Schema.Field second = field("second", primitive(Schema.Type.LONG));
    Schema record = Schema.createRecord("AlreadySet", null, "ns", true, Arrays.asList(first, second));

    assertTrue(record.isError());
    assertTrue(record.hasFields());
    assertSame(first, record.getField("first"));
    assertSame(second, record.getField("second"));
    assertEquals(0, first.pos());
    assertEquals(1, second.pos());
  }

  @Test
  public void anonymousRecordFactorySetsProvidedFields() {
    Schema.Field only = field("only", primitive(Schema.Type.BOOLEAN));
    Schema anonymous = Schema.createRecord(Collections.singletonList(only));

    assertSame(Schema.Type.RECORD, anonymous.getType());
    assertNull(anonymous.getName());
    assertEquals(Collections.singletonList(only), anonymous.getFields());
  }

  @Test(expected = AvroRuntimeException.class)
  public void recordGetFieldBeforeFieldsAreSetThrows() {
    Schema.createRecord("NoFieldsYet", null, null, false).getField("x");
  }

  @Test(expected = AvroRuntimeException.class)
  public void recordGetFieldsBeforeFieldsAreSetThrows() {
    Schema.createRecord("NoFieldsYet", null, null, false).getFields();
  }

  @Test(expected = AvroRuntimeException.class)
  public void recordRejectsDuplicateFieldNames() {
    Schema record = Schema.createRecord("DuplicateFields", null, null, false);
    record.setFields(Arrays.asList(field("dup", primitive(Schema.Type.INT)), field("dup", primitive(Schema.Type.LONG))));
  }

  @Test(expected = AvroRuntimeException.class)
  public void recordRejectsFieldInstanceAlreadyUsedByAnotherRecord() {
    Schema.Field reused = field("id", primitive(Schema.Type.INT));
    record("FirstRecord", "reuse", reused);
    record("SecondRecord", "reuse", reused);
  }

  @Test
  public void namedSchemasExposeAliasesWithNamespaceRules() {
    Schema record = record("User", "example.model", field("id", primitive(Schema.Type.INT)));
    record.addAlias("LegacyUser");
    record.addAlias("ExternalUser", "external.model");

    Set<String> aliases = record.getAliases();
    assertEquals(new LinkedHashSet<>(Arrays.asList("example.model.LegacyUser", "external.model.ExternalUser")), aliases);
  }

  @Test
  public void enumSchemaExposesSymbolsDefaultOrdinalsAndMembership() {
    Schema enumSchema = Schema.createEnum("Suit", "doc", "cards", Arrays.asList("SPADES", "HEARTS", "DIAMONDS"),
        "HEARTS");

    assertSame(Schema.Type.ENUM, enumSchema.getType());
    assertEquals("HEARTS", enumSchema.getEnumDefault());
    assertTrue(enumSchema.hasEnumSymbol("SPADES"));
    assertFalse(enumSchema.hasEnumSymbol("CLUBS"));
    assertEquals(0, enumSchema.getEnumOrdinal("SPADES"));
    assertEquals(1, enumSchema.getEnumOrdinal("HEARTS"));
    assertUnmodifiableByLock(enumSchema.getEnumSymbols());
  }

  @Test(expected = SchemaParseException.class)
  public void enumRejectsDuplicateSymbols() {
    Schema.createEnum("BadEnum", null, null, Arrays.asList("A", "A"));
  }

  @Test(expected = SchemaParseException.class)
  public void enumRejectsDefaultOutsideSymbols() {
    Schema.createEnum("BadDefault", null, null, Arrays.asList("A", "B"), "C");
  }

  @Test(expected = TracingAvroTypeException.class)
  public void enumOrdinalRejectsUnknownSymbol() {
    Schema.createEnum("SmallEnum", null, null, Arrays.asList("A")).getEnumOrdinal("B");
  }

  @Test
  public void arrayMapUnionAndFixedSchemasExposeTheirSpecializedAccessors() {
    Schema intSchema = primitive(Schema.Type.INT);
    Schema array = Schema.createArray(intSchema);
    Schema map = Schema.createMap(primitive(Schema.Type.STRING));
    Schema fixed = Schema.createFixed("Hash", "fixed doc", "crypto", 16);
    Schema union = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING), fixed);

    assertSame(intSchema, array.getElementType());
    assertEquals(Schema.Type.ARRAY, array.getType());
    assertEquals(Schema.Type.STRING, map.getValueType().getType());
    assertEquals(16, fixed.getFixedSize());
    assertEquals("crypto.Hash", fixed.getFullName());
    assertTrue(union.isUnion());
    assertEquals(Arrays.asList(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING), fixed), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("crypto.Hash"));
    assertNull(union.getIndexNamed("missing"));
    assertTrue(union.getName().startsWith("union["));
  }

  @Test(expected = AvroRuntimeException.class)
  public void unionRejectsNestedUnion() {
    Schema nested = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    Schema.createUnion(nested, primitive(Schema.Type.INT));
  }

  @Test(expected = AvroRuntimeException.class)
  public void unionRejectsDuplicateBranchNames() {
    Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.INT));
  }

  @Test(expected = AvroRuntimeException.class)
  public void unionDoesNotAcceptCustomProperties() {
    Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING)).addProp("x", "y");
  }

  @Test
  public void nullableDetectionWorksForNullPrimitiveAndUnions() {
    assertTrue(primitive(Schema.Type.NULL).isNullable());
    assertFalse(primitive(Schema.Type.INT).isNullable());
    assertTrue(Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING)).isNullable());
    assertFalse(Schema.createUnion(primitive(Schema.Type.INT), primitive(Schema.Type.STRING)).isNullable());
  }

  @Test
  public void fieldConstructorsExposeNameSchemaDocDefaultOrderAndAliases() {
    Schema.Field plain = new Schema.Field("age", primitive(Schema.Type.INT));
    assertEquals("age", plain.name());
    assertSame(Schema.Type.INT, plain.schema().getType());
    assertNull(plain.doc());
    assertFalse(plain.hasDefaultValue());
    assertEquals(Schema.Field.Order.ASCENDING, plain.order());

    Schema.Field withDefault = new Schema.Field("name", primitive(Schema.Type.STRING), "doc", "unknown",
        Schema.Field.Order.DESCENDING);
    withDefault.addAlias("fullName");
    assertEquals("doc", withDefault.doc());
    assertTrue(withDefault.hasDefaultValue());
    assertEquals("unknown", withDefault.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, withDefault.order());
    assertEquals(Collections.singleton("fullName"), withDefault.aliases());
    assertUnmodifiableSet(withDefault.aliases());
    assertTrue(withDefault.toString().contains("name type:STRING pos:-1"));
  }

  @Test
  public void fieldSupportsExplicitNullDefaultSentinel() {
    Schema nullableString = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    Schema.Field field = new Schema.Field("optional", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());
    assertNotNull(field.defaultVal());
    assertEquals("org.apache.avro.JsonProperties$Null", field.defaultVal().getClass().getName());
  }

  @Test(expected = NullPointerException.class)
  public void fieldRejectsNullSchema() {
    new Schema.Field("broken", null);
  }

  @Test(expected = NullPointerException.class)
  public void fieldRejectsNullOrder() {
    new Schema.Field("broken", primitive(Schema.Type.INT), null, 1, null);
  }

  @Test(expected = AvroTypeException.class)
  public void fieldRejectsInvalidDefaultWhenValidationIsEnabled() {
    new Schema.Field("bad", primitive(Schema.Type.INT), null, "not an int");
  }

  @Test
  public void fieldCopyConstructorChangesSchemaAndPreservesMetadata() {
    Schema.Field original = new Schema.Field("value", primitive(Schema.Type.STRING), "doc", "x", Schema.Field.Order.IGNORE);
    original.addAlias("oldValue");
    original.addProp("custom", "prop");

    Schema.Field copy = new Schema.Field(original, primitive(Schema.Type.BYTES));

    assertEquals(original.name(), copy.name());
    assertEquals(original.doc(), copy.doc());
    assertEquals("x", original.defaultVal());
    assertArrayEquals("x".getBytes(StandardCharsets.UTF_8), (byte[]) copy.defaultVal());
    assertEquals(original.order(), copy.order());
    assertEquals(original.aliases(), copy.aliases());
    assertEquals("prop", copy.getProp("custom"));
    assertSame(Schema.Type.BYTES, copy.schema().getType());
  }

  @Test
  public void fieldEqualityIncludesDefaultOrderAndPropertiesButNotAliases() {
    Schema.Field left = new Schema.Field("f", primitive(Schema.Type.DOUBLE), null, Double.NaN,
        Schema.Field.Order.ASCENDING);
    Schema.Field right = new Schema.Field("f", primitive(Schema.Type.DOUBLE), null, Double.NaN,
        Schema.Field.Order.ASCENDING);
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    right.addAlias("aliasOnly");
    assertEquals("Aliases are intentionally not part of Field.equals", left, right);

    right.addProp("p", "v");
    assertNotEquals(left, right);
  }

  @Test
  public void schemaEqualityAndHashCodeConsiderTypeNameFieldsAndProperties() {
    Schema one = record("EqualityRecord", "eq", field("id", primitive(Schema.Type.INT)));
    Schema same = record("EqualityRecord", "eq", field("id", primitive(Schema.Type.INT)));
    Schema differentField = record("EqualityRecord", "eq", field("id", primitive(Schema.Type.LONG)));

    assertEquals(one, same);
    assertEquals(one.hashCode(), same.hashCode());
    assertNotEquals(one, differentField);

    int oldHash = one.hashCode();
    one.addProp("custom", "value");
    assertNotEquals(oldHash, one.hashCode());
    assertNotEquals(one, same);
    same.addProp("custom", "value");
    assertEquals(one, same);
  }

  @Test(expected = AvroRuntimeException.class)
  public void reservedPropertiesCannotBeAddedToSchema() {
    primitive(Schema.Type.INT).addProp("type", "long");
  }

  @Test
  public void recursiveRecordEqualityAndHashCodeDoNotOverflow() {
    Schema node = Schema.createRecord("Node", null, "graph", false);
    Schema nullableNode = Schema.createUnion(primitive(Schema.Type.NULL), node);
    node.setFields(Arrays.asList(field("value", primitive(Schema.Type.INT)), field("next", nullableNode)));

    Schema otherNode = Schema.createRecord("Node", null, "graph", false);
    Schema otherNullableNode = Schema.createUnion(primitive(Schema.Type.NULL), otherNode);
    otherNode.setFields(Arrays.asList(field("value", primitive(Schema.Type.INT)), field("next", otherNullableNode)));

    assertEquals(node, otherNode);
    assertEquals(node.hashCode(), otherNode.hashCode());
    assertTrue(node.toString().contains("Node"));
  }

  @Test
  public void jsonRenderingSupportsCompactPrettyAndReferencedNamedSchemas() {
    Schema child = record("Child", "render", field("id", primitive(Schema.Type.INT)));
    Schema parent = record("Parent", "render", field("child", child));

    String compact = parent.toString();
    String pretty = parent.toString(true);
    String referenced = parent.toString(Collections.singleton(child), false);

    assertTrue(compact.contains("\"type\":\"record\""));
    assertTrue(compact.contains("\"name\":\"Parent\""));
    assertTrue(pretty.contains(System.lineSeparator()) || pretty.contains("\n"));
    assertTrue("Known referenced schemas should be written by name instead of being inlined",
        referenced.contains("\"type\":\"Child\"") || referenced.contains("\"type\":\"render.Child\""));
  }

  @Test
  public void schemaWriteReplaceAndJavaSerializationRoundTripThroughSchemaText() throws Exception {
    Schema schema = record("SerializableRecord", "ser", field("id", primitive(Schema.Type.INT)));
    Object replacement = schema.writeReplace();
    assertNotNull(replacement);
    assertTrue(replacement instanceof Serializable);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream objectOut = new ObjectOutputStream(out);
    objectOut.writeObject(schema);
    objectOut.close();

    ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
    Object restored = objectIn.readObject();
    assertEquals(schema, restored);
    assertTrue(restored instanceof Schema);
  }

  @Test
  public void parserParsesStringsFilesStreamsAndMultipleStringFragments() throws Exception {
    String schemaText = "{\"type\":\"record\",\"name\":\"Parsed\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}";

    Schema fromString = new Schema.Parser().parse(schemaText);
    assertEquals("Parsed", fromString.getName());

    Schema fromFragments = new Schema.Parser().parse("{\"type\":\"", "int", "\"}");
    assertSame(Schema.Type.INT, fromFragments.getType());

    File file = temporaryFolder.newFile("schema.avsc");
    java.nio.file.Files.write(file.toPath(), schemaText.getBytes(StandardCharsets.UTF_8));
    assertEquals(fromString, new Schema.Parser().parse(file));
    assertEquals(fromString, Schema.parse(file));

    CloseTrackingInputStream stream = new CloseTrackingInputStream(schemaText.getBytes(StandardCharsets.UTF_8));
    assertEquals(fromString, new Schema.Parser().parse(stream));
    assertFalse("Parser.parse(InputStream) must leave the source stream open", stream.closed);
    stream.close();
  }

  @Test
  public void parserAcceptsJsonCommentsAndRejectsDanglingContentForStringInput() {
    assertSame(Schema.Type.INT, new Schema.Parser().parse("/* comment */ \"int\"").getType());

    try {
      new Schema.Parser().parse("\"int\" \"string\"");
      fail("Expected dangling content to be rejected");
    } catch (SchemaParseException expected) {
      assertTrue(expected.getMessage().contains("dangling content"));
    }
  }

  @Test
  public void parserTracksKnownNamedTypesAndCanResolveLaterReferences() {
    Schema existing = record("Existing", "known", field("id", primitive(Schema.Type.INT)));
    Map<String, Schema> map = new LinkedHashMap<>();
    map.put("ignored-key-because-values-are-used", existing);

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(map));
    assertSame(parser, parser.addTypes(Collections.singleton(existing)));
    assertTrue(parser.getTypes().containsKey("known.Existing"));

    Schema holder = parser.parse(
        "{\"type\":\"record\",\"name\":\"Holder\",\"namespace\":\"known\",\"fields\":[{\"name\":\"nested\",\"type\":\"Existing\"}]}");
    assertEquals(existing, holder.getField("nested").schema());
  }

  @Test
  public void parserDefaultValidationFlagIsExposedAndHonored() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\",\"fields\":[{\"name\":\"i\",\"type\":\"int\",\"default\":\"bad\"}]}";

    Schema.Parser validating = new Schema.Parser();
    assertTrue(validating.getValidateDefaults());
    try {
      validating.parse(invalidDefault);
      fail("Expected invalid field default to be rejected");
    } catch (AvroTypeException expected) {
      assertTrue(expected.getMessage().contains("Invalid default for field i"));
    }

    Schema.Parser nonValidating = new Schema.Parser().setValidateDefaults(false);
    assertFalse(nonValidating.getValidateDefaults());
    Schema parsed = nonValidating.parse(invalidDefault);
    assertEquals("BadDefault", parsed.getName());
    assertTrue(parsed.getField("i").hasDefaultValue());
  }

  @Test
  public void parserCanDisableNameValidationAndParseInternalWithoutResolutionCommit() {
    Schema invalidByDefault = new Schema.Parser(NameValidator.NO_VALIDATION)
        .parse("{\"type\":\"record\",\"name\":\"1NotNormallyValid\",\"fields\":[]}");
    assertEquals("1NotNormallyValid", invalidByDefault.getName());

    Schema internal = new Schema.Parser().parseInternal(
        "{\"type\":\"record\",\"name\":\"InternalOnly\",\"fields\":[{\"name\":\"id\",\"type\":\"long\"}]}");
    assertEquals("InternalOnly", internal.getName());
    assertSame(Schema.Type.LONG, internal.getField("id").schema().getType());
  }

  @Test
  public void deprecatedStaticParseMethodsDelegateToParser() throws Exception {
    String schemaText = "{\"type\":\"record\",\"name\":\"StaticParsed\",\"fields\":[]}";
    assertEquals("StaticParsed", Schema.parse(schemaText).getName());
    assertEquals("1InvalidWhenValidated", Schema.parse(
        "{\"type\":\"record\",\"name\":\"1InvalidWhenValidated\",\"fields\":[]}", false).getName());

    ByteArrayInputStream in = new ByteArrayInputStream(schemaText.getBytes(StandardCharsets.UTF_8));
    assertEquals("StaticParsed", Schema.parse(in).getName());
  }

  @Test
  public void globalValidationFlagsCanBeTemporarilyChangedAndRestored() {
    boolean previousValidateDefaults = Schema.getValidateDefaults();
    NameValidator previousValidator = Schema.getNameValidator();
    try {
      Schema.setValidateDefaults(false);
      Schema.Field invalidDefault = new Schema.Field("i", primitive(Schema.Type.INT), null, "bad");
      assertTrue(invalidDefault.hasDefaultValue());

      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      Schema invalidName = Schema.createRecord("1Invalid", null, null, false);
      invalidName.setFields(Collections.<Schema.Field>emptyList());
      assertEquals("1Invalid", invalidName.getName());
    } finally {
      Schema.setValidateDefaults(previousValidateDefaults);
      Schema.setNameValidator(previousValidator);
    }
  }

  @Test
  public void validDefaultValidationCoversPrimitiveAndContainerSchemas() {
    assertTrue(primitive(Schema.Type.STRING).isValidDefault(json("\"x\"")));
    assertTrue(primitive(Schema.Type.BYTES).isValidDefault(json("\"bytes\"")));
    assertTrue(primitive(Schema.Type.INT).isValidDefault(json("1")));
    assertFalse(primitive(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertTrue(primitive(Schema.Type.LONG).isValidDefault(json("2147483648")));
    assertTrue(primitive(Schema.Type.FLOAT).isValidDefault(json("1.5")));
    assertTrue(primitive(Schema.Type.DOUBLE).isValidDefault(json("1.5")));
    assertTrue(primitive(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertTrue(primitive(Schema.Type.NULL).isValidDefault(json("null")));

    Schema enumSchema = Schema.createEnum("Letters", null, null, Arrays.asList("A", "B"));
    assertTrue(enumSchema.isValidDefault(json("\"A\"")));
    Schema fixed = Schema.createFixed("F", null, null, 2);
    assertTrue(fixed.isValidDefault(json("\"ab\"")));

    Schema array = Schema.createArray(primitive(Schema.Type.INT));
    assertTrue(array.isValidDefault(json("[1,2,3]")));
    assertFalse(array.isValidDefault(json("[1,\"bad\"]")));

    Schema map = Schema.createMap(primitive(Schema.Type.BOOLEAN));
    assertTrue(map.isValidDefault(json("{\"a\":true,\"b\":false}")));
    assertFalse(map.isValidDefault(json("{\"a\":\"bad\"}")));
  }

  @Test
  public void validDefaultValidationCoversUnionAndRecordSchemas() {
    Schema nullableString = Schema.createUnion(primitive(Schema.Type.NULL), primitive(Schema.Type.STRING));
    assertTrue(nullableString.isValidDefault(json("null")));
    assertTrue(nullableString.isValidDefault(json("\"x\"")));
    assertFalse(nullableString.isValidDefault(json("1")));

    Schema record = Schema.createRecord("DefaultedRecord", null, null, false);
    record.setFields(Arrays.asList(new Schema.Field("required", primitive(Schema.Type.INT), null, null),
        new Schema.Field("withDefault", primitive(Schema.Type.STRING), null, "fallback")));
    assertTrue(record.isValidDefault(json("{\"required\":1}")));
    assertTrue(record.isValidDefault(json("{\"required\":1,\"withDefault\":\"value\"}")));
    assertFalse(record.isValidDefault(json("{\"withDefault\":\"value\"}")));
    assertFalse(record.isValidDefault(json("[]")));
  }

  @Test
  public void parseJsonToObjectConvertsJsonScalarsAndContainers() {
    Object object = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,null,\"x\"]}");
    assertTrue(object instanceof Map);
    Map<String, Object> map = (Map<String, Object>) object;
    assertEquals(1, map.get("a"));
    List<Object> values = (List<Object>) map.get("b");
    assertEquals(Boolean.TRUE, values.get(0));
    assertNotNull(values.get(1));
    assertEquals("org.apache.avro.JsonProperties$Null", values.get(1).getClass().getName());
    assertEquals("x", values.get(2));
  }

  @Test
  public void applyAliasesReturnsSameWriterWhenNoAliasesArePresentOrSchemasAlreadyMatch() {
    Schema writer = record("Writer", "alias.none", field("id", primitive(Schema.Type.INT)));
    Schema same = record("Writer", "alias.none", field("id", primitive(Schema.Type.INT)));
    Schema unrelatedReader = record("Reader", "alias.none", field("id", primitive(Schema.Type.INT)));

    assertSame(writer, Schema.applyAliases(writer, same));
    assertSame(writer, Schema.applyAliases(writer, unrelatedReader));
  }

  @Test
  public void applyAliasesRewritesRecordAndFieldNamesAcrossNestedContainers() {
    Schema oldNested = record("OldNested", "alias.model", field("oldValue", primitive(Schema.Type.STRING)));
    Schema writer = record("OldRecord", "alias.model", new Schema.Field("oldField", Schema.createArray(oldNested)));
    writer.addProp("writerProp", "preserved");

    Schema newNested = record("NewNested", "alias.model", field("newValue", primitive(Schema.Type.STRING)));
    newNested.addAlias("OldNested");
    newNested.getField("newValue").addAlias("oldValue");
    Schema reader = record("NewRecord", "alias.model", new Schema.Field("newField", Schema.createArray(newNested)));
    reader.addAlias("OldRecord");
    reader.getField("newField").addAlias("oldField");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("alias.model.NewRecord", rewritten.getFullName());
    assertEquals("preserved", rewritten.getProp("writerProp"));
    assertNotNull(rewritten.getField("newField"));
    assertNull(rewritten.getField("oldField"));
    Schema rewrittenNested = rewritten.getField("newField").schema().getElementType();
    assertEquals("alias.model.NewNested", rewrittenNested.getFullName());
    assertNotNull(rewrittenNested.getField("newValue"));
    assertNull(rewrittenNested.getField("oldValue"));
  }

  @Test
  public void applyAliasesRewritesEnumFixedMapAndUnionBranches() {
    Schema oldEnum = Schema.createEnum("OldEnum", null, "alias.more", Arrays.asList("A", "B"), "A");
    Schema oldFixed = Schema.createFixed("OldFixed", null, "alias.more", 4);
    Schema writer = Schema.createUnion(Schema.createMap(oldEnum), oldFixed, primitive(Schema.Type.NULL));

    Schema newEnum = Schema.createEnum("NewEnum", null, "alias.more", Arrays.asList("A", "B"), "A");
    newEnum.addAlias("OldEnum");
    Schema newFixed = Schema.createFixed("NewFixed", null, "alias.more", 4);
    newFixed.addAlias("OldFixed");
    Schema reader = Schema.createUnion(Schema.createMap(newEnum), newFixed, primitive(Schema.Type.NULL));

    Schema rewritten = Schema.applyAliases(writer, reader);
    assertEquals("alias.more.NewEnum", rewritten.getTypes().get(0).getValueType().getFullName());
    assertEquals("alias.more.NewFixed", rewritten.getTypes().get(1).getFullName());
    assertSame(Schema.Type.NULL, rewritten.getTypes().get(2).getType());
  }

  @Test
  public void seenPairUsesIdentityNotObjectEquality() {
    String leftOne = new String("same");
    String leftTwo = new String("same");
    Object right = new Object();

    Schema.SeenPair first = new Schema.SeenPair(leftOne, right);
    Schema.SeenPair sameIdentities = new Schema.SeenPair(leftOne, right);
    Schema.SeenPair equalButDifferentLeftIdentity = new Schema.SeenPair(leftTwo, right);

    assertEquals(first, sameIdentities);
    assertEquals(first.hashCode(), sameIdentities.hashCode());
    assertNotEquals(first, equalButDifferentLeftIdentity);
    assertNotEquals(first, "not a pair");
  }

  @Test
  public void namesMapResolvesPrimitivesDefaultNamespaceAndDuplicateDefinitions() {
    Schema.Names names = new Schema.Names("names.ns");
    assertEquals("names.ns", names.space());
    names.space("other.ns");
    assertEquals("other.ns", names.space());
    assertSame(Schema.Type.INT, names.get("int").getType());

    Schema first = record("Known", "other.ns", field("id", primitive(Schema.Type.INT)));
    names.add(first);
    assertTrue(names.contains(first));
    assertSame(first, names.get("Known"));
    assertSame(first, names.put(new Schema.Name("Known", "other.ns"), first));

    try {
      Schema different = record("Known", "other.ns", field("id", primitive(Schema.Type.LONG)));
      names.put(new Schema.Name("Known", "other.ns"), different);
      fail("Expected duplicate, different schema definition to be rejected");
    } catch (SchemaParseException expected) {
      assertTrue(expected.getMessage().contains("Can't redefine"));
    }
  }

  @Test
  public void nameQualificationHandlesNamespacesAndPrimitiveNameCollisions() {
    Schema.Name anonymous = new Schema.Name(null, null);
    assertNull(anonymous.toString());

    Schema.Name qualified = new Schema.Name("a.b.Record", "ignored");
    assertEquals("Record", qualified.getQualified("a.b"));

    Schema.Name sameNamespace = new Schema.Name("User", "a.b");
    assertEquals("User", sameNamespace.getQualified("a.b"));
    assertEquals("a.b.User", sameNamespace.getQualified("other"));

    Schema.Name primitiveCollision = new Schema.Name("int", "a.b");
    assertEquals("a.b.int", primitiveCollision.getQualified("a.b"));
    assertEquals(primitiveCollision, new Schema.Name("a.b.int", null));
    assertEquals(primitiveCollision.hashCode(), new Schema.Name("a.b.int", null).hashCode());
  }

  @Test
  public void lockableArrayListAllowsMutationUntilLockedAndThenRejectsMutators() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>("a", "b");
    assertTrue(list.add("c"));
    assertEquals(Arrays.asList("a", "b", "c"), list);
    List<String> locked = list.lock();
    assertSame(list, locked);
    assertSame(list, list.lock());

    assertIllegalState(() -> list.add("d"));
    assertIllegalState(() -> list.remove("a"));
    assertIllegalState(() -> list.remove(0));
    assertIllegalState(() -> list.addAll(Collections.singleton("x")));
    assertIllegalState(() -> list.addAll(0, Collections.singleton("x")));
    assertIllegalState(() -> list.removeAll(Collections.singleton("a")));
    assertIllegalState(() -> list.retainAll(Collections.singleton("a")));
    assertIllegalState(list::clear);
  }

  @Test
  public void parsedSchemasPreserveLogicalTypesCustomPropertiesAliasesOrdersAndDefaults() {
    Schema schema = new Schema.Parser().parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"WithMetadata\","
        + "\"namespace\":\"meta\","
        + "\"doc\":\"doc text\","
        + "\"aliases\":[\"OldWithMetadata\"],"
        + "\"customRecordProp\":\"recordValue\","
        + "\"fields\":["
        + "  {\"name\":\"amount\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":4,\"scale\":2},\"default\":\"AA==\",\"order\":\"descending\",\"aliases\":[\"oldAmount\"],\"fieldProp\":\"fieldValue\"},"
        + "  {\"name\":\"ratio\",\"type\":\"double\",\"default\":\"1.5\"}"
        + "]}");

    assertEquals("doc text", schema.getDoc());
    assertEquals("recordValue", schema.getProp("customRecordProp"));
    assertEquals(Collections.singleton("meta.OldWithMetadata"), schema.getAliases());

    Schema.Field amount = schema.getField("amount");
    assertEquals(Schema.Field.Order.DESCENDING, amount.order());
    assertEquals(Collections.singleton("oldAmount"), amount.aliases());
    assertEquals("fieldValue", amount.getProp("fieldProp"));
    assertNotNull(amount.schema().getLogicalType());
    assertEquals("decimal", amount.schema().getLogicalType().getName());

    Schema.Field ratio = schema.getField("ratio");
    assertTrue(ratio.hasDefaultValue());
    assertEquals(1.5d, (Double) ratio.defaultVal(), 0.0d);
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsRecordWithoutFields() {
    new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFields\"}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsFieldWithoutType() {
    new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFieldType\",\"fields\":[{\"name\":\"x\"}]}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsEnumWithoutSymbols() {
    new Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"NoSymbols\"}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsArrayWithoutItems() {
    new Schema.Parser().parse("{\"type\":\"array\"}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsMapWithoutValues() {
    new Schema.Parser().parse("{\"type\":\"map\"}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsFixedWithoutIntegerSize() {
    new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"BadFixed\",\"size\":\"4\"}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsAliasesThatAreNotArrays() {
    new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":\"Old\",\"fields\":[]}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsAliasesContainingNonStringValues() {
    new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BadAliases\",\"aliases\":[1],\"fields\":[]}");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsUnsupportedJsonSchemaNode() {
    new Schema.Parser().parse("1");
  }

  @Test(expected = SchemaParseException.class)
  public void parserRejectsNullSchemaNodeThroughPackageHelper() {
    Schema.parse(null, new ParseContext(NameValidator.UTF_VALIDATOR), null);
  }

  private static void assertUnsupported(ThrowingRunnable action) {
    try {
      action.run();
      fail("Expected AvroRuntimeException");
    } catch (AvroRuntimeException expected) {
      assertNotNull(expected.getMessage());
    } catch (Exception unexpected) {
      throw new AssertionError(unexpected);
    }
  }

  private static void assertUnmodifiable(Collection<?> collection) {
    try {
      ((Collection<Object>) collection).add(new Object());
      fail("Expected the collection to reject mutation");
    } catch (UnsupportedOperationException | IllegalStateException expected) {
      // Either a standard unmodifiable list or Schema.LockableArrayList is acceptable here.
    }
  }

  private static void assertUnmodifiableByLock(List<?> list) {
    try {
      ((List<Object>) list).add(new Object());
      fail("Expected locked list to reject mutation");
    } catch (IllegalStateException expected) {
      // Expected for Schema.LockableArrayList after lock().
    }
  }

  private static void assertUnmodifiableSet(Set<?> set) {
    try {
      ((Set<Object>) set).add(new Object());
      fail("Expected set to reject mutation");
    } catch (UnsupportedOperationException expected) {
      // Expected.
    }
  }

  private static void assertIllegalState(ThrowingRunnable action) {
    try {
      action.run();
      fail("Expected IllegalStateException");
    } catch (IllegalStateException expected) {
      // Expected.
    } catch (Exception unexpected) {
      throw new AssertionError(unexpected);
    }
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class CloseTrackingInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseTrackingInputStream(byte[] bytes) {
      super(bytes);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
