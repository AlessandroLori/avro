package org.apache.avro.randoop.schemac2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SchemaRandoopErrorTest0 {

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
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test001");
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList5 = new java.util.ArrayList<java.lang.String>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList5, strArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createEnum("", "hi!", "", (java.util.List<java.lang.String>) strList5);
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test002");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray4 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder1.aliases(strArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler6 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test003");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray9 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder10 = schemaRecordBuilder6.aliases(strArray9);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = schemaEnumBuilder4.symbols(strArray9);
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test004");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler2 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test005");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.booleanType();
        java.lang.String str8 = schema6.toString(false);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("logicalType", schema6, "string");
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test006");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema5 = schemaFixedBuilder3.size((int) '4');
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test007");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "", "hi!", true);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test008");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("logicalType", "hi!", "", false);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test009");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "logicalType", "string", false);
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test010");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = org.apache.avro.Schema.createMap(schema5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field7 = new org.apache.avro.Schema.Field("string", schema6);
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test011");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("logicalType", "hi!", "decimal", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test012");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.booleanType();
        java.lang.String str8 = schema6.toString(false);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createMap(schema6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field11 = new org.apache.avro.Schema.Field("map", schema6, "hi!");
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test013");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "logicalType", "string", true);
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test014");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray2 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder3 = schemaRecordBuilder1.aliases(strArray2);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler4 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test015");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray7 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder6.aliases(strArray7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = schemaEnumBuilder4.symbols(strArray7);
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test016");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "map", true);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test017");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("logicalType", "logicalType", "string", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test018");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("map", "{\"type\":\"map\",\"values\":\"boolean\"}", "decimal", true);
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test019");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.booleanType();
        java.lang.String str8 = schema6.toString(false);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("map", schema6, "map");
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test020");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"boolean\"}", "", "logicalType", true);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test021");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "decimal", "{\"type\":\"map\",\"values\":\"boolean\"}", true);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test022");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("decimal", "int", "hi!", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test023");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler8 = schemaRecordBuilder5.fields();
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test024");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.floatType();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder6 = schemaTypeBuilder1.nullBuilder();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder7 = schemaTypeBuilder1.floatBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder9 = schemaTypeBuilder1.fixed("logicalType");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = schemaFixedBuilder9.size((int) (byte) 100);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test025");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler10 = schemaRecordBuilder9.fields();
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test026");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder2.stringType();
        schema4.addProp("", "");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", schema4, "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test027");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "map", (int) (byte) -1);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test028");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", true);
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test029");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder5.prop("float", "float");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler9 = schemaRecordBuilder8.fields();
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test030");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaStringBldr4.endString();
        org.apache.avro.Schema schema6 = schemaStringBldr4.endString();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder8 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder9 = schemaMapBuilder8.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder10 = schemaTypeBuilder9.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr11 = schemaTypeBuilder9.stringBuilder();
        org.apache.avro.Schema schema12 = schemaTypeBuilder9.stringType();
        org.apache.avro.LogicalType logicalType13 = org.apache.avro.LogicalTypes.fromSchema(schema12);
        schema12.addProp("{\"type\":\"map\",\"values\":\"boolean\"}", "");
        java.lang.String str17 = schema12.getFullName();
        schema6.addProp("logicalType", (java.lang.Object) str17);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createArray(schema6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field21 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"null\"}", schema6, "hi!");
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test031");
        java.lang.String[] strArray10 = new java.lang.String[] { "int", "logicalType", "int", "{\"type\":\"map\",\"values\":\"boolean\"}", "logicalType", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"boolean\"}" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema13 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"null\"}", "", "{\"type\":\"map\",\"values\":\"null\"}", (java.util.List<java.lang.String>) strList11);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test032");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("decimal", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"string\"}", (int) (byte) -1);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test033");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("map", "decimal", "", (int) (short) -1);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test034");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder6 = schemaEnumBuilder4.defaultSymbol("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaTypeBuilder8.record("logicalType");
        java.lang.String[] strArray13 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder12.aliases(strArray13);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = schemaRecordBuilder12.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder19 = schemaRecordBuilder12.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder21 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray22 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder23 = schemaRecordBuilder21.aliases(strArray22);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder24 = schemaRecordBuilder12.aliases(strArray22);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema25 = schemaEnumBuilder6.symbols(strArray22);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test035");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"boolean\"}", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", (int) (byte) -1);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test036");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder2.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder7 = schemaTypeBuilder2.doubleBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder8 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema9 = schemaLongBuilder8.endLong();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("int", schema9);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test037");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder5 = schemaTypeBuilder2.floatBuilder();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.doubleType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field7 = new org.apache.avro.Schema.Field("", schema6);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test038");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray2 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder3 = schemaRecordBuilder1.aliases(strArray2);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder1.doc("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder1.namespace("");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler8 = schemaRecordBuilder7.fields();
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test039");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("decimal", "string", "union", 100);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test040");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.Schema schema3 = schemaTypeBuilder1.stringType();
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.stringType();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder5 = schemaTypeBuilder1.floatBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder7 = schemaTypeBuilder1.fixed("union");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = schemaFixedBuilder7.size((int) (short) 100);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test041");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaRecordBuilder5.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder15 = schemaRecordBuilder5.prop("", "float");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler16 = schemaRecordBuilder15.fields();
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test042");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("logicalType", "map", "string", false);
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test043");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "", (int) (byte) 100);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test044");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "map", "{\"type\":\"map\",\"values\":\"boolean\"}", (-1));
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test045");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "logicalType", true);
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test046");
        java.lang.String[] strArray8 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "hi!", "{\"type\":\"map\",\"values\":\"null\"}", "logicalType", "{\"type\":\"map\",\"values\":\"boolean\"}" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", (java.util.List<java.lang.String>) strList9, "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test047");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord("union", "logicalType", "union", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test048");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaRecordBuilder9.prop("hi!", "int");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler13 = schemaRecordBuilder9.fields();
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test049");
        java.lang.String[] strArray5 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "map" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createEnum("int", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "", (java.util.List<java.lang.String>) strList6, "int");
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test050");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("float", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "map", (int) (byte) 100);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test051");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder5 = schemaTypeBuilder2.floatBuilder();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder6 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder7 = schemaMapBuilder6.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder8 = schemaTypeBuilder7.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder10 = schemaTypeBuilder7.enumeration("");
        org.apache.avro.Schema schema11 = schemaTypeBuilder7.floatType();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder12 = schemaTypeBuilder7.nullBuilder();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder13 = schemaTypeBuilder7.floatBuilder();
        org.apache.avro.Schema schema14 = schemaFloatBuilder13.endFloat();
        java.lang.String str16 = schema14.toString(true);
        org.apache.avro.Schema schema17 = schemaTypeBuilder2.type(schema14);
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder18 = schemaTypeBuilder2.floatBuilder();
        org.apache.avro.Schema schema19 = schemaTypeBuilder2.bytesType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field21 = new org.apache.avro.Schema.Field("", schema19, "logicalType");
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test052");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.floatType();
        java.lang.Object obj7 = new java.lang.Object();
        boolean boolean8 = schema6.equals(obj7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("array", schema6, "{\"type\":\"map\",\"values\":\"null\"}");
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test053");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", (int) (short) 10);
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test054");
        java.lang.String[] strArray7 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"float\"}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createEnum("float", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "hi!", (java.util.List<java.lang.String>) strList8);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test055");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder5.doc("float");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler12 = schemaRecordBuilder11.fields();
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test056");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler8 = schemaRecordBuilder7.fields();
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test057");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test058");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) 'a');
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test059");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test060");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder2.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder7 = schemaTypeBuilder2.doubleBuilder();
        org.apache.avro.Schema schema8 = schemaDoubleBuilder7.endDouble();
        org.apache.avro.Schema schema9 = schemaDoubleBuilder7.endDouble();
        org.apache.avro.Schema schema10 = schemaDoubleBuilder7.endDouble();
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createArray(schema10);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field12 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"string\"}", schema11);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test061");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("hi!");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (byte) 10);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test062");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (short) 1);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test063");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("float", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":\"float\"}", false);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test064");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder2.fixed("hi!");
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.nullType();
        org.apache.avro.LogicalType logicalType6 = org.apache.avro.LogicalTypes.fromSchema(schema5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field8 = new org.apache.avro.Schema.Field("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", schema5, "big-decimal");
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test065");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("", "", "big-decimal", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test066");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.Schema schema3 = schemaTypeBuilder2.doubleType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr7 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema8 = schemaTypeBuilder5.stringType();
        org.apache.avro.LogicalType logicalType9 = org.apache.avro.LogicalTypes.fromSchema(schema8);
        java.lang.String str11 = schema8.getProp("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.Schema schema12 = schemaTypeBuilder2.type(schema8);
        boolean boolean14 = schema12.propsContainsKey("");
        java.lang.String str15 = schema12.getDoc();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder16 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder17 = schemaMapBuilder16.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder18 = schemaTypeBuilder17.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr19 = schemaTypeBuilder17.stringBuilder();
        org.apache.avro.Schema schema20 = schemaTypeBuilder17.bytesType();
        schema12.putAll((org.apache.avro.JsonProperties) schema20);
        java.lang.Object obj23 = schema12.getObjectProp("union");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder25 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder26 = schemaMapBuilder25.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder27 = schemaTypeBuilder26.bytesBuilder();
        org.apache.avro.Schema schema28 = schemaTypeBuilder26.stringType();
        org.apache.avro.Schema schema29 = schemaTypeBuilder26.stringType();
        org.apache.avro.Schema schema30 = org.apache.avro.Schema.createArray(schema29);
        java.lang.Object obj32 = schema30.getObjectProp("hi!");
        java.lang.Object obj35 = schema30.getObjectProp("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", (java.lang.Object) 10.0f);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field36 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", schema12, "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", (java.lang.Object) "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test067");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray4 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder1.aliases(strArray4);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder1.doc("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler8 = schemaRecordBuilder7.fields();
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test068");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", (-1));
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test069");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.floatType();
        boolean boolean7 = schema6.isNullable();
        boolean boolean9 = schema6.equals((java.lang.Object) (byte) -1);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("union", schema6);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test070");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (short) 10);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test071");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaRecordBuilder9.prop("hi!", "int");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler13 = schemaRecordBuilder12.fields();
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test072");
        org.apache.avro.Schema.Field[] fieldArray0 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList1 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList1, fieldArray0);
        org.apache.avro.Schema schema3 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList1);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        schema3.addAlias("hi!", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}");
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test073");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "hi!", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", (int) 'a');
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test074");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "int", "{\"type\":\"map\",\"values\":\"string\"}", true);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test075");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder1 = org.apache.avro.SchemaBuilder.enumeration("decimal");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder3 = schemaEnumBuilder1.defaultSymbol("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray8 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.aliases(strArray8);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder10 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder11 = schemaMapBuilder10.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder12 = schemaTypeBuilder11.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr13 = schemaTypeBuilder11.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder15 = schemaTypeBuilder11.record("logicalType");
        java.lang.String[] strArray16 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder17 = schemaRecordBuilder15.aliases(strArray16);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder18 = schemaRecordBuilder9.aliases(strArray16);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema19 = schemaEnumBuilder3.symbols(strArray16);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test076");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray12 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder13 = schemaRecordBuilder9.aliases(strArray12);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder7.aliases(strArray12);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = schemaRecordBuilder14.namespace("map");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler17 = schemaRecordBuilder14.fields();
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test077");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.nullType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field7 = new org.apache.avro.Schema.Field("string", schema6);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test078");
        java.lang.String[] strArray19 = new java.lang.String[] { "union", "{\"type\":\"map\",\"values\":\"null\"}", "float", "int", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "record", "float", "record", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"long\"}", "int", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "logicalType", "union", "" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema23 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", (java.util.List<java.lang.String>) strList20, "int");
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test079");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (byte) -1);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test080");
        java.lang.String[] strArray32 = new java.lang.String[] { "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"float\"}", "hi!", "{\"type\":\"map\",\"values\":\"string\"}", "", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "union", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "float", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "array", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"record\",\"fields\":[]}", "map", "logicalType", "{\"type\":\"map\",\"values\":\"null\"}" };
        java.util.ArrayList<java.lang.String> strList33 = new java.util.ArrayList<java.lang.String>();
        boolean boolean34 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList33, strArray32);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema35 = org.apache.avro.Schema.createEnum("hi!", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "", (java.util.List<java.lang.String>) strList33);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test081");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord("", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test082");
        java.lang.String[] strArray27 = new java.lang.String[] { "big-decimal", "array", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "boolean", "big-decimal", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "boolean", "{\"type\":\"map\",\"values\":\"long\"}", "array", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "logicalType" };
        java.util.ArrayList<java.lang.String> strList28 = new java.util.ArrayList<java.lang.String>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList28, strArray27);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createEnum("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "big-decimal", (java.util.List<java.lang.String>) strList28, "{\"type\":\"record\",\"fields\":[]}");
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test083");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.Schema schema3 = schemaTypeBuilder2.doubleType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr7 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema8 = schemaTypeBuilder5.stringType();
        org.apache.avro.LogicalType logicalType9 = org.apache.avro.LogicalTypes.fromSchema(schema8);
        java.lang.String str11 = schema8.getProp("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.Schema schema12 = schemaTypeBuilder2.type(schema8);
        boolean boolean14 = schema12.propsContainsKey("");
        boolean boolean15 = schema12.hasProps();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field17 = new org.apache.avro.Schema.Field("record", schema12, "");
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test084");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema6 = schemaBytesBuilder3.endBytes();
        org.apache.avro.LogicalType logicalType7 = org.apache.avro.LogicalTypes.fromSchema(schema6);
        boolean boolean9 = schema6.propsContainsKey("{\"type\":\"map\",\"values\":\"null\"}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder10 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder11 = schemaMapBuilder10.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder12 = schemaTypeBuilder11.bytesBuilder();
        org.apache.avro.Schema schema13 = schemaBytesBuilder12.endBytes();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder14 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder15 = schemaMapBuilder14.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder16 = schemaTypeBuilder15.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr17 = schemaTypeBuilder15.stringBuilder();
        org.apache.avro.Schema schema18 = schemaStringBldr17.endString();
        schema13.addAllProps((org.apache.avro.JsonProperties) schema18);
        schema6.addAllProps((org.apache.avro.JsonProperties) schema13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field22 = new org.apache.avro.Schema.Field("logicalType", schema13, "boolean");
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test085");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\"type\":\"map\",\"values\":\"float\"}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size(0);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test086");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test087");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"array\",\"items\":\"long\"}", true);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test088");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("union", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", 10);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test089");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr5 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema6 = schemaStringBldr5.endString();
        java.lang.Object obj8 = schema6.getObjectProp("duration");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("union", schema6);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test090");
        java.lang.String[] strArray15 = new java.lang.String[] { "big-decimal", "decimal", "union", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "int", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "boolean", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}" };
        java.util.ArrayList<java.lang.String> strList16 = new java.util.ArrayList<java.lang.String>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList16, strArray15);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createEnum("float", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "union", (java.util.List<java.lang.String>) strList16);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test091");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaStringBldr4.endString();
        org.apache.avro.LogicalTypes.Decimal decimal8 = org.apache.avro.LogicalTypes.decimal((int) '#');
        org.apache.avro.LogicalTypes.Decimal decimal10 = org.apache.avro.LogicalTypes.decimal(10);
        int int11 = decimal10.getPrecision();
        boolean boolean12 = decimal8.equals((java.lang.Object) int11);
        java.lang.Object obj13 = schema5.getObjectProp("logicalType", (java.lang.Object) int11);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field15 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", schema5, "union");
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test092");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test093");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord("duration", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "record", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test094");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (byte) -1);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test095");
        java.lang.String[] strArray34 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "", "float", "big-decimal", "array", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "", "array", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "hi!", "{\"type\":\"map\",\"values\":\"long\"}", "big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "record" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "", (java.util.List<java.lang.String>) strList35, "{\"type\":\"record\",\"fields\":[]}");
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test096");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("logicalType", "", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", (int) (byte) 0);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test097");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        java.lang.String str6 = schema5.getName();
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createMap(schema5);
        java.lang.String str9 = schema7.getProp("");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", schema7);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test098");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("decimal", "{\"type\":\"map\",\"values\":\"null\"}", "", true);
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test099");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.intType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.Schema schema11 = schemaTypeBuilder8.stringType();
        org.apache.avro.LogicalType logicalType12 = org.apache.avro.LogicalTypes.fromSchema(schema11);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder13 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder14 = schemaMapBuilder13.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder15 = schemaTypeBuilder14.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder17 = schemaTypeBuilder14.enumeration("");
        org.apache.avro.Schema schema18 = schemaTypeBuilder14.floatType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder19 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder20 = schemaMapBuilder19.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder21 = schemaTypeBuilder20.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr22 = schemaTypeBuilder20.stringBuilder();
        org.apache.avro.Schema schema23 = schemaStringBldr22.endString();
        org.apache.avro.Schema schema24 = schemaStringBldr22.endString();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder25 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder26 = schemaMapBuilder25.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder27 = schemaTypeBuilder26.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr28 = schemaTypeBuilder26.stringBuilder();
        org.apache.avro.Schema schema29 = schemaTypeBuilder26.bytesType();
        org.apache.avro.Schema schema30 = schemaTypeBuilder26.booleanType();
        org.apache.avro.Schema[] schemaArray31 = new org.apache.avro.Schema[] { schema24, schema30 };
        java.util.ArrayList<org.apache.avro.Schema> schemaList32 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean33 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList32, schemaArray31);
        java.lang.String str35 = schema18.toString((java.util.Collection<org.apache.avro.Schema>) schemaList32, true);
        java.lang.String str37 = schema11.toString((java.util.Collection<org.apache.avro.Schema>) schemaList32, false);
        java.lang.String str39 = schema6.toString((java.util.Collection<org.apache.avro.Schema>) schemaList32, true);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field41 = new org.apache.avro.Schema.Field("array", schema6, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}");
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test100");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        java.lang.String str6 = schema5.getName();
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createMap(schema5);
        java.lang.String str8 = schema7.getName();
        boolean boolean9 = schema7.isUnion();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field11 = new org.apache.avro.Schema.Field("big-decimal", schema7, "");
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test101");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaStringBldr4.endString();
        org.apache.avro.Schema schema6 = schemaStringBldr4.endString();
        java.util.Map<java.lang.String, java.lang.Object> strMap7 = schema6.getObjectProps();
        org.apache.avro.LogicalType logicalType8 = org.apache.avro.LogicalTypes.fromSchemaIgnoreInvalid(schema6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("{\"type\":\"record\",\"fields\":[]}", schema6, "logicalType");
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test102");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder5 = schemaTypeBuilder2.nullBuilder();
        org.apache.avro.Schema schema6 = schemaNullBuilder5.endNull();
        org.apache.avro.Schema schema7 = schemaNullBuilder5.endNull();
        boolean boolean8 = schema7.isUnion();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("array", schema7, "union");
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test103");
        java.lang.String[] strArray37 = new java.lang.String[] { "hi!", "float", "logicalType", "float", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "logicalType", "boolean", "map", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"long\"}", "int", "boolean", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "string", "logicalType", "int", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "duration", "{\"type\":\"array\",\"items\":\"long\"}", "hi!", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "decimal", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"array\",\"items\":\"long\"}" };
        java.util.ArrayList<java.lang.String> strList38 = new java.util.ArrayList<java.lang.String>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList38, strArray37);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema40 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "decimal", (java.util.List<java.lang.String>) strList38);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test104");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder6 = schemaEnumBuilder4.defaultSymbol("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaTypeBuilder8.record("logicalType");
        java.lang.String[] strArray13 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder12.aliases(strArray13);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray19 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder20 = schemaRecordBuilder16.aliases(strArray19);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder21 = schemaRecordBuilder14.aliases(strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = schemaEnumBuilder4.symbols(strArray19);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test105");
        java.lang.String[] strArray19 = new java.lang.String[] { "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":\"long\"}", "string", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"null\"}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "logicalType", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"long\"}", (java.util.List<java.lang.String>) strList20);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test106");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("array", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "big-decimal", (int) (short) 100);
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test107");
        java.lang.String[] strArray34 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "array", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "int", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"record\",\"fields\":[]}", "map", "string", "logicalType", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"record\",\"fields\":[]}", "record", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "map", "duration", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createEnum("", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"string\"}", (java.util.List<java.lang.String>) strList35, "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test108");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder2.stringType();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        org.apache.avro.SchemaBuilder.ArrayBuilder<org.apache.avro.Schema> schemaArrayBuilder6 = schemaTypeBuilder2.array();
        org.apache.avro.Schema schema7 = schemaTypeBuilder2.stringType();
        org.apache.avro.LogicalType logicalType8 = org.apache.avro.LogicalTypes.fromSchemaIgnoreInvalid(schema7);
        org.apache.avro.LogicalTypes.Decimal decimal11 = org.apache.avro.LogicalTypes.decimal(10);
        int int12 = decimal11.getPrecision();
        boolean boolean14 = decimal11.equals((java.lang.Object) 0.0d);
        int int15 = decimal11.getPrecision();
        org.apache.avro.Schema.Field.Order order16 = org.apache.avro.Schema.Field.Order.IGNORE;
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field17 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", schema7, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", (java.lang.Object) int15, order16);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test109");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder2.fixed("hi!");
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.longType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.nullType();
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.SchemaBuilder.UnionAccumulator<org.apache.avro.Schema>> schemaUnionAccumulatorBaseTypeBuilder7 = schemaTypeBuilder2.unionOf();
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.SchemaBuilder.UnionAccumulator<org.apache.avro.Schema>> schemaUnionAccumulatorBaseTypeBuilder8 = schemaTypeBuilder2.unionOf();
        org.apache.avro.Schema schema9 = schemaTypeBuilder2.stringType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field11 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", schema9, "");
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test110");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema6 = schemaBytesBuilder3.endBytes();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field8 = new org.apache.avro.Schema.Field("", schema6, "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}");
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test111");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "map", "int", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test112");
        java.lang.String[] strArray30 = new java.lang.String[] { "boolean", "{\"type\":\"array\",\"items\":\"long\"}", "logicalType", "big-decimal", "record", "float", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"boolean\"}", "big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "hi!", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "boolean", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "map", "hi!", "union", "{\"type\":\"map\",\"values\":\"float\"}", "decimal", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}" };
        java.util.ArrayList<java.lang.String> strList31 = new java.util.ArrayList<java.lang.String>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList31, strArray30);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createEnum("big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "duration", (java.util.List<java.lang.String>) strList31);
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test113");
        java.lang.String[] strArray10 = new java.lang.String[] { "boolean", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", "logicalType", "decimal", "duration" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createEnum("record", "float", "", (java.util.List<java.lang.String>) strList11, "duration");
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test114");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) '4');
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test115");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema6 = schemaBytesBuilder3.endBytes();
        org.apache.avro.LogicalType logicalType7 = org.apache.avro.LogicalTypes.fromSchema(schema6);
        boolean boolean9 = schema6.propsContainsKey("{\"type\":\"map\",\"values\":\"null\"}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder10 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder11 = schemaMapBuilder10.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder12 = schemaTypeBuilder11.bytesBuilder();
        org.apache.avro.Schema schema13 = schemaBytesBuilder12.endBytes();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder14 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder15 = schemaMapBuilder14.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder16 = schemaTypeBuilder15.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr17 = schemaTypeBuilder15.stringBuilder();
        org.apache.avro.Schema schema18 = schemaStringBldr17.endString();
        schema13.addAllProps((org.apache.avro.JsonProperties) schema18);
        schema6.addAllProps((org.apache.avro.JsonProperties) schema13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field21 = new org.apache.avro.Schema.Field("{\"type\":\"record\",\"fields\":[]}", schema13);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test116");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder2.stringType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field6 = new org.apache.avro.Schema.Field("logicalType", schema4, "{\"type\":\"map\",\"values\":\"float\"}");
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test117");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.Schema schema3 = schemaTypeBuilder1.stringType();
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.stringType();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder5 = schemaTypeBuilder1.floatBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder7 = schemaTypeBuilder1.fixed("union");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = schemaFixedBuilder7.size((int) '#');
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test118");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"null\"}", "big-decimal", false);
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test119");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder5.prop("float", "float");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder10 = schemaRecordBuilder8.namespace("int");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler11 = schemaRecordBuilder8.fields();
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test120");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord("{\"type\":\"array\",\"items\":\"long\"}", "int", "{\"type\":\"array\",\"items\":\"long\"}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test121");
        java.lang.String[] strArray34 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "big-decimal", "big-decimal", "int", "float", "", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":\"null\"}", "array", "string", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "union", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "", "duration", "record", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"array\",\"items\":\"long\"}", "record", "logicalType", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "array" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema37 = org.apache.avro.Schema.createEnum("logicalType", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"long\"}", (java.util.List<java.lang.String>) strList35);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test122");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.floatType();
        boolean boolean7 = schema6.isNullable();
        org.apache.avro.Schema.Type type8 = schema6.getType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder9 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder10 = schemaMapBuilder9.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder12 = schemaTypeBuilder10.fixed("hi!");
        org.apache.avro.Schema schema13 = schemaTypeBuilder10.longType();
        org.apache.avro.Schema schema14 = schemaTypeBuilder10.longType();
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createMap(schema14);
        schema6.putAll((org.apache.avro.JsonProperties) schema14);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field18 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", schema6, "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}");
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test123");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder2.fixed("hi!");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder5 = schemaTypeBuilder2.doubleBuilder();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.longType();
        boolean boolean7 = schema6.isUnion();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field8 = new org.apache.avro.Schema.Field("", schema6);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test124");
        java.lang.String[] strArray29 = new java.lang.String[] { "duration", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "duration", "{\"type\":\"map\",\"values\":\"null\"}", "big-decimal", "float", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"long\"}", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "string", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "map", "record", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "hi!" };
        java.util.ArrayList<java.lang.String> strList30 = new java.util.ArrayList<java.lang.String>();
        boolean boolean31 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList30, strArray29);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", (java.util.List<java.lang.String>) strList30, "{\"type\":\"array\",\"items\":\"long\"}");
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test125");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray2 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder3 = schemaRecordBuilder1.aliases(strArray2);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder1.doc("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder1.namespace("{\"type\":\"map\",\"values\":\"string\"}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler8 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test126");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema5 = schemaFixedBuilder3.size((int) (short) 1);
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test127");
        java.lang.String[] strArray19 = new java.lang.String[] { "map", "double", "big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "decimal" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createEnum("string", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "float", (java.util.List<java.lang.String>) strList20);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test128");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr5 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.Schema> schemaBaseTypeBuilder6 = schemaTypeBuilder2.nullable();
        org.apache.avro.SchemaBuilder.IntBuilder<org.apache.avro.Schema> schemaIntBuilder7 = schemaTypeBuilder2.intBuilder();
        org.apache.avro.Schema schema8 = schemaIntBuilder7.endInt();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("union", schema8);
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test129");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray12 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder13 = schemaRecordBuilder9.aliases(strArray12);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder7.aliases(strArray12);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder17 = schemaRecordBuilder14.prop("union", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler18 = schemaRecordBuilder14.fields();
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test130");
        java.lang.String[] strArray17 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "double", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "string" };
        java.util.ArrayList<java.lang.String> strList18 = new java.util.ArrayList<java.lang.String>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList18, strArray17);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema21 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "logicalType", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", (java.util.List<java.lang.String>) strList18, "float");
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test131");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema5 = schemaFixedBuilder3.size((int) ' ');
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test132");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.intType();
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder7 = schemaTypeBuilder2.booleanBuilder();
        org.apache.avro.Schema schema8 = schemaBooleanBuilder7.endBoolean();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("hi!", schema8);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test133");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", (int) ' ');
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test134");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("int", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", true);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test135");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "boolean", (int) ' ');
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test136");
        java.lang.String[] strArray30 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "double", "double", "string", "map", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "double", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "boolean", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"float\"}", "int", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"string\"}" };
        java.util.ArrayList<java.lang.String> strList31 = new java.util.ArrayList<java.lang.String>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList31, strArray30);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createEnum("big-decimal", "big-decimal", "record", (java.util.List<java.lang.String>) strList31);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test137");
        java.lang.String[] strArray27 = new java.lang.String[] { "array", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "float", "big-decimal", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "", "string", "{\"type\":\"map\",\"values\":\"float\"}", "duration", "double", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "hi!", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "float" };
        java.util.ArrayList<java.lang.String> strList28 = new java.util.ArrayList<java.lang.String>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList28, strArray27);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema30 = org.apache.avro.Schema.createEnum("{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", (java.util.List<java.lang.String>) strList28);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test138");
        org.apache.avro.LogicalTypes.Decimal decimal3 = org.apache.avro.LogicalTypes.decimal((int) (byte) 10, (int) (short) 1);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr7 = schemaTypeBuilder5.stringBuilder();
        boolean boolean8 = decimal3.equals((java.lang.Object) schemaStringBldr7);
        org.apache.avro.Schema schema9 = schemaStringBldr7.endString();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field11 = new org.apache.avro.Schema.Field("duration", schema9, "");
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test139");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", (int) '4');
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test140");
        java.lang.String[] strArray18 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "", "", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "duration", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}" };
        java.util.ArrayList<java.lang.String> strList19 = new java.util.ArrayList<java.lang.String>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList19, strArray18);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"float\"}", "record", "array", (java.util.List<java.lang.String>) strList19, "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}");
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test141");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaStringBldr4.endString();
        org.apache.avro.LogicalTypes.Decimal decimal8 = org.apache.avro.LogicalTypes.decimal((int) '#');
        org.apache.avro.LogicalTypes.Decimal decimal10 = org.apache.avro.LogicalTypes.decimal(10);
        int int11 = decimal10.getPrecision();
        boolean boolean12 = decimal8.equals((java.lang.Object) int11);
        java.lang.Object obj13 = schema5.getObjectProp("logicalType", (java.lang.Object) int11);
        org.apache.avro.LogicalType logicalType14 = schema5.getLogicalType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field16 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", schema5, "{\"type\":\"map\",\"values\":\"double\"}");
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test142");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder5 = schemaTypeBuilder2.nullBuilder();
        org.apache.avro.Schema schema6 = schemaNullBuilder5.endNull();
        org.apache.avro.LogicalType logicalType7 = org.apache.avro.LogicalTypes.fromSchemaIgnoreInvalid(schema6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", schema6, "boolean");
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test143");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray15 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = schemaRecordBuilder12.aliases(strArray15);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder18 = schemaRecordBuilder12.doc("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder19 = schemaRecordBuilder5.prop("logicalType", (java.lang.Object) "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder22 = schemaRecordBuilder19.prop("int", "duration");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder25 = schemaRecordBuilder22.prop("union", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler26 = schemaRecordBuilder22.fields();
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test144");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "string", "{\"type\":\"record\",\"fields\":[]}", false);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test145");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "duration", "hi!", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test146");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test147");
        java.lang.String[] strArray17 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "int", "float", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"double\"}" };
        java.util.ArrayList<java.lang.String> strList18 = new java.util.ArrayList<java.lang.String>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList18, strArray17);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "map", "array", (java.util.List<java.lang.String>) strList18);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test148");
        java.lang.String[] strArray16 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"record\",\"fields\":[]}", "float", "union", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "map", "int", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":\"null\"}", "", (java.util.List<java.lang.String>) strList17, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}");
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test149");
        java.lang.String[] strArray34 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "union", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "", "string", "array", "record", "float", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "boolean", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "array", "hi!", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "string", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "float" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema37 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "record", (java.util.List<java.lang.String>) strList35);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test150");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.bytesType();
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.booleanType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder6 = schemaTypeBuilder1.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder7 = schemaMapBuilder6.values();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder9 = schemaTypeBuilder7.enumeration("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray14 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder15 = schemaRecordBuilder11.aliases(strArray14);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema16 = schemaEnumBuilder9.symbols(strArray14);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test151");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":\"bytes\"}", "", false);
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test152");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", false);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test153");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.FloatBuilder<org.apache.avro.Schema> schemaFloatBuilder4 = schemaTypeBuilder1.floatBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder6 = schemaTypeBuilder1.fixed("{\"type\":\"map\",\"values\":\"boolean\"}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = schemaFixedBuilder6.size(1);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test154");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "float", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", false);
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test155");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder1 = org.apache.avro.SchemaBuilder.enumeration("{\"type\":\"record\",\"fields\":[]}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder2 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder3 = schemaMapBuilder2.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder4 = schemaTypeBuilder3.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr5 = schemaTypeBuilder3.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaTypeBuilder3.record("logicalType");
        java.lang.String[] strArray8 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder7.aliases(strArray8);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder7.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder7.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray17 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder18 = schemaRecordBuilder16.aliases(strArray17);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder19 = schemaRecordBuilder7.aliases(strArray17);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = schemaEnumBuilder1.symbols(strArray17);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test156");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", false);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test157");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        boolean boolean7 = schema5.propsContainsKey("boolean");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", schema5, "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test158");
        java.lang.String[] strArray5 = new java.lang.String[] { "union", "map" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createEnum("hi!", "float", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", (java.util.List<java.lang.String>) strList6);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test159");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.booleanType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = schemaTypeBuilder2.map();
        org.apache.avro.Schema schema8 = schemaTypeBuilder2.booleanType();
        java.lang.String str9 = schema8.getName();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", schema8);
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test160");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.floatType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field7 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"boolean\"}", schema5, "{\"type\":\"map\",\"values\":\"bytes\"}");
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test161");
        java.lang.String[] strArray7 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "boolean" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "", "{\"type\":\"map\",\"values\":\"long\"}", (java.util.List<java.lang.String>) strList8, "logicalType");
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test162");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder2.fixed("hi!");
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.longType();
        boolean boolean7 = schema5.propsContainsKey("logicalType");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder8 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder9 = schemaMapBuilder8.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder10 = schemaTypeBuilder9.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr11 = schemaTypeBuilder9.stringBuilder();
        org.apache.avro.Schema schema12 = schemaTypeBuilder9.longType();
        java.lang.String str14 = schema12.getProp("map");
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.applyAliases(schema5, schema12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field16 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", schema5);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test163");
        java.lang.String[] strArray30 = new java.lang.String[] { "hi!", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "decimal", "array", "record", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\"type\":\"map\",\"values\":\"boolean\"}", "string", "logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "string", "null", "{\"type\":\"map\",\"values\":\"boolean\"}", "array", "boolean", "{\"type\":\"map\",\"values\":\"double\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "union", "record", "{\"type\":\"map\",\"values\":\"long\"}", "int" };
        java.util.ArrayList<java.lang.String> strList31 = new java.util.ArrayList<java.lang.String>();
        boolean boolean32 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList31, strArray30);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema33 = org.apache.avro.Schema.createEnum("big-decimal", "null", "{\"type\":\"map\",\"values\":\"float\"}", (java.util.List<java.lang.String>) strList31);
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test164");
        java.lang.String[] strArray24 = new java.lang.String[] { "float", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "record", "{\"type\":\"array\",\"items\":\"long\"}", "float", "decimal", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "duration", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "double", "{\"type\":\"map\",\"values\":\"long\"}" };
        java.util.ArrayList<java.lang.String> strList25 = new java.util.ArrayList<java.lang.String>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList25, strArray24);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema27 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "", (java.util.List<java.lang.String>) strList25);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test165");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        java.lang.Object obj8 = schema5.getObjectProp("hi!", (java.lang.Object) 100);
        org.apache.avro.LogicalType logicalType9 = schema5.getLogicalType();
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createMap(schema5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field12 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", schema5, "record");
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test166");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.Schema schema3 = schemaTypeBuilder1.stringType();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder1.enumeration("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder6 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder7 = schemaMapBuilder6.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder8 = schemaTypeBuilder7.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr9 = schemaTypeBuilder7.stringBuilder();
        org.apache.avro.Schema schema10 = schemaTypeBuilder7.bytesType();
        org.apache.avro.Schema schema11 = schemaTypeBuilder7.booleanType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder12 = schemaTypeBuilder7.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder13 = schemaMapBuilder12.values();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr14 = schemaTypeBuilder13.stringBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder15 = schemaTypeBuilder13.nullBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder16 = schemaTypeBuilder13.nullBuilder();
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.Schema> schemaBaseTypeBuilder17 = schemaTypeBuilder13.nullable();
        org.apache.avro.SchemaBuilder.IntBuilder<org.apache.avro.Schema> schemaIntBuilder18 = schemaTypeBuilder13.intBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder20 = schemaTypeBuilder13.record("record");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder22 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray25 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder26 = schemaRecordBuilder22.aliases(strArray25);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder27 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder28 = schemaMapBuilder27.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder29 = schemaTypeBuilder28.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr30 = schemaTypeBuilder28.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder32 = schemaTypeBuilder28.record("logicalType");
        java.lang.String[] strArray33 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder34 = schemaRecordBuilder32.aliases(strArray33);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder35 = schemaRecordBuilder26.aliases(strArray33);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder36 = schemaRecordBuilder20.aliases(strArray33);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema37 = schemaEnumBuilder5.symbols(strArray33);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test167");
        java.lang.String[] strArray48 = new java.lang.String[] { "map", "{\"type\":\"map\",\"values\":\"boolean\"}", "logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "decimal", "", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "null", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "duration", "duration", "{\"type\":\"map\",\"values\":\"string\"}", "array", "big-decimal", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"double\"}", "string", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "array", "{\"type\":\"map\",\"values\":\"string\"}", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "record", "decimal", "{\"type\":\"map\",\"values\":\"string\"}", "map", "{\"type\":\"map\",\"values\":\"long\"}", "null", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "decimal", "double", "string", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "record" };
        java.util.ArrayList<java.lang.String> strList49 = new java.util.ArrayList<java.lang.String>();
        boolean boolean50 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList49, strArray48);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema51 = org.apache.avro.Schema.createEnum("", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "map", (java.util.List<java.lang.String>) strList49);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test168");
        java.lang.String[] strArray38 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "string", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "string", "{\"type\":\"map\",\"values\":\"double\"}", "logicalType", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "hi!", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "hi!", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "decimal", "double", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "string", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":\"float\"}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}" };
        java.util.ArrayList<java.lang.String> strList39 = new java.util.ArrayList<java.lang.String>();
        boolean boolean40 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList39, strArray38);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema42 = org.apache.avro.Schema.createEnum("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "string", (java.util.List<java.lang.String>) strList39, "hi!");
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test169");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder5.prop("float", "float");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder10 = schemaRecordBuilder8.namespace("logicalType");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler11 = schemaRecordBuilder8.fields();
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test170");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.floatType();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder6 = schemaTypeBuilder1.nullBuilder();
        org.apache.avro.Schema schema7 = schemaTypeBuilder1.stringType();
        org.apache.avro.Schema schema8 = schemaTypeBuilder1.booleanType();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder10 = schemaTypeBuilder1.record("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler11 = schemaRecordBuilder10.fields();
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test171");
        org.apache.avro.LogicalTypes.Decimal decimal1 = org.apache.avro.LogicalTypes.decimal(10);
        int int2 = decimal1.getPrecision();
        java.lang.String str3 = decimal1.getName();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = org.apache.avro.SchemaBuilder.record("");
        boolean boolean6 = decimal1.equals((java.lang.Object) schemaRecordBuilder5);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaTypeBuilder8.record("logicalType");
        java.lang.String[] strArray13 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder12.aliases(strArray13);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder15 = schemaRecordBuilder5.aliases(strArray13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler16 = schemaRecordBuilder5.fields();
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test172");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":\"float\"}", true);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test173");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder2.stringType();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        org.apache.avro.LogicalType logicalType6 = org.apache.avro.LogicalTypes.fromSchema(schema5);
        schema5.addProp("duration", "{\"type\":\"record\",\"fields\":[]}");
        boolean boolean10 = schema5.hasProps();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field12 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", schema5, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}");
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test174");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", false);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test175");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test176");
        java.lang.String[] strArray40 = new java.lang.String[] { "hi!", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"bytes\"}", "null", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\"type\":\"map\",\"values\":\"string\"}", "big-decimal", "{\"type\":\"map\",\"values\":\"bytes\"}", "float", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "null", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "duration", "{\"type\":\"map\",\"values\":\"null\"}", "", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "logicalType", "map", "union", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "string", "{\"type\":\"array\",\"items\":\"long\"}" };
        java.util.ArrayList<java.lang.String> strList41 = new java.util.ArrayList<java.lang.String>();
        boolean boolean42 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList41, strArray40);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema44 = org.apache.avro.Schema.createEnum("big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", (java.util.List<java.lang.String>) strList41, "");
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test177");
        java.lang.String[] strArray37 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "null", "{\"type\":\"map\",\"values\":\"long\"}", "decimal", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\"type\":\"map\",\"values\":\"float\"}", "", "string", "decimal", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "hi!", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "union", "duration", "array", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"map\",\"values\":\"bytes\"}" };
        java.util.ArrayList<java.lang.String> strList38 = new java.util.ArrayList<java.lang.String>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList38, strArray37);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema41 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"float\"}", "union", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", (java.util.List<java.lang.String>) strList38, "array");
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test178");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "double", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test179");
        java.lang.String[] strArray27 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "int", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "record", "boolean", "record", "{\"type\":\"map\",\"values\":\"bytes\"}", "decimal", "duration", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "union", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "boolean", "map", "map", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"null\"}" };
        java.util.ArrayList<java.lang.String> strList28 = new java.util.ArrayList<java.lang.String>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList28, strArray27);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "hi!", "{\"type\":\"map\",\"values\":\"boolean\"}", (java.util.List<java.lang.String>) strList28, "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}");
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test180");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray4 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder1.aliases(strArray4);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder1.doc("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder1.namespace("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler10 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test181");
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.Schema> schemaBaseTypeBuilder0 = org.apache.avro.SchemaBuilder.nullable();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder2 = schemaBaseTypeBuilder0.record("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder4 = schemaRecordBuilder2.namespace("map");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler5 = schemaRecordBuilder2.fields();
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test182");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("", "{\"type\":\"array\",\"items\":\"long\"}", "logicalType", 1);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test183");
        java.lang.String[] strArray39 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "map", "double", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "logicalType", "\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "duration", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\"type\":\"map\",\"values\":\"float\"}", "", "int", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", "float", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "record", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "map", "{\"type\":\"map\",\"values\":\"bytes\"}" };
        java.util.ArrayList<java.lang.String> strList40 = new java.util.ArrayList<java.lang.String>();
        boolean boolean41 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList40, strArray39);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema42 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", (java.util.List<java.lang.String>) strList40);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test184");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder6 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema7 = schemaTypeBuilder2.doubleType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", schema7, "array");
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test185");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder6 = schemaTypeBuilder2.booleanBuilder();
        org.apache.avro.Schema schema7 = schemaBooleanBuilder6.endBoolean();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", schema7, "{\"type\":\"array\",\"items\":\"long\"}");
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test186");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.bytesType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.booleanType();
        java.lang.String str8 = schema6.toString(false);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createMap(schema6);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder10 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder11 = schemaMapBuilder10.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder12 = schemaTypeBuilder11.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr13 = schemaTypeBuilder11.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder15 = schemaTypeBuilder11.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder16 = schemaTypeBuilder11.doubleBuilder();
        org.apache.avro.Schema schema17 = schemaDoubleBuilder16.endDouble();
        org.apache.avro.Schema schema18 = schemaDoubleBuilder16.endDouble();
        org.apache.avro.Schema schema19 = schemaDoubleBuilder16.endDouble();
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createArray(schema19);
        java.lang.String str21 = schema19.getDoc();
        schema6.putAll((org.apache.avro.JsonProperties) schema19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field24 = new org.apache.avro.Schema.Field("", schema19, "duration");
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test187");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder2.fixed("hi!");
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.nullType();
        org.apache.avro.LogicalType logicalType6 = org.apache.avro.LogicalTypes.fromSchema(schema5);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.Schema schema11 = schemaStringBldr10.endString();
        org.apache.avro.LogicalTypes.Decimal decimal14 = org.apache.avro.LogicalTypes.decimal((int) '#');
        org.apache.avro.LogicalTypes.Decimal decimal16 = org.apache.avro.LogicalTypes.decimal(10);
        int int17 = decimal16.getPrecision();
        boolean boolean18 = decimal14.equals((java.lang.Object) int17);
        java.lang.Object obj19 = schema11.getObjectProp("logicalType", (java.lang.Object) int17);
        org.apache.avro.LogicalType logicalType20 = schema11.getLogicalType();
        org.apache.avro.Schema.Type type21 = schema11.getType();
        boolean boolean22 = schema5.equals((java.lang.Object) schema11);
        org.apache.avro.LogicalTypes.Decimal decimal25 = org.apache.avro.LogicalTypes.decimal(10);
        java.lang.String str26 = decimal25.getName();
        int int27 = decimal25.getScale();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder28 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder29 = schemaMapBuilder28.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder30 = schemaTypeBuilder29.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder31 = schemaTypeBuilder29.longBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder32 = schemaTypeBuilder29.nullBuilder();
        org.apache.avro.Schema schema33 = schemaNullBuilder32.endNull();
        org.apache.avro.Schema schema34 = schemaNullBuilder32.endNull();
        boolean boolean35 = decimal25.equals((java.lang.Object) schema34);
        int int36 = decimal25.getScale();
        org.apache.avro.Schema.Field.Order order37 = org.apache.avro.Schema.Field.Order.DESCENDING;
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field38 = new org.apache.avro.Schema.Field("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", schema5, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", (java.lang.Object) int36, order37);
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test188");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema6 = schemaBytesBuilder3.endBytes();
        org.apache.avro.LogicalType logicalType7 = org.apache.avro.LogicalTypes.fromSchema(schema6);
        boolean boolean9 = schema6.propsContainsKey("{\"type\":\"map\",\"values\":\"null\"}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder10 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder11 = schemaMapBuilder10.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder12 = schemaTypeBuilder11.bytesBuilder();
        org.apache.avro.Schema schema13 = schemaBytesBuilder12.endBytes();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder14 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder15 = schemaMapBuilder14.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder16 = schemaTypeBuilder15.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr17 = schemaTypeBuilder15.stringBuilder();
        org.apache.avro.Schema schema18 = schemaStringBldr17.endString();
        schema13.addAllProps((org.apache.avro.JsonProperties) schema18);
        schema6.addAllProps((org.apache.avro.JsonProperties) schema13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field22 = new org.apache.avro.Schema.Field("float", schema13, "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test189");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"double\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", (-1));
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test190");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder6 = schemaTypeBuilder2.fixed("{\"type\":\"map\",\"values\":\"null\"}");
        org.apache.avro.Schema schema7 = schemaTypeBuilder2.bytesType();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema9 = schemaTypeBuilder2.floatType();
        boolean boolean10 = schema9.hasProps();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field12 = new org.apache.avro.Schema.Field("boolean", schema9, "float");
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test191");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size(100);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test192");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder6 = schemaTypeBuilder1.doubleBuilder();
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder7 = schemaTypeBuilder1.booleanBuilder();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder8 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder10 = schemaTypeBuilder1.fixed("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema12 = schemaFixedBuilder10.size((int) (short) 10);
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test193");
        java.lang.String[] strArray22 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", "timestamp-nanos", "{\"type\":\"map\",\"values\":\"double\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}" };
        java.util.ArrayList<java.lang.String> strList23 = new java.util.ArrayList<java.lang.String>();
        boolean boolean24 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList23, strArray22);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema26 = org.apache.avro.Schema.createEnum("decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "int", (java.util.List<java.lang.String>) strList23, "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}");
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test194");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"double\"}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test195");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.Schema schema3 = schemaTypeBuilder2.doubleType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr7 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema8 = schemaTypeBuilder5.stringType();
        org.apache.avro.LogicalType logicalType9 = org.apache.avro.LogicalTypes.fromSchema(schema8);
        java.lang.String str11 = schema8.getProp("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.Schema schema12 = schemaTypeBuilder2.type(schema8);
        boolean boolean14 = schema12.propsContainsKey("");
        java.lang.String str15 = schema12.getDoc();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder16 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder17 = schemaMapBuilder16.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder18 = schemaTypeBuilder17.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr19 = schemaTypeBuilder17.stringBuilder();
        org.apache.avro.Schema schema20 = schemaTypeBuilder17.bytesType();
        schema12.putAll((org.apache.avro.JsonProperties) schema20);
        java.lang.Object obj23 = schema12.getObjectProp("union");
        java.lang.String str25 = schema12.toString(false);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder27 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder28 = schemaMapBuilder27.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder29 = schemaTypeBuilder28.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder31 = schemaTypeBuilder28.enumeration("");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder33 = schemaEnumBuilder31.defaultSymbol("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder35 = schemaEnumBuilder31.defaultSymbol("union");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder37 = schemaEnumBuilder31.defaultSymbol("string");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field38 = new org.apache.avro.Schema.Field("", schema12, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", (java.lang.Object) "string");
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test196");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder4 = schemaTypeBuilder1.enumeration("");
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.floatType();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder6 = schemaTypeBuilder1.nullBuilder();
        org.apache.avro.Schema schema7 = schemaTypeBuilder1.stringType();
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder8 = schemaTypeBuilder1.doubleBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder10 = schemaTypeBuilder1.fixed("");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema12 = schemaFixedBuilder10.size((int) (byte) 100);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test197");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "int", false);
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test198");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) '#');
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test199");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test200");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder5.prop("float", "float");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder5.prop("big-decimal", (java.lang.Object) "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder11.prop("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler15 = schemaRecordBuilder11.fields();
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test201");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.Schema schema2 = schemaTypeBuilder1.doubleType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder3 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder4 = schemaMapBuilder3.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder5 = schemaTypeBuilder4.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr6 = schemaTypeBuilder4.stringBuilder();
        org.apache.avro.Schema schema7 = schemaTypeBuilder4.stringType();
        org.apache.avro.LogicalType logicalType8 = org.apache.avro.LogicalTypes.fromSchema(schema7);
        java.lang.String str10 = schema7.getProp("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.Schema schema11 = schemaTypeBuilder1.type(schema7);
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder13 = schemaTypeBuilder1.enumeration("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}");
        java.lang.String[] strArray14 = new java.lang.String[] {};
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema15 = schemaEnumBuilder13.symbols(strArray14);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test202");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.stringType();
        java.lang.String str6 = schema5.getName();
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createMap(schema5);
        java.util.Map<java.lang.String, java.lang.Object> strMap8 = schema7.getObjectProps();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", schema7);
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test203");
        java.lang.String[] strArray24 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "string", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "big-decimal", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "int", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "map", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"map\",\"values\":\"bytes\"}", "map", "\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}" };
        java.util.ArrayList<java.lang.String> strList25 = new java.util.ArrayList<java.lang.String>();
        boolean boolean26 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList25, strArray24);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema28 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "big-decimal", (java.util.List<java.lang.String>) strList25, "duration");
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test204");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"record\",\"fields\":[]}", "hi!", true);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test205");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "record", false);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test206");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", 100);
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test207");
        java.lang.String[] strArray15 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "array", "float", "hi!", "{\"type\":\"map\",\"values\":\"float\"}", "hi!", "{\"type\":\"map\",\"values\":\"long\"}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}" };
        java.util.ArrayList<java.lang.String> strList16 = new java.util.ArrayList<java.lang.String>();
        boolean boolean17 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList16, strArray15);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"boolean\"}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", (java.util.List<java.lang.String>) strList16, "string");
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test208");
        java.lang.String[] strArray41 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"string\"}", "record", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "duration", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "record", "array", "union", "null", "hi!", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "boolean", "\"string\"", "{\"type\":\"map\",\"values\":\"string\"}", "double" };
        java.util.ArrayList<java.lang.String> strList42 = new java.util.ArrayList<java.lang.String>();
        boolean boolean43 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList42, strArray41);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema45 = org.apache.avro.Schema.createEnum("{\"type\":\"array\",\"items\":\"long\"}", "boolean", "{\"type\":\"map\",\"values\":\"double\"}", (java.util.List<java.lang.String>) strList42, "");
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test209");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaRecordBuilder5.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder12.namespace("");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder16 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray17 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder18 = schemaRecordBuilder16.aliases(strArray17);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder20 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray23 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder24 = schemaRecordBuilder20.aliases(strArray23);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder25 = schemaRecordBuilder16.aliases(strArray23);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder26 = schemaRecordBuilder12.aliases(strArray23);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler27 = schemaRecordBuilder12.fields();
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test210");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder6 = schemaTypeBuilder1.doubleBuilder();
        org.apache.avro.Schema schema7 = schemaTypeBuilder1.floatType();
        org.apache.avro.Schema schema8 = schemaTypeBuilder1.floatType();
        org.apache.avro.Schema schema9 = schemaTypeBuilder1.booleanType();
        org.apache.avro.Schema schema10 = schemaTypeBuilder1.intType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder11 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder12 = schemaMapBuilder11.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder13 = schemaTypeBuilder12.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder14 = schemaTypeBuilder12.longBuilder();
        org.apache.avro.Schema schema15 = schemaLongBuilder14.endLong();
        org.apache.avro.Schema schema16 = schemaLongBuilder14.endLong();
        org.apache.avro.LogicalType logicalType17 = org.apache.avro.LogicalTypes.fromSchemaIgnoreInvalid(schema16);
        boolean boolean18 = schema16.isUnion();
        org.apache.avro.Schema schema19 = schemaTypeBuilder1.type(schema16);
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder21 = schemaTypeBuilder1.enumeration("{\"type\":\"map\",\"values\":\"string\"}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder23 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray26 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder27 = schemaRecordBuilder23.aliases(strArray26);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder28 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder29 = schemaMapBuilder28.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder30 = schemaTypeBuilder29.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr31 = schemaTypeBuilder29.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder33 = schemaTypeBuilder29.record("logicalType");
        java.lang.String[] strArray34 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder35 = schemaRecordBuilder33.aliases(strArray34);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder36 = schemaRecordBuilder27.aliases(strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema37 = schemaEnumBuilder21.symbols(strArray34);
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test211");
        java.lang.String[] strArray5 = new java.lang.String[] { "null", "{\"type\":\"map\",\"values\":\"boolean\"}" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", (java.util.List<java.lang.String>) strList6, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}");
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test212");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        java.lang.Object obj8 = org.apache.avro.Schema.parseJsonToObject("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field9 = new org.apache.avro.Schema.Field("logicalType", schema5, "{\"type\":\"record\",\"fields\":[]}", (java.lang.Object) "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test213");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder4 = schemaTypeBuilder1.fixed("map");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema6 = schemaFixedBuilder4.size(100);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test214");
        java.lang.String[] strArray37 = new java.lang.String[] { "map", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "map", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "float", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "logicalType", "{\"type\":\"map\",\"values\":\"float\"}", "hi!", "duration", "decimal", "{\"type\":\"record\",\"fields\":[]}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"record\",\"fields\":[]}", "float", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "boolean", "logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}" };
        java.util.ArrayList<java.lang.String> strList38 = new java.util.ArrayList<java.lang.String>();
        boolean boolean39 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList38, strArray37);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema41 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", (java.util.List<java.lang.String>) strList38, "array");
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test215");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"map\",\"values\":\"bytes\"}", "array", false);
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test216");
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = org.apache.avro.SchemaBuilder.builder("map");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema5 = schemaFixedBuilder3.size((int) (short) 100);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test217");
        java.lang.String[] strArray34 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"boolean\"}", "int", "big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}", "{\"type\":\"map\",\"values\":\"long\"}", "null", "{\"type\":\"map\",\"values\":\"int\"}", "float", "duration", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "null", "{\"type\":\"array\",\"items\":\"long\"}", "null", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "logicalType", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"array\",\"items\":\"long\"}", "double" };
        java.util.ArrayList<java.lang.String> strList35 = new java.util.ArrayList<java.lang.String>();
        boolean boolean36 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList35, strArray34);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema38 = org.apache.avro.Schema.createEnum("record", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "null", (java.util.List<java.lang.String>) strList35, "{\"type\":\"map\",\"values\":\"boolean\"}");
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test218");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createRecord("map", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test219");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", false);
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test220");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder4 = schemaTypeBuilder2.longBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.nullType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = schemaTypeBuilder2.map();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder8 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder9 = schemaMapBuilder8.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder10 = schemaTypeBuilder9.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder12 = schemaTypeBuilder9.enumeration("");
        org.apache.avro.Schema schema13 = schemaTypeBuilder9.floatType();
        boolean boolean14 = schema13.isNullable();
        boolean boolean16 = schema13.equals((java.lang.Object) (byte) -1);
        org.apache.avro.Schema schema17 = schemaMapBuilder7.values(schema13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field19 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"bytes\"}", schema17, "{\"type\":\"map\",\"values\":\"bytes\"}");
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test221");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.Schema schema3 = schemaTypeBuilder1.stringType();
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.stringType();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder1.record("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler7 = schemaRecordBuilder6.fields();
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test222");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder1.record("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler7 = schemaRecordBuilder6.fields();
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test223");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder6 = schemaTypeBuilder2.booleanBuilder();
        org.apache.avro.Schema schema7 = schemaBooleanBuilder6.endBoolean();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field8 = new org.apache.avro.Schema.Field("", schema7);
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test224");
        java.lang.String[] strArray27 = new java.lang.String[] { "logicalType", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "hi!", "\"string\"", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"null\"}", "null", "", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "record", "{\"type\":\"map\",\"values\":\"double\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"float\"}", "union", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "timestamp-nanos", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}" };
        java.util.ArrayList<java.lang.String> strList28 = new java.util.ArrayList<java.lang.String>();
        boolean boolean29 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList28, strArray27);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema31 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "int", (java.util.List<java.lang.String>) strList28, "\"string\"");
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test225");
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = org.apache.avro.SchemaBuilder.builder("float");
        org.apache.avro.Schema schema3 = schemaTypeBuilder2.nullType();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field5 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", schema3, "{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}");
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test226");
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder1 = org.apache.avro.SchemaBuilder.enumeration("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder3 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray4 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder3.aliases(strArray4);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder3.doc("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder3.namespace("{\"type\":\"map\",\"values\":\"string\"}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder3.namespace("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder12 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder13 = schemaMapBuilder12.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder14 = schemaTypeBuilder13.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr15 = schemaTypeBuilder13.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder17 = schemaTypeBuilder13.record("logicalType");
        java.lang.String[] strArray18 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder19 = schemaRecordBuilder17.aliases(strArray18);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder21 = schemaRecordBuilder17.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder24 = schemaRecordBuilder17.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder26 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray27 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder28 = schemaRecordBuilder26.aliases(strArray27);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder29 = schemaRecordBuilder17.aliases(strArray27);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder30 = schemaRecordBuilder11.aliases(strArray27);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema31 = schemaEnumBuilder1.symbols(strArray27);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test227");
        java.lang.String[] strArray6 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "double", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "boolean", (java.util.List<java.lang.String>) strList7, "{\"type\":\"map\",\"values\":\"float\"}");
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test228");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler10 = schemaRecordBuilder5.fields();
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test229");
        java.lang.String[] strArray12 = new java.lang.String[] { "\"string\"", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "decimal", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "double", "union" };
        java.util.ArrayList<java.lang.String> strList13 = new java.util.ArrayList<java.lang.String>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList13, strArray12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "", "{\"type\":\"map\",\"values\":\"string\"}", (java.util.List<java.lang.String>) strList13, "hi!");
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test230");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder5 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder6 = schemaMapBuilder5.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder7 = schemaTypeBuilder6.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder6.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        schema4.addAllProps((org.apache.avro.JsonProperties) schema9);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field12 = new org.apache.avro.Schema.Field("null", schema9, "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}");
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test231");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("decimal", "{\"type\":\"map\",\"values\":\"int\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", false);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test232");
        java.lang.String[] strArray20 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":\"double\"}", "duration", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "timestamp-nanos", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "timestamp-nanos", "duration", "\"string\"", "hi!", "timestamp-nanos", "hi!", "int", "double", "record" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema24 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", (java.util.List<java.lang.String>) strList21, "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}");
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test233");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.floatType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder7 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder8 = schemaMapBuilder7.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder9 = schemaTypeBuilder8.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr10 = schemaTypeBuilder8.stringBuilder();
        org.apache.avro.Schema schema11 = schemaStringBldr10.endString();
        org.apache.avro.Schema schema12 = schemaStringBldr10.endString();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder13 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder14 = schemaMapBuilder13.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder15 = schemaTypeBuilder14.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr16 = schemaTypeBuilder14.stringBuilder();
        org.apache.avro.Schema schema17 = schemaTypeBuilder14.bytesType();
        org.apache.avro.Schema schema18 = schemaTypeBuilder14.booleanType();
        org.apache.avro.Schema[] schemaArray19 = new org.apache.avro.Schema[] { schema12, schema18 };
        java.util.ArrayList<org.apache.avro.Schema> schemaList20 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList20, schemaArray19);
        java.lang.String str23 = schema6.toString((java.util.Collection<org.apache.avro.Schema>) schemaList20, true);
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder25 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder26 = schemaMapBuilder25.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder27 = schemaTypeBuilder26.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr28 = schemaTypeBuilder26.stringBuilder();
        org.apache.avro.Schema schema29 = schemaStringBldr28.endString();
        java.lang.Object obj30 = schema6.getObjectProp("map", (java.lang.Object) schema29);
        java.lang.String str31 = schema29.getName();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field33 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"boolean\"}", schema29, "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}");
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test234");
        java.lang.String[] strArray31 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "{\"type\":\"map\",\"values\":\"null\"}", "duration", "{\"type\":\"map\",\"values\":\"boolean\"}", "float", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "double", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "timestamp-nanos", "", "logicalType", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "\"string\"", "null", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"record\",\"fields\":[]}", "duration", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "double", "{\"type\":\"map\",\"values\":\"int\"}", "{\"type\":\"map\",\"values\":\"null\"}", "union", "logicalType", "\"string\"", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}" };
        java.util.ArrayList<java.lang.String> strList32 = new java.util.ArrayList<java.lang.String>();
        boolean boolean33 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList32, strArray31);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema35 = org.apache.avro.Schema.createEnum("decimal", "{\"type\":\"record\",\"fields\":[]}", "{\"type\":\"map\",\"values\":\"float\"}", (java.util.List<java.lang.String>) strList32, "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}");
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test235");
        java.lang.String[] strArray21 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "{\"type\":\"map\",\"values\":\"int\"}", "union", "big-decimal", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"int\"}", "{\"type\":\"map\",\"values\":\"float\"}", "boolean", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "duration" };
        java.util.ArrayList<java.lang.String> strList22 = new java.util.ArrayList<java.lang.String>();
        boolean boolean23 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList22, strArray21);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema24 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"int\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "map", (java.util.List<java.lang.String>) strList22);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test236");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.Schema schema3 = schemaTypeBuilder2.doubleType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr7 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema8 = schemaTypeBuilder5.stringType();
        org.apache.avro.LogicalType logicalType9 = org.apache.avro.LogicalTypes.fromSchema(schema8);
        java.lang.String str11 = schema8.getProp("{\"type\":\"map\",\"values\":\"boolean\"}");
        org.apache.avro.Schema schema12 = schemaTypeBuilder2.type(schema8);
        schema12.addProp("", "hi!");
        schema12.addProp("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "record");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field19 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"null\"}", schema12);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test237");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}", false);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test238");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"boolean\"}", false);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test239");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("boolean");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler2 = schemaRecordBuilder1.fields();
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test240");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"bytes\"}", (int) (short) 1);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test241");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder2.nullType();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder5 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder6 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder7 = schemaMapBuilder6.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder8 = schemaTypeBuilder7.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder10 = schemaTypeBuilder7.enumeration("");
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder11 = schemaTypeBuilder7.booleanBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder12 = schemaTypeBuilder7.nullBuilder();
        org.apache.avro.Schema schema13 = schemaNullBuilder12.endNull();
        org.apache.avro.Schema schema14 = schemaNullBuilder12.endNull();
        org.apache.avro.Schema.SeenPair seenPair15 = new org.apache.avro.Schema.SeenPair((java.lang.Object) schemaTypeBuilder2, (java.lang.Object) schema14);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field16 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"null\"}", schema14);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test242");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "hi!", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", false);
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test243");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", (int) (short) 10);
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test244");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", false);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test245");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("boolean", "{\"type\":\"map\",\"values\":\"int\"}", "boolean", false);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test246");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "double", "logicalType", false);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test247");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder2.record("logicalType");
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.SchemaBuilder.UnionAccumulator<org.apache.avro.Schema>> schemaUnionAccumulatorBaseTypeBuilder7 = schemaTypeBuilder2.unionOf();
        org.apache.avro.Schema schema8 = schemaTypeBuilder2.doubleType();
        boolean boolean9 = schema8.hasProps();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field11 = new org.apache.avro.Schema.Field("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", schema8, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}");
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test248");
        java.lang.String[] strArray5 = new java.lang.String[] { "", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"double\"}", "double", "map", (java.util.List<java.lang.String>) strList6, "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}");
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test249");
        java.lang.String[] strArray19 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"float\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "int", "map", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "timestamp-nanos", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "null", "logicalType" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createEnum("\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}", "", (java.util.List<java.lang.String>) strList20);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test250");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("null", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", false);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test251");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"int\"}", "{\"type\":\"map\",\"values\":\"int\"}", (int) 'a');
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test252");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "{\"type\":\"array\",\"items\":\"long\"}", "", true);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test253");
        java.lang.String[] strArray6 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "map", (java.util.List<java.lang.String>) strList7);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test254");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.longType();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder1.record("{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler7 = schemaRecordBuilder6.fields();
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test255");
        java.lang.String[] strArray17 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"bytes\"},\"map\":\"double\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "map", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}" };
        java.util.ArrayList<java.lang.String> strList18 = new java.util.ArrayList<java.lang.String>();
        boolean boolean19 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList18, strArray17);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", (java.util.List<java.lang.String>) strList18);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test256");
        java.lang.String[] strArray14 = new java.lang.String[] { "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"bytes\"},\"map\":\"double\"}", "int", "{\"type\":\"map\",\"values\":\"bytes\"}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "record", "decimal", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "timestamp-nanos" };
        java.util.ArrayList<java.lang.String> strList15 = new java.util.ArrayList<java.lang.String>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList15, strArray14);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", (java.util.List<java.lang.String>) strList15, "{\"type\":\"map\",\"values\":\"float\"}");
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test257");
        java.lang.String[] strArray57 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"double\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"map\",\"values\":\"int\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "duration", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}", "{\"type\":\"map\",\"values\":\"int\"}", "boolean", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "union", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "record", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "logicalType", "logicalType", "hi!", "{\"type\":\"map\",\"values\":\"int\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}", "{\"type\":\"map\",\"values\":\"null\"}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "record", "hi!", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", "{\"type\":\"map\",\"values\":\"boolean\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"bytes\"},\"map\":\"double\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "array", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "big-decimal", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "\"string\"", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"map\",\"values\":\"double\"}", "array", "string", "int", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}" };
        java.util.ArrayList<java.lang.String> strList58 = new java.util.ArrayList<java.lang.String>();
        boolean boolean59 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList58, strArray57);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema60 = org.apache.avro.Schema.createEnum("", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "null", (java.util.List<java.lang.String>) strList58);
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test258");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":\"int\"}", "null", (int) 'a');
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test259");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.Schema schema4 = schemaTypeBuilder1.bytesType();
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.booleanType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder6 = schemaTypeBuilder1.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder7 = schemaMapBuilder6.values();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder7.stringBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder9 = schemaTypeBuilder7.nullBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder10 = schemaTypeBuilder7.nullBuilder();
        org.apache.avro.SchemaBuilder.BaseTypeBuilder<org.apache.avro.Schema> schemaBaseTypeBuilder11 = schemaTypeBuilder7.nullable();
        org.apache.avro.SchemaBuilder.IntBuilder<org.apache.avro.Schema> schemaIntBuilder12 = schemaTypeBuilder7.intBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaTypeBuilder7.record("record");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler15 = schemaRecordBuilder14.fields();
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test260");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("record", "big-decimal", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", (int) (short) 0);
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test261");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("union");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (short) -1);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test262");
        java.lang.String[] strArray53 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"string\",\n  \"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\" : \"\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "string", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "array", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"float\"\n  }\n}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\"type\":\"map\",\"values\":\"double\"}", "time-micros", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"string\"\n  }\n}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\"type\":\"array\",\"items\":\"long\"}", "", "string", "timestamp-nanos", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "record", "{\n  \"type\" : \"map\",\n  \"values\" : \"null\"\n}", "decimal", "float", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", "double", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "big-decimal", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "big-decimal", "{\"type\":\"map\",\"values\":\"long\"}", "null", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "map", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : {\n      \"type\" : \"map\",\n      \"values\" : \"long\"\n    }\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}" };
        java.util.ArrayList<java.lang.String> strList54 = new java.util.ArrayList<java.lang.String>();
        boolean boolean55 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList54, strArray53);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema56 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"double\"}}", "", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", (java.util.List<java.lang.String>) strList54);
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test263");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("", "", "", true);
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test264");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema5 = schemaFixedBuilder3.size((int) (byte) 10);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test265");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema12 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", "float", "int", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test266");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr4 = schemaTypeBuilder2.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder6 = schemaTypeBuilder2.record("logicalType");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder7 = schemaTypeBuilder2.doubleBuilder();
        org.apache.avro.Schema schema8 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema9 = schemaTypeBuilder2.floatType();
        org.apache.avro.Schema schema10 = org.apache.avro.Schema.createArray(schema9);
        org.apache.avro.LogicalType logicalType11 = org.apache.avro.LogicalTypes.fromSchema(schema10);
        java.lang.String str13 = schema10.getProp("union");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field15 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", schema10, "");
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test267");
        org.apache.avro.Schema schema2 = org.apache.avro.Schema.parse("\"string\"");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field3 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", schema2);
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test268");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder3 = schemaTypeBuilder1.fixed("hi!");
        org.apache.avro.SchemaBuilder.DoubleBuilder<org.apache.avro.Schema> schemaDoubleBuilder4 = schemaTypeBuilder1.doubleBuilder();
        org.apache.avro.Schema schema5 = schemaTypeBuilder1.doubleType();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder7 = schemaTypeBuilder1.enumeration("int");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray10 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder9.aliases(strArray10);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder13 = org.apache.avro.SchemaBuilder.record("");
        java.lang.String[] strArray16 = new java.lang.String[] { "", "" };
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder17 = schemaRecordBuilder13.aliases(strArray16);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder18 = schemaRecordBuilder9.aliases(strArray16);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema19 = schemaEnumBuilder7.symbols(strArray16);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test269");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder1 = org.apache.avro.SchemaBuilder.record("");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder3 = schemaRecordBuilder1.namespace("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaRecordBuilder3.namespace("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler6 = schemaRecordBuilder3.fields();
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test270");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema6 = schemaBytesBuilder3.endBytes();
        org.apache.avro.LogicalType logicalType7 = org.apache.avro.LogicalTypes.fromSchema(schema6);
        boolean boolean9 = schema6.propsContainsKey("{\"type\":\"map\",\"values\":\"null\"}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"float\"}", schema6);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test271");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.namespace("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler10 = schemaRecordBuilder5.fields();
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test272");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.Schema schema6 = schemaTypeBuilder2.floatType();
        boolean boolean7 = schema6.isNullable();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder8 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder9 = schemaMapBuilder8.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder10 = schemaTypeBuilder9.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder12 = schemaTypeBuilder9.enumeration("");
        org.apache.avro.Schema schema13 = schemaTypeBuilder9.floatType();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder14 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder15 = schemaMapBuilder14.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder16 = schemaTypeBuilder15.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr17 = schemaTypeBuilder15.stringBuilder();
        org.apache.avro.Schema schema18 = schemaStringBldr17.endString();
        org.apache.avro.Schema schema19 = schemaStringBldr17.endString();
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder20 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder21 = schemaMapBuilder20.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder22 = schemaTypeBuilder21.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr23 = schemaTypeBuilder21.stringBuilder();
        org.apache.avro.Schema schema24 = schemaTypeBuilder21.bytesType();
        org.apache.avro.Schema schema25 = schemaTypeBuilder21.booleanType();
        org.apache.avro.Schema[] schemaArray26 = new org.apache.avro.Schema[] { schema19, schema25 };
        java.util.ArrayList<org.apache.avro.Schema> schemaList27 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean28 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList27, schemaArray26);
        java.lang.String str30 = schema13.toString((java.util.Collection<org.apache.avro.Schema>) schemaList27, true);
        java.lang.String str32 = schema6.toString((java.util.Collection<org.apache.avro.Schema>) schemaList27, true);
        java.lang.Object obj34 = schema6.getObjectProp("hi!");
        java.lang.String str35 = schema6.getDoc();
        org.apache.avro.LogicalType logicalType36 = org.apache.avro.LogicalTypes.fromSchema(schema6);
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder39 = org.apache.avro.SchemaBuilder.enumeration("logicalType");
        org.apache.avro.Schema.Field.Order order40 = org.apache.avro.Schema.Field.Order.IGNORE;
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field41 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":\"string\"}", schema6, "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", (java.lang.Object) "logicalType", order40);
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test273");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.Schema schema4 = schemaBytesBuilder3.endBytes();
        org.apache.avro.Schema schema5 = schemaBytesBuilder3.endBytes();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field6 = new org.apache.avro.Schema.Field("{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}", schema5);
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test274");
        java.lang.String[] strArray51 = new java.lang.String[] { "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "{\"type\":\"map\",\"values\":\"boolean\"}", "duration", "{\"type\":\"record\",\"fields\":[]}", "map", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"float\"}}", "big-decimal", "{\"type\":\"array\",\"items\":\"long\"}", "string", "null", "{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "array", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "array", "{\"type\":\"map\",\"values\":\"float\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "record", "\"string\"", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\",\n  \"{\\n  \\\"type\\\" : \\\"map\\\",\\n  \\\"values\\\" : \\\"int\\\"\\n}\" : \"string\"\n}", "array", "int", "logicalType", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "decimal", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"double\"\n  }\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "decimal", "{\"type\":\"map\",\"values\":\"bytes\"}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"int\"\n}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "double", "{\"type\":\"map\",\"values\":\"int\"}", "{\"type\":\"map\",\"values\":\"int\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"string\"\n}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\"type\":\"array\",\"items\":\"long\"}", "{\"type\":\"map\",\"values\":\"bytes\"}", "double", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}" };
        java.util.ArrayList<java.lang.String> strList52 = new java.util.ArrayList<java.lang.String>();
        boolean boolean53 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList52, strArray51);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema55 = org.apache.avro.Schema.createEnum("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"},\"\":\"hi!\"}", "{\"type\":\"map\",\"values\":\"long\"}", "{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", (java.util.List<java.lang.String>) strList52, "union");
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test275");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord("logicalType", "{\n  \"type\" : \"map\",\n  \"values\" : \"boolean\"\n}", "map", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test276");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder1 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder2 = schemaMapBuilder1.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder3 = schemaTypeBuilder2.bytesBuilder();
        org.apache.avro.SchemaBuilder.EnumBuilder<org.apache.avro.Schema> schemaEnumBuilder5 = schemaTypeBuilder2.enumeration("");
        org.apache.avro.SchemaBuilder.BooleanBuilder<org.apache.avro.Schema> schemaBooleanBuilder6 = schemaTypeBuilder2.booleanBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder7 = schemaTypeBuilder2.nullBuilder();
        org.apache.avro.SchemaBuilder.NullBuilder<org.apache.avro.Schema> schemaNullBuilder8 = schemaTypeBuilder2.nullBuilder();
        org.apache.avro.Schema schema9 = schemaNullBuilder8.endNull();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field10 = new org.apache.avro.Schema.Field("{\"type\":\"map\",\"values\":{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\",\"logicalType\":{}}}}", schema9);
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test277");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "null", "timestamp-nanos", (int) (short) 10);
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test278");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder4 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder5 = schemaMapBuilder4.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder6 = schemaTypeBuilder5.bytesBuilder();
        org.apache.avro.SchemaBuilder.LongBuilder<org.apache.avro.Schema> schemaLongBuilder7 = schemaTypeBuilder5.longBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr8 = schemaTypeBuilder5.stringBuilder();
        org.apache.avro.Schema schema9 = schemaStringBldr8.endString();
        org.apache.avro.Schema.Field[] fieldArray11 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList12 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList12, fieldArray11);
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        schema9.addProp("{\"type\":\"map\",\"values\":\"float\"}", (java.lang.Object) fieldList12);
        org.apache.avro.Schema schema17 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        org.apache.avro.Schema schema19 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createRecord("{\n  \"type\" : \"array\",\n  \"items\" : {\n    \"type\" : \"map\",\n    \"values\" : \"bytes\"\n  }\n}", "{\"type\":\"array\",\"items\":\"long\"}", "{\n  \"type\" : \"map\",\n  \"values\" : {\n    \"type\" : \"map\",\n    \"values\" : \"null\",\n    \"duration\" : \"\"\n  }\n}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList12);
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test279");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("{\"type\":\"map\",\"values\":\"float\"}", "", "logicalType", (int) (short) 10);
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test280");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"string\"}}", "double", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test281");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        java.lang.String[] strArray6 = new java.lang.String[] {};
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder7 = schemaRecordBuilder5.aliases(strArray6);
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder9 = schemaRecordBuilder5.doc("map");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder12 = schemaRecordBuilder5.prop("{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", "union");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder12.doc("duration");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler15 = schemaRecordBuilder12.fields();
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test282");
        org.apache.avro.SchemaBuilder.FixedBuilder<org.apache.avro.Schema> schemaFixedBuilder1 = org.apache.avro.SchemaBuilder.fixed("boolean");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema3 = schemaFixedBuilder1.size((int) (short) 10);
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test283");
        java.lang.String[] strArray18 = new java.lang.String[] { "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"boolean\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"null\",\"duration\":\"\"}}", "{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":{\"type\":\"map\",\"values\":\"int\"}}}", "float", "{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "hi!", "string", "record", "\"string\"", "double", "logicalType", "double", "{\"type\":\"map\",\"values\":\"string\"}", "{\"type\":\"map\",\"values\":\"string\",\"{\\\"type\\\":\\\"map\\\",\\\"values\\\":\\\"boolean\\\"}\":\"\"}", "{\n  \"type\" : \"map\",\n  \"values\" : [ \"bytes\", \"null\" ]\n}" };
        java.util.ArrayList<java.lang.String> strList19 = new java.util.ArrayList<java.lang.String>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList19, strArray18);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema21 = org.apache.avro.Schema.createEnum("{\n  \"type\" : \"map\",\n  \"values\" : \"float\"\n}", "{\"type\":\"map\",\"values\":\"string\"}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}", (java.util.List<java.lang.String>) strList19);
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "SchemaRandoopErrorTest0.test284");
        org.apache.avro.SchemaBuilder.MapBuilder<org.apache.avro.Schema> schemaMapBuilder0 = org.apache.avro.SchemaBuilder.map();
        org.apache.avro.SchemaBuilder.TypeBuilder<org.apache.avro.Schema> schemaTypeBuilder1 = schemaMapBuilder0.values();
        org.apache.avro.SchemaBuilder.BytesBuilder<org.apache.avro.Schema> schemaBytesBuilder2 = schemaTypeBuilder1.bytesBuilder();
        org.apache.avro.SchemaBuilder.StringBldr<org.apache.avro.Schema> schemaStringBldr3 = schemaTypeBuilder1.stringBuilder();
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder5 = schemaTypeBuilder1.record("logicalType");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder8 = schemaRecordBuilder5.prop("float", "float");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder11 = schemaRecordBuilder5.prop("big-decimal", (java.lang.Object) "{\n  \"type\" : \"map\",\n  \"values\" : \"bytes\"\n}");
        org.apache.avro.SchemaBuilder.RecordBuilder<org.apache.avro.Schema> schemaRecordBuilder14 = schemaRecordBuilder11.prop("{\"type\":\"array\",\"items\":{\"type\":\"map\",\"values\":\"string\"}}", "{\n  \"type\" : \"map\",\n  \"values\" : \"long\"\n}");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.SchemaBuilder.FieldAssembler<org.apache.avro.Schema> schemaFieldAssembler15 = schemaRecordBuilder14.fields();
    }
}

