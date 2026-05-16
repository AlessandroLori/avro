package org.apache.avro.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataFileWriterRandoopRegressionTest1 {

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
    public void test501() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test501");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.io.Encoder encoder18 = null;
        // The following exception was thrown during execution in test generation
        try {
            schemaListGenericDatumWriter10.write((java.util.AbstractList<org.apache.avro.Schema>) schemaList14, encoder18);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Encoder cannot be null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNotNull(schema17);
    }

    @Test
    public void test502() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test502");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser8 = new org.apache.avro.Schema.Parser();
        boolean boolean9 = parser8.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap10 = parser8.getTypes();
        org.apache.avro.Schema[] schemaArray11 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList12 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList12, schemaArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList12);
        org.apache.avro.Schema.Parser parser15 = parser8.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList12);
        org.apache.avro.Schema.Parser parser16 = new org.apache.avro.Schema.Parser();
        boolean boolean17 = parser16.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap18 = parser16.getTypes();
        org.apache.avro.Schema.Parser parser19 = parser8.addTypes(strMap18);
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap20 = parser19.getTypes();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap21 = parser19.getTypes();
        org.apache.avro.Schema.Parser parser22 = parser7.addTypes(strMap21);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema24 = parser22.parseInternal("map");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'map': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(strMap10);
        org.junit.Assert.assertNotNull(schemaArray11);
        org.junit.Assert.assertArrayEquals(schemaArray11, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(schema14);
        org.junit.Assert.assertNotNull(parser15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(strMap18);
        org.junit.Assert.assertNotNull(parser19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNotNull(parser22);
    }

    @Test
    public void test503() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test503");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter7.setMeta("array", "fixed");
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter13 = charSequenceDataFileWriter7.setMeta("", (-1L));
        org.apache.avro.Schema[] schemaArray14 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList15 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList15, schemaArray14);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList15);
        java.lang.String str18 = schema17.getDoc();
        com.fasterxml.jackson.databind.JsonNode jsonNode19 = null;
        boolean boolean20 = schema17.isValidDefault(jsonNode19);
        java.util.Map<java.lang.String, java.lang.Object> strMap21 = schema17.getObjectProps();
        boolean boolean22 = schema17.isUnion();
        java.lang.Object obj23 = null;
        boolean boolean24 = schema17.equals(obj23);
        java.io.ByteArrayOutputStream byteArrayOutputStream26 = new java.io.ByteArrayOutputStream((int) ' ');
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter27 = charSequenceDataFileWriter7.create(schema17, (java.io.OutputStream) byteArrayOutputStream26);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.io.DatumWriter.setSchema(org.apache.avro.Schema)\" because \"this.dout\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter13);
        org.junit.Assert.assertNotNull(schemaArray14);
        org.junit.Assert.assertArrayEquals(schemaArray14, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(schema17);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertEquals(byteArrayOutputStream26.toString(), "");
    }

    @Test
    public void test504() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test504");
        java.lang.Object obj0 = null;
        java.io.ByteArrayOutputStream byteArrayOutputStream2 = new java.io.ByteArrayOutputStream((int) 'a');
        org.apache.avro.Schema.SeenPair seenPair3 = new org.apache.avro.Schema.SeenPair(obj0, (java.lang.Object) 'a');
        org.junit.Assert.assertEquals(byteArrayOutputStream2.toString(), "");
    }

    @Test
    public void test505() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test505");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser8 = new org.apache.avro.Schema.Parser();
        boolean boolean9 = parser8.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap10 = parser8.getTypes();
        org.apache.avro.Schema.Parser parser11 = parser0.addTypes(strMap10);
        org.apache.avro.Schema.Parser parser13 = parser0.setValidateDefaults(true);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(strMap10);
        org.junit.Assert.assertNotNull(parser11);
        org.junit.Assert.assertNotNull(parser13);
    }

    @Test
    public void test506() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test506");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter9 = charSequenceDataFileWriter6.setMeta("array", 100L);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter9.fSync();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter9);
    }

    @Test
    public void test507() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test507");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        com.fasterxml.jackson.databind.JsonNode jsonNode5 = null;
        boolean boolean6 = schema3.isValidDefault(jsonNode5);
        java.util.Map<java.lang.String, java.lang.Object> strMap7 = schema3.getObjectProps();
        boolean boolean8 = schema3.isUnion();
        java.lang.Object obj9 = null;
        boolean boolean10 = schema3.equals(obj9);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field12 = schema3.getField("d");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strMap7);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test508() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test508");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        org.apache.avro.LogicalType logicalType4 = schema3.getLogicalType();
        java.lang.String str5 = schema3.getDoc();
        org.apache.avro.generic.GenericData genericData6 = null;
        org.apache.avro.generic.GenericDatumWriter<java.lang.reflect.GenericDeclaration> genericDeclarationGenericDatumWriter7 = new org.apache.avro.generic.GenericDatumWriter<java.lang.reflect.GenericDeclaration>(schema3, genericData6);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createMap(schema3);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer9 = null;
        schema3.forEachProperty(strBiConsumer9);
        java.lang.Object obj12 = schema3.getObjectProp("array");
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(logicalType4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(obj12);
    }

    @Test
    public void test509() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test509");
        java.lang.Exception exception0 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException1 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception0);
        java.lang.Throwable[] throwableArray2 = appendWriteException1.getSuppressed();
        java.lang.Exception exception3 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException4 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception3);
        java.lang.Throwable[] throwableArray5 = appendWriteException4.getSuppressed();
        java.lang.Exception exception6 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException7 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception6);
        java.lang.Throwable[] throwableArray8 = appendWriteException7.getSuppressed();
        appendWriteException4.addSuppressed((java.lang.Throwable) appendWriteException7);
        appendWriteException1.addSuppressed((java.lang.Throwable) appendWriteException7);
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException11 = new org.apache.avro.file.DataFileWriter.AppendWriteException((java.lang.Exception) appendWriteException7);
        java.lang.Class<?> wildcardClass12 = appendWriteException7.getClass();
        org.junit.Assert.assertNotNull(throwableArray2);
        org.junit.Assert.assertArrayEquals(throwableArray2, new java.lang.Throwable[] {});
        org.junit.Assert.assertNotNull(throwableArray5);
        org.junit.Assert.assertArrayEquals(throwableArray5, new java.lang.Throwable[] {});
        org.junit.Assert.assertNotNull(throwableArray8);
        org.junit.Assert.assertArrayEquals(throwableArray8, new java.lang.Throwable[] {});
        org.junit.Assert.assertNotNull(wildcardClass12);
    }

    @Test
    public void test510() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test510");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        schema3.addProp("hi!", (java.lang.Object) 100L);
        // The following exception was thrown during execution in test generation
        try {
            java.util.Set<java.lang.String> strSet14 = schema3.getAliases();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
    }

    @Test
    public void test511() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test511");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap8 = schema3.getObjectProps();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter9 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3);
        boolean boolean11 = schema3.propsContainsKey("");
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer12 = null;
        schema3.forEachProperty(strBiConsumer12);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field15 = schema3.getField("[ ]");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertNotNull(strMap8);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test512() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test512");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema16.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.LogicalType logicalType29 = schema25.getLogicalType();
        schemaListGenericDatumWriter10.setSchema(schema25);
        java.io.ByteArrayOutputStream byteArrayOutputStream33 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream33.write((int) '#');
        byte[] byteArray38 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream33.writeBytes(byteArray38);
        byteArrayOutputStream33.reset();
        byteArrayOutputStream33.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream44 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream44.write((int) '#');
        byte[] byteArray49 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream44.writeBytes(byteArray49);
        byteArrayOutputStream33.writeBytes(byteArray49);
        java.lang.Object obj52 = schema25.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream33);
        java.io.ByteArrayOutputStream byteArrayOutputStream54 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream54.write((int) '#');
        byte[] byteArray59 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream54.writeBytes(byteArray59);
        byteArrayOutputStream54.reset();
        byteArrayOutputStream54.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream65 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream65.write((int) '#');
        byte[] byteArray70 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream65.writeBytes(byteArray70);
        byteArrayOutputStream54.writeBytes(byteArray70);
        byteArrayOutputStream54.write((int) 'a');
        byte[] byteArray75 = byteArrayOutputStream54.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream77 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream77.write((int) '#');
        byte[] byteArray82 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream77.writeBytes(byteArray82);
        byteArrayOutputStream77.reset();
        byte[] byteArray85 = byteArrayOutputStream77.toByteArray();
        byteArrayOutputStream54.write(byteArray85);
        byteArrayOutputStream33.writeBytes(byteArray85);
        java.io.ByteArrayOutputStream byteArrayOutputStream89 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream89.write((int) '#');
        byte[] byteArray94 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream89.writeBytes(byteArray94);
        byteArrayOutputStream89.reset();
        byteArrayOutputStream89.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream100 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream100.write((int) '#');
        byte[] byteArray105 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream100.writeBytes(byteArray105);
        byteArrayOutputStream89.writeBytes(byteArray105);
        byteArrayOutputStream89.write((int) 'a');
        byte[] byteArray110 = byteArrayOutputStream89.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream112 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream112.write((int) '#');
        byte[] byteArray117 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream112.writeBytes(byteArray117);
        byteArrayOutputStream112.reset();
        byte[] byteArray120 = byteArrayOutputStream112.toByteArray();
        byteArrayOutputStream89.write(byteArray120);
        byteArrayOutputStream33.writeBytes(byteArray120);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(logicalType29);
        org.junit.Assert.assertEquals(byteArrayOutputStream33.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream44.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj52);
        org.junit.Assert.assertEquals(obj52.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream54.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray59);
        org.junit.Assert.assertArrayEquals(byteArray59, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream65.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray70);
        org.junit.Assert.assertArrayEquals(byteArray70, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream77.toString(), "");
        org.junit.Assert.assertNotNull(byteArray82);
        org.junit.Assert.assertArrayEquals(byteArray82, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray85);
        org.junit.Assert.assertArrayEquals(byteArray85, new byte[] {});
        org.junit.Assert.assertEquals(byteArrayOutputStream89.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray94);
        org.junit.Assert.assertArrayEquals(byteArray94, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream100.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray105);
        org.junit.Assert.assertArrayEquals(byteArray105, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray110);
        org.junit.Assert.assertArrayEquals(byteArray110, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream112.toString(), "");
        org.junit.Assert.assertNotNull(byteArray117);
        org.junit.Assert.assertArrayEquals(byteArray117, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray120);
        org.junit.Assert.assertArrayEquals(byteArray120, new byte[] {});
    }

    @Test
    public void test513() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test513");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        charSequenceDataFileWriter6.close();
        boolean boolean8 = charSequenceDataFileWriter6.isFlushOnEveryBlock();
        // The following exception was thrown during execution in test generation
        try {
            long long9 = charSequenceDataFileWriter6.sync();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test514() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test514");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction8 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter9 = charSequenceDataFileWriter7.setEncoder(outputStreamFunction8);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter9.append((java.lang.CharSequence) "\uff00\uff01\uff0a");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter9);
    }

    @Test
    public void test515() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test515");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema12 = schema3.getValueType();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a map: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test516() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test516");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        boolean boolean9 = schema3.isNullable();
        org.apache.avro.Schema[] schemaArray10 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList11 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList11, schemaArray10);
        org.apache.avro.Schema schema13 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList11);
        java.lang.String str14 = schema13.getDoc();
        org.apache.avro.Schema[] schemaArray15 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList16 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList16, schemaArray15);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList16);
        java.lang.String str19 = schema18.getDoc();
        boolean boolean20 = schema18.hasProps();
        schema13.addAllProps((org.apache.avro.JsonProperties) schema18);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter22 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema18);
        org.apache.avro.generic.GenericData genericData23 = schemaListGenericDatumWriter22.getData();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter24 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData23);
        org.apache.avro.Schema.Field[] fieldArray25 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList26 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList26, fieldArray25);
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList26);
        org.apache.avro.Schema schema29 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList26);
        boolean boolean30 = schema29.hasFields();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema29);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schemaArray10);
        org.junit.Assert.assertArrayEquals(schemaArray10, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(schema13);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertNotNull(schemaArray15);
        org.junit.Assert.assertArrayEquals(schemaArray15, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(schema18);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(genericData23);
        org.junit.Assert.assertNotNull(fieldArray25);
        org.junit.Assert.assertArrayEquals(fieldArray25, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(schema28);
        org.junit.Assert.assertNotNull(schema29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + true + "'", boolean30 == true);
    }

    @Test
    public void test517() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test517");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser9 = parser7.setValidateDefaults(true);
        org.apache.avro.Schema.Parser parser10 = new org.apache.avro.Schema.Parser();
        boolean boolean11 = parser10.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap12 = parser10.getTypes();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser17 = parser10.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser18 = parser7.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        java.lang.String[] strArray21 = new java.lang.String[] { "[]" };
        org.apache.avro.Schema schema22 = parser18.parse("", strArray21);
        org.apache.avro.Schema[] schemaArray23 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList24 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList24, schemaArray23);
        org.apache.avro.Schema schema26 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList24);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer27 = null;
        schema26.forEachProperty(strBiConsumer27);
        java.lang.Integer int30 = schema26.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap31 = schema26.getObjectProps();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter32 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema26);
        java.lang.String str34 = schema26.toString(true);
        org.apache.avro.Schema.Parser parser36 = new org.apache.avro.Schema.Parser();
        boolean boolean37 = parser36.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap38 = parser36.getTypes();
        schema26.addProp("[ ]", (java.lang.Object) strMap38);
        org.apache.avro.Schema.Parser parser40 = parser18.addTypes(strMap38);
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap41 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Parser parser42 = parser40.addTypes(strMap41);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.util.Map.values()\" because \"types\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertNotNull(parser9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(strMap12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNotNull(parser17);
        org.junit.Assert.assertNotNull(parser18);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertArrayEquals(strArray21, new java.lang.String[] { "[]" });
        org.junit.Assert.assertNotNull(schema22);
        org.junit.Assert.assertNotNull(schemaArray23);
        org.junit.Assert.assertArrayEquals(schemaArray23, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertNotNull(schema26);
        org.junit.Assert.assertNull(int30);
        org.junit.Assert.assertNotNull(strMap31);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "[ ]" + "'", str34, "[ ]");
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(strMap38);
        org.junit.Assert.assertNotNull(parser40);
    }

    @Test
    public void test518() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test518");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        org.apache.avro.Schema[] schemaArray16 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList17 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList17, schemaArray16);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList17);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer20 = null;
        schema19.forEachProperty(strBiConsumer20);
        java.lang.Integer int23 = schema19.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type24 = schema19.getType();
        org.apache.avro.Schema[] schemaArray25 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList26 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList26, schemaArray25);
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList26);
        java.lang.String str29 = schema28.getDoc();
        boolean boolean30 = schema28.hasProps();
        schema19.addAllProps((org.apache.avro.JsonProperties) schema28);
        org.apache.avro.LogicalType logicalType32 = schema28.getLogicalType();
        org.apache.avro.Schema.SeenPair seenPair33 = new org.apache.avro.Schema.SeenPair((java.lang.Object) schema3, (java.lang.Object) logicalType32);
        java.lang.String str35 = schema3.getProp("");
        org.apache.avro.Schema[] schemaArray36 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList37 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean38 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList37, schemaArray36);
        org.apache.avro.Schema schema39 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList37);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer40 = null;
        schema39.forEachProperty(strBiConsumer40);
        java.lang.Integer int43 = schema39.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type44 = schema39.getType();
        org.apache.avro.generic.GenericData genericData45 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter46 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema39, genericData45);
        java.lang.String str47 = schema39.getFullName();
        java.lang.String str48 = schema39.getDoc();
        org.apache.avro.Schema schema49 = org.apache.avro.Schema.createArray(schema39);
        org.apache.avro.Schema schema50 = org.apache.avro.Schema.applyAliases(schema3, schema39);
        java.lang.Integer int52 = schema3.getIndexNamed("[ ]");
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(schemaArray16);
        org.junit.Assert.assertArrayEquals(schemaArray16, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(schema19);
        org.junit.Assert.assertNull(int23);
        org.junit.Assert.assertTrue("'" + type24 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type24.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray25);
        org.junit.Assert.assertArrayEquals(schemaArray25, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(schema28);
        org.junit.Assert.assertNull(str29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNull(logicalType32);
        org.junit.Assert.assertNull(str35);
        org.junit.Assert.assertNotNull(schemaArray36);
        org.junit.Assert.assertArrayEquals(schemaArray36, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertNotNull(schema39);
        org.junit.Assert.assertNull(int43);
        org.junit.Assert.assertTrue("'" + type44 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type44.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str47 + "' != '" + "union[]" + "'", str47, "union[]");
        org.junit.Assert.assertNull(str48);
        org.junit.Assert.assertNotNull(schema49);
        org.junit.Assert.assertNotNull(schema50);
        org.junit.Assert.assertNull(int52);
    }

    @Test
    public void test519() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test519");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        org.apache.avro.LogicalType logicalType4 = schema3.getLogicalType();
        java.lang.String str5 = schema3.getDoc();
        java.lang.String str6 = schema3.getName();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.generic.GenericData genericData16 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter17 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema10, genericData16);
        java.lang.String str18 = schema10.getFullName();
        java.lang.String str19 = schema10.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream21 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream21.write((int) '#');
        byte[] byteArray26 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream21.writeBytes(byteArray26);
        byteArrayOutputStream21.reset();
        byteArrayOutputStream21.write((int) (byte) 0);
        byteArrayOutputStream21.reset();
        byteArrayOutputStream21.write(1);
        byte[] byteArray34 = byteArrayOutputStream21.toByteArray();
        boolean boolean35 = schema10.equals((java.lang.Object) byteArray34);
        java.util.List<org.apache.avro.Schema> schemaList36 = schema10.getTypes();
        boolean boolean37 = schema3.equals((java.lang.Object) schema10);
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createMap(schema3);
        java.lang.String str39 = schema38.getDoc();
        java.lang.String str41 = schema38.toString(false);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(logicalType4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "union[]" + "'", str6, "union[]");
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "union[]" + "'", str18, "union[]");
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertEquals(byteArrayOutputStream21.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertNotNull(schemaList36);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(schema38);
        org.junit.Assert.assertNull(str39);
        org.junit.Assert.assertEquals("'" + str41 + "' != '" + "{\"type\":\"map\",\"values\":[]}" + "'", str41, "{\"type\":\"map\",\"values\":[]}");
    }

    @Test
    public void test520() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test520");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter12 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema8);
        java.util.List<org.apache.avro.Schema> schemaList13 = schema8.getTypes();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = schema8.getEnumDefault();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(schemaList13);
    }

    @Test
    public void test521() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test521");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createArray(schema3);
        // The following exception was thrown during execution in test generation
        try {
            int int11 = schema3.getEnumOrdinal("\u2301");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schema9);
    }

    @Test
    public void test522() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test522");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        byteArrayOutputStream1.write((int) 'a');
        byte[] byteArray22 = byteArrayOutputStream1.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream24.reset();
        byteArrayOutputStream24.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream35 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream35.write((int) '#');
        byte[] byteArray40 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream35.writeBytes(byteArray40);
        byteArrayOutputStream24.writeBytes(byteArray40);
        byteArrayOutputStream24.write((int) 'a');
        byte[] byteArray45 = byteArrayOutputStream24.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream47 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream47.write((int) '#');
        byte[] byteArray52 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream47.writeBytes(byteArray52);
        byteArrayOutputStream47.reset();
        byte[] byteArray55 = byteArrayOutputStream47.toByteArray();
        byteArrayOutputStream24.write(byteArray55);
        byteArrayOutputStream1.write(byteArray55);
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream35.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray40);
        org.junit.Assert.assertArrayEquals(byteArray40, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray45);
        org.junit.Assert.assertArrayEquals(byteArray45, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream47.toString(), "");
        org.junit.Assert.assertNotNull(byteArray52);
        org.junit.Assert.assertArrayEquals(byteArray52, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] {});
    }

    @Test
    public void test523() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test523");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter8 = charSequenceDataFileWriter1.setMeta("#", (-1L));
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter1.append((java.lang.CharSequence) "{\"type\":\"map\",\"values\":[]}");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter8);
    }

    @Test
    public void test524() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test524");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        boolean boolean17 = schema12.equals((java.lang.Object) "#");
        org.apache.avro.generic.GenericData genericData18 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter19 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema12, genericData18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer20 = null;
        schema12.forEachProperty(strBiConsumer20);
        boolean boolean22 = schema12.isUnion();
        org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>> schemaListGenericDatumWriterGenericDatumWriter23 = new org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>>(schema12);
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<org.apache.avro.Schema.Field> fieldList24 = schema12.getFields();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test525() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test525");
        org.apache.avro.NameValidator nameValidator0 = null;
        org.apache.avro.Schema.Parser parser1 = new org.apache.avro.Schema.Parser(nameValidator0);
        org.apache.avro.Schema.Parser parser2 = new org.apache.avro.Schema.Parser();
        boolean boolean3 = parser2.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap4 = parser2.getTypes();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        org.apache.avro.Schema.Parser parser9 = parser2.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList6);
        org.apache.avro.Schema.Parser parser10 = new org.apache.avro.Schema.Parser();
        boolean boolean11 = parser10.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap12 = parser10.getTypes();
        org.apache.avro.Schema.Parser parser13 = parser2.addTypes(strMap12);
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap14 = parser13.getTypes();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap15 = parser13.getTypes();
        org.apache.avro.Schema.Parser parser16 = parser1.addTypes(strMap15);
        boolean boolean17 = parser1.getValidateDefaults();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertNotNull(strMap4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNotNull(parser9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(strMap12);
        org.junit.Assert.assertNotNull(parser13);
        org.junit.Assert.assertNotNull(strMap14);
        org.junit.Assert.assertNotNull(strMap15);
        org.junit.Assert.assertNotNull(parser16);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
    }

    @Test
    public void test526() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test526");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema2 = org.apache.avro.Schema.parse("\u3400\u3401\u340a", true);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: com.fasterxml.jackson.core.JsonParseException: Unrecognized token '???': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test527() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test527");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        java.lang.String str13 = schema3.getProp("#");
        boolean boolean14 = schema3.hasProps();
        org.apache.avro.LogicalType logicalType15 = schema3.getLogicalType();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNull(logicalType15);
    }

    @Test
    public void test528() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test528");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        schema3.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Field.Order order15 = org.apache.avro.Schema.Field.Order.IGNORE;
        java.lang.Object obj16 = schema3.getObjectProp("hi!", (java.lang.Object) order15);
        org.apache.avro.Schema[] schemaArray17 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList18 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList18, schemaArray17);
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList18);
        java.lang.String str21 = schema20.getDoc();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema20.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter29 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema25);
        java.util.List<org.apache.avro.Schema> schemaList30 = schema25.getTypes();
        java.lang.String str32 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList30, false);
        java.lang.String str34 = schema3.toString(true);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + order15 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order15.equals(org.apache.avro.Schema.Field.Order.IGNORE));
        org.junit.Assert.assertEquals("'" + obj16 + "' != '" + 100L + "'", obj16, 100L);
        org.junit.Assert.assertNotNull(schemaArray17);
        org.junit.Assert.assertArrayEquals(schemaArray17, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(schema20);
        org.junit.Assert.assertNull(str21);
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(schemaList30);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "[]" + "'", str32, "[]");
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "[ ]" + "'", str34, "[ ]");
    }

    @Test
    public void test529() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test529");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        java.lang.String str13 = schema3.getProp("#");
        // The following exception was thrown during execution in test generation
        try {
            java.util.Set<java.lang.String> strSet14 = schema3.getAliases();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str13);
    }

    @Test
    public void test530() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test530");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream1.reset();
        byte[] byteArray3 = byteArrayOutputStream1.toByteArray();
        org.apache.avro.Schema[] schemaArray4 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList5 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList5, schemaArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList5);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer8 = null;
        schema7.forEachProperty(strBiConsumer8);
        java.lang.Integer int11 = schema7.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type12 = schema7.getType();
        org.apache.avro.generic.GenericData genericData13 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter14 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema7, genericData13);
        org.apache.avro.generic.GenericData genericData15 = schemaListGenericDatumWriter14.getData();
        org.apache.avro.generic.GenericData genericData16 = schemaListGenericDatumWriter14.getData();
        org.apache.avro.Schema[] schemaArray17 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList18 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList18, schemaArray17);
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer21 = null;
        schema20.forEachProperty(strBiConsumer21);
        java.lang.Integer int24 = schema20.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type25 = schema20.getType();
        org.apache.avro.Schema[] schemaArray26 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList27 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList27, schemaArray26);
        org.apache.avro.Schema schema29 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList27);
        java.lang.String str30 = schema29.getDoc();
        boolean boolean31 = schema29.hasProps();
        schema20.addAllProps((org.apache.avro.JsonProperties) schema29);
        org.apache.avro.LogicalType logicalType33 = schema29.getLogicalType();
        schemaListGenericDatumWriter14.setSchema(schema29);
        java.io.ByteArrayOutputStream byteArrayOutputStream37 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream37.write((int) '#');
        byte[] byteArray42 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream37.writeBytes(byteArray42);
        byteArrayOutputStream37.reset();
        byteArrayOutputStream37.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream48 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream48.write((int) '#');
        byte[] byteArray53 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream48.writeBytes(byteArray53);
        byteArrayOutputStream37.writeBytes(byteArray53);
        java.lang.Object obj56 = schema29.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream37);
        byteArrayOutputStream1.writeTo((java.io.OutputStream) byteArrayOutputStream37);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str59 = byteArrayOutputStream37.toString("org.apache.avro.file.DataFileWriter$AppendWriteException: org.apache.avro.file.DataFileWriter$AppendWriteException");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: org.apache.avro.file.DataFileWriter$AppendWriteException: org.apache.avro.file.DataFileWriter$AppendWriteException");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(schemaArray4);
        org.junit.Assert.assertArrayEquals(schemaArray4, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(schema7);
        org.junit.Assert.assertNull(int11);
        org.junit.Assert.assertTrue("'" + type12 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type12.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData15);
        org.junit.Assert.assertNull(genericData16);
        org.junit.Assert.assertNotNull(schemaArray17);
        org.junit.Assert.assertArrayEquals(schemaArray17, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(schema20);
        org.junit.Assert.assertNull(int24);
        org.junit.Assert.assertTrue("'" + type25 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type25.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray26);
        org.junit.Assert.assertArrayEquals(schemaArray26, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(schema29);
        org.junit.Assert.assertNull(str30);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNull(logicalType33);
        org.junit.Assert.assertEquals(byteArrayOutputStream37.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray42);
        org.junit.Assert.assertArrayEquals(byteArray42, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream48.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray53);
        org.junit.Assert.assertArrayEquals(byteArray53, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj56);
        org.junit.Assert.assertEquals(obj56.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj56), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj56), "\000\001\n");
    }

    @Test
    public void test531() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test531");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        org.apache.avro.Schema[] schemaArray16 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList17 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList17, schemaArray16);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList17);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer20 = null;
        schema19.forEachProperty(strBiConsumer20);
        java.lang.Integer int23 = schema19.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type24 = schema19.getType();
        org.apache.avro.Schema[] schemaArray25 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList26 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList26, schemaArray25);
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList26);
        java.lang.String str29 = schema28.getDoc();
        boolean boolean30 = schema28.hasProps();
        schema19.addAllProps((org.apache.avro.JsonProperties) schema28);
        org.apache.avro.LogicalType logicalType32 = schema28.getLogicalType();
        org.apache.avro.Schema.SeenPair seenPair33 = new org.apache.avro.Schema.SeenPair((java.lang.Object) schema3, (java.lang.Object) logicalType32);
        // The following exception was thrown during execution in test generation
        try {
            int int34 = schema3.getFixedSize();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not fixed: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(schemaArray16);
        org.junit.Assert.assertArrayEquals(schemaArray16, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(schema19);
        org.junit.Assert.assertNull(int23);
        org.junit.Assert.assertTrue("'" + type24 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type24.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray25);
        org.junit.Assert.assertArrayEquals(schemaArray25, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(schema28);
        org.junit.Assert.assertNull(str29);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNull(logicalType32);
    }

    @Test
    public void test532() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test532");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        boolean boolean17 = schema12.equals((java.lang.Object) "#");
        org.apache.avro.generic.GenericData genericData18 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter19 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema12, genericData18);
        java.lang.String str20 = schema12.getFullName();
        org.apache.avro.Schema[] schemaArray21 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList22 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList22, schemaArray21);
        org.apache.avro.Schema schema24 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList22);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer25 = null;
        schema24.forEachProperty(strBiConsumer25);
        java.lang.Integer int28 = schema24.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type29 = schema24.getType();
        org.apache.avro.Schema[] schemaArray30 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList31 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList31, schemaArray30);
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList31);
        java.lang.String str34 = schema33.getDoc();
        boolean boolean35 = schema33.hasProps();
        schema24.addAllProps((org.apache.avro.JsonProperties) schema33);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer37 = null;
        schema24.forEachProperty(strBiConsumer37);
        schema12.putAll((org.apache.avro.JsonProperties) schema24);
        java.lang.String str40 = schema24.getFullName();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "union[]" + "'", str20, "union[]");
        org.junit.Assert.assertNotNull(schemaArray21);
        org.junit.Assert.assertArrayEquals(schemaArray21, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(schema24);
        org.junit.Assert.assertNull(int28);
        org.junit.Assert.assertTrue("'" + type29 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type29.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray30);
        org.junit.Assert.assertArrayEquals(schemaArray30, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNotNull(schema33);
        org.junit.Assert.assertNull(str34);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "union[]" + "'", str40, "union[]");
    }

    @Test
    public void test533() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test533");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        java.lang.Integer int9 = schema3.getIndexNamed("");
        boolean boolean10 = schema3.isUnion();
        boolean boolean12 = schema3.propsContainsKey("hi!");
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        java.lang.String str23 = schema16.getProp("enum");
        schema16.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Field.Order order28 = org.apache.avro.Schema.Field.Order.IGNORE;
        java.lang.Object obj29 = schema16.getObjectProp("hi!", (java.lang.Object) order28);
        org.apache.avro.Schema[] schemaArray30 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList31 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList31, schemaArray30);
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList31);
        java.lang.String str34 = schema33.getDoc();
        org.apache.avro.Schema[] schemaArray35 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList36 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList36, schemaArray35);
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList36);
        java.lang.String str39 = schema38.getDoc();
        boolean boolean40 = schema38.hasProps();
        schema33.addAllProps((org.apache.avro.JsonProperties) schema38);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter42 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema38);
        java.util.List<org.apache.avro.Schema> schemaList43 = schema38.getTypes();
        java.lang.String str45 = schema16.toString((java.util.Collection<org.apache.avro.Schema>) schemaList43, false);
        java.lang.String str47 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList43, true);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertNull(int9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str23);
        org.junit.Assert.assertTrue("'" + order28 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order28.equals(org.apache.avro.Schema.Field.Order.IGNORE));
        org.junit.Assert.assertEquals("'" + obj29 + "' != '" + 100L + "'", obj29, 100L);
        org.junit.Assert.assertNotNull(schemaArray30);
        org.junit.Assert.assertArrayEquals(schemaArray30, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNotNull(schema33);
        org.junit.Assert.assertNull(str34);
        org.junit.Assert.assertNotNull(schemaArray35);
        org.junit.Assert.assertArrayEquals(schemaArray35, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNotNull(schema38);
        org.junit.Assert.assertNull(str39);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertNotNull(schemaList43);
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "[]" + "'", str45, "[]");
        org.junit.Assert.assertEquals("'" + str47 + "' != '" + "[ ]" + "'", str47, "[ ]");
    }

    @Test
    public void test534() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test534");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) ' ');
        byte[] byteArray2 = byteArrayOutputStream1.toByteArray();
        byteArrayOutputStream1.reset();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
    }

    @Test
    public void test535() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test535");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        java.lang.String str11 = schema3.getFullName();
        java.lang.String str12 = schema3.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream14 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream14.write((int) '#');
        byte[] byteArray19 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream14.writeBytes(byteArray19);
        byteArrayOutputStream14.reset();
        byteArrayOutputStream14.write((int) (byte) 0);
        byteArrayOutputStream14.reset();
        byteArrayOutputStream14.write(1);
        byte[] byteArray27 = byteArrayOutputStream14.toByteArray();
        boolean boolean28 = schema3.equals((java.lang.Object) byteArray27);
        org.apache.avro.Schema[] schemaArray29 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList30 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList30, schemaArray29);
        org.apache.avro.Schema schema32 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList30);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer33 = null;
        schema32.forEachProperty(strBiConsumer33);
        java.lang.Integer int36 = schema32.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type37 = schema32.getType();
        org.apache.avro.Schema[] schemaArray38 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList39 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean40 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList39, schemaArray38);
        org.apache.avro.Schema schema41 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList39);
        java.lang.String str42 = schema41.getDoc();
        boolean boolean43 = schema41.hasProps();
        schema32.addAllProps((org.apache.avro.JsonProperties) schema41);
        boolean boolean46 = schema41.equals((java.lang.Object) "#");
        boolean boolean48 = schema41.equals((java.lang.Object) "[]");
        org.apache.avro.Schema.Field[] fieldArray50 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList51 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList51, fieldArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList51);
        schema41.addProp("", (java.lang.Object) fieldList51);
        // The following exception was thrown during execution in test generation
        try {
            schema3.setFields((java.util.List<org.apache.avro.Schema.Field>) fieldList51);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "union[]" + "'", str11, "union[]");
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertEquals(byteArrayOutputStream14.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray27);
        org.junit.Assert.assertArrayEquals(byteArray27, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(schemaArray29);
        org.junit.Assert.assertArrayEquals(schemaArray29, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNotNull(schema32);
        org.junit.Assert.assertNull(int36);
        org.junit.Assert.assertTrue("'" + type37 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type37.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray38);
        org.junit.Assert.assertArrayEquals(schemaArray38, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertNotNull(schema41);
        org.junit.Assert.assertNull(str42);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertNotNull(fieldArray50);
        org.junit.Assert.assertArrayEquals(fieldArray50, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
    }

    @Test
    public void test536() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test536");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        java.nio.ByteBuffer byteBuffer36 = null;
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter4.appendEncoded(byteBuffer36);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
    }

    @Test
    public void test537() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test537");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        com.fasterxml.jackson.databind.JsonNode jsonNode5 = null;
        boolean boolean6 = schema3.isValidDefault(jsonNode5);
        boolean boolean7 = schema3.isNullable();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test538() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test538");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema schema4 = parser0.parseInternal("[]");
        org.apache.avro.Schema.Parser parser5 = new org.apache.avro.Schema.Parser();
        boolean boolean6 = parser5.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap7 = parser5.getTypes();
        org.apache.avro.Schema[] schemaArray8 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList9 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList9, schemaArray8);
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList9);
        org.apache.avro.Schema.Parser parser12 = parser5.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList9);
        org.apache.avro.Schema.Parser parser13 = new org.apache.avro.Schema.Parser();
        boolean boolean14 = parser13.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap15 = parser13.getTypes();
        org.apache.avro.Schema.Parser parser16 = parser5.addTypes(strMap15);
        org.apache.avro.Schema.Parser parser17 = parser0.addTypes(strMap15);
        org.apache.avro.Schema.Parser parser19 = parser17.setValidateDefaults(false);
        boolean boolean20 = parser17.getValidateDefaults();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(strMap7);
        org.junit.Assert.assertNotNull(schemaArray8);
        org.junit.Assert.assertArrayEquals(schemaArray8, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(schema11);
        org.junit.Assert.assertNotNull(parser12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertNotNull(strMap15);
        org.junit.Assert.assertNotNull(parser16);
        org.junit.Assert.assertNotNull(parser17);
        org.junit.Assert.assertNotNull(parser19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
    }

    @Test
    public void test539() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test539");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema16.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.LogicalType logicalType29 = schema25.getLogicalType();
        schemaListGenericDatumWriter10.setSchema(schema25);
        java.io.ByteArrayOutputStream byteArrayOutputStream33 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream33.write((int) '#');
        byte[] byteArray38 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream33.writeBytes(byteArray38);
        byteArrayOutputStream33.reset();
        byteArrayOutputStream33.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream44 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream44.write((int) '#');
        byte[] byteArray49 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream44.writeBytes(byteArray49);
        byteArrayOutputStream33.writeBytes(byteArray49);
        java.lang.Object obj52 = schema25.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream33);
        java.lang.Integer int54 = schema25.getIndexNamed("array");
        org.apache.avro.Schema[] schemaArray55 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList56 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean57 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList56, schemaArray55);
        org.apache.avro.Schema schema58 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList56);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer59 = null;
        schema58.forEachProperty(strBiConsumer59);
        java.lang.Integer int62 = schema58.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type63 = schema58.getType();
        org.apache.avro.generic.GenericData genericData64 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter65 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema58, genericData64);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter66 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema58);
        org.apache.avro.Schema schema67 = null;
        schemaListGenericDatumWriter66.setSchema(schema67);
        org.apache.avro.generic.GenericData genericData69 = schemaListGenericDatumWriter66.getData();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter70 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema25, genericData69);
        org.apache.avro.Schema.Parser parser71 = new org.apache.avro.Schema.Parser();
        boolean boolean72 = parser71.getValidateDefaults();
        org.apache.avro.Schema.Parser parser74 = parser71.setValidateDefaults(false);
        org.apache.avro.Schema[] schemaArray75 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList76 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean77 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList76, schemaArray75);
        org.apache.avro.Schema schema78 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList76);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer79 = null;
        schema78.forEachProperty(strBiConsumer79);
        java.lang.Integer int82 = schema78.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap83 = schema78.getObjectProps();
        org.apache.avro.Schema[] schemaArray84 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList85 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean86 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList85, schemaArray84);
        org.apache.avro.Schema schema87 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList85);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer88 = null;
        schema87.forEachProperty(strBiConsumer88);
        java.lang.Integer int91 = schema87.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type92 = schema87.getType();
        java.lang.String str94 = schema87.getProp("enum");
        boolean boolean96 = schema87.propsContainsKey("");
        boolean boolean97 = schema87.isUnion();
        java.lang.String str98 = schema87.toString();
        boolean boolean99 = schema78.equals((java.lang.Object) str98);
        java.util.List<org.apache.avro.Schema> schemaList100 = schema78.getTypes();
        org.apache.avro.Schema schema101 = org.apache.avro.Schema.createUnion(schemaList100);
        org.apache.avro.Schema.Parser parser102 = parser71.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList100);
        java.lang.String str104 = schema25.toString((java.util.Collection<org.apache.avro.Schema>) schemaList100, true);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(logicalType29);
        org.junit.Assert.assertEquals(byteArrayOutputStream33.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream44.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj52);
        org.junit.Assert.assertEquals(obj52.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj52), "\000\001\n");
        org.junit.Assert.assertNull(int54);
        org.junit.Assert.assertNotNull(schemaArray55);
        org.junit.Assert.assertArrayEquals(schemaArray55, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean57 + "' != '" + false + "'", boolean57 == false);
        org.junit.Assert.assertNotNull(schema58);
        org.junit.Assert.assertNull(int62);
        org.junit.Assert.assertTrue("'" + type63 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type63.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(genericData69);
        org.junit.Assert.assertTrue("'" + boolean72 + "' != '" + true + "'", boolean72 == true);
        org.junit.Assert.assertNotNull(parser74);
        org.junit.Assert.assertNotNull(schemaArray75);
        org.junit.Assert.assertArrayEquals(schemaArray75, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean77 + "' != '" + false + "'", boolean77 == false);
        org.junit.Assert.assertNotNull(schema78);
        org.junit.Assert.assertNull(int82);
        org.junit.Assert.assertNotNull(strMap83);
        org.junit.Assert.assertNotNull(schemaArray84);
        org.junit.Assert.assertArrayEquals(schemaArray84, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean86 + "' != '" + false + "'", boolean86 == false);
        org.junit.Assert.assertNotNull(schema87);
        org.junit.Assert.assertNull(int91);
        org.junit.Assert.assertTrue("'" + type92 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type92.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str94);
        org.junit.Assert.assertTrue("'" + boolean96 + "' != '" + false + "'", boolean96 == false);
        org.junit.Assert.assertTrue("'" + boolean97 + "' != '" + true + "'", boolean97 == true);
        org.junit.Assert.assertEquals("'" + str98 + "' != '" + "[]" + "'", str98, "[]");
        org.junit.Assert.assertTrue("'" + boolean99 + "' != '" + false + "'", boolean99 == false);
        org.junit.Assert.assertNotNull(schemaList100);
        org.junit.Assert.assertNotNull(schema101);
        org.junit.Assert.assertNotNull(parser102);
        org.junit.Assert.assertEquals("'" + str104 + "' != '" + "[ ]" + "'", str104, "[ ]");
    }

    @Test
    public void test540() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test540");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        boolean boolean12 = schema3.isNullable();
        // The following exception was thrown during execution in test generation
        try {
            int int14 = schema3.getEnumOrdinal("enum");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
    }

    @Test
    public void test541() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test541");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        org.apache.avro.Schema.Parser parser3 = parser0.setValidateDefaults(false);
        org.apache.avro.Schema.Parser parser4 = new org.apache.avro.Schema.Parser();
        boolean boolean5 = parser4.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap6 = parser4.getTypes();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        org.apache.avro.Schema.Parser parser11 = parser4.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList8);
        org.apache.avro.Schema.Parser parser12 = new org.apache.avro.Schema.Parser();
        boolean boolean13 = parser12.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap14 = parser12.getTypes();
        org.apache.avro.Schema.Parser parser15 = parser4.addTypes(strMap14);
        org.apache.avro.Schema.Parser parser16 = parser3.addTypes(strMap14);
        org.apache.avro.Schema[] schemaArray17 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList18 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList18, schemaArray17);
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer21 = null;
        schema20.forEachProperty(strBiConsumer21);
        java.lang.Integer int24 = schema20.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type25 = schema20.getType();
        java.lang.String str27 = schema20.getProp("enum");
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createArray(schema20);
        org.apache.avro.Schema.SeenPair seenPair29 = new org.apache.avro.Schema.SeenPair((java.lang.Object) parser3, (java.lang.Object) schema28);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(parser3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertNotNull(strMap6);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNotNull(parser11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertNotNull(strMap14);
        org.junit.Assert.assertNotNull(parser15);
        org.junit.Assert.assertNotNull(parser16);
        org.junit.Assert.assertNotNull(schemaArray17);
        org.junit.Assert.assertArrayEquals(schemaArray17, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(schema20);
        org.junit.Assert.assertNull(int24);
        org.junit.Assert.assertTrue("'" + type25 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type25.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str27);
        org.junit.Assert.assertNotNull(schema28);
    }

    @Test
    public void test542() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test542");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        org.apache.avro.LogicalType logicalType4 = schema3.getLogicalType();
        java.lang.String str5 = schema3.getDoc();
        java.lang.String str6 = schema3.getName();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.generic.GenericData genericData16 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter17 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema10, genericData16);
        java.lang.String str18 = schema10.getFullName();
        java.lang.String str19 = schema10.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream21 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream21.write((int) '#');
        byte[] byteArray26 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream21.writeBytes(byteArray26);
        byteArrayOutputStream21.reset();
        byteArrayOutputStream21.write((int) (byte) 0);
        byteArrayOutputStream21.reset();
        byteArrayOutputStream21.write(1);
        byte[] byteArray34 = byteArrayOutputStream21.toByteArray();
        boolean boolean35 = schema10.equals((java.lang.Object) byteArray34);
        java.util.List<org.apache.avro.Schema> schemaList36 = schema10.getTypes();
        boolean boolean37 = schema3.equals((java.lang.Object) schema10);
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createMap(schema3);
        java.lang.Object obj40 = schema38.getObjectProp("[ ]");
        // The following exception was thrown during execution in test generation
        try {
            int int41 = schema38.getFixedSize();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not fixed: {\"type\":\"map\",\"values\":[]}");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(logicalType4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "union[]" + "'", str6, "union[]");
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "union[]" + "'", str18, "union[]");
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertEquals(byteArrayOutputStream21.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray26);
        org.junit.Assert.assertArrayEquals(byteArray26, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertNotNull(schemaList36);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertNotNull(schema38);
        org.junit.Assert.assertNull(obj40);
    }

    @Test
    public void test543() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test543");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        org.apache.avro.Schema[] schemaArray37 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList38 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList38, schemaArray37);
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList38);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer41 = null;
        schema40.forEachProperty(strBiConsumer41);
        java.lang.Integer int44 = schema40.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type45 = schema40.getType();
        org.apache.avro.generic.GenericData genericData46 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter47 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema40, genericData46);
        org.apache.avro.generic.GenericData genericData48 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.generic.GenericData genericData49 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.Schema[] schemaArray50 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList51 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList51, schemaArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList51);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer54 = null;
        schema53.forEachProperty(strBiConsumer54);
        java.lang.Integer int57 = schema53.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type58 = schema53.getType();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.lang.String str63 = schema62.getDoc();
        boolean boolean64 = schema62.hasProps();
        schema53.addAllProps((org.apache.avro.JsonProperties) schema62);
        org.apache.avro.LogicalType logicalType66 = schema62.getLogicalType();
        schemaListGenericDatumWriter47.setSchema(schema62);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        java.lang.Object obj89 = schema62.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream70);
        java.io.ByteArrayOutputStream byteArrayOutputStream91 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream91.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream91.write(byteArray100);
        byteArrayOutputStream70.write(byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter104 = charSequenceDataFileWriter35.setMeta("\uff00\uff01\uff0a", byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter107 = charSequenceDataFileWriter35.setMeta("fixed", 0L);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction108 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter109 = charSequenceDataFileWriter35.setEncoder(outputStreamFunction108);
        charSequenceDataFileWriter35.setFlushOnEveryBlock(true);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter114 = charSequenceDataFileWriter35.setMeta("{\"type\":\"record\",\"fields\":[]}", "#\001\n");
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter35.append((java.lang.CharSequence) "{\"type\":\"map\",\"values\":[]}");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
        org.junit.Assert.assertNotNull(schemaArray37);
        org.junit.Assert.assertArrayEquals(schemaArray37, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schema40);
        org.junit.Assert.assertNull(int44);
        org.junit.Assert.assertTrue("'" + type45 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type45.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData48);
        org.junit.Assert.assertNull(genericData49);
        org.junit.Assert.assertNotNull(schemaArray50);
        org.junit.Assert.assertArrayEquals(schemaArray50, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNull(int57);
        org.junit.Assert.assertTrue("'" + type58 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type58.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(logicalType66);
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj89);
        org.junit.Assert.assertEquals(obj89.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream91.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter104);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter107);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter109);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter114);
    }

    @Test
    public void test544() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test544");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        java.lang.String str11 = schema3.getFullName();
        // The following exception was thrown during execution in test generation
        try {
            schema3.addProp("enum", "org.apache.avro.file.DataFileWriter$AppendWriteException");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Can't set properties on a union: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "union[]" + "'", str11, "union[]");
    }

    @Test
    public void test545() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test545");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        java.lang.String str11 = schema3.getFullName();
        java.lang.String str12 = schema3.getName();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "union[]" + "'", str11, "union[]");
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "union[]" + "'", str12, "union[]");
    }

    @Test
    public void test546() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test546");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.flush();
        int int10 = byteArrayOutputStream1.size();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test547() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test547");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean16 = schema12.hasFields();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test548() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test548");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        org.apache.avro.Schema[] schemaArray37 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList38 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList38, schemaArray37);
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList38);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer41 = null;
        schema40.forEachProperty(strBiConsumer41);
        java.lang.Integer int44 = schema40.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type45 = schema40.getType();
        org.apache.avro.generic.GenericData genericData46 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter47 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema40, genericData46);
        org.apache.avro.generic.GenericData genericData48 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.generic.GenericData genericData49 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.Schema[] schemaArray50 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList51 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList51, schemaArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList51);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer54 = null;
        schema53.forEachProperty(strBiConsumer54);
        java.lang.Integer int57 = schema53.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type58 = schema53.getType();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.lang.String str63 = schema62.getDoc();
        boolean boolean64 = schema62.hasProps();
        schema53.addAllProps((org.apache.avro.JsonProperties) schema62);
        org.apache.avro.LogicalType logicalType66 = schema62.getLogicalType();
        schemaListGenericDatumWriter47.setSchema(schema62);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        java.lang.Object obj89 = schema62.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream70);
        java.io.ByteArrayOutputStream byteArrayOutputStream91 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream91.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream91.write(byteArray100);
        byteArrayOutputStream70.write(byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter104 = charSequenceDataFileWriter35.setMeta("\uff00\uff01\uff0a", byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter107 = charSequenceDataFileWriter35.setMeta("fixed", 0L);
        boolean boolean108 = charSequenceDataFileWriter107.isFlushOnEveryBlock();
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
        org.junit.Assert.assertNotNull(schemaArray37);
        org.junit.Assert.assertArrayEquals(schemaArray37, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schema40);
        org.junit.Assert.assertNull(int44);
        org.junit.Assert.assertTrue("'" + type45 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type45.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData48);
        org.junit.Assert.assertNull(genericData49);
        org.junit.Assert.assertNotNull(schemaArray50);
        org.junit.Assert.assertArrayEquals(schemaArray50, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNull(int57);
        org.junit.Assert.assertTrue("'" + type58 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type58.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(logicalType66);
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj89);
        org.junit.Assert.assertEquals(obj89.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream91.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter104);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter107);
        org.junit.Assert.assertTrue("'" + boolean108 + "' != '" + true + "'", boolean108 == true);
    }

    @Test
    public void test549() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test549");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        boolean boolean6 = schema3.isUnion();
        // The following exception was thrown during execution in test generation
        try {
            int int7 = schema3.getFixedSize();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not fixed: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
    }

    @Test
    public void test550() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test550");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        org.apache.avro.Schema[] schemaArray37 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList38 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList38, schemaArray37);
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList38);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer41 = null;
        schema40.forEachProperty(strBiConsumer41);
        java.lang.Integer int44 = schema40.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type45 = schema40.getType();
        org.apache.avro.generic.GenericData genericData46 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter47 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema40, genericData46);
        org.apache.avro.generic.GenericData genericData48 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.generic.GenericData genericData49 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.Schema[] schemaArray50 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList51 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList51, schemaArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList51);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer54 = null;
        schema53.forEachProperty(strBiConsumer54);
        java.lang.Integer int57 = schema53.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type58 = schema53.getType();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.lang.String str63 = schema62.getDoc();
        boolean boolean64 = schema62.hasProps();
        schema53.addAllProps((org.apache.avro.JsonProperties) schema62);
        org.apache.avro.LogicalType logicalType66 = schema62.getLogicalType();
        schemaListGenericDatumWriter47.setSchema(schema62);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        java.lang.Object obj89 = schema62.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream70);
        java.io.ByteArrayOutputStream byteArrayOutputStream91 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream91.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream91.write(byteArray100);
        byteArrayOutputStream70.write(byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter104 = charSequenceDataFileWriter35.setMeta("\uff00\uff01\uff0a", byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter107 = charSequenceDataFileWriter35.setMeta("fixed", 0L);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter35.append((java.lang.CharSequence) "{\"type\":\"record\",\"fields\":[]}");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
        org.junit.Assert.assertNotNull(schemaArray37);
        org.junit.Assert.assertArrayEquals(schemaArray37, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schema40);
        org.junit.Assert.assertNull(int44);
        org.junit.Assert.assertTrue("'" + type45 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type45.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData48);
        org.junit.Assert.assertNull(genericData49);
        org.junit.Assert.assertNotNull(schemaArray50);
        org.junit.Assert.assertArrayEquals(schemaArray50, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNull(int57);
        org.junit.Assert.assertTrue("'" + type58 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type58.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(logicalType66);
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj89);
        org.junit.Assert.assertEquals(obj89.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream91.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter104);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter107);
    }

    @Test
    public void test551() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test551");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction7 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter8 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction7);
        charSequenceDataFileWriter8.setFlushOnEveryBlock(false);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter8);
    }

    @Test
    public void test552() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test552");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        java.lang.String str12 = schema3.getProp("#");
        boolean boolean14 = schema3.propsContainsKey("enum");
        org.apache.avro.Schema[] schemaArray15 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList16 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList16, schemaArray15);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList16);
        java.lang.String str19 = schema18.getDoc();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema18);
        boolean boolean21 = schema3.isNullable();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNotNull(schemaArray15);
        org.junit.Assert.assertArrayEquals(schemaArray15, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(schema18);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
    }

    @Test
    public void test553() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test553");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap8 = schema3.getObjectProps();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter9 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3);
        java.lang.String str11 = schema3.toString(true);
        org.apache.avro.LogicalType logicalType12 = schema3.getLogicalType();
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter14 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter15 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter14);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter18 = charSequenceDataFileWriter15.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction19 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter20 = charSequenceDataFileWriter18.setEncoder(outputStreamFunction19);
        byte[] byteArray23 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter24 = charSequenceDataFileWriter18.setMeta("[ ]", byteArray23);
        java.io.ByteArrayOutputStream byteArrayOutputStream27 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream27.write((int) '#');
        byte[] byteArray32 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream27.writeBytes(byteArray32);
        byteArrayOutputStream27.reset();
        byteArrayOutputStream27.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream38 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream38.write((int) '#');
        byte[] byteArray43 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream38.writeBytes(byteArray43);
        byteArrayOutputStream27.writeBytes(byteArray43);
        byteArrayOutputStream27.write((int) 'a');
        byte[] byteArray48 = byteArrayOutputStream27.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter49 = charSequenceDataFileWriter18.setMeta("[ ]", byteArray48);
        org.apache.avro.Schema[] schemaArray51 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList52 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean53 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList52, schemaArray51);
        org.apache.avro.Schema schema54 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList52);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer55 = null;
        schema54.forEachProperty(strBiConsumer55);
        java.lang.Integer int58 = schema54.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type59 = schema54.getType();
        org.apache.avro.generic.GenericData genericData60 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter61 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema54, genericData60);
        org.apache.avro.generic.GenericData genericData62 = schemaListGenericDatumWriter61.getData();
        org.apache.avro.generic.GenericData genericData63 = schemaListGenericDatumWriter61.getData();
        org.apache.avro.Schema[] schemaArray64 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList65 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean66 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList65, schemaArray64);
        org.apache.avro.Schema schema67 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList65);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer68 = null;
        schema67.forEachProperty(strBiConsumer68);
        java.lang.Integer int71 = schema67.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type72 = schema67.getType();
        org.apache.avro.Schema[] schemaArray73 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList74 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean75 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList74, schemaArray73);
        org.apache.avro.Schema schema76 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList74);
        java.lang.String str77 = schema76.getDoc();
        boolean boolean78 = schema76.hasProps();
        schema67.addAllProps((org.apache.avro.JsonProperties) schema76);
        org.apache.avro.LogicalType logicalType80 = schema76.getLogicalType();
        schemaListGenericDatumWriter61.setSchema(schema76);
        java.io.ByteArrayOutputStream byteArrayOutputStream84 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream84.write((int) '#');
        byte[] byteArray89 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream84.writeBytes(byteArray89);
        byteArrayOutputStream84.reset();
        byteArrayOutputStream84.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream84.writeBytes(byteArray100);
        java.lang.Object obj103 = schema76.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream84);
        java.io.ByteArrayOutputStream byteArrayOutputStream105 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream105.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream109 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream109.write((int) '#');
        byte[] byteArray114 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream109.writeBytes(byteArray114);
        byteArrayOutputStream105.write(byteArray114);
        byteArrayOutputStream84.write(byteArray114);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter118 = charSequenceDataFileWriter49.setMeta("\uff00\uff01\uff0a", byteArray114);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter121 = charSequenceDataFileWriter49.setMeta("fixed", 0L);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction122 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter123 = charSequenceDataFileWriter49.setEncoder(outputStreamFunction122);
        // The following exception was thrown during execution in test generation
        try {
            schema3.addProp("[ ]", (java.lang.Object) charSequenceDataFileWriter49);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Unknown datum class: class org.apache.avro.file.DataFileWriter");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertNotNull(strMap8);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "[ ]" + "'", str11, "[ ]");
        org.junit.Assert.assertNull(logicalType12);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter18);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter20);
        org.junit.Assert.assertNotNull(byteArray23);
        org.junit.Assert.assertArrayEquals(byteArray23, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter24);
        org.junit.Assert.assertEquals(byteArrayOutputStream27.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray32);
        org.junit.Assert.assertArrayEquals(byteArray32, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream38.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray43);
        org.junit.Assert.assertArrayEquals(byteArray43, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray48);
        org.junit.Assert.assertArrayEquals(byteArray48, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter49);
        org.junit.Assert.assertNotNull(schemaArray51);
        org.junit.Assert.assertArrayEquals(schemaArray51, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean53 + "' != '" + false + "'", boolean53 == false);
        org.junit.Assert.assertNotNull(schema54);
        org.junit.Assert.assertNull(int58);
        org.junit.Assert.assertTrue("'" + type59 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type59.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData62);
        org.junit.Assert.assertNull(genericData63);
        org.junit.Assert.assertNotNull(schemaArray64);
        org.junit.Assert.assertArrayEquals(schemaArray64, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean66 + "' != '" + false + "'", boolean66 == false);
        org.junit.Assert.assertNotNull(schema67);
        org.junit.Assert.assertNull(int71);
        org.junit.Assert.assertTrue("'" + type72 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type72.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray73);
        org.junit.Assert.assertArrayEquals(schemaArray73, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean75 + "' != '" + false + "'", boolean75 == false);
        org.junit.Assert.assertNotNull(schema76);
        org.junit.Assert.assertNull(str77);
        org.junit.Assert.assertTrue("'" + boolean78 + "' != '" + false + "'", boolean78 == false);
        org.junit.Assert.assertNull(logicalType80);
        org.junit.Assert.assertEquals(byteArrayOutputStream84.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray89);
        org.junit.Assert.assertArrayEquals(byteArray89, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj103);
        org.junit.Assert.assertEquals(obj103.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj103), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj103), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream105.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream109.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray114);
        org.junit.Assert.assertArrayEquals(byteArray114, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter118);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter121);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter123);
    }

    @Test
    public void test554() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test554");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter7.setMeta("array", "fixed");
        boolean boolean11 = charSequenceDataFileWriter7.isFlushOnEveryBlock();
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test555() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test555");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write(1);
        byteArrayOutputStream1.write((int) ' ');
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\001 ");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
    }

    @Test
    public void test556() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test556");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser9 = parser7.setValidateDefaults(true);
        org.apache.avro.Schema.Parser parser10 = new org.apache.avro.Schema.Parser();
        boolean boolean11 = parser10.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap12 = parser10.getTypes();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser17 = parser10.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser18 = parser7.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        java.lang.String[] strArray21 = new java.lang.String[] { "[]" };
        org.apache.avro.Schema schema22 = parser18.parse("", strArray21);
        boolean boolean23 = parser18.getValidateDefaults();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertNotNull(parser9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(strMap12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNotNull(parser17);
        org.junit.Assert.assertNotNull(parser18);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertArrayEquals(strArray21, new java.lang.String[] { "[]" });
        org.junit.Assert.assertNotNull(schema22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
    }

    @Test
    public void test557() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test557");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema16.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.LogicalType logicalType29 = schema25.getLogicalType();
        schemaListGenericDatumWriter10.setSchema(schema25);
        java.io.ByteArrayOutputStream byteArrayOutputStream33 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream33.write((int) '#');
        byte[] byteArray38 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream33.writeBytes(byteArray38);
        byteArrayOutputStream33.reset();
        byteArrayOutputStream33.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream44 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream44.write((int) '#');
        byte[] byteArray49 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream44.writeBytes(byteArray49);
        byteArrayOutputStream33.writeBytes(byteArray49);
        java.lang.Object obj52 = schema25.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream33);
        java.io.ByteArrayOutputStream byteArrayOutputStream54 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream54.write((int) '#');
        byte[] byteArray59 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream54.writeBytes(byteArray59);
        byteArrayOutputStream54.reset();
        byte[] byteArray62 = byteArrayOutputStream54.toByteArray();
        byteArrayOutputStream33.writeTo((java.io.OutputStream) byteArrayOutputStream54);
        byteArrayOutputStream54.close();
        byteArrayOutputStream54.close();
        java.nio.charset.Charset charset66 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str67 = byteArrayOutputStream54.toString(charset66);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(logicalType29);
        org.junit.Assert.assertEquals(byteArrayOutputStream33.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream44.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj52);
        org.junit.Assert.assertEquals(obj52.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream54.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray59);
        org.junit.Assert.assertArrayEquals(byteArray59, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray62);
        org.junit.Assert.assertArrayEquals(byteArray62, new byte[] {});
    }

    @Test
    public void test558() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test558");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        boolean boolean6 = schema3.isUnion();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        org.apache.avro.LogicalType logicalType11 = schema10.getLogicalType();
        java.lang.String str12 = schema10.getDoc();
        java.lang.String str13 = schema10.getName();
        org.apache.avro.Schema[] schemaArray14 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList15 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList15, schemaArray14);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList15);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer18 = null;
        schema17.forEachProperty(strBiConsumer18);
        java.lang.Integer int21 = schema17.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type22 = schema17.getType();
        org.apache.avro.generic.GenericData genericData23 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter24 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema17, genericData23);
        java.lang.String str25 = schema17.getFullName();
        java.lang.String str26 = schema17.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream28 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream28.write((int) '#');
        byte[] byteArray33 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream28.writeBytes(byteArray33);
        byteArrayOutputStream28.reset();
        byteArrayOutputStream28.write((int) (byte) 0);
        byteArrayOutputStream28.reset();
        byteArrayOutputStream28.write(1);
        byte[] byteArray41 = byteArrayOutputStream28.toByteArray();
        boolean boolean42 = schema17.equals((java.lang.Object) byteArray41);
        java.util.List<org.apache.avro.Schema> schemaList43 = schema17.getTypes();
        boolean boolean44 = schema10.equals((java.lang.Object) schema17);
        org.apache.avro.Schema schema45 = org.apache.avro.Schema.createMap(schema10);
        schema3.putAll((org.apache.avro.JsonProperties) schema10);
        java.util.Map<java.lang.String, java.lang.Object> strMap47 = schema3.getObjectProps();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(logicalType11);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "union[]" + "'", str13, "union[]");
        org.junit.Assert.assertNotNull(schemaArray14);
        org.junit.Assert.assertArrayEquals(schemaArray14, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(schema17);
        org.junit.Assert.assertNull(int21);
        org.junit.Assert.assertTrue("'" + type22 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type22.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "union[]" + "'", str25, "union[]");
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertEquals(byteArrayOutputStream28.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray33);
        org.junit.Assert.assertArrayEquals(byteArray33, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray41);
        org.junit.Assert.assertArrayEquals(byteArray41, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertNotNull(schemaList43);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + true + "'", boolean44 == true);
        org.junit.Assert.assertNotNull(schema45);
        org.junit.Assert.assertNotNull(strMap47);
    }

    @Test
    public void test559() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test559");
        org.apache.avro.Schema.Field[] fieldArray0 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList1 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList1, fieldArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema.Field field6 = schema4.getField("\u6423\u6401\u640a");
        boolean boolean7 = schema4.hasFields();
        schema4.addProp("union[]", "#\001\n");
        org.junit.Assert.assertNotNull(fieldArray0);
        org.junit.Assert.assertArrayEquals(fieldArray0, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNull(field6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
    }

    @Test
    public void test560() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test560");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        org.apache.avro.LogicalType logicalType16 = schema12.getLogicalType();
        org.apache.avro.Schema.Type type17 = schema12.getType();
        boolean boolean18 = schema12.isNullable();
        // The following exception was thrown during execution in test generation
        try {
            schema12.addProp("\u3400\u3401\u340a", "array");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Can't set properties on a union: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNull(logicalType16);
        org.junit.Assert.assertTrue("'" + type17 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type17.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
    }

    @Test
    public void test561() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test561");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        byteArrayOutputStream1.write((int) 'a');
        byte[] byteArray22 = byteArrayOutputStream1.toByteArray();
        org.apache.avro.Schema[] schemaArray23 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList24 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean25 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList24, schemaArray23);
        org.apache.avro.Schema schema26 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList24);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer27 = null;
        schema26.forEachProperty(strBiConsumer27);
        java.lang.Integer int30 = schema26.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type31 = schema26.getType();
        java.lang.String str33 = schema26.getProp("enum");
        java.lang.String str35 = schema26.getProp("#");
        java.lang.String str36 = schema26.getDoc();
        org.apache.avro.Schema.SeenPair seenPair38 = new org.apache.avro.Schema.SeenPair((java.lang.Object) schema26, (java.lang.Object) "[]");
        java.lang.Object obj39 = null;
        boolean boolean40 = seenPair38.equals(obj39);
        java.io.ByteArrayOutputStream byteArrayOutputStream42 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream42.write((int) '#');
        byte[] byteArray47 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream42.writeBytes(byteArray47);
        byteArrayOutputStream42.reset();
        byte[] byteArray50 = byteArrayOutputStream42.toByteArray();
        boolean boolean51 = seenPair38.equals((java.lang.Object) byteArray50);
        byteArrayOutputStream1.writeBytes(byteArray50);
        byteArrayOutputStream1.write((int) (byte) 10);
        java.nio.charset.Charset charset55 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str56 = byteArrayOutputStream1.toString(charset55);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na\n");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(schemaArray23);
        org.junit.Assert.assertArrayEquals(schemaArray23, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertNotNull(schema26);
        org.junit.Assert.assertNull(int30);
        org.junit.Assert.assertTrue("'" + type31 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type31.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str33);
        org.junit.Assert.assertNull(str35);
        org.junit.Assert.assertNull(str36);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertEquals(byteArrayOutputStream42.toString(), "");
        org.junit.Assert.assertNotNull(byteArray47);
        org.junit.Assert.assertArrayEquals(byteArray47, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertArrayEquals(byteArray50, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean51 + "' != '" + false + "'", boolean51 == false);
    }

    @Test
    public void test562() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test562");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        boolean boolean12 = schema3.propsContainsKey("");
        boolean boolean13 = schema3.isUnion();
        java.lang.String str14 = schema3.toString();
        java.util.List<org.apache.avro.Schema> schemaList15 = schema3.getTypes();
        java.util.List<org.apache.avro.Schema> schemaList16 = schema3.getTypes();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + true + "'", boolean13 == true);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "[]" + "'", str14, "[]");
        org.junit.Assert.assertNotNull(schemaList15);
        org.junit.Assert.assertNotNull(schemaList16);
    }

    @Test
    public void test563() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test563");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction8 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter9 = charSequenceDataFileWriter7.setEncoder(outputStreamFunction8);
        charSequenceDataFileWriter7.setFlushOnEveryBlock(false);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter9);
    }

    @Test
    public void test564() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test564");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema16.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.LogicalType logicalType29 = schema25.getLogicalType();
        schemaListGenericDatumWriter10.setSchema(schema25);
        java.io.ByteArrayOutputStream byteArrayOutputStream33 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream33.write((int) '#');
        byte[] byteArray38 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream33.writeBytes(byteArray38);
        byteArrayOutputStream33.reset();
        byteArrayOutputStream33.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream44 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream44.write((int) '#');
        byte[] byteArray49 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream44.writeBytes(byteArray49);
        byteArrayOutputStream33.writeBytes(byteArray49);
        java.lang.Object obj52 = schema25.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream33);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter53 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema25);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(logicalType29);
        org.junit.Assert.assertEquals(byteArrayOutputStream33.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream44.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj52);
        org.junit.Assert.assertEquals(obj52.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj52), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj52), "\000\001\n");
    }

    @Test
    public void test565() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test565");
        org.apache.avro.Schema schema1 = null;
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter3 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter3);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter4.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction8 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter9 = charSequenceDataFileWriter7.setEncoder(outputStreamFunction8);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction10 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter11 = charSequenceDataFileWriter7.setEncoder(outputStreamFunction10);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field12 = null; // flaky "1) test565(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)": new org.apache.avro.Schema.Field("org.apache.avro.file.DataFileWriter$AppendWriteException", schema1, "union[]", (java.lang.Object) outputStreamFunction10);
// flaky "1) test565(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)":             org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.NameValidator.validate(String)\" because the return value of \"java.lang.ThreadLocal.get()\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter9);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter11);
    }

    @Test
    public void test566() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test566");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) ' ');
        byte[] byteArray2 = byteArrayOutputStream1.toByteArray();
        byteArrayOutputStream1.write(0);
        java.lang.String str5 = byteArrayOutputStream1.toString();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000");
        org.junit.Assert.assertNotNull(byteArray2);
        org.junit.Assert.assertArrayEquals(byteArray2, new byte[] {});
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\000" + "'", str5, "\000");
    }

    @Test
    public void test567() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test567");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.generic.GenericData genericData11 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.generic.GenericData genericData12 = schemaListGenericDatumWriter10.getData();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        java.lang.String str26 = schema25.getDoc();
        boolean boolean27 = schema25.hasProps();
        schema16.addAllProps((org.apache.avro.JsonProperties) schema25);
        org.apache.avro.LogicalType logicalType29 = schema25.getLogicalType();
        schemaListGenericDatumWriter10.setSchema(schema25);
        java.io.ByteArrayOutputStream byteArrayOutputStream33 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream33.write((int) '#');
        byte[] byteArray38 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream33.writeBytes(byteArray38);
        byteArrayOutputStream33.reset();
        byteArrayOutputStream33.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream44 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream44.write((int) '#');
        byte[] byteArray49 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream44.writeBytes(byteArray49);
        byteArrayOutputStream33.writeBytes(byteArray49);
        java.lang.Object obj52 = schema25.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream33);
        java.io.ByteArrayOutputStream byteArrayOutputStream54 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream54.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream58 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream58.write((int) '#');
        byte[] byteArray63 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream58.writeBytes(byteArray63);
        byteArrayOutputStream54.write(byteArray63);
        byteArrayOutputStream33.write(byteArray63);
        byte[] byteArray67 = byteArrayOutputStream33.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str69 = byteArrayOutputStream33.toString("\000");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: ?");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData11);
        org.junit.Assert.assertNull(genericData12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNull(logicalType29);
        org.junit.Assert.assertEquals(byteArrayOutputStream33.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray38);
        org.junit.Assert.assertArrayEquals(byteArray38, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream44.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray49);
        org.junit.Assert.assertArrayEquals(byteArray49, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj52);
        org.junit.Assert.assertEquals(obj52.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj52), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj52), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream54.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream58.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray63);
        org.junit.Assert.assertArrayEquals(byteArray63, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray67);
        org.junit.Assert.assertArrayEquals(byteArray67, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 1, (byte) 10 });
    }

    @Test
    public void test568() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test568");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        com.fasterxml.jackson.databind.JsonNode jsonNode5 = null;
        boolean boolean6 = schema3.isValidDefault(jsonNode5);
        java.lang.String str7 = schema3.getDoc();
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createArray(schema3);
        boolean boolean10 = schema8.propsContainsKey("hi!");
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
    }

    @Test
    public void test569() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test569");
        org.apache.avro.Schema[] schemaArray1 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList2 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean3 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList2, schemaArray1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList2);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer5 = null;
        schema4.forEachProperty(strBiConsumer5);
        java.lang.Integer int8 = schema4.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type9 = schema4.getType();
        org.apache.avro.Schema[] schemaArray10 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList11 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList11, schemaArray10);
        org.apache.avro.Schema schema13 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList11);
        java.lang.String str14 = schema13.getDoc();
        boolean boolean15 = schema13.hasProps();
        schema4.addAllProps((org.apache.avro.JsonProperties) schema13);
        boolean boolean18 = schema13.equals((java.lang.Object) "#");
        org.apache.avro.generic.GenericData genericData19 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter20 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema13, genericData19);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer21 = null;
        schema13.forEachProperty(strBiConsumer21);
        boolean boolean23 = schema13.isUnion();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter24 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema13);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field26 = null; // flaky "2) test569(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)": new org.apache.avro.Schema.Field("\u2300\u2301\u230a\u2361", schema13, "array");
