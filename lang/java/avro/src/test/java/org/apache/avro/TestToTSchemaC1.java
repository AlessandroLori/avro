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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * TestToTSchemaC1Fixed exercises the public API of Schema_C1.java when the class is
 * used as org.apache.avro.Schema inside the Avro module.
 *
 * Public API areas covered:
 * - Schema.Type#getName
 * - Schema factory methods: create, createRecord, createEnum, createArray,
 *   createMap, createUnion, createFixed
 * - Schema accessors and behaviour: getType, getField, getFields, hasFields,
 *   setFields, enum accessors, name/doc/namespace/fullName, aliases, isError,
 *   element/value/union/fixed accessors, toString overloads, equals, hashCode,
 *   isUnion, isNullable, addProp overloads, getLogicalType, isValidDefault
 * - Static parsing helpers: parse(File), parse(InputStream), parse(String),
 *   parse(String, boolean), parseJsonToObject, applyAliases
 * - Global validation toggles: setValidateDefaults/getValidateDefaults
 * - Nested public classes: Schema.Field, Schema.Field.Order, Schema.Parser,
 *   Schema.SeenPair
 */
@SuppressWarnings({ "deprecation", "ConstantConditions" })
public class TestToTSchemaC1 {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Rule
  public final TemporaryFolder temp = new TemporaryFolder();

  @After
  public void restoreGlobalSchemaSettings() {
    Schema.setValidateDefaults(true);
  }

  /*
   * ============================================================
   * Primitive schema creation and primitive API behaviour
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
  public void createShouldBuildEveryPrimitiveSchema() {
    assertPrimitiveType(Schema.Type.STRING);
    assertPrimitiveType(Schema.Type.BYTES);
    assertPrimitiveType(Schema.Type.INT);
    assertPrimitiveType(Schema.Type.LONG);
    assertPrimitiveType(Schema.Type.FLOAT);
    assertPrimitiveType(Schema.Type.DOUBLE);
    assertPrimitiveType(Schema.Type.BOOLEAN);
    assertPrimitiveType(Schema.Type.NULL);
  }

  @Test
  public void createShouldRejectNonPrimitiveSchemaTypes() {
    assertCreateFails(Schema.Type.RECORD);
    assertCreateFails(Schema.Type.ENUM);
    assertCreateFails(Schema.Type.ARRAY);
    assertCreateFails(Schema.Type.MAP);
    assertCreateFails(Schema.Type.UNION);
    assertCreateFails(Schema.Type.FIXED);
  }

  @Test
  public void primitiveSchemaShouldExposeNameAndFullNameButNoNamespaceOrDoc() {
    Schema schema = Schema.create(Schema.Type.STRING);

    assertEquals(Schema.Type.STRING, schema.getType());
    assertEquals("string", schema.getName());
    assertEquals("string", schema.getFullName());
    assertNull(schema.getDoc());
    assertNull(schema.getLogicalType());
    assertFalse(schema.isUnion());
    assertFalse(schema.isNullable());

    expectAvroRuntime("Not a named type", new ThrowingRunnable() {
      @Override
      public void run() {
        schema.getNamespace();
      }
    });
  }

  @Test
  public void primitiveSpecificAccessorsShouldRejectIncompatibleType() {
    final Schema schema = Schema.create(Schema.Type.STRING);

    expectAvroRuntime("Not a record", new ThrowingRunnable() { @Override public void run() { schema.getField("x"); } });
    expectAvroRuntime("Not a record", new ThrowingRunnable() { @Override public void run() { schema.getFields(); } });
    expectAvroRuntime("Not a record", new ThrowingRunnable() { @Override public void run() { schema.hasFields(); } });
    expectAvroRuntime("Not a record", new ThrowingRunnable() { @Override public void run() { schema.setFields(Collections.<Schema.Field>emptyList()); } });
    expectAvroRuntime("Not an enum", new ThrowingRunnable() { @Override public void run() { schema.getEnumSymbols(); } });
    expectAvroRuntime("Not an enum", new ThrowingRunnable() { @Override public void run() { schema.getEnumDefault(); } });
    expectAvroRuntime("Not an enum", new ThrowingRunnable() { @Override public void run() { schema.getEnumOrdinal("A"); } });
    expectAvroRuntime("Not an enum", new ThrowingRunnable() { @Override public void run() { schema.hasEnumSymbol("A"); } });
    expectAvroRuntime("Not a named type", new ThrowingRunnable() { @Override public void run() { schema.addAlias("Alias"); } });
    expectAvroRuntime("Not a named type", new ThrowingRunnable() { @Override public void run() { schema.addAlias("Alias", "example"); } });
    expectAvroRuntime("Not a named type", new ThrowingRunnable() { @Override public void run() { schema.getAliases(); } });
    expectAvroRuntime("Not a record", new ThrowingRunnable() { @Override public void run() { schema.isError(); } });
    expectAvroRuntime("Not an array", new ThrowingRunnable() { @Override public void run() { schema.getElementType(); } });
    expectAvroRuntime("Not a map", new ThrowingRunnable() { @Override public void run() { schema.getValueType(); } });
    expectAvroRuntime("Not a union", new ThrowingRunnable() { @Override public void run() { schema.getTypes(); } });
    expectAvroRuntime("Not a union", new ThrowingRunnable() { @Override public void run() { schema.getIndexNamed("string"); } });
    expectAvroRuntime("Not fixed", new ThrowingRunnable() { @Override public void run() { schema.getFixedSize(); } });
  }

  @Test
  public void addPropShouldChangeEqualitySerializationAndHashCodeCache() {
    Schema plain = Schema.create(Schema.Type.STRING);
    Schema withProp = Schema.create(Schema.Type.STRING);
    int initialHash = withProp.hashCode();

    withProp.addProp("custom", "value");

    assertEquals("value", withProp.getProp("custom"));
    assertFalse("Properties are part of Schema equality", plain.equals(withProp));
    assertFalse("addProp must invalidate the cached hash code", initialHash == withProp.hashCode());
    assertTrue(withProp.toString().contains("\"custom\":\"value\""));
  }

  @Test
  public void addObjectPropShouldSerializeNonStringJsonProperty() {
    Schema schema = Schema.create(Schema.Type.INT);

    schema.addProp("customNumber", Integer.valueOf(7));

    assertTrue(schema.toString().contains("\"customNumber\":7"));
  }

  /*
   * ============================================================
   * Record schemas and Field API
   * ============================================================
   */

