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

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.avro.path.TracingAvroTypeException;
import org.junit.Test;

/**
 * LLM generated few-shot tests for Schema.
 *
 * <p>This test class intentionally complements parser-heavy tests by exercising
 * the public factory methods, type-specific getters, default validation,
 * aliases, equality, JSON rendering, parser entry points and important error
 * paths of {@link Schema} directly.</p>
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class TestLLMFewShotCodeSchema {

  @Test
  public void typeNamesShouldBeLowerCaseAvroNames() {
    for (Schema.Type type : Schema.Type.values()) {
      assertEquals(type.name().toLowerCase(java.util.Locale.ENGLISH), type.getName());
    }
  }

  @Test
  public void createShouldBuildEveryPrimitiveSchemaAndRejectComplexTypes() {
    Schema.Type[] primitiveTypes = {
        Schema.Type.STRING,
        Schema.Type.BYTES,
        Schema.Type.INT,
        Schema.Type.LONG,
        Schema.Type.FLOAT,
        Schema.Type.DOUBLE,
        Schema.Type.BOOLEAN,
        Schema.Type.NULL
    };

    for (Schema.Type type : primitiveTypes) {
      Schema schema = Schema.create(type);
      assertEquals(type, schema.getType());
      assertEquals(type.getName(), schema.getName());
      assertEquals(type.getName(), schema.getFullName());
      assertNull(schema.getDoc());
      assertFalse(schema.isUnion());
      assertEquals(type == Schema.Type.NULL, schema.isNullable());
      assertEquals('"' + type.getName() + '"', schema.toString());
    }

    Schema.Type[] complexTypes = {
        Schema.Type.RECORD,
        Schema.Type.ENUM,
        Schema.Type.ARRAY,
        Schema.Type.MAP,
        Schema.Type.UNION,
        Schema.Type.FIXED
    };

    for (final Schema.Type type : complexTypes) {
      expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
        @Override
        public void run() {
          Schema.create(type);
        }
      });
    }
  }

  @Test
  public void primitiveSchemasShouldRejectTypeSpecificAccessors() {
    final Schema stringSchema = Schema.create(Schema.Type.STRING);

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getField("field");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getFields();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.hasFields();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.setFields(Collections.<Schema.Field>emptyList());
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getEnumSymbols();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getEnumDefault();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getEnumOrdinal("A");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.hasEnumSymbol("A");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getNamespace();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.addAlias("Alias");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.addAlias("Alias", "example");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getAliases();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.isError();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getElementType();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getValueType();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getTypes();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getIndexNamed("string");
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        stringSchema.getFixedSize();
      }
    });
  }

  @Test
  public void createRecordShouldExposeRecordMetadataFieldsAndPositions() {
    Schema.Field id = new Schema.Field("id", Schema.create(Schema.Type.INT), "identifier", 0);
    Schema.Field name = new Schema.Field("name", Schema.create(Schema.Type.STRING), "display name", "unknown",
        Schema.Field.Order.DESCENDING);

    Schema record = Schema.createRecord("User", "user doc", "example.avro", false, Arrays.asList(id, name));

    assertEquals(Schema.Type.RECORD, record.getType());
    assertEquals("User", record.getName());
    assertEquals("user doc", record.getDoc());
    assertEquals("example.avro", record.getNamespace());
    assertEquals("example.avro.User", record.getFullName());
    assertFalse(record.isError());
    assertTrue(record.hasFields());
    assertEquals(Arrays.asList(id, name), record.getFields());
    assertSame(id, record.getField("id"));
    assertSame(name, record.getField("name"));
    assertNull(record.getField("missing"));
    assertEquals(0, id.pos());
    assertEquals(1, name.pos());
    assertEquals(Schema.Field.Order.DESCENDING, name.order());
    assertEquals("name type:STRING pos:1", name.toString());

    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        record.getFields().add(new Schema.Field("extra", Schema.create(Schema.Type.STRING)));
      }
    });
  }

  @Test
  public void createRecordWithoutFieldsShouldAllowSetFieldsExactlyOnce() {
    final Schema record = Schema.createRecord("Event", null, "example.avro", true);
    assertFalse(record.hasFields());
    assertTrue(record.isError());

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        record.getFields();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        record.getField("id");
      }
    });

    Schema.Field field = new Schema.Field("id", Schema.create(Schema.Type.LONG));
    record.setFields(Collections.singletonList(field));
    assertTrue(record.hasFields());
    assertSame(field, record.getField("id"));

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        record.setFields(Collections.<Schema.Field>emptyList());
      }
    });
  }

  @Test
  public void createRecordShouldRejectDuplicateFieldsAndReusedFieldInstances() {
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createRecord("Duplicate", null, "example.avro", false, Arrays.asList(
            new Schema.Field("id", Schema.create(Schema.Type.INT)),
            new Schema.Field("id", Schema.create(Schema.Type.LONG))));
      }
    });

    final Schema.Field reused = new Schema.Field("id", Schema.create(Schema.Type.INT));
    Schema.createRecord("First", null, "example.avro", false, Collections.singletonList(reused));

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createRecord("Second", null, "example.avro", false, Collections.singletonList(reused));
      }
    });
  }

  @Test
  public void deprecatedAnonymousRecordFactoryShouldCreateRecordWithFields() {
    Schema.Field field = new Schema.Field("value", Schema.create(Schema.Type.STRING));
    Schema record = Schema.createRecord(Collections.singletonList(field));

    assertEquals(Schema.Type.RECORD, record.getType());
    assertNull(record.getName());
    assertNull(record.getNamespace());
    assertNull(record.getFullName());
    assertSame(field, record.getField("value"));
  }

  @Test
  public void namedSchemasShouldSupportAliasesAndQualifiedNames() {
    Schema record = Schema.createRecord("User", "doc", "example.avro", false,
        Collections.<Schema.Field>emptyList());

    assertTrue(record.getAliases().isEmpty());
    record.addAlias("LegacyUser");
    record.addAlias("GlobalUser", "");
    record.addAlias("external.ExternalUser", "ignored.namespace");

    assertTrue(record.getAliases().contains("example.avro.LegacyUser"));
    assertTrue(record.getAliases().contains(".GlobalUser"));
    assertTrue(record.getAliases().contains("external.ExternalUser"));

    expectThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        record.getAliases().add("forbidden");
      }
    });
  }

  @Test
  public void createEnumShouldExposeSymbolsDefaultAndOrdinals() {
    final Schema schema = Schema.createEnum("Status", "status doc", "example.avro",
        Arrays.asList("CREATED", "RUNNING", "DONE"), "RUNNING");

    assertEquals(Schema.Type.ENUM, schema.getType());
    assertEquals("Status", schema.getName());
    assertEquals("status doc", schema.getDoc());
    assertEquals("example.avro", schema.getNamespace());
    assertEquals("example.avro.Status", schema.getFullName());
    assertEquals(Arrays.asList("CREATED", "RUNNING", "DONE"), schema.getEnumSymbols());
    assertEquals("RUNNING", schema.getEnumDefault());
    assertTrue(schema.hasEnumSymbol("DONE"));
    assertFalse(schema.hasEnumSymbol("MISSING"));
    assertEquals(0, schema.getEnumOrdinal("CREATED"));
    assertEquals(1, schema.getEnumOrdinal("RUNNING"));
    assertEquals(2, schema.getEnumOrdinal("DONE"));

    expectThrows(TracingAvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        schema.getEnumOrdinal("MISSING");
      }
    });
    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        schema.getEnumSymbols().add("NEW");
      }
    });
  }

  @Test
  public void createEnumShouldRejectDuplicateSymbolsInvalidSymbolsAndInvalidDefault() {
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createEnum("BadEnum", null, "example.avro", Arrays.asList("A", "A"));
      }
    });
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createEnum("BadEnum", null, "example.avro", Arrays.asList("1INVALID"));
      }
    });
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createEnum("BadEnum", null, "example.avro", Arrays.asList("A"), "B");
      }
    });
  }

  @Test
  public void createArrayAndMapShouldExposeNestedTypes() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    final Schema array = Schema.createArray(stringSchema);
    final Schema map = Schema.createMap(stringSchema);

    assertEquals(Schema.Type.ARRAY, array.getType());
    assertSame(stringSchema, array.getElementType());
    assertEquals("array", array.getName());
    assertFalse(array.isUnion());
    assertFalse(array.isNullable());

    assertEquals(Schema.Type.MAP, map.getType());
    assertSame(stringSchema, map.getValueType());
    assertEquals("map", map.getName());
    assertFalse(map.isUnion());
    assertFalse(map.isNullable());

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        array.getValueType();
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        map.getElementType();
      }
    });
  }

  @Test
  public void createUnionShouldExposeBranchesIndexesNameAndNullability() {
    Schema nullSchema = Schema.create(Schema.Type.NULL);
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    final Schema union = Schema.createUnion(nullSchema, stringSchema);

    assertEquals(Schema.Type.UNION, union.getType());
    assertTrue(union.isUnion());
    assertTrue(union.isNullable());
    assertEquals(Arrays.asList(nullSchema, stringSchema), union.getTypes());
    assertEquals(Integer.valueOf(0), union.getIndexNamed("null"));
    assertEquals(Integer.valueOf(1), union.getIndexNamed("string"));
    assertNull(union.getIndexNamed("missing"));
    assertEquals("union[null, string]", union.getName());

    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        union.getTypes().add(Schema.create(Schema.Type.INT));
      }
    });
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        union.addProp("x", "y");
      }
    });
  }

  @Test
  public void createUnionShouldRejectNestedUnionsDuplicateBranchesAndNamelessBranches() {
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createUnion(Schema.create(Schema.Type.STRING), Schema.create(Schema.Type.STRING));
      }
    });

    final Schema nested = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createUnion(nested, Schema.create(Schema.Type.INT));
      }
    });

    final Schema anonymousRecord = Schema.createRecord(Collections.singletonList(
        new Schema.Field("id", Schema.create(Schema.Type.INT))));
    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.createUnion(anonymousRecord, Schema.create(Schema.Type.STRING));
      }
    });
  }

  @Test
  public void createFixedShouldExposeSizeAndNamedMetadata() {
    Schema fixed = Schema.createFixed("Md5", "hash doc", "example.avro", 16);

    assertEquals(Schema.Type.FIXED, fixed.getType());
    assertEquals("Md5", fixed.getName());
    assertEquals("hash doc", fixed.getDoc());
    assertEquals("example.avro", fixed.getNamespace());
    assertEquals("example.avro.Md5", fixed.getFullName());
    assertEquals(16, fixed.getFixedSize());
  }

  @Test
  public void fieldConstructorsShouldExposeMetadataDefaultsOrderAliasesAndCopy() {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    Schema.Field field = new Schema.Field("name", stringSchema, "field doc", "alice", Schema.Field.Order.IGNORE);
    field.addAlias("full_name");
    field.addProp("x-field-prop", "field-prop-value");

    assertEquals("name", field.name());
    assertEquals(-1, field.pos());
    assertSame(stringSchema, field.schema());
    assertEquals("field doc", field.doc());
    assertTrue(field.hasDefaultValue());
    assertEquals("alice", field.defaultVal());
    assertEquals(Schema.Field.Order.IGNORE, field.order());
    assertTrue(field.aliases().contains("full_name"));
    assertEquals("field-prop-value", field.getProp("x-field-prop"));

    Schema.Field copy = new Schema.Field(field, Schema.create(Schema.Type.BYTES));
    assertEquals("name", copy.name());
    assertEquals(Schema.Type.BYTES, copy.schema().getType());
    assertEquals("field doc", copy.doc());
    assertEquals("alice", copy.defaultVal());
    assertEquals(Schema.Field.Order.IGNORE, copy.order());
    assertTrue(copy.aliases().contains("full_name"));
    assertEquals("field-prop-value", copy.getProp("x-field-prop"));

    expectThrows(UnsupportedOperationException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        field.aliases().add("forbidden");
      }
    });
  }

  @Test
  public void fieldShouldSupportNoDefaultAndExplicitNullDefaultForNullableUnion() {
    Schema.Field noDefault = new Schema.Field("plain", Schema.create(Schema.Type.STRING));
    assertFalse(noDefault.hasDefaultValue());
    assertNull(noDefault.defaultVal());
    assertTrue(noDefault.aliases().isEmpty());

    Schema nullableString = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    Schema.Field explicitNull = new Schema.Field("maybe", nullableString, null, Schema.Field.NULL_DEFAULT_VALUE);
    assertTrue(explicitNull.hasDefaultValue());
    assertNull(explicitNull.defaultVal());
  }

  @Test
  public void fieldShouldRejectInvalidConstructorArgumentsAndInvalidDefaultsWhenValidationIsEnabled() {
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("1bad", Schema.create(Schema.Type.STRING));
      }
    });
    expectThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("field", null);
      }
    });
    expectThrows(NullPointerException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("field", Schema.create(Schema.Type.STRING), null, "value", null);
      }
    });
    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Field("count", Schema.create(Schema.Type.INT), null, "not-an-int");
      }
    });
  }

  @Test
  public void fieldEqualityShouldConsiderNameSchemaDefaultOrderAndProperties() {
    Schema doubleSchema = Schema.create(Schema.Type.DOUBLE);
    Schema.Field left = new Schema.Field("score", doubleSchema, "left doc", Double.NaN, Schema.Field.Order.ASCENDING);
    Schema.Field right = new Schema.Field("score", doubleSchema, "right doc", Double.NaN, Schema.Field.Order.ASCENDING);

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    right.addProp("x", "y");
    assertFalse(left.equals(right));
    assertFalse(left.hashCode() == 0 && right.hashCode() == 0);
  }

  @Test
  public void schemaEqualityAndHashCodeShouldIncludeTypeNestedSchemasNamesAndProperties() {
    Schema left = Schema.createArray(Schema.create(Schema.Type.STRING));
    Schema right = Schema.createArray(Schema.create(Schema.Type.STRING));
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    right.addProp("x-array-prop", "value");
    assertFalse(left.equals(right));

    left.addProp("x-array-prop", "value");
    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());

    Schema recordOne = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema recordTwo = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema recordThree = Schema.createRecord("Account", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    assertEquals(recordOne, recordTwo);
    assertEquals(recordOne.hashCode(), recordTwo.hashCode());
    assertFalse(recordOne.equals(recordThree));
    assertFalse(recordOne.equals("not a schema"));
  }

  @Test
  public void recursiveRecordEqualityAndHashCodeShouldAvoidInfiniteRecursion() {
    Schema left = recursiveNodeSchema();
    Schema right = recursiveNodeSchema();

    assertEquals(left, right);
    assertEquals(left.hashCode(), right.hashCode());
    assertEquals(left, new Schema.Parser().parse(left.toString()));
  }

  @Test
  public void addPropShouldStoreCustomPropertiesResetHashAndRenderInJson() {
    Schema schema = Schema.create(Schema.Type.STRING);
    Schema same = Schema.create(Schema.Type.STRING);
    assertEquals(schema, same);

    schema.addProp("x-string-prop", "text");
    schema.addProp("x-number-prop", 7);

    assertEquals("text", schema.getProp("x-string-prop"));
    assertFalse(schema.equals(same));
    assertTrue(schema.toString().contains("\"x-string-prop\":\"text\""));
    assertTrue(schema.toString().contains("\"x-number-prop\":7"));

    same.addProp("x-string-prop", "text");
    same.addProp("x-number-prop", 7);
    assertEquals(schema, same);
    assertEquals(schema.hashCode(), same.hashCode());
  }

  @Test
  public void logicalTypeShouldBeReadableWhenAddedOrParsed() {
    Schema added = LogicalTypes.uuid().addToSchema(Schema.create(Schema.Type.STRING));
    assertNotNull(added.getLogicalType());
    assertEquals("uuid", added.getLogicalType().getName());

    Schema parsed = new Schema.Parser().parse("{\"type\":\"int\",\"logicalType\":\"date\"}");
    assertNotNull(parsed.getLogicalType());
    assertEquals("date", parsed.getLogicalType().getName());
  }

  @Test
  public void toStringShouldRenderInlinePrettyAndReferencedSchemas() {
    Schema address = Schema.createRecord("Address", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("street", Schema.create(Schema.Type.STRING))));
    Schema user = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("address", address)));

    String inline = user.toString();
    assertTrue(inline.contains("\"name\":\"User\""));
    assertTrue(inline.contains("\"name\":\"Address\""));
    assertEquals(user, new Schema.Parser().parse(inline));

    String pretty = user.toString(true);
    assertTrue(pretty.contains(System.lineSeparator()) || pretty.contains("\n"));
    assertEquals(user, new Schema.Parser().parse(pretty));

    String withReference = user.toString(Collections.singleton(address), false);
    assertTrue(withReference.contains("\"type\":\"example.avro.Address\""));
  }

  @Test
  public void packagePrivateToJsonAndFieldsToJsonShouldMatchDefaultBehaviors() throws IOException {
    Schema stringSchema = Schema.create(Schema.Type.STRING);
    StringWriter writer = new StringWriter();
    JsonGenerator generator = Schema.FACTORY.createGenerator(writer);
    stringSchema.toJson(new HashSet<String>(), null, generator);
    generator.flush();
    assertEquals("\"string\"", writer.toString());

    expectThrows(AvroRuntimeException.class, new ThrowingRunnable() {
      @Override
      public void run() throws IOException {
        JsonGenerator g = Schema.FACTORY.createGenerator(new StringWriter());
        stringSchema.fieldsToJson(new HashSet<String>(), null, g);
      }
    });
  }

  @Test
  public void parserShouldParseFromStringPartsFileInputStreamAndInternalString() throws Exception {
    Schema fromParts = new Schema.Parser().parse("{\"type\":\"", "string", "\"}");
    assertEquals(Schema.Type.STRING, fromParts.getType());

    File file = File.createTempFile("schema", ".avsc");
    Files.write(file.toPath(), "{\"type\":\"long\"}".getBytes(StandardCharsets.UTF_8));
    Schema fromFile = new Schema.Parser().parse(file);
    assertEquals(Schema.Type.LONG, fromFile.getType());

    CloseAwareInputStream input = new CloseAwareInputStream("\"int\" {\"type\":\"string\"}");
    Schema fromStream = new Schema.Parser().parse(input);
    assertEquals(Schema.Type.INT, fromStream.getType());
    assertFalse(input.closed);

    Schema internal = new Schema.Parser().parseInternal("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":4}");
    assertEquals(Schema.Type.FIXED, internal.getType());
    assertEquals(4, internal.getFixedSize());
  }

  @Test
  public void parserShouldRejectDanglingContentWhenParsingString() {
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Parser().parse("\"string\" \"int\"");
      }
    });
  }

  @Test
  public void parserShouldRegisterAndResolveAddedTypes() {
    Schema address = Schema.createRecord("Address", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("street", Schema.create(Schema.Type.STRING))));

    Schema.Parser parser = new Schema.Parser();
    assertSame(parser, parser.addTypes(Collections.singletonList(address)));
    assertTrue(parser.getTypes().containsKey("example.avro.Address"));

    Schema array = parser.parse("{\"type\":\"array\",\"items\":\"example.avro.Address\"}");
    assertEquals(Schema.Type.ARRAY, array.getType());
    assertEquals(address, array.getElementType());

    Schema.Parser mapParser = new Schema.Parser();
    mapParser.addTypes(Collections.singletonMap(address.getFullName(), address));
    Schema map = mapParser.parse("{\"type\":\"map\",\"values\":\"example.avro.Address\"}");
    assertEquals(address, map.getValueType());
  }

  @Test
  public void parserValidateDefaultsFlagShouldControlDefaultValidation() {
    final String invalidDefaultRecord = "{"
        + "\"type\":\"record\","
        + "\"name\":\"InvalidDefault\","
        + "\"fields\":[{\"name\":\"count\",\"type\":\"int\",\"default\":\"bad\"}]"
        + "}";

    expectThrows(AvroTypeException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Parser().parse(invalidDefaultRecord);
      }
    });

    Schema.Parser parser = new Schema.Parser();
    assertTrue(parser.getValidateDefaults());
    assertSame(parser, parser.setValidateDefaults(false));
    assertFalse(parser.getValidateDefaults());
    Schema schema = parser.parse(invalidDefaultRecord);
    assertEquals("bad", schema.getField("count").defaultVal());
  }

  @Test
  public void parserConstructorsShouldHonorNameValidators() {
    final String invalidUtfName = "{\"type\":\"record\",\"name\":\"1Invalid\",\"fields\":[]}";

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        new Schema.Parser().parse(invalidUtfName);
      }
    });

    Schema parsedWithNoValidation = new Schema.Parser((NameValidator) null).parse(invalidUtfName);
    assertEquals("1Invalid", parsedWithNoValidation.getName());
  }

  @Test
  public void deprecatedStaticParseMethodsShouldDelegateToParser() throws Exception {
    Schema fromString = Schema.parse("{\"type\":\"boolean\"}");
    assertEquals(Schema.Type.BOOLEAN, fromString.getType());

    File file = File.createTempFile("static-schema", ".avsc");
    Files.write(file.toPath(), "{\"type\":\"double\"}".getBytes(StandardCharsets.UTF_8));
    Schema fromFile = Schema.parse(file);
    assertEquals(Schema.Type.DOUBLE, fromFile.getType());

    InputStream in = new ByteArrayInputStream("{\"type\":\"float\"}".getBytes(StandardCharsets.UTF_8));
    Schema fromStream = Schema.parse(in);
    assertEquals(Schema.Type.FLOAT, fromStream.getType());

    Schema invalidNameAccepted = Schema.parse("{\"type\":\"record\",\"name\":\"1Invalid\",\"fields\":[]}", false);
    assertEquals("1Invalid", invalidNameAccepted.getName());

    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        Schema.parse("{\"type\":\"record\",\"name\":\"1Invalid\",\"fields\":[]}", true);
      }
    });
  }

  @Test
  public void deprecatedThreadLocalValidationControlsShouldAffectManualConstruction() {
    NameValidator savedValidator = Schema.getNameValidator();
    boolean savedValidateDefaults = Schema.getValidateDefaults();
    try {
      Schema.setNameValidator(NameValidator.NO_VALIDATION);
      Schema invalidName = Schema.createRecord("1Invalid", null, null, false,
          Collections.<Schema.Field>emptyList());
      assertEquals("1Invalid", invalidName.getName());

      Schema.setValidateDefaults(false);
      Schema.Field invalidDefault = new Schema.Field("count", Schema.create(Schema.Type.INT), null, "bad");
      assertEquals("bad", invalidDefault.defaultVal());
    } finally {
      Schema.setNameValidator(savedValidator);
      Schema.setValidateDefaults(savedValidateDefaults);
    }
  }

  @Test
  public void isValidDefaultShouldCoverPrimitiveCollectionUnionAndRecordDefaults() {
    assertTrue(Schema.create(Schema.Type.STRING).isValidDefault(Schema.parseJson("\"text\"")));
    assertFalse(Schema.create(Schema.Type.STRING).isValidDefault(Schema.parseJson("1")));
    assertTrue(Schema.create(Schema.Type.INT).isValidDefault(Schema.parseJson("2147483647")));
    assertFalse(Schema.create(Schema.Type.INT).isValidDefault(Schema.parseJson("2147483648")));
    assertTrue(Schema.create(Schema.Type.LONG).isValidDefault(Schema.parseJson("2147483648")));
    assertTrue(Schema.create(Schema.Type.FLOAT).isValidDefault(Schema.parseJson("1.25")));
    assertTrue(Schema.create(Schema.Type.DOUBLE).isValidDefault(Schema.parseJson("1.25")));
    assertTrue(Schema.create(Schema.Type.BOOLEAN).isValidDefault(Schema.parseJson("true")));
    assertTrue(Schema.create(Schema.Type.NULL).isValidDefault(Schema.parseJson("null")));

    Schema array = Schema.createArray(Schema.create(Schema.Type.INT));
    assertTrue(array.isValidDefault(Schema.parseJson("[1,2,3]")));
    assertFalse(array.isValidDefault(Schema.parseJson("[1,\"bad\"]")));

    Schema map = Schema.createMap(Schema.create(Schema.Type.BOOLEAN));
    assertTrue(map.isValidDefault(Schema.parseJson("{\"a\":true,\"b\":false}")));
    assertFalse(map.isValidDefault(Schema.parseJson("{\"a\":\"bad\"}")));

    Schema union = Schema.createUnion(Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.STRING));
    assertTrue(union.isValidDefault(Schema.parseJson("null")));
    assertTrue(union.isValidDefault(Schema.parseJson("\"text\"")));
    assertFalse(union.isValidDefault(Schema.parseJson("1")));

    Schema record = Schema.createRecord("WithDefault", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT), null, 1)));
    assertTrue(record.isValidDefault(Schema.parseJson("{}")));
    assertTrue(record.isValidDefault(Schema.parseJson("{\"id\":2}")));
    assertFalse(record.isValidDefault(Schema.parseJson("{\"id\":\"bad\"}")));
  }

  @Test
  public void parseJsonToObjectShouldReturnJavaObjects() {
    Object parsed = Schema.parseJsonToObject("{\"name\":\"alice\",\"scores\":[1,2],\"active\":true}");
    assertTrue(parsed instanceof Map);

    Map<String, Object> map = (Map<String, Object>) parsed;
    assertEquals("alice", map.get("name"));
    assertEquals(Arrays.asList(1, 2), map.get("scores"));
    assertEquals(Boolean.TRUE, map.get("active"));
  }

  @Test
  public void applyAliasesShouldReturnSameWriterWhenNoAliasesExist() {
    Schema writer = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    Schema reader = Schema.createRecord("User", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    assertSame(writer, Schema.applyAliases(writer, reader));
  }

  @Test
  public void applyAliasesShouldRewriteNamedSchemasAndFieldNames() {
    Schema writer = Schema.createRecord("OldUser", null, "example.avro", false,
        Arrays.asList(
            new Schema.Field("oldId", Schema.create(Schema.Type.INT)),
            new Schema.Field("name", Schema.create(Schema.Type.STRING))));

    Schema.Field newId = new Schema.Field("newId", Schema.create(Schema.Type.INT));
    newId.addAlias("oldId");
    Schema reader = Schema.createRecord("NewUser", null, "example.avro", false,
        Arrays.asList(newId, new Schema.Field("name", Schema.create(Schema.Type.STRING))));
    reader.addAlias("OldUser");

    Schema rewritten = Schema.applyAliases(writer, reader);
    assertEquals("example.avro.NewUser", rewritten.getFullName());
    assertNotNull(rewritten.getField("newId"));
    assertNull(rewritten.getField("oldId"));
    assertEquals(Schema.Type.INT, rewritten.getField("newId").schema().getType());
  }

  @Test
  public void applyAliasesShouldRewriteAliasesInsideArraysMapsUnionsEnumsAndFixed() {
    Schema writerEnum = Schema.createEnum("OldEnum", null, "example.avro", Arrays.asList("A", "B"), "A");
    Schema readerEnum = Schema.createEnum("NewEnum", null, "example.avro", Arrays.asList("A", "B"), "A");
    readerEnum.addAlias("OldEnum");
    assertEquals("example.avro.NewEnum", Schema.applyAliases(writerEnum, readerEnum).getFullName());

    Schema writerFixed = Schema.createFixed("OldFixed", null, "example.avro", 8);
    Schema readerFixed = Schema.createFixed("NewFixed", null, "example.avro", 8);
    readerFixed.addAlias("OldFixed");
    assertEquals("example.avro.NewFixed", Schema.applyAliases(writerFixed, readerFixed).getFullName());

    Schema writerArray = Schema.createArray(writerFixed);
    Schema readerArray = Schema.createArray(readerFixed);
    assertEquals("example.avro.NewFixed", Schema.applyAliases(writerArray, readerArray)
        .getElementType().getFullName());

    Schema writerMap = Schema.createMap(writerFixed);
    Schema readerMap = Schema.createMap(readerFixed);
    assertEquals("example.avro.NewFixed", Schema.applyAliases(writerMap, readerMap)
        .getValueType().getFullName());

    Schema writerUnion = Schema.createUnion(Schema.create(Schema.Type.NULL), writerFixed);
    Schema readerUnion = Schema.createUnion(Schema.create(Schema.Type.NULL), readerFixed);
    assertEquals("example.avro.NewFixed", Schema.applyAliases(writerUnion, readerUnion)
        .getTypes().get(1).getFullName());
  }

  @Test
  public void schemaShouldSerializeAndDeserializeThroughWriteReplace() throws Exception {
    Schema original = Schema.createRecord("SerializableUser", null, "example.avro", false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
    objectOutputStream.writeObject(original);
    objectOutputStream.close();

    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
    Object deserialized = objectInputStream.readObject();

    assertTrue(deserialized instanceof Schema);
    assertEquals(original, deserialized);
  }

  @Test
  public void seenPairShouldUseObjectIdentityForEqualityAndHashCode() {
    Object first = new Object();
    Object second = new Object();
    Schema.SeenPair pair = new Schema.SeenPair(first, second);

    assertEquals(pair, new Schema.SeenPair(first, second));
    assertFalse(pair.equals(new Schema.SeenPair(new Object(), second)));
    assertFalse(pair.equals("not a pair"));
    assertEquals(System.identityHashCode(first) + System.identityHashCode(second), pair.hashCode());
  }

  @Test
  public void lockableArrayListShouldRejectMutationsAfterLock() {
    final Schema.LockableArrayList<String> list = new Schema.LockableArrayList<String>();
    list.add("a");
    List<String> locked = list.lock();
    assertSame(list, locked);
    assertEquals(Collections.singletonList("a"), locked);

    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        list.add("b");
      }
    });
    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        list.remove("a");
      }
    });
    expectThrows(IllegalStateException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        list.clear();
      }
    });
  }

  @Test
  public void namesShouldResolvePrimitivesDefaultNamespaceAndPreventRedefinition() {
    Schema.Names names = new Schema.Names("example.avro");
    assertEquals("example.avro", names.space());
    names.space("other.avro");
    assertEquals("other.avro", names.space());
    assertEquals(Schema.Type.STRING, names.get("string").getType());

    Schema record = Schema.createRecord("other.avro.User", null, null, false,
        Collections.<Schema.Field>emptyList());
    names.add(record);
    assertTrue(names.contains(record));
    assertSame(record, names.get("User"));
    assertSame(record, names.put(new Schema.Name("other.avro.User", null), record));

    final Schema conflicting = Schema.createRecord("other.avro.User", null, null, false,
        Collections.singletonList(new Schema.Field("id", Schema.create(Schema.Type.INT))));
    expectThrows(SchemaParseException.class, new ThrowingRunnable() {
      @Override
      public void run() {
        names.put(new Schema.Name("other.avro.User", null), conflicting);
      }
    });
  }

  private static Schema recursiveNodeSchema() {
    Schema node = Schema.createRecord("Node", null, "example.avro", false);
    Schema nullableNode = Schema.createUnion(Schema.create(Schema.Type.NULL), node);
    node.setFields(Arrays.asList(
        new Schema.Field("value", Schema.create(Schema.Type.INT)),
        new Schema.Field("next", nullableNode, null, Schema.Field.NULL_DEFAULT_VALUE)));
    return node;
  }

  private static void expectThrows(Class<? extends Throwable> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
      fail("Expected " + expectedType.getName() + " to be thrown");
    } catch (Throwable throwable) {
      if (!expectedType.isInstance(throwable)) {
        AssertionError assertionError = new AssertionError(
            "Expected " + expectedType.getName() + " but got " + throwable.getClass().getName());
        assertionError.initCause(throwable);
        throw assertionError;
      }
    }
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class CloseAwareInputStream extends ByteArrayInputStream {
    private boolean closed;

    private CloseAwareInputStream(String value) {
      super(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