// flaky "2) test569(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)":             org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.NameValidator.validate(String)\" because the return value of \"java.lang.ThreadLocal.get()\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray1);
        org.junit.Assert.assertArrayEquals(schemaArray1, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNull(int8);
        org.junit.Assert.assertTrue("'" + type9 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type9.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray10);
        org.junit.Assert.assertArrayEquals(schemaArray10, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(schema13);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
    }

    @Test
    public void test570() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test570");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        charSequenceDataFileWriter6.close();
        boolean boolean8 = charSequenceDataFileWriter6.isFlushOnEveryBlock();
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter6.append((java.lang.CharSequence) "\u3400\u3401\u340a");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test571() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test571");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        com.fasterxml.jackson.databind.JsonNode jsonNode5 = null;
        boolean boolean6 = schema3.isValidDefault(jsonNode5);
        java.lang.String str8 = schema3.getProp("\001");
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNull(str8);
    }

    @Test
    public void test572() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test572");
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema2 = org.apache.avro.Schema.parse("\001", false);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: com.fasterxml.jackson.core.JsonParseException: Illegal character ((CTRL-CHAR, code 1)): only regular white space (\\r, \\n, \\t) is allowed between tokens? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 2]");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
    }

    @Test
    public void test573() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test573");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.flush();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = byteArrayOutputStream1.toString("union[]");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: union[]");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
    }

    @Test
    public void test574() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test574");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser8 = new org.apache.avro.Schema.Parser();
        boolean boolean9 = parser8.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap10 = parser8.getTypes();
        org.apache.avro.Schema[] schemaArray11 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList12 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList12, schemaArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList12);
        org.apache.avro.Schema.Parser parser15 = parser8.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList12);
        org.apache.avro.Schema.Parser parser16 = new org.apache.avro.Schema.Parser();
        boolean boolean17 = parser16.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap18 = parser16.getTypes();
        org.apache.avro.Schema.Parser parser19 = parser8.addTypes(strMap18);
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap20 = parser19.getTypes();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap21 = parser19.getTypes();
        org.apache.avro.Schema.Parser parser22 = parser7.addTypes(strMap21);
        org.apache.avro.Schema schema24 = parser22.parseInternal("[]");
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertNotNull(strMap10);
        org.junit.Assert.assertNotNull(schemaArray11);
        org.junit.Assert.assertArrayEquals(schemaArray11, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(schema14);
        org.junit.Assert.assertNotNull(parser15);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + true + "'", boolean17 == true);
        org.junit.Assert.assertNotNull(strMap18);
        org.junit.Assert.assertNotNull(parser19);
        org.junit.Assert.assertNotNull(strMap20);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNotNull(parser22);
        org.junit.Assert.assertNotNull(schema24);
    }

    @Test
    public void test575() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test575");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        com.fasterxml.jackson.databind.JsonNode jsonNode4 = null;
        boolean boolean5 = schema3.isValidDefault(jsonNode4);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean6 = schema3.hasFields();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test576() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test576");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        java.lang.String str21 = byteArrayOutputStream1.toString((int) (short) 100);
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "\u6400\u6401\u640a" + "'", str21, "\u6400\u6401\u640a");
    }

    @Test
    public void test577() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test577");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createArray(schema3);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createArray(schema3);
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean12 = schema3.hasEnumSymbol("\u2301");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schema9);
        org.junit.Assert.assertNotNull(schema10);
    }

    @Test
    public void test578() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test578");
        org.apache.avro.Schema[] schemaArray1 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList2 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean3 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList2, schemaArray1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList2);
        java.lang.String str5 = schema4.getDoc();
        org.apache.avro.Schema[] schemaArray6 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList7 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList7, schemaArray6);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList7);
        java.lang.String str10 = schema9.getDoc();
        boolean boolean11 = schema9.hasProps();
        schema4.addAllProps((org.apache.avro.JsonProperties) schema9);
        java.lang.String str14 = schema4.getProp("#");
        java.lang.Exception exception15 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException16 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception15);
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException17 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception15);
        java.lang.Throwable[] throwableArray18 = appendWriteException17.getSuppressed();
        boolean boolean19 = schema4.equals((java.lang.Object) appendWriteException17);
        boolean boolean20 = schema4.isUnion();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field21 = null; // flaky "3) test578(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)": new org.apache.avro.Schema.Field("org.apache.avro.file.DataFileWriter$AppendWriteException: org.apache.avro.file.DataFileWriter$AppendWriteException", schema4);
