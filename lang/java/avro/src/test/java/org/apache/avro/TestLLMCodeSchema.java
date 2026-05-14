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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.avro.path.TracingAvroTypeException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Comprehensive JUnit 4 tests for {@link Schema}.
 *
 * <p>The test class is intentionally placed in package {@code org.apache.avro}
 * so that package-private helpers exposed by Schema.java, such as {@code Name},
 * {@code Names}, {@code parseJson}, {@code parseAliases} and
 * {@code LockableArrayList}, can also be exercised.</p>
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class TestLLMCodeSchema {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @After
  public void restoreThreadLocalSettings() {
    Schema.setValidateDefaults(true);
    Schema.setNameValidator(NameValidator.UTF_VALIDATOR);
  }

  @Test
  public void typeGetNameReturnsLowerCaseNames() {
    for (Schema.Type type : Schema.Type.values()) {
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), type.getName());
    }
  }

  @Test
  public void createPrimitiveSchemasReturnsExpectedTypesAndNames() {
    assertPrimitive(Schema.Type.STRING, "string");
    assertPrimitive(Schema.Type.BYTES, "bytes");
    assertPrimitive(Schema.Type.INT, "int");
    assertPrimitive(Schema.Type.LONG, "long");
    assertPrimitive(Schema.Type.FLOAT, "float");
    assertPrimitive(Schema.Type.DOUBLE, "double");
    assertPrimitive(Schema.Type.BOOLEAN, "boolean");
    assertPrimitive(Schema.Type.NULL, "null");
  }

  @Test
  public void createRejectsNonPrimitiveSchemaTypes() {
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.RECORD));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ENUM));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.ARRAY));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.MAP));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.UNION));
    expectThrows(AvroRuntimeException.class, () -> Schema.create(Schema.Type.FIXED));
  }

  @Test
  public void primitiveSchemaRejectsAccessorsForOtherSchemaKinds() {
    Schema primitive = Schema.create(Schema.Type.STRING);

    expectThrows(AvroRuntimeException.class, () -> primitive.getField("x"));
    expectThrows(AvroRuntimeException.class, primitive::getFields);
    expectThrows(AvroRuntimeException.class, primitive::hasFields);
    expectThrows(AvroRuntimeException.class,
        () -> primitive.setFields(Collections.singletonList(new Schema.Field("x", Schema.create(Schema.Type.INT)))));
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
  public void primitiveToStringAndPropertiesAffectEqualityAndHashCode() {
    Schema left = Schema.create(Schema.Type.STRING);
    Schema right = Schema.create(Schema.Type.STRING);
    assertEquals("\"string\"", left.toString());
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    int cachedHashBeforeMutation = left.hashCode();
    left.addProp("custom", "value");
    assertEquals("value", left.getProp("custom"));
    assertFalse(left.equals(right));

    right.addProp("custom", "value");
    assertEquals(left, right);
    assertEquals(right.hashCode(), left.hashCode());
    assertFalse("hash should be recomputed after addProp", cachedHashBeforeMutation == left.hashCode());
    assertTrue(left.toString().contains("\"custom\":\"value\""));
  }

  @Test
  public void objectPropertyCanBeAddedToPrimitiveSchema() {
    Schema schema = Schema.create(Schema.Type.STRING);
    schema.addProp("metadata", Collections.singletonMap("key", "value"));
    assertTrue(schema.toString().contains("\"metadata\""));
    assertTrue(schema.toString().contains("\"key\":\"value\""));
  }

  @Test
  public void logicalTypeIsReadFromSchemaJson() {
    Schema date = new Schema.Parser().parse("{\"type\":\"int\",\"logicalType\":\"date\"}");
    assertNotNull(date.getLogicalType());
    assertEquals("date", date.getLogicalType().getName());
  }

  @Test
  public void createNamedRecordWithoutFieldsThenSetFieldsOnce() {
    Schema record = Schema.createRecord("User", "user doc", "example.avro", false);
    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("user doc", record.getDoc());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertFalse(record.isError());
    assertFalse(record.hasFields());
    expectThrows(AvroRuntimeException.class, () -> record.getField("id"));
    expectThrows(AvroRuntimeException.class, record::getFields);

    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT), "identifier");
    Schema.Field name = new Schema.Field("name", Schema.create(Schema.Type.STRING), "name doc", "unknown");
    record.setFields(Arrays.asList(id, name));

    assertTrue(record.hasFields());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertEquals(Arrays.asList(id, name), record.getFields());
    assertEquals("unknown", name.defaultVal());
    expectThrows(IllegalStateException.class,
        () -> record.getFields().add(new Schema.Field("extra", Schema.create(Schema.Type.STRING))));
    expectThrows(AvroRuntimeException.class,
        () -> record.setFields(Collections.singletonList(new Schema.Field("again", Schema.create(Schema.Type.INT)))));
  }

  @Test
  public void createNamedRecordWithFieldsAndAnonymousRecordFactoryWork() {
    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT));
    Schema named = Schema.createRecord("WithFields", null, "example", false, Collections.singletonList(id));
    assertEquals("example.WithFields", named.getFullName());
    assertTrue(named.hasFields());
    assertEquals(0, named.getField("id").pos());

    Schema.Field value = new Schema.Field("value", Schema.create(Schema.Type.LONG));
    Schema anonymous = Schema.createRecord(Collections.singletonList(value));
    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertNull(anonymous.getName());
    assertNull(anonymous.getFullName());
    assertSame(value, anonymous.getField("value"));
  }

  @Test
  public void recordSetFieldsRejectsDuplicateNamesAndReusedFieldInstances() {
    Schema duplicateRecord = Schema.createRecord("Duplicate", null, "example", false);
    expectThrows(AvroRuntimeException.class, () -> duplicateRecord.setFields(Arrays.asList(
        new Schema.Field("same", Schema.create(Schema.Type.INT)),
        new Schema.Field("same", Schema.create(Schema.Type.STRING)))));

    Schema.Field reused = new Schema.Field("id", Schema.create(Schema.Type.INT));
    Schema first = Schema.createRecord("First", null, "example", false);
    first.setFields(Collections.singletonList(reused));
    Schema second = Schema.createRecord("Second", null, "example", false);
    expectThrows(AvroRuntimeException.class, () -> second.setFields(Collections.singletonList(reused)));
  }

  @Test
  public void recordAliasesAreStoredUsingNamespaceRules() {
    Schema record = Schema.createRecord("User", null, "example", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    record.addAlias("LegacyUser");
    record.addAlias("ExternalUser", "external");
    record.addAlias("RootUser", "");

    Set<String> aliases = record.getAliases();
    assertTrue(aliases.contains("example.LegacyUser"));
    assertTrue(aliases.contains("external.ExternalUser"));
    assertTrue(aliases.contains(".RootUser"));

    aliases.add("localMutationDoesNotAffectSchema");
    assertFalse(record.getAliases().contains("localMutationDoesNotAffectSchema"));
  }

  @Test
  public void errorRecordReportsErrorTypeAndSerializesAsError() {
    Schema error = Schema.createRecord("Problem", "problem doc", "example", true,
        Collections.singletonList(new Schema.Field("message", Schema.create(Schema.Type.STRING))));

    assertTrue(error.isError());
    assertTrue(error.toString().contains("\"type\":\"error\""));
    assertTrue(error.toString(true).contains("\n"));
  }

  @Test
  public void fieldConstructorsExposeNameSchemaDocDefaultOrderAndAliases() {
    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema.Field field = new Schema.Field("count", intSchema, "count doc", 7, Schema.Field.Order.DESCENDING);

    assertEquals("count", field.name());
    assertEquals(-1, field.pos());
    assertSame(intSchema, field.schema());
    assertEquals("count doc", field.doc());
    assertTrue(field.hasDefaultValue());
    assertEquals(7, ((Number) field.defaultVal()).intValue());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertTrue(field.toString().contains("count type:INT pos:-1"));

    field.addAlias("old_count");
    assertTrue(field.aliases().contains("old_count"));
    expectThrows(UnsupportedOperationException.class, () -> field.aliases().add("cannot_add"));
  }

  @Test
  public void fieldSupportsNullDefaultForNullableUnion() {
    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    Schema.Field optional = new Schema.Field("optional", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(optional.hasDefaultValue());
    assertSame(JsonProperties.NULL_VALUE, optional.defaultVal());
    assertTrue(optional.schema().isNullable());
  }

  @Test
  public void fieldCopyConstructorCopiesPropsAndAliasesButUsesNewSchema() {
    Schema.Field original = new Schema.Field("value", Schema.create(Schema.Type.STRING), "doc", "x");
    original.addProp("p", "v");
    original.addAlias("old_value");

    Schema.Field copy = new Schema.Field(original, Schema.create(Schema.Type.BYTES));

    assertEquals(original.name(), copy.name());
    assertEquals(original.doc(), copy.doc());
    assertEquals("x", original.defaultVal());
    assertTrue(copy.defaultVal() instanceof byte[]);
    assertEquals("x", new String((byte[]) copy.defaultVal(), StandardCharsets.UTF_8));
    assertEquals(original.order(), copy.order());
    assertEquals("v", copy.getProp("p"));
    assertTrue(copy.aliases().contains("old_value"));
    assertEquals(Schema.Type.BYTES, copy.schema().getType());
    assertFalse(original.schema().equals(copy.schema()));
  }

  @Test
  public void fieldConstructorRejectsNullSchemaNullOrderInvalidNameAndInvalidDefault() {
    expectThrows(NullPointerException.class, () -> new Schema.Field("x", null));
    expectThrows(NullPointerException.class,
        () -> new Schema.Field("x", Schema.create(Schema.Type.INT), null, 1, null));
    expectThrows(SchemaParseException.class,
        () -> new Schema.Field("bad-name", Schema.create(Schema.Type.STRING)));
    expectThrows(AvroTypeException.class,
        () -> new Schema.Field("age", Schema.create(Schema.Type.INT), null, "not-an-int"));
  }

  @Test
  public void globalNameValidatorAndValidateDefaultsSettingsAffectFieldConstruction() {
    assertSame(NameValidator.UTF_VALIDATOR, Schema.getNameValidator());
    Schema.setNameValidator(NameValidator.NO_VALIDATION);
    Schema.Field invalidByDefaultRules = new Schema.Field("bad-name", Schema.create(Schema.Type.STRING));
    assertEquals("bad-name", invalidByDefaultRules.name());

    assertTrue(Schema.getValidateDefaults());
    Schema.setValidateDefaults(false);
    Schema.Field invalidDefaultAllowed = new Schema.Field("age", Schema.create(Schema.Type.INT), null, "not-an-int");
    assertTrue(invalidDefaultAllowed.hasDefaultValue());
    assertFalse(Schema.getValidateDefaults());
  }

  @Test
  public void fieldEqualityIncludesNameSchemaDefaultOrderAndProperties() {
    Schema.Field left = new Schema.Field("name", Schema.create(Schema.Type.STRING), "doc one", "x");
    Schema.Field right = new Schema.Field("name", Schema.create(Schema.Type.STRING), "doc two", "x");
    assertEquals("doc is intentionally ignored by Field.equals", left, right);
    assertEquals(left.hashCode(), right.hashCode());

    right.addProp("p", "v");
    assertFalse(left.equals(right));

    Schema.Field differentDefault = new Schema.Field("name", Schema.create(Schema.Type.STRING), null, "y");
    assertFalse(left.equals(differentDefault));

    Schema.Field differentOrder = new Schema.Field("name", Schema.create(Schema.Type.STRING), null, "x",
        Schema.Field.Order.IGNORE);
    assertFalse(left.equals(differentOrder));
  }

  @Test
  public void createEnumExposesSymbolsDefaultOrdinalsAndAliases() {
    Schema enumSchema = Schema.createEnum("Color", "color doc", "example", Arrays.asList("RED", "GREEN", "BLUE"),
        "GREEN");

    assertEquals(Schema.Type.ENUM, enumSchema.getType());
    assertEquals("Color", enumSchema.getName());
    assertEquals("color doc", enumSchema.getDoc());
    assertEquals("example", enumSchema.getNamespace());
    assertEquals("example.Color", enumSchema.getFullName());
    assertEquals(Arrays.asList("RED", "GREEN", "BLUE"), enumSchema.getEnumSymbols());
    assertEquals("GREEN", enumSchema.getEnumDefault());
    assertTrue(enumSchema.hasEnumSymbol("RED"));
    assertFalse(enumSchema.hasEnumSymbol("PURPLE"));
    assertEquals(0, enumSchema.getEnumOrdinal("RED"));
    assertEquals(2, enumSchema.getEnumOrdinal("BLUE"));
    expectThrows(TracingAvroTypeException.class, () -> enumSchema.getEnumOrdinal("PURPLE"));

    enumSchema.addAlias("OldColor");
    assertTrue(enumSchema.getAliases().contains("example.OldColor"));
    expectThrows(IllegalStateException.class, () -> enumSchema.getEnumSymbols().add("PURPLE"));
  }

  @Test
  public void createEnumRejectsDuplicateSymbolsInvalidNamesAndInvalidDefault() {
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Color", null, "example", Arrays.asList("RED", "RED")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Color", null, "example", Arrays.asList("bad-symbol")));
    expectThrows(SchemaParseException.class,
        () -> Schema.createEnum("Color", null, "example", Arrays.asList("RED"), "GREEN"));
  }

  @Test
  public void createArrayAndMapExposeNestedTypesAndSerialization() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema array = Schema.createArray(stringSchema);
    Schema map = Schema.createMap(stringSchema);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(stringSchema, array.getElementType());
    assertTrue(array.toString().contains("\"type\":\"array\""));
    assertTrue(array.toString().contains("\"items\":\"string\""));

    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(stringSchema, map.getValueType());
    assertTrue(map.toString().contains("\"type\":\"map\""));
    assertTrue(map.toString().contains("\"values\":\"string\""));
  }

  @Test
  public void createUnionExposesBranchesIndexByNameNameNullableAndLockedTypes() {
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema fixed = Schema.createFixed("Hash", null, "example", 16);
    Schema union = Schema.createUnion(nullSchema, stringSchema, fixed);

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(Arrays.asList(nullSchema, stringSchema, fixed), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("example.Hash"));
    assertNull(union.getIndexNamed("missing"));
    assertTrue(union.getName().startsWith("union["));
    expectThrows(IllegalStateException.class, () -> union.getTypes().add(Schema.create(Schema.Type.INT)));
    expectThrows(AvroRuntimeException.class, () -> union.addProp("notAllowed", "value"));
  }

  @Test
  public void createUnionRejectsDuplicateAndNestedUnionBranches() {
    expectThrows(AvroRuntimeException.class,
        () -> Schema.createUnion(Schema.create(Schema.Type.INT), Schema.create(Schema.Type.INT)));

    Schema nested = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    expectThrows(AvroRuntimeException.class, () -> Schema.createUnion(Schema.create(Schema.Type.INT), nested));
  }

  @Test
  public void createFixedExposesNameDocNamespaceSizeAliasesAndSerialization() {
    Schema fixed = Schema.createFixed("Md5", "md5 doc", "example", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Md5", fixed.getName());
    assertEquals("md5 doc", fixed.getDoc());
    assertEquals("example", fixed.getNamespace());
    assertEquals("example.Md5", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
    fixed.addAlias("OldMd5");
    assertTrue(fixed.getAliases().contains("example.OldMd5"));
    assertTrue(fixed.toString().contains("\"type\":\"fixed\""));
    assertTrue(fixed.toString().contains("\"size\":16"));
  }

  @Test
  public void namedSchemasCannotUsePrimitiveNames() {
    expectThrows(AvroTypeException.class, () -> Schema.createRecord("int", null, null, false));
    expectThrows(AvroTypeException.class, () -> Schema.createEnum("string", null, null, Collections.singletonList("A")));
    expectThrows(AvroTypeException.class, () -> Schema.createFixed("long", null, null, 4));
  }

  @Test
  public void isNullableReturnsTrueForNullAndUnionsContainingNullOnly() {
    assertTrue(Schema.create(Schema.Type.NULL).isNullable());
    assertFalse(Schema.create(Schema.Type.STRING).isNullable());
    assertFalse(Schema.createArray(Schema.create(Schema.Type.NULL)).isNullable());
    assertTrue(Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.NULL)).isNullable());
  }

  @Test
  public void equalsAndHashCodeHandleRecordsCollectionsAndDifferentTypes() {
    Schema left = userRecordSchema("User", "example", "id");
    Schema right = userRecordSchema("User", "example", "id");
    Schema differentField = userRecordSchema("User", "example", "otherId");
    Schema differentName = userRecordSchema("Other", "example", "id");

    assertEquals(left, left);
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertFalse(left.equals(differentField));
    assertFalse(left.equals(differentName));
    assertFalse(left.equals(Schema.create(Schema.Type.STRING)));
    assertFalse(left.equals("not a schema"));
  }

  @Test
  public void recursiveRecordEqualsHashCodeAndToStringDoNotOverflow() {
    Schema node = Schema.createRecord("Node", null, "example", false);
    Schema nullableNode = Schema.createUnion(Schema.create(Schema.Type.NULL), node);
    node.setFields(Arrays.asList(
        new Schema.Field("value", Schema.create(Schema.Type.STRING)),
        new Schema.Field("next", nullableNode, null, Schema.Field.NULL_DEFAULT_VALUE)));

    Schema parsed = new Schema.Parser().parse(node.toString());
    assertEquals(node, parsed);
    assertEquals(node.hashCode(), parsed.hashCode());
    assertTrue(node.toString().contains("\"Node\""));
  }

  @Test
  public void toStringWithReferencedSchemasUsesNameReferenceInsteadOfInliningKnownSchema() {
    Schema child = Schema.createRecord("Child", null, "example", false,
        Collections.singletonList(new Schema.Field("name", Schema.create(Schema.Type.STRING))));
    Schema parent = Schema.createRecord("Parent", null, "example", false,
        Collections.singletonList(new Schema.Field("child", child)));

    String json = parent.toString(Collections.singleton(child), false);
    assertTrue(json.contains("\"name\":\"Parent\""));
    assertTrue(json.contains("\"type\":\"Child\""));
  }

  @Test
  public void parserParsesPrimitiveRecordEnumArrayMapUnionFixedAndProperties() {
    String json = "{"
        + "\"type\":\"record\","
        + "\"name\":\"Container\","
        + "\"namespace\":\"example\","
        + "\"doc\":\"container doc\","
        + "\"aliases\":[\"OldContainer\"],"
        + "\"customProp\":\"customValue\","
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"long\",\"default\":1},"
        + "{\"name\":\"color\",\"type\":{\"type\":\"enum\",\"name\":\"Color\",\"symbols\":[\"RED\",\"GREEN\"],\"default\":\"RED\"}},"
        + "{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},"
        + "{\"name\":\"attributes\",\"type\":{\"type\":\"map\",\"values\":\"string\"}},"
        + "{\"name\":\"maybe\",\"type\":[\"null\",\"string\"],\"default\":null},"
        + "{\"name\":\"hash\",\"type\":{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":16}}"
        + "]}";

    Schema schema = new Schema.Parser().parse(json);

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals("example.Container", schema.getFullName());
    assertEquals("container doc", schema.getDoc());
    assertEquals("customValue", schema.getProp("customProp"));
    assertTrue(schema.getAliases().contains("example.OldContainer"));
    assertEquals(6, schema.getFields().size());
    assertEquals(Schema.Type.LONG, schema.getField("id").schema().getType());
    assertEquals(1L, ((Number) schema.getField("id").defaultVal()).longValue());
    assertEquals(Schema.Type.ENUM, schema.getField("color").schema().getType());
    assertEquals("RED", schema.getField("color").schema().getEnumDefault());
    assertEquals(Schema.Type.ARRAY, schema.getField("tags").schema().getType());
    assertEquals(Schema.Type.MAP, schema.getField("attributes").schema().getType());
    assertTrue(schema.getField("maybe").schema().isNullable());
    assertEquals(Schema.Type.FIXED, schema.getField("hash").schema().getType());
    assertEquals(16, schema.getField("hash").schema().getFixedSize());
  }

  @Test
  public void parserSupportsCommentsStringPiecesFileStreamStaticMethodsAndParseInternal() throws Exception {
    Schema.Parser parser = new Schema.Parser();
    assertEquals(Schema.Type.STRING, parser.parse("{ /* comment */ \"type\":\"string\" }").getType());
    assertEquals(Schema.Type.INT, parser.parse("{\"type\":", "\"int\"}").getType());
    assertEquals(Schema.Type.LONG, parser.parseInternal("\"long\"").getType());
    assertEquals(Schema.Type.BOOLEAN, Schema.parse("\"boolean\"").getType());
    assertEquals(Schema.Type.FLOAT, Schema.parse("{\"type\":\"float\"}", true).getType());

    File schemaFile = temporaryFolder.newFile("schema.avsc");
    writeUtf8(schemaFile, "\"double\"");
    assertEquals(Schema.Type.DOUBLE, parser.parse(schemaFile).getType());
    assertEquals(Schema.Type.DOUBLE, Schema.parse(schemaFile).getType());

    CloseAwareInputStream input = new CloseAwareInputStream("\"bytes\"");
    assertEquals(Schema.Type.BYTES, parser.parse(input).getType());
    assertFalse("Parser.parse(InputStream) must leave the input stream open", input.closed);
    input.close();
    assertTrue(input.closed);

    CloseAwareInputStream staticInput = new CloseAwareInputStream("\"null\"");
    assertEquals(Schema.Type.NULL, Schema.parse(staticInput).getType());
    assertFalse(staticInput.closed);
    staticInput.close();
  }

  @Test
  public void parserTracksTypesAndCanUseAddedTypesFromIterableAndMap() {
    Schema address = Schema.createRecord("Address", null, "example", false,
        Collections.singletonList(new Schema.Field("street", Schema.create(Schema.Type.STRING))));

    Schema.Parser iterableParser = new Schema.Parser();
    assertSame(iterableParser, iterableParser.addTypes(Collections.singletonList(address)));
    Schema userWithAddedType = iterableParser.parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"address\",\"type\":\"Address\"}]}");
    assertEquals("example.Address", userWithAddedType.getField("address").schema().getFullName());
    assertTrue(iterableParser.getTypes().containsKey("example.User"));
    assertTrue(iterableParser.getTypes().containsKey("example.Address"));

    Map<String, Schema> namedTypes = new HashMap<>();
    namedTypes.put(address.getFullName(), address);
    Schema.Parser mapParser = new Schema.Parser();
    assertSame(mapParser, mapParser.addTypes(namedTypes));
    Schema parsedAddressRef = mapParser.parse("{\"type\":\"record\",\"name\":\"Envelope\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"address\",\"type\":\"Address\"}]}");
    assertEquals("example.Address", parsedAddressRef.getField("address").schema().getFullName());
  }

  @Test
  public void parserValidateDefaultsCanBeDisabled() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\",\"fields\":["
        + "{\"name\":\"age\",\"type\":\"int\",\"default\":\"not-an-int\"}]}";

    Schema.Parser validatingParser = new Schema.Parser();
    assertTrue(validatingParser.getValidateDefaults());
    expectThrows(AvroTypeException.class, () -> validatingParser.parse(invalidDefault));

    Schema.Parser nonValidatingParser = new Schema.Parser();
    assertSame(nonValidatingParser, nonValidatingParser.setValidateDefaults(false));
    assertFalse(nonValidatingParser.getValidateDefaults());
    Schema schema = nonValidatingParser.parse(invalidDefault);
    assertEquals("age", schema.getFields().get(0).name());
  }

  @Test
  public void parserRejectsMalformedUnknownAndDanglingSchemas() {
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{not-json"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NoFields\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"array\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"map\"}"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"F\"}"));
    expectThrows(AvroRuntimeException.class, () -> new Schema.Parser().parse("[\"int\",\"int\"]"));
    expectThrows(SchemaParseException.class, () -> new Schema.Parser().parse("\"string\" \"int\""));
  }

  @Test
  public void staticParseWithValidationFlagControlsNameValidation() {
    String invalidName = "{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}";
    expectThrows(SchemaParseException.class, () -> Schema.parse(invalidName, true));

    Schema parsed = Schema.parse(invalidName, false);
    assertEquals("bad-name", parsed.getName());
  }

  @Test
  public void parseAliasesHandlesMissingValidAndInvalidAliases() {
    assertNull(Schema.parseAliases(Schema.parseJson("{}")));
    assertEquals(new java.util.LinkedHashSet<>(Arrays.asList("a", "b")),
        Schema.parseAliases(Schema.parseJson("{\"aliases\":[\"a\",\"b\"]}")));

    expectThrows(SchemaParseException.class, () -> Schema.parseAliases(Schema.parseJson("{\"aliases\":\"not-array\"}")));
    expectThrows(SchemaParseException.class, () -> Schema.parseAliases(Schema.parseJson("{\"aliases\":[1]}")));
  }

  @Test
  public void parseJsonAndParseJsonToObjectConvertJsonContent() {
    JsonNode node = Schema.parseJson("{\"k\":\"v\",\"numbers\":[1,2]}");
    assertEquals("v", node.get("k").textValue());
    assertEquals(2, node.get("numbers").size());

    Object object = Schema.parseJsonToObject("{\"k\":\"v\"}");
    assertTrue(object instanceof Map);
    assertEquals("v", ((Map<?, ?>) object).get("k"));

    expectThrows(RuntimeException.class, () -> Schema.parseJson("{invalid-json"));
  }

  @Test
  public void isValidDefaultAcceptsAndRejectsValuesForAllSchemaKinds() {
    assertTrue(Schema.create(Schema.Type.STRING).isValidDefault(Schema.parseJson("\"abc\"")));
    assertFalse(Schema.create(Schema.Type.STRING).isValidDefault(Schema.parseJson("1")));
    assertTrue(Schema.create(Schema.Type.BYTES).isValidDefault(Schema.parseJson("\"abc\"")));
    assertTrue(Schema.create(Schema.Type.INT).isValidDefault(Schema.parseJson("2147483647")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(Schema.parseJson("2147483648")));
    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(Schema.parseJson("9223372036854775807")));
    assertFalse(Schema.create(Schema.Type.LONG).isValidDefault(Schema.parseJson("9223372036854775808")));
    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(Schema.parseJson("1.5")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(Schema.parseJson("1.5")));
    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(Schema.parseJson("true")));
    assertFalse(Schema.create(Schema.Type.BOOLEAN).isValidDefault(Schema.parseJson("\"true\"")));
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(Schema.parseJson("null")));
    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault(null));

    Schema arrayOfInts = Schema.createArray(Schema.create(Schema.Type.INT));
    assertTrue(arrayOfInts.isValidDefault(Schema.parseJson("[1,2,3]")));
    assertFalse(arrayOfInts.isValidDefault(Schema.parseJson("[1,\"bad\"]")));

    Schema mapOfBooleans = Schema.createMap(Schema.create(Schema.Type.BOOLEAN));
    assertTrue(mapOfBooleans.isValidDefault(Schema.parseJson("{\"a\":true,\"b\":false}")));
    assertFalse(mapOfBooleans.isValidDefault(Schema.parseJson("{\"a\":\"true\"}")));

    Schema fixed = Schema.createFixed("F", null, "example", 2);
    assertTrue(fixed.isValidDefault(Schema.parseJson("\"aa\"")));
    assertFalse(fixed.isValidDefault(Schema.parseJson("12")));

    Schema enumSchema = Schema.createEnum("Choice", null, "example", Arrays.asList("A", "B"));
    assertTrue(enumSchema.isValidDefault(Schema.parseJson("\"A\"")));
    assertFalse(enumSchema.isValidDefault(Schema.parseJson("1")));
  }

  @Test
  public void isValidDefaultSupportsRecordsAndUnions() {
    Schema record = Schema.createRecord("Defaults", null, "example", false, Arrays.asList(
        new Schema.Field("required", Schema.create(Schema.Type.INT)),
        new Schema.Field("optional", Schema.create(Schema.Type.STRING), null, "fallback")));

    assertTrue(record.isValidDefault(Schema.parseJson("{\"required\":1,\"optional\":\"x\"}")));
    assertTrue(record.isValidDefault(Schema.parseJson("{\"required\":1}")));
    assertFalse(record.isValidDefault(Schema.parseJson("{\"optional\":\"x\"}")));
    assertFalse(record.isValidDefault(Schema.parseJson("[]")));

    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING), record);
    assertTrue(union.isValidDefault(Schema.parseJson("null")));
    assertTrue(union.isValidDefault(Schema.parseJson("\"text\"")));
    assertTrue(union.isValidDefault(Schema.parseJson("{\"required\":1}")));
    assertFalse(union.isValidDefault(Schema.parseJson("123")));
  }

  @Test
  public void applyAliasesReturnsSameWriterWhenReaderHasNoAliasesOrSchemasEqual() {
    Schema writer = userRecordSchema("User", "example", "id");
    Schema same = userRecordSchema("User", "example", "id");
    assertSame(writer, Schema.applyAliases(writer, writer));
    assertSame(writer, Schema.applyAliases(writer, same));
  }

  @Test
  public void applyAliasesRewritesRecordNameAndFieldNameFromReaderAliases() {
    Schema writer = Schema.createRecord("OldUser", null, "example", false,
        Collections.singletonList(new Schema.Field("oldName", Schema.create(Schema.Type.STRING))));

    Schema.Field readerField = new Schema.Field("name", Schema.create(Schema.Type.STRING));
    readerField.addAlias("oldName");
    Schema reader = Schema.createRecord("User", null, "example", false, Collections.singletonList(readerField));
    reader.addAlias("OldUser");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertNotSame(writer, rewritten);
    assertEquals("example.User", rewritten.getFullName());
    assertNull(rewritten.getField("oldName"));
    assertNotNull(rewritten.getField("name"));
    assertEquals(Schema.Type.STRING, rewritten.getField("name").schema().getType());
  }

  @Test
  public void applyAliasesRewritesEnumFixedArrayMapAndUnionBranches() {
    Schema oldRecord = Schema.createRecord("OldNested", null, "example", false,
        Collections.singletonList(new Schema.Field("value", Schema.create(Schema.Type.STRING))));
    Schema newRecord = Schema.createRecord("Nested", null, "example", false,
        Collections.singletonList(new Schema.Field("value", Schema.create(Schema.Type.STRING))));
    newRecord.addAlias("OldNested");

    Schema writer = Schema.createUnion(Schema.createArray(oldRecord), Schema.createMap(oldRecord));
    Schema reader = Schema.createUnion(Schema.createArray(newRecord), Schema.createMap(newRecord));

    Schema rewritten = Schema.applyAliases(writer, reader);
    assertEquals("example.Nested", rewritten.getTypes().get(0).getElementType().getFullName());
    assertEquals("example.Nested", rewritten.getTypes().get(1).getValueType().getFullName());

    Schema oldEnum = Schema.createEnum("OldEnum", null, "example", Arrays.asList("A", "B"));
    Schema newEnum = Schema.createEnum("NewEnum", null, "example", Arrays.asList("A", "B"));
    newEnum.addAlias("OldEnum");
    assertEquals("example.NewEnum", Schema.applyAliases(oldEnum, newEnum).getFullName());

    Schema oldFixed = Schema.createFixed("OldFixed", null, "example", 4);
    Schema newFixed = Schema.createFixed("NewFixed", null, "example", 4);
    newFixed.addAlias("OldFixed");
    assertEquals("example.NewFixed", Schema.applyAliases(oldFixed, newFixed).getFullName());
  }

  @Test
  public void seenPairUsesIdentityNotEquals() {
    String left = new String("same");
    String right = new String("other");
    Schema.SeenPair one = new Schema.SeenPair(left, right);
    Schema.SeenPair sameReferences = new Schema.SeenPair(left, right);
    Schema.SeenPair equalButDifferentReferences = new Schema.SeenPair(new String("same"), new String("other"));

    assertEquals(one, sameReferences);
    assertEquals(one.hashCode(), sameReferences.hashCode());
    assertFalse(one.equals(equalButDifferentReferences));
    assertFalse(one.equals("not a SeenPair"));
  }

  @Test
  public void nameHandlesQualifiedNamesNamespaceQualificationAndJsonWriting() throws Exception {
    Schema.Name qualified = new Schema.Name("example.User", "ignored");
    assertEquals("example.User", qualified.toString());
    assertEquals("User", qualified.getQualified("example"));
    assertEquals("example.User", qualified.getQualified("other"));
    assertEquals(qualified, new Schema.Name("User", "example"));
    assertEquals(qualified.hashCode(), new Schema.Name("User", "example").hashCode());

    Schema.Name primitiveNamedRecord = new Schema.Name("example.int", null);
    assertEquals("example.int", primitiveNamedRecord.getQualified("example"));

    java.io.StringWriter out = new java.io.StringWriter();
    com.fasterxml.jackson.core.JsonGenerator generator = Schema.FACTORY.createGenerator(out);
    generator.writeStartObject();
    qualified.writeName("other", generator);
    generator.writeEndObject();
    generator.flush();
    generator.close();
    assertTrue(out.toString().contains("\"name\":\"User\""));
    assertTrue(out.toString().contains("\"namespace\":\"example\""));
  }

  @Test
  public void namesMapResolvesPrimitivesDefaultNamespaceAndRejectsConflictingRedefinitions() {
    Schema.Names names = new Schema.Names("example");
    assertEquals("example", names.space());
    names.space("changed");
    assertEquals("changed", names.space());
    assertEquals(Schema.Type.INT, names.get("int").getType());

    Schema user = Schema.createRecord("User", null, "changed", false, Collections.emptyList());
    names.add(user);
    assertTrue(names.contains(user));
    assertSame(user, names.get("User"));
    assertSame(user, names.get("changed.User"));

    assertSame(user, names.put(new Schema.Name("User", "changed"), user));
    Schema conflicting = Schema.createRecord("User", null, "changed", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    expectThrows(SchemaParseException.class, () -> names.put(new Schema.Name("User", "changed"), conflicting));
  }

  @Test
  public void lockableArrayListAllowsMutationBeforeLockAndRejectsMutationAfterLock() {
    Schema.LockableArrayList<String> list = new Schema.LockableArrayList<>("a", "b");
    assertTrue(list.add("c"));
    assertTrue(list.remove("a"));
    assertTrue(list.addAll(Arrays.asList("d", "e", "z")));
    assertTrue(list.retainAll(Arrays.asList("b", "c", "d", "e")));
    assertEquals(Arrays.asList("b", "c", "d", "e"), list);

    List<String> locked = list.lock();
    assertSame(list, locked);
    expectThrows(IllegalStateException.class, () -> list.add("x"));
    expectThrows(IllegalStateException.class, () -> list.remove("b"));
    expectThrows(IllegalStateException.class, () -> list.remove(0));
    expectThrows(IllegalStateException.class, () -> list.addAll(Collections.singletonList("x")));
    expectThrows(IllegalStateException.class, () -> list.addAll(0, Collections.singletonList("x")));
    expectThrows(IllegalStateException.class, () -> list.removeAll(Collections.singletonList("b")));
    expectThrows(IllegalStateException.class, () -> list.retainAll(Collections.singletonList("b")));
    expectThrows(IllegalStateException.class, list::clear);
  }

  @Test
  public void schemaSerializationRoundTripUsesWriteReplaceAndParser() throws Exception {
    Schema schema = userRecordSchema("SerializableUser", "example", "id");
    schema.addProp("source", "unit-test");

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bytes);
    out.writeObject(schema);
    out.close();

    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()));
    Object restored = in.readObject();
    in.close();

    assertTrue(restored instanceof Schema);
    assertEquals(schema, restored);
    assertEquals(schema.toString(), restored.toString());
  }

  private static void assertPrimitive(Schema.Type type, String name) {
    Schema schema = Schema.create(type);
    assertEquals(type, schema.getType());
    assertEquals(name, schema.getName());
    assertEquals(name, schema.getFullName());
    assertNull(schema.getDoc());
    assertFalse(schema.isUnion());
  }

  private static Schema userRecordSchema(String name, String namespace, String fieldName) {
    return Schema.createRecord(name, null, namespace, false,
        Collections.singletonList(new Schema.Field(fieldName, Schema.create(Schema.Type.INT))));
  }

  private static void writeUtf8(File file, String content) throws IOException {
    FileOutputStream output = new FileOutputStream(file);
    try {
      output.write(content.getBytes(StandardCharsets.UTF_8));
    } finally {
      output.close();
    }
  }

  private static <T extends Throwable> T expectThrows(Class<T> expected, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expected.isInstance(actual)) {
        return expected.cast(actual);
      }
      actual.printStackTrace();
      fail("Expected " + expected.getName() + " but caught " + actual.getClass().getName());
    }
    fail("Expected " + expected.getName() + " to be thrown");
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class CloseAwareInputStream extends InputStream {
    private final ByteArrayInputStream delegate;
    private boolean closed;

    private CloseAwareInputStream(String content) {
      this.delegate = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
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
  }
}
