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
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Additional black-box tests for Schema default validation and alias rewriting.
 */
public class TestSchema_J2 {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  /*
   * ============================================================
   * isValidDefault(JsonNode)
   * ============================================================
   */

  @Test
  public void isValidDefaultShouldValidateNullDefaults() throws Exception {
    Schema nullSchema = Schema.create(Schema.Type.NULL);

    assertTrue(nullSchema.isValidDefault(json("null")));
    assertFalse(nullSchema.isValidDefault(json("\"value\"")));
    assertFalse(nullSchema.isValidDefault(json("1")));
  }

  @Test
  public void isValidDefaultShouldValidateBooleanDefaults() throws Exception {
    Schema booleanSchema = Schema.create(Schema.Type.BOOLEAN);

    assertTrue(booleanSchema.isValidDefault(json("true")));
    assertTrue(booleanSchema.isValidDefault(json("false")));
    assertFalse(booleanSchema.isValidDefault(json("\"true\"")));
    assertFalse(booleanSchema.isValidDefault(json("1")));
  }

  @Test
  public void isValidDefaultShouldValidateIntDefaults() throws Exception {
    Schema intSchema = Schema.create(Schema.Type.INT);

    assertTrue(intSchema.isValidDefault(json("1")));
    assertTrue(intSchema.isValidDefault(json("0")));
    assertFalse(intSchema.isValidDefault(json("2147483648")));
    assertFalse(intSchema.isValidDefault(json("\"1\"")));
  }

  @Test
  public void isValidDefaultShouldValidateLongDefaults() throws Exception {
    Schema longSchema = Schema.create(Schema.Type.LONG);

    assertTrue(longSchema.isValidDefault(json("1")));
    assertTrue(longSchema.isValidDefault(json("2147483648")));
    assertFalse(longSchema.isValidDefault(json("\"2147483648\"")));
  }

  @Test
  public void isValidDefaultShouldValidateFloatAndDoubleDefaults() throws Exception {
    Schema floatSchema = Schema.create(Schema.Type.FLOAT);
    Schema doubleSchema = Schema.create(Schema.Type.DOUBLE);

    assertTrue(floatSchema.isValidDefault(json("1")));
    assertTrue(floatSchema.isValidDefault(json("1.5")));
    assertFalse(floatSchema.isValidDefault(json("\"1.5\"")));

    assertTrue(doubleSchema.isValidDefault(json("1")));
    assertTrue(doubleSchema.isValidDefault(json("1.5")));
    assertFalse(doubleSchema.isValidDefault(json("\"1.5\"")));
  }

  @Test
  public void isValidDefaultShouldValidateStringDefaults() throws Exception {
    Schema stringSchema = Schema.create(Schema.Type.STRING);

    assertTrue(stringSchema.isValidDefault(json("\"value\"")));
    assertTrue(stringSchema.isValidDefault(json("\"\"")));
    assertFalse(stringSchema.isValidDefault(json("1")));
    assertFalse(stringSchema.isValidDefault(json("{\"x\":1}")));
  }

  @Test
  public void isValidDefaultShouldValidateBytesDefaultsAsTextualValues() throws Exception {
    Schema bytesSchema = Schema.create(Schema.Type.BYTES);

    assertTrue(bytesSchema.isValidDefault(json("\"ab\"")));
    assertTrue(bytesSchema.isValidDefault(json("\"\"")));
    assertFalse(bytesSchema.isValidDefault(json("1")));
    assertFalse(bytesSchema.isValidDefault(json("[1,2]")));
  }

  @Test
  public void isValidDefaultShouldValidateEnumDefaultsAsTextualValues() throws Exception {
    Schema enumSchema = parse("{\"type\":\"enum\",\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"DONE\"]}");

    assertTrue(enumSchema.isValidDefault(json("\"NEW\"")));
    assertTrue("Schema.isValidDefault checks the JSON shape of enum defaults as textual values.",
        enumSchema.isValidDefault(json("\"MISSING\"")));
    assertFalse(enumSchema.isValidDefault(json("1")));
    assertFalse(enumSchema.isValidDefault(json("null")));
  }

  @Test
  public void isValidDefaultShouldValidateFixedDefaultsAsTextualValues() throws Exception {
    Schema fixedSchema = parse("{\"type\":\"fixed\",\"name\":\"Hash\",\"size\":2}");

    assertTrue(fixedSchema.isValidDefault(json("\"ab\"")));
    assertTrue("Schema.isValidDefault treats fixed defaults as textual JSON values.",
        fixedSchema.isValidDefault(json("\"abc\"")));
    assertFalse(fixedSchema.isValidDefault(json("1")));
    assertFalse(fixedSchema.isValidDefault(json("{\"x\":1}")));
  }

  @Test
  public void isValidDefaultShouldValidateArrayDefaultsRecursively() throws Exception {
    Schema arraySchema = parse("{\"type\":\"array\",\"items\":\"int\"}");

    assertTrue(arraySchema.isValidDefault(json("[]")));
    assertTrue(arraySchema.isValidDefault(json("[1,2,3]")));
    assertFalse(arraySchema.isValidDefault(json("[1,\"wrong\"]")));
    assertFalse(arraySchema.isValidDefault(json("{\"a\":1}")));
  }