  @Test
  public void createRecordShouldCreateEmptyNamedRecordAndAllowFieldsOnce() {
    Schema record = Schema.createRecord("User", "user doc", "example.avro", false);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("user doc", record.getDoc());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertFalse(record.isError());
    assertFalse(record.hasFields());

    List<Schema.Field> fields = Arrays.asList(
        new Schema.Field("id", Schema.create(Schema.Type.INT), "identifier", 0),
        new Schema.Field("name", Schema.create(Schema.Type.STRING), "user name", "unknown"));
    record.setFields(fields);

    assertTrue(record.hasFields());
    assertEquals(2, record.getFields().size());
    assertSame(fields.get(0), record.getField("id"));
    assertSame(fields.get(1), record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, fields.get(0).pos());
    assertEquals(1, fields.get(1).pos());
  }

  @Test
  public void createRecordWithFieldsShouldSetFieldsImmediately() {
    List<Schema.Field> fields = Collections.singletonList(
        new Schema.Field("active", Schema.create(Schema.Type.BOOLEAN), "active flag", Boolean.TRUE));

    Schema record = Schema.createRecord("Account", null, "example", true, fields);

    assertTrue(record.hasFields());
    assertTrue(record.isError());
    assertEquals("active", record.getFields().get(0).name());
    assertEquals(Schema.Type.BOOLEAN, record.getField("active").schema().getType());
  }

  @Test
  public void deprecatedAnonymousCreateRecordShouldCreateRecordWithFields() {
    List<Schema.Field> fields = Collections.singletonList(
        new Schema.Field("value", Schema.create(Schema.Type.STRING)));

    Schema record = Schema.createRecord(fields);

    assertEquals(Schema.Type.RECORD, record.getType());
    assertTrue(record.hasFields());
    assertEquals("value", record.getFields().get(0).name());
  }

  @Test
  public void setFieldsShouldRejectSecondCallDuplicateFieldNamesAndFieldReuse() {
    Schema record = Schema.createRecord("User", null, null, false);
    record.setFields(Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    expectAvroRuntime("Fields are already set", new ThrowingRunnable() {
      @Override
      public void run() {
        record.setFields(Collections.singletonList(new Schema.Field("name", Schema.create(Schema.Type.STRING))));
      }
    });

    final Schema duplicateRecord = Schema.createRecord("Duplicate", null, null, false);
    expectAvroRuntime("Duplicate field", new ThrowingRunnable() {
      @Override
      public void run() {
        duplicateRecord.setFields(Arrays.asList(
            new Schema.Field("id", Schema.create(Schema.Type.INT)),
            new Schema.Field("id", Schema.create(Schema.Type.LONG))));
      }
    });

    final Schema reusedRecord = Schema.createRecord("Reuse", null, null, false);
    final Schema.Field reused = record.getField("id");
    expectAvroRuntime("Field already used", new ThrowingRunnable() {
      @Override
      public void run() {
        reusedRecord.setFields(Collections.singletonList(reused));
      }
    });
  }

  @Test
  public void recordFieldsListShouldBeUnmodifiable() {
    Schema record = Schema.createRecord("ImmutableFields", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    try {
      record.getFields().add(new Schema.Field("name", Schema.create(Schema.Type.STRING)));
      fail("Expected record fields to be immutable after setFields");
    } catch (IllegalStateException expected) {
      assertNotNull(expected);
      assertEquals(1, record.getFields().size());
    }
  }

  @Test
  public void recordAliasesShouldRespectDefaultAndExplicitNamespaces() {
    Schema record = Schema.createRecord("User", null, "reader.ns", false);

    record.addAlias("OldUser");
    record.addAlias("LegacyUser", "legacy.ns");
    record.addAlias("RootAlias", "");

    Set<String> aliases = record.getAliases();
    assertTrue(aliases.contains("reader.ns.OldUser"));
    assertTrue(aliases.contains("legacy.ns.LegacyUser"));
    assertTrue(aliases.contains(".RootAlias"));
  }

  @Test
  public void fieldConstructorsAndAccessorsShouldExposeNameSchemaDocDefaultAndOrder() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema.Field simple = new Schema.Field("simple", stringSchema);
    Schema.Field documented = new Schema.Field("documented", stringSchema, "field doc");
    Schema.Field withDefault = new Schema.Field("withDefault", stringSchema, "doc", "abc");
    Schema.Field descending = new Schema.Field("descending", stringSchema, "doc", "abc", Schema.Field.Order.DESCENDING);

    assertEquals("simple", simple.name());
    assertSame(stringSchema, simple.schema());
    assertNull(simple.doc());
    assertFalse(simple.hasDefaultValue());
    assertEquals(Schema.Field.Order.ASCENDING, simple.order());
    assertEquals(-1, simple.pos());

    assertEquals("field doc", documented.doc());
    assertTrue(withDefault.hasDefaultValue());
    assertEquals("abc", withDefault.defaultVal());
    assertEquals(Schema.Field.Order.DESCENDING, descending.order());
  }

  @Test
  public void fieldNullDefaultSentinelShouldRepresentExplicitNullDefault() {
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));

