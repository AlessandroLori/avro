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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableFileInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Black-box tests for {@link DataFileWriter}.
 *
 * <p>The suite is intentionally documentation-driven: it exercises the public
 * behaviours exposed by DataFileWriter, including lifecycle, creation overloads,
 * append, appendEncoded, appendAllFrom, appendTo, metadata, sync/flush/fsync,
 * codec configuration, sync interval boundaries and custom encoder wiring.</p>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestLLMFewShotDocDataFileWriter {

  private static final String USER_SCHEMA_JSON = "{"
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
  private static final byte[] EXPLICIT_SYNC_MARKER = new byte[] {
      1, 2, 3, 4, 5, 6, 7, 8,
      9, 10, 11, 12, 13, 14, 15, 16
  };

  @Mock
  private DatumWriter<GenericRecord> datumWriter;

  @Test
  public void appendShouldDelegateToDatumWriterWhenWriterIsOpen() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = newIntRecord(schema, 1);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      assertSame(dataFileWriter, dataFileWriter.create(schema, outputStream));
      dataFileWriter.append(record);
    }

    verify(datumWriter, times(1)).write(same(record), any(Encoder.class));
  }

  @Test
  public void appendShouldCallDatumWriterForEveryDatum() throws Exception {
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

  @Test
  public void appendShouldWrapDatumWriterIOExceptionInAppendWriteException() throws Exception {
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
        fail("Expected AppendWriteException when DatumWriter.write throws IOException.");
      } catch (DataFileWriter.AppendWriteException exception) {
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause().getMessage().contains("forced datum writer failure"));
      }
    }
  }

  @Test
  public void appendShouldAllowRecoveryAfterAppendWriteException() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord firstRecord = newIntRecord(schema, 1);
    GenericRecord secondRecord = newIntRecord(schema, 2);

    doThrow(new IOException("first write failed"))
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

  @Test
  public void appendBeforeCreateShouldFailBecauseWriterIsNotOpen() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    final DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema);
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          dataFileWriter.append(newIntRecord(schema, 1));
        }
      });
    } finally {
      closeQuietly(dataFileWriter);
    }
  }

  @Test
  public void appendAfterCloseShouldFail() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    GenericRecord record = newIntRecord(schema, 1);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    final DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema);
    dataFileWriter.create(schema, outputStream);
    dataFileWriter.close();

    assertOperationFails(new FailingOperation() {
      @Override
      public void run() throws Exception {
        dataFileWriter.append(record);
      }
    });
  }

  @Test
  public void createWithOutputStreamShouldWriteReadableContainerFile() throws Exception {
    Schema schema = parseSchema(USER_SCHEMA_JSON);
    GenericRecord record = newUserRecord(schema, 7, "Alice");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.create(schema, outputStream));
      dataFileWriter.append(record);
    }

    assertTrue(outputStream.size() > 0);
    assertEquals(Arrays.asList(7), readIds(outputStream.toByteArray()));
  }

  @Test
  public void createWithFileShouldWriteReadableContainerFile() throws Exception {
    Schema schema = parseSchema(USER_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-create-file-");

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.create(schema, tempFile));
      dataFileWriter.append(newUserRecord(schema, 8, "Bob"));
    }

    assertEquals(schema, readSchema(tempFile));
    assertEquals(Arrays.asList(8), readIds(tempFile));
  }

  @Test
  public void createWithExplicitSyncShouldWriteReadableContainerFile() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.create(schema, outputStream, EXPLICIT_SYNC_MARKER));
      dataFileWriter.append(newIntRecord(schema, 11));
    }

    assertEquals(Arrays.asList(11), readIds(outputStream.toByteArray()));
  }

  @Test
  public void appendShouldAcceptIntegerBoundaryValuesInRecord() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-int-boundaries-");

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newIntRecord(schema, Integer.MIN_VALUE));
      dataFileWriter.append(newIntRecord(schema, 0));
      dataFileWriter.append(newIntRecord(schema, Integer.MAX_VALUE));
    }

    assertEquals(Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE), readIds(tempFile));
  }

  @Test
  public void appendShouldAcceptEmptyAndUnicodeStringValues() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-string-values-");

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newStringRecord(schema, ""));
      dataFileWriter.append(newStringRecord(schema, UNICODE_LABEL));
    }

    assertEquals(Arrays.asList("", UNICODE_LABEL), readLabels(tempFile));
  }

  @Test
  public void appendShouldRejectWrongFieldType() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    final GenericRecord record = new GenericData.Record(schema);
    record.put("id", "not-an-int");

    try (final DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, new ByteArrayOutputStream());

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          dataFileWriter.append(record);
        }
      });
    }
  }

  @Test
  public void appendShouldRejectNullForRequiredField() throws Exception {
    Schema schema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    final GenericRecord record = new GenericData.Record(schema);
    record.put("label", null);

    try (final DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, new ByteArrayOutputStream());

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          dataFileWriter.append(record);
        }
      });
    }
  }

  @Test
  public void appendShouldAcceptNullWhenSchemaIsNullableUnion() throws Exception {
    Schema schema = parseSchema(NULLABLE_STRING_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-nullable-union-");

    try (DataFileWriter<Object> dataFileWriter = newObjectDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(null);
      dataFileWriter.append("value");
    }

    List<Object> values = readObjects(tempFile);
    assertEquals(2, values.size());
    assertEquals(null, values.get(0));
    assertEquals("value", values.get(1).toString());
  }

  @Test
  public void appendShouldRejectNullWhenSchemaIsNotNullable() throws Exception {
    Schema schema = Schema.create(Schema.Type.STRING);

    try (final DataFileWriter<Object> dataFileWriter = newObjectDataFileWriter(schema)) {
      dataFileWriter.create(schema, new ByteArrayOutputStream());

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          dataFileWriter.append(null);
        }
      });
    }
  }

  @Test
  public void appendToFileShouldAppendRecordsToExistingAvroFile() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-append-to-file-");

    writeIds(tempFile, schema, 1, 2);

    try (DataFileWriter<GenericRecord> appender = newDataFileWriter(schema).appendTo(tempFile)) {
      appender.append(newIntRecord(schema, 3));
      appender.append(newIntRecord(schema, 4));
    }

    assertEquals(Arrays.asList(1, 2, 3, 4), readIds(tempFile));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamShouldAppendWithoutClosingInput() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-append-to-seekable-");

    writeIds(tempFile, schema, 10);

    SeekableFileInput seekableInput = new SeekableFileInput(tempFile);
    try {
      OutputStream positionedOutput = new FileOutputStream(tempFile, true);
      try (DataFileWriter<GenericRecord> appender =
               newDataFileWriter(schema).appendTo(seekableInput, positionedOutput)) {
        appender.append(newIntRecord(schema, 20));
      }

      assertTrue("appendTo(SeekableInput, OutputStream) must not close the input.",
          seekableInput.length() > 0);
    } finally {
      seekableInput.close();
    }

    assertEquals(Arrays.asList(10, 20), readIds(tempFile));
  }

  @Test
  public void setMetaShouldPersistStringLongAndBytesMetadata() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-meta-");
    byte[] rawMetadata = new byte[] {9, 8, 7, 6};

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.setMeta("test.string", "alpha"));
      assertSame(dataFileWriter, dataFileWriter.setMeta("test.long", 123456789L));
      assertSame(dataFileWriter, dataFileWriter.setMeta("test.bytes", rawMetadata));
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newIntRecord(schema, 1));
    }

    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(tempFile, new GenericDatumReader<GenericRecord>())) {
      assertEquals("alpha", dataFileReader.getMetaString("test.string"));
      assertEquals(123456789L, dataFileReader.getMetaLong("test.long"));
      assertArrayEquals(rawMetadata, dataFileReader.getMeta("test.bytes"));
      assertTrue(dataFileReader.getMetaKeys().contains("test.string"));
      assertTrue(dataFileReader.getMetaKeys().contains("test.long"));
      assertTrue(dataFileReader.getMetaKeys().contains("test.bytes"));
    }
  }

  @Test
  public void isReservedMetaShouldIdentifyAvroInternalKeysOnly() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertFalse(DataFileWriter.isReservedMeta("application.owner"));
    assertFalse(DataFileWriter.isReservedMeta("test.string"));
  }

  @Test
  public void setCodecShouldWriteReadableDeflateCompressedFile() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-codec-");

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.setCodec(CodecFactory.deflateCodec(1)));
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newIntRecord(schema, 100));
      dataFileWriter.append(newIntRecord(schema, 200));
    }

    assertEquals(Arrays.asList(100, 200), readIds(tempFile));
  }

  @Test
  public void setCodecAfterWritesHaveBegunShouldFail() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    try (final DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, new ByteArrayOutputStream());
      dataFileWriter.append(newIntRecord(schema, 1));

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          dataFileWriter.setCodec(CodecFactory.deflateCodec(1));
        }
      });
    }
  }

  @Test
  public void setSyncIntervalShouldAcceptDocumentedBoundaryValues() {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema);

    try {
      assertSame(dataFileWriter, dataFileWriter.setSyncInterval(32));
      assertSame(dataFileWriter, dataFileWriter.setSyncInterval(2 * 1024));
      assertSame(dataFileWriter, dataFileWriter.setSyncInterval(2 * 1024 * 1024));
      assertSame(dataFileWriter, dataFileWriter.setSyncInterval(1 << 30));
    } finally {
      closeQuietly(dataFileWriter);
    }
  }

  @Test
  public void setSyncIntervalShouldRejectValuesOutsideDocumentedRange() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);

    final DataFileWriter<GenericRecord> lowValueWriter = newDataFileWriter(schema);
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          lowValueWriter.setSyncInterval(31);
        }
      });
    } finally {
      closeQuietly(lowValueWriter);
    }

    final DataFileWriter<GenericRecord> highValueWriter = newDataFileWriter(schema);
    try {
      assertOperationFails(new FailingOperation() {
        @Override
        public void run() {
          highValueWriter.setSyncInterval((1 << 30) + 1);
        }
      });
    } finally {
      closeQuietly(highValueWriter);
    }
  }

  @Test
  public void setFlushOnEveryBlockShouldToggleObservableState() {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema);

    try {
      assertTrue(dataFileWriter.isFlushOnEveryBlock());
      dataFileWriter.setFlushOnEveryBlock(false);
      assertFalse(dataFileWriter.isFlushOnEveryBlock());
      dataFileWriter.setFlushOnEveryBlock(true);
      assertTrue(dataFileWriter.isFlushOnEveryBlock());
    } finally {
      closeQuietly(dataFileWriter);
    }
  }

  @Test
  public void setEncoderShouldUseProvidedEncoderFactoryFunction() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    final AtomicInteger encoderCreations = new AtomicInteger(0);
    File tempFile = newTempAvroFile("dfw-custom-encoder-");

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      assertSame(dataFileWriter, dataFileWriter.setEncoder(outputStream -> {
        encoderCreations.incrementAndGet();
        return EncoderFactory.get().binaryEncoder(outputStream, null);
      }));
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newIntRecord(schema, 1));
    }

    assertTrue("Expected DataFileWriter to create at least one encoder through the provided function.",
        encoderCreations.get() > 0);
    assertEquals(Arrays.asList(1), readIds(tempFile));
  }

  @Test
  public void syncShouldReturnPositionUsableByDataFileReaderSeek() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File tempFile = newTempAvroFile("dfw-sync-seek-");
    long syncPosition;

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.append(newIntRecord(schema, 1));
      syncPosition = dataFileWriter.sync();
      dataFileWriter.append(newIntRecord(schema, 2));
    }

    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(tempFile, new GenericDatumReader<GenericRecord>())) {
      dataFileReader.seek(syncPosition);
      assertTrue(dataFileReader.hasNext());
      assertEquals(2, dataFileReader.next().get("id"));
      assertFalse(dataFileReader.hasNext());
    }
  }

  @Test
  public void flushShouldFlushUnderlyingOutputWithoutClosingIt() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    TrackingOutputStream outputStream = new TrackingOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(newIntRecord(schema, 1));

      int flushCountBefore = outputStream.getFlushCount();
      dataFileWriter.flush();

      assertTrue(outputStream.getFlushCount() > flushCountBefore);
      assertFalse(outputStream.isClosed());
    }
  }

  @Test
  public void fSyncShouldBehaveLikeFlushForNonSyncableOutputStream() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    TrackingOutputStream outputStream = new TrackingOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(newIntRecord(schema, 1));

      int flushCountBefore = outputStream.getFlushCount();
      dataFileWriter.fSync();

      assertTrue(outputStream.getFlushCount() > flushCountBefore);
      assertFalse(outputStream.isClosed());
    }
  }

  @Test
  public void closeShouldFlushAndCloseUnderlyingOutputStream() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    TrackingOutputStream outputStream = new TrackingOutputStream();
    DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema);

    dataFileWriter.create(schema, outputStream);
    dataFileWriter.append(newIntRecord(schema, 1));
    dataFileWriter.close();

    assertTrue(outputStream.getFlushCount() > 0);
    assertTrue(outputStream.isClosed());
  }

  @Test
  public void appendEncodedShouldWritePreEncodedDatum() throws Exception {
    Schema schema = Schema.create(Schema.Type.INT);
    File tempFile = newTempAvroFile("dfw-append-encoded-");
    ByteBuffer encodedDatum = encodeInt(42);

    try (DataFileWriter<Object> dataFileWriter = newObjectDataFileWriter(schema)) {
      dataFileWriter.create(schema, tempFile);
      dataFileWriter.appendEncoded(encodedDatum);
    }

    assertEquals(Arrays.asList(42), readPrimitiveInts(tempFile));
  }

  @Test
  public void appendAllFromShouldCopyRecordsFromAnotherFileWithoutRecompression() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File sourceFile = newTempAvroFile("dfw-append-all-source-");
    File targetFile = newTempAvroFile("dfw-append-all-target-");

    writeIds(sourceFile, schema, 2, 3);
    writeIds(targetFile, schema, 1);

    try (DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(sourceFile, new GenericDatumReader<GenericRecord>());
         DataFileWriter<GenericRecord> targetWriter = newDataFileWriter(schema).appendTo(targetFile)) {
      targetWriter.appendAllFrom(sourceReader, false);
    }

    assertEquals(Arrays.asList(1, 2, 3), readIds(targetFile));
  }

  @Test
  public void appendAllFromShouldCopyRecordsWhenRecompressionIsRequested() throws Exception {
    Schema schema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File sourceFile = newTempAvroFile("dfw-append-all-recompress-source-");
    File targetFile = newTempAvroFile("dfw-append-all-recompress-target-");

    try (DataFileWriter<GenericRecord> sourceWriter = newDataFileWriter(schema)) {
      sourceWriter.setCodec(CodecFactory.deflateCodec(1));
      sourceWriter.create(schema, sourceFile);
      sourceWriter.append(newIntRecord(schema, 20));
      sourceWriter.append(newIntRecord(schema, 30));
    }

    try (DataFileWriter<GenericRecord> targetWriter = newDataFileWriter(schema)) {
      targetWriter.setCodec(CodecFactory.deflateCodec(9));
      targetWriter.create(schema, targetFile);
      targetWriter.append(newIntRecord(schema, 10));
    }

    try (DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(sourceFile, new GenericDatumReader<GenericRecord>());
         DataFileWriter<GenericRecord> targetWriter = newDataFileWriter(schema).appendTo(targetFile)) {
      targetWriter.appendAllFrom(sourceReader, true);
    }

    assertEquals(Arrays.asList(10, 20, 30), readIds(targetFile));
  }

  @Test
  public void appendAllFromShouldRejectFilesWithDifferentSchemas() throws Exception {
    Schema sourceSchema = parseSchema(STRING_RECORD_SCHEMA_JSON);
    Schema targetSchema = parseSchema(INT_RECORD_SCHEMA_JSON);
    File sourceFile = newTempAvroFile("dfw-append-all-bad-source-");
    File targetFile = newTempAvroFile("dfw-append-all-bad-target-");

    try (DataFileWriter<GenericRecord> sourceWriter = newDataFileWriter(sourceSchema)) {
      sourceWriter.create(sourceSchema, sourceFile);
      sourceWriter.append(newStringRecord(sourceSchema, "not-compatible"));
    }

    writeIds(targetFile, targetSchema, 1);

    try (final DataFileReader<GenericRecord> sourceReader =
             new DataFileReader<>(sourceFile, new GenericDatumReader<GenericRecord>());
         final DataFileWriter<GenericRecord> targetWriter =
             newDataFileWriter(targetSchema).appendTo(targetFile)) {

      assertOperationFails(new FailingOperation() {
        @Override
        public void run() throws Exception {
          targetWriter.appendAllFrom(sourceReader, false);
        }
      });
    }
  }

  @Test
  public void dataFileStreamReaderShouldObserveSchemaAndMetadataWrittenByWriter() throws Exception {
    Schema schema = parseSchema(USER_SCHEMA_JSON);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.setMeta("suite", "blackbox");
      dataFileWriter.create(schema, outputStream);
      dataFileWriter.append(newUserRecord(schema, 5, "Carol"));
    }

    try (DataFileStream<GenericRecord> dataFileStream =
             new DataFileStream<>(new java.io.ByteArrayInputStream(outputStream.toByteArray()),
                 new GenericDatumReader<GenericRecord>())) {
      assertEquals(schema, dataFileStream.getSchema());
      assertEquals("blackbox", dataFileStream.getMetaString("suite"));
      assertTrue(dataFileStream.hasNext());
      assertEquals("Carol", dataFileStream.next().get("label").toString());
      assertFalse(dataFileStream.hasNext());
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

  private static DataFileWriter<Object> newObjectDataFileWriter(Schema schema) {
    return new DataFileWriter<>(new GenericDatumWriter<Object>(schema));
  }

  private static File newTempAvroFile(String prefix) throws IOException {
    File tempFile = Files.createTempFile(prefix, ".avro").toFile();
    tempFile.deleteOnExit();
    return tempFile;
  }

  private static void writeIds(File file, Schema schema, int... ids) throws IOException {
    try (DataFileWriter<GenericRecord> dataFileWriter = newDataFileWriter(schema)) {
      dataFileWriter.create(schema, file);
      for (int id : ids) {
        dataFileWriter.append(newIntRecord(schema, id));
      }
    }
  }

  private static Schema readSchema(File file) throws IOException {
    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      return dataFileReader.getSchema();
    }
  }

  private static List<Integer> readIds(File file) throws IOException {
    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      return collectIds(dataFileReader);
    }
  }

  private static List<Integer> readIds(byte[] bytes) throws IOException {
    try (DataFileStream<GenericRecord> dataFileStream =
             new DataFileStream<>(new java.io.ByteArrayInputStream(bytes),
                 new GenericDatumReader<GenericRecord>())) {
      return collectIds(dataFileStream);
    }
  }

  private static List<Integer> collectIds(DataFileStream<GenericRecord> dataFileStream)
      throws IOException {
    List<Integer> ids = new ArrayList<>();
    while (dataFileStream.hasNext()) {
      ids.add(((Number) dataFileStream.next().get("id")).intValue());
    }
    return ids;
  }

  private static List<String> readLabels(File file) throws IOException {
    List<String> labels = new ArrayList<>();
    try (DataFileReader<GenericRecord> dataFileReader =
             new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      while (dataFileReader.hasNext()) {
        labels.add(dataFileReader.next().get("label").toString());
      }
    }
    return labels;
  }

  private static List<Object> readObjects(File file) throws IOException {
    List<Object> values = new ArrayList<>();
    try (DataFileReader<Object> dataFileReader =
             new DataFileReader<>(file, new GenericDatumReader<Object>())) {
      while (dataFileReader.hasNext()) {
        values.add(dataFileReader.next());
      }
    }
    return values;
  }

  private static List<Integer> readPrimitiveInts(File file) throws IOException {
    List<Integer> values = new ArrayList<>();
    try (DataFileReader<Integer> dataFileReader =
             new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      while (dataFileReader.hasNext()) {
        values.add(dataFileReader.next());
      }
    }
    return values;
  }

  private static ByteBuffer encodeInt(int value) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
    encoder.writeInt(value);
    encoder.flush();
    return ByteBuffer.wrap(outputStream.toByteArray());
  }

  private static void assertOperationFails(FailingOperation operation) throws Exception {
    try {
      operation.run();
      fail("Expected operation to fail.");
    } catch (Exception exception) {
      assertNotNull(exception);
    }
  }

  private static void closeQuietly(DataFileWriter<?> dataFileWriter) {
    try {
      dataFileWriter.close();
    } catch (IOException | RuntimeException ignored) {
      // Intentionally ignored by tests that close a writer which might not be open.
    }
  }

  private interface FailingOperation {
    void run() throws Exception;
  }

  private static final class TrackingOutputStream extends OutputStream {
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

    @SuppressWarnings("unused")
    byte[] toByteArray() {
      return delegate.toByteArray();
    }

    private void ensureOpen() throws IOException {
      if (closed) {
        throw new IOException("stream already closed");
      }
    }
  }
}