  @Test
  public void isValidDefaultShouldValidateMapDefaultsRecursively() throws Exception {
    Schema mapSchema = parse("{\"type\":\"map\",\"values\":\"int\"}");

    assertTrue(mapSchema.isValidDefault(json("{}")));
    assertTrue(mapSchema.isValidDefault(json("{\"a\":1,\"b\":2}")));
    assertFalse(mapSchema.isValidDefault(json("{\"a\":1,\"b\":\"wrong\"}")));
    assertFalse(mapSchema.isValidDefault(json("[1,2]")));
  }

  @Test
  public void isValidDefaultShouldValidateRecordDefaultsRecursively() throws Exception {
    Schema recordSchema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\"},"
        + "{\"name\":\"label\",\"type\":\"string\"}"
        + "]}");

    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"label\":\"ok\"}")));
    assertTrue(recordSchema.isValidDefault(json("{\"id\":1,\"label\":\"ok\",\"extra\":true}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":1}")));
    assertFalse(recordSchema.isValidDefault(json("{\"id\":\"wrong\",\"label\":\"ok\"}")));
    assertFalse(recordSchema.isValidDefault(json("[1,\"ok\"]")));
  }

  @Test
  public void isValidDefaultShouldValidateUnionDefaultsAgainstAnyBranch() throws Exception {
    Schema unionSchema = parse("[\"null\",\"string\"]");

    assertTrue(unionSchema.isValidDefault(json("null")));
    assertTrue(unionSchema.isValidDefault(json("\"value\"")));
    assertFalse(unionSchema.isValidDefault(json("1")));
    assertFalse(unionSchema.isValidDefault(json("{\"x\":1}")));
  }

  @Test
  public void isValidDefaultShouldRejectEveryDefaultForEmptyUnion() throws Exception {
    Schema emptyUnionSchema = parse("[]");

    assertFalse(emptyUnionSchema.isValidDefault(json("null")));
    assertFalse(emptyUnionSchema.isValidDefault(json("\"value\"")));
    assertFalse(emptyUnionSchema.isValidDefault(json("1")));
  }

  @Test
  public void isValidDefaultShouldValidateNestedCompositeDefaults() throws Exception {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"Container\","
        + "\"fields\":["
        + "{\"name\":\"values\",\"type\":{\"type\":\"array\",\"items\":\"long\"}},"
        + "{\"name\":\"metadata\",\"type\":{\"type\":\"map\",\"values\":\"string\"}}"
        + "]}");

    assertTrue(schema.isValidDefault(json("{\"values\":[1,2],\"metadata\":{\"a\":\"x\"}}")));
    assertFalse(schema.isValidDefault(json("{\"values\":[1,\"wrong\"],\"metadata\":{\"a\":\"x\"}}")));
    assertFalse(schema.isValidDefault(json("{\"values\":[1,2],\"metadata\":{\"a\":1}}")));
  }

  /*
   * ============================================================
   * Schema.applyAliases(writer, reader)
   * ============================================================
   */

  @Test
  public void applyAliasesShouldLeaveRecordWithoutAliasesUnchanged() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("User", result.getName());
    assertNotNull(result.getField("id"));
    assertEquals(Schema.Type.INT, result.getField("id").schema().getType());
  }

  @Test
  public void applyAliasesShouldRewriteTopLevelRecordNameFromSimpleAlias() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"aliases\":[\"OldUser\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("User", result.getName());
    assertEquals("User", result.getFullName());
    assertNotNull(result.getField("id"));
  }

  @Test
  public void applyAliasesShouldRewriteTopLevelRecordFullNameFromFullAlias() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"old.ns\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"new.ns\","
        + "\"aliases\":[\"old.ns.User\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("User", result.getName());
    assertEquals("new.ns", result.getNamespace());
    assertEquals("new.ns.User", result.getFullName());
  }

  @Test
  public void applyAliasesShouldNotRewriteRecordWhenAliasDoesNotMatch() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"aliases\":[\"LegacyUser\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("OldUser", result.getName());
    assertEquals("OldUser", result.getFullName());
  }

  @Test
  public void applyAliasesShouldRewriteRecordFieldNameFromFieldAlias() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"oldId\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"aliases\":[\"oldId\"]}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertNull(result.getField("oldId"));
    assertNotNull(result.getField("id"));
    assertEquals(Schema.Type.INT, result.getField("id").schema().getType());
  }

  @Test
  public void applyAliasesShouldNotRewriteFieldWhenFieldAliasDoesNotMatch() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"oldId\",\"type\":\"int\"}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"aliases\":[\"legacyId\"]}]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertNotNull(result.getField("oldId"));
    assertNull(result.getField("id"));
    assertEquals(Schema.Type.INT, result.getField("oldId").schema().getType());
  }

  @Test
  public void applyAliasesShouldRewriteEnumNameAndPreserveSymbols() {
    Schema writer = parse("{\"type\":\"enum\",\"name\":\"OldStatus\","
        + "\"symbols\":[\"NEW\",\"DONE\"]}");
    Schema reader = parse("{\"type\":\"enum\",\"name\":\"Status\","
        + "\"aliases\":[\"OldStatus\"],"
        + "\"symbols\":[\"NEW\",\"DONE\"]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.ENUM, result.getType());
    assertEquals("Status", result.getName());
    assertEquals(Arrays.asList("NEW", "DONE"), result.getEnumSymbols());
  }

  @Test
  public void applyAliasesShouldRewriteFixedNameAndPreserveSize() {
    Schema writer = parse("{\"type\":\"fixed\",\"name\":\"OldHash\",\"size\":16}");
    Schema reader = parse("{\"type\":\"fixed\",\"name\":\"Hash\","
        + "\"aliases\":[\"OldHash\"],\"size\":16}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.FIXED, result.getType());
    assertEquals("Hash", result.getName());
    assertEquals(16, result.getFixedSize());
  }

  @Test
  public void applyAliasesShouldRewriteNestedRecordType() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"address\",\"type\":{"
        + "\"type\":\"record\",\"name\":\"OldAddress\","
        + "\"fields\":[{\"name\":\"street\",\"type\":\"string\"}]}}]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"address\",\"type\":{"
        + "\"type\":\"record\",\"name\":\"Address\",\"aliases\":[\"OldAddress\"],"
        + "\"fields\":[{\"name\":\"street\",\"type\":\"string\"}]}}]}");

    Schema result = Schema.applyAliases(writer, reader);
    Schema nested = result.getField("address").schema();

    assertEquals(Schema.Type.RECORD, nested.getType());
    assertEquals("Address", nested.getName());
    assertNotNull(nested.getField("street"));
  }

  @Test
  public void applyAliasesShouldRewriteArrayItemType() {
    Schema writer = parse("{\"type\":\"array\",\"items\":{"
        + "\"type\":\"record\",\"name\":\"OldItem\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}}");
    Schema reader = parse("{\"type\":\"array\",\"items\":{"
        + "\"type\":\"record\",\"name\":\"Item\",\"aliases\":[\"OldItem\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.ARRAY, result.getType());
    assertEquals("Item", result.getElementType().getName());
  }

  @Test
  public void applyAliasesShouldRewriteMapValueType() {
    Schema writer = parse("{\"type\":\"map\",\"values\":{"
        + "\"type\":\"record\",\"name\":\"OldValue\","
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]}}");
    Schema reader = parse("{\"type\":\"map\",\"values\":{"
        + "\"type\":\"record\",\"name\":\"Value\",\"aliases\":[\"OldValue\"],"
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]}}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.MAP, result.getType());
    assertEquals("Value", result.getValueType().getName());
  }

  @Test
  public void applyAliasesShouldRewriteUnionBranchType() {
    Schema writer = parse("[\"null\",{"
        + "\"type\":\"record\",\"name\":\"OldEvent\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}]");
    Schema reader = parse("[\"null\",{"
        + "\"type\":\"record\",\"name\":\"Event\",\"aliases\":[\"OldEvent\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}]");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.UNION, result.getType());
    assertEquals(Schema.Type.NULL, result.getTypes().get(0).getType());
    assertEquals("Event", result.getTypes().get(1).getName());
  }

  @Test
  public void applyAliasesShouldLeavePrimitiveSchemaUnchanged() {
    Schema writer = Schema.create(Schema.Type.STRING);
    Schema reader = Schema.create(Schema.Type.STRING);

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.STRING, result.getType());
  }

  @Test
  public void applyAliasesShouldPreserveFieldOrderAndTypesWhenRenamingRecord() {
    Schema writer = parse("{\"type\":\"record\",\"name\":\"OldPair\","
        + "\"fields\":["
        + "{\"name\":\"a\",\"type\":\"int\"},"
        + "{\"name\":\"b\",\"type\":\"string\"}"
        + "]}");
    Schema reader = parse("{\"type\":\"record\",\"name\":\"Pair\","
        + "\"aliases\":[\"OldPair\"],"
        + "\"fields\":["
        + "{\"name\":\"a\",\"type\":\"int\"},"
        + "{\"name\":\"b\",\"type\":\"string\"}"
        + "]}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("Pair", result.getName());
    assertEquals("a", result.getFields().get(0).name());
    assertEquals(Schema.Type.INT, result.getFields().get(0).schema().getType());
    assertEquals("b", result.getFields().get(1).name());
    assertEquals(Schema.Type.STRING, result.getFields().get(1).schema().getType());
  }

  @Test(expected = SchemaParseException.class)
  public void readerWithInvalidSyntaxAliasShouldFailDuringParsing() {
    parse("{\"type\":\"record\",\"name\":\"User\","
        + "\"aliases\":[\"old-user\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}");
  }

  private static Schema parse(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static JsonNode json(String json) throws Exception {
    return MAPPER.readTree(json);
  }
}
