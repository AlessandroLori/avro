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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.file.Syncable;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.junit.Test;

/**
 * Additional black-box tests for {@link DataFileWriter}, written from the
 * public API documentation only.
 *
 * <p>The goal is to stress behaviours that are easy to miss after a strong
 * append-focused suite: metadata preconditions, reserved metadata keys,
 * append-to invalid files, appendAllFrom codec branches, appendEncoded expert
 * mode, sync/flush variants, fSync on Syncable streams and file truncation when
 * create(File) is used for a new container.</p>
 */
public class TestLLMFewShotDocDataFileWriter_J {

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

  private static final byte[] SYNC_MARKER_A = new byte[] {
      0, 1, 2, 3, 4, 5, 6, 7,
      8, 9, 10, 11, 12, 13, 14, 15
  };

  private static final byte[] SYNC_MARKER_B = new byte[] {
      15, 14, 13, 12, 11, 10, 9, 8,
      7, 6, 5, 4, 3, 2, 1, 0
  };

  @Test
  public void reservedMetadataRecognitionShouldDistinguishAvroPrefixFromUserKeys() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertTrue(DataFileWriter.isReservedMeta("avro.custom.internal"));

    assertFalse(DataFileWriter.isReservedMeta("avro"));
    assertFalse(DataFileWriter.isReservedMeta("application.avro.schema"));
    assertFalse(DataFileWriter.isReservedMeta("user.metadata"));
  }

  @Test
  public void setMetaShouldRejectReservedAvroKeysBeforeCreate() throws Exception {
    final DataFileWriter<GenericRecord> writer = newDataFileWriter(intRecordSchema());
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          writer.setMeta("avro.schema", "reserved");
        }
      });
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          writer.setMeta("avro.codec", new byte[] {1, 2, 3});
        }
      });
    } finally {
      closeQuietly(writer);
    }
  }

  @Test
  public void setMetaShouldRejectLateMetadataAfterCreate() throws Exception {
    Schema schema = intRecordSchema();

    try (final DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          writer.setMeta("late.string", "value");
        }
      });
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          writer.setMeta("late.long", 10L);
        }
      });
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          writer.setMeta("late.bytes", new byte[] {1});
        }
      });
    }
  }

  @Test
  public void setMetaShouldUseTheLastValueForRepeatedUserMetadataKey() throws Exception {
    Schema schema = intRecordSchema();
    File file = newTempAvroFile("dfw-j-meta-overwrite-");

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.setMeta("owner", "first");
      writer.setMeta("owner", "second");
      writer.setMeta("counter", 1L);
      writer.setMeta("counter", 2L);
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 1));
    }

    try (DataFileReader<GenericRecord> reader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("second", reader.getMetaString("owner"));
      assertEquals(2L, reader.getMetaLong("counter"));
    }
  }

  @Test
  public void createWithFileShouldReplacePreviousContainerContent() throws Exception {
    Schema schema = intRecordSchema();
    File file = newTempAvroFile("dfw-j-create-overwrite-");

    writeIds(file, schema, 1, 2, 3);
    assertEquals(Arrays.asList(1, 2, 3), readIds(file));

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 99));
    }

    assertEquals(Arrays.asList(99), readIds(file));
  }

  @Test
  public void createWithDifferentExplicitSyncMarkersShouldProduceDifferentFiles() throws Exception {
    Schema schema = intRecordSchema();
    ByteArrayOutputStream firstOutput = new ByteArrayOutputStream();
    ByteArrayOutputStream secondOutput = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, firstOutput, SYNC_MARKER_A);
      writer.append(newIntRecord(schema, 1));
    }

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, secondOutput, SYNC_MARKER_B);
      writer.append(newIntRecord(schema, 1));
    }

    assertNotEquals(firstOutput.toByteArray().length, 0);
    assertNotEquals(secondOutput.toByteArray().length, 0);
    assertFalse("Different explicit sync markers should affect the container bytes.",
        Arrays.equals(firstOutput.toByteArray(), secondOutput.toByteArray()));

    assertEquals(Arrays.asList(1), readIds(firstOutput.toByteArray()));
    assertEquals(Arrays.asList(1), readIds(secondOutput.toByteArray()));
  }

  @Test
  public void appendToShouldRejectMissingFile() throws Exception {
    Schema schema = intRecordSchema();
    File missingFile = new File(newTempDirectory(), "missing.avro");
    assertFalse(missingFile.exists());

    final DataFileWriter<GenericRecord> writer = newDataFileWriter(schema);
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          writer.appendTo(missingFile);
        }
      });
    } finally {
      closeQuietly(writer);
    }
  }

  @Test
  public void appendToShouldRejectEmptyFileThatIsNotAnAvroContainer() throws Exception {
    Schema schema = intRecordSchema();
    File emptyFile = newTempAvroFile("dfw-j-empty-append-to-");
    assertEquals(0L, emptyFile.length());

    final DataFileWriter<GenericRecord> writer = newDataFileWriter(schema);
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          writer.appendTo(emptyFile);
        }
      });
    } finally {
      closeQuietly(writer);
    }
  }

  @Test
  public void appendToShouldPreserveExistingMetadataWhileAddingRecords() throws Exception {
    Schema schema = intRecordSchema();
    File file = newTempAvroFile("dfw-j-append-metadata-");

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.setMeta("dataset", "training");
      writer.setMeta("generation", 1L);
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 10));
    }

    try (DataFileWriter<GenericRecord> appender = newDataFileWriter(schema).appendTo(file)) {
      appender.append(newIntRecord(schema, 20));
    }

    try (DataFileReader<GenericRecord> reader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("training", reader.getMetaString("dataset"));
      assertEquals(1L, reader.getMetaLong("generation"));
    }
    assertEquals(Arrays.asList(10, 20), readIds(file));
  }

  @Test
  public void appendToSeekableInputShouldNotCloseTheInputEvenWhenAppenderIsClosed() throws Exception {
    Schema schema = intRecordSchema();
    File file = newTempAvroFile("dfw-j-seekable-not-closed-");
    writeIds(file, schema, 1);

    SeekableFileInput seekableInput = new SeekableFileInput(file);
    try {
      OutputStream positionedOutput = new FileOutputStream(file, true);
      try (DataFileWriter<GenericRecord> appender =
               newDataFileWriter(schema).appendTo(seekableInput, positionedOutput)) {
        appender.append(newIntRecord(schema, 2));
      }

      assertTrue("The documented appendTo(SeekableInput, OutputStream) contract says that input is not closed.",
          seekableInput.length() > 0);
    } finally {
      seekableInput.close();
    }

    assertEquals(Arrays.asList(1, 2), readIds(file));
  }

  @Test
  public void appendAllFromShouldAppendNothingWhenSourceHasNoDataBlocks() throws Exception {
    Schema schema = intRecordSchema();
    File emptySource = newTempAvroFile("dfw-j-empty-source-");
    File target = newTempAvroFile("dfw-j-empty-source-target-");

    try (DataFileWriter<GenericRecord> sourceWriter = newDataFileWriter(schema)) {
      sourceWriter.create(schema, emptySource);
    }
    writeIds(target, schema, 7);

    try (DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(emptySource, new GenericDatumReader<GenericRecord>());
         DataFileWriter<GenericRecord> targetWriter = newDataFileWriter(schema).appendTo(target)) {
      targetWriter.appendAllFrom(sourceReader, false);
    }

    assertEquals(Arrays.asList(7), readIds(target));
  }

  @Test
  public void appendAllFromShouldTranscodeWhenCodecsDifferAndRecompressIsFalse() throws Exception {
    Schema schema = intRecordSchema();
    File uncompressedSource = newTempAvroFile("dfw-j-transcode-source-");
    File deflateTarget = newTempAvroFile("dfw-j-transcode-target-");

    writeIds(uncompressedSource, schema, 2, 3);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.setCodec(CodecFactory.deflateCodec(1));
      writer.create(schema, deflateTarget);
      writer.append(newIntRecord(schema, 1));
    }

    try (DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(uncompressedSource, new GenericDatumReader<GenericRecord>());
         DataFileWriter<GenericRecord> targetWriter = newDataFileWriter(schema).appendTo(deflateTarget)) {
      targetWriter.appendAllFrom(sourceReader, false);
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(deflateTarget));
  }

  @Test
  public void appendAllFromShouldRejectSameShapeButDifferentNamedRecordSchema() throws Exception {
    Schema sourceSchema = new Schema.Parser().parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"SourceRecord\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");
    Schema targetSchema = new Schema.Parser().parse("{"
        + "\"type\":\"record\","
        + "\"name\":\"TargetRecord\","
        + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
        + "}");
    File source = newTempAvroFile("dfw-j-different-names-source-");
    File target = newTempAvroFile("dfw-j-different-names-target-");

    writeIds(source, sourceSchema, 1);
    writeIds(target, targetSchema, 2);

    try (final DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(source, new GenericDatumReader<GenericRecord>());
         final DataFileWriter<GenericRecord> targetWriter =
             newDataFileWriter(targetSchema).appendTo(target)) {

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          targetWriter.appendAllFrom(sourceReader, false);
        }
      });
    }
  }

  @Test
  public void appendEncodedShouldRespectByteBufferPositionAndLimit() throws Exception {
    Schema schema = Schema.create(Schema.Type.INT);
    File file = newTempAvroFile("dfw-j-encoded-position-");
    byte[] encoded = encodeInt(314);
    byte[] wrapped = new byte[encoded.length + 4];

    wrapped[0] = 55;
    wrapped[1] = 66;
    System.arraycopy(encoded, 0, wrapped, 2, encoded.length);
    wrapped[wrapped.length - 2] = 77;
    wrapped[wrapped.length - 1] = 88;

    ByteBuffer datum = ByteBuffer.wrap(wrapped);
    datum.position(2);
    datum.limit(2 + encoded.length);

    try (DataFileWriter<Object> writer = newObjectDataFileWriter(schema)) {
      writer.create(schema, file);
      writer.appendEncoded(datum);
    }

    assertEquals(Arrays.asList(314), readPrimitiveInts(file));
  }

  @Test
  public void appendEncodedShouldNotValidateNonConformingBytesAtWriteTime() throws Exception {
    Schema schema = Schema.create(Schema.Type.STRING);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<Object> writer = newObjectDataFileWriter(schema)) {
      writer.create(schema, outputStream);
      writer.appendEncoded(ByteBuffer.wrap(new byte[] {1}));
    }

    assertTrue("appendEncoded is documented as expert mode without schema validation.",
        outputStream.size() > 0);
  }

  @Test
  public void syncShouldFlushUnderlyingStreamByDefault() throws Exception {
    Schema schema = intRecordSchema();
    TrackingOutputStream outputStream = new TrackingOutputStream();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      assertTrue(writer.isFlushOnEveryBlock());
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 1));

      int flushCountBeforeSync = outputStream.getFlushCount();
      writer.sync();

      assertTrue(outputStream.getFlushCount() > flushCountBeforeSync);
      assertFalse(outputStream.isClosed());
    }
  }

  @Test
  public void syncShouldNotFlushWhenFlushOnEveryBlockIsDisabled() throws Exception {
    Schema schema = intRecordSchema();
    TrackingOutputStream outputStream = new TrackingOutputStream();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.setFlushOnEveryBlock(false);
      assertFalse(writer.isFlushOnEveryBlock());
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 1));

      int flushCountBeforeSync = outputStream.getFlushCount();
      writer.sync();
      assertEquals(flushCountBeforeSync, outputStream.getFlushCount());

      writer.flush();
      assertTrue(outputStream.getFlushCount() > flushCountBeforeSync);
    }
  }

  @Test
  public void fSyncShouldCallSyncOnSyncableOutputStream() throws Exception {
    Schema schema = intRecordSchema();
    TrackingSyncableOutputStream outputStream = new TrackingSyncableOutputStream();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 1));

      int syncCountBefore = outputStream.getSyncCount();
      writer.fSync();

      assertTrue(outputStream.getSyncCount() > syncCountBefore);
      assertFalse(outputStream.isClosed());
    }
  }

  @Test
  public void setEncoderShouldAllowCustomBinaryEncoderBeforeCreate() throws Exception {
    Schema schema = intRecordSchema();
    File file = newTempAvroFile("dfw-j-custom-encoder-");
    final AtomicInteger customEncoderCalls = new AtomicInteger();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      assertSame(writer, writer.setEncoder(outputStream -> {
        customEncoderCalls.incrementAndGet();
        return EncoderFactory.get().binaryEncoder(outputStream, null);
      }));
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 123));
    }

    assertTrue(customEncoderCalls.get() > 0);
    assertEquals(Arrays.asList(123), readIds(file));
  }

  @Test
  public void setCodecBeforeCreateShouldReturnSameWriterAndProduceReadableFile() throws Exception {
    Schema schema = stringRecordSchema();
    File file = newTempAvroFile("dfw-j-set-codec-chain-");

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      assertSame(writer, writer.setCodec(CodecFactory.deflateCodec(9)));
      writer.create(schema, file);
      writer.append(newStringRecord(schema, ""));
      writer.append(newStringRecord(schema, "compressed"));
    }

    assertEquals(Arrays.asList("", "compressed"), readLabels(file));
  }

  @Test
  public void flushAfterCloseShouldFailAsWriterIsNoLongerOpen() throws Exception {
    Schema schema = intRecordSchema();
    final DataFileWriter<GenericRecord> writer = newDataFileWriter(schema);

    writer.create(schema, new ByteArrayOutputStream());
    writer.append(newIntRecord(schema, 1));
    writer.close();

    assertOperationFails(new FailingOperation() {
      @Override
      public void run() throws Exception {
        writer.flush();
      }
    });
  }

  private static Schema intRecordSchema() {
    return new Schema.Parser().parse(INT_RECORD_SCHEMA_JSON);
  }

  private static Schema stringRecordSchema() {
    return new Schema.Parser().parse(STRING_RECORD_SCHEMA_JSON);
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

  private static DataFileWriter<GenericRecord> newDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>(schema));
  }

  private static DataFileWriter<Object> newObjectDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<Object>(schema));
  }

  private static File newTempAvroFile(String prefix) throws IOException {
    File file = Files.createTempFile(prefix, ".avro").toFile();
    file.deleteOnExit();
    return file;
  }

  private static File newTempDirectory() throws IOException {
    File directory = Files.createTempDirectory("dfw-j-dir-").toFile();
    directory.deleteOnExit();
    return directory;
  }

  private static void writeIds(File file, Schema schema, int... ids) throws IOException {
    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(schema)) {
      writer.create(schema, file);
      for (int id : ids) {
        writer.append(newIntRecord(schema, id));
      }
    }
  }

  private static List<Integer> readIds(File file) throws IOException {
    try (DataFileReader<GenericRecord> reader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      return collectIds(reader);
    }
  }

  private static List<Integer> readIds(byte[] bytes) throws IOException {
    try (DataFileStream<GenericRecord> reader =
             new DataFileStream<>(new ByteArrayInputStream(bytes),
                 new GenericDatumReader<GenericRecord>())) {
      return collectIds(reader);
    }
  }

  private static List<Integer> collectIds(DataFileStream<GenericRecord> reader)
      throws IOException {
    List<Integer> ids = new ArrayList<>();
    while (reader.hasNext()) {
      ids.add(((Number) reader.next().get("id")).intValue());
    }
    return ids;
  }

  private static List<String> readLabels(File file) throws IOException {
    List<String> labels = new ArrayList<>();
    try (DataFileReader<GenericRecord> reader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      while (reader.hasNext()) {
        labels.add(reader.next().get("label").toString());
      }
    }
    return labels;
  }

  private static List<Integer> readPrimitiveInts(File file) throws IOException {
    List<Integer> values = new ArrayList<>();
    try (DataFileReader<Integer> reader =
             new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      while (reader.hasNext()) {
        values.add(reader.next());
      }
    }
    return values;
  }

  private static byte[] encodeInt(int value) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
    encoder.writeInt(value);
    encoder.flush();
    return outputStream.toByteArray();
  }

  private static void assertOperationFails(FailingOperation operation) throws Exception {
    try {
      operation.run();
      fail("Expected the operation to fail.");
    } catch (Exception exception) {
      assertNotNull(exception);
    }
  }

  private static void closeQuietly(DataFileWriter<?> writer) {
    try {
      writer.close();
    } catch (IOException | RuntimeException ignored) {
      // Some tests intentionally close writers that were never successfully opened.
    }
  }

  private interface FailingOperation {
    void run() throws Exception;
  }

  private static class TrackingOutputStream extends OutputStream {
    private final ByteArrayOutputStream delegate = new ByteArrayOutputStream();
    private int flushCount;
    private boolean closed;

    @Override
    public void write(int value) throws IOException {
      ensureOpen();
      delegate.write(value);
    }

    @Override
    public void write(byte[] bytes, int offset, int length) throws IOException {
      ensureOpen();
      delegate.write(bytes, offset, length);
    }

    @Override
    public void flush() throws IOException {
      ensureOpen();
      flushCount++;
      delegate.flush();
    }

    @Override
    public void close() throws IOException {
      flushCount++;
      closed = true;
      delegate.close();
    }

    int getFlushCount() {
      return flushCount;
    }

    boolean isClosed() {
      return closed;
    }

    private void ensureOpen() throws IOException {
      if (closed) {
        throw new IOException("stream already closed");
      }
    }
  }

  private static final class TrackingSyncableOutputStream extends TrackingOutputStream implements Syncable {
    private int syncCount;

    @Override
    public void sync() throws IOException {
      syncCount++;
      flush();
    }

    int getSyncCount() {
      return syncCount;
    }
  }
}
