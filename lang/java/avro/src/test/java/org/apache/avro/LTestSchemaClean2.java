/*
 * Black-box JUnit 4 tests for org.apache.avro.Schema based only on the
 * public Apache Avro 1.12.0 API documentation.
 *
 * This file intentionally uses a new class name, LTestSchemaClean2, so it can
 * be run independently from any previous LTestSchema.java file.
 *
 * Suggested path:
 *   lang/java/avro/src/test/java/org/apache/avro/LTestSchemaClean22.java
 *
 * Suggested command:
 *   mvn -pl lang/java/avro -Dspotless.check.skip=true -Dcheckstyle.skip=true -Dtest=LTestSchemaClean22 clean test
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("deprecation")
public class LTestSchemaClean2 {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @FunctionalInterface
  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static void expectRuntimeException(ThrowingRunnable runnable) {
    try {
      runnable.run();
      fail("Expected RuntimeException");
    } catch (RuntimeException expected) {
      // expected
    } catch (Exception unexpected) {
      fail("Expected RuntimeException, but got checked exception: " + unexpected);
    }
  }

  private static void expectUnsupportedOperation(ThrowingRunnable runnable) {
    try {
      runnable.run();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException expected) {
      // expected
    } catch (Exception unexpected) {
      fail("Expected UnsupportedOperationException, but got: " + unexpected);
    }
  }

  private static JsonNode json(String value) throws IOException {
    return MAPPER.readTree(value);
  }

  private static Schema stringSchema() {
    return Schema.create(Schema.Type.STRING);
  }

  private static Schema intSchema() {
    return Schema.create(Schema.Type.INT);
  }

  private static Schema nullSchema() {
    return Schema.create(Schema.Type.NULL);
  }

  private static Schema recordSchema(String name, String namespace) {
    return Schema.createRecord(name, "doc", namespace, false, Collections.emptyList());
  }

  private static boolean containsAliasSuffix(Set<String> aliases, String suffix) {
    for (String alias : aliases) {
      if (alias.equals(suffix) || alias.endsWith("." + suffix)) {
        return true;
      }
    }
    return false;
  }

  @Test
  public void primitiveSchemasExposeTypeNameFullNameAndTypeOnly() {
    List<Schema.Type> primitiveTypes = Arrays.asList(
        Schema.Type.NULL,
        Schema.Type.BOOLEAN,
        Schema.Type.INT,
        Schema.Type.LONG,
        Schema.Type.FLOAT,
        Schema.Type.DOUBLE,
        Schema.Type.BYTES,
        Schema.Type.STRING);

    for (Schema.Type type : primitiveTypes) {
      Schema schema = Schema.create(type);

      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
      assertFalse(schema.isUnion());
      if (type == Schema.Type.NULL) {
        assertTrue(schema.isNullable());
      } else {
        assertFalse(schema.isNullable());
      }
      assertNull(schema.getLogicalType());
    }
  }

  @Test
  public void schemaTypeEnumExposesDocumentedLowercaseNames() {
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
  public void createPrimitiveRejectsNonPrimitiveTypes() {
    expectRuntimeException(() -> Schema.create(Schema.Type.RECORD));
    expectRuntimeException(() -> Schema.create(Schema.Type.ENUM));
    expectRuntimeException(() -> Schema.create(Schema.Type.ARRAY));
    expectRuntimeException(() -> Schema.create(Schema.Type.MAP));
    expectRuntimeException(() -> Schema.create(Schema.Type.UNION));
    expectRuntimeException(() -> Schema.create(Schema.Type.FIXED));
  }

  @Test
  public void namedRecordWithoutFieldsCanHaveFieldsSetExactlyOnce() {
    Schema record = Schema.createRecord("Person", "person documentation", "example.avro", false);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("Person", record.getName());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.Person", record.getFullName());
    assertEquals("person documentation", record.getDoc());
    assertFalse(record.isError());
    assertFalse(record.hasFields());

    Schema.Field name = new Schema.Field("name", stringSchema(), "name documentation", "anonymous");
    Schema.Field age = new Schema.Field("age", intSchema(), "age documentation", 0);
    record.setFields(Arrays.asList(name, age));

    assertTrue(record.hasFields());
    assertEquals(Arrays.asList(name, age), record.getFields());
    assertSame(name, record.getField("name"));
    assertSame(age, record.getField("age"));
    assertNull(record.getField("missing"));
    assertEquals(0, name.pos());
    assertEquals(1, age.pos());

    expectRuntimeException(() -> record.setFields(Collections.singletonList(
        new Schema.Field("another", stringSchema()))));
  }

  @Test
  public void createRecordWithFieldsAndErrorFlag() {
    Schema.Field message = new Schema.Field("message", stringSchema(), "message documentation", "");
    Schema errorRecord = Schema.createRecord(
        "Problem",
        "error documentation",
        "example.avro",
        true,
        Collections.singletonList(message));

    assertEquals(Schema.Type.RECORD, errorRecord.getType());
    assertEquals("Problem", errorRecord.getName());
    assertEquals("example.avro.Problem", errorRecord.getFullName());
    assertEquals("error documentation", errorRecord.getDoc());
    assertTrue(errorRecord.isError());
    assertTrue(errorRecord.hasFields());
    assertSame(message, errorRecord.getField("message"));
    assertEquals(0, message.pos());
  }

  @Test
  public void deprecatedAnonymousRecordCanBeCreatedWithFields() {
    Schema.Field value = new Schema.Field("value", stringSchema());
    Schema anonymous = Schema.createRecord(Collections.singletonList(value));

    assertEquals(Schema.Type.RECORD, anonymous.getType());
    assertTrue(anonymous.hasFields());
    assertSame(value, anonymous.getField("value"));
  }

  @Test
  public void enumSchemaExposesSymbolsDefaultOrdinalAndMembership() {
    List<String> symbols = Arrays.asList("SPADES", "HEARTS", "DIAMONDS", "CLUBS");
    Schema enumSchema = Schema.createEnum("Suit", "card suit", "cards", symbols, "HEARTS");

    assertEquals(Schema.Type.ENUM, enumSchema.getType());
    assertEquals("Suit", enumSchema.getName());
    assertEquals("cards", enumSchema.getNamespace());
    assertEquals("cards.Suit", enumSchema.getFullName());
    assertEquals("card suit", enumSchema.getDoc());
    assertEquals(symbols, enumSchema.getEnumSymbols());
    assertEquals("HEARTS", enumSchema.getEnumDefault());
    assertEquals(0, enumSchema.getEnumOrdinal("SPADES"));
    assertEquals(1, enumSchema.getEnumOrdinal("HEARTS"));
    assertTrue(enumSchema.hasEnumSymbol("DIAMONDS"));
    assertFalse(enumSchema.hasEnumSymbol("JOKER"));
  }

  @Test
  public void enumWithoutDefaultReturnsNullDefault() {
    Schema enumSchema = Schema.createEnum(
        "TrafficLight",
        "traffic light",
        "road",
        Arrays.asList("RED", "YELLOW", "GREEN"));

    assertNull(enumSchema.getEnumDefault());
    assertEquals(Arrays.asList("RED", "YELLOW", "GREEN"), enumSchema.getEnumSymbols());
  }

  @Test
  public void fixedSchemaExposesSizeAndNamedMetadata() {
    Schema fixed = Schema.createFixed("Hash16", "sixteen bytes", "crypto", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Hash16", fixed.getName());
    assertEquals("crypto", fixed.getNamespace());
    assertEquals("crypto.Hash16", fixed.getFullName());
    assertEquals("sixteen bytes", fixed.getDoc());
    assertEquals(16, fixed.getFixedSize());
  }

  @Test
  public void arrayAndMapSchemasExposeContainedTypes() {
    Schema elementType = stringSchema();
    Schema array = Schema.createArray(elementType);
    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(elementType, array.getElementType());

    Schema valueType = intSchema();
    Schema map = Schema.createMap(valueType);
    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(valueType, map.getValueType());
  }

  @Test
  public void unionSchemaExposesBranchesIndexesAndNullability() {
    Schema nullSchema = nullSchema();
    Schema stringSchema = stringSchema();
    Schema recordSchema = recordSchema("Person", "example.avro");
    Schema union = Schema.createUnion(nullSchema, stringSchema, recordSchema);

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(Arrays.asList(nullSchema, stringSchema, recordSchema), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), union.getIndexNamed("example.avro.Person"));
    assertNull(union.getIndexNamed("example.avro.Missing"));
  }

  @Test
  public void unionWithoutNullIsNotNullable() {
    Schema union = Schema.createUnion(stringSchema(), intSchema());

    assertTrue(union.isUnion());
    assertFalse(union.isNullable());
  }

  @Test
  public void unionCreatedFromListAndVarargsAreEquivalent() {
    List<Schema> branches = Arrays.asList(nullSchema(), stringSchema());

    Schema fromList = Schema.createUnion(branches);
    Schema fromVarargs = Schema.createUnion(branches.get(0), branches.get(1));

    assertEquals(fromList, fromVarargs);
    assertEquals(fromList.hashCode(), fromVarargs.hashCode());
  }

  @Test
  public void namedSchemasCanStoreAliases() {
    Schema record = Schema.createRecord("Person", "doc", "example.avro", false, Collections.emptyList());
    record.addAlias("Human");
    record.addAlias("LegacyPerson", "legacy.avro");

    assertTrue(containsAliasSuffix(record.getAliases(), "Human"));
    assertTrue(containsAliasSuffix(record.getAliases(), "LegacyPerson"));

    Schema enumSchema = Schema.createEnum("Color", "doc", "paint", Arrays.asList("RED", "BLUE"));
    enumSchema.addAlias("Colour");
    assertTrue(containsAliasSuffix(enumSchema.getAliases(), "Colour"));

    Schema fixed = Schema.createFixed("Digest", "doc", "hash", 8);
    fixed.addAlias("Checksum");
    assertTrue(containsAliasSuffix(fixed.getAliases(), "Checksum"));
  }

  @Test
  public void addPropStoresStringAndObjectProperties() {
    Schema schema = stringSchema();

    assertFalse(schema.hasProps());
    assertFalse(schema.propsContainsKey("stringProp"));
    assertNull(schema.getProp("stringProp"));

    schema.addProp("stringProp", "value");
    schema.addProp("numberProp", Integer.valueOf(7));

    assertTrue(schema.hasProps());
    assertTrue(schema.propsContainsKey("stringProp"));
    assertTrue(schema.propsContainsKey("numberProp"));
    assertEquals("value", schema.getProp("stringProp"));
    assertEquals("value", schema.getObjectProp("stringProp"));
    assertEquals(Integer.valueOf(7), schema.getObjectProp("numberProp"));
    assertNull(schema.getProp("numberProp"));
    assertEquals("fallback", schema.getObjectProp("missing", "fallback"));
  }

  @Test
  public void addPropRejectsNullNameNullValueAndChangingExistingValue() {
    Schema schema = stringSchema();

    expectRuntimeException(() -> schema.addProp(null, "value"));
    expectRuntimeException(() -> schema.addProp("name", (String) null));

    schema.addProp("stable", "same");
    schema.addProp("stable", "same");

    expectRuntimeException(() -> schema.addProp("stable", "different"));
  }

  @Test
  public void objectPropertiesAreUnmodifiableAndIterable() {
    Schema schema = stringSchema();
    schema.addProp("first", "a");
    schema.addProp("second", Integer.valueOf(2));

    Map<String, Object> props = schema.getObjectProps();
    assertEquals("a", props.get("first"));
    assertEquals(Integer.valueOf(2), props.get("second"));
    expectUnsupportedOperation(() -> props.put("third", "c"));

    final List<String> visited = new ArrayList<>();
    schema.forEachProperty((name, value) -> visited.add(name + "=" + value));

    assertTrue(visited.contains("first=a"));
    assertTrue(visited.contains("second=2"));
  }

  @Test
  public void addAllPropsAndPutAllCopyPropertiesFromAnotherJsonPropertiesInstance() {
    Schema source = stringSchema();
    source.addProp("sourceText", "hello");
    source.addProp("sourceNumber", Integer.valueOf(42));

    Schema targetUsingAddAll = intSchema();
    targetUsingAddAll.addAllProps(source);

    assertEquals("hello", targetUsingAddAll.getProp("sourceText"));
    assertEquals(Integer.valueOf(42), targetUsingAddAll.getObjectProp("sourceNumber"));

    Schema targetUsingPutAll = Schema.create(Schema.Type.LONG);
    targetUsingPutAll.putAll(source);

    assertEquals("hello", targetUsingPutAll.getProp("sourceText"));
    assertEquals(Integer.valueOf(42), targetUsingPutAll.getObjectProp("sourceNumber"));
  }

  @Test
  public void logicalTypeIsReturnedForSchemaWithRecognizedLogicalType() {
    Schema date = intSchema();
    LogicalTypes.date().addToSchema(date);

    assertNotNull(date.getLogicalType());
    assertEquals("date", date.getLogicalType().getName());
    assertNull(stringSchema().getLogicalType());
  }

  @Test
  public void isValidDefaultChecksJsonValueAgainstSchema() throws Exception {
    assertTrue(intSchema().isValidDefault(json("7")));
    assertFalse(intSchema().isValidDefault(json("\"seven\"")));

    assertTrue(stringSchema().isValidDefault(json("\"text\"")));
    assertFalse(stringSchema().isValidDefault(json("false")));

    Schema nullableString = Schema.createUnion(nullSchema(), stringSchema());
    assertTrue(nullableString.isValidDefault(json("null")));
    assertTrue(nullableString.isValidDefault(json("\"text\"")));
    assertFalse(nullableString.isValidDefault(json("12")));
  }

  @Test
  public void parseJsonToObjectMapsJsonValuesToAvroJsonPropertyValues() {
    Object parsed = Schema.parseJsonToObject("{\"name\":\"alpha\",\"items\":[1,true,null]}");

    assertTrue(parsed instanceof Map);
    Map<?, ?> map = (Map<?, ?>) parsed;
    assertEquals("alpha", map.get("name"));

    Object items = map.get("items");
    assertTrue(items instanceof Collection);

    List<?> itemList = new ArrayList<>((Collection<?>) items);
    assertEquals(3, itemList.size());
    assertEquals(1, ((Number) itemList.get(0)).intValue());
    assertEquals(Boolean.TRUE, itemList.get(1));
    assertTrue(itemList.get(2) == null || JsonProperties.NULL_VALUE.equals(itemList.get(2)));
  }

  @Test
  public void toStringRendersJsonThatCanBeParsedBack() {
    Schema original = Schema.createRecord("Person", "doc", "example.avro", false,
        Collections.singletonList(new Schema.Field("name", stringSchema(), "doc", "unknown")));

    String rendered = original.toString();
    Schema reparsed = new Schema.Parser().parse(rendered);

    assertEquals(original, reparsed);
  }

  @Test
  public void deprecatedPrettyToStringRendersParseableJson() {
    Schema original = Schema.createArray(stringSchema());

    String pretty = original.toString(true);
    Schema reparsed = new Schema.Parser().parse(pretty);

    assertEquals(original, reparsed);
    assertTrue(pretty.length() >= original.toString().length());
  }

  @Test
  public void equalsAndHashCodeRespectLogicalSchemaEquality() {
    Schema first = Schema.createArray(stringSchema());
    Schema second = new Schema.Parser().parse(first.toString());
    Schema different = Schema.createArray(intSchema());

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertNotEquals(first, different);
    assertNotEquals(first, "not a schema");
  }

  @Test
  public void deprecatedStaticParseMethodsParseStringInputStreamAndFile() throws Exception {
    Schema fromString = Schema.parse("\"string\"");
    assertEquals(Schema.Type.STRING, fromString.getType());

    Schema fromStringWithValidationFlag = Schema.parse("\"int\"", true);
    assertEquals(Schema.Type.INT, fromStringWithValidationFlag.getType());

    byte[] bytes = "\"long\"".getBytes(StandardCharsets.UTF_8);
    Schema fromStream = Schema.parse(new ByteArrayInputStream(bytes));
    assertEquals(Schema.Type.LONG, fromStream.getType());

    File schemaFile = temporaryFolder.newFile("schema.avsc");
    Files.write(schemaFile.toPath(), "\"boolean\"".getBytes(StandardCharsets.UTF_8));
    Schema fromFile = Schema.parse(schemaFile);
    assertEquals(Schema.Type.BOOLEAN, fromFile.getType());
  }

  @Test
  public void deprecatedStaticValidateDefaultsFlagCanBeChangedAndRestored() {
    boolean original = Schema.getValidateDefaults();
    try {
      Schema.setValidateDefaults(true);
      assertTrue(Schema.getValidateDefaults());

      Schema.setValidateDefaults(false);
      assertFalse(Schema.getValidateDefaults());
    } finally {
      Schema.setValidateDefaults(original);
    }
  }

  @Test
  public void applyAliasesRewritesWriterRecordAndFieldNamesUsingReaderAliases() {
    Schema writer = Schema.createRecord("OldRecord", "writer doc", "old.ns", false);
    writer.setFields(Collections.singletonList(
        new Schema.Field("oldName", stringSchema(), "writer field", "")));

    Schema reader = Schema.createRecord("NewRecord", "reader doc", "new.ns", false);
    reader.addAlias("OldRecord", "old.ns");
    Schema.Field readerField = new Schema.Field("newName", stringSchema(), "reader field", "");
    readerField.addAlias("oldName");
    reader.setFields(Collections.singletonList(readerField));

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("new.ns.NewRecord", rewritten.getFullName());
    assertNotNull(rewritten.getField("newName"));
    assertNull(rewritten.getField("oldName"));
    assertEquals(Schema.Type.STRING, rewritten.getField("newName").schema().getType());
  }

  @Test
  public void unsupportedTypeSpecificAccessorsOnPrimitiveThrowRuntimeException() {
    Schema primitive = intSchema();

    expectRuntimeException(() -> primitive.getFields());
    expectRuntimeException(() -> primitive.getField("x"));
    expectRuntimeException(() -> primitive.setFields(Collections.emptyList()));
    expectRuntimeException(() -> primitive.getEnumSymbols());
    expectRuntimeException(() -> primitive.getEnumDefault());
    expectRuntimeException(() -> primitive.getEnumOrdinal("X"));
    expectRuntimeException(() -> primitive.hasEnumSymbol("X"));
    expectRuntimeException(() -> primitive.getElementType());
    expectRuntimeException(() -> primitive.getValueType());
    expectRuntimeException(() -> primitive.getTypes());
    expectRuntimeException(() -> primitive.getIndexNamed("int"));
    expectRuntimeException(() -> primitive.getFixedSize());
  }

  @Test
  public void fieldConstructorsAndAccessorsExposeDocumentedValuesAfterRecordAssignment() {
    Schema.Field field = new Schema.Field(
        "score",
        intSchema(),
        "score documentation",
        10,
        Schema.Field.Order.DESCENDING);

    Schema record = Schema.createRecord("Game", "doc", "play", false, Collections.singletonList(field));

    assertEquals("score", field.name());
    assertEquals(0, field.pos());
    assertEquals(Schema.Type.INT, field.schema().getType());
    assertEquals("score documentation", field.doc());
    assertTrue(field.hasDefaultValue());
    assertEquals(10, ((Number) field.defaultVal()).intValue());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertSame(field, record.getField("score"));
  }

  @Test
  public void fieldWithoutDefaultReportsNoDefaultEvenWhenDefaultValueReturnsNull() {
    Schema.Field field = new Schema.Field("name", stringSchema());

    assertFalse(field.hasDefaultValue());
    assertNull(field.defaultVal());
    assertNull(field.doc());
    assertEquals(Schema.Field.Order.ASCENDING, field.order());
  }

  @Test
  public void fieldWithNullDefaultMarkerReportsDefaultPresence() {
    Schema nullableString = Schema.createUnion(nullSchema(), stringSchema());
    Schema.Field optional = new Schema.Field(
        "optional",
        nullableString,
        "optional documentation",
        Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(optional.hasDefaultValue());
  }

  @Test
  public void fieldAliasesAreStoredAsUnmodifiableSet() {
    Schema.Field field = new Schema.Field("newName", stringSchema());
    field.addAlias("oldName");
    field.addAlias("legacyName");

    assertTrue(field.aliases().contains("oldName"));
    assertTrue(field.aliases().contains("legacyName"));
    expectUnsupportedOperation(() -> field.aliases().add("anotherAlias"));
  }

  @Test
  public void fieldCopyConstructorCopiesPropsAndAliasesWhenNewSchemaHasNoDefaultConflict() {
    Schema.Field original = new Schema.Field("value", stringSchema(), "original documentation");
    original.addAlias("oldValue");
    original.addProp("fieldProp", "propValue");

    Schema.Field copy = new Schema.Field(original, intSchema());

    assertEquals("value", copy.name());
    assertEquals("original documentation", copy.doc());
    assertEquals(Schema.Type.INT, copy.schema().getType());
    assertFalse(copy.hasDefaultValue());
    assertNull(copy.defaultVal());
    assertEquals(Schema.Field.Order.ASCENDING, copy.order());
    assertTrue(copy.aliases().contains("oldValue"));
    assertEquals("propValue", copy.getProp("fieldProp"));
  }

  @Test
  public void fieldCopyConstructorRejectsCopiedDefaultThatIsInvalidForNewSchema() {
    Schema.Field original = new Schema.Field(
        "value",
        stringSchema(),
        "original documentation",
        "default",
        Schema.Field.Order.IGNORE);

    expectRuntimeException(() -> new Schema.Field(original, intSchema()));
  }

  @Test
  public void fieldCopyConstructorCopiesDefaultWhenItRemainsCompatible() {
    Schema.Field original = new Schema.Field(
        "value",
        stringSchema(),
        "original documentation",
        "default",
        Schema.Field.Order.IGNORE);

    Schema.Field copy = new Schema.Field(original, stringSchema());

    assertEquals("value", copy.name());
    assertEquals("original documentation", copy.doc());
    assertEquals("default", copy.defaultVal());
    assertTrue(copy.hasDefaultValue());
    assertEquals(Schema.Field.Order.IGNORE, copy.order());
    assertEquals(Schema.Type.STRING, copy.schema().getType());
  }

  @Test
  public void fieldEqualityHashCodeAndToStringAreConsistentForEquivalentFields() {
    Schema.Field first = new Schema.Field("value", stringSchema(), "doc", "default");
    Schema.Field second = new Schema.Field("value", stringSchema(), "doc", "default");
    Schema.Field different = new Schema.Field("other", stringSchema(), "doc", "default");

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertNotEquals(first, different);
    assertTrue(first.toString().contains("value"));
  }

  @Test
  public void parserValidateDefaultsToggleIsExposedWithoutAssumingInitialGlobalState() {
    Schema.Parser parser = new Schema.Parser();

    assertSame(parser, parser.setValidateDefaults(true));
    assertTrue(parser.getValidateDefaults());

    assertSame(parser, parser.setValidateDefaults(false));
    assertFalse(parser.getValidateDefaults());

    assertSame(parser, parser.setValidateDefaults(true));
    assertTrue(parser.getValidateDefaults());
  }

  @Test
  public void parserAddsNamedSchemasToKnownTypesAndResolvesLaterReferences() {
    Schema.Parser parser = new Schema.Parser();

    Schema known = Schema.createRecord("KnownType", "doc", "p", false, Collections.emptyList());
    assertSame(parser, parser.addTypes(Collections.singletonList(known)));

    assertTrue(parser.getTypes().containsKey("p.KnownType"));
    assertEquals(known, parser.getTypes().get("p.KnownType"));

    Schema parsed = parser.parse("{\"type\":\"array\",\"items\":\"p.KnownType\"}");
    assertEquals(Schema.Type.ARRAY, parsed.getType());
    assertEquals("p.KnownType", parsed.getElementType().getFullName());
  }

  @Test
  public void deprecatedParserAddTypesMapRegistersNamedSchemas() {
    Schema known = Schema.createRecord("MappedType", "doc", "p", false, Collections.emptyList());
    Map<String, Schema> map = new LinkedHashMap<>();
    map.put(known.getFullName(), known);

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(map));

    assertTrue(parser.getTypes().containsKey("p.MappedType"));
    assertEquals(known, parser.getTypes().get("p.MappedType"));
  }

  @Test
  public void parserParseStringAndStringFragments() {
    Schema fromSingleString = new Schema.Parser().parse("\"string\"");
    assertEquals(Schema.Type.STRING, fromSingleString.getType());

    Schema fromFragments = new Schema.Parser().parse(
        "{\"type\":\"record\",",
        "\"name\":\"Fragmented\",",
        "\"fields\":[]}");
    assertEquals(Schema.Type.RECORD, fromFragments.getType());
    assertEquals("Fragmented", fromFragments.getName());
  }

  @Test
  public void parserParseInternalParsesSchemaString() {
    Schema schema = new Schema.Parser().parseInternal("\"boolean\"");

    assertEquals(Schema.Type.BOOLEAN, schema.getType());
  }

  @Test
  public void parserParseInputStreamLeavesStreamOpen() throws Exception {
    CloseTrackingInputStream input =
        new CloseTrackingInputStream("\"long\"".getBytes(StandardCharsets.UTF_8));

    Schema schema = new Schema.Parser().parse(input);

    assertEquals(Schema.Type.LONG, schema.getType());
    assertFalse(input.closed);
  }

  @Test
  public void parserParseFileReadsUtf8SchemaFile() throws Exception {
    File schemaFile = temporaryFolder.newFile("parser-schema.avsc");
    Files.write(schemaFile.toPath(), "\"double\"".getBytes(StandardCharsets.UTF_8));

    Schema schema = new Schema.Parser().parse(schemaFile);

    assertEquals(Schema.Type.DOUBLE, schema.getType());
  }

  @Test
  public void parserValidateDefaultsTrueRejectsInvalidDefaultValues() {
    String invalidDefault = "{"
        + "\"type\":\"record\","
        + "\"name\":\"InvalidDefault\","
        + "\"fields\":[{\"name\":\"age\",\"type\":\"int\",\"default\":\"wrong\"}]"
        + "}";

    Schema parsedWhenValidationDisabled = new Schema.Parser()
        .setValidateDefaults(false)
        .parse(invalidDefault);
    assertEquals("InvalidDefault", parsedWhenValidationDisabled.getName());

    expectRuntimeException(() -> new Schema.Parser()
        .setValidateDefaults(true)
        .parse(invalidDefault));
  }

  @Test
  public void seenPairEqualityAndHashCodeDependOnBothObjects() {
    Schema first = stringSchema();
    Schema second = intSchema();

    Schema.SeenPair pair = new Schema.SeenPair(first, second);
    Schema.SeenPair same = new Schema.SeenPair(first, second);
    Schema.SeenPair reversed = new Schema.SeenPair(second, first);

    assertEquals(pair, same);
    assertEquals(pair.hashCode(), same.hashCode());
    assertNotEquals(pair, reversed);
    assertNotEquals(pair, "not a seen pair");
  }

  private static final class CloseTrackingInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseTrackingInputStream(byte[] data) {
      super(data);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
