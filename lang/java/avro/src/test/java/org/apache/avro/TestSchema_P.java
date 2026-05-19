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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class TestSchema_P {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  public void applyAliasesShouldReturnOriginalWriterWhenReaderHasNoAliasesAndSchemasDiffer() {
    Schema writer = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);

    assertSame("When the reader does not declare aliases, the writer schema must not be rewritten", writer, result);
    assertEquals("OldUser", result.getName());
    assertEquals(1, result.getFields().size());
    assertNotNull(result.getField("id"));
  }

  @Test
  public void applyAliasesShouldRewriteFieldWhenRecordAliasAndFieldAliasAreBothPresent() {
    Schema writer = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"OldUser\","
        + "\"fields\":[{\"name\":\"oldId\",\"type\":\"int\"}]"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[\"OldUser\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"aliases\":[\"oldId\"]}]"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("User", result.getName());
    assertNotNull("The field alias must rewrite oldId into id", result.getField("id"));
    assertNull("The old writer field name should not remain as a field name after alias rewriting", result.getField("oldId"));
    assertEquals(Schema.Type.INT, result.getField("id").schema().getType());
    assertEquals(1, result.getFields().size());
  }

  @Test
  public void applyAliasesShouldPreserveSchemaPropertiesWhenRecordIsRenamed() {
    Schema writer = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"OldUser\","
        + "\"doc\":\"writer record documentation\","
        + "\"x-schema\":\"writer-metadata\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[\"OldUser\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals("User", result.getName());
    assertEquals("writer record documentation", result.getDoc());
    assertEquals("writer-metadata", result.getProp("x-schema"));
    assertEquals(1, result.getFields().size());
  }

  @Test
  public void applyAliasesShouldPreserveFieldPropertiesWhenFieldIsRenamed() {
    Schema writer = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{"
        + "\"name\":\"oldId\","
        + "\"type\":\"int\","
        + "\"doc\":\"old identifier documentation\","
        + "\"default\":7,"
        + "\"order\":\"descending\","
        + "\"x-field\":\"field-metadata\""
        + "}]"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"type\":\"int\","
        + "\"aliases\":[\"oldId\"]"
        + "}]"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);
    Schema.Field field = result.getField("id");

    assertNotNull(field);
    assertNull(result.getField("oldId"));
    assertEquals("old identifier documentation", field.doc());
    assertEquals(Schema.Field.Order.DESCENDING, field.order());
    assertEquals(7, ((Number) field.defaultVal()).intValue());
    assertEquals("field-metadata", field.getProp("x-field"));
    assertEquals(Schema.Type.INT, field.schema().getType());
  }

  @Test
  public void applyAliasesShouldPreserveContainerPropertiesWhenArrayItemIsRewritten() {
    Schema writer = parse("{"
        + "\"type\":\"array\","
        + "\"x-array\":\"array-metadata\","
        + "\"items\":{"
        + "\"type\":\"record\","
        + "\"name\":\"OldItem\","
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]"
        + "}"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"array\","
        + "\"items\":{"
        + "\"type\":\"record\","
        + "\"name\":\"Item\","
        + "\"aliases\":[\"OldItem\"],"
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]"
        + "}"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.ARRAY, result.getType());
    assertEquals("array-metadata", result.getProp("x-array"));
    assertEquals("Item", result.getElementType().getName());
    assertEquals(Schema.Type.RECORD, result.getElementType().getType());
  }

  @Test
  public void applyAliasesShouldPreserveContainerPropertiesWhenMapValueIsRewritten() {
    Schema writer = parse("{"
        + "\"type\":\"map\","
        + "\"x-map\":\"map-metadata\","
        + "\"values\":{"
        + "\"type\":\"record\","
        + "\"name\":\"OldValue\","
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]"
        + "}"
        + "}");

    Schema reader = parse("{"
        + "\"type\":\"map\","
        + "\"values\":{"
        + "\"type\":\"record\","
        + "\"name\":\"Value\","
        + "\"aliases\":[\"OldValue\"],"
        + "\"fields\":[{\"name\":\"value\",\"type\":\"string\"}]"
        + "}"
        + "}");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.MAP, result.getType());
    assertEquals("map-metadata", result.getProp("x-map"));
    assertEquals("Value", result.getValueType().getName());
    assertEquals(Schema.Type.RECORD, result.getValueType().getType());
  }

  @Test
  public void applyAliasesShouldPreserveUnionBranchOrderWhenBranchIsRewritten() {
    Schema writer = parse("["
        + "\"null\","
        + "{\"type\":\"record\",\"name\":\"OldEvent\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]},"
        + "\"string\""
        + "]");

    Schema reader = parse("["
        + "\"null\","
        + "{\"type\":\"record\",\"name\":\"Event\",\"aliases\":[\"OldEvent\"],\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]},"
        + "\"string\""
        + "]");

    Schema result = Schema.applyAliases(writer, reader);

    assertEquals(Schema.Type.UNION, result.getType());
    assertEquals(3, result.getTypes().size());
    assertEquals(Schema.Type.NULL, result.getTypes().get(0).getType());
    assertEquals(Schema.Type.RECORD, result.getTypes().get(1).getType());
    assertEquals("Event", result.getTypes().get(1).getName());
    assertEquals(Schema.Type.STRING, result.getTypes().get(2).getType());
  }

  @Test
  public void isValidDefaultShouldReturnFalseForJavaNullJsonNode() {
    assertFalse(Schema.create(Schema.Type.NULL).isValidDefault((JsonNode) null));
    assertFalse(Schema.create(Schema.Type.STRING).isValidDefault((JsonNode) null));
  }

  @Test
  public void isValidDefaultShouldValidateUnionFieldInsideRecord() throws Exception {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"Container\","
        + "\"fields\":[{\"name\":\"choice\",\"type\":[\"null\",\"string\"]}]"
        + "}");

    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals(Schema.Type.UNION, schema.getField("choice").schema().getType());
    assertEquals(2, schema.getField("choice").schema().getTypes().size());

    assertEquals(true, schema.isValidDefault(json("{\"choice\":\"text\"}")));
    assertEquals(true, schema.isValidDefault(json("{\"choice\":null}")));
    assertFalse(schema.isValidDefault(json("{\"choice\":1}")));
    assertFalse(schema.isValidDefault(json("{}")));
  }

  @Test
  public void parserShouldPreserveCustomPropertiesOnRecordAndFields() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"x-schema\":\"schema-metadata\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"type\":\"int\","
        + "\"x-field\":\"field-metadata\""
        + "}]"
        + "}");

    assertEquals("schema-metadata", schema.getProp("x-schema"));
    assertEquals(1, schema.getFields().size());
    assertEquals("field-metadata", schema.getField("id").getProp("x-field"));
  }

  @Test
  public void parserShouldPreserveDocAndLogicalTypeWhenPresent() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"doc\":\"record documentation\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"doc\":\"identifier documentation\","
        + "\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}"
        + "}]"
        + "}");

    Schema.Field field = schema.getField("id");

    assertEquals("record documentation", schema.getDoc());
    assertEquals("identifier documentation", field.doc());
    assertNotNull(field.schema().getLogicalType());
    assertEquals("uuid", field.schema().getLogicalType().getName());
  }

  private static Schema parse(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static JsonNode json(String json) throws Exception {
    return MAPPER.readTree(json);
  }
}
