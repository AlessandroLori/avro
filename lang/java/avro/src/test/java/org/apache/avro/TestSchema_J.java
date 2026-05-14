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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Black-box tests for advanced Schema parsing cases.
 *
 * <p>The tests focus on schema structures that are not covered by the basic
 * product of equivalence classes over type/name/namespace/logicalType. The goal
 * is to exercise documented Avro schema features such as fields, defaults,
 * aliases, enum symbols, fixed sizes, array/map nested schemas and unions.</p>
 */
public class TestSchema_J {

  @Test
  public void recordShouldAcceptEmptyFieldsArray() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"EmptyRecord\","
        + "\"fields\":[]"
        + "}");

    assertSame(Schema.Type.RECORD, schema.getType());
    assertEquals("EmptyRecord", schema.getName());
    assertTrue(schema.getFields().isEmpty());
  }

  @Test
  public void recordShouldAcceptMultipleValidFields() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\"},"
        + "{\"name\":\"username\",\"type\":\"string\"},"
        + "{\"name\":\"active\",\"type\":\"boolean\"}"
        + "]"
        + "}");

    assertSame(Schema.Type.RECORD, schema.getType());
    assertEquals(3, schema.getFields().size());
    assertSame(Schema.Type.INT, schema.getField("id").schema().getType());
    assertSame(Schema.Type.STRING, schema.getField("username").schema().getType());
    assertSame(Schema.Type.BOOLEAN, schema.getField("active").schema().getType());
  }

  @Test
  public void recordShouldRejectMissingFieldsProperty() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"MissingFields\""
        + "}");
  }

  @Test
  public void recordShouldRejectNullFieldsProperty() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"NullFields\","
        + "\"fields\":null"
        + "}");
  }

  @Test
  public void recordShouldRejectNonArrayFieldsProperty() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"WrongFields\","
        + "\"fields\":\"not-an-array\""
        + "}");
  }

  @Test
  public void recordShouldRejectFieldWithoutName() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"MissingFieldName\","
        + "\"fields\":[{\"type\":\"int\"}]"
        + "}");
  }

  @Test
  public void recordShouldRejectFieldWithoutType() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"MissingFieldType\","
        + "\"fields\":[{\"name\":\"id\"}]"
        + "}");
  }

  @Test
  public void recordShouldRejectNonObjectField() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"WrongField\","
        + "\"fields\":[\"id\"]"
        + "}");
  }

  @Test
  public void recordShouldRejectDuplicateFieldNames() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"DuplicateFields\","
        + "\"fields\":["
        + "{\"name\":\"id\",\"type\":\"int\"},"
        + "{\"name\":\"id\",\"type\":\"string\"}"
        + "]"
        + "}");
  }

  @Test
  public void recordShouldAcceptNestedRecordField() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{"
        + "\"name\":\"address\","
        + "\"type\":{"
        + "\"type\":\"record\","
        + "\"name\":\"Address\","
        + "\"fields\":[{\"name\":\"city\",\"type\":\"string\"}]"
        + "}"
        + "}]"
        + "}");

    Schema addressSchema = schema.getField("address").schema();
    assertSame(Schema.Type.RECORD, addressSchema.getType());
    assertEquals("Address", addressSchema.getName());
    assertEquals("city", addressSchema.getFields().get(0).name());
  }

  @Test
  public void recordShouldAcceptArrayAndMapFields() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"Container\","
        + "\"fields\":["
        + "{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"string\"}},"
        + "{\"name\":\"scores\",\"type\":{\"type\":\"map\",\"values\":\"int\"}}"
        + "]"
        + "}");

    assertSame(Schema.Type.ARRAY, schema.getField("tags").schema().getType());
    assertSame(Schema.Type.STRING, schema.getField("tags").schema().getElementType().getType());
    assertSame(Schema.Type.MAP, schema.getField("scores").schema().getType());
    assertSame(Schema.Type.INT, schema.getField("scores").schema().getValueType().getType());
  }

  @Test
  public void recordShouldRejectUnknownFieldType() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"UnknownFieldType\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"unknown.Type\"}]"
        + "}");
  }

  @Test
  public void defaultValidationShouldAcceptPrimitiveDefaultMatchingType() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{\"name\":\"age\",\"type\":\"int\",\"default\":10}]"
        + "}", true);

    assertEquals(10, schema.getField("age").defaultVal());
  }

  @Test
  public void defaultValidationShouldRejectPrimitiveDefaultWithWrongType() {
    assertParseFailsWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{\"name\":\"age\",\"type\":\"int\",\"default\":\"wrong\"}]"
        + "}", true);
  }

  @Test
  public void defaultValidationDisabledShouldAllowPrimitiveDefaultWithWrongType() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{\"name\":\"age\",\"type\":\"int\",\"default\":\"wrong\"}]"
        + "}", false);

    assertNotNull(schema.getField("age"));
  }

  @Test
  public void defaultValidationShouldAcceptRecordArrayMapAndEnumDefaults() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"Defaults\","
        + "\"fields\":["
        + "{\"name\":\"address\","
        + "\"type\":{\"type\":\"record\",\"name\":\"Address\","
        + "\"fields\":[{\"name\":\"city\",\"type\":\"string\"}]},"
        + "\"default\":{\"city\":\"Rome\"}},"
        + "{\"name\":\"tags\",\"type\":{\"type\":\"array\",\"items\":\"string\"},"
        + "\"default\":[\"a\",\"b\"]},"
        + "{\"name\":\"attributes\",\"type\":{\"type\":\"map\",\"values\":\"string\"},"
        + "\"default\":{\"k\":\"v\"}},"
        + "{\"name\":\"status\",\"type\":{\"type\":\"enum\",\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"DONE\"]},\"default\":\"NEW\"}"
        + "]"
        + "}", true);

    assertEquals(4, schema.getFields().size());
    assertNotNull(schema.getField("address").defaultVal());
    assertNotNull(schema.getField("tags").defaultVal());
    assertNotNull(schema.getField("attributes").defaultVal());
    assertEquals("NEW", schema.getField("status").defaultVal());
  }

  @Test
  public void defaultValidationShouldAcceptEnumFieldDefaultEvenIfSymbolIsMissing() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"Workflow\","
        + "\"fields\":[{"
        + "\"name\":\"status\","
        + "\"type\":{\"type\":\"enum\",\"name\":\"Status\",\"symbols\":[\"NEW\",\"DONE\"]},"
        + "\"default\":\"MISSING\""
        + "}]"
        + "}", true);

    assertSame(Schema.Type.ENUM, schema.getField("status").schema().getType());
    assertEquals("MISSING", schema.getField("status").defaultVal());
  }

  @Test
  public void defaultValidationShouldAcceptUnionDefaultMatchingFirstBranch() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"NullableName\","
        + "\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":null}]"
        + "}", true);

    assertSame(Schema.Type.UNION, schema.getField("name").schema().getType());
  }

  @Test
  public void defaultValidationShouldAcceptUnionDefaultMatchingNonFirstBranch() {
    Schema schema = parseWithDefaultValidation("{"
        + "\"type\":\"record\","
        + "\"name\":\"NullableNameWithValue\","
        + "\"fields\":[{\"name\":\"name\",\"type\":[\"null\",\"string\"],\"default\":\"value\"}]"
        + "}", true);

    Schema fieldSchema = schema.getField("name").schema();

    assertSame(Schema.Type.UNION, fieldSchema.getType());
    assertEquals(2, fieldSchema.getTypes().size());
    assertSame(Schema.Type.NULL, fieldSchema.getTypes().get(0).getType());
    assertSame(Schema.Type.STRING, fieldSchema.getTypes().get(1).getType());
  }

  @Test
  public void recordAliasesShouldBeParsedWhenArrayOfStrings() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[\"Person\",\"Account\"],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    assertEquals(2, schema.getAliases().size());
  }

  @Test
  public void recordAliasesShouldAcceptEmptyArray() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[] ,"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");

    assertTrue(schema.getAliases().isEmpty());
  }

  @Test
  public void recordAliasesShouldRejectNonArrayValue() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":\"Person\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");
  }

  @Test
  public void recordAliasesShouldRejectNonStringElement() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"aliases\":[\"Person\",123],"
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");
  }

  @Test
  public void fieldAliasesShouldBeParsedWhenArrayOfStrings() {
    Schema schema = parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"type\":\"int\","
        + "\"aliases\":[\"identifier\",\"legacyId\"]"
        + "}]"
        + "}");

    assertEquals(2, schema.getField("id").aliases().size());
  }

  @Test
  public void fieldAliasesShouldRejectMixedArray() {
    assertParseFails("{"
        + "\"type\":\"record\","
        + "\"name\":\"User\","
        + "\"fields\":[{"
        + "\"name\":\"id\","
        + "\"type\":\"int\","
        + "\"aliases\":[\"identifier\",false]"
        + "}]"
        + "}");
  }

  @Test
  public void enumShouldParseSymbolsAndDefault() {
    Schema schema = parse("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"RUNNING\",\"DONE\"],"
        + "\"default\":\"NEW\""
        + "}");

    assertSame(Schema.Type.ENUM, schema.getType());
    assertEquals(Arrays.asList("NEW", "RUNNING", "DONE"), schema.getEnumSymbols());
    assertEquals("NEW", schema.getEnumDefault());
  }

  @Test
  public void enumShouldRejectMissingSymbols() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\""
        + "}");
  }

  @Test
  public void enumShouldRejectNullSymbols() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":null"
        + "}");
  }

  @Test
  public void enumShouldRejectNonArraySymbols() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":\"NEW\""
        + "}");
  }

  @Test
  public void enumShouldAcceptEmptySymbols() {
    Schema schema = parse("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[]"
        + "}");

    assertSame(Schema.Type.ENUM, schema.getType());
    assertTrue(schema.getEnumSymbols().isEmpty());
  }

  @Test
  public void enumShouldRejectDuplicateSymbols() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"NEW\"]"
        + "}");
  }

  @Test
  public void enumShouldRejectInvalidSymbolSyntax() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"BAD-SYMBOL\"]"
        + "}");
  }

  @Test
  public void enumShouldRejectNonStringSymbol() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",123]"
        + "}");
  }

  @Test
  public void enumShouldRejectDefaultNotInSymbols() {
    assertParseFails("{"
        + "\"type\":\"enum\","
        + "\"name\":\"Status\","
        + "\"symbols\":[\"NEW\",\"DONE\"],"
        + "\"default\":\"MISSING\""
        + "}");
  }

  @Test
  public void fixedShouldAcceptPositiveSize() {
    Schema schema = parse("{"
        + "\"type\":\"fixed\","
        + "\"name\":\"Hash\","
        + "\"size\":1"
        + "}");

    assertSame(Schema.Type.FIXED, schema.getType());
    assertEquals(1, schema.getFixedSize());
  }

  @Test
  public void fixedShouldRejectMissingSize() {
    assertParseFails("{"
        + "\"type\":\"fixed\","
        + "\"name\":\"Hash\""
        + "}");
  }

  @Test
  public void fixedShouldRejectNullSize() {
    assertParseFails("{"
        + "\"type\":\"fixed\","
        + "\"name\":\"Hash\","
        + "\"size\":null"
        + "}");
  }

  @Test
  public void fixedShouldRejectNonIntegerSize() {
    assertParseFails("{"
        + "\"type\":\"fixed\","
        + "\"name\":\"Hash\","
        + "\"size\":\"16\""
        + "}");
  }

  @Test
  public void fixedShouldRejectNegativeSize() {
    assertParseFails("{"
        + "\"type\":\"fixed\","
        + "\"name\":\"Hash\","
        + "\"size\":-1"
        + "}");
  }

  @Test
  public void arrayShouldParsePrimitiveRecordAndUnionItems() {
    Schema primitiveArray = parse("{\"type\":\"array\",\"items\":\"string\"}");
    assertSame(Schema.Type.ARRAY, primitiveArray.getType());
    assertSame(Schema.Type.STRING, primitiveArray.getElementType().getType());

    Schema recordArray = parse("{"
        + "\"type\":\"array\","
        + "\"items\":{\"type\":\"record\",\"name\":\"Item\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}"
        + "}");
    assertSame(Schema.Type.RECORD, recordArray.getElementType().getType());

    Schema unionArray = parse("{\"type\":\"array\",\"items\":[\"null\",\"string\"]}");
    assertSame(Schema.Type.UNION, unionArray.getElementType().getType());
  }

  @Test
  public void arrayShouldRejectMissingNullOrUnknownItems() {
    assertParseFails("{\"type\":\"array\"}");
    assertParseFails("{\"type\":\"array\",\"items\":null}");
    assertParseFails("{\"type\":\"array\",\"items\":\"unknown.Type\"}");
  }

  @Test
  public void mapShouldParsePrimitiveRecordAndUnionValues() {
    Schema primitiveMap = parse("{\"type\":\"map\",\"values\":\"int\"}");
    assertSame(Schema.Type.MAP, primitiveMap.getType());
    assertSame(Schema.Type.INT, primitiveMap.getValueType().getType());

    Schema recordMap = parse("{"
        + "\"type\":\"map\","
        + "\"values\":{\"type\":\"record\",\"name\":\"ValueRecord\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"long\"}]}"
        + "}");
    assertSame(Schema.Type.RECORD, recordMap.getValueType().getType());

    Schema unionMap = parse("{\"type\":\"map\",\"values\":[\"null\",\"string\"]}");
    assertSame(Schema.Type.UNION, unionMap.getValueType().getType());
  }

  @Test
  public void mapShouldRejectMissingNullOrUnknownValues() {
    assertParseFails("{\"type\":\"map\"}");
    assertParseFails("{\"type\":\"map\",\"values\":null}");
    assertParseFails("{\"type\":\"map\",\"values\":\"unknown.Type\"}");
  }

  @Test
  public void unionShouldAcceptDifferentPrimitiveBranches() {
    Schema schema = parse("[\"null\",\"string\",\"int\"]");

    assertSame(Schema.Type.UNION, schema.getType());
    assertEquals(3, schema.getTypes().size());
    assertSame(Schema.Type.NULL, schema.getTypes().get(0).getType());
    assertSame(Schema.Type.STRING, schema.getTypes().get(1).getType());
    assertSame(Schema.Type.INT, schema.getTypes().get(2).getType());
  }

  @Test
  public void unionShouldAcceptRecordBranch() {
    Schema schema = parse("["
        + "\"null\","
        + "{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}"
        + "]");

    assertSame(Schema.Type.UNION, schema.getType());
    assertSame(Schema.Type.RECORD, schema.getTypes().get(1).getType());
  }

  @Test
  public void unionShouldAcceptDifferentNamedRecordBranches() {
    Schema schema = parse("["
        + "{\"type\":\"record\",\"name\":\"User\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]},"
        + "{\"type\":\"record\",\"name\":\"Account\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]}"
        + "]");

    List<Schema> branches = schema.getTypes();
    assertEquals(2, branches.size());
    assertEquals("User", branches.get(0).getName());
    assertEquals("Account", branches.get(1).getName());
  }

  @Test
  public void unionShouldAcceptEmptyUnion() {
    Schema schema = parse("[]");

    assertSame(Schema.Type.UNION, schema.getType());
    assertTrue(schema.getTypes().isEmpty());
  }

  @Test
  public void unionShouldRejectDuplicatePrimitiveBranches() {
    assertParseFails("[\"string\",\"string\"]");
  }

  @Test
  public void unionShouldRejectNestedUnion() {
    assertParseFails("[\"null\",[\"string\",\"int\"]]");
  }

  @Test
  public void unionShouldRejectDuplicateRecordFullName() {
    assertParseFails("["
        + "{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]},"
        + "{\"type\":\"record\",\"name\":\"User\",\"namespace\":\"example\","
        + "\"fields\":[{\"name\":\"code\",\"type\":\"string\"}]}"
        + "]");
  }

  @Test
  public void unionShouldRejectUnknownBranchType() {
    assertParseFails("[\"null\",\"unknown.Type\"]");
  }

  private static Schema parse(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static Schema parseWithDefaultValidation(String schemaJson, boolean validateDefaults) {
    Schema.Parser parser = new Schema.Parser();
    parser.setValidateDefaults(validateDefaults);
    return parser.parse(schemaJson);
  }

  private static void assertParseFails(String schemaJson) {
    try {
      new Schema.Parser().parse(schemaJson);
      fail("Expected parsing to fail for schema: " + schemaJson);
    } catch (RuntimeException expected) {
      assertNotNull(expected);
    }
  }

  private static void assertParseFailsWithDefaultValidation(
      String schemaJson,
      boolean validateDefaults) {
    try {
      parseWithDefaultValidation(schemaJson, validateDefaults);
      fail("Expected parsing to fail for schema: " + schemaJson);
    } catch (RuntimeException expected) {
      assertNotNull(expected);
    }
  }
}
