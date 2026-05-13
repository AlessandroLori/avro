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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.file.*;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestToTDataFileWriter {
  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  private static final Schema RECORD_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"RecordUnderTest\","
      + "\"namespace\":\"example.avro\","
      + "\"fields\":["
      + "  {\"name\":\"id\",\"type\":\"int\"},"
      + "  {\"name\":\"label\",\"type\":\"string\"}"
      + "]"
      + "}");

  private static final Schema DIFFERENT_RECORD_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"OtherRecord\","
      + "\"namespace\":\"example.avro\","
      + "\"fields\":["
      + "  {\"name\":\"id\",\"type\":\"int\"}"
      + "]"
      + "}");

  private static final Schema STRING_SCHEMA = Schema.create(Schema.Type.STRING);
  private static final Schema INT_SCHEMA = Schema.create(Schema.Type.INT);

  @Test
  public void constructorShouldCreateClosedWriterThatRejectsAppendBeforeCreate() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    AvroRuntimeException error = expectThrows(AvroRuntimeException.class, () -> writer.append(record(1, "not-open")));

    assertTrue(error.getMessage().contains("not open"));
  }

  @Test
  public void setCodecShouldWriteReadableCompressedFile() throws Exception {
    File file = temporaryFolder.newFile("compressed.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      DataFileWriter<GenericRecord> returned = writer.setCodec(CodecFactory.deflateCodec(1));
      assertSame(writer, returned);

      writer.create(RECORD_SCHEMA, file);
      writer.append(record(1, "compressed"));
    }

    assertEquals(Arrays.asList("1:compressed"), readRecordSummaries(file));
  }

  @Test
  public void setCodecAfterCreateShouldFailBecauseWriterIsAlreadyOpen() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, out);

      AvroRuntimeException error = expectThrows(AvroRuntimeException.class,
          () -> writer.setCodec(CodecFactory.nullCodec()));

      assertTrue(error.getMessage().contains("already open"));
    }
  }

  @Test
  public void setSyncIntervalShouldAcceptBoundaryValuesAndRejectInvalidValues() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    assertSame(writer, writer.setSyncInterval(32));
    assertSame(writer, writer.setSyncInterval(1 << 30));

    IllegalArgumentException tooSmall = expectThrows(IllegalArgumentException.class, () -> writer.setSyncInterval(31));
    IllegalArgumentException tooLarge = expectThrows(IllegalArgumentException.class,
        () -> writer.setSyncInterval((1 << 30) + 1));

    assertTrue(tooSmall.getMessage().contains("Invalid syncInterval"));
    assertTrue(tooLarge.getMessage().contains("Invalid syncInterval"));
  }

  @Test
  public void setEncoderShouldUseCustomEncoderFactoryWhenWriterIsOpened() throws Exception {
    File file = temporaryFolder.newFile("custom-encoder.avro");
    AtomicBoolean encoderFactoryCalled = new AtomicBoolean(false);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      DataFileWriter<GenericRecord> returned = writer.setEncoder(out -> {
        encoderFactoryCalled.set(true);
        return new EncoderFactory().directBinaryEncoder(out, null);
      });

      assertSame(writer, returned);

      writer.create(RECORD_SCHEMA, file);
      writer.append(record(7, "custom-encoder"));
    }

    assertTrue(encoderFactoryCalled.get());
    assertEquals(Arrays.asList("7:custom-encoder"), readRecordSummaries(file));
  }

  @Test
  public void createWithFileShouldWriteHeaderAndRecords() throws Exception {
    File file = temporaryFolder.newFile("create-file.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      DataFileWriter<GenericRecord> returned = writer.create(RECORD_SCHEMA, file);
      assertSame(writer, returned);

      writer.append(record(1, "one"));
      writer.append(record(2, "two"));
    }

    assertEquals(Arrays.asList("1:one", "2:two"), readRecordSummaries(file));
  }

  @Test
  public void createWithOutputStreamShouldWriteReadableData() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<CharSequence> writer = newStringWriter()) {
      DataFileWriter<CharSequence> returned = writer.create(STRING_SCHEMA, out);
      assertSame(writer, returned);

      writer.append("alpha");
      writer.append("beta");
    }

    assertEquals(Arrays.asList("alpha", "beta"), readStrings(out.toByteArray()));
  }

  @Test
  public void createWithExplicitSixteenByteSyncShouldRoundTrip() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte[] sync = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

    try (DataFileWriter<CharSequence> writer = newStringWriter()) {
      DataFileWriter<CharSequence> returned = writer.create(STRING_SCHEMA, out, sync);
      assertSame(writer, returned);

      writer.append("explicit-sync");
    }

    assertEquals(Arrays.asList("explicit-sync"), readStrings(out.toByteArray()));
  }

  @Test
  public void createWithInvalidExplicitSyncLengthShouldFail() {
    DataFileWriter<CharSequence> writer = newStringWriter();

    IOException error = expectThrows(IOException.class,
        () -> writer.create(STRING_SCHEMA, new ByteArrayOutputStream(), new byte[15]));

    assertTrue(error.getMessage().contains("sync must be exactly 16 bytes"));
  }

  @Test
  public void createWhileAlreadyOpenShouldFail() throws Exception {
    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());

      AvroRuntimeException error = expectThrows(AvroRuntimeException.class,
          () -> writer.create(RECORD_SCHEMA, new ByteArrayOutputStream()));

      assertTrue(error.getMessage().contains("already open"));
    }
  }

  @Test
  public void setFlushOnEveryBlockShouldToggleTheFlag() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    assertTrue(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(false);
    assertFalse(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(true);
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void flushShouldMakeOutputStreamReadableWithoutClosingWriter() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DataFileWriter<CharSequence> writer = newStringWriter();

    try {
      writer.create(STRING_SCHEMA, out);
      writer.setFlushOnEveryBlock(false);
      writer.append("visible-after-flush");

      writer.flush();

      assertEquals(Arrays.asList("visible-after-flush"), readStrings(out.toByteArray()));
    } finally {
      writer.close();
    }
  }

  @Test
  public void setMetaShouldStoreStringLongAndByteArrayMetadata() throws Exception {
    File file = temporaryFolder.newFile("metadata.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.setMeta("owner", "tester"));
      assertSame(writer, writer.setMeta("attempts", 3L));
      assertSame(writer, writer.setMeta("raw-bytes", new byte[] { 1, 2, 3 }));

      writer.create(RECORD_SCHEMA, file);
      writer.append(record(9, "metadata"));
    }

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("tester", reader.getMetaString("owner"));
      assertEquals(3L, reader.getMetaLong("attempts"));
      assertArrayEquals(new byte[] { 1, 2, 3 }, reader.getMeta("raw-bytes"));
    }
  }

  @Test
  public void setMetaShouldRejectReservedKeysAndKeysAfterOpen() throws Exception {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertFalse(DataFileWriter.isReservedMeta("custom.key"));

    DataFileWriter<GenericRecord> writer = newRecordWriter();

    AvroRuntimeException reserved = expectThrows(AvroRuntimeException.class,
        () -> writer.setMeta("avro.custom", "not-allowed"));

    assertTrue(reserved.getMessage().contains("Cannot set reserved meta key"));

    try {
      writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());

      AvroRuntimeException alreadyOpen = expectThrows(AvroRuntimeException.class,
          () -> writer.setMeta("custom.after.open", "not-allowed"));

      assertTrue(alreadyOpen.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void appendShouldWriteRecordsAndCloseShouldFlushFinalBlock() throws Exception {
    File file = temporaryFolder.newFile("append.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      writer.append(record(1, "first"));
      writer.append(record(2, "second"));
      writer.append(record(3, "third"));
    }

    assertEquals(Arrays.asList("1:first", "2:second", "3:third"), readRecordSummaries(file));
  }

  @Test
  public void appendShouldWrapDatumWriterIOExceptionAndDiscardPartialDatum() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<CharSequence> writer = new DataFileWriter<>(new FailAfterPartialStringWriter())) {
      writer.create(STRING_SCHEMA, out);
      writer.append("before");

      DataFileWriter.AppendWriteException error = expectThrows(DataFileWriter.AppendWriteException.class,
          () -> writer.append("FAIL"));

      assertNotNull(error.getCause());
      assertTrue(error.getCause() instanceof IOException);

      writer.append("after");
    }

    assertEquals(Arrays.asList("before", "after"), readStrings(out.toByteArray()));
  }

  @Test
  public void appendShouldWrapRuntimeExceptionsFromDatumWriter() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<Object> writer = new DataFileWriter<>(new GenericDatumWriter<Object>())) {
      writer.create(STRING_SCHEMA, out);

      DataFileWriter.AppendWriteException error = expectThrows(DataFileWriter.AppendWriteException.class,
          () -> writer.append(Integer.valueOf(42)));

      assertNotNull(error.getCause());
    }
  }

  @Test
  public void appendAfterCloseShouldFailWithNotOpen() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
    writer.close();
    writer.close();

    AvroRuntimeException error = expectThrows(AvroRuntimeException.class, () -> writer.append(record(1, "closed")));

    assertTrue(error.getMessage().contains("not open"));
  }

  @Test
  public void appendEncodedShouldWritePreEncodedDatum() throws Exception {
    File file = temporaryFolder.newFile("append-encoded.avro");
    ByteArrayOutputStream encodedDatum = new ByteArrayOutputStream();
    BinaryEncoder encoder = new EncoderFactory().directBinaryEncoder(encodedDatum, null);
    encoder.writeInt(42);
    encoder.flush();

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>())) {
      writer.create(INT_SCHEMA, file);
      writer.appendEncoded(ByteBuffer.wrap(encodedDatum.toByteArray()));
    }

    assertEquals(Arrays.asList(42), readIntegers(file));
  }

  @Test
  public void appendEncodedBeforeCreateShouldFail() {
    DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>());

    AvroRuntimeException error = expectThrows(AvroRuntimeException.class,
        () -> writer.appendEncoded(ByteBuffer.wrap(new byte[] { 1 })));

    assertTrue(error.getMessage().contains("not open"));
  }

  @Test
  public void appendToFileShouldPreserveExistingDataAndAppendNewRecords() throws Exception {
    File file = temporaryFolder.newFile("append-to-file.avro");

    writeRecords(file, record(1, "original"));

    try (DataFileWriter<GenericRecord> appender = newRecordWriter()) {
      DataFileWriter<GenericRecord> returned = appender.appendTo(file);
      assertSame(appender, returned);

      appender.append(record(2, "appended"));
    }

    assertEquals(Arrays.asList("1:original", "2:appended"), readRecordSummaries(file));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamShouldAppendAtEnd() throws Exception {
    File file = temporaryFolder.newFile("append-to-seekable.avro");

    writeRecords(file, record(1, "first"));

    try (SeekableFileInput input = new SeekableFileInput(file);
         FileOutputStream output = new FileOutputStream(file, true);
         DataFileWriter<GenericRecord> appender = newRecordWriter()) {
      DataFileWriter<GenericRecord> returned = appender.appendTo(input, output);
      assertSame(appender, returned);

      appender.append(record(2, "second"));
    }

    assertEquals(Arrays.asList("1:first", "2:second"), readRecordSummaries(file));
  }

  @Test
  public void appendToShouldFailWhenWriterIsAlreadyOpen() throws Exception {
    File file = temporaryFolder.newFile("append-to-open.avro");
    writeRecords(file, record(1, "source"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());

      AvroRuntimeException error = expectThrows(AvroRuntimeException.class, () -> writer.appendTo(file));

      assertTrue(error.getMessage().contains("already open"));
    }
  }

  @Test
  public void appendAllFromShouldCopyBlocksWhenSchemasMatch() throws Exception {
    File source = temporaryFolder.newFile("append-all-source.avro");
    File target = temporaryFolder.newFile("append-all-target.avro");

    writeRecords(source, record(2, "source-a"), record(3, "source-b"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, target);
      writer.append(record(1, "target"));

      try (DataFileStream<GenericRecord> otherFile = new DataFileStream<>(new FileInputStream(source),
          new GenericDatumReader<GenericRecord>())) {
        writer.appendAllFrom(otherFile, false);
      }
    }

    assertEquals(Arrays.asList("1:target", "2:source-a", "3:source-b"), readRecordSummaries(target));
  }

  @Test
  public void appendAllFromShouldRecompressWhenCodecsDiffer() throws Exception {
    File source = temporaryFolder.newFile("append-all-source-null-codec.avro");
    File target = temporaryFolder.newFile("append-all-target-deflate.avro");

    writeRecords(source, record(1, "source"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.setCodec(CodecFactory.deflateCodec(1));
      writer.create(RECORD_SCHEMA, target);

      try (DataFileStream<GenericRecord> otherFile = new DataFileStream<>(new FileInputStream(source),
          new GenericDatumReader<GenericRecord>())) {
        writer.appendAllFrom(otherFile, false);
      }
    }

    assertEquals(Arrays.asList("1:source"), readRecordSummaries(target));
  }

  @Test
  public void appendAllFromShouldHonorRecompressFlagEvenWithCompatibleCodecs() throws Exception {
    File source = temporaryFolder.newFile("append-all-recompress-source.avro");
    File target = temporaryFolder.newFile("append-all-recompress-target.avro");

    writeRecords(source, record(10, "recompress-a"), record(11, "recompress-b"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, target);

      try (DataFileStream<GenericRecord> otherFile = new DataFileStream<>(new FileInputStream(source),
          new GenericDatumReader<GenericRecord>())) {
        writer.appendAllFrom(otherFile, true);
      }
    }

    assertEquals(Arrays.asList("10:recompress-a", "11:recompress-b"), readRecordSummaries(target));
  }

  @Test
  public void appendAllFromShouldRejectDifferentSchema() throws Exception {
    File source = temporaryFolder.newFile("different-schema-source.avro");
    File target = temporaryFolder.newFile("different-schema-target.avro");

    writeDifferentRecords(source, differentRecord(1));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, target);

      try (DataFileStream<GenericRecord> otherFile = new DataFileStream<>(new FileInputStream(source),
          new GenericDatumReader<GenericRecord>())) {
        IOException error = expectThrows(IOException.class, () -> writer.appendAllFrom(otherFile, false));
        assertTrue(error.getMessage().contains("does not match"));
      }
    }
  }

  @Test
  public void appendAllFromBeforeCreateShouldFail() throws Exception {
    File source = temporaryFolder.newFile("append-all-before-create-source.avro");
    writeRecords(source, record(1, "source"));

    try (DataFileStream<GenericRecord> otherFile = new DataFileStream<>(new FileInputStream(source),
        new GenericDatumReader<GenericRecord>())) {
      DataFileWriter<GenericRecord> writer = newRecordWriter();

      AvroRuntimeException error = expectThrows(AvroRuntimeException.class, () -> writer.appendAllFrom(otherFile, false));

      assertTrue(error.getMessage().contains("not open"));
    }
  }

  @Test
  public void syncShouldReturnPositionUsableByDataFileReaderSeek() throws Exception {
    File file = temporaryFolder.newFile("sync-seek.avro");
    long positionAfterFirstBlock;

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      writer.append(record(1, "before-sync"));

      positionAfterFirstBlock = writer.sync();
      assertTrue(positionAfterFirstBlock > 0);

      writer.append(record(2, "after-sync"));
    }

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      reader.seek(positionAfterFirstBlock);

      assertTrue(reader.hasNext());
      GenericRecord found = reader.next();

      assertEquals(2, found.get("id"));
      assertEquals("after-sync", found.get("label").toString());
    }
  }

  @Test
  public void syncFlushAndFsyncShouldFailBeforeWriterIsOpen() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    assertTrue(expectThrows(AvroRuntimeException.class, writer::sync).getMessage().contains("not open"));
    assertTrue(expectThrows(AvroRuntimeException.class, writer::flush).getMessage().contains("not open"));
    assertTrue(expectThrows(AvroRuntimeException.class, writer::fSync).getMessage().contains("not open"));
  }

  @Test
  public void fSyncShouldFlushAndInvokeSyncableOutputStream() throws Exception {
    CloseAwareSyncableOutputStream out = new CloseAwareSyncableOutputStream();

    try (DataFileWriter<CharSequence> writer = newStringWriter()) {
      writer.create(STRING_SCHEMA, out);
      writer.append("fsync");

      writer.fSync();

      assertTrue(out.synced);
      assertEquals(Arrays.asList("fsync"), readStrings(out.toByteArray()));
    }

    assertTrue(out.closed);
  }

  @Test
  public void closeBeforeCreateShouldBeNoOpAndCloseAfterCreateShouldCloseUnderlyingStream() throws Exception {
    DataFileWriter<CharSequence> unopened = newStringWriter();
    unopened.close();
    unopened.close();

    CloseAwareSyncableOutputStream out = new CloseAwareSyncableOutputStream();
    DataFileWriter<CharSequence> opened = newStringWriter();

    opened.create(STRING_SCHEMA, out);
    opened.append("closed");
    opened.close();
    opened.close();

    assertTrue(out.closed);
    assertEquals(Arrays.asList("closed"), readStrings(out.toByteArray()));
  }

  private static DataFileWriter<GenericRecord> newRecordWriter() {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>());
  }

  private static DataFileWriter<CharSequence> newStringWriter() {
    return new DataFileWriter<>(new GenericDatumWriter<CharSequence>());
  }

  private static GenericRecord record(int id, String label) {
    GenericRecord record = new GenericData.Record(RECORD_SCHEMA);
    record.put("id", id);
    record.put("label", label);
    return record;
  }

  private static GenericRecord differentRecord(int id) {
    GenericRecord record = new GenericData.Record(DIFFERENT_RECORD_SCHEMA);
    record.put("id", id);
    return record;
  }

  private static void writeRecords(File file, GenericRecord... records) throws IOException {
    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      for (GenericRecord record : records) {
        writer.append(record);
      }
    }
  }

  private static void writeDifferentRecords(File file, GenericRecord... records) throws IOException {
    try (DataFileWriter<GenericRecord> writer = new DataFileWriter<>(new GenericDatumWriter<GenericRecord>())) {
      writer.create(DIFFERENT_RECORD_SCHEMA, file);
      for (GenericRecord record : records) {
        writer.append(record);
      }
    }
  }

  private static List<String> readRecordSummaries(File file) throws IOException {
    List<String> values = new ArrayList<>();

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      while (reader.hasNext()) {
        GenericRecord record = reader.next();
        values.add(record.get("id") + ":" + record.get("label").toString());
      }
    }

    return values;
  }

  private static List<String> readStrings(byte[] data) throws IOException {
    List<String> values = new ArrayList<>();

    try (DataFileStream<CharSequence> reader = new DataFileStream<>(new ByteArrayInputStream(data),
        new GenericDatumReader<CharSequence>())) {
      while (reader.hasNext()) {
        values.add(reader.next().toString());
      }
    }

    return values;
  }

  private static List<Integer> readIntegers(File file) throws IOException {
    List<Integer> values = new ArrayList<>();

    try (DataFileReader<Integer> reader = new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      while (reader.hasNext()) {
        values.add(reader.next());
      }
    }

    return values;
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      throw new AssertionError(
          "Expected " + expectedType.getName() + " but got " + actual.getClass().getName(), actual);
    }

    fail("Expected " + expectedType.getName());
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class FailAfterPartialStringWriter implements DatumWriter<CharSequence> {
    @Override
    public void setSchema(Schema schema) {
      // The test writer intentionally accepts the schema provided by DataFileWriter.
    }

    @Override
    public void write(CharSequence datum, Encoder out) throws IOException {
      if ("FAIL".contentEquals(datum)) {
        out.writeString("partial-data");
        throw new IOException("forced partial write failure");
      }

      out.writeString(datum.toString());
    }
  }

  private static final class CloseAwareSyncableOutputStream extends ByteArrayOutputStream implements Syncable {
    private boolean synced;
    private boolean closed;

    @Override
    public void sync() {
      synced = true;
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
