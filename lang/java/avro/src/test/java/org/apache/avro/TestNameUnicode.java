package org.apache.avro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

import org.apache.avro.SchemaCompatibility.SchemaCompatibilityType;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.junit.Test;

public class TestNameUnicode {

  private static final String UNICODE_NAME = "用户";
  private static final String ASCII_NAME = "User";

  private static final String UNICODE_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"},"
      + "{\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}";

  private static final String ASCII_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"" + ASCII_NAME + "\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"},"
      + "{\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}";

  private static final String ASCII_RECORD_WITH_UNICODE_ALIAS_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"" + ASCII_NAME + "\","
      + "\"aliases\":[\"" + UNICODE_NAME + "\"],"
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"},"
      + "{\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}";

  private static final String UNICODE_ENUM_SCHEMA_JSON = "{"
      + "\"type\":\"enum\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"symbols\":[\"CREATED\",\"RUNNING\",\"DONE\"]"
      + "}";

  private static final String UNICODE_FIXED_SCHEMA_JSON = "{"
      + "\"type\":\"fixed\","
      + "\"name\":\"" + UNICODE_NAME + "\","
      + "\"size\":16"
      + "}";

  /*
   * Verifica base sul record:
   * il parser accetta name Unicode e lo conserva dentro Schema.getName()
   * e Schema.getFullName().
   */
  @Test
  public void parserShouldPreserveUnicodeRecordName() {
    Schema schema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    assertNotNull(schema);
    assertEquals(Schema.Type.RECORD, schema.getType());
    assertEquals(UNICODE_NAME, schema.getName());
    assertEquals(UNICODE_NAME, schema.getFullName());
  }

  /*
   * Verifica base sull'enum:
   * il name Unicode viene accettato anche per un named type enum.
   */
  @Test
  public void parserShouldPreserveUnicodeEnumName() {
    Schema schema = new Schema.Parser().parse(UNICODE_ENUM_SCHEMA_JSON);

    assertNotNull(schema);
    assertEquals(Schema.Type.ENUM, schema.getType());
    assertEquals(UNICODE_NAME, schema.getName());
    assertEquals(UNICODE_NAME, schema.getFullName());
  }

  /*
   * Verifica base sul fixed:
   * il name Unicode viene accettato anche per un named type fixed.
   */
  @Test
  public void parserShouldPreserveUnicodeFixedName() {
    Schema schema = new Schema.Parser().parse(UNICODE_FIXED_SCHEMA_JSON);

    assertNotNull(schema);
    assertEquals(Schema.Type.FIXED, schema.getType());
    assertEquals(UNICODE_NAME, schema.getName());
    assertEquals(UNICODE_NAME, schema.getFullName());
  }

  /*
   * Verifica round-trip:
   * JSON schema -> Schema -> JSON serializzato -> Schema.
   *
   * Se questo test passa, significa che il name Unicode non viene perso,
   * corrotto o reso non parsabile durante la serializzazione dello schema.
   */
  @Test
  public void schemaShouldRoundTripWithUnicodeName() {
    Schema original = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    String serializedSchema = original.toString();
    Schema reparsed = new Schema.Parser().parse(serializedSchema);

    assertEquals(original, reparsed);
    assertEquals(UNICODE_NAME, reparsed.getName());
    assertEquals(UNICODE_NAME, reparsed.getFullName());
  }

  /*
   * Verifica canonical form:
   * il name Unicode entra nella rappresentazione canonica dello schema.
   *
   * Se Avro non riuscisse a normalizzare il nome Unicode, qui potrebbe
   * emergere un errore.
   */
  @Test
  public void canonicalFormShouldHandleUnicodeName() {
    Schema schema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    String parsingForm = SchemaNormalization.toParsingForm(schema);

    assertNotNull(parsingForm);
    assertEquals(true, parsingForm.contains(UNICODE_NAME));
  }

  /*
   * Verifica fingerprint:
   * Avro deve riuscire a calcolare un fingerprint stabile anche quando il
   * nome del record contiene caratteri Unicode.
   */
  @Test
  public void fingerprintShouldHandleUnicodeName() {
    Schema schema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    long firstFingerprint = SchemaNormalization.parsingFingerprint64(schema);
    long secondFingerprint = SchemaNormalization.parsingFingerprint64(schema);

    assertEquals(firstFingerprint, secondFingerprint);
  }

  /*
   * Verifica che due schemi equivalenti con lo stesso name Unicode risultino
   * compatibili.
   */
  @Test
  public void compatibilityShouldAcceptSameUnicodeName() {
    Schema writerSchema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);
    Schema readerSchema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(readerSchema, writerSchema);

    assertEquals(SchemaCompatibilityType.COMPATIBLE, compatibility.getType());
  }

  /*
   * Verifica che il nome Unicode sia semanticamente rilevante.
   *
   * Writer schema: name = "用户"
   * Reader schema: name = "User"
   *
   * Se Avro considera davvero il name come identità del record, i due schemi
   * non dovrebbero essere compatibili.
   */
  @Test
  public void compatibilityShouldDetectDifferentNameBetweenUnicodeAndAsciiRecord() {
    Schema writerSchema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);
    Schema readerSchema = new Schema.Parser().parse(ASCII_RECORD_SCHEMA_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(readerSchema, writerSchema);

    assertEquals(SchemaCompatibilityType.INCOMPATIBLE, compatibility.getType());
  }

  /*
   * Verifica alias Unicode.
   *
   * Writer schema: name = "用户"
   * Reader schema: name = "User", aliases = ["用户"]
   *
   * Questo test controlla se Avro riesce a usare un alias Unicode per
   * risolvere la compatibilità tra writer e reader.
   */
  @Test
  public void compatibilityShouldAcceptUnicodeNameThroughAlias() {
    Schema writerSchema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);
    Schema readerSchema = new Schema.Parser().parse(ASCII_RECORD_WITH_UNICODE_ALIAS_SCHEMA_JSON);

    SchemaCompatibility.SchemaPairCompatibility compatibility =
        SchemaCompatibility.checkReaderWriterCompatibility(readerSchema, writerSchema);

    assertEquals(SchemaCompatibilityType.COMPATIBLE, compatibility.getType());
  }

  /*
   * Verifica che il name Unicode influenzi realmente la fingerprint.
   *
   * Due record strutturalmente uguali ma con name diverso devono produrre
   * canonical form/fingerprint diversi.
   */
  @Test
  public void fingerprintShouldChangeWhenRecordNameChanges() {
    Schema unicodeSchema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);
    Schema asciiSchema = new Schema.Parser().parse(ASCII_RECORD_SCHEMA_JSON);

    long unicodeFingerprint = SchemaNormalization.parsingFingerprint64(unicodeSchema);
    long asciiFingerprint = SchemaNormalization.parsingFingerprint64(asciiSchema);

    assertNotEquals(unicodeFingerprint, asciiFingerprint);
  }

  /*
   * Verifica GenericRecord I/O in memoria.
   *
   * Qui non controlliamo solo il parsing dello schema, ma anche se lo schema
   * con name Unicode può essere usato per scrivere e leggere dati reali tramite
   * GenericDatumWriter e GenericDatumReader.
   */
  @Test
  public void genericRecordShouldSerializeAndDeserializeWithUnicodeName() throws Exception {
    Schema schema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    GenericRecord inputRecord = new GenericData.Record(schema);
    inputRecord.put("id", 7);
    inputRecord.put("label", "test-value");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);

    GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
    writer.write(inputRecord, encoder);
    encoder.flush();

    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(inputStream, null);

    GenericDatumReader<GenericRecord> reader = new GenericDatumReader<>(schema);
    GenericRecord outputRecord = reader.read(null, decoder);

    assertEquals(7, outputRecord.get("id"));
    assertEquals("test-value", outputRecord.get("label").toString());
  }

  /*
   * Verifica DataFileWriter/DataFileReader.
   *
   * Questo test scrive un file Avro vero usando uno schema con name Unicode,
   * poi lo rilegge e verifica che:
   * - i dati siano corretti;
   * - lo schema salvato nel file conservi il name Unicode.
   */
  @Test
  public void dataFileShouldPreserveUnicodeNameInEmbeddedSchema() throws Exception {
    Schema schema = new Schema.Parser().parse(UNICODE_RECORD_SCHEMA_JSON);

    GenericRecord inputRecord = new GenericData.Record(schema);
    inputRecord.put("id", 42);
    inputRecord.put("label", "from-file");

    File tempFile = Files.createTempFile("avro-unicode-name-", ".avro").toFile();
    tempFile.deleteOnExit();

    try (DataFileWriter<GenericRecord> fileWriter =
             new DataFileWriter<>(new GenericDatumWriter<GenericRecord>(schema))) {
      fileWriter.create(schema, tempFile);
      fileWriter.append(inputRecord);
    }

    try (DataFileReader<GenericRecord> fileReader =
             new DataFileReader<>(tempFile, new GenericDatumReader<GenericRecord>())) {

      Schema embeddedSchema = fileReader.getSchema();

      assertEquals(UNICODE_NAME, embeddedSchema.getName());
      assertEquals(UNICODE_NAME, embeddedSchema.getFullName());

      GenericRecord outputRecord = fileReader.next();

      assertEquals(42, outputRecord.get("id"));
      assertEquals("from-file", outputRecord.get("label").toString());
    }
  }
}
