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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;

/**
 * Mutation-oriented black-box tests for DataFileWriter.
 *
 * <p>The tests refine the oracles used for appendTo(File),
 * appendTo(SeekableInput, OutputStream), appendAllFrom(DataFileStream, boolean)
 * and append(D datum), without relying on implementation details.
 */
public class TestDataFileWriter_P {
  private static final String CUSTOM_META_KEY = "custom.test.key";
  private static final String CUSTOM_META_VALUE = "custom-test-value";

  private static final Schema ID_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"PitAppendIdRecord\","
      + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
      + "}");

  private static final Schema LABEL_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"PitAppendLabelRecord\","
      + "\"fields\":[{\"name\":\"label\",\"type\":\"string\"}]"
      + "}");

  private static final Schema PAYLOAD_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"PitPayloadRecord\","
      + "\"fields\":["
      + "{\"name\":\"id\",\"type\":\"int\"},"
      + "{\"name\":\"payload\",\"type\":\"string\"}"
      + "]"
      + "}");

  @Test
  public void appendToValidFileShouldPreserveOldRecordsAndAppendNewOne() throws Exception {
    File file = writeIdFileWithOptions(CodecFactory.deflateCodec(1), CUSTOM_META_KEY, CUSTOM_META_VALUE, 1);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      assertSame(writer, writer.appendTo(file));
      writer.append(idRecord(2));
    }

    assertEquals(Arrays.asList(1, 2), readIds(file));
    assertEquals(CUSTOM_META_VALUE, readMetaString(file, CUSTOM_META_KEY));
  }

  @Test
  public void appendToEmptyValidFileShouldAllowAppendingFirstRecord() throws Exception {
    File file = writeIdFileWithOptions(null, null, null);

    assertEquals(Collections.emptyList(), readIds(file));

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      assertSame(writer, writer.appendTo(file));
      writer.append(idRecord(1));
    }

    assertEquals(Collections.singletonList(1), readIds(file));
  }

  @Test
  public void appendToInvalidNonAvroFileShouldFail() throws Exception {
    File file = newTempFile("non-avro", ".txt");
    Files.write(file.toPath(), "this is not an avro container".getBytes(StandardCharsets.UTF_8));

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo(file);
        }
      }
    });

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        readIds(file);
      }
    });
  }

  @Test
  public void appendToNullFileShouldFail() throws Exception {
    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo((File) null);
        }
      }
    });
  }

  @Test
  public void appendToValidSeekableInputAndValidOutputShouldAppendRecord() throws Exception {
    File file = writeIdFileWithOptions(CodecFactory.deflateCodec(1), CUSTOM_META_KEY, CUSTOM_META_VALUE, 1);

    try (SeekableInput input = new SeekableFileInput(file);
        OutputStream output = new FileOutputStream(file, true);
        DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      assertSame(writer, writer.appendTo(input, output));
      writer.append(idRecord(2));
    }

    assertEquals(Arrays.asList(1, 2), readIds(file));
    assertEquals(CUSTOM_META_VALUE, readMetaString(file, CUSTOM_META_KEY));
  }

  @Test
  public void appendToInvalidSeekableInputShouldFail() throws Exception {
    File file = newTempFile("invalid-seekable", ".txt");
    Files.write(file.toPath(), "not an avro file".getBytes(StandardCharsets.UTF_8));
    final ByteArrayOutputStream output = new ByteArrayOutputStream();

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo(input, output);
        }
      }
    });

    assertEquals(0, output.size());
  }

  @Test
  public void appendToNullSeekableInputShouldFail() throws Exception {
    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (OutputStream output = new ByteArrayOutputStream();
            DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo((SeekableInput) null, output);
        }
      }
    });
  }

  @Test
  public void appendToInvalidOutputStreamShouldFailWithoutChangingOriginalFile() throws Exception {
    File file = writeIdFileWithOptions(null, null, null, 1);

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo(input, failingOutputStream());
          writer.append(idRecord(2));
        }
      }
    });

    assertEquals(Collections.singletonList(1), readIds(file));
  }

  @Test
  public void appendToNullOutputStreamShouldFail() throws Exception {
    File file = writeIdFileWithOptions(null, null, null, 1);

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          writer.appendTo(input, null);
          writer.append(idRecord(2));
        }
      }
    });

    assertEquals(Collections.singletonList(1), readIds(file));
  }

  @Test
  public void appendAllFromValidSourceShouldCopyOneRecord() throws Exception {
    File destination = newTempFile("append-all-destination-one", ".avro");
    File source = writeIdFileWithOptions(null, null, null, 2);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      assertSame(writer, writer.create(ID_SCHEMA, destination));
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Arrays.asList(1, 2), readIds(destination));
  }

  @Test
  public void appendAllFromValidSourceShouldCopyMultipleRecordsPreservingOrder() throws Exception {
    File destination = newTempFile("append-all-destination-multiple", ".avro");
    File source = writeIdFileWithOptions(null, null, null, 2, 3);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      assertSame(writer, writer.create(ID_SCHEMA, destination));
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(destination));
  }

  @Test
  public void appendAllFromValidEmptySourceShouldLeaveDestinationReadable() throws Exception {
    File destination = newTempFile("append-all-destination-empty-source", ".avro");
    File source = writeIdFileWithOptions(null, null, null);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      assertSame(writer, writer.create(ID_SCHEMA, destination));
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Collections.singletonList(1), readIds(destination));
  }

  @Test
  public void appendAllFromInvalidSourceSchemaShouldFailWithoutChangingDestination() throws Exception {
    File destination = newTempFile("append-all-schema-mismatch-destination", ".avro");
    File source = writeLabelFile("two");

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
            DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
          assertSame(writer, writer.create(ID_SCHEMA, destination));
          writer.append(idRecord(1));
          writer.appendAllFrom(sourceStream, false);
        }
      }
    });

    assertEquals(Collections.singletonList(1), readIds(destination));
  }

  @Test
  public void appendAllFromNullSourceShouldFail() throws Exception {
    File destination = newTempFile("append-all-null-source-destination", ".avro");

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
          assertSame(writer, writer.create(ID_SCHEMA, destination));
          writer.append(idRecord(1));
          writer.appendAllFrom(null, false);
        }
      }
    });

    assertEquals(Collections.singletonList(1), readIds(destination));
  }

  @Test
  public void appendAllFromWithRecompressTrueShouldStillCopyRecords() throws Exception {
    File destination = newTempFile("append-all-recompress-destination", ".avro");
    File source = writeIdFileWithOptions(CodecFactory.deflateCodec(1), null, null, 2);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      assertSame(writer, writer.create(ID_SCHEMA, destination));
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, true);
    }

    assertEquals(Arrays.asList(1, 2), readIds(destination));
  }

  @Test
  public void appendAllFromDifferentCodecShouldPreserveRecords() throws Exception {
    File destination = newTempFile("append-all-different-codec-destination", ".avro");
    File source = writeIdFileWithOptions(CodecFactory.deflateCodec(1), null, null, 2);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter();
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      assertSame(writer, writer.create(ID_SCHEMA, destination));
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Arrays.asList(1, 2), readIds(destination));
  }

  @Test
  public void appendShouldFailBeforeCreate() throws Exception {
    Throwable failure = expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        DataFileWriter<GenericRecord> writer = newDataFileWriter();
        writer.append(idRecord(1));
      }
    });

    assertTrue(failure instanceof AvroRuntimeException);
  }

  @Test
  public void appendWithSmallSyncIntervalShouldFlushBlockAndKeepRecordReadable() throws Exception {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    String payload = repeatedString('a', 4096);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      assertSame(writer, writer.setSyncInterval(32));
      assertSame(writer, writer.create(PAYLOAD_SCHEMA, output));
      int sizeAfterHeader = output.size();

      writer.append(payloadRecord(1, payload));

      assertTrue("A large record should force a block write before close.", output.size() > sizeAfterHeader);
    }

    assertEquals(Collections.singletonList(payload), readPayloads(output.toByteArray()));
  }

  private static DataFileWriter<GenericRecord> newDataFileWriter() {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>());
  }

  private static GenericRecord idRecord(int id) {
    GenericRecord record = new GenericData.Record(ID_SCHEMA);
    record.put("id", id);
    return record;
  }

  private static GenericRecord labelRecord(String label) {
    GenericRecord record = new GenericData.Record(LABEL_SCHEMA);
    record.put("label", label);
    return record;
  }

  private static GenericRecord payloadRecord(int id, String payload) {
    GenericRecord record = new GenericData.Record(PAYLOAD_SCHEMA);
    record.put("id", id);
    record.put("payload", payload);
    return record;
  }

  private static File writeIdFileWithOptions(CodecFactory codecFactory, String metaKey, String metaValue, int... ids)
      throws IOException {
    File file = newTempFile("id-records", ".avro");
    try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      if (codecFactory != null) {
        writer.setCodec(codecFactory);
      }
      if (metaKey != null) {
        writer.setMeta(metaKey, metaValue);
      }
      assertSame(writer, writer.create(ID_SCHEMA, file));
      for (int id : ids) {
        writer.append(idRecord(id));
      }
    }
    return file;
  }

  private static File writeLabelFile(String... labels) throws IOException {
    File file = newTempFile("label-records", ".avro");
    try (DataFileWriter<GenericRecord> writer = newDataFileWriter()) {
      assertSame(writer, writer.create(LABEL_SCHEMA, file));
      for (String label : labels) {
        writer.append(labelRecord(label));
      }
    }
    return file;
  }

  private static DataFileStream<GenericRecord> newDataFileStream(File file) throws IOException {
    return new DataFileStream<>(Files.newInputStream(file.toPath()), new GenericDatumReader<GenericRecord>());
  }

  private static List<Integer> readIds(File file) throws IOException {
    List<Integer> ids = new ArrayList<>();
    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      while (reader.hasNext()) {
        GenericRecord record = reader.next();
        ids.add(((Number) record.get("id")).intValue());
      }
    }
    return ids;
  }

  private static String readMetaString(File file, String key) throws IOException {
    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      return reader.getMetaString(key);
    }
  }

  private static List<String> readPayloads(byte[] fileBytes) throws IOException {
    List<String> payloads = new ArrayList<>();
    try (DataFileStream<GenericRecord> reader = new DataFileStream<>(new ByteArrayInputStream(fileBytes),
        new GenericDatumReader<GenericRecord>())) {
      while (reader.hasNext()) {
        GenericRecord record = reader.next();
        payloads.add(record.get("payload").toString());
      }
    }
    return payloads;
  }

  private static OutputStream failingOutputStream() {
    return new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        throw new IOException("forced output failure");
      }

      @Override
      public void write(byte[] b, int off, int len) throws IOException {
        throw new IOException("forced output failure");
      }

      @Override
      public void flush() throws IOException {
        throw new IOException("forced output failure");
      }
    };
  }

  private static String repeatedString(char value, int length) {
    char[] chars = new char[length];
    Arrays.fill(chars, value);
    return new String(chars);
  }

  private static File newTempFile(String prefix, String suffix) throws IOException {
    File file = Files.createTempFile("avro-dfw-p-" + prefix + "-", suffix).toFile();
    file.deleteOnExit();
    return file;
  }

  private static Throwable expectFailure(ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable throwable) {
      return throwable;
    }
    fail("Expected the operation to fail, but it completed successfully.");
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }
}
