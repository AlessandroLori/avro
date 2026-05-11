package org.apache.avro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestSchema {

  private enum SchemaTypeCase {
    PRIM_NULL,
    PRIM_BOOLEAN,
    PRIM_INT,
    PRIM_LONG,
    PRIM_FLOAT,
    PRIM_DOUBLE,
    PRIM_BYTES,
    PRIM_STRING,

    RECORD,
    ENUM,
    ARRAY,
    MAP,
    FIXED,
    UNION,

    TYPE_ABSENT,
    TYPE_NULL,
    INVALID_UNKNOWN_TYPE,
    INVALID_CASE_TYPE,
    INVALID_EMPTY_TYPE,
    INVALID_UNICODE_TYPE
  }

  private enum NameCase {
    ABSENT,
    NULL_VALUE,
    VALID_SIMPLE,
    VALID_UNDERSCORE,
    VALID_WITH_NUMBER,
    INVALID_EMPTY,
    INVALID_STARTS_WITH_NUMBER,
    INVALID_DASH,
    INVALID_SPACE,
    INVALID_NON_STRING,
    INVALID_UNICODE
  }

  private enum NamespaceCase {
    ABSENT,
    NULL_VALUE,
    VALID_SIMPLE,
    VALID_COMPOUND,
    VALID_EMPTY,
    INVALID_STARTS_WITH_NUMBER,
    INVALID_DOUBLE_DOT,
    INVALID_DASH,
    INVALID_SPACE,
    INVALID_NON_STRING,
    INVALID_UNICODE
  }

  private enum LogicalTypeCase {
    ABSENT,
    NULL_VALUE,
    VALID_FOR_TYPE,
    INVALID_FOR_TYPE,
    UNKNOWN,
    CASE_WRONG,
    EMPTY,
    NON_STRING,
    DECIMAL_VALID,
    DECIMAL_MISSING_PRECISION,
    DECIMAL_SCALE_GREATER_THAN_PRECISION
  }

  private static final SchemaTypeCase[] NAMED_SCHEMA_TYPES = {
      SchemaTypeCase.RECORD,
      SchemaTypeCase.ENUM,
      SchemaTypeCase.FIXED
  };

  private static final NameCase[] ALL_NAME_CASES = {
      NameCase.ABSENT,
      NameCase.NULL_VALUE,
      NameCase.VALID_SIMPLE,
      NameCase.VALID_UNDERSCORE,
      NameCase.VALID_WITH_NUMBER,
      NameCase.INVALID_EMPTY,
      NameCase.INVALID_STARTS_WITH_NUMBER,
      NameCase.INVALID_DASH,
      NameCase.INVALID_SPACE,
      NameCase.INVALID_NON_STRING,
      NameCase.INVALID_UNICODE
  };

  private static final NamespaceCase[] REPRESENTATIVE_NAMESPACE_CASES = {
      NamespaceCase.ABSENT,
      NamespaceCase.VALID_COMPOUND,
      NamespaceCase.INVALID_DOUBLE_DOT,
      NamespaceCase.INVALID_NON_STRING,
      NamespaceCase.INVALID_UNICODE
  };

  private static final LogicalTypeCase[] REPRESENTATIVE_LOGICAL_TYPE_CASES = {
      LogicalTypeCase.ABSENT,
      LogicalTypeCase.VALID_FOR_TYPE,
      LogicalTypeCase.INVALID_FOR_TYPE
  };

  private final SchemaTypeCase schemaTypeCase;
  private final NameCase nameCase;
  private final NamespaceCase namespaceCase;
  private final LogicalTypeCase logicalTypeCase;
  private final boolean expectedParseValid;
  private final Schema.Type expectedSchemaType;
  private final String expectedName;
  private final String expectedNamespace;
  private final Boolean expectedLogicalTypeApplied;
  private final String expectedLogicalTypeName;

  private final String schemaJson;

  public TestSchema(
      SchemaTypeCase schemaTypeCase,
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase,
      boolean expectedParseValid,
      Schema.Type expectedSchemaType,
      String expectedName,
      String expectedNamespace,
      Boolean expectedLogicalTypeApplied,
      String expectedLogicalTypeName) {
    this.schemaTypeCase = schemaTypeCase;
    this.nameCase = nameCase;
    this.namespaceCase = namespaceCase;
    this.logicalTypeCase = logicalTypeCase;
    this.expectedParseValid = expectedParseValid;
    this.expectedSchemaType = expectedSchemaType;
    this.expectedName = expectedName;
    this.expectedNamespace = expectedNamespace;
    this.expectedLogicalTypeApplied = expectedLogicalTypeApplied;
    this.expectedLogicalTypeName = expectedLogicalTypeName;
    this.schemaJson = buildSchemaJson(schemaTypeCase, nameCase, namespaceCase, logicalTypeCase);
  }

  @Parameterized.Parameters(
      name = "{index}: schemaType={0}, name={1}, namespace={2}, logicalType={3}, parseValid={4}")
  public static Collection<Object[]> parameters() {
    List<Object[]> cases = new ArrayList<>();

    /*
     * ============================================================
     * 1. Copertura base del dominio schema_type
     * ============================================================
     */

    addComputedCase(cases, SchemaTypeCase.PRIM_NULL, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_BOOLEAN, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_INT, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_LONG, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_FLOAT, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_DOUBLE, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_BYTES, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.PRIM_STRING, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);

    addComputedCase(cases, SchemaTypeCase.RECORD, NameCase.VALID_SIMPLE, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.ENUM, NameCase.VALID_SIMPLE, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.ARRAY, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.MAP, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.FIXED, NameCase.VALID_SIMPLE, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.UNION, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);

    addComputedCase(cases, SchemaTypeCase.TYPE_ABSENT, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.TYPE_NULL, NameCase.ABSENT, NamespaceCase.ABSENT,
        LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.INVALID_UNKNOWN_TYPE, NameCase.ABSENT,
        NamespaceCase.ABSENT, LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.INVALID_CASE_TYPE, NameCase.ABSENT,
        NamespaceCase.ABSENT, LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.INVALID_EMPTY_TYPE, NameCase.ABSENT,
        NamespaceCase.ABSENT, LogicalTypeCase.ABSENT);
    addComputedCase(cases, SchemaTypeCase.INVALID_UNICODE_TYPE, NameCase.ABSENT,
        NamespaceCase.ABSENT, LogicalTypeCase.ABSENT);

    /*
     * ============================================================
     * 2. Prodotto cartesiano ridotto sui named types
     * ============================================================
     *
     * Qui name viene combinato con namespace e logicalType sui tipi in cui
     * è davvero rilevante:
     * - record
     * - enum
     * - fixed
     */

    for (SchemaTypeCase schemaType : NAMED_SCHEMA_TYPES) {
      for (NameCase name : ALL_NAME_CASES) {
        for (NamespaceCase namespace : REPRESENTATIVE_NAMESPACE_CASES) {
          for (LogicalTypeCase logicalType : REPRESENTATIVE_LOGICAL_TYPE_CASES) {
            addComputedCase(cases, schemaType, name, namespace, logicalType);
          }
        }
      }
    }

    /*
     * ============================================================
     * 3. Casi di robustezza: name su primitive schema
     * ============================================================
     *
     * name non è semanticamente rilevante sui primitivi, ma questi casi
     * verificano che il parser gestisca attributi extra senza produrre uno
     * schema incoerente.
     */

    for (NameCase name : ALL_NAME_CASES) {
      addComputedCase(cases, SchemaTypeCase.PRIM_STRING, name, NamespaceCase.ABSENT,
          LogicalTypeCase.ABSENT);
      addComputedCase(cases, SchemaTypeCase.PRIM_STRING, name, NamespaceCase.VALID_COMPOUND,
          LogicalTypeCase.VALID_FOR_TYPE);
      addComputedCase(cases, SchemaTypeCase.PRIM_STRING, name, NamespaceCase.INVALID_DOUBLE_DOT,
          LogicalTypeCase.INVALID_FOR_TYPE);
    }

    /*
     * ============================================================
     * 3-bis. Casi di robustezza: attributi extra su array e map
     * ============================================================
     *
     * array e map non sono named types. Quindi name e namespace non sono
     * semanticamente rilevanti. Questi casi verificano che il parser gestisca
     * attributi extra validi o invalidi senza produrre uno schema incoerente.
     */

    SchemaTypeCase[] collectionSchemaTypes = {
        SchemaTypeCase.ARRAY,
        SchemaTypeCase.MAP
    };

    for (SchemaTypeCase schemaType : collectionSchemaTypes) {
      addComputedCase(cases, schemaType, NameCase.VALID_SIMPLE, NamespaceCase.VALID_COMPOUND,
          LogicalTypeCase.ABSENT);

      addComputedCase(cases, schemaType, NameCase.INVALID_NON_STRING,
          NamespaceCase.INVALID_DOUBLE_DOT, LogicalTypeCase.ABSENT);

      addComputedCase(cases, schemaType, NameCase.INVALID_UNICODE, NamespaceCase.INVALID_UNICODE,
          LogicalTypeCase.ABSENT);

      addComputedCase(cases, schemaType, NameCase.VALID_SIMPLE, NamespaceCase.VALID_COMPOUND,
          LogicalTypeCase.VALID_FOR_TYPE);

      addComputedCase(cases, schemaType, NameCase.VALID_SIMPLE,
          NamespaceCase.INVALID_DOUBLE_DOT, LogicalTypeCase.INVALID_FOR_TYPE);
    }

    /*
     * ============================================================
     * 4. LogicalType sui primitivi e su fixed
     * ============================================================
     */

    SchemaTypeCase[] logicalTypeSchemaTypes = {
        SchemaTypeCase.PRIM_STRING,
        SchemaTypeCase.PRIM_INT,
        SchemaTypeCase.PRIM_LONG,
        SchemaTypeCase.PRIM_BOOLEAN,
        SchemaTypeCase.PRIM_BYTES,
        SchemaTypeCase.FIXED
    };

    for (SchemaTypeCase schemaType : logicalTypeSchemaTypes) {
      NameCase name = isNamedSchemaType(schemaType) ? NameCase.VALID_SIMPLE : NameCase.ABSENT;

      for (LogicalTypeCase logicalType : LogicalTypeCase.values()) {
        addComputedCase(cases, schemaType, name, NamespaceCase.ABSENT, logicalType);
      }
    }

    /*
     * ============================================================
     * 5. Casi multi-errore rappresentativi
     * ============================================================
     */

    addComputedCase(cases, SchemaTypeCase.INVALID_UNKNOWN_TYPE, NameCase.VALID_SIMPLE,
        NamespaceCase.VALID_COMPOUND, LogicalTypeCase.VALID_FOR_TYPE);

    addComputedCase(cases, SchemaTypeCase.INVALID_UNKNOWN_TYPE, NameCase.INVALID_DASH,
        NamespaceCase.INVALID_DOUBLE_DOT, LogicalTypeCase.INVALID_FOR_TYPE);

    addComputedCase(cases, SchemaTypeCase.TYPE_NULL, NameCase.VALID_SIMPLE,
        NamespaceCase.VALID_COMPOUND, LogicalTypeCase.VALID_FOR_TYPE);

    addComputedCase(cases, SchemaTypeCase.TYPE_ABSENT, NameCase.VALID_SIMPLE,
        NamespaceCase.VALID_COMPOUND, LogicalTypeCase.VALID_FOR_TYPE);

    return cases;
  }

  @Test
  public void parserShouldHandleSchemaTypeNameNamespaceAndLogicalTypeCombinations() {
    try {
      Schema schema = new Schema.Parser().parse(schemaJson);

      if (!expectedParseValid) {
        fail("Expected parsing to fail, but it succeeded for schema: " + schemaJson);
      }

      assertNotNull(schema);

      if (expectedSchemaType != null) {
        assertEquals(expectedSchemaType, schema.getType());
      }

      if (expectedName != null) {
        assertEquals(expectedName, schema.getName());
      }

      if (expectedNamespace != null) {
        assertEquals(expectedNamespace, schema.getNamespace());
      }

      if (expectedLogicalTypeApplied != null) {
        if (expectedLogicalTypeApplied) {
          assertNotNull(
              "Expected logicalType to be applied for schema: " + schemaJson,
              schema.getLogicalType());
          assertEquals(expectedLogicalTypeName, schema.getLogicalType().getName());
        } else {
          assertNull(
              "Expected logicalType to be ignored or not applied for schema: " + schemaJson,
              schema.getLogicalType());
        }
      }

    } catch (RuntimeException exception) {
      if (expectedParseValid) {
        fail("Expected parsing to succeed, but it failed for schema: "
            + schemaJson
            + " with exception: "
            + exception);
      }

      assertNotNull(exception);
    }
  }

  private static void addComputedCase(
      List<Object[]> cases,
      SchemaTypeCase schemaTypeCase,
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase) {

    Expected expected = expectedFor(schemaTypeCase, nameCase, namespaceCase, logicalTypeCase);

    cases.add(new Object[] {
        schemaTypeCase,
        nameCase,
        namespaceCase,
        logicalTypeCase,
        expected.parseValid,
        expected.schemaType,
        expected.name,
        expected.namespace,
        expected.logicalTypeApplied,
        expected.logicalTypeName
    });
  }

  private static Expected expectedFor(
      SchemaTypeCase schemaTypeCase,
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase) {

    if (!isValidSchemaTypeCase(schemaTypeCase)) {
      return Expected.invalid();
    }

    Schema.Type expectedSchemaType = schemaTypeFor(schemaTypeCase);

    String expectedName = null;
    String expectedNamespace = null;

    if (isNamedSchemaType(schemaTypeCase)) {
      if (!isAcceptedNameForNamedType(nameCase)) {
        return Expected.invalid();
      }

      if (!isAcceptedNamespaceForNamedType(namespaceCase)) {
        return Expected.invalid();
      }

      expectedName = actualName(nameCase);
      expectedNamespace = actualNamespace(namespaceCase);
    }

    String appliedLogicalTypeName = appliedLogicalTypeName(schemaTypeCase, logicalTypeCase);

    if (appliedLogicalTypeName != null) {
      return Expected.valid(
          expectedSchemaType,
          expectedName,
          expectedNamespace,
          true,
          appliedLogicalTypeName);
    }

    if (logicalTypeCase != LogicalTypeCase.ABSENT) {
      return Expected.valid(
          expectedSchemaType,
          expectedName,
          expectedNamespace,
          false,
          null);
    }

    return Expected.valid(
        expectedSchemaType,
        expectedName,
        expectedNamespace,
        null,
        null);
  }

  private static boolean isValidSchemaTypeCase(SchemaTypeCase schemaTypeCase) {
    switch (schemaTypeCase) {
    case PRIM_NULL:
    case PRIM_BOOLEAN:
    case PRIM_INT:
    case PRIM_LONG:
    case PRIM_FLOAT:
    case PRIM_DOUBLE:
    case PRIM_BYTES:
    case PRIM_STRING:
    case RECORD:
    case ENUM:
    case ARRAY:
    case MAP:
    case FIXED:
    case UNION:
      return true;

    case TYPE_ABSENT:
    case TYPE_NULL:
    case INVALID_UNKNOWN_TYPE:
    case INVALID_CASE_TYPE:
    case INVALID_EMPTY_TYPE:
    case INVALID_UNICODE_TYPE:
    default:
      return false;
    }
  }

  private static boolean isNamedSchemaType(SchemaTypeCase schemaTypeCase) {
    return schemaTypeCase == SchemaTypeCase.RECORD
        || schemaTypeCase == SchemaTypeCase.ENUM
        || schemaTypeCase == SchemaTypeCase.FIXED;
  }

  private static boolean isAcceptedNameForNamedType(NameCase nameCase) {
    switch (nameCase) {
    case VALID_SIMPLE:
    case VALID_UNDERSCORE:
    case VALID_WITH_NUMBER:
    case INVALID_UNICODE:
      return true;

    case ABSENT:
    case NULL_VALUE:
    case INVALID_EMPTY:
    case INVALID_STARTS_WITH_NUMBER:
    case INVALID_DASH:
    case INVALID_SPACE:
    case INVALID_NON_STRING:
    default:
      return false;
    }
  }

  private static boolean isAcceptedNamespaceForNamedType(NamespaceCase namespaceCase) {
    switch (namespaceCase) {
    case ABSENT:
    case NULL_VALUE:
    case VALID_SIMPLE:
    case VALID_COMPOUND:
    case VALID_EMPTY:
    case INVALID_NON_STRING:
    case INVALID_UNICODE:
      return true;

    case INVALID_STARTS_WITH_NUMBER:
    case INVALID_DOUBLE_DOT:
    case INVALID_DASH:
    case INVALID_SPACE:
    default:
      return false;
    }
  }

  private static Schema.Type schemaTypeFor(SchemaTypeCase schemaTypeCase) {
    switch (schemaTypeCase) {
    case PRIM_NULL:
      return Schema.Type.NULL;
    case PRIM_BOOLEAN:
      return Schema.Type.BOOLEAN;
    case PRIM_INT:
      return Schema.Type.INT;
    case PRIM_LONG:
      return Schema.Type.LONG;
    case PRIM_FLOAT:
      return Schema.Type.FLOAT;
    case PRIM_DOUBLE:
      return Schema.Type.DOUBLE;
    case PRIM_BYTES:
      return Schema.Type.BYTES;
    case PRIM_STRING:
      return Schema.Type.STRING;
    case RECORD:
      return Schema.Type.RECORD;
    case ENUM:
      return Schema.Type.ENUM;
    case ARRAY:
      return Schema.Type.ARRAY;
    case MAP:
      return Schema.Type.MAP;
    case FIXED:
      return Schema.Type.FIXED;
    case UNION:
      return Schema.Type.UNION;
    default:
      return null;
    }
  }

  private static String actualName(NameCase nameCase) {
    switch (nameCase) {
    case VALID_SIMPLE:
      return "User";
    case VALID_UNDERSCORE:
      return "_User";
    case VALID_WITH_NUMBER:
      return "User1";
    case INVALID_UNICODE:
      return "用户";
    default:
      return null;
    }
  }

  private static String actualNamespace(NamespaceCase namespaceCase) {
    switch (namespaceCase) {
    case VALID_SIMPLE:
      return "org";
    case VALID_COMPOUND:
      return "org.apache.avro";
    case INVALID_UNICODE:
      return "组织.apache";
    case ABSENT:
    case NULL_VALUE:
    case VALID_EMPTY:
    case INVALID_NON_STRING:
    default:
      return null;
    }
  }

  private static String appliedLogicalTypeName(
      SchemaTypeCase schemaTypeCase,
      LogicalTypeCase logicalTypeCase) {

    switch (logicalTypeCase) {
    case VALID_FOR_TYPE:
      switch (schemaTypeCase) {
      case PRIM_STRING:
        return "uuid";
      case PRIM_INT:
        return "date";
      case PRIM_LONG:
        return "timestamp-millis";
      case PRIM_BYTES:
      case FIXED:
        return "decimal";
      default:
        return null;
      }

    case DECIMAL_VALID:
      if (schemaTypeCase == SchemaTypeCase.PRIM_BYTES || schemaTypeCase == SchemaTypeCase.FIXED) {
        return "decimal";
      }
      return null;

    default:
      return null;
    }
  }

  private static String buildSchemaJson(
      SchemaTypeCase schemaTypeCase,
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase) {

    switch (schemaTypeCase) {
    case PRIM_NULL:
      return primitiveSchema("null", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_BOOLEAN:
      return primitiveSchema("boolean", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_INT:
      return primitiveSchema("int", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_LONG:
      return primitiveSchema("long", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_FLOAT:
      return primitiveSchema("float", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_DOUBLE:
      return primitiveSchema("double", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_BYTES:
      return primitiveSchema("bytes", nameCase, namespaceCase, logicalTypeCase);

    case PRIM_STRING:
      return primitiveSchema("string", nameCase, namespaceCase, logicalTypeCase);

    case RECORD:
      return "{"
          + "\"type\":\"record\""
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + logicalTypeFragment(SchemaTypeCase.RECORD, logicalTypeCase)
          + ",\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
          + "}";

    case ENUM:
      return "{"
          + "\"type\":\"enum\""
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + logicalTypeFragment(SchemaTypeCase.ENUM, logicalTypeCase)
          + ",\"symbols\":[\"CREATED\",\"RUNNING\",\"DONE\"]"
          + "}";

    case ARRAY:
      return "{"
          + "\"type\":\"array\""
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + logicalTypeFragment(SchemaTypeCase.ARRAY, logicalTypeCase)
          + ",\"items\":\"string\""
          + "}";

    case MAP:
      return "{"
          + "\"type\":\"map\""
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + logicalTypeFragment(SchemaTypeCase.MAP, logicalTypeCase)
          + ",\"values\":\"string\""
          + "}";

    case FIXED:
      return "{"
          + "\"type\":\"fixed\""
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + ",\"size\":16"
          + logicalTypeFragment(SchemaTypeCase.FIXED, logicalTypeCase)
          + "}";

    case UNION:
      return "[\"null\",\"string\"]";

    case TYPE_ABSENT:
      return objectWithoutType(nameCase, namespaceCase, logicalTypeCase);

    case TYPE_NULL:
      return "{"
          + "\"type\":null"
          + nameFragment(nameCase)
          + namespaceFragment(namespaceCase)
          + logicalTypeFragment(SchemaTypeCase.TYPE_NULL, logicalTypeCase)
          + "}";

    case INVALID_UNKNOWN_TYPE:
      return primitiveSchema("integer", nameCase, namespaceCase, logicalTypeCase);

    case INVALID_CASE_TYPE:
      return primitiveSchema("String", nameCase, namespaceCase, logicalTypeCase);

    case INVALID_EMPTY_TYPE:
      return primitiveSchema("", nameCase, namespaceCase, logicalTypeCase);

    case INVALID_UNICODE_TYPE:
      return primitiveSchema("字符串", nameCase, namespaceCase, logicalTypeCase);

    default:
      throw new IllegalArgumentException("Unsupported schema type case: " + schemaTypeCase);
    }
  }

  private static String primitiveSchema(
      String type,
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase) {
    return "{"
        + "\"type\":\"" + type + "\""
        + nameFragment(nameCase)
        + namespaceFragment(namespaceCase)
        + logicalTypeFragment(typeToSchemaTypeCase(type), logicalTypeCase)
        + "}";
  }

  private static SchemaTypeCase typeToSchemaTypeCase(String type) {
    switch (type) {
    case "null":
      return SchemaTypeCase.PRIM_NULL;
    case "boolean":
      return SchemaTypeCase.PRIM_BOOLEAN;
    case "int":
      return SchemaTypeCase.PRIM_INT;
    case "long":
      return SchemaTypeCase.PRIM_LONG;
    case "float":
      return SchemaTypeCase.PRIM_FLOAT;
    case "double":
      return SchemaTypeCase.PRIM_DOUBLE;
    case "bytes":
      return SchemaTypeCase.PRIM_BYTES;
    case "string":
      return SchemaTypeCase.PRIM_STRING;
    default:
      return SchemaTypeCase.INVALID_UNKNOWN_TYPE;
    }
  }

  private static String nameFragment(NameCase nameCase) {
    switch (nameCase) {
    case ABSENT:
      return "";

    case NULL_VALUE:
      return ",\"name\":null";

    case VALID_SIMPLE:
      return ",\"name\":\"User\"";

    case VALID_UNDERSCORE:
      return ",\"name\":\"_User\"";

    case VALID_WITH_NUMBER:
      return ",\"name\":\"User1\"";

    case INVALID_EMPTY:
      return ",\"name\":\"\"";

    case INVALID_STARTS_WITH_NUMBER:
      return ",\"name\":\"1User\"";

    case INVALID_DASH:
      return ",\"name\":\"User-Name\"";

    case INVALID_SPACE:
      return ",\"name\":\"User Name\"";

    case INVALID_NON_STRING:
      return ",\"name\":123";

    case INVALID_UNICODE:
      return ",\"name\":\"用户\"";

    default:
      throw new IllegalArgumentException("Unsupported name case: " + nameCase);
    }
  }

  private static String namespaceFragment(NamespaceCase namespaceCase) {
    switch (namespaceCase) {
    case ABSENT:
      return "";

    case NULL_VALUE:
      return ",\"namespace\":null";

    case VALID_SIMPLE:
      return ",\"namespace\":\"org\"";

    case VALID_COMPOUND:
      return ",\"namespace\":\"org.apache.avro\"";

    case VALID_EMPTY:
      return ",\"namespace\":\"\"";

    case INVALID_STARTS_WITH_NUMBER:
      return ",\"namespace\":\"1org.apache\"";

    case INVALID_DOUBLE_DOT:
      return ",\"namespace\":\"org..apache\"";

    case INVALID_DASH:
      return ",\"namespace\":\"org-apache.test\"";

    case INVALID_SPACE:
      return ",\"namespace\":\"org apache\"";

    case INVALID_NON_STRING:
      return ",\"namespace\":123";

    case INVALID_UNICODE:
      return ",\"namespace\":\"组织.apache\"";

    default:
      throw new IllegalArgumentException("Unsupported namespace case: " + namespaceCase);
    }
  }

  private static String logicalTypeFragment(
      SchemaTypeCase schemaTypeCase,
      LogicalTypeCase logicalTypeCase) {

    switch (logicalTypeCase) {
    case ABSENT:
      return "";

    case NULL_VALUE:
      return ",\"logicalType\":null";

    case VALID_FOR_TYPE:
      return validLogicalTypeFor(schemaTypeCase);

    case INVALID_FOR_TYPE:
      return invalidLogicalTypeFor(schemaTypeCase);

    case UNKNOWN:
      return ",\"logicalType\":\"custom-logical-type\"";

    case CASE_WRONG:
      return ",\"logicalType\":\"UUID\"";

    case EMPTY:
      return ",\"logicalType\":\"\"";

    case NON_STRING:
      return ",\"logicalType\":123";

    case DECIMAL_VALID:
      return ",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2";

    case DECIMAL_MISSING_PRECISION:
      return ",\"logicalType\":\"decimal\",\"scale\":2";

    case DECIMAL_SCALE_GREATER_THAN_PRECISION:
      return ",\"logicalType\":\"decimal\",\"precision\":2,\"scale\":10";

    default:
      throw new IllegalArgumentException("Unsupported logical type case: " + logicalTypeCase);
    }
  }

  private static String validLogicalTypeFor(SchemaTypeCase schemaTypeCase) {
    switch (schemaTypeCase) {
    case PRIM_STRING:
      return ",\"logicalType\":\"uuid\"";

    case PRIM_INT:
      return ",\"logicalType\":\"date\"";

    case PRIM_LONG:
      return ",\"logicalType\":\"timestamp-millis\"";

    case PRIM_BYTES:
    case FIXED:
      return ",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2";

    default:
      return ",\"logicalType\":\"uuid\"";
    }
  }

  private static String invalidLogicalTypeFor(SchemaTypeCase schemaTypeCase) {
    switch (schemaTypeCase) {
    case PRIM_STRING:
      return ",\"logicalType\":\"date\"";

    case PRIM_INT:
      return ",\"logicalType\":\"uuid\"";

    case PRIM_LONG:
      return ",\"logicalType\":\"date\"";

    case PRIM_BOOLEAN:
      return ",\"logicalType\":\"timestamp-millis\"";

    case PRIM_BYTES:
      return ",\"logicalType\":\"uuid\"";

    case FIXED:
      return ",\"logicalType\":\"date\"";

    default:
      return ",\"logicalType\":\"date\"";
    }
  }

  private static String objectWithoutType(
      NameCase nameCase,
      NamespaceCase namespaceCase,
      LogicalTypeCase logicalTypeCase) {

    String fragments = nameFragment(nameCase)
        + namespaceFragment(namespaceCase)
        + logicalTypeFragment(SchemaTypeCase.TYPE_ABSENT, logicalTypeCase);

    if (fragments.startsWith(",")) {
      fragments = fragments.substring(1);
    }

    return "{" + fragments + "}";
  }

  private static class Expected {
    private final boolean parseValid;
    private final Schema.Type schemaType;
    private final String name;
    private final String namespace;
    private final Boolean logicalTypeApplied;
    private final String logicalTypeName;

    private Expected(
        boolean parseValid,
        Schema.Type schemaType,
        String name,
        String namespace,
        Boolean logicalTypeApplied,
        String logicalTypeName) {
      this.parseValid = parseValid;
      this.schemaType = schemaType;
      this.name = name;
      this.namespace = namespace;
      this.logicalTypeApplied = logicalTypeApplied;
      this.logicalTypeName = logicalTypeName;
    }

    private static Expected invalid() {
      return new Expected(false, null, null, null, null, null);
    }

    private static Expected valid(
        Schema.Type schemaType,
        String name,
        String namespace,
        Boolean logicalTypeApplied,
        String logicalTypeName) {
      return new Expected(true, schemaType, name, namespace, logicalTypeApplied, logicalTypeName);
    }
  }
}
