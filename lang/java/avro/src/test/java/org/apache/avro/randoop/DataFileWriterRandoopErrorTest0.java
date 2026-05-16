package org.apache.avro.randoop;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataFileWriterRandoopErrorTest0 {

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
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test01");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("hi!", "hi!", "hi!", (int) ' ');
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test02");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("", "", "#", false);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test03");
        java.lang.String[] strArray11 = new java.lang.String[] { "hi!", "#", "[]", "[]", "enum", "\uff00\uff01\uff0a", "hi!", "#" };
        java.util.ArrayList<java.lang.String> strList12 = new java.util.ArrayList<java.lang.String>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList12, strArray11);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema14 = org.apache.avro.Schema.createEnum("fixed", "\u6423\u6401\u640a", "[]", (java.util.List<java.lang.String>) strList12);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test04");
        java.lang.String[] strArray4 = new java.lang.String[] { "enum" };
        java.util.ArrayList<java.lang.String> strList5 = new java.util.ArrayList<java.lang.String>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList5, strArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createEnum("hi!", "\u6423\u6401\u640a", "#", (java.util.List<java.lang.String>) strList5, "#");
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test05");
        java.lang.String[] strArray5 = new java.lang.String[] { "[ ]", "enum" };
        java.util.ArrayList<java.lang.String> strList6 = new java.util.ArrayList<java.lang.String>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList6, strArray5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createEnum("[]", "\uff00\uff01\uff0a", "", (java.util.List<java.lang.String>) strList6);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test06");
        java.lang.String[] strArray7 = new java.lang.String[] { "enum", "hi!", "\u6423\u6401\u640a", "union[]" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createEnum("#", "hi!", "\u6423\u6401\u640a", (java.util.List<java.lang.String>) strList8, "enum");
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test07");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("[ ]", "array", "[]", false);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test08");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("org.apache.avro.file.DataFileWriter$AppendWriteException", "#", "\uff00\uff01\uff0a", (int) (short) 100);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test09");
        java.lang.String[] strArray7 = new java.lang.String[] { "\u6423\u6401\u640a", "#", "enum", "\u2300\u2301\u230a\u2361" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createEnum("[ ]", "fixed", "[]", (java.util.List<java.lang.String>) strList8, "");
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test10");
        java.lang.String[] strArray14 = new java.lang.String[] { "\uff00\uff01\uff0a", "union[]", "array", "\u6423\u6401\u640a", "\u2300\u2301\u230a\u2361", "array", "array", "\uff00\uff01\uff0a", "{\"type\":\"record\",\"fields\":[]}", "", "union[]" };
        java.util.ArrayList<java.lang.String> strList15 = new java.util.ArrayList<java.lang.String>();
        boolean boolean16 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList15, strArray14);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema18 = org.apache.avro.Schema.createEnum("union[]", "union[]", "hi!", (java.util.List<java.lang.String>) strList15, "org.apache.avro.file.DataFileWriter$AppendWriteException");
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test11");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord("fixed", "{\"type\":\"record\",\"fields\":[]}", "\uff00\uff01\uff0a", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test12");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("[]", "\u6423\u6401\u640a", "{\"type\":\"record\",\"fields\":[]}", (-1));
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test13");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("#", "\u6423\u6401\u640a", "#", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test14");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("#", "fixed", "{\"type\":\"record\",\"fields\":[]}", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test15");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("#", "array", "\u6423\u6401\u640a", false);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test16");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("\u6100\u6101\u610a\u6161", "array", "", false);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test17");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createRecord("hi!", "union", "org.apache.avro.file.DataFileWriter$AppendWriteException", true);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test18");
        java.lang.String[] strArray12 = new java.lang.String[] { "\u6100\u6101\u610a\u6161", "array", "union", "#", "union[]", "[]", "\u2300\u2301\u230a\u2361", "enum", "[ ]" };
        java.util.ArrayList<java.lang.String> strList13 = new java.util.ArrayList<java.lang.String>();
        boolean boolean14 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList13, strArray12);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createEnum("org.apache.avro.file.DataFileWriter$AppendWriteException", "[ ]", "\uff00\uff01\uff0a", (java.util.List<java.lang.String>) strList13, "\u6423\u6401\u640a");
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test19");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord("\u2300\u2301\u230a\u2361", "union", "\u6100\u6101\u610a\u6161", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test20");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("#\001\n", "", "org.apache.avro.file.DataFileWriter$AppendWriteException", (-1));
    }

    @Test
    public void test21() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test21");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("#", "enum", "{\"type\":\"record\",\"fields\":[]}", (int) (short) 0);
    }

    @Test
    public void test22() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test22");
        org.apache.avro.Schema[] schemaArray1 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList2 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean3 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList2, schemaArray1);
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList2);
        java.lang.String str5 = schema4.getDoc();
        boolean boolean6 = schema4.hasProps();
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createArray(schema4);
        org.apache.avro.Schema[] schemaArray8 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList9 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList9, schemaArray8);
        org.apache.avro.Schema schema11 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList9);
        java.lang.String str12 = schema11.getDoc();
        org.apache.avro.Schema[] schemaArray13 = new org.apache.avro.Schema[] {};
        java.util.ArrayList<org.apache.avro.Schema> schemaList14 = new java.util.ArrayList<org.apache.avro.Schema>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema>) schemaList14, schemaArray13);
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createUnion((java.util.List<org.apache.avro.Schema>) schemaList14);
        java.lang.String str17 = schema16.getDoc();
        boolean boolean18 = schema16.hasProps();
        schema11.addAllProps((org.apache.avro.JsonProperties) schema16);
        org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>> schemaListGenericDatumWriter20 = new org.apache.avro.generic.GenericDatumWriter<java.util.AbstractList<org.apache.avro.Schema>>(schema16);
        java.util.List<org.apache.avro.Schema> schemaList21 = schema16.getTypes();
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createUnion(schemaList21);
        schema7.putAll((org.apache.avro.JsonProperties) schema22);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema.Field field25 = new org.apache.avro.Schema.Field("\u2300\u2301\u230a\u2361", schema7, "[]");
    }

    @Test
    public void test23() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test23");
        java.lang.String[] strArray19 = new java.lang.String[] { "#", "org.apache.avro.file.DataFileWriter$AppendWriteException", "[]", "#", "#\001\n", "union", "", "", "#", "array", "hi!", "", "array", "org.apache.avro.file.DataFileWriter$AppendWriteException", "{\"type\":\"record\",\"fields\":[]}", "union[]" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema23 = org.apache.avro.Schema.createEnum("\uff00\uff01\uff0a", "array", "hi!", (java.util.List<java.lang.String>) strList20, "\u6100\u6101\u610a\u6161");
    }

    @Test
    public void test24() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test24");
        java.lang.String[] strArray19 = new java.lang.String[] { "org.apache.avro.file.DataFileWriter$AppendWriteException", "\uff00\uff01\uff0a", "#\001\n", "{\"type\":\"record\",\"fields\":[]}", "\u6100\u6101\u610a\u6161", "hi!", "\u6100\u6101\u610a\u6161", "array", "\u6100\u6101\u610a\u6161", "\u6423\u6401\u640a", "union[]", "#\001\n", "org.apache.avro.file.DataFileWriter$AppendWriteException", "org.apache.avro.file.DataFileWriter$AppendWriteException", "[ ]", "[]" };
        java.util.ArrayList<java.lang.String> strList20 = new java.util.ArrayList<java.lang.String>();
        boolean boolean21 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList20, strArray19);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema22 = org.apache.avro.Schema.createEnum("enum", "fixed", "", (java.util.List<java.lang.String>) strList20);
    }

    @Test
    public void test25() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test25");
        java.lang.String[] strArray20 = new java.lang.String[] { "hi!", "array", "union", "\u2300\u2301\u230a\u2361", "{\"type\":\"record\",\"fields\":[]}", "enum", "union[]", "fixed", "[ ]", "", "union", "hi!", "{\"type\":\"record\",\"fields\":[]}", "enum", "union[]", "org.apache.avro.file.DataFileWriter$AppendWriteException", "org.apache.avro.file.DataFileWriter$AppendWriteException" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema23 = org.apache.avro.Schema.createEnum("union", "", "[ ]", (java.util.List<java.lang.String>) strList21);
    }

    @Test
    public void test26() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test26");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("hi!", "fixed", "map", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test27() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test27");
        java.lang.String[] strArray16 = new java.lang.String[] { "\u6100\u6101\u610a\u6161", "\u6423\u6401\u640a", "fixed", "org.apache.avro.file.DataFileWriter$AppendWriteException", "\u6100\u6101\u610a\u6161", "fixed", "\uff00\uff01\uff0a", "array", "{\"type\":\"record\",\"fields\":[]}", "#\001\n", "\uff00\uff01\uff0a", "fixed", "union" };
        java.util.ArrayList<java.lang.String> strList17 = new java.util.ArrayList<java.lang.String>();
        boolean boolean18 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList17, strArray16);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema20 = org.apache.avro.Schema.createEnum("union[]", "#", "{\"type\":\"record\",\"fields\":[]}", (java.util.List<java.lang.String>) strList17, "fixed");
    }

    @Test
    public void test28() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test28");
        java.lang.String[] strArray20 = new java.lang.String[] { "union", "union[]", "array", "union", "map", "\u6100\u6101\u610a\u6161", "org.apache.avro.file.DataFileWriter$AppendWriteException", "enum", "org.apache.avro.file.DataFileWriter$AppendWriteException", "#\001\n", "#", "", "fixed", "#", "union[]", "fixed", "enum" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema24 = org.apache.avro.Schema.createEnum("\u6423\u6401\u640a", "[]", "fixed", (java.util.List<java.lang.String>) strList21, "enum");
    }

    @Test
    public void test29() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test29");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("fixed", "[ ]", "#", (int) (byte) 100);
    }

    @Test
    public void test30() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test30");
        java.lang.String[] strArray20 = new java.lang.String[] { "map", "union[]", "{\"type\":\"record\",\"fields\":[]}", "enum", "", "union[]", "enum", "map", "\u2300\u2301\u230a\u2361", "fixed", "fixed", "org.apache.avro.file.DataFileWriter$AppendWriteException", "\u2301", "\u2301", "hi!", "fixed", "union[]" };
        java.util.ArrayList<java.lang.String> strList21 = new java.util.ArrayList<java.lang.String>();
        boolean boolean22 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList21, strArray20);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema24 = org.apache.avro.Schema.createEnum("fixed", "#\001\n", "\u6100\u6101\u610a\u6161", (java.util.List<java.lang.String>) strList21, "[ ]");
    }

    @Test
    public void test31() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test31");
        java.lang.String[] strArray13 = new java.lang.String[] { "{\"type\":\"record\",\"fields\":[]}", "[]", "{\"type\":\"record\",\"fields\":[]}", "\u2301", "#\001\n", "array", "array", "hi!", "", "#" };
        java.util.ArrayList<java.lang.String> strList14 = new java.util.ArrayList<java.lang.String>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList14, strArray13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createEnum("\001", "union[]", "\u6100\u6101\u610a\u6161", (java.util.List<java.lang.String>) strList14);
    }

    @Test
    public void test32() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test32");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        org.apache.avro.Schema schema8 = org.apache.avro.Schema.createRecord((java.util.List<org.apache.avro.Schema.Field>) fieldList5);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema9 = org.apache.avro.Schema.createRecord("\001", "map", "fixed", true, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test33() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test33");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("", "", "", 0);
    }

    @Test
    public void test34() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test34");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("\u2300\u2301\u230a\u2361", "#\001\n", "#\001\n", 10);
    }

    @Test
    public void test35() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test35");
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema4 = org.apache.avro.Schema.createFixed("\u3400\u3401\u340a", "\u2300\u2301\u230a\u2361", "\001", (int) (byte) 100);
    }

    @Test
    public void test36() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test36");
        java.lang.String[] strArray13 = new java.lang.String[] { "\u2301", "[]", "hi!", "#", "map", "#", "", "union", "\u2300\u2301\u230a\u2361", "hi!" };
        java.util.ArrayList<java.lang.String> strList14 = new java.util.ArrayList<java.lang.String>();
        boolean boolean15 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList14, strArray13);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema16 = org.apache.avro.Schema.createEnum("[ ]", "fixed", "", (java.util.List<java.lang.String>) strList14);
    }

    @Test
    public void test37() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test37");
        org.apache.avro.Schema.Field[] fieldArray4 = new org.apache.avro.Schema.Field[] {};
        java.util.ArrayList<org.apache.avro.Schema.Field> fieldList5 = new java.util.ArrayList<org.apache.avro.Schema.Field>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<org.apache.avro.Schema.Field>) fieldList5, fieldArray4);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema7 = org.apache.avro.Schema.createRecord("{\"type\":\"map\",\"values\":[]}", "{\"type\":\"record\",\"fields\":[]}", "map", false, (java.util.List<org.apache.avro.Schema.Field>) fieldList5);
    }

    @Test
    public void test38() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "DataFileWriterRandoopErrorTest0.test38");
        java.lang.String[] strArray11 = new java.lang.String[] { "\u2300\u2301\u230a\u2361", "[ ]", "\000", "\uff00\uff01\uff0a", "fixed", "array", "\u6423\u6401\u640a", "\u2301" };
        java.util.ArrayList<java.lang.String> strList12 = new java.util.ArrayList<java.lang.String>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList12, strArray11);
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        org.apache.avro.Schema schema15 = org.apache.avro.Schema.createEnum("", "\u3400\u3401\u340a", "org.apache.avro.file.DataFileWriter$AppendWriteException: org.apache.avro.file.DataFileWriter$AppendWriteException", (java.util.List<java.lang.String>) strList12, "\u6400\u6401\u640a");
    }
}

