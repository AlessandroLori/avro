package org.apache.avro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestDataFileWriter {

  private static final String RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"User\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"},"
      + "{\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}";

  private static final String INT_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"IntUser\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}";

  private static final String STRING_RECORD_SCHEMA_JSON = "{"
      + "\"type\":\"record\","
      + "\"name\":\"LabelRecord\","
      + "\"fields\":["
      + "{\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}";

  private static final String NULLABLE_STRING_SCHEMA_JSON = "[\"null\",\"string\"]";
  private static final String UNICODE_LABEL = "utente-用户";

  @Mock
  private DatumWriter<GenericRecord> datumWriter;

  /*
   * Verifica di interazione con @Mock Mockito.
   *
   * Obiettivo:
   * isolare DataFileWriter dalla serializzazione reale e controllare che
   * append(datum) deleghi la scrittura al DatumWriter ricevuto nel costruttore.
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_open_writer: writer aperto con create(...)
   * - valid_datum_writer_single_append: DatumWriter.write(...) completa
   * - valid_record_datum: record conforme allo schema
   */
  @Test
  public void appendShouldDelegateToDatumWriter() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = newIntRecord(schema, 1);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(record);
    }

    verify(datumWriter, times(1)).write(same(record), any(Encoder.class));
  }

  /*
   * Verifica di interazione con mock Mockito su append multiple.
   *
   * Obiettivo:
   * controllare che ogni chiamata valida ad append(datum) produca una chiamata
   * distinta a DatumWriter.write(...).
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_already_written_writer: writer con un datum già scritto
   * - valid_datum_writer_multiple_append: writer valido su due append
   * - valid_multiple_record_datum: due record validi consecutivi
   */
  @Test
  public void appendShouldCallDatumWriterForEachDatum() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord firstRecord = newIntRecord(schema, 1);
    GenericRecord secondRecord = newIntRecord(schema, 2);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(firstRecord);
      dataFileWriter.append(secondRecord);
    }

    verify(datumWriter, times(1)).write(same(firstRecord), any(Encoder.class));
    verify(datumWriter, times(1)).write(same(secondRecord), any(Encoder.class));
    verify(datumWriter, times(2)).write(any(GenericRecord.class), any(Encoder.class));
  }

  /*
   * Verifica di errore controllato con mock Mockito dichiarato tramite @Mock.
   *
   * Obiettivo:
   * simulare una failure del DatumWriter durante la serializzazione del datum.
   * Il mock è necessario perché permette di forzare l'errore esattamente nella
   * dipendenza usata da DataFileWriter.append(...), senza coinvolgere un datum
   * invalido o un file system guasto.
   *
   * Classe di equivalenza: invalid_instance.
   * Partizione coperta:
   * - failing_datum_writer_first_append: DatumWriter.write(...) fallisce alla
   *   prima append.
   */
  @Test
  public void appendShouldThrowWhenDatumWriterFails() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = newIntRecord(schema, 1);

    doThrow(new IOException("forced datum writer failure"))
        .when(datumWriter)
        .write(same(record), any(Encoder.class));

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(schema, outputStream);

      try {
        dataFileWriter.append(record);
        fail("Expected append to fail when DatumWriter.write(...) throws IOException.");
      } catch (DataFileWriter.AppendWriteException exception) {
        assertNotNull(exception);
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause().getMessage().contains("forced datum writer failure"));
      }
    }
  }

  /*
   * Verifica di recovery dopo AppendWriteException con mock Mockito.
   *
   * Obiettivo:
   * controllare il caso documentato in cui una failure durante la scrittura del
   * datum non deve rendere il writer definitivamente inutilizzabile.
   *
   * Classe di equivalenza: invalid_instance -> valid_instance.
   * Partizione coperta:
   * - failing_then_recovering_datum_writer: prima write fallisce, seconda write
   *   completa senza eccezioni.
   */
  @Test
  public void appendShouldAllowAnotherAppendAfterAppendWriteException() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord firstRecord = newIntRecord(schema, 1);
    GenericRecord secondRecord = newIntRecord(schema, 2);

    doThrow(new IOException("forced first append failure"))
        .doNothing()
        .when(datumWriter)
        .write(any(GenericRecord.class), any(Encoder.class));

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(schema, outputStream);

      try {
        dataFileWriter.append(firstRecord);
        fail("Expected the first append to fail.");
      } catch (DataFileWriter.AppendWriteException exception) {
        assertNotNull(exception);
      }

      dataFileWriter.append(secondRecord);
    }

    verify(datumWriter, times(2)).write(any(GenericRecord.class), any(Encoder.class));
  }

  /*
   * Verifica funzionale su datum record valido.
   *
   * Obiettivo:
   * usare un GenericDatumWriter reale per verificare che append accetti un
   * record conforme allo schema.
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_record_schema_minimal
   * - valid_record_minimal_values
   * Boundary value: id = 0.
   */
  @Test
  public void appendShouldAcceptValidRecordDatum() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = newIntRecord(schema, 0);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(record);
    }

    assertTrue("Expected the Avro container bytes to be written.", outputStream.size() > 0);
  }

  /*
   * Verifica boundary values per campo int.
   *
   * Obiettivo:
   * controllare che append accetti i valori limite del tipo int quando il datum
   * è conforme allo schema.
   *
   * Classe di equivalenza: valid_instance.
   * Partizione coperta: valid_record_int_min / valid_record_int_max.
   * Boundary values: Integer.MIN_VALUE e Integer.MAX_VALUE.
   */
  @Test
  public void appendShouldAcceptBoundaryIntegerValuesInRecord() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord minRecord = newIntRecord(schema, Integer.MIN_VALUE);
    GenericRecord maxRecord = newIntRecord(schema, Integer.MAX_VALUE);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(minRecord);
      dataFileWriter.append(maxRecord);
    }

    assertTrue("Expected the Avro container bytes to be written.", outputStream.size() > 0);
  }

  /*
   * Verifica boundary values per campo string.
   *
   * Obiettivo:
   * controllare che i dati stringa possano essere vuoti o Unicode. A differenza
   * dei name/namespace Avro, qui Unicode è un valore valido del dato stringa.
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_record_empty_string
   * - valid_record_unicode_string
   * Boundary values: "" e "utente-用户".
   */
  @Test
  public void appendShouldAcceptUnicodeStringData() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    GenericRecord emptyStringRecord = newStringRecord(schema, "");
    GenericRecord unicodeStringRecord = newStringRecord(schema, UNICODE_LABEL);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(emptyStringRecord);
      dataFileWriter.append(unicodeStringRecord);
    }

    assertTrue("Expected the Avro container bytes to be written.", outputStream.size() > 0);
  }

  /*
   * Verifica datum invalido: campo required null.
   *
   * Obiettivo:
   * controllare che append rifiuti un record in cui un campo string non nullable
   * viene valorizzato a null.
   *
   * Classe di equivalenza: invalid_instance.
   * Partizione coperta: invalid_record_null_required_field.
   * Boundary value: label = null con schema string non nullable.
   */
  @Test
  public void appendShouldRejectNullForRequiredField() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    GenericRecord record = new GenericData.Record(schema);
    record.put("label", null);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      assertAppendFails(dataFileWriter, record);
    }
  }

  /*
   * Verifica datum invalido: tipo del campo non conforme allo schema.
   *
   * Obiettivo:
   * controllare che append rifiuti un record in cui un campo int viene
   * valorizzato con una stringa.
   *
   * Classe di equivalenza: invalid_instance.
   * Partizione coperta: invalid_record_wrong_field_type.
   * Boundary value: id = "not-int" con schema id:int.
   */
  @Test
  public void appendShouldRejectWrongFieldType() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = new GenericData.Record(schema);
    record.put("id", "not-int");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      assertAppendFails(dataFileWriter, record);
    }
  }

  /*
   * Verifica datum null valido quando lo schema è una union nullable.
   *
   * Obiettivo:
   * controllare che append accetti null quando lo schema include esplicitamente
   * il branch null.
   *
   * Classe di equivalenza: null_instance.
   * Partizione coperta: valid_null_datum.
   * Boundary value: schema ["null", "string"], datum = null.
   */
  @Test
  public void appendShouldAcceptNullWhenSchemaIsNullableUnion() throws Exception {
    Schema schema = parseSchema(NULLABLE_STRING_SCHEMA_JSON);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<Object> dataFileWriter = newGenericObjectDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(null);
      dataFileWriter.append("value");
    }

    assertTrue("Expected the Avro container bytes to be written.", outputStream.size() > 0);
  }

  /*
   * Verifica datum null invalido quando lo schema non è nullable.
   *
   * Obiettivo:
   * controllare che append rifiuti null quando lo schema è string e non include
   * il tipo null.
   *
   * Classe di equivalenza: null_instance.
   * Partizione coperta: invalid_null_datum.
   * Boundary value: schema "string", datum = null.
   */
  @Test
  public void appendShouldRejectNullWhenSchemaIsNonNullable() throws Exception {
    Schema schema = Schema.create(Schema.Type.STRING);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<Object> dataFileWriter = newGenericObjectDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      assertAppendFails(dataFileWriter, null);
    }
  }

  /*
   * Test di integrazione: DataFileWriter + GenericDatumWriter + DataFileReader
   * + GenericDatumReader.
   *
   * Obiettivo:
   * verificare che un datum scritto tramite append venga realmente salvato in
   * un file Avro leggibile e che lo schema embedded sia quello atteso.
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_temp_file_output
   * - valid_record_schema_multi_field
   * - valid_record_positive_values
   */
  @Test
  public void dataFileShouldBeReadableAfterAppend() throws Exception {
    Schema schema = parseSchema(RECORD_SCHEMA_JSON);
    GenericRecord inputRecord = newUserRecord(schema, 7, "Alice");

    File tempFile = Files.createTempFile("avro-data-file-writer-", ".avro").toFile();
    tempFile.deleteOnExit();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(inputRecord);
    }

    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(tempFile, new GenericDatumReader<GenericRecord>())) {
      assertEquals(schema, dataFileReader.getSchema());
      assertTrue(dataFileReader.hasNext());

      GenericRecord outputRecord = dataFileReader.next();

      assertEquals(7, outputRecord.get("id"));
      assertEquals("Alice", outputRecord.get("label").toString());
      assertFalse("Expected only one record in the file.", dataFileReader.hasNext());
    }
  }

  /*
   * Test di integrazione su append multiple.
   *
   * Obiettivo:
   * verificare che due record scritti con due chiamate consecutive ad append
   * siano entrambi leggibili dal file Avro prodotto e che l'ordine sia
   * preservato.
   *
   * Classe di equivalenza: valid_instance.
   * Partizioni coperte:
   * - valid_multiple_append_writer
   * - valid_temp_file_output_with_multiple_records
   * - valid_multiple_record_datum
   */
  @Test
  public void dataFileShouldPreserveMultipleAppendedRecords() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord firstRecord = newIntRecord(schema, 1);
    GenericRecord secondRecord = newIntRecord(schema, 2);

    File tempFile = Files.createTempFile("avro-data-file-writer-multiple-", ".avro").toFile();
    tempFile.deleteOnExit();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(firstRecord);
      dataFileWriter.append(secondRecord);
    }

    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(tempFile, new GenericDatumReader<GenericRecord>())) {
      assertTrue(dataFileReader.hasNext());
      assertEquals(1, dataFileReader.next().get("id"));

      assertTrue(dataFileReader.hasNext());
      assertEquals(2, dataFileReader.next().get("id"));

      assertFalse("Expected exactly two records in the file.", dataFileReader.hasNext());
    }
  }

  private static Schema parseSchema(String schemaJson) {
    return new Schema.Parser().parse(schemaJson);
  }

  private static GenericRecord newIntRecord(Schema schema, int id) {
    GenericRecord record = new GenericData.Record(schema);
    record.put("id", id);
    return record;
  }

  private static GenericRecord newStringRecord(Schema schema, String label) {
    GenericRecord record = new GenericData.Record(schema);
    record.put("label", label);
    return record;
  }

  private static GenericRecord newUserRecord(Schema schema, int id, String label) {
    GenericRecord record = new GenericData.Record(schema);
    record.put("id", id);
    record.put("label", label);
    return record;
  }

  private static DataFileWriter<GenericRecord> newDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>(schema));
  }

  private static DataFileWriter<Object> newGenericObjectDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<Object>(schema));
  }

  private static <D> void assertAppendFails(DataFileWriter<D> dataFileWriter, D datum)
      throws IOException {
    try {
      dataFileWriter.append(datum);
      fail("Expected append to fail for invalid datum: " + datum);
    } catch (IOException | RuntimeException exception) {
      assertNotNull(exception);
    }
  }
}
