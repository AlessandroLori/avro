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
import static org.junit.Assert.fail;

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
 * Black-box tests for DataFileWriter append operations not covered by append(D datum).
 *
 * <p>The tests focus on documented public behaviours of appendTo(File),
 * appendTo(SeekableInput, OutputStream) and appendAllFrom(DataFileStream, boolean).
 */
public class TestDataFileWriter_J {
  private static final Schema ID_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"AppendIdRecord\","
      + "\"fields\":[{\"name\":\"id\",\"type\":\"int\"}]"
      + "}");

  private static final Schema LABEL_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"AppendLabelRecord\","
      + "\"fields\":[{\"name\":\"label\",\"type\":\"string\"}]"
      + "}");

  @Test
  public void appendToValidFileShouldPreserveOldRecordsAndAppendNewOne() throws Exception {
    File file = writeIdFile(1);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
      writer.appendTo(file);
      writer.append(idRecord(2));
    }

    assertEquals(Arrays.asList(1, 2), readIds(file));
  }

  @Test
  public void appendToEmptyValidFileShouldAllowAppendingFirstRecord() throws Exception {
    File file = writeIdFile();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
      writer.appendTo(file);
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
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo(file);
        }
      }
    });
  }

  @Test
  public void appendToNullFileShouldFail() throws Exception {
    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo((File) null);
        }
      }
    });
  }

  @Test
  public void appendToValidSeekableInputAndValidOutputShouldAppendRecord() throws Exception {
    File file = writeIdFile(1);

    try (SeekableInput input = new SeekableFileInput(file);
        OutputStream output = new FileOutputStream(file, true);
        DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
      writer.appendTo(input, output);
      writer.append(idRecord(2));
    }

    assertEquals(Arrays.asList(1, 2), readIds(file));
  }

  @Test
  public void appendToInvalidSeekableInputShouldFail() throws Exception {
    File file = newTempFile("invalid-seekable", ".txt");
    Files.write(file.toPath(), "not an avro file".getBytes(StandardCharsets.UTF_8));

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            OutputStream output = new ByteArrayOutputStream();
            DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo(input, output);
        }
      }
    });
  }

  @Test
  public void appendToNullSeekableInputShouldFail() throws Exception {
    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (OutputStream output = new ByteArrayOutputStream();
            DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo((SeekableInput) null, output);
        }
      }
    });
  }

  @Test
  public void appendToInvalidOutputStreamShouldFail() throws Exception {
    File file = writeIdFile(1);

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo(input, failingOutputStream());
          writer.append(idRecord(2));
          writer.close();
        }
      }
    });
  }

  @Test
  public void appendToNullOutputStreamShouldFail() throws Exception {
    File file = writeIdFile(1);

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (SeekableInput input = new SeekableFileInput(file);
            DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.appendTo(input, null);
          writer.append(idRecord(2));
          writer.close();
        }
      }
    });
  }

  @Test
  public void appendAllFromValidSourceShouldCopyOneRecord() throws Exception {
    File destination = newTempFile("append-all-destination-one", ".avro");
    File source = writeIdFile(2);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA);
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      writer.create(ID_SCHEMA, destination);
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Arrays.asList(1, 2), readIds(destination));
  }

  @Test
  public void appendAllFromValidSourceShouldCopyMultipleRecordsPreservingOrder() throws Exception {
    File destination = newTempFile("append-all-destination-multiple", ".avro");
    File source = writeIdFile(2, 3);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA);
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      writer.create(ID_SCHEMA, destination);
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(destination));
  }

  @Test
  public void appendAllFromValidEmptySourceShouldLeaveDestinationReadable() throws Exception {
    File destination = newTempFile("append-all-destination-empty-source", ".avro");
    File source = writeIdFile();

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA);
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      writer.create(ID_SCHEMA, destination);
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, false);
    }

    assertEquals(Collections.singletonList(1), readIds(destination));
  }

  @Test
  public void appendAllFromInvalidSourceSchemaShouldFail() throws Exception {
    File destination = newTempFile("append-all-schema-mismatch-destination", ".avro");
    File source = writeLabelFile("two");

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA);
            DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
          writer.create(ID_SCHEMA, destination);
          writer.append(idRecord(1));
          writer.appendAllFrom(sourceStream, false);
        }
      }
    });
  }

  @Test
  public void appendAllFromNullSourceShouldFail() throws Exception {
    File destination = newTempFile("append-all-null-source-destination", ".avro");

    expectFailure(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
          writer.create(ID_SCHEMA, destination);
          writer.append(idRecord(1));
          writer.appendAllFrom(null, false);
        }
      }
    });
  }

  @Test
  public void appendAllFromWithRecompressTrueShouldStillCopyRecords() throws Exception {
    File destination = newTempFile("append-all-recompress-destination", ".avro");
    File source = writeIdFile(2);

    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA);
        DataFileStream<GenericRecord> sourceStream = newDataFileStream(source)) {
      writer.create(ID_SCHEMA, destination);
      writer.append(idRecord(1));
      writer.appendAllFrom(sourceStream, true);
    }

    assertEquals(Arrays.asList(1, 2), readIds(destination));
  }

  private static DataFileWriter<GenericRecord> newDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>(schema));
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

  private static File writeIdFile(int... ids) throws IOException {
    File file = newTempFile("id-records", ".avro");
    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(ID_SCHEMA)) {
      writer.create(ID_SCHEMA, file);
      for (int id : ids) {
        writer.append(idRecord(id));
      }
    }
    return file;
  }

  private static File writeLabelFile(String... labels) throws IOException {
    File file = newTempFile("label-records", ".avro");
    try (DataFileWriter<GenericRecord> writer = newDataFileWriter(LABEL_SCHEMA)) {
      writer.create(LABEL_SCHEMA, file);
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

  private static File newTempFile(String prefix, String suffix) throws IOException {
    File file = Files.createTempFile("avro-dfw-j-" + prefix + "-", suffix).toFile();
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