    Schema.Field field = new Schema.Field("maybe", union, null, Schema.Field.NULL_DEFAULT_VALUE);

    assertTrue(field.hasDefaultValue());
    assertNotNull(field.defaultVal());
  }

  @Test
  public void fieldShouldRejectNullSchemaNullOrderInvalidNameAndInvalidDefault() {
    expectThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("missingSchema", null);
      }
    });

    expectThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("badOrder", Schema.create(Schema.Type.INT), null, Integer.valueOf(1), null);
      }
    });

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("bad-name", Schema.create(Schema.Type.INT));
      }
    });

    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("badDefault", Schema.create(Schema.Type.INT), null, "not an int");
      }
    });
  }

  @Test
  public void fieldAliasesShouldBeReturnedAsUnmodifiableSet() {
    Schema.Field field = new Schema.Field("id", Schema.create(Schema.Type.INT));

    assertTrue(field.aliases().isEmpty());
    field.addAlias("oldId");
    assertTrue(field.aliases().contains("oldId"));

    try {
      field.aliases().add("other");
      fail("Expected aliases set to be immutable");
    } catch (UnsupportedOperationException expected) {
      assertNotNull(expected);
    }
  }

  @Test
  public void fieldCopyConstructorShouldCopyMetadataAliasesAndChangeSchema() {
    Schema.Field original = new Schema.Field("id", Schema.create(Schema.Type.INT), "doc", Integer.valueOf(1),
        Schema.Field.Order.IGNORE);
    original.addAlias("oldId");
    original.addProp("custom", "metadata");

    Schema.Field copy = new Schema.Field(original, Schema.create(Schema.Type.LONG));

    assertEquals(original.name(), copy.name());
    assertEquals(Schema.Type.LONG, copy.schema().getType());
    assertEquals(original.doc(), copy.doc());
    assertEquals(1L, ((Number) copy.defaultVal()).longValue());
    assertEquals(original.order(), copy.order());
    assertTrue(copy.aliases().contains("oldId"));
    assertEquals("metadata", copy.getProp("custom"));
  }

  @Test
  public void fieldEqualsHashCodeAndToStringShouldUseObservableFieldState() {
    Schema.Field left = new Schema.Field("id", Schema.create(Schema.Type.INT), null, Integer.valueOf(1));
    Schema.Field right = new Schema.Field("id", Schema.create(Schema.Type.INT), null, Integer.valueOf(1));
    Schema.Field differentSchema = new Schema.Field("id", Schema.create(Schema.Type.LONG), null, Long.valueOf(1L));

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertFalse(left.equals(differentSchema));
    assertTrue(left.toString().contains("id type:INT"));
  }

  /*
   * ============================================================
   * Enum, array, map, union and fixed schemas
   * ============================================================
   */

  @Test
  public void createEnumShouldExposeSymbolsOrdinalsMembershipAndDefault() {
    Schema enumSchema = Schema.createEnum("Status", "status doc", "example", Arrays.asList("NEW", "DONE"), "DONE");

    assertEquals(Schema.Type.ENUM, enumSchema.getType());
    assertEquals("Status", enumSchema.getName());
    assertEquals("example.Status", enumSchema.getFullName());
    assertEquals("status doc", enumSchema.getDoc());
    assertEquals(Arrays.asList("NEW", "DONE"), enumSchema.getEnumSymbols());
    assertEquals("DONE", enumSchema.getEnumDefault());
    assertTrue(enumSchema.hasEnumSymbol("NEW"));
    assertFalse(enumSchema.hasEnumSymbol("MISSING"));
    assertEquals(0, enumSchema.getEnumOrdinal("NEW"));
    assertEquals(1, enumSchema.getEnumOrdinal("DONE"));
  }

  @Test
  public void createEnumWithoutDefaultShouldReturnNullDefault() {
    Schema enumSchema = Schema.createEnum("Direction", null, null, Arrays.asList("NORTH", "SOUTH"));

    assertNull(enumSchema.getEnumDefault());
    assertEquals(Arrays.asList("NORTH", "SOUTH"), enumSchema.getEnumSymbols());
  }

  @Test
  public void enumShouldRejectDuplicateSymbolDefaultOutsideSymbolsAndMissingOrdinal() {
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createEnum("BadEnum", null, null, Arrays.asList("A", "A"));
      }
    });

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createEnum("BadDefault", null, null, Arrays.asList("A", "B"), "C");
      }
    });

    final Schema enumSchema = Schema.createEnum("GoodEnum", null, null, Arrays.asList("A", "B"));
    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        enumSchema.getEnumOrdinal("C");
      }
    });
  }

  @Test
  public void enumSymbolsShouldBeImmutable() {
    Schema enumSchema = Schema.createEnum("Status", null, null, Arrays.asList("NEW", "DONE"));

    try {
      enumSchema.getEnumSymbols().add("BROKEN");
      fail("Expected enum symbols to be immutable");
    } catch (IllegalStateException expected) {
      assertNotNull(expected);
      assertEquals(Arrays.asList("NEW", "DONE"), enumSchema.getEnumSymbols());
    }
  }

  @Test
  public void createArrayAndMapShouldExposeNestedTypes() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema array = Schema.createArray(stringSchema);
    Schema map = Schema.createMap(stringSchema);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(stringSchema, array.getElementType());
    assertFalse(array.isNullable());

    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(stringSchema, map.getValueType());
    assertFalse(map.isNullable());
  }

  @Test
  public void createUnionFromVarargsAndListShouldExposeTypesIndexesAndNullability() {
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema intSchema = Schema.create(Schema.Type.INT);

    Schema varargsUnion = Schema.createUnion(nullSchema, stringSchema, intSchema);
    Schema listUnion = Schema.createUnion(Arrays.asList(nullSchema, stringSchema));

    assertTrue(varargsUnion.isUnion());
    assertTrue(varargsUnion.isNullable());
    assertEquals(3, varargsUnion.getTypes().size());
    assertSame(nullSchema, varargsUnion.getTypes().get(0));
    assertEquals(Integer.valueOf(0), varargsUnion.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), varargsUnion.getIndexNamed("string"));
    assertEquals(Integer.valueOf(2), varargsUnion.getIndexNamed("int"));
    assertNull(varargsUnion.getIndexNamed("missing"));

    assertEquals(2, listUnion.getTypes().size());
    assertTrue(listUnion.isNullable());
  }

  @Test
  public void createUnionShouldAllowEmptyUnionButRejectDuplicatePrimitiveAndNestedUnion() {
    Schema empty = Schema.createUnion(Collections.<Schema>emptyList());
    assertTrue(empty.isUnion());
    assertTrue(empty.getTypes().isEmpty());
    assertFalse(empty.isNullable());

    expectAvroRuntime("Duplicate in union", new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.STRING));
      }
    });

    expectAvroRuntime("Nested union", new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.createUnion(Schema.create(Schema.Type.STRING)));
      }
    });
  }

  @Test
  public void unionTypesShouldBeImmutable() {
    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));

    try {
      union.getTypes().add(Schema.create(Schema.Type.INT));
      fail("Expected union branches to be immutable");
    } catch (IllegalStateException expected) {
      assertNotNull(expected);
      assertEquals(2, union.getTypes().size());
    }
  }

  @Test
  public void createFixedShouldExposeNameDocNamespaceSizeAndRejectNegativeSize() {
    Schema fixed = Schema.createFixed("Hash", "hash doc", "example", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Hash", fixed.getName());
    assertEquals("hash doc", fixed.getDoc());
    assertEquals("example", fixed.getNamespace());
    assertEquals("example.Hash", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createFixed("Bad", null, null, -1);
      }
    });
  }

  @Test
  public void namedSchemaShouldRejectPrimitiveNamesAndInvalidNames() {
    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createRecord("int", null, null, false);
      }
    });

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createFixed("bad-name", null, null, 1);
      }
    });
  }

  /*
   * ============================================================
   * Parsing public helpers and parser state
   * ============================================================
   */

  @Test
  public void staticParseStringShouldParsePrimitiveCompositeAndNamedSchemas() {
    assertEquals(Schema.Type.STRING, Schema.parse("\"string\"").getType());

    Schema record = Schema.parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals(Schema.Type.INT, record.getField("id").schema().getType());
  }

  @Test
  public void staticParseStringWithValidationFlagShouldControlNameValidation() {
    String invalidNameSchema = "{\"type\":\"record\",\"name\":\"bad-name\",\"fields\":[]}";

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.parse(invalidNameSchema, true);
      }
    });

    Schema parsedWithoutValidation = Schema.parse(invalidNameSchema, false);
    assertEquals("bad-name", parsedWithoutValidation.getName());
  }

  @Test
  public void staticParseInputStreamShouldNotCloseCallerOwnedStream() throws Exception {
    NonClosingInputStream input = new NonClosingInputStream("\"long\"");

    Schema parsed = Schema.parse(input);

    assertEquals(Schema.Type.LONG, parsed.getType());
    assertFalse("Parser must not close caller-owned InputStream", input.wasClosed());
  }

  @Test
  public void staticParseFileShouldReadUtf8SchemaFile() throws Exception {
    File file = temp.newFile("schema.avsc");
    writeUtf8(file, "{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":4}");

    Schema parsed = Schema.parse(file);

    assertEquals(Schema.Type.FIXED, parsed.getType());
    assertEquals("Hash", parsed.getName());
    assertEquals(4, parsed.getFixedSize());
  }

  @Test
  public void parseShouldRejectMalformedMissingTypeUnknownTypeAndInvalidAliases() {
    assertParseFails("{\"type\":");
    assertParseFails("{}");
    assertParseFails("{\"type\":\"unknown.Type\"}");
    assertParseFails("{\"type\":\"record\",\"name\":\"User\",\"aliases\":\"Old\",\"fields\":[]}");
    assertParseFails("{\"type\":\"record\",\"name\":\"User\",\"aliases\":[123],\"fields\":[]}");
  }

  @Test
  public void parserParseShouldConcatenateMultipleStringParts() {
    Schema.Parser parser = new Schema.Parser();

    Schema schema = parser.parse("{\"type\":\"record\",", "\"name\":\"User\",", "\"fields\":[]}");

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals("User", schema.getName());
  }

  @Test
  public void parserAddTypesShouldAllowLaterSchemasToReferenceKnownNamedTypes() {
    Schema address = Schema.createRecord("Address", null, "example", false,
        Collections.singletonList(new Schema.Field("city", Schema.create(Schema.Type.STRING))));
    Schema.Parser parser = new Schema.Parser();

    parser.addTypes(Collections.singletonList(address));
    Schema user = parser.parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"address\",\"type\":\"Address\"}]}");

    assertTrue(parser.getTypes().containsKey("example.Address"));
    assertEquals("Address", user.getField("address").schema().getName());
  }

  @Test
  public void parserAddTypesMapShouldUseMapValuesAsNamedTypes() {
    Schema event = Schema.createRecord("Event", null, "example", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.LONG))));
    Map<String, Schema> types = new LinkedHashMap<>();
    types.put("ignored-key", event);

    Schema.Parser parser = new Schema.Parser().addTypes(types);
    Schema wrapper = parser.parse("{\"type\":\"record\",\"name\":\"Wrapper\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"event\",\"type\":\"Event\"}]}");

    assertEquals("Event", wrapper.getField("event").schema().getName());
    assertTrue(parser.getTypes().containsKey("example.Event"));
  }

  @Test
  public void parserShouldRejectConflictingRedefinitionOfNamedType() {
    final Schema first = Schema.createRecord("Thing", null, "example", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    final Schema second = Schema.createRecord("Thing", null, "example", false,
        Collections.singletonList(new Schema.Field("name", Schema.create(Schema.Type.STRING))));
    final Schema.Parser parser = new Schema.Parser().addTypes(Collections.singletonList(first));

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        parser.addTypes(Collections.singletonList(second));
      }
    });
  }

  @Test
  public void parserValidateDefaultsFlagShouldControlInvalidFieldDefaultDuringParsing() {
    String invalidDefault = "{\"type\":\"record\",\"name\":\"BadDefault\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"default\":\"wrong\"}]}";

    Schema.Parser strict = new Schema.Parser();
    assertTrue(strict.getValidateDefaults());
    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        strict.parse(invalidDefault);
      }
    });

    Schema.Parser lenient = new Schema.Parser().setValidateDefaults(false);
    assertFalse(lenient.getValidateDefaults());
    Schema parsed = lenient.parse(invalidDefault);
    assertEquals("BadDefault", parsed.getName());
    assertEquals(Schema.Type.INT, parsed.getField("id").schema().getType());
    assertNull(parsed.getField("id").defaultVal());
  }

  @Test
  public void parserParseInternalShouldParseSchemaAndRejectDanglingContent() {
    Schema.Parser parser = new Schema.Parser();

    Schema parsed = parser.parseInternal("{\"type\":\"record\",\"name\":\"InternalOnly\",\"fields\":[]}");

    assertEquals("InternalOnly", parsed.getName());

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        parser.parseInternal("\"string\" \"int\"");
      }
    });
  }

  @Test
  public void parserShouldParseRecursiveRecordDefinitions() {
    Schema node = new Schema.Parser().parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"Node\","
        + "\"fields\":["
        + "{\"name\":\"value\",\"type\":\"string\"},"
        + "{\"name\":\"next\",\"type\":[\"null\",\"Node\"],\"default\":null}"
        + "]}");

    Schema nextUnion = node.getField("next").schema();
    assertTrue(nextUnion.isUnion());
    assertEquals("Node", nextUnion.getTypes().get(1).getName());
    assertSame("Recursive references should resolve to the same schema instance", node, nextUnion.getTypes().get(1));
  }

  @Test
  public void parseJsonToObjectShouldConvertJsonObjectsArraysAndScalars() {
    Object object = Schema.parseJsonToObject("{\"a\":1,\"b\":[true,\"x\"]}");

    assertTrue(object instanceof Map);
    Map<?, ?> map = (Map<?, ?>) object;
    assertEquals(1, ((Number) map.get("a")).intValue());
    assertTrue(map.get("b") instanceof List);

    assertEquals("text", Schema.parseJsonToObject("\"text\""));
  }

  /*
   * ============================================================
   * Serialization, equality, hashCode and logical/default validation
   * ============================================================
   */

  @Test
  public void toStringShouldSerializePrimitiveRecordPrettyAndReferencedSchemas() {
    Schema child = Schema.createRecord("Child", null, "example", false,
        Collections.singletonList(new Schema.Field("name", Schema.create(Schema.Type.STRING))));
    Schema parent = Schema.createRecord("Parent", null, "example", false,
        Collections.singletonList(new Schema.Field("child", child)));

    String compact = parent.toString();
    String pretty = parent.toString(true);
    String referenced = parent.toString(Collections.singletonList(child), false);

    assertTrue(compact.contains("\"type\":\"record\""));
    assertTrue(compact.contains("\"name\":\"Parent\""));
    assertTrue("Pretty rendering should contain line breaks", pretty.contains("\n"));
    assertTrue("Referenced schema rendering should use a named reference", referenced.contains("\"type\":\"Child\""));
  }

  @Test
  public void equalsAndHashCodeShouldDistinguishTypeNameFieldsPropsAndSymbols() {
    Schema left = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema same = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema differentField = Schema.createRecord("User", null, null, false,
        Collections.singletonList(new Schema.Field("name", Schema.create(Schema.Type.STRING))));

    assertEquals(left, same);
    assertEquals(left.hashCode(), same.hashCode());
    assertFalse(left.equals(differentField));
    assertFalse(left.equals(null));
    assertFalse(left.equals("not a schema"));

    Schema enumOne = Schema.createEnum("Status", null, null, Arrays.asList("A", "B"));
    Schema enumTwo = Schema.createEnum("Status", null, null, Arrays.asList("A", "C"));
    assertFalse(enumOne.equals(enumTwo));
  }

  @Test
  public void logicalTypeShouldBeAppliedWhenSchemaContainsValidLogicalType() {
    Schema date = new Schema.Parser().parse("{\"type\":\"int\",\"logicalType\":\"date\"}");

    assertNotNull(date.getLogicalType());
    assertEquals("date", date.getLogicalType().getName());
  }

  @Test
  public void isNullableShouldReturnTrueForNullSchemaAndNestedNullableUnion() {
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema nonNullableUnion = Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.INT));
    Schema nullableArray = Schema.createArray(Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING)));

    assertTrue(nullSchema.isNullable());
    assertFalse(nonNullableUnion.isNullable());
    assertTrue(nullableArray.getElementType().isNullable());
  }

  @Test
  public void globalValidateDefaultsShouldControlFieldConstructorDefaultValidation() {
    assertTrue(Schema.getValidateDefaults());

    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("id", Schema.create(Schema.Type.INT), null, "bad");
      }
    });

    Schema.setValidateDefaults(false);
    assertFalse(Schema.getValidateDefaults());

    Schema.Field field = new Schema.Field("id", Schema.create(Schema.Type.INT), null, "bad");
    assertEquals("id", field.name());
    assertEquals(Schema.Type.INT, field.schema().getType());
    assertNull(field.defaultVal());
  }

  @Test
  public void isValidDefaultShouldValidatePrimitiveDefaults() throws Exception {
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(json("null")));
    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault(json("1")));

    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("true")));
    assertFalse(Schema.create(Schema.Type.BOOLEAN).isValidDefault(json("\"true\"")));

    assertTrue(Schema.create(Schema.Type.INT).isValidDefault(json("2147483647")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(json("2147483648")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(json("\"1\"")));

    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(json("2147483648")));
    assertFalse(Schema.create(Schema.Type.LONG).isValidDefault(json("\"2147483648\"")));

    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(json("1.25")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("1.25")));
    assertFalse(Schema.create(Schema.Type.DOUBLE).isValidDefault(json("\"1.25\"")));

    assertTrue(Schema.create(Schema.Type.STRING).isValidDefault(json("\"abc\"")));
    assertTrue(Schema.create(Schema.Type.BYTES).isValidDefault(json("\"bytes\"")));
    assertFalse(Schema.create(Schema.Type.BYTES).isValidDefault(json("[1,2]")));
  }

  @Test
  public void isValidDefaultShouldValidateEnumFixedArrayMapRecordAndUnionDefaults() throws Exception {
    Schema enumSchema = Schema.createEnum("Status", null, null, Arrays.asList("NEW", "DONE"));
    Schema fixedSchema = Schema.createFixed("Hash", null, null, 2);
    Schema arraySchema = Schema.createArray(Schema.create(Schema.Type.INT));
    Schema mapSchema = Schema.createMap(Schema.create(Schema.Type.STRING));
    Schema recordSchema = Schema.createRecord("User", null, null, false,
        Arrays.asList(
            new Schema.Field("id", Schema.create(Schema.Type.INT)),
            new Schema.Field("name", Schema.create(Schema.Type.STRING), null, "anonymous")));
    Schema unionSchema = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));

    assertTrue(enumSchema.isValidDefault(json("\"NEW\"")));
    assertFalse(enumSchema.isValidDefault(json("1")));

    assertTrue(fixedSchema.isValidDefault(json("\"ab\"")));
    assertFalse(fixedSchema.isValidDefault(json("{}")));

    assertTrue(arraySchema.isValidDefault(json("[1,2,3]")));
    assertFalse(arraySchema.isValidDefault(json("[1,\"bad\"]")));

    assertTrue(mapSchema.isValidDefault(json("{\"a\":\"x\"}")));
    assertFalse(mapSchema.isValidDefault(json("{\"a\":1}")));

    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"name\":\"a\"}")));
    assertTrue("Missing field with valid declared default should be accepted",
        recordSchema.isValidDefault(json("{\"id\":1}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":\"bad\"}")));

    assertTrue(unionSchema.isValidDefault(json("null")));
    assertTrue(unionSchema.isValidDefault(json("\"abc\"")));
    assertFalse(unionSchema.isValidDefault(json("1")));
  }

  @Test
  public void isValidDefaultShouldReturnFalseForNullJsonNode() {
    assertFalse(Schema.create(Schema.Type.STRING).isValidDefault(null));
  }

  /*
   * ============================================================
   * Alias rewriting
   * ============================================================
   */

  @Test
  public void applyAliasesShouldReturnSameWriterWhenSchemasAreEqualOrReaderHasNoAliases() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema equalReader = parse("{\"type\":\"record\",\"name\":\"User\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema differentReaderWithoutAliases = parse("{\"type\":\"record\",\"name\":\"Other\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    assertSame(writer, Schema.applyAliases(writer, equalReader));
    assertSame(writer, Schema.applyAliases(writer, differentReaderWithoutAliases));
  }

  @Test
  public void applyAliasesShouldRewriteRecordAndFieldNamesWhilePreservingOrderTypesAndProps() {
    Schema writer = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"OldUser\","
        + "\"x-schema\":\"writer-metadata\","
        + "\"fields\":["
        + "{\"name\":\"oldId\",\"type\":\"int\",\"x-field\":\"field-metadata\"},"
        + "{\"name\":\"name\",\"type\":\"string\"}"
        + "]}");
    Schema reader = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[\"OldUser\"],"
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\",\"aliases\":[\"oldId\"]},"
        + "{\"name\":\"name\",\"type\":\"string\"}"
        + "]}");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("User", rewritten.getName());
    assertEquals("writer-metadata", rewritten.getProp("x-schema"));
    assertNull(rewritten.getField("oldId"));
    assertNotNull(rewritten.getField("id"));
    assertEquals(0, rewritten.getField("id").pos());
    assertEquals(Schema.Type.INT, rewritten.getField("id").schema().getType());
    assertEquals("field-metadata", rewritten.getField("id").getProp("x-field"));
    assertEquals("name", rewritten.getFields().get(1).name());
  }

  @Test
  public void applyAliasesShouldRewriteEnumFixedArrayMapAndUnionNamedTypes() {
    Schema enumWriter = parse("{\"type\":\"enum\",\"name\":\"OldStatus\",\"symbols\":[\"A\",\"B\"]}");
    Schema enumReader = parse("{\"type\":\"enum\",\"name\":\"Status\",\"aliases\":[\"OldStatus\"],\"symbols\":[\"A\",\"B\"]}");
    assertEquals("Status", Schema.applyAliases(enumWriter, enumReader).getName());

    Schema fixedWriter = parse("{\"type\":\"fixed\",\"name\":\"OldHash\",\"size\":4}");
    Schema fixedReader = parse("{\"type\":\"fixed\",\"name\":\"Hash\",\"aliases\":[\"OldHash\"],\"size\":4}");
    Schema rewrittenFixed = Schema.applyAliases(fixedWriter, fixedReader);
    assertEquals("Hash", rewrittenFixed.getName());
    assertEquals(4, rewrittenFixed.getFixedSize());

    Schema arrayWriter = parse("{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"OldItem\",\"fields\":[]}}");
    Schema arrayReader = parse("{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Item\",\"aliases\":[\"OldItem\"],\"fields\":[]}}");
    assertEquals("Item", Schema.applyAliases(arrayWriter, arrayReader).getElementType().getName());

    Schema mapWriter = parse("{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"OldValue\",\"fields\":[]}}");
    Schema mapReader = parse("{\"type\":\"map\",\"values\":{\"type\":\"record\",\"name\":\"Value\",\"aliases\":[\"OldValue\"],\"fields\":[]}}");
    assertEquals("Value", Schema.applyAliases(mapWriter, mapReader).getValueType().getName());

    Schema unionWriter = parse("[\"null\",{\"type\":\"record\",\"name\":\"OldEvent\",\"fields\":[]}]");
    Schema unionReader = parse("[\"null\",{\"type\":\"record\",\"name\":\"Event\",\"aliases\":[\"OldEvent\"],\"fields\":[]}]");
    assertEquals("Event", Schema.applyAliases(unionWriter, unionReader).getTypes().get(1).getName());
  }

  @Test
  public void applyAliasesShouldRewriteRecursiveRecordWithoutInfiniteRecursion() {
    Schema writer = parse("{"
        + "\"type\":\"record\",\"name\":\"OldNode\","
        + "\"fields\":[{\"name\":\"next\",\"type\":[\"null\",\"OldNode\"],\"default\":null}]"
        + "}");
    Schema reader = parse("{"
        + "\"type\":\"record\",\"name\":\"Node\",\"aliases\":[\"OldNode\"],"
        + "\"fields\":[{\"name\":\"next\",\"type\":[\"null\",\"Node\"],\"default\":null}]"
        + "}");

    Schema rewritten = Schema.applyAliases(writer, reader);

    assertEquals("Node", rewritten.getName());
    Schema recursiveBranch = rewritten.getField("next").schema().getTypes().get(1);
    assertSame(rewritten, recursiveBranch);
  }

  /*
   * ============================================================
   * Parser edge cases for individual schema forms
   * ============================================================
   */

  @Test
  public void parserShouldHandleRecordEnumArrayMapUnionAndFixedEdgeCases() {
    Schema record = parse("{\"type\":\"record\",\"name\":\"EmptyRecord\",\"fields\":[]}");
    assertEquals(Schema.Type.RECORD, record.getType());
    assertTrue(record.getFields().isEmpty());

    assertParseFails("{\"type\":\"record\",\"name\":\"MissingFields\"}");
    assertParseFails("{\"type\":\"record\",\"name\":\"Bad\",\"fields\":null}");
    assertParseFails("{\"type\":\"record\",\"name\":\"Bad\",\"fields\":[{\"type\":\"int\"}]}");
    assertParseFails("{\"type\":\"record\",\"name\":\"Bad\",\"fields\":[{\"name\":\"id\"}]}");

    Schema parsedEnum = parse("{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"NEW\",\"DONE\"],\"default\":\"NEW\"}");
    assertEquals("NEW", parsedEnum.getEnumDefault());
    assertParseFails("{\"type\":\"enum\",\"name\":\"Bad\",\"symbols\":[\"A\",\"A\"]}");
    assertParseFails("{\"type\":\"enum\",\"name\":\"Bad\",\"symbols\":[\"A\",1]}");
    assertParseFails("{\"type\":\"enum\",\"name\":\"Bad\",\"symbols\":[\"A\"],\"default\":\"B\"}");

    assertEquals(Schema.Type.ARRAY, parse("{\"type\":\"array\",\"items\":\"string\"}").getType());
    assertParseFails("{\"type\":\"array\"}");
    assertParseFails("{\"type\":\"array\",\"items\":null}");

    assertEquals(Schema.Type.MAP, parse("{\"type\":\"map\",\"values\":\"long\"}").getType());
    assertParseFails("{\"type\":\"map\"}");
    assertParseFails("{\"type\":\"map\",\"values\":null}");

    assertEquals(Schema.Type.UNION, parse("[\"null\",\"string\"]").getType());
    assertParseFails("[\"string\",\"string\"]");
    assertParseFails("[\"null\",[\"string\",\"int\"]]");

    assertEquals(2, parse("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":2}").getFixedSize());
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\"}");
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":\"2\"}");
    assertParseFails("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":-1}");
  }

  /*
   * ============================================================
   * SeenPair public nested class
   * ============================================================
   */

  @Test
  public void seenPairShouldUseObjectIdentityForEqualityAndHashCode() {
    Object left = new String("same-value");
    Object right = new String("same-value");
    Schema.SeenPair first = new Schema.SeenPair(left, right);
    Schema.SeenPair sameReferences = new Schema.SeenPair(left, right);
    Schema.SeenPair equalValuesDifferentReferences = new Schema.SeenPair(new String("same-value"), new String("same-value"));

    assertEquals(first, sameReferences);
    assertEquals(first.hashCode(), sameReferences.hashCode());
    assertFalse(first.equals(equalValuesDifferentReferences));
    assertFalse(first.equals("not a pair"));
  }

  /*
   * ============================================================
   * Helpers
   * ============================================================
   */

  private static void assertPrimitiveType(Schema.Type type) {
    Schema schema = Schema.create(type);
    assertEquals(type, schema.getType());
    assertEquals(type.getName(), schema.getName());
    assertEquals(type.getName(), schema.getFullName());
  }

  private static void assertCreateFails(final Schema.Type type) {
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.create(type);
      }
    });
  }

  private static Schema parse(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static JsonNode json(String json) throws IOException {
    return MAPPER.readTree(json);
  }

  private static void assertParseFails(String schemaJson) {
    try {
      new Schema.Parser().parse(schemaJson);
      fail("Expected parsing to fail for schema: " + schemaJson);
    } catch (RuntimeException expected) {
      assertNotNull(expected);
    }
  }

  private static void expectAvroRuntime(String expectedMessagePart, ThrowingRunnable runnable) {
    try {
      runnable.run();
      fail("Expected AvroRuntimeException containing: " + expectedMessagePart);
    } catch (Exception expected) {
      assertTrue("Unexpected message: " + expected.getMessage(),
          expected.getMessage() != null && expected.getMessage().contains(expectedMessagePart));
    }
  }

  private static <T extends Throwable> void expectThrows(Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
      fail("Expected exception type: " + expectedType.getName());
    } catch (Throwable actual) {
      if (!expectedType.isInstance(actual)) {
        AssertionError assertion = new AssertionError("Expected " + expectedType.getName()
            + " but got " + actual.getClass().getName());
        assertion.initCause(actual);
        throw assertion;
      }
    }
  }

  private static void writeUtf8(File file, String text) throws IOException {
    try (FileOutputStream out = new FileOutputStream(file)) {
      out.write(text.getBytes(StandardCharsets.UTF_8));
    }
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class NonClosingInputStream extends ByteArrayInputStream {
    private boolean closed;

    NonClosingInputStream(String value) {
      super(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }

    boolean wasClosed() {
      return closed;
    }
  }
}