// flaky "3) test578(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)":             org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.NameValidator.validate(String)\" because the return value of \"java.lang.ThreadLocal.get()\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray1);
        org.junit.Assert.assertArrayEquals(schemaArray1, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(schemaArray6);
        org.junit.Assert.assertArrayEquals(schemaArray6, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(schema9);
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertNotNull(throwableArray18);
        org.junit.Assert.assertArrayEquals(throwableArray18, new java.lang.Throwable[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test579() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test579");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        byteArrayOutputStream1.write((int) 'a');
        byte[] byteArray22 = byteArrayOutputStream1.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream24.reset();
        byteArrayOutputStream1.writeTo((java.io.OutputStream) byteArrayOutputStream24);
        java.io.ByteArrayOutputStream byteArrayOutputStream34 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream34.write((int) '#');
        byte[] byteArray39 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream34.writeBytes(byteArray39);
        byteArrayOutputStream34.reset();
        byteArrayOutputStream34.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream45 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream45.write((int) '#');
        byte[] byteArray50 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream45.writeBytes(byteArray50);
        byteArrayOutputStream34.writeBytes(byteArray50);
        byteArrayOutputStream34.write((int) 'a');
        byte[] byteArray55 = byteArrayOutputStream34.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream57 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream57.write((int) '#');
        byte[] byteArray62 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream57.writeBytes(byteArray62);
        byteArrayOutputStream57.reset();
        byteArrayOutputStream34.writeTo((java.io.OutputStream) byteArrayOutputStream57);
        byteArrayOutputStream24.writeTo((java.io.OutputStream) byteArrayOutputStream34);
        int int67 = byteArrayOutputStream34.size();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray22);
        org.junit.Assert.assertArrayEquals(byteArray22, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream34.toString(), "\000\001\na\000\001\na");
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertArrayEquals(byteArray39, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream45.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray50);
        org.junit.Assert.assertArrayEquals(byteArray50, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream57.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray62);
        org.junit.Assert.assertArrayEquals(byteArray62, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertTrue("'" + int67 + "' != '" + 8 + "'", int67 == 8);
    }

    @Test
    public void test580() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test580");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        boolean boolean17 = schema12.equals((java.lang.Object) "#");
        boolean boolean19 = schema12.equals((java.lang.Object) "[]");
        org.apache.avro.LogicalType logicalType20 = schema12.getLogicalType();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = schema12.getNamespace();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNull(logicalType20);
    }

    @Test
    public void test581() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test581");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        byteArrayOutputStream1.write((int) 'a');
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str23 = byteArrayOutputStream1.toString("#");
            org.junit.Assert.fail("Expected exception of type java.io.UnsupportedEncodingException; message: #");
        } catch (java.io.UnsupportedEncodingException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
    }

    @Test
    public void test582() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test582");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        schema3.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Field.Order order15 = org.apache.avro.Schema.Field.Order.IGNORE;
        java.lang.Object obj16 = schema3.getObjectProp("hi!", (java.lang.Object) order15);
        com.fasterxml.jackson.databind.JsonNode jsonNode17 = null;
        boolean boolean18 = schema3.isValidDefault(jsonNode17);
        java.lang.Integer int20 = schema3.getIndexNamed("#\001\n");
        // The following exception was thrown during execution in test generation
        try {
            boolean boolean21 = schema3.isError();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a record: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + order15 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order15.equals(org.apache.avro.Schema.Field.Order.IGNORE));
        org.junit.Assert.assertEquals("'" + obj16 + "' != '" + 100L + "'", obj16, 100L);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNull(int20);
    }

    @Test
    public void test583() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test583");
        java.lang.Exception exception0 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException1 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception0);
        java.lang.Throwable[] throwableArray2 = appendWriteException1.getSuppressed();
        java.lang.Exception exception3 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException4 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception3);
        java.lang.Throwable[] throwableArray5 = appendWriteException4.getSuppressed();
        java.lang.Exception exception6 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException7 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception6);
        java.lang.Throwable[] throwableArray8 = appendWriteException7.getSuppressed();
        appendWriteException4.addSuppressed((java.lang.Throwable) appendWriteException7);
        appendWriteException1.addSuppressed((java.lang.Throwable) appendWriteException7);
        org.apache.avro.Schema.Parser parser11 = new org.apache.avro.Schema.Parser();
        boolean boolean12 = parser11.getValidateDefaults();
        org.apache.avro.Schema.Parser parser14 = parser11.setValidateDefaults(false);
        org.apache.avro.Schema[] schemaArray15 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList16 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList16, schemaArray15);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList16);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer19 = null;
        schema18.forEachProperty(strBiConsumer19);
        java.lang.Integer int22 = schema18.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap23 = schema18.getObjectProps();
        org.apache.avro.Schema[] schemaArray24 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList25 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList25, schemaArray24);
        org.apache.avro.Schema schema27 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList25);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer28 = null;
        schema27.forEachProperty(strBiConsumer28);
        java.lang.Integer int31 = schema27.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type32 = schema27.getType();
        java.lang.String str34 = schema27.getProp("enum");
        boolean boolean36 = schema27.propsContainsKey("");
        boolean boolean37 = schema27.isUnion();
        java.lang.String str38 = schema27.toString();
        boolean boolean39 = schema18.equals((java.lang.Object) str38);
        java.util.List<org.apache.avro.Schema> schemaList40 = schema18.getTypes();
        org.apache.avro.Schema schema41 = org.apache.avro.Schema.createUnion(schemaList40);
        org.apache.avro.Schema.Parser parser42 = parser11.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList40);
        org.apache.avro.Schema.SeenPair seenPair43 = new org.apache.avro.Schema.SeenPair((java.lang.Object) appendWriteException1, (java.lang.Object) parser11);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema45 = parser11.parse("\001");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.SchemaParseException; message: com.fasterxml.jackson.core.JsonParseException: Illegal character ((CTRL-CHAR, code 1)): only regular white space (\\r, \\n, \\t) is allowed between tokens? at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 2]");
        } catch (org.apache.avro.SchemaParseException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(throwableArray2);
        org.junit.Assert.assertArrayEquals(throwableArray2, new java.lang.Throwable[] {});
        org.junit.Assert.assertNotNull(throwableArray5);
        org.junit.Assert.assertArrayEquals(throwableArray5, new java.lang.Throwable[] {});
        org.junit.Assert.assertNotNull(throwableArray8);
        org.junit.Assert.assertArrayEquals(throwableArray8, new java.lang.Throwable[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(parser14);
        org.junit.Assert.assertNotNull(schemaArray15);
        org.junit.Assert.assertArrayEquals(schemaArray15, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(schema18);
        org.junit.Assert.assertNull(int22);
        org.junit.Assert.assertNotNull(strMap23);
        org.junit.Assert.assertNotNull(schemaArray24);
        org.junit.Assert.assertArrayEquals(schemaArray24, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(schema27);
        org.junit.Assert.assertNull(int31);
        org.junit.Assert.assertTrue("'" + type32 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type32.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str34);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + true + "'", boolean37 == true);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "[]" + "'", str38, "[]");
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schemaList40);
        org.junit.Assert.assertNotNull(schema41);
        org.junit.Assert.assertNotNull(parser42);
    }

    @Test
    public void test584() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test584");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter1.flush();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
    }

    @Test
    public void test585() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test585");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        org.apache.avro.LogicalType logicalType16 = schema12.getLogicalType();
        java.util.Map<java.lang.String, java.lang.Object> strMap17 = schema12.getObjectProps();
        java.util.Map<java.lang.String, java.lang.Object> strMap18 = schema12.getObjectProps();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertNull(logicalType16);
        org.junit.Assert.assertNotNull(strMap17);
        org.junit.Assert.assertNotNull(strMap18);
    }

    @Test
    public void test586() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test586");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream1.write((int) '#');
        byte[] byteArray6 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream1.writeBytes(byteArray6);
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream12 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream12.write((int) '#');
        byte[] byteArray17 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream12.writeBytes(byteArray17);
        byteArrayOutputStream1.writeBytes(byteArray17);
        byteArrayOutputStream1.write((int) 'a');
        java.lang.String str23 = byteArrayOutputStream1.toString((int) ' ');
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray6);
        org.junit.Assert.assertArrayEquals(byteArray6, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream12.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray17);
        org.junit.Assert.assertArrayEquals(byteArray17, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "\u2000\u2001\u200a\u2061" + "'", str23, "\u2000\u2001\u200a\u2061");
    }

    @Test
    public void test587() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test587");
        org.apache.avro.Schema.Field[] fieldArray0 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList1 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList1, fieldArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema schema5 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.Schema[] schemaArray16 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList17 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList17, schemaArray16);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList17);
        java.lang.String str20 = schema19.getDoc();
        boolean boolean21 = schema19.hasProps();
        schema10.addAllProps((org.apache.avro.JsonProperties) schema19);
        boolean boolean24 = schema19.equals((java.lang.Object) "#");
        boolean boolean26 = schema19.equals((java.lang.Object) "[]");
        org.apache.avro.Schema.Field[] fieldArray28 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList29 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList29, fieldArray28);
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList29);
        schema19.addProp("", (java.lang.Object) fieldList29);
        // The following exception was thrown during execution in test generation
        try {
            schema6.setFields((java.util.List<org.apache.avro.Schema.Field>) fieldList29);
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Fields are already set");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(fieldArray0);
        org.junit.Assert.assertArrayEquals(fieldArray0, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNotNull(schema5);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray16);
        org.junit.Assert.assertArrayEquals(schemaArray16, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(schema19);
        org.junit.Assert.assertNull(str20);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertNotNull(fieldArray28);
        org.junit.Assert.assertArrayEquals(fieldArray28, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(schema31);
    }

    @Test
    public void test588() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test588");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        charSequenceDataFileWriter4.setFlushOnEveryBlock(false);
        boolean boolean7 = charSequenceDataFileWriter4.isFlushOnEveryBlock();
        // The following exception was thrown during execution in test generation
        try {
            long long8 = charSequenceDataFileWriter4.sync();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test589() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test589");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 1);
        byteArrayOutputStream1.flush();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
    }

    @Test
    public void test590() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test590");
        org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericData> genericDataGenericDatumWriter0 = new org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericData>();
    }

    @Test
    public void test591() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test591");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        java.lang.String str13 = schema3.getProp("#");
        java.lang.Exception exception14 = null;
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException15 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception14);
        org.apache.avro.file.DataFileWriter.AppendWriteException appendWriteException16 = new org.apache.avro.file.DataFileWriter.AppendWriteException(exception14);
        java.lang.Throwable[] throwableArray17 = appendWriteException16.getSuppressed();
        boolean boolean18 = schema3.equals((java.lang.Object) appendWriteException16);
        java.lang.Throwable[] throwableArray19 = appendWriteException16.getSuppressed();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertNotNull(throwableArray17);
        org.junit.Assert.assertArrayEquals(throwableArray17, new java.lang.Throwable[] {});
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertNotNull(throwableArray19);
        org.junit.Assert.assertArrayEquals(throwableArray19, new java.lang.Throwable[] {});
    }

    @Test
    public void test592() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test592");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        java.lang.String str13 = schema3.getProp("#");
        boolean boolean14 = schema3.hasProps();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = schema3.getEnumDefault();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
    }

    @Test
    public void test593() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test593");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        org.apache.avro.Schema.Type type12 = schema3.getType();
        org.apache.avro.Schema.Type type13 = schema3.getType();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = schema3.getEnumDefault();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an enum: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + type12 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type12.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertTrue("'" + type13 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type13.equals(org.apache.avro.Schema.Type.UNION));
    }

    @Test
    public void test594() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test594");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream1.reset();
        byte[] byteArray3 = byteArrayOutputStream1.toByteArray();
        byteArrayOutputStream1.reset();
        byteArrayOutputStream1.write((int) (short) 1);
        byteArrayOutputStream1.reset();
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
    }

    @Test
    public void test595() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test595");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        org.apache.avro.Schema[] schemaArray37 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList38 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList38, schemaArray37);
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList38);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer41 = null;
        schema40.forEachProperty(strBiConsumer41);
        java.lang.Integer int44 = schema40.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type45 = schema40.getType();
        org.apache.avro.generic.GenericData genericData46 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter47 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema40, genericData46);
        org.apache.avro.generic.GenericData genericData48 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.generic.GenericData genericData49 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.Schema[] schemaArray50 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList51 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList51, schemaArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList51);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer54 = null;
        schema53.forEachProperty(strBiConsumer54);
        java.lang.Integer int57 = schema53.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type58 = schema53.getType();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.lang.String str63 = schema62.getDoc();
        boolean boolean64 = schema62.hasProps();
        schema53.addAllProps((org.apache.avro.JsonProperties) schema62);
        org.apache.avro.LogicalType logicalType66 = schema62.getLogicalType();
        schemaListGenericDatumWriter47.setSchema(schema62);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        java.lang.Object obj89 = schema62.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream70);
        java.io.ByteArrayOutputStream byteArrayOutputStream91 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream91.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream91.write(byteArray100);
        byteArrayOutputStream70.write(byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter104 = charSequenceDataFileWriter35.setMeta("\uff00\uff01\uff0a", byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter107 = charSequenceDataFileWriter35.setMeta("fixed", 0L);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction108 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter109 = charSequenceDataFileWriter35.setEncoder(outputStreamFunction108);
        org.apache.avro.Schema.Field[] fieldArray110 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList111 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean112 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList111, fieldArray110);
        org.apache.avro.Schema schema113 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList111);
        org.apache.avro.Schema schema114 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList111);
        org.apache.avro.Schema[] schemaArray115 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList116 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean117 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList116, schemaArray115);
        org.apache.avro.Schema schema118 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList116);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer119 = null;
        schema118.forEachProperty(strBiConsumer119);
        java.lang.Integer int122 = schema118.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap123 = schema118.getObjectProps();
        org.apache.avro.Schema[] schemaArray124 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList125 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean126 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList125, schemaArray124);
        org.apache.avro.Schema schema127 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList125);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer128 = null;
        schema127.forEachProperty(strBiConsumer128);
        java.lang.Integer int131 = schema127.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type132 = schema127.getType();
        java.lang.String str134 = schema127.getProp("enum");
        boolean boolean136 = schema127.propsContainsKey("");
        boolean boolean137 = schema127.isUnion();
        java.lang.String str138 = schema127.toString();
        boolean boolean139 = schema118.equals((java.lang.Object) str138);
        java.util.List<org.apache.avro.Schema> schemaList140 = schema118.getTypes();
        org.apache.avro.Schema schema141 = org.apache.avro.Schema.createUnion(schemaList140);
        java.lang.String str143 = schema114.toString((java.util.Collection<org.apache.avro.Schema>) schemaList140, false);
        java.io.ByteArrayOutputStream byteArrayOutputStream145 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream145.write((int) '#');
        byte[] byteArray150 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream145.writeBytes(byteArray150);
        byteArrayOutputStream145.reset();
        byteArrayOutputStream145.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream156 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream156.write((int) '#');
        byte[] byteArray161 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream156.writeBytes(byteArray161);
        byteArrayOutputStream145.writeBytes(byteArray161);
        byteArrayOutputStream145.write((int) 'a');
        byte[] byteArray166 = byteArrayOutputStream145.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream168 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream168.write((int) '#');
        byte[] byteArray173 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream168.writeBytes(byteArray173);
        byteArrayOutputStream168.reset();
        byteArrayOutputStream145.writeTo((java.io.OutputStream) byteArrayOutputStream168);
        java.io.ByteArrayOutputStream byteArrayOutputStream178 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream178.write((int) '#');
        byte[] byteArray183 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream178.writeBytes(byteArray183);
        byteArrayOutputStream178.reset();
        byteArrayOutputStream178.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream189 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream189.write((int) '#');
        byte[] byteArray194 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream189.writeBytes(byteArray194);
        byteArrayOutputStream178.writeBytes(byteArray194);
        byteArrayOutputStream178.write((int) 'a');
        byte[] byteArray199 = byteArrayOutputStream178.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream201 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream201.write((int) '#');
        byte[] byteArray206 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream201.writeBytes(byteArray206);
        byteArrayOutputStream201.reset();
        byteArrayOutputStream178.writeTo((java.io.OutputStream) byteArrayOutputStream201);
        byteArrayOutputStream168.writeTo((java.io.OutputStream) byteArrayOutputStream178);
        byteArrayOutputStream178.write(0);
        java.io.ByteArrayOutputStream byteArrayOutputStream214 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream214.reset();
        byte[] byteArray216 = byteArrayOutputStream214.toByteArray();
        org.apache.avro.Schema[] schemaArray217 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList218 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean219 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList218, schemaArray217);
        org.apache.avro.Schema schema220 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList218);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer221 = null;
        schema220.forEachProperty(strBiConsumer221);
        java.lang.Integer int224 = schema220.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type225 = schema220.getType();
        org.apache.avro.generic.GenericData genericData226 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter227 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema220, genericData226);
        org.apache.avro.generic.GenericData genericData228 = schemaListGenericDatumWriter227.getData();
        org.apache.avro.generic.GenericData genericData229 = schemaListGenericDatumWriter227.getData();
        org.apache.avro.Schema[] schemaArray230 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList231 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean232 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList231, schemaArray230);
        org.apache.avro.Schema schema233 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList231);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer234 = null;
        schema233.forEachProperty(strBiConsumer234);
        java.lang.Integer int237 = schema233.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type238 = schema233.getType();
        org.apache.avro.Schema[] schemaArray239 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList240 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean241 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList240, schemaArray239);
        org.apache.avro.Schema schema242 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList240);
        java.lang.String str243 = schema242.getDoc();
        boolean boolean244 = schema242.hasProps();
        schema233.addAllProps((org.apache.avro.JsonProperties) schema242);
        org.apache.avro.LogicalType logicalType246 = schema242.getLogicalType();
        schemaListGenericDatumWriter227.setSchema(schema242);
        java.io.ByteArrayOutputStream byteArrayOutputStream250 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream250.write((int) '#');
        byte[] byteArray255 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream250.writeBytes(byteArray255);
        byteArrayOutputStream250.reset();
        byteArrayOutputStream250.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream261 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream261.write((int) '#');
        byte[] byteArray266 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream261.writeBytes(byteArray266);
        byteArrayOutputStream250.writeBytes(byteArray266);
        java.lang.Object obj269 = schema242.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream250);
        byteArrayOutputStream214.writeTo((java.io.OutputStream) byteArrayOutputStream250);
        byte[] byteArray271 = byteArrayOutputStream250.toByteArray();
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter272 = charSequenceDataFileWriter109.create(schema114, (java.io.OutputStream) byteArrayOutputStream178, byteArray271);
            org.junit.Assert.fail("Expected exception of type java.io.IOException; message: sync must be exactly 16 bytes");
        } catch (java.io.IOException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
        org.junit.Assert.assertNotNull(schemaArray37);
        org.junit.Assert.assertArrayEquals(schemaArray37, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schema40);
        org.junit.Assert.assertNull(int44);
        org.junit.Assert.assertTrue("'" + type45 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type45.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData48);
        org.junit.Assert.assertNull(genericData49);
        org.junit.Assert.assertNotNull(schemaArray50);
        org.junit.Assert.assertArrayEquals(schemaArray50, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNull(int57);
        org.junit.Assert.assertTrue("'" + type58 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type58.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(logicalType66);
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj89);
        org.junit.Assert.assertEquals(obj89.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream91.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter104);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter107);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter109);
        org.junit.Assert.assertNotNull(fieldArray110);
        org.junit.Assert.assertArrayEquals(fieldArray110, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean112 + "' != '" + false + "'", boolean112 == false);
        org.junit.Assert.assertNotNull(schema113);
        org.junit.Assert.assertNotNull(schema114);
        org.junit.Assert.assertNotNull(schemaArray115);
        org.junit.Assert.assertArrayEquals(schemaArray115, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean117 + "' != '" + false + "'", boolean117 == false);
        org.junit.Assert.assertNotNull(schema118);
        org.junit.Assert.assertNull(int122);
        org.junit.Assert.assertNotNull(strMap123);
        org.junit.Assert.assertNotNull(schemaArray124);
        org.junit.Assert.assertArrayEquals(schemaArray124, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean126 + "' != '" + false + "'", boolean126 == false);
        org.junit.Assert.assertNotNull(schema127);
        org.junit.Assert.assertNull(int131);
        org.junit.Assert.assertTrue("'" + type132 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type132.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str134);
        org.junit.Assert.assertTrue("'" + boolean136 + "' != '" + false + "'", boolean136 == false);
        org.junit.Assert.assertTrue("'" + boolean137 + "' != '" + true + "'", boolean137 == true);
        org.junit.Assert.assertEquals("'" + str138 + "' != '" + "[]" + "'", str138, "[]");
        org.junit.Assert.assertTrue("'" + boolean139 + "' != '" + false + "'", boolean139 == false);
        org.junit.Assert.assertNotNull(schemaList140);
        org.junit.Assert.assertNotNull(schema141);
        org.junit.Assert.assertEquals("'" + str143 + "' != '" + "{\"type\":\"record\",\"fields\":[]}" + "'", str143, "{\"type\":\"record\",\"fields\":[]}");
        org.junit.Assert.assertEquals(byteArrayOutputStream145.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray150);
        org.junit.Assert.assertArrayEquals(byteArray150, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream156.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray161);
        org.junit.Assert.assertArrayEquals(byteArray161, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray166);
        org.junit.Assert.assertArrayEquals(byteArray166, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream168.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray173);
        org.junit.Assert.assertArrayEquals(byteArray173, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream178.toString(), "\000\001\na\000\001\na\000");
        org.junit.Assert.assertNotNull(byteArray183);
        org.junit.Assert.assertArrayEquals(byteArray183, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream189.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray194);
        org.junit.Assert.assertArrayEquals(byteArray194, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray199);
        org.junit.Assert.assertArrayEquals(byteArray199, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream201.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray206);
        org.junit.Assert.assertArrayEquals(byteArray206, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream214.toString(), "");
        org.junit.Assert.assertNotNull(byteArray216);
        org.junit.Assert.assertArrayEquals(byteArray216, new byte[] {});
        org.junit.Assert.assertNotNull(schemaArray217);
        org.junit.Assert.assertArrayEquals(schemaArray217, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean219 + "' != '" + false + "'", boolean219 == false);
        org.junit.Assert.assertNotNull(schema220);
        org.junit.Assert.assertNull(int224);
        org.junit.Assert.assertTrue("'" + type225 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type225.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData228);
        org.junit.Assert.assertNull(genericData229);
        org.junit.Assert.assertNotNull(schemaArray230);
        org.junit.Assert.assertArrayEquals(schemaArray230, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean232 + "' != '" + false + "'", boolean232 == false);
        org.junit.Assert.assertNotNull(schema233);
        org.junit.Assert.assertNull(int237);
        org.junit.Assert.assertTrue("'" + type238 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type238.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray239);
        org.junit.Assert.assertArrayEquals(schemaArray239, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean241 + "' != '" + false + "'", boolean241 == false);
        org.junit.Assert.assertNotNull(schema242);
        org.junit.Assert.assertNull(str243);
        org.junit.Assert.assertTrue("'" + boolean244 + "' != '" + false + "'", boolean244 == false);
        org.junit.Assert.assertNull(logicalType246);
        org.junit.Assert.assertEquals(byteArrayOutputStream250.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray255);
        org.junit.Assert.assertArrayEquals(byteArray255, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream261.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray266);
        org.junit.Assert.assertArrayEquals(byteArray266, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj269);
        org.junit.Assert.assertEquals(obj269.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj269), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj269), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray271);
        org.junit.Assert.assertArrayEquals(byteArray271, new byte[] { (byte) 0, (byte) 1, (byte) 10 });
    }

    @Test
    public void test596() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test596");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        boolean boolean9 = schema3.isNullable();
        org.apache.avro.Schema[] schemaArray10 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList11 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList11, schemaArray10);
        org.apache.avro.Schema schema13 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList11);
        java.lang.String str14 = schema13.getDoc();
        org.apache.avro.Schema[] schemaArray15 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList16 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList16, schemaArray15);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList16);
        java.lang.String str19 = schema18.getDoc();
        boolean boolean20 = schema18.hasProps();
        schema13.addAllProps((org.apache.avro.JsonProperties) schema18);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter22 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema18);
        org.apache.avro.generic.GenericData genericData23 = schemaListGenericDatumWriter22.getData();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter24 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData23);
        org.apache.avro.Schema[] schemaArray25 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList26 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean27 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList26, schemaArray25);
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList26);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer29 = null;
        schema28.forEachProperty(strBiConsumer29);
        java.lang.Integer int32 = schema28.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type33 = schema28.getType();
        org.apache.avro.generic.GenericData genericData34 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter35 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema28, genericData34);
        org.apache.avro.Schema schema36 = org.apache.avro.Schema.createArray(schema28);
        java.lang.Object obj39 = schema28.getObjectProp("", (java.lang.Object) "[ ]");
        boolean boolean40 = schema28.isNullable();
        schemaListGenericDatumWriter24.setSchema(schema28);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schemaArray10);
        org.junit.Assert.assertArrayEquals(schemaArray10, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(schema13);
        org.junit.Assert.assertNull(str14);
        org.junit.Assert.assertNotNull(schemaArray15);
        org.junit.Assert.assertArrayEquals(schemaArray15, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertNotNull(schema18);
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertNotNull(genericData23);
        org.junit.Assert.assertNotNull(schemaArray25);
        org.junit.Assert.assertArrayEquals(schemaArray25, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertNotNull(schema28);
        org.junit.Assert.assertNull(int32);
        org.junit.Assert.assertTrue("'" + type33 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type33.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schema36);
        org.junit.Assert.assertEquals("'" + obj39 + "' != '" + "[ ]" + "'", obj39, "[ ]");
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
    }

    @Test
    public void test597() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test597");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        boolean boolean6 = schema3.isUnion();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.generic.GenericData genericData16 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter17 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema10, genericData16);
        java.lang.String str18 = schema10.getFullName();
        org.apache.avro.Schema[] schemaArray19 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList20 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList20, schemaArray19);
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList20);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer23 = null;
        schema22.forEachProperty(strBiConsumer23);
        java.lang.Integer int26 = schema22.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap27 = schema22.getObjectProps();
        org.apache.avro.Schema[] schemaArray28 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList29 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList29, schemaArray28);
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList29);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer32 = null;
        schema31.forEachProperty(strBiConsumer32);
        java.lang.Integer int35 = schema31.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type36 = schema31.getType();
        java.lang.String str38 = schema31.getProp("enum");
        boolean boolean40 = schema31.propsContainsKey("");
        boolean boolean41 = schema31.isUnion();
        java.lang.String str42 = schema31.toString();
        boolean boolean43 = schema22.equals((java.lang.Object) str42);
        java.util.List<org.apache.avro.Schema> schemaList44 = schema22.getTypes();
        java.lang.String str46 = schema10.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, true);
        java.lang.String str48 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, false);
        org.apache.avro.Schema.Parser parser49 = new org.apache.avro.Schema.Parser();
        boolean boolean50 = parser49.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap51 = parser49.getTypes();
        org.apache.avro.Schema schema53 = parser49.parseInternal("[]");
        java.util.Map<java.lang.String, java.lang.Object> strMap54 = schema53.getObjectProps();
        org.apache.avro.Schema schema55 = org.apache.avro.Schema.applyAliases(schema3, schema53);
        // The following exception was thrown during execution in test generation
        try {
            schema53.addAlias("\u6423\u6401\u640a", "\001");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "union[]" + "'", str18, "union[]");
        org.junit.Assert.assertNotNull(schemaArray19);
        org.junit.Assert.assertArrayEquals(schemaArray19, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(schema22);
        org.junit.Assert.assertNull(int26);
        org.junit.Assert.assertNotNull(strMap27);
        org.junit.Assert.assertNotNull(schemaArray28);
        org.junit.Assert.assertArrayEquals(schemaArray28, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(schema31);
        org.junit.Assert.assertNull(int35);
        org.junit.Assert.assertTrue("'" + type36 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type36.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str38);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "[]" + "'", str42, "[]");
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(schemaList44);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "[ ]" + "'", str46, "[ ]");
        org.junit.Assert.assertEquals("'" + str48 + "' != '" + "[]" + "'", str48, "[]");
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertNotNull(strMap51);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNotNull(strMap54);
        org.junit.Assert.assertNotNull(schema55);
    }

    @Test
    public void test598() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test598");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream1.reset();
        byte[] byteArray3 = byteArrayOutputStream1.toByteArray();
        org.apache.avro.Schema[] schemaArray4 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList5 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList5, schemaArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList5);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer8 = null;
        schema7.forEachProperty(strBiConsumer8);
        java.lang.Integer int11 = schema7.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type12 = schema7.getType();
        org.apache.avro.generic.GenericData genericData13 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter14 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema7, genericData13);
        org.apache.avro.generic.GenericData genericData15 = schemaListGenericDatumWriter14.getData();
        org.apache.avro.generic.GenericData genericData16 = schemaListGenericDatumWriter14.getData();
        org.apache.avro.Schema[] schemaArray17 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList18 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList18, schemaArray17);
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer21 = null;
        schema20.forEachProperty(strBiConsumer21);
        java.lang.Integer int24 = schema20.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type25 = schema20.getType();
        org.apache.avro.Schema[] schemaArray26 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList27 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList27, schemaArray26);
        org.apache.avro.Schema schema29 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList27);
        java.lang.String str30 = schema29.getDoc();
        boolean boolean31 = schema29.hasProps();
        schema20.addAllProps((org.apache.avro.JsonProperties) schema29);
        org.apache.avro.LogicalType logicalType33 = schema29.getLogicalType();
        schemaListGenericDatumWriter14.setSchema(schema29);
        java.io.ByteArrayOutputStream byteArrayOutputStream37 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream37.write((int) '#');
        byte[] byteArray42 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream37.writeBytes(byteArray42);
        byteArrayOutputStream37.reset();
        byteArrayOutputStream37.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream48 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream48.write((int) '#');
        byte[] byteArray53 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream48.writeBytes(byteArray53);
        byteArrayOutputStream37.writeBytes(byteArray53);
        java.lang.Object obj56 = schema29.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream37);
        byteArrayOutputStream1.writeTo((java.io.OutputStream) byteArrayOutputStream37);
        byteArrayOutputStream37.flush();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer63 = null;
        schema62.forEachProperty(strBiConsumer63);
        java.lang.Integer int66 = schema62.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type67 = schema62.getType();
        java.lang.String str69 = schema62.getProp("enum");
        schema62.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Field.Order order74 = org.apache.avro.Schema.Field.Order.IGNORE;
        java.lang.Object obj75 = schema62.getObjectProp("hi!", (java.lang.Object) order74);
        org.apache.avro.Schema[] schemaArray76 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList77 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean78 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList77, schemaArray76);
        org.apache.avro.Schema schema79 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList77);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer80 = null;
        schema79.forEachProperty(strBiConsumer80);
        java.lang.Integer int83 = schema79.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type84 = schema79.getType();
        org.apache.avro.generic.GenericData genericData85 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter86 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema79, genericData85);
        org.apache.avro.generic.GenericData genericData87 = schemaListGenericDatumWriter86.getData();
        org.apache.avro.generic.GenericData genericData88 = schemaListGenericDatumWriter86.getData();
        org.apache.avro.Schema[] schemaArray89 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList90 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean91 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList90, schemaArray89);
        org.apache.avro.Schema schema92 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList90);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer93 = null;
        schema92.forEachProperty(strBiConsumer93);
        java.lang.Integer int96 = schema92.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type97 = schema92.getType();
        org.apache.avro.Schema[] schemaArray98 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList99 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean100 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList99, schemaArray98);
        org.apache.avro.Schema schema101 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList99);
        java.lang.String str102 = schema101.getDoc();
        boolean boolean103 = schema101.hasProps();
        schema92.addAllProps((org.apache.avro.JsonProperties) schema101);
        org.apache.avro.LogicalType logicalType105 = schema101.getLogicalType();
        schemaListGenericDatumWriter86.setSchema(schema101);
        java.io.ByteArrayOutputStream byteArrayOutputStream109 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream109.write((int) '#');
        byte[] byteArray114 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream109.writeBytes(byteArray114);
        byteArrayOutputStream109.reset();
        byteArrayOutputStream109.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream120 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream120.write((int) '#');
        byte[] byteArray125 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream120.writeBytes(byteArray125);
        byteArrayOutputStream109.writeBytes(byteArray125);
        java.lang.Object obj128 = schema101.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream109);
        java.lang.Integer int130 = schema101.getIndexNamed("array");
        org.apache.avro.Schema[] schemaArray131 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList132 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean133 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList132, schemaArray131);
        org.apache.avro.Schema schema134 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList132);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer135 = null;
        schema134.forEachProperty(strBiConsumer135);
        java.lang.Integer int138 = schema134.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type139 = schema134.getType();
        org.apache.avro.generic.GenericData genericData140 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter141 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema134, genericData140);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter142 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema134);
        org.apache.avro.Schema schema143 = null;
        schemaListGenericDatumWriter142.setSchema(schema143);
        org.apache.avro.generic.GenericData genericData145 = schemaListGenericDatumWriter142.getData();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter146 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema101, genericData145);
        org.apache.avro.Schema[] schemaArray147 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList148 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean149 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList148, schemaArray147);
        org.apache.avro.Schema schema150 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList148);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer151 = null;
        schema150.forEachProperty(strBiConsumer151);
        java.lang.Integer int154 = schema150.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type155 = schema150.getType();
        java.lang.String str157 = schema150.getProp("enum");
        java.lang.String str159 = schema150.getProp("#");
        boolean boolean161 = schema150.propsContainsKey("enum");
        org.apache.avro.Schema[] schemaArray162 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList163 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean164 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList163, schemaArray162);
        org.apache.avro.Schema schema165 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList163);
        java.lang.String str166 = schema165.getDoc();
        schema150.addAllProps((org.apache.avro.JsonProperties) schema165);
        boolean boolean169 = schema165.propsContainsKey("[ ]");
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer170 = null;
        schema165.forEachProperty(strBiConsumer170);
        schema101.addAllProps((org.apache.avro.JsonProperties) schema165);
        schema62.addAllProps((org.apache.avro.JsonProperties) schema101);
        java.io.ByteArrayOutputStream byteArrayOutputStream175 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream175.reset();
        byte[] byteArray177 = byteArrayOutputStream175.toByteArray();
        boolean boolean178 = schema62.equals((java.lang.Object) byteArrayOutputStream175);
        byteArrayOutputStream37.writeTo((java.io.OutputStream) byteArrayOutputStream175);
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
        org.junit.Assert.assertNotNull(byteArray3);
        org.junit.Assert.assertArrayEquals(byteArray3, new byte[] {});
        org.junit.Assert.assertNotNull(schemaArray4);
        org.junit.Assert.assertArrayEquals(schemaArray4, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(schema7);
        org.junit.Assert.assertNull(int11);
        org.junit.Assert.assertTrue("'" + type12 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type12.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData15);
        org.junit.Assert.assertNull(genericData16);
        org.junit.Assert.assertNotNull(schemaArray17);
        org.junit.Assert.assertArrayEquals(schemaArray17, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(schema20);
        org.junit.Assert.assertNull(int24);
        org.junit.Assert.assertTrue("'" + type25 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type25.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray26);
        org.junit.Assert.assertArrayEquals(schemaArray26, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(schema29);
        org.junit.Assert.assertNull(str30);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertNull(logicalType33);
        org.junit.Assert.assertEquals(byteArrayOutputStream37.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray42);
        org.junit.Assert.assertArrayEquals(byteArray42, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream48.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray53);
        org.junit.Assert.assertArrayEquals(byteArray53, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj56);
        org.junit.Assert.assertEquals(obj56.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj56), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj56), "\000\001\n");
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(int66);
        org.junit.Assert.assertTrue("'" + type67 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type67.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str69);
        org.junit.Assert.assertTrue("'" + order74 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order74.equals(org.apache.avro.Schema.Field.Order.IGNORE));
        org.junit.Assert.assertEquals("'" + obj75 + "' != '" + 100L + "'", obj75, 100L);
        org.junit.Assert.assertNotNull(schemaArray76);
        org.junit.Assert.assertArrayEquals(schemaArray76, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean78 + "' != '" + false + "'", boolean78 == false);
        org.junit.Assert.assertNotNull(schema79);
        org.junit.Assert.assertNull(int83);
        org.junit.Assert.assertTrue("'" + type84 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type84.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData87);
        org.junit.Assert.assertNull(genericData88);
        org.junit.Assert.assertNotNull(schemaArray89);
        org.junit.Assert.assertArrayEquals(schemaArray89, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean91 + "' != '" + false + "'", boolean91 == false);
        org.junit.Assert.assertNotNull(schema92);
        org.junit.Assert.assertNull(int96);
        org.junit.Assert.assertTrue("'" + type97 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type97.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray98);
        org.junit.Assert.assertArrayEquals(schemaArray98, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean100 + "' != '" + false + "'", boolean100 == false);
        org.junit.Assert.assertNotNull(schema101);
        org.junit.Assert.assertNull(str102);
        org.junit.Assert.assertTrue("'" + boolean103 + "' != '" + false + "'", boolean103 == false);
        org.junit.Assert.assertNull(logicalType105);
        org.junit.Assert.assertEquals(byteArrayOutputStream109.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray114);
        org.junit.Assert.assertArrayEquals(byteArray114, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream120.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray125);
        org.junit.Assert.assertArrayEquals(byteArray125, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj128);
        org.junit.Assert.assertEquals(obj128.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj128), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj128), "\000\001\n");
        org.junit.Assert.assertNull(int130);
        org.junit.Assert.assertNotNull(schemaArray131);
        org.junit.Assert.assertArrayEquals(schemaArray131, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean133 + "' != '" + false + "'", boolean133 == false);
        org.junit.Assert.assertNotNull(schema134);
        org.junit.Assert.assertNull(int138);
        org.junit.Assert.assertTrue("'" + type139 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type139.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(genericData145);
        org.junit.Assert.assertNotNull(schemaArray147);
        org.junit.Assert.assertArrayEquals(schemaArray147, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean149 + "' != '" + false + "'", boolean149 == false);
        org.junit.Assert.assertNotNull(schema150);
        org.junit.Assert.assertNull(int154);
        org.junit.Assert.assertTrue("'" + type155 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type155.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str157);
        org.junit.Assert.assertNull(str159);
        org.junit.Assert.assertTrue("'" + boolean161 + "' != '" + false + "'", boolean161 == false);
        org.junit.Assert.assertNotNull(schemaArray162);
        org.junit.Assert.assertArrayEquals(schemaArray162, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean164 + "' != '" + false + "'", boolean164 == false);
        org.junit.Assert.assertNotNull(schema165);
        org.junit.Assert.assertNull(str166);
        org.junit.Assert.assertTrue("'" + boolean169 + "' != '" + false + "'", boolean169 == false);
        org.junit.Assert.assertEquals(byteArrayOutputStream175.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray177);
        org.junit.Assert.assertArrayEquals(byteArray177, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean178 + "' != '" + false + "'", boolean178 == false);
    }

    @Test
    public void test599() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test599");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter12 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema8);
        java.util.List<org.apache.avro.Schema> schemaList13 = schema8.getTypes();
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createUnion(schemaList13);
        boolean boolean15 = schema14.isUnion();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(schemaList13);
        org.junit.Assert.assertNotNull(schema14);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + true + "'", boolean15 == true);
    }

    @Test
    public void test600() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test600");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter12 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema8);
        java.lang.String str13 = schema8.getFullName();
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createMap(schema8);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "union[]" + "'", str13, "union[]");
        org.junit.Assert.assertNotNull(schema14);
    }

    @Test
    public void test601() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test601");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.generic.GenericData genericData9 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter10 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema3, genericData9);
        org.apache.avro.Schema[] schemaArray11 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList12 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList12, schemaArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList12);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer15 = null;
        schema14.forEachProperty(strBiConsumer15);
        java.lang.Integer int18 = schema14.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type19 = schema14.getType();
        org.apache.avro.Schema[] schemaArray20 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList21 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList21, schemaArray20);
        org.apache.avro.Schema schema23 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList21);
        java.lang.String str24 = schema23.getDoc();
        boolean boolean25 = schema23.hasProps();
        schema14.addAllProps((org.apache.avro.JsonProperties) schema23);
        boolean boolean28 = schema23.equals((java.lang.Object) "#");
        org.apache.avro.generic.GenericData genericData29 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter30 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema23, genericData29);
        java.lang.String str31 = schema23.getFullName();
        org.apache.avro.Schema[] schemaArray32 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList33 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean34 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList33, schemaArray32);
        org.apache.avro.Schema schema35 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList33);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer36 = null;
        schema35.forEachProperty(strBiConsumer36);
        java.lang.Integer int39 = schema35.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type40 = schema35.getType();
        org.apache.avro.Schema[] schemaArray41 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList42 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean43 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList42, schemaArray41);
        org.apache.avro.Schema schema44 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList42);
        java.lang.String str45 = schema44.getDoc();
        boolean boolean46 = schema44.hasProps();
        schema35.addAllProps((org.apache.avro.JsonProperties) schema44);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer48 = null;
        schema35.forEachProperty(strBiConsumer48);
        schema23.putAll((org.apache.avro.JsonProperties) schema35);
        schemaListGenericDatumWriter10.setSchema(schema35);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray11);
        org.junit.Assert.assertArrayEquals(schemaArray11, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(schema14);
        org.junit.Assert.assertNull(int18);
        org.junit.Assert.assertTrue("'" + type19 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type19.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray20);
        org.junit.Assert.assertArrayEquals(schemaArray20, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(schema23);
        org.junit.Assert.assertNull(str24);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "union[]" + "'", str31, "union[]");
        org.junit.Assert.assertNotNull(schemaArray32);
        org.junit.Assert.assertArrayEquals(schemaArray32, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(schema35);
        org.junit.Assert.assertNull(int39);
        org.junit.Assert.assertTrue("'" + type40 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type40.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray41);
        org.junit.Assert.assertArrayEquals(schemaArray41, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(schema44);
        org.junit.Assert.assertNull(str45);
        org.junit.Assert.assertTrue("'" + boolean46 + "' != '" + false + "'", boolean46 == false);
    }

    @Test
    public void test602() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test602");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        java.lang.String str13 = schema3.getProp("#");
        boolean boolean14 = schema3.hasProps();
        java.lang.String str15 = schema3.toString();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "[]" + "'", str15, "[]");
    }

    @Test
    public void test603() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test603");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter13 = charSequenceDataFileWriter10.setMeta("", "\001");
        org.apache.avro.file.CodecFactory codecFactory14 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter15 = charSequenceDataFileWriter13.setCodec(codecFactory14);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.file.CodecFactory.createInstance()\" because \"c\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter13);
    }

    @Test
    public void test604() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test604");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.File file11 = null;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter12 = charSequenceDataFileWriter4.appendTo(file11);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
    }

    @Test
    public void test605() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test605");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        schema3.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Type type14 = schema3.getType();
        java.lang.Class<?> wildcardClass15 = type14.getClass();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + type14 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type14.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(wildcardClass15);
    }

    @Test
    public void test606() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test606");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (short) 100);
        java.io.ByteArrayOutputStream byteArrayOutputStream3 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream3.write((int) '#');
        byte[] byteArray8 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream3.writeBytes(byteArray8);
        byteArrayOutputStream3.reset();
        byteArrayOutputStream3.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream14 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream14.write((int) '#');
        byte[] byteArray19 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream14.writeBytes(byteArray19);
        byteArrayOutputStream3.writeBytes(byteArray19);
        byteArrayOutputStream3.write((int) 'a');
        byte[] byteArray24 = byteArrayOutputStream3.toByteArray();
        byteArrayOutputStream1.writeBytes(byteArray24);
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "\000\001\na");
        org.junit.Assert.assertEquals(byteArrayOutputStream3.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray8);
        org.junit.Assert.assertArrayEquals(byteArray8, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream14.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray19);
        org.junit.Assert.assertArrayEquals(byteArray19, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray24);
        org.junit.Assert.assertArrayEquals(byteArray24, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
    }

    @Test
    public void test607() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test607");
        org.apache.avro.Schema.Parser parser0 = new org.apache.avro.Schema.Parser();
        boolean boolean1 = parser0.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap2 = parser0.getTypes();
        org.apache.avro.Schema[] schemaArray3 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList4 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList4, schemaArray3);
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser7 = parser0.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList4);
        org.apache.avro.Schema.Parser parser9 = parser7.setValidateDefaults(true);
        org.apache.avro.Schema.Parser parser10 = new org.apache.avro.Schema.Parser();
        boolean boolean11 = parser10.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap12 = parser10.getTypes();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser17 = parser10.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser18 = parser7.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.Schema.Parser parser19 = new org.apache.avro.Schema.Parser();
        boolean boolean20 = parser19.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap21 = parser19.getTypes();
        org.apache.avro.Schema[] schemaArray22 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList23 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList23, schemaArray22);
        org.apache.avro.Schema schema25 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList23);
        org.apache.avro.Schema.Parser parser26 = parser19.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList23);
        org.apache.avro.Schema.Parser parser27 = new org.apache.avro.Schema.Parser();
        boolean boolean28 = parser27.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap29 = parser27.getTypes();
        org.apache.avro.Schema.Parser parser30 = parser19.addTypes(strMap29);
        org.apache.avro.Schema.Parser parser31 = parser7.addTypes(strMap29);
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap32 = parser31.getTypes();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(strMap2);
        org.junit.Assert.assertNotNull(schemaArray3);
        org.junit.Assert.assertArrayEquals(schemaArray3, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(schema6);
        org.junit.Assert.assertNotNull(parser7);
        org.junit.Assert.assertNotNull(parser9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(strMap12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNotNull(parser17);
        org.junit.Assert.assertNotNull(parser18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(strMap21);
        org.junit.Assert.assertNotNull(schemaArray22);
        org.junit.Assert.assertArrayEquals(schemaArray22, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertNotNull(schema25);
        org.junit.Assert.assertNotNull(parser26);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertNotNull(strMap29);
        org.junit.Assert.assertNotNull(parser30);
        org.junit.Assert.assertNotNull(parser31);
        org.junit.Assert.assertNotNull(strMap32);
    }

    @Test
    public void test608() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test608");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter4.flush();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
    }

    @Test
    public void test609() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test609");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        org.apache.avro.Schema schema7 = null;
        java.io.ByteArrayOutputStream byteArrayOutputStream9 = new java.io.ByteArrayOutputStream((int) (short) 10);
        java.io.ByteArrayOutputStream byteArrayOutputStream11 = new java.io.ByteArrayOutputStream((int) ' ');
        byte[] byteArray12 = byteArrayOutputStream11.toByteArray();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer17 = null;
        schema16.forEachProperty(strBiConsumer17);
        java.lang.Integer int20 = schema16.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type21 = schema16.getType();
        org.apache.avro.generic.GenericData genericData22 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter23 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema16, genericData22);
        org.apache.avro.generic.GenericData genericData24 = schemaListGenericDatumWriter23.getData();
        org.apache.avro.generic.GenericData genericData25 = schemaListGenericDatumWriter23.getData();
        org.apache.avro.Schema[] schemaArray26 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList27 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList27, schemaArray26);
        org.apache.avro.Schema schema29 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList27);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer30 = null;
        schema29.forEachProperty(strBiConsumer30);
        java.lang.Integer int33 = schema29.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type34 = schema29.getType();
        org.apache.avro.Schema[] schemaArray35 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList36 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean37 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList36, schemaArray35);
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList36);
        java.lang.String str39 = schema38.getDoc();
        boolean boolean40 = schema38.hasProps();
        schema29.addAllProps((org.apache.avro.JsonProperties) schema38);
        org.apache.avro.LogicalType logicalType42 = schema38.getLogicalType();
        schemaListGenericDatumWriter23.setSchema(schema38);
        java.io.ByteArrayOutputStream byteArrayOutputStream46 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream46.write((int) '#');
        byte[] byteArray51 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream46.writeBytes(byteArray51);
        byteArrayOutputStream46.reset();
        byteArrayOutputStream46.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream57 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream57.write((int) '#');
        byte[] byteArray62 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream57.writeBytes(byteArray62);
        byteArrayOutputStream46.writeBytes(byteArray62);
        java.lang.Object obj65 = schema38.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream46);
        java.io.ByteArrayOutputStream byteArrayOutputStream67 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream67.write((int) '#');
        byte[] byteArray72 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream67.writeBytes(byteArray72);
        byteArrayOutputStream67.reset();
        byteArrayOutputStream67.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream78 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream78.write((int) '#');
        byte[] byteArray83 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream78.writeBytes(byteArray83);
        byteArrayOutputStream67.writeBytes(byteArray83);
        byteArrayOutputStream67.write((int) 'a');
        byte[] byteArray88 = byteArrayOutputStream67.toByteArray();
        java.io.ByteArrayOutputStream byteArrayOutputStream90 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream90.write((int) '#');
        byte[] byteArray95 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream90.writeBytes(byteArray95);
        byteArrayOutputStream90.reset();
        byte[] byteArray98 = byteArrayOutputStream90.toByteArray();
        byteArrayOutputStream67.write(byteArray98);
        byteArrayOutputStream46.writeBytes(byteArray98);
        byteArrayOutputStream11.write(byteArray98);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter102 = charSequenceDataFileWriter6.create(schema7, (java.io.OutputStream) byteArrayOutputStream9, byteArray98);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.Schema.toString()\" because \"schema\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertEquals(byteArrayOutputStream9.toString(), "");
        org.junit.Assert.assertEquals(byteArrayOutputStream11.toString(), "");
        org.junit.Assert.assertNotNull(byteArray12);
        org.junit.Assert.assertArrayEquals(byteArray12, new byte[] {});
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(int20);
        org.junit.Assert.assertTrue("'" + type21 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type21.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData24);
        org.junit.Assert.assertNull(genericData25);
        org.junit.Assert.assertNotNull(schemaArray26);
        org.junit.Assert.assertArrayEquals(schemaArray26, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(schema29);
        org.junit.Assert.assertNull(int33);
        org.junit.Assert.assertTrue("'" + type34 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type34.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray35);
        org.junit.Assert.assertArrayEquals(schemaArray35, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertNotNull(schema38);
        org.junit.Assert.assertNull(str39);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertNull(logicalType42);
        org.junit.Assert.assertEquals(byteArrayOutputStream46.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray51);
        org.junit.Assert.assertArrayEquals(byteArray51, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream57.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray62);
        org.junit.Assert.assertArrayEquals(byteArray62, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj65);
        org.junit.Assert.assertEquals(obj65.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj65), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj65), "\000\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream67.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray72);
        org.junit.Assert.assertArrayEquals(byteArray72, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream78.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray83);
        org.junit.Assert.assertArrayEquals(byteArray83, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray88);
        org.junit.Assert.assertArrayEquals(byteArray88, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertEquals(byteArrayOutputStream90.toString(), "");
        org.junit.Assert.assertNotNull(byteArray95);
        org.junit.Assert.assertArrayEquals(byteArray95, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray98);
        org.junit.Assert.assertArrayEquals(byteArray98, new byte[] {});
    }

    @Test
    public void test610() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test610");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        boolean boolean6 = schema3.isUnion();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.generic.GenericData genericData16 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter17 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema10, genericData16);
        java.lang.String str18 = schema10.getFullName();
        org.apache.avro.Schema[] schemaArray19 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList20 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList20, schemaArray19);
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList20);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer23 = null;
        schema22.forEachProperty(strBiConsumer23);
        java.lang.Integer int26 = schema22.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap27 = schema22.getObjectProps();
        org.apache.avro.Schema[] schemaArray28 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList29 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList29, schemaArray28);
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList29);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer32 = null;
        schema31.forEachProperty(strBiConsumer32);
        java.lang.Integer int35 = schema31.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type36 = schema31.getType();
        java.lang.String str38 = schema31.getProp("enum");
        boolean boolean40 = schema31.propsContainsKey("");
        boolean boolean41 = schema31.isUnion();
        java.lang.String str42 = schema31.toString();
        boolean boolean43 = schema22.equals((java.lang.Object) str42);
        java.util.List<org.apache.avro.Schema> schemaList44 = schema22.getTypes();
        java.lang.String str46 = schema10.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, true);
        java.lang.String str48 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, false);
        org.apache.avro.Schema.Parser parser49 = new org.apache.avro.Schema.Parser();
        boolean boolean50 = parser49.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap51 = parser49.getTypes();
        org.apache.avro.Schema schema53 = parser49.parseInternal("[]");
        java.util.Map<java.lang.String, java.lang.Object> strMap54 = schema53.getObjectProps();
        org.apache.avro.Schema schema55 = org.apache.avro.Schema.applyAliases(schema3, schema53);
        java.lang.Object obj57 = schema53.getObjectProp("{\"type\":\"record\",\"fields\":[]}");
        java.lang.String str58 = schema53.getFullName();
        // The following exception was thrown during execution in test generation
        try {
            schema53.addAlias("\u6423\u6401\u640a", "[]");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "union[]" + "'", str18, "union[]");
        org.junit.Assert.assertNotNull(schemaArray19);
        org.junit.Assert.assertArrayEquals(schemaArray19, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(schema22);
        org.junit.Assert.assertNull(int26);
        org.junit.Assert.assertNotNull(strMap27);
        org.junit.Assert.assertNotNull(schemaArray28);
        org.junit.Assert.assertArrayEquals(schemaArray28, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(schema31);
        org.junit.Assert.assertNull(int35);
        org.junit.Assert.assertTrue("'" + type36 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type36.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str38);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "[]" + "'", str42, "[]");
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(schemaList44);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "[ ]" + "'", str46, "[ ]");
        org.junit.Assert.assertEquals("'" + str48 + "' != '" + "[]" + "'", str48, "[]");
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertNotNull(strMap51);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNotNull(strMap54);
        org.junit.Assert.assertNotNull(schema55);
        org.junit.Assert.assertNull(obj57);
        org.junit.Assert.assertEquals("'" + str58 + "' != '" + "union[]" + "'", str58, "union[]");
    }

    @Test
    public void test611() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test611");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap8 = schema3.getObjectProps();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer13 = null;
        schema12.forEachProperty(strBiConsumer13);
        java.lang.Integer int16 = schema12.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type17 = schema12.getType();
        java.lang.String str19 = schema12.getProp("enum");
        boolean boolean21 = schema12.propsContainsKey("");
        boolean boolean22 = schema12.isUnion();
        java.lang.String str23 = schema12.toString();
        boolean boolean24 = schema3.equals((java.lang.Object) str23);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema schema25 = schema3.getElementType();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not an array: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertNotNull(strMap8);
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(int16);
        org.junit.Assert.assertTrue("'" + type17 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type17.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str19);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "[]" + "'", str23, "[]");
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
    }

    @Test
    public void test612() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test612");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        boolean boolean6 = schema3.isUnion();
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer11 = null;
        schema10.forEachProperty(strBiConsumer11);
        java.lang.Integer int14 = schema10.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type15 = schema10.getType();
        org.apache.avro.generic.GenericData genericData16 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter17 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema10, genericData16);
        java.lang.String str18 = schema10.getFullName();
        org.apache.avro.Schema[] schemaArray19 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList20 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList20, schemaArray19);
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList20);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer23 = null;
        schema22.forEachProperty(strBiConsumer23);
        java.lang.Integer int26 = schema22.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap27 = schema22.getObjectProps();
        org.apache.avro.Schema[] schemaArray28 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList29 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean30 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList29, schemaArray28);
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList29);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer32 = null;
        schema31.forEachProperty(strBiConsumer32);
        java.lang.Integer int35 = schema31.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type36 = schema31.getType();
        java.lang.String str38 = schema31.getProp("enum");
        boolean boolean40 = schema31.propsContainsKey("");
        boolean boolean41 = schema31.isUnion();
        java.lang.String str42 = schema31.toString();
        boolean boolean43 = schema22.equals((java.lang.Object) str42);
        java.util.List<org.apache.avro.Schema> schemaList44 = schema22.getTypes();
        java.lang.String str46 = schema10.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, true);
        java.lang.String str48 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList44, false);
        org.apache.avro.Schema.Parser parser49 = new org.apache.avro.Schema.Parser();
        boolean boolean50 = parser49.getValidateDefaults();
        java.util.Map<java.lang.String, org.apache.avro.Schema> strMap51 = parser49.getTypes();
        org.apache.avro.Schema schema53 = parser49.parseInternal("[]");
        java.util.Map<java.lang.String, java.lang.Object> strMap54 = schema53.getObjectProps();
        org.apache.avro.Schema schema55 = org.apache.avro.Schema.applyAliases(schema3, schema53);
        java.lang.String str57 = schema53.getProp("map");
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(int14);
        org.junit.Assert.assertTrue("'" + type15 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type15.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "union[]" + "'", str18, "union[]");
        org.junit.Assert.assertNotNull(schemaArray19);
        org.junit.Assert.assertArrayEquals(schemaArray19, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertNotNull(schema22);
        org.junit.Assert.assertNull(int26);
        org.junit.Assert.assertNotNull(strMap27);
        org.junit.Assert.assertNotNull(schemaArray28);
        org.junit.Assert.assertArrayEquals(schemaArray28, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertNotNull(schema31);
        org.junit.Assert.assertNull(int35);
        org.junit.Assert.assertTrue("'" + type36 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type36.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str38);
        org.junit.Assert.assertTrue("'" + boolean40 + "' != '" + false + "'", boolean40 == false);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + true + "'", boolean41 == true);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "[]" + "'", str42, "[]");
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + false + "'", boolean43 == false);
        org.junit.Assert.assertNotNull(schemaList44);
        org.junit.Assert.assertEquals("'" + str46 + "' != '" + "[ ]" + "'", str46, "[ ]");
        org.junit.Assert.assertEquals("'" + str48 + "' != '" + "[]" + "'", str48, "[]");
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertNotNull(strMap51);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNotNull(strMap54);
        org.junit.Assert.assertNotNull(schema55);
        org.junit.Assert.assertNull(str57);
    }

    @Test
    public void test613() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test613");
        java.io.ByteArrayOutputStream byteArrayOutputStream1 = new java.io.ByteArrayOutputStream((int) (byte) 100);
        org.junit.Assert.assertEquals(byteArrayOutputStream1.toString(), "");
    }

    @Test
    public void test614() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test614");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        org.apache.avro.Schema[] schemaArray9 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList10 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList10, schemaArray9);
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList10);
        java.lang.String str13 = schema12.getDoc();
        boolean boolean14 = schema12.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema12);
        boolean boolean17 = schema12.equals((java.lang.Object) "#");
        org.apache.avro.generic.GenericData genericData18 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter19 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema12, genericData18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer20 = null;
        schema12.forEachProperty(strBiConsumer20);
        // The following exception was thrown during execution in test generation
        try {
            schema12.addAlias("hi!", "\u6400\u6401\u640a");
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: Not a named type: []");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray9);
        org.junit.Assert.assertArrayEquals(schemaArray9, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertNotNull(schema12);
        org.junit.Assert.assertNull(str13);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
    }

    @Test
    public void test615() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test615");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        // The following exception was thrown during execution in test generation
        try {
            charSequenceDataFileWriter1.flush();
            org.junit.Assert.fail("Expected exception of type org.apache.avro.AvroRuntimeException; message: not open");
        } catch (org.apache.avro.AvroRuntimeException e) {
            // Expected exception.
        }
    }

    @Test
    public void test616() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test616");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction5 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter6 = charSequenceDataFileWriter4.setEncoder(outputStreamFunction5);
        byte[] byteArray9 = new byte[] { (byte) 10 };
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter10 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray9);
        java.io.ByteArrayOutputStream byteArrayOutputStream13 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream13.write((int) '#');
        byte[] byteArray18 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream13.writeBytes(byteArray18);
        byteArrayOutputStream13.reset();
        byteArrayOutputStream13.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream24 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream24.write((int) '#');
        byte[] byteArray29 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream24.writeBytes(byteArray29);
        byteArrayOutputStream13.writeBytes(byteArray29);
        byteArrayOutputStream13.write((int) 'a');
        byte[] byteArray34 = byteArrayOutputStream13.toByteArray();
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter35 = charSequenceDataFileWriter4.setMeta("[ ]", byteArray34);
        org.apache.avro.Schema[] schemaArray37 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList38 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList38, schemaArray37);
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList38);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer41 = null;
        schema40.forEachProperty(strBiConsumer41);
        java.lang.Integer int44 = schema40.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type45 = schema40.getType();
        org.apache.avro.generic.GenericData genericData46 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter47 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema40, genericData46);
        org.apache.avro.generic.GenericData genericData48 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.generic.GenericData genericData49 = schemaListGenericDatumWriter47.getData();
        org.apache.avro.Schema[] schemaArray50 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList51 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean52 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList51, schemaArray50);
        org.apache.avro.Schema schema53 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList51);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer54 = null;
        schema53.forEachProperty(strBiConsumer54);
        java.lang.Integer int57 = schema53.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type58 = schema53.getType();
        org.apache.avro.Schema[] schemaArray59 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList60 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean61 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList60, schemaArray59);
        org.apache.avro.Schema schema62 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList60);
        java.lang.String str63 = schema62.getDoc();
        boolean boolean64 = schema62.hasProps();
        schema53.addAllProps((org.apache.avro.JsonProperties) schema62);
        org.apache.avro.LogicalType logicalType66 = schema62.getLogicalType();
        schemaListGenericDatumWriter47.setSchema(schema62);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        java.lang.Object obj89 = schema62.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream70);
        java.io.ByteArrayOutputStream byteArrayOutputStream91 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream91.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream95 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream95.write((int) '#');
        byte[] byteArray100 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream95.writeBytes(byteArray100);
        byteArrayOutputStream91.write(byteArray100);
        byteArrayOutputStream70.write(byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter104 = charSequenceDataFileWriter35.setMeta("\uff00\uff01\uff0a", byteArray100);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter107 = charSequenceDataFileWriter35.setMeta("fixed", 0L);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction108 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter109 = charSequenceDataFileWriter35.setEncoder(outputStreamFunction108);
        charSequenceDataFileWriter35.setFlushOnEveryBlock(true);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter114 = charSequenceDataFileWriter35.setMeta("{\"type\":\"record\",\"fields\":[]}", "#\001\n");
        boolean boolean115 = charSequenceDataFileWriter114.isFlushOnEveryBlock();
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter6);
        org.junit.Assert.assertNotNull(byteArray9);
        org.junit.Assert.assertArrayEquals(byteArray9, new byte[] { (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter10);
        org.junit.Assert.assertEquals(byteArrayOutputStream13.toString(), "\000\001\na");
        org.junit.Assert.assertNotNull(byteArray18);
        org.junit.Assert.assertArrayEquals(byteArray18, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream24.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray29);
        org.junit.Assert.assertArrayEquals(byteArray29, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray34);
        org.junit.Assert.assertArrayEquals(byteArray34, new byte[] { (byte) 0, (byte) 1, (byte) 10, (byte) 97 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter35);
        org.junit.Assert.assertNotNull(schemaArray37);
        org.junit.Assert.assertArrayEquals(schemaArray37, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertNotNull(schema40);
        org.junit.Assert.assertNull(int44);
        org.junit.Assert.assertTrue("'" + type45 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type45.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData48);
        org.junit.Assert.assertNull(genericData49);
        org.junit.Assert.assertNotNull(schemaArray50);
        org.junit.Assert.assertArrayEquals(schemaArray50, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertNotNull(schema53);
        org.junit.Assert.assertNull(int57);
        org.junit.Assert.assertTrue("'" + type58 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type58.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray59);
        org.junit.Assert.assertArrayEquals(schemaArray59, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean61 + "' != '" + false + "'", boolean61 == false);
        org.junit.Assert.assertNotNull(schema62);
        org.junit.Assert.assertNull(str63);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
        org.junit.Assert.assertNull(logicalType66);
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj89);
        org.junit.Assert.assertEquals(obj89.toString(), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj89), "\000\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream91.toString(), "#\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream95.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray100);
        org.junit.Assert.assertArrayEquals(byteArray100, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter104);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter107);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter109);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter114);
        org.junit.Assert.assertTrue("'" + boolean115 + "' != '" + true + "'", boolean115 == true);
    }

    @Test
    public void test617() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test617");
        org.apache.avro.Schema.Field[] fieldArray0 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList1 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList1, fieldArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        org.apache.avro.Schema.Field field6 = schema4.getField("\u6423\u6401\u640a");
        boolean boolean7 = schema4.isUnion();
        org.junit.Assert.assertNotNull(fieldArray0);
        org.junit.Assert.assertArrayEquals(fieldArray0, new org.apache.avro.Schema.Field[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNull(field6);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test618() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test618");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        charSequenceDataFileWriter1.setFlushOnEveryBlock(true);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction6 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter7 = charSequenceDataFileWriter1.setEncoder(outputStreamFunction6);
        java.util.function.Function<java.io.OutputStream, org.apache.avro.io.BinaryEncoder> outputStreamFunction8 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter9 = charSequenceDataFileWriter7.setEncoder(outputStreamFunction8);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter12 = charSequenceDataFileWriter7.setMeta("{\"type\":\"record\",\"fields\":[]}", (long) (short) 100);
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        org.apache.avro.LogicalType logicalType17 = schema16.getLogicalType();
        java.lang.String str18 = schema16.getDoc();
        java.lang.String str19 = schema16.getName();
        org.apache.avro.Schema[] schemaArray20 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList21 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList21, schemaArray20);
        org.apache.avro.Schema schema23 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList21);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer24 = null;
        schema23.forEachProperty(strBiConsumer24);
        java.lang.Integer int27 = schema23.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type28 = schema23.getType();
        org.apache.avro.generic.GenericData genericData29 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter30 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema23, genericData29);
        java.lang.String str31 = schema23.getFullName();
        java.lang.String str32 = schema23.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream34 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream34.write((int) '#');
        byte[] byteArray39 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream34.writeBytes(byteArray39);
        byteArrayOutputStream34.reset();
        byteArrayOutputStream34.write((int) (byte) 0);
        byteArrayOutputStream34.reset();
        byteArrayOutputStream34.write(1);
        byte[] byteArray47 = byteArrayOutputStream34.toByteArray();
        boolean boolean48 = schema23.equals((java.lang.Object) byteArray47);
        java.util.List<org.apache.avro.Schema> schemaList49 = schema23.getTypes();
        boolean boolean50 = schema16.equals((java.lang.Object) schema23);
        org.apache.avro.Schema schema51 = org.apache.avro.Schema.createMap(schema16);
        java.lang.Object obj53 = schema51.getObjectProp("[ ]");
        java.lang.Object obj55 = schema51.getObjectProp("\001");
        java.io.ByteArrayOutputStream byteArrayOutputStream57 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream57.write((int) '#');
        java.io.ByteArrayOutputStream byteArrayOutputStream61 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream61.write((int) '#');
        byte[] byteArray66 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream61.writeBytes(byteArray66);
        byteArrayOutputStream57.writeBytes(byteArray66);
        java.io.ByteArrayOutputStream byteArrayOutputStream70 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream70.write((int) '#');
        byte[] byteArray75 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream70.writeBytes(byteArray75);
        byteArrayOutputStream70.reset();
        byteArrayOutputStream70.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream81 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream81.write((int) '#');
        byte[] byteArray86 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream81.writeBytes(byteArray86);
        byteArrayOutputStream70.writeBytes(byteArray86);
        byteArrayOutputStream57.writeBytes(byteArray86);
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter90 = charSequenceDataFileWriter12.create(schema51, (java.io.OutputStream) byteArrayOutputStream57);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.io.DatumWriter.setSchema(org.apache.avro.Schema)\" because \"this.dout\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter7);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter9);
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter12);
        org.junit.Assert.assertNotNull(schemaArray13);
        org.junit.Assert.assertArrayEquals(schemaArray13, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertNotNull(schema16);
        org.junit.Assert.assertNull(logicalType17);
        org.junit.Assert.assertNull(str18);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "union[]" + "'", str19, "union[]");
        org.junit.Assert.assertNotNull(schemaArray20);
        org.junit.Assert.assertArrayEquals(schemaArray20, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertNotNull(schema23);
        org.junit.Assert.assertNull(int27);
        org.junit.Assert.assertTrue("'" + type28 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type28.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str31 + "' != '" + "union[]" + "'", str31, "union[]");
        org.junit.Assert.assertNull(str32);
        org.junit.Assert.assertEquals(byteArrayOutputStream34.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray39);
        org.junit.Assert.assertArrayEquals(byteArray39, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray47);
        org.junit.Assert.assertArrayEquals(byteArray47, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertNotNull(schemaList49);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertNotNull(schema51);
        org.junit.Assert.assertNull(obj53);
        org.junit.Assert.assertNull(obj55);
        org.junit.Assert.assertEquals(byteArrayOutputStream57.toString(), "#\001\n\001\n");
        org.junit.Assert.assertEquals(byteArrayOutputStream61.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray66);
        org.junit.Assert.assertArrayEquals(byteArray66, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream70.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray75);
        org.junit.Assert.assertArrayEquals(byteArray75, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream81.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray86);
        org.junit.Assert.assertArrayEquals(byteArray86, new byte[] { (byte) 1, (byte) 10 });
    }

    @Test
    public void test619() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test619");
        org.apache.avro.Schema.Type type0 = org.apache.avro.Schema.Type.FLOAT;
        java.lang.String str1 = type0.getName();
        org.junit.Assert.assertTrue("'" + type0 + "' != '" + org.apache.avro.Schema.Type.FLOAT + "'", type0.equals(org.apache.avro.Schema.Type.FLOAT));
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "float" + "'", str1, "float");
    }

    @Test
    public void test620() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test620");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.lang.String str4 = schema3.getDoc();
        org.apache.avro.Schema[] schemaArray5 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList6 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList6, schemaArray5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList6);
        java.lang.String str9 = schema8.getDoc();
        boolean boolean10 = schema8.hasProps();
        schema3.addAllProps((org.apache.avro.JsonProperties) schema8);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter12 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema8);
        java.util.List<org.apache.avro.Schema> schemaList13 = schema8.getTypes();
        org.apache.avro.LogicalType logicalType14 = schema8.getLogicalType();
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(schemaArray5);
        org.junit.Assert.assertArrayEquals(schemaArray5, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(schema8);
        org.junit.Assert.assertNull(str9);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertNotNull(schemaList13);
        org.junit.Assert.assertNull(logicalType14);
    }

    @Test
    public void test621() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test621");
        org.apache.avro.Schema[] schemaArray1 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList2 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean3 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList2, schemaArray1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList2);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer5 = null;
        schema4.forEachProperty(strBiConsumer5);
        java.lang.Integer int8 = schema4.getIndexNamed("hi!");
        java.lang.Integer int10 = schema4.getIndexNamed("");
        java.lang.String str11 = schema4.getDoc();
        boolean boolean12 = schema4.isUnion();
        org.apache.avro.Schema[] schemaArray14 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList15 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList15, schemaArray14);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList15);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer18 = null;
        schema17.forEachProperty(strBiConsumer18);
        java.lang.Integer int21 = schema17.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type22 = schema17.getType();
        java.lang.String str24 = schema17.getProp("enum");
        java.lang.String str26 = schema17.getProp("#");
        java.lang.String str27 = schema17.getDoc();
        org.apache.avro.Schema.SeenPair seenPair29 = new org.apache.avro.Schema.SeenPair((java.lang.Object) schema17, (java.lang.Object) "[]");
        java.lang.Integer int31 = schema17.getIndexNamed("enum");
        java.lang.Integer int33 = schema17.getIndexNamed("\u2300\u2301\u230a\u2361");
        java.util.Map<java.lang.String, java.lang.Object> strMap34 = schema17.getObjectProps();
        org.apache.avro.Schema.Field.Order order35 = org.apache.avro.Schema.Field.Order.IGNORE;
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.Schema.Field field36 = null; // flaky "4) test621(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)": new org.apache.avro.Schema.Field("float", schema4, "{\"type\":\"record\",\"fields\":[]}", (java.lang.Object) strMap34, order35);
// flaky "4) test621(org.apache.avro.randoop.datafilewriter.DataFileWriterRandoopRegressionTest1)":             org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.NameValidator.validate(String)\" because the return value of \"java.lang.ThreadLocal.get()\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(schemaArray1);
        org.junit.Assert.assertArrayEquals(schemaArray1, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertNotNull(schema4);
        org.junit.Assert.assertNull(int8);
        org.junit.Assert.assertNull(int10);
        org.junit.Assert.assertNull(str11);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertNotNull(schemaArray14);
        org.junit.Assert.assertArrayEquals(schemaArray14, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(schema17);
        org.junit.Assert.assertNull(int21);
        org.junit.Assert.assertTrue("'" + type22 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type22.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str24);
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertNull(str27);
        org.junit.Assert.assertNull(int31);
        org.junit.Assert.assertNull(int33);
        org.junit.Assert.assertNotNull(strMap34);
        org.junit.Assert.assertTrue("'" + order35 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order35.equals(org.apache.avro.Schema.Field.Order.IGNORE));
    }

    @Test
    public void test622() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test622");
        org.apache.avro.io.DatumWriter<java.lang.CharSequence> charSequenceDatumWriter0 = null;
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter1 = new org.apache.avro.file.DataFileWriter<java.lang.CharSequence>(charSequenceDatumWriter0);
        org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter4 = charSequenceDataFileWriter1.setMeta("\u2300\u2301\u230a\u2361", (-1L));
        charSequenceDataFileWriter4.setFlushOnEveryBlock(false);
        org.apache.avro.Schema[] schemaArray7 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList8 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList8, schemaArray7);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList8);
        org.apache.avro.LogicalType logicalType11 = schema10.getLogicalType();
        java.lang.String str12 = schema10.getDoc();
        java.lang.String str13 = schema10.getName();
        org.apache.avro.Schema[] schemaArray14 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList15 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList15, schemaArray14);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList15);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer18 = null;
        schema17.forEachProperty(strBiConsumer18);
        java.lang.Integer int21 = schema17.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type22 = schema17.getType();
        org.apache.avro.generic.GenericData genericData23 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter24 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema17, genericData23);
        java.lang.String str25 = schema17.getFullName();
        java.lang.String str26 = schema17.getDoc();
        java.io.ByteArrayOutputStream byteArrayOutputStream28 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream28.write((int) '#');
        byte[] byteArray33 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream28.writeBytes(byteArray33);
        byteArrayOutputStream28.reset();
        byteArrayOutputStream28.write((int) (byte) 0);
        byteArrayOutputStream28.reset();
        byteArrayOutputStream28.write(1);
        byte[] byteArray41 = byteArrayOutputStream28.toByteArray();
        boolean boolean42 = schema17.equals((java.lang.Object) byteArray41);
        java.util.List<org.apache.avro.Schema> schemaList43 = schema17.getTypes();
        boolean boolean44 = schema10.equals((java.lang.Object) schema17);
        java.io.ByteArrayOutputStream byteArrayOutputStream46 = new java.io.ByteArrayOutputStream((int) ' ');
        // The following exception was thrown during execution in test generation
        try {
            org.apache.avro.file.DataFileWriter<java.lang.CharSequence> charSequenceDataFileWriter47 = charSequenceDataFileWriter4.create(schema17, (java.io.OutputStream) byteArrayOutputStream46);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"org.apache.avro.io.DatumWriter.setSchema(org.apache.avro.Schema)\" because \"this.dout\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(charSequenceDataFileWriter4);
        org.junit.Assert.assertNotNull(schemaArray7);
        org.junit.Assert.assertArrayEquals(schemaArray7, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(schema10);
        org.junit.Assert.assertNull(logicalType11);
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "union[]" + "'", str13, "union[]");
        org.junit.Assert.assertNotNull(schemaArray14);
        org.junit.Assert.assertArrayEquals(schemaArray14, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(schema17);
        org.junit.Assert.assertNull(int21);
        org.junit.Assert.assertTrue("'" + type22 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type22.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "union[]" + "'", str25, "union[]");
        org.junit.Assert.assertNull(str26);
        org.junit.Assert.assertEquals(byteArrayOutputStream28.toString(), "\001");
        org.junit.Assert.assertNotNull(byteArray33);
        org.junit.Assert.assertArrayEquals(byteArray33, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(byteArray41);
        org.junit.Assert.assertArrayEquals(byteArray41, new byte[] { (byte) 1 });
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
        org.junit.Assert.assertNotNull(schemaList43);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + true + "'", boolean44 == true);
        org.junit.Assert.assertEquals(byteArrayOutputStream46.toString(), "");
    }

    @Test
    public void test623() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopRegressionTest1.test623");
        org.apache.avro.Schema[] schemaArray0 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList1 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList1, schemaArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList1);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer4 = null;
        schema3.forEachProperty(strBiConsumer4);
        java.lang.Integer int7 = schema3.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type8 = schema3.getType();
        java.lang.String str10 = schema3.getProp("enum");
        schema3.addProp("hi!", (java.lang.Object) 100L);
        org.apache.avro.Schema.Field.Order order15 = org.apache.avro.Schema.Field.Order.IGNORE;
        java.lang.Object obj16 = schema3.getObjectProp("hi!", (java.lang.Object) order15);
        org.apache.avro.Schema[] schemaArray17 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList18 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList18, schemaArray17);
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList18);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer21 = null;
        schema20.forEachProperty(strBiConsumer21);
        java.lang.Integer int24 = schema20.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type25 = schema20.getType();
        org.apache.avro.generic.GenericData genericData26 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter27 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema20, genericData26);
        org.apache.avro.generic.GenericData genericData28 = schemaListGenericDatumWriter27.getData();
        org.apache.avro.generic.GenericData genericData29 = schemaListGenericDatumWriter27.getData();
        org.apache.avro.Schema[] schemaArray30 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList31 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList31, schemaArray30);
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList31);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer34 = null;
        schema33.forEachProperty(strBiConsumer34);
        java.lang.Integer int37 = schema33.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type38 = schema33.getType();
        org.apache.avro.Schema[] schemaArray39 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList40 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean41 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList40, schemaArray39);
        org.apache.avro.Schema schema42 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList40);
        java.lang.String str43 = schema42.getDoc();
        boolean boolean44 = schema42.hasProps();
        schema33.addAllProps((org.apache.avro.JsonProperties) schema42);
        org.apache.avro.LogicalType logicalType46 = schema42.getLogicalType();
        schemaListGenericDatumWriter27.setSchema(schema42);
        java.io.ByteArrayOutputStream byteArrayOutputStream50 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream50.write((int) '#');
        byte[] byteArray55 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream50.writeBytes(byteArray55);
        byteArrayOutputStream50.reset();
        byteArrayOutputStream50.write((int) (byte) 0);
        java.io.ByteArrayOutputStream byteArrayOutputStream61 = new java.io.ByteArrayOutputStream((int) (short) 100);
        byteArrayOutputStream61.write((int) '#');
        byte[] byteArray66 = new byte[] { (byte) 1, (byte) 10 };
        byteArrayOutputStream61.writeBytes(byteArray66);
        byteArrayOutputStream50.writeBytes(byteArray66);
        java.lang.Object obj69 = schema42.getObjectProp("enum", (java.lang.Object) byteArrayOutputStream50);
        java.lang.Integer int71 = schema42.getIndexNamed("array");
        org.apache.avro.Schema[] schemaArray72 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList73 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean74 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList73, schemaArray72);
        org.apache.avro.Schema schema75 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList73);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer76 = null;
        schema75.forEachProperty(strBiConsumer76);
        java.lang.Integer int79 = schema75.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type80 = schema75.getType();
        org.apache.avro.generic.GenericData genericData81 = null;
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter82 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema75, genericData81);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter83 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema75);
        org.apache.avro.Schema schema84 = null;
        schemaListGenericDatumWriter83.setSchema(schema84);
        org.apache.avro.generic.GenericData genericData86 = schemaListGenericDatumWriter83.getData();
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter87 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema42, genericData86);
        org.apache.avro.Schema[] schemaArray88 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList89 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean90 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList89, schemaArray88);
        org.apache.avro.Schema schema91 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList89);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer92 = null;
        schema91.forEachProperty(strBiConsumer92);
        java.lang.Integer int95 = schema91.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type96 = schema91.getType();
        java.lang.String str98 = schema91.getProp("enum");
        java.lang.String str100 = schema91.getProp("#");
        boolean boolean102 = schema91.propsContainsKey("enum");
        org.apache.avro.Schema[] schemaArray103 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList104 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean105 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList104, schemaArray103);
        org.apache.avro.Schema schema106 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList104);
        java.lang.String str107 = schema106.getDoc();
        schema91.addAllProps((org.apache.avro.JsonProperties) schema106);
        boolean boolean110 = schema106.propsContainsKey("[ ]");
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer111 = null;
        schema106.forEachProperty(strBiConsumer111);
        schema42.addAllProps((org.apache.avro.JsonProperties) schema106);
        schema3.addAllProps((org.apache.avro.JsonProperties) schema42);
        java.io.ByteArrayOutputStream byteArrayOutputStream116 = new java.io.ByteArrayOutputStream((int) '4');
        byteArrayOutputStream116.reset();
        byte[] byteArray118 = byteArrayOutputStream116.toByteArray();
        boolean boolean119 = schema3.equals((java.lang.Object) byteArrayOutputStream116);
        org.apache.avro.Schema.Parser parser120 = new org.apache.avro.Schema.Parser();
        boolean boolean121 = parser120.getValidateDefaults();
        org.apache.avro.Schema.Parser parser123 = parser120.setValidateDefaults(false);
        org.apache.avro.Schema[] schemaArray124 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList125 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean126 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList125, schemaArray124);
        org.apache.avro.Schema schema127 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList125);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer128 = null;
        schema127.forEachProperty(strBiConsumer128);
        java.lang.Integer int131 = schema127.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap132 = schema127.getObjectProps();
        org.apache.avro.Schema[] schemaArray133 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList134 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean135 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList134, schemaArray133);
        org.apache.avro.Schema schema136 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList134);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer137 = null;
        schema136.forEachProperty(strBiConsumer137);
        java.lang.Integer int140 = schema136.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type141 = schema136.getType();
        java.lang.String str143 = schema136.getProp("enum");
        boolean boolean145 = schema136.propsContainsKey("");
        boolean boolean146 = schema136.isUnion();
        java.lang.String str147 = schema136.toString();
        boolean boolean148 = schema127.equals((java.lang.Object) str147);
        java.util.List<org.apache.avro.Schema> schemaList149 = schema127.getTypes();
        org.apache.avro.Schema schema150 = org.apache.avro.Schema.createUnion(schemaList149);
        org.apache.avro.Schema.Parser parser151 = parser120.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList149);
        org.apache.avro.Schema[] schemaArray152 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList153 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean154 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList153, schemaArray152);
        org.apache.avro.Schema schema155 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList153);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer156 = null;
        schema155.forEachProperty(strBiConsumer156);
        java.lang.Integer int159 = schema155.getIndexNamed("hi!");
        java.util.Map<java.lang.String, java.lang.Object> strMap160 = schema155.getObjectProps();
        org.apache.avro.Schema[] schemaArray161 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList162 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean163 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList162, schemaArray161);
        org.apache.avro.Schema schema164 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList162);
        java.util.function.BiConsumer<java.lang.String, java.lang.Object> strBiConsumer165 = null;
        schema164.forEachProperty(strBiConsumer165);
        java.lang.Integer int168 = schema164.getIndexNamed("hi!");
        org.apache.avro.Schema.Type type169 = schema164.getType();
        java.lang.String str171 = schema164.getProp("enum");
        boolean boolean173 = schema164.propsContainsKey("");
        boolean boolean174 = schema164.isUnion();
        java.lang.String str175 = schema164.toString();
        boolean boolean176 = schema155.equals((java.lang.Object) str175);
        java.util.List<org.apache.avro.Schema> schemaList177 = schema155.getTypes();
        org.apache.avro.Schema schema178 = org.apache.avro.Schema.createUnion(schemaList177);
        org.apache.avro.Schema.Parser parser179 = parser151.addTypes((java.lang.Iterable<org.apache.avro.Schema>) schemaList177);
        java.lang.String str181 = schema3.toString((java.util.Collection<org.apache.avro.Schema>) schemaList177, true);
        org.junit.Assert.assertNotNull(schemaArray0);
        org.junit.Assert.assertArrayEquals(schemaArray0, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(schema3);
        org.junit.Assert.assertNull(int7);
        org.junit.Assert.assertTrue("'" + type8 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type8.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str10);
        org.junit.Assert.assertTrue("'" + order15 + "' != '" + org.apache.avro.Schema.Field.Order.IGNORE + "'", order15.equals(org.apache.avro.Schema.Field.Order.IGNORE));
        org.junit.Assert.assertEquals("'" + obj16 + "' != '" + 100L + "'", obj16, 100L);
        org.junit.Assert.assertNotNull(schemaArray17);
        org.junit.Assert.assertArrayEquals(schemaArray17, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNotNull(schema20);
        org.junit.Assert.assertNull(int24);
        org.junit.Assert.assertTrue("'" + type25 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type25.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(genericData28);
        org.junit.Assert.assertNull(genericData29);
        org.junit.Assert.assertNotNull(schemaArray30);
        org.junit.Assert.assertArrayEquals(schemaArray30, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertNotNull(schema33);
        org.junit.Assert.assertNull(int37);
        org.junit.Assert.assertTrue("'" + type38 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type38.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(schemaArray39);
        org.junit.Assert.assertArrayEquals(schemaArray39, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
        org.junit.Assert.assertNotNull(schema42);
        org.junit.Assert.assertNull(str43);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
        org.junit.Assert.assertNull(logicalType46);
        org.junit.Assert.assertEquals(byteArrayOutputStream50.toString(), "\000\001\n");
        org.junit.Assert.assertNotNull(byteArray55);
        org.junit.Assert.assertArrayEquals(byteArray55, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertEquals(byteArrayOutputStream61.toString(), "#\001\n");
        org.junit.Assert.assertNotNull(byteArray66);
        org.junit.Assert.assertArrayEquals(byteArray66, new byte[] { (byte) 1, (byte) 10 });
        org.junit.Assert.assertNotNull(obj69);
        org.junit.Assert.assertEquals(obj69.toString(), "\000\001\n");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj69), "\000\001\n");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj69), "\000\001\n");
        org.junit.Assert.assertNull(int71);
        org.junit.Assert.assertNotNull(schemaArray72);
        org.junit.Assert.assertArrayEquals(schemaArray72, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean74 + "' != '" + false + "'", boolean74 == false);
        org.junit.Assert.assertNotNull(schema75);
        org.junit.Assert.assertNull(int79);
        org.junit.Assert.assertTrue("'" + type80 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type80.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNotNull(genericData86);
        org.junit.Assert.assertNotNull(schemaArray88);
        org.junit.Assert.assertArrayEquals(schemaArray88, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean90 + "' != '" + false + "'", boolean90 == false);
        org.junit.Assert.assertNotNull(schema91);
        org.junit.Assert.assertNull(int95);
        org.junit.Assert.assertTrue("'" + type96 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type96.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str98);
        org.junit.Assert.assertNull(str100);
        org.junit.Assert.assertTrue("'" + boolean102 + "' != '" + false + "'", boolean102 == false);
        org.junit.Assert.assertNotNull(schemaArray103);
        org.junit.Assert.assertArrayEquals(schemaArray103, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean105 + "' != '" + false + "'", boolean105 == false);
        org.junit.Assert.assertNotNull(schema106);
        org.junit.Assert.assertNull(str107);
        org.junit.Assert.assertTrue("'" + boolean110 + "' != '" + false + "'", boolean110 == false);
        org.junit.Assert.assertEquals(byteArrayOutputStream116.toString(), "");
        org.junit.Assert.assertNotNull(byteArray118);
        org.junit.Assert.assertArrayEquals(byteArray118, new byte[] {});
        org.junit.Assert.assertTrue("'" + boolean119 + "' != '" + false + "'", boolean119 == false);
        org.junit.Assert.assertTrue("'" + boolean121 + "' != '" + true + "'", boolean121 == true);
        org.junit.Assert.assertNotNull(parser123);
        org.junit.Assert.assertNotNull(schemaArray124);
        org.junit.Assert.assertArrayEquals(schemaArray124, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean126 + "' != '" + false + "'", boolean126 == false);
        org.junit.Assert.assertNotNull(schema127);
        org.junit.Assert.assertNull(int131);
        org.junit.Assert.assertNotNull(strMap132);
        org.junit.Assert.assertNotNull(schemaArray133);
        org.junit.Assert.assertArrayEquals(schemaArray133, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean135 + "' != '" + false + "'", boolean135 == false);
        org.junit.Assert.assertNotNull(schema136);
        org.junit.Assert.assertNull(int140);
        org.junit.Assert.assertTrue("'" + type141 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type141.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str143);
        org.junit.Assert.assertTrue("'" + boolean145 + "' != '" + false + "'", boolean145 == false);
        org.junit.Assert.assertTrue("'" + boolean146 + "' != '" + true + "'", boolean146 == true);
        org.junit.Assert.assertEquals("'" + str147 + "' != '" + "[]" + "'", str147, "[]");
        org.junit.Assert.assertTrue("'" + boolean148 + "' != '" + false + "'", boolean148 == false);
        org.junit.Assert.assertNotNull(schemaList149);
        org.junit.Assert.assertNotNull(schema150);
        org.junit.Assert.assertNotNull(parser151);
        org.junit.Assert.assertNotNull(schemaArray152);
        org.junit.Assert.assertArrayEquals(schemaArray152, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean154 + "' != '" + false + "'", boolean154 == false);
        org.junit.Assert.assertNotNull(schema155);
        org.junit.Assert.assertNull(int159);
        org.junit.Assert.assertNotNull(strMap160);
        org.junit.Assert.assertNotNull(schemaArray161);
        org.junit.Assert.assertArrayEquals(schemaArray161, new org.apache.avro.Schema[] {});
        org.junit.Assert.assertTrue("'" + boolean163 + "' != '" + false + "'", boolean163 == false);
        org.junit.Assert.assertNotNull(schema164);
        org.junit.Assert.assertNull(int168);
        org.junit.Assert.assertTrue("'" + type169 + "' != '" + org.apache.avro.Schema.Type.UNION + "'", type169.equals(org.apache.avro.Schema.Type.UNION));
        org.junit.Assert.assertNull(str171);
        org.junit.Assert.assertTrue("'" + boolean173 + "' != '" + false + "'", boolean173 == false);
        org.junit.Assert.assertTrue("'" + boolean174 + "' != '" + true + "'", boolean174 == true);
        org.junit.Assert.assertEquals("'" + str175 + "' != '" + "[]" + "'", str175, "[]");
        org.junit.Assert.assertTrue("'" + boolean176 + "' != '" + false + "'", boolean176 == false);
        org.junit.Assert.assertNotNull(schemaList177);
        org.junit.Assert.assertNotNull(schema178);
        org.junit.Assert.assertNotNull(parser179);
        org.junit.Assert.assertEquals("'" + str181 + "' != '" + "[ ]" + "'", str181, "[ ]");
    }
}
