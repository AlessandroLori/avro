package org.apache.avro.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SchemaRandoopRegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test01");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        org.apache.avro.Schema schema2 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema3 = org.apache.avro.Schema.applyAliases(schema0, schema2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.equals(Object)\" because \"writer\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test02");
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList5 = new java.util.ArrayList<java.lang.String>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList5, strArray4);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema8 = org.apache.avro.Schema.createEnum("", "", "hi!", (java.util.List<java.lang.String>) strList5, "");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Empty name");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertArrayEquals(strArray4, new java.lang.String[] { "hi!" });
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test03");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema2 = schema1.getValueType();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test04");
        org.apache.avro.ParseContext parseContext0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Parser parser1 = new org.apache.avro.Schema.Parser(parseContext0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"nameValidator\" because \"context\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test05");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        org.apache.avro.Schema schema2 = null;
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createArray(schema2);
        // The following exception was thrown during execution in test generation
        try {
            schema1.addAllProps((org.apache.avro.JsonProperties) schema2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"props\" because \"properties\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertNotNull(schema3);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test06");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            schema1.addProp("", (java.lang.Object) '4');
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Unknown datum class: class java.lang.Character");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test07");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("", "hi!", "", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Empty name");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fieldArray4);
        org.junit.Assert.assertArrayEquals(fieldArray4, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test08");
        org.apache.avro.Schema schema1 = null;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.createArray(schema1);
        org.apache.avro.Schema.Field.Order order5 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field6 = new org.apache.avro.Schema.Field("", schema1, "hi!", (java.lang.Object) (byte) 10, order5);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Unknown datum class: class java.lang.Byte");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema2);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test09");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.DOUBLE;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.DOUBLE + "'", type0.equals(org.apache.avro.Schema.Type.DOUBLE));
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test10");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<org.apache.avro.Schema.Field> fieldList2 = schema1.getFields();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test11");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = schema1.getNamespace();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test12");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        org.apache.avro.Schema schema2 = null;
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createArray(schema2);
        // The following exception was thrown during execution in test generation
        try {
            schema1.putAll((org.apache.avro.JsonProperties) schema2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read field \"props\" because \"np\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertNotNull(schema3);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test13");
        java.lang.Object obj1 = org.apache.avro.Schema.parseJsonToObject("");
        org.junit.Assert.assertNull(obj1);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test14");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<java.lang.String> strList2 = schema1.getEnumSymbols();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test15");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            schema1.addProp("", (java.lang.Object) (byte) 100);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Unknown datum class: class java.lang.Byte");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test16");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        org.apache.avro.Schema schema2 = null;
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createArray(schema2);
        org.apache.avro.Schema schema4 = null;
        org.apache.avro.Schema schema5 = org.apache.avro.Schema.createArray(schema4);
        org.apache.avro.Schema schema6 = null;
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createArray(schema6);
        org.apache.avro.Schema schema8 = null;
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createArray(schema8);
        org.apache.avro.Schema schema10 = null;
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createArray(schema10);
        org.apache.avro.Schema[] schemaArray12 = new org.apache.avro.Schema[] { schema1, schema2, schema5, schema7, schema8, schema10 };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema13 = org.apache.avro.Schema.createUnion(schemaArray12);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.getType()\" because \"type\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema5);
        org.junit.Assert.assertNotNull(schema7);
        org.junit.Assert.assertNotNull(schema9);
        org.junit.Assert.assertNotNull(schema11);
        org.junit.Assert.assertNotNull(schemaArray12);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test17");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.INT;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.INT + "'", type0.equals(org.apache.avro.Schema.Type.INT));
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test18");
        java.lang.Object obj0 = org.apache.avro.Schema.Field.NULL_DEFAULT_VALUE;
        org.junit.Assert.assertNotNull(obj0);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test19");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        org.apache.avro.Schema schema2 = null;
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createArray(schema2);
        org.apache.avro.Schema schema4 = null;
        org.apache.avro.Schema schema5 = org.apache.avro.Schema.createArray(schema4);
        org.apache.avro.Schema schema6 = null;
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createArray(schema6);
        org.apache.avro.Schema[] schemaArray8 = new org.apache.avro.Schema[] { schema1, schema2, schema5, schema6 };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema9 = org.apache.avro.Schema.createUnion(schemaArray8);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.getType()\" because \"type\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema5);
        org.junit.Assert.assertNotNull(schema7);
        org.junit.Assert.assertNotNull(schemaArray8);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test20");
        org.apache.avro.NameValidator nameValidator0 = org.apache.avro.Schema.getNameValidator();
        java.lang.Class<?> wildcardClass1 = nameValidator0.getClass();
        org.junit.Assert.assertNotNull(nameValidator0);
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test21");
        org.apache.avro.Schema schema1 = null;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.createArray(schema1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field4 = new org.apache.avro.Schema.Field("hi!", schema2, "");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Illegal character in: hi!");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema2);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test22");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean3 = schema1.hasEnumSymbol("");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test23");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.RECORD;
        java.lang.String str1 = type0.getName();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.RECORD + "'", type0.equals(org.apache.avro.Schema.Type.RECORD));
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "record" + "'", str1, "record");
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test24");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("", "hi!", "", false);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Empty name");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test25");
        java.lang.String[] strArray5 = new java.lang.String[] { "record", "" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema8 = org.apache.avro.Schema.createEnum("hi!", "record", "record", (java.util.List<java.lang.String>) strList6);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Illegal character in: hi!");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertArrayEquals(strArray5, new java.lang.String[] { "record", "" });
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test26");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap1 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Parser parser2 = parser0.addTypes(strMap1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Map.values()\" because \"types\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test27");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            int int3 = schema1.getEnumOrdinal("record");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test28");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = schema1.getNamespace();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: \"long\"");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test29");
        org.apache.avro.Schema.SeenPair seenPair2 = new org.apache.avro.Schema.SeenPair((java.lang.Object) (-1L), (java.lang.Object) 100.0f);
        boolean boolean4 = seenPair2.equals((java.lang.Object) 0.0f);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test30");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "" };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema6 = parser0.parse("", strArray5);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'hi': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertArrayEquals(strArray5, new java.lang.String[] { "hi!", "", "" });
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test31");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            int int3 = schema1.getEnumOrdinal("");
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test32");
        org.apache.avro.Schema schema0 = null;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.createArray(schema0);
        // The following exception was thrown during execution in test generation
        try {
            int int2 = schema1.getFixedSize();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toJson(java.util.Set, String, com.fasterxml.jackson.core.JsonGenerator)\" because \"this.elementType\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema1);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test33");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.UNION;
        java.lang.Class<?> wildcardClass1 = type0.getClass();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type0.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test34");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.ENUM;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.ENUM + "'", type0.equals(org.apache.avro.Schema.Type.ENUM));
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test35");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.BOOLEAN;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.BOOLEAN + "'", type0.equals(org.apache.avro.Schema.Type.BOOLEAN));
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test36");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.FIXED;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Can't create a: FIXED");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.FIXED + "'", type0.equals(org.apache.avro.Schema.Type.FIXED));
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test37");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean4 = schema1.isError();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: \"long\"");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test38");
        org.apache.avro.JsonProperties.Null null0 = org.apache.avro.JsonProperties.NULL_VALUE;
        org.junit.Assert.assertNotNull(null0);
    }

    @Test
    public void test39() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test39");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        java.lang.String[] strArray8 = new java.lang.String[] { "", "long", "" };
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema9 = parser2.parse("\"long\"", strArray8);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: dangling content after end of schema: long");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertArrayEquals(strArray8, new java.lang.String[] { "", "long", "" });
    }

    @Test
    public void test40() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test40");
        java.io.InputStream inputStream0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema1 = org.apache.avro.Schema.parse(inputStream0);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Cannot parse <null> schema");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test41() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test41");
        java.io.File file0 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema1 = org.apache.avro.Schema.parse(file0);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test42() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test42");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("", "long", "\"long\"", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Empty name");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fieldArray4);
        org.junit.Assert.assertArrayEquals(fieldArray4, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test43() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test43");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("", "hi!", "record", false);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Empty name");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test44() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test44");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.ARRAY;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.ARRAY + "'", type0.equals(org.apache.avro.Schema.Type.ARRAY));
    }

    @Test
    public void test45() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test45");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        org.apache.avro.Schema.Type type4 = org.apache.avro.Schema.Type.UNION;
        org.apache.avro.Schema.SeenPair seenPair5 = new org.apache.avro.Schema.SeenPair((java.lang.Object) boolean3, (java.lang.Object) type4);
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + type4 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type4.equals(org.apache.avro.Schema.Type.UNION));
    }

    @Test
    public void test46() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test46");
        java.lang.String[] strArray6 = new java.lang.String[] { "record", "", "\"long\"" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema9 = org.apache.avro.Schema.createEnum("hi!", "hi!", "\"long\"", (java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Illegal character in: hi!");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertArrayEquals(strArray6, new java.lang.String[] { "record", "", "\"long\"" });
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test47() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test47");
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("record", "long", "record", true);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema5 = schema4.getElementType();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an array: {\"type\":\"error\",\"name\":\"record\",\"namespace\":\"record\",\"doc\":\"long\"}");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema4);
    }

    @Test
    public void test48() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test48");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        schema1.addProp("", "long");
        org.apache.avro.Schema.Type type7 = schema1.getType();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + type7 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type7.equals(org.apache.avro.Schema.Type.LONG));
    }

    @Test
    public void test49() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test49");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.BYTES;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.BYTES + "'", type0.equals(org.apache.avro.Schema.Type.BYTES));
    }

    @Test
    public void test50() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test50");
        org.apache.avro.NameValidator nameValidator0 = org.apache.avro.Schema.getNameValidator();
        org.apache.avro.Schema.setNameValidator(nameValidator0);
        org.apache.avro.Schema.setNameValidator(nameValidator0);
        org.apache.avro.Schema.Parser parser3 = new org.apache.avro.Schema.Parser(nameValidator0);
        org.junit.Assert.assertNotNull(nameValidator0);
    }

    @Test
    public void test51() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test51");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.MAP;
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.MAP + "'", type0.equals(org.apache.avro.Schema.Type.MAP));
    }

    @Test
    public void test52() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test52");
        org.apache.avro.Schema.Type type1 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.create(type1);
        org.apache.avro.Schema.Field field4 = new org.apache.avro.Schema.Field("long", schema2, "");
        org.apache.avro.Schema.Type type5 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.create(type5);
        org.apache.avro.Schema.Parser parser7 = new org.apache.avro.Schema.Parser();
        boolean boolean8 = schema6.equals((java.lang.Object) parser7);
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field(field4, schema6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Integer int11 = schema6.getIndexNamed("record");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a union: \"long\"");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type1 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type1.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema2);
        org.junit.Assert.assertTrue("'" + type5 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type5.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test53() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test53");
        org.apache.avro.Schema.Type type1 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.create(type1);
        org.apache.avro.Schema.Field field4 = new org.apache.avro.Schema.Field("long", schema2, "");
        org.apache.avro.Schema schema5 = field4.schema();
        java.lang.String str6 = field4.toString();
        org.junit.Assert.assertTrue("'" + type1 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type1.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema2);
        org.junit.Assert.assertNotNull(schema5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "long type:LONG pos:-1" + "'", str6, "long type:LONG pos:-1");
    }

    @Test
    public void test54() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test54");
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj1 = org.apache.avro.Schema.parseJsonToObject("long");
            org.junit.Assert.fail("Expected exception of type java.lang.RuntimeException; message: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'long': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]");
        } catch (java.lang.RuntimeException e) {
            // Expected exception.
        }
    }

    @Test
    public void test55() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test55");
        org.apache.avro.Schema schema1 = null;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.createArray(schema1);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field3 = new org.apache.avro.Schema.Field("long", schema1);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: schema is required and cannot be null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema2);
    }

    @Test
    public void test56() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test56");
        java.lang.String[] strArray10 = new java.lang.String[] { "long type:LONG pos:-1", "long type:LONG pos:-1", "", "{\n  \"type\" : \"long\",\n  \"hi!\" : \"record\"\n}", "\"long\"", "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema14 = org.apache.avro.Schema.createEnum("long", "", "hi!", (java.util.List<java.lang.String>) strList11, "long type:LONG pos:-1");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Illegal character in: long type:LONG pos:-1");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertArrayEquals(strArray10, new java.lang.String[] { "long type:LONG pos:-1", "long type:LONG pos:-1", "", "{\n  \"type\" : \"long\",\n  \"hi!\" : \"record\"\n}", "\"long\"", "hi!", "hi!" });
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test57() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test57");
        org.apache.avro.Schema.Type type2 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.create(type2);
        org.apache.avro.Schema.Field field5 = new org.apache.avro.Schema.Field("long", schema3, "");
        org.apache.avro.Schema schema6 = field5.schema();
        org.apache.avro.Schema.Type type8 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.create(type8);
        java.lang.String str10 = schema9.toString();
        boolean boolean11 = schema9.isNullable();
        org.apache.avro.Schema.Field.Order order12 = org.apache.avro.Schema.Field.Order.IGNORE;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field13 = new org.apache.avro.Schema.Field("long", schema6, "long", (java.lang.Object) schema9, order12);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Unknown datum class: class org.apache.avro.Schema$LongSchema");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type2 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type2.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type8.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\"long\"" + "'", str10, "\"long\"");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + order12 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order12.equals(org.apache.avro.Schema.Field.Order.IGNORE));
    }

    @Test
    public void test58() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test58");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        com.fasterxml.jackson.databind.JsonNode jsonNode4 = null;
        boolean boolean5 = schema1.isValidDefault(jsonNode4);
        java.lang.Object obj7 = schema1.getObjectProp("record");
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNull(obj7);
    }

    @Test
    public void test59() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test59");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.getFullName();
        schema1.addProp("hi!", (java.lang.Object) "record");
        java.lang.String str6 = schema1.getFullName();
        com.fasterxml.jackson.databind.JsonNode jsonNode7 = null;
        boolean boolean8 = schema1.isValidDefault(jsonNode7);
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "long" + "'", str2, "long");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "long" + "'", str6, "long");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test60() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test60");
        org.apache.avro.Schema.Type type1 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.create(type1);
        org.apache.avro.Schema.Field field4 = new org.apache.avro.Schema.Field("long", schema2, "");
        org.apache.avro.Schema.Type type5 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.create(type5);
        org.apache.avro.Schema.Parser parser7 = new org.apache.avro.Schema.Parser();
        boolean boolean8 = schema6.equals((java.lang.Object) parser7);
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field(field4, schema6);
        java.lang.String str10 = schema6.getDoc();
        org.junit.Assert.assertTrue("'" + type1 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type1.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema2);
        org.junit.Assert.assertTrue("'" + type5 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type5.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNull(str10);
    }

    @Test
    public void test61() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test61");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema5 = parser2.parse(inputStream4);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Cannot parse <null> schema");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test62() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test62");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.getFullName();
        schema1.addProp("hi!", (java.lang.Object) "record");
        org.apache.avro.Schema.Type type6 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.create(type6);
        java.lang.String str8 = schema7.getFullName();
        org.apache.avro.Schema.Type type9 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.create(type9);
        org.apache.avro.Schema.Parser parser11 = new org.apache.avro.Schema.Parser();
        boolean boolean12 = schema10.equals((java.lang.Object) parser11);
        java.lang.String str13 = schema10.toString();
        org.apache.avro.Schema.Type type14 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.create(type14);
        java.lang.String str16 = schema15.getFullName();
        schema15.addProp("hi!", (java.lang.Object) "record");
        org.apache.avro.Schema[] schemaArray20 = new org.apache.avro.Schema[] { schema7, schema10, schema15 };
        java.util.ArrayList<org.apache.avro.Schema> schemaList21 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList21, schemaArray20);
        java.lang.String str24 = schema1.toString((java.util.Collection<org.apache.avro.Schema>) schemaList21, true);
        java.lang.String str25 = schema1.getDoc();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "long" + "'", str2, "long");
        org.junit.Assert.assertTrue("'" + type6 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type6.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "long" + "'", str8, "long");
        org.junit.Assert.assertTrue("'" + type9 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type9.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\"long\"" + "'", str13, "\"long\"");
        org.junit.Assert.assertTrue("'" + type14 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type14.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema15);
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "long" + "'", str16, "long");
        org.junit.Assert.assertNotNull(schemaArray20);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "{\n  \"type\" : \"long\",\n  \"hi!\" : \"record\"\n}" + "'", str24, "{\n  \"type\" : \"long\",\n  \"hi!\" : \"record\"\n}");
        org.junit.Assert.assertNull(str25);
    }

    @Test
    public void test63() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test63");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        com.fasterxml.jackson.databind.JsonNode jsonNode4 = null;
        boolean boolean5 = schema1.isValidDefault(jsonNode4);
        boolean boolean6 = schema1.hasProps();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test64() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test64");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.getFullName();
        schema1.addProp("hi!", (java.lang.Object) "record");
        java.lang.String str6 = schema1.getFullName();
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer7 = null;
        // The following exception was thrown during execution in test generation
        try {
            schema1.forEachProperty(strBiConsumer7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.function.BiConsumer.accept(Object, Object)\" because \"consumer\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "long" + "'", str2, "long");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "long" + "'", str6, "long");
    }

    @Test
    public void test65() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test65");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.getFullName();
        schema1.addProp("record", (java.lang.Object) 10.0d);
        java.lang.String str7 = schema1.getProp("record");
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<org.apache.avro.Schema.Field> fieldList8 = schema1.getFields();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: {\"type\":\"long\",\"record\":10.0}");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "long" + "'", str2, "long");
        org.junit.Assert.assertNull(str7);
    }

    @Test
    public void test66() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test66");
        org.apache.avro.Schema schema5 = org.apache.avro.Schema.createRecord("record", "long", "record", true);
        org.apache.avro.Schema.Type type7 = org.apache.avro.Schema.Type.NULL;
        org.apache.avro.Schema.Field.Order order8 = org.apache.avro.Schema.Field.Order.IGNORE;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("long type:LONG pos:-1", schema5, "long type:LONG pos:-1", (java.lang.Object) type7, order8);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: Illegal character in: long type:LONG pos:-1");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schema5);
        org.junit.Assert.assertTrue("'" + type7 + "' != '" + org.apache.avro.Schema.Type.NULL + "'", type7.equals(org.apache.avro.Schema.Type.NULL));
        org.junit.Assert.assertTrue("'" + order8 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order8.equals(org.apache.avro.Schema.Field.Order.IGNORE));
    }

    @Test
    public void test67() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test67");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        java.lang.String str4 = schema1.toString();
        boolean boolean5 = schema1.hasProps();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\"long\"" + "'", str4, "\"long\"");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test68() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test68");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = schema1.equals((java.lang.Object) parser2);
        java.lang.Class<?> wildcardClass4 = parser2.getClass();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test69() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test69");
        org.apache.avro.Schema.Type type1 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.create(type1);
        org.apache.avro.Schema.Field field4 = new org.apache.avro.Schema.Field("long", schema2, "");
        java.lang.String str5 = field4.toString();
        org.junit.Assert.assertTrue("'" + type1 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type1.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema2);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "long type:LONG pos:-1" + "'", str5, "long type:LONG pos:-1");
    }

    @Test
    public void test70() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test70");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.getFullName();
        schema1.addProp("hi!", (java.lang.Object) "record");
        java.lang.String str6 = schema1.toString();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "long" + "'", str2, "long");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "{\"type\":\"long\",\"hi!\":\"record\"}" + "'", str6, "{\"type\":\"long\",\"hi!\":\"record\"}");
    }

    @Test
    public void test71() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopRegressionTest0.test71");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.LONG;
        org.apache.avro.Schema schema1 = org.apache.avro.Schema.create(type0);
        java.lang.String str2 = schema1.toString();
        boolean boolean3 = schema1.isNullable();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema4 = schema1.getElementType();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an array: \"long\"");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.LONG + "'", type0.equals(org.apache.avro.Schema.Type.LONG));
        org.junit.Assert.assertNotNull(schema1);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "\"long\"" + "'", str2, "\"long\"");
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }
}

