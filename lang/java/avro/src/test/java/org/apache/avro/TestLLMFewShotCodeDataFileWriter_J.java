/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.avro;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileConstants;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.file.Syncable;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.junit.Test;

public class TestLLMFewShotCodeDataFileWriter_J {

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

  @Test
  public void isReservedMetaShouldRecognizeOnlyAvroPrefixedKeys() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertTrue(DataFileWriter.isReservedMeta("avro.custom"));
    assertFalse(DataFileWriter.isReservedMeta("owner"));
    assertFalse(DataFileWriter.isReservedMeta("x-avro.schema"));
  }

  @Test
  public void setMetaShouldPersistStringLongAndBytesMetadata() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("metadata");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.setMeta("owner", "tester"));
      assertSame(writer, writer.setMeta("build", 42L));
      assertSame(writer, writer.setMeta("raw", new byte[] { 1, 2, 3 }));
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 7));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      assertEquals("tester", metaAsString(reader, "owner"));
      assertEquals("42", metaAsString(reader, "build"));
      assertArrayEquals(new byte[] { 1, 2, 3 }, metaBytes(reader, "raw"));
      assertEquals(7, reader.next().get("id"));
    }
  }

  @Test
  public void setMetaShouldRejectReservedKeys() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() {
          writer.setMeta("avro.schema", "not allowed");
        }
      });
    }
  }

  @Test
  public void setMetaShouldFailAfterWriterIsOpen() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() {
          writer.setMeta("owner", "late");
        }
      });
    }
  }

  @Test
  public void setCodecShouldPersistCodecMetadataAndKeepFileReadable() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("codec");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.setCodec(CodecFactory.deflateCodec(1)));
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 11));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      assertEquals("deflate", metaAsString(reader, DataFileConstants.CODEC));
      assertEquals(11, reader.next().get("id"));
    }
  }

  @Test
  public void setCodecShouldFailAfterWriterIsOpen() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() {
          writer.setCodec(CodecFactory.nullCodec());
        }
      });
    }
  }

  @Test
  public void setSyncIntervalShouldAcceptDocumentedBoundariesBeforeOpen() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.setSyncInterval(32));
    }

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.setSyncInterval(1 << 30));
    }
  }

  @Test
  public void setSyncIntervalShouldRejectValuesOutsideDocumentedRange() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertIllegalArgumentException(new ThrowingRunnable() {
        @Override
        public void run() {
          writer.setSyncInterval(31);
        }
      });
      assertIllegalArgumentException(new ThrowingRunnable() {
        @Override
        public void run() {
          writer.setSyncInterval((1 << 30) + 1);
        }
      });
    }
  }

  @Test
  public void setEncoderShouldUseCustomEncoderFactoryDuringInitialization() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    final AtomicInteger factoryInvocations = new AtomicInteger(0);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.setEncoder(new java.util.function.Function<OutputStream, BinaryEncoder>() {
        @Override
        public BinaryEncoder apply(OutputStream output) {
          factoryInvocations.incrementAndGet();
          return EncoderFactory.get().directBinaryEncoder(output, null);
        }
      }));
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 5));
    }

    assertEquals(1, factoryInvocations.get());
    assertTrue(outputStream.size() > 0);
  }

  @Test
  public void createWithOutputStreamShouldProduceReadableAvroContainer() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.create(schema, outputStream));
      writer.append(newIntRecord(schema, 1));
    }

    File file = writeBytesToTempFile("create-output-stream", outputStream.toByteArray());
    assertEquals(Arrays.asList(1), readIds(file));
  }

  @Test
  public void createWithFileShouldProduceReadableAvroContainer() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("create-file");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.create(schema, file));
      writer.append(newIntRecord(schema, 2));
    }

    assertEquals(Arrays.asList(2), readIds(file));
  }

  @Test
  public void createWithExplicitSyncShouldStoreProvidedSixteenByteMarker() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("explicit-sync");
    byte[] sync = new byte[] {
        0, 1, 2, 3,
        4, 5, 6, 7,
        8, 9, 10, 11,
        12, 13, 14, 15
    };

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new FileOutputStream(file), sync);
      writer.append(newIntRecord(schema, 3));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      assertTrue("Expected the explicit sync marker to be written in the container file.",
          containsSubsequence(Files.readAllBytes(file.toPath()), sync));
      assertEquals(3, reader.next().get("id"));
    }
  }

  @Test
  public void createWithExplicitSyncShouldRejectNonSixteenByteMarker() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertIOException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.create(schema, new ByteArrayOutputStream(), new byte[15]);
        }
      });
    }
  }

  @Test
  public void createShouldRejectAlreadyOpenWriter() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.create(schema, new ByteArrayOutputStream());
        }
      });
    }
  }

  @Test
  public void appendShouldRequireOpenWriter() throws Exception {
    final Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.append(newIntRecord(schema, 1));
        }
      });
    }
  }

  @Test
  public void appendShouldWriteSingleRecord() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("append-single");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 10));
    }

    assertEquals(Arrays.asList(10), readIds(file));
  }

  @Test
  public void appendShouldPreserveMultipleRecordsInOrder() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("append-many");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 1));
      writer.append(newIntRecord(schema, 2));
      writer.append(newIntRecord(schema, 3));
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(file));
  }

  @Test
  public void appendShouldWrapIOExceptionAndDiscardPartiallyEncodedDatum() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("append-io-recovery");

    try (DataFileWriter<GenericRecord> writer = new DataFileWriter<>(new FailingOnceAfterPartialWriteDatumWriter(
        new IOException("forced io failure")))) {
      writer.create(schema, file);

      try {
        writer.append(newIntRecord(schema, 1));
        fail("Expected the first append to fail.");
      } catch (DataFileWriter.AppendWriteException exception) {
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause().getMessage().contains("forced io failure"));
      }

      writer.append(newIntRecord(schema, 2));
    }

    assertEquals("The partially encoded first datum must not corrupt the file.", Arrays.asList(2), readIds(file));
  }

  @Test
  public void appendShouldWrapRuntimeExceptionAndDiscardPartiallyEncodedDatum() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("append-runtime-recovery");

    try (DataFileWriter<GenericRecord> writer = new DataFileWriter<>(new FailingOnceAfterPartialWriteDatumWriter(
        new IllegalStateException("forced runtime failure")))) {
      writer.create(schema, file);

      try {
        writer.append(newIntRecord(schema, 1));
        fail("Expected the first append to fail.");
      } catch (DataFileWriter.AppendWriteException exception) {
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause().getMessage().contains("forced runtime failure"));
      }

      writer.append(newIntRecord(schema, 2));
    }

    assertEquals("The partially encoded first datum must not corrupt the file.", Arrays.asList(2), readIds(file));
  }

  @Test
  public void appendShouldRejectDatumThatDoesNotMatchSchema() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());
      assertAppendWriteException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.append(newStringRecord(schema, null));
        }
      });
    }
  }

  @Test
  public void appendEncodedShouldWritePreEncodedDatumWithoutUsingDatumWriterValidation() throws Exception {
    Schema schema = Schema.create(Schema.Type.INT);
    File file = newTempFile("append-encoded");
    ByteBuffer encodedInt = encodeInt(12345);

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>(schema))) {
      writer.create(schema, file);
      writer.appendEncoded(encodedInt);
    }

    try (DataFileReader<Integer> reader = new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      assertTrue(reader.hasNext());
      assertEquals(Integer.valueOf(12345), reader.next());
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void appendEncodedShouldRequireOpenWriter() throws Exception {
    Schema schema = Schema.create(Schema.Type.INT);

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>(schema))) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.appendEncoded(ByteBuffer.wrap(new byte[] { 0 }));
        }
      });
    }
  }

  @Test
  public void appendToFileShouldAppendRecordsToExistingContainer() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = createFileWithIds(schema, "append-to-file", 1, 2);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertSame(writer, writer.appendTo(file));
      writer.append(newIntRecord(schema, 3));
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(file));
  }

  @Test
  public void appendToSeekableInputShouldAppendAndLeaveInputOpenForCaller() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = createFileWithIds(schema, "append-to-seekable", 4);
    SeekableFileInput input = new SeekableFileInput(file);

    try {
      try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
        assertSame(writer, writer.appendTo(input, new FileOutputStream(file, true)));
        writer.append(newIntRecord(schema, 5));
      }

      assertTrue("appendTo(SeekableInput, OutputStream) must not close the input.", input.length() > 0);
    } finally {
      input.close();
    }

    assertEquals(Arrays.asList(4, 5), readIds(file));
  }

  @Test
  public void appendToShouldRejectAlreadyOpenWriter() throws Exception {
    final Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    final File existingFile = createFileWithIds(schema, "append-to-open", 1);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new ByteArrayOutputStream());
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.appendTo(existingFile);
        }
      });
    }
  }

  @Test
  public void appendAllFromShouldCopyBlocksWhenSchemasMatch() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File source = createFileWithIds(schema, "append-all-source", 1, 2);
    File target = createFileWithIds(schema, "append-all-target", 100);

    try (DataFileReader<GenericRecord> sourceReader = newRecordReader(source);
         DataFileWriter<GenericRecord> targetWriter = newRecordWriter(schema)) {
      targetWriter.appendTo(target);
      targetWriter.appendAllFrom(sourceReader, false);
    }

    assertEquals(Arrays.asList(100, 1, 2), readIds(target));
  }

  @Test
  public void appendAllFromShouldSupportForcedRecompressionPath() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File source = createFileWithIds(schema, "append-all-recompress-source", 7, 8);
    File target = newTempFile("append-all-recompress-target");

    try (DataFileReader<GenericRecord> sourceReader = newRecordReader(source);
         DataFileWriter<GenericRecord> targetWriter = newRecordWriter(schema)) {
      targetWriter.create(schema, target);
      targetWriter.appendAllFrom(sourceReader, true);
    }

    assertEquals(Arrays.asList(7, 8), readIds(target));
  }

  @Test
  public void appendAllFromShouldRejectDifferentSchema() throws Exception {
    Schema targetSchema = parseSchema(INT_RECORD_SCHEMA_JSON);
    Schema sourceSchema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    File source = newTempFile("append-all-wrong-schema-source");

    try (DataFileWriter<GenericRecord> sourceWriter = newRecordWriter(sourceSchema)) {
      sourceWriter.create(sourceSchema, source);
      sourceWriter.append(newStringRecord(sourceSchema, "value"));
    }

    try (DataFileReader<GenericRecord> sourceReader = newRecordReader(source);
         DataFileWriter<GenericRecord> targetWriter = newRecordWriter(targetSchema)) {
      targetWriter.create(targetSchema, new ByteArrayOutputStream());
      assertIOException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          targetWriter.appendAllFrom(sourceReader, false);
        }
      });
    }
  }

  @Test
  public void appendAllFromShouldRequireOpenWriterBeforeInspectingOtherFile() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.appendAllFrom(null, false);
        }
      });
    }
  }

  @Test
  public void syncShouldReturnSeekablePositionAtNextBlock() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("sync-position");
    long secondBlockPosition;

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 1));
      secondBlockPosition = writer.sync();
      writer.append(newIntRecord(schema, 2));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      reader.seek(secondBlockPosition);
      assertTrue(reader.hasNext());
      assertEquals(2, reader.next().get("id"));
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void syncShouldRequireOpenWriter() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.sync();
        }
      });
    }
  }

  @Test
  public void flushShouldMakeAppendedDataReadableBeforeClose() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("flush-before-close");
    DataFileWriter<GenericRecord> writer = newRecordWriter(schema);

    try {
      writer.create(schema, new FileOutputStream(file));
      writer.append(newIntRecord(schema, 44));
      writer.flush();

      assertEquals(Arrays.asList(44), readIds(file));
    } finally {
      writer.close();
    }
  }

  @Test
  public void flushShouldRequireOpenWriter() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.flush();
        }
      });
    }
  }

  @Test
  public void fSyncShouldFlushAndInvokeSyncableStream() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    CountingSyncableOutputStream outputStream = new CountingSyncableOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 55));
      writer.fSync();
      assertEquals(1, outputStream.syncCount());
      assertTrue(outputStream.size() > 0);
    }
  }

  @Test
  public void fSyncShouldRequireOpenWriter() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertAvroRuntimeException(new ThrowingRunnable() {
        @Override
        public void run() throws Exception {
          writer.fSync();
        }
      });
    }
  }

  @Test
  public void setFlushOnEveryBlockShouldToggleObservableFlag() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      assertTrue(writer.isFlushOnEveryBlock());
      writer.setFlushOnEveryBlock(false);
      assertFalse(writer.isFlushOnEveryBlock());
      writer.setFlushOnEveryBlock(true);
      assertTrue(writer.isFlushOnEveryBlock());
    }
  }

  @Test
  public void closeShouldFlushCloseUnderlyingStreamAndBeIdempotent() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    CloseTrackingOutputStream outputStream = new CloseTrackingOutputStream();
    DataFileWriter<GenericRecord> writer = newRecordWriter(schema);

    writer.create(schema, outputStream);
    writer.append(newIntRecord(schema, 66));
    writer.close();
    writer.close();

    assertTrue(outputStream.isClosed());
    assertTrue(outputStream.size() > 0);

    assertAvroRuntimeException(new ThrowingRunnable() {
      @Override
      public void run() throws Exception {
        writer.append(newIntRecord(schema, 77));
      }
    });
  }


  @Test
  public void createWithFileShouldCloseStreamAndRethrowWhenNestedCreateFails() throws Exception {
    File file = newTempFile("create-file-failure");

    try (DataFileWriter<GenericRecord> writer = new DataFileWriter<>(new GenericDatumWriter<GenericRecord>())) {
      try {
        writer.create(null, file);
        fail("Expected create(File) to rethrow the failure raised by create(Schema, OutputStream, byte[]). ");
      } catch (NullPointerException expected) {
        assertNotNull(expected);
      }
    }

    assertTrue("The file stream opened by create(File) should have been closed after the failure.",
        file.delete() || !file.exists());
  }

  @Test
  public void appendToSeekableInputShouldRestoreCodecMetadataFromExistingDeflateContainer() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("append-to-deflate-codec");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.setCodec(CodecFactory.deflateCodec(1));
      writer.create(schema, file);
      writer.append(newIntRecord(schema, 10));
    }

    try (SeekableFileInput input = new SeekableFileInput(file);
         DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.appendTo(input, new FileOutputStream(file, true));
      writer.append(newIntRecord(schema, 20));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      assertEquals("deflate", metaAsString(reader, DataFileConstants.CODEC));
    }
    assertEquals(Arrays.asList(10, 20), readIds(file));
  }

  @Test
  public void appendShouldWriteBlockAutomaticallyWhenSyncIntervalIsReached() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    File file = newTempFile("automatic-block-write");
    char[] chars = new char[256];
    Arrays.fill(chars, 'x');
    String largeLabel = new String(chars);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.setSyncInterval(32);
      writer.create(schema, file);
      writer.append(newStringRecord(schema, largeLabel));
    }

    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      assertTrue(reader.hasNext());
      assertEquals(largeLabel, reader.next().get("label").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void appendAllFromShouldHandleEmptyMatchingSourceOnRawCopyPath() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File source = newTempFile("append-all-empty-source");
    File target = createFileWithIds(schema, "append-all-empty-target", 99);

    try (DataFileWriter<GenericRecord> sourceWriter = newRecordWriter(schema)) {
      sourceWriter.create(schema, source);
    }

    try (DataFileReader<GenericRecord> sourceReader = newRecordReader(source);
         DataFileWriter<GenericRecord> targetWriter = newRecordWriter(schema)) {
      targetWriter.appendTo(target);
      targetWriter.appendAllFrom(sourceReader, false);
    }

    assertEquals(Arrays.asList(99), readIds(target));
  }

  @Test
  public void fSyncShouldFlushOnlyWhenUnderlyingStreamIsNotSyncableOrFileOutputStream() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, outputStream);
      writer.append(newIntRecord(schema, 77));
      writer.fSync();
      assertTrue(outputStream.size() > 0);
    }
  }

  @Test
  public void fSyncShouldFlushAndSyncFileDescriptorForPlainFileOutputStream() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File file = newTempFile("fsync-file-output-stream");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, new FileOutputStream(file));
      writer.append(newIntRecord(schema, 88));
      writer.fSync();
    }

    assertEquals(Arrays.asList(88), readIds(file));
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

  private static DataFileWriter<GenericRecord> newRecordWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>(schema));
  }

  private static DataFileReader<GenericRecord> newRecordReader(File file) throws IOException {
    return new DataFileReader<>(file, new GenericDatumReader<GenericRecord>());
  }

  private static File newTempFile(String name) throws IOException {
    File file = Files.createTempFile("llm-data-file-writer-" + name + "-", ".avro").toFile();
    file.deleteOnExit();
    return file;
  }

  private static File writeBytesToTempFile(String name, byte[] bytes) throws IOException {
    File file = newTempFile(name);
    Files.write(file.toPath(), bytes);
    return file;
  }

  private static File createFileWithIds(Schema schema, String name, int... ids) throws IOException {
    File file = newTempFile(name);
    try (DataFileWriter<GenericRecord> writer = newRecordWriter(schema)) {
      writer.create(schema, file);
      for (int id : ids) {
        writer.append(newIntRecord(schema, id));
      }
    }
    return file;
  }

  private static List<Integer> readIds(File file) throws IOException {
    List<Integer> ids = new ArrayList<>();
    try (DataFileReader<GenericRecord> reader = newRecordReader(file)) {
      while (reader.hasNext()) {
        ids.add((Integer) reader.next().get("id"));
      }
    }
    return ids;
  }

  private static byte[] metaBytes(DataFileReader<?> reader, String key) {
    byte[] value = reader.getMeta(key);
    assertNotNull("Expected metadata key: " + key, value);
    return value;
  }

  private static boolean containsSubsequence(byte[] haystack, byte[] needle) {
    if (needle.length == 0) {
      return true;
    }
    for (int i = 0; i <= haystack.length - needle.length; i++) {
      boolean matches = true;
      for (int j = 0; j < needle.length; j++) {
        if (haystack[i + j] != needle[j]) {
          matches = false;
          break;
        }
      }
      if (matches) {
        return true;
      }
    }
    return false;
  }

  private static String metaAsString(DataFileReader<?> reader, String key) {
    return new String(metaBytes(reader, key), StandardCharsets.UTF_8);
  }

  private static ByteBuffer encodeInt(int value) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
    encoder.writeInt(value);
    encoder.flush();
    return ByteBuffer.wrap(outputStream.toByteArray());
  }

  private static void assertAvroRuntimeException(ThrowingRunnable runnable) throws Exception {
    try {
      runnable.run();
      fail("Expected AvroRuntimeException.");
    } catch (AvroRuntimeException expected) {
      assertNotNull(expected);
    }
  }

  private static void assertIllegalArgumentException(ThrowingRunnable runnable) throws Exception {
    try {
      runnable.run();
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException expected) {
      assertNotNull(expected);
    }
  }

  private static void assertIOException(ThrowingRunnable runnable) throws Exception {
    try {
      runnable.run();
      fail("Expected IOException.");
    } catch (IOException expected) {
      assertNotNull(expected);
    }
  }

  private static void assertAppendWriteException(ThrowingRunnable runnable) throws Exception {
    try {
      runnable.run();
      fail("Expected AppendWriteException.");
    } catch (DataFileWriter.AppendWriteException expected) {
      assertNotNull(expected);
    }
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class FailingOnceAfterPartialWriteDatumWriter implements DatumWriter<GenericRecord> {
    private final RuntimeException runtimeFailure;
    private final IOException ioFailure;
    private boolean failNextWrite = true;

    private FailingOnceAfterPartialWriteDatumWriter(IOException ioFailure) {
      this.ioFailure = ioFailure;
      this.runtimeFailure = null;
    }

    private FailingOnceAfterPartialWriteDatumWriter(RuntimeException runtimeFailure) {
      this.ioFailure = null;
      this.runtimeFailure = runtimeFailure;
    }

    @Override
    public void setSchema(Schema schema) {
      // The test writer intentionally performs only the minimal encoding needed
      // for the one-field int record used by the tests.
    }

    @Override
    public void write(GenericRecord datum, Encoder out) throws IOException {
      out.writeInt((Integer) datum.get("id"));
      if (failNextWrite) {
        failNextWrite = false;
        if (ioFailure != null) {
          throw ioFailure;
        }
        throw runtimeFailure;
      }
    }
  }

  private static final class CountingSyncableOutputStream extends ByteArrayOutputStream implements Syncable {
    private int syncCount;

    @Override
    public void sync() {
      syncCount++;
    }

    private int syncCount() {
      return syncCount;
    }
  }

  private static final class CloseTrackingOutputStream extends ByteArrayOutputStream {
    private boolean closed;

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }

    private boolean isClosed() {
      return closed;
    }
  }
}
