package org.apache.avro.file;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
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

/**
 * Comprehensive JUnit 4 tests for DataFileWriter_C2.java, whose public class is
 * org.apache.avro.file.DataFileWriter.
 */
public class TestToTSDataFileWriterC2 {
  @Rule
  public TemporaryFolder tmp = new TemporaryFolder();

  private static final Schema RECORD_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"DataFileWriterC2Record\","
      + "\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]"
      + "}");

  private static final Schema OTHER_RECORD_SCHEMA = new Schema.Parser().parse("{"
      + "\"type\":\"record\","
      + "\"name\":\"DataFileWriterC2OtherRecord\","
      + "\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]"
      + "}");

  private static final Schema INT_SCHEMA = Schema.create(Schema.Type.INT);

  private static DataFileWriter<GenericRecord> newRecordWriter() {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>());
  }

  private static GenericRecord record(String name) {
    GenericRecord record = new GenericData.Record(RECORD_SCHEMA);
    record.put("name", name);
    return record;
  }

  private static GenericRecord otherRecord(String name) {
    GenericRecord record = new GenericData.Record(OTHER_RECORD_SCHEMA);
    record.put("name", name);
    return record;
  }

  private File writeRecordFile(String... names) throws IOException {
    File file = tmp.newFile();
    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      for (String name : names) {
        writer.append(record(name));
      }
    }
    return file;
  }

  private File writeOtherRecordFile(String... names) throws IOException {
    File file = tmp.newFile();
    try (DataFileWriter<GenericRecord> writer = new DataFileWriter<>(new GenericDatumWriter<GenericRecord>())) {
      writer.create(OTHER_RECORD_SCHEMA, file);
      for (String name : names) {
        writer.append(otherRecord(name));
      }
    }
    return file;
  }

  private static List<String> readNames(File file) throws IOException {
    List<String> names = new ArrayList<>();
    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      while (reader.hasNext()) {
        names.add(reader.next().get("name").toString());
      }
    }
    return names;
  }

  private static List<Integer> readInts(File file) throws IOException {
    List<Integer> values = new ArrayList<>();
    try (DataFileReader<Integer> reader = new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      while (reader.hasNext()) {
        values.add(reader.next());
      }
    }
    return values;
  }

  private static byte[] encodeInt(int value) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
    encoder.writeInt(value);
    encoder.flush();
    return out.toByteArray();
  }

  private static int indexOf(byte[] haystack, byte[] needle) {
    outer:
    for (int i = 0; i <= haystack.length - needle.length; i++) {
      for (int j = 0; j < needle.length; j++) {
        if (haystack[i + j] != needle[j]) {
          continue outer;
        }
      }
      return i;
    }
    return -1;
  }

  @Test
  public void constructorDefaultsToFlushOnEveryBlock() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void setCodecBeforeCreateStoresCodecMetadataAndKeepsDataReadable() throws Exception {
    File file = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.setCodec(CodecFactory.deflateCodec(1)));
      writer.create(RECORD_SCHEMA, file);
      writer.append(record("compressed"));
    }

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("deflate", reader.getMetaString(DataFileConstants.CODEC));
      assertEquals("compressed", reader.next().get("name").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void setCodecAfterCreateThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, tmp.newFile());
    try {
      writer.setCodec(CodecFactory.nullCodec());
    } finally {
      writer.close();
    }
  }

  @Test
  public void setSyncIntervalAcceptsBoundaryValuesAndReturnsThis() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    assertSame(writer, writer.setSyncInterval(32));
    assertSame(writer, writer.setSyncInterval(1 << 30));
  }

  @Test(expected = IllegalArgumentException.class)
  public void setSyncIntervalRejectsTooSmallValue() {
    newRecordWriter().setSyncInterval(31);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setSyncIntervalRejectsTooLargeValue() {
    newRecordWriter().setSyncInterval((1 << 30) + 1);
  }

  @Test
  public void setSyncIntervalTriggersAutomaticBlockWriteWhenBufferReachesLimit() throws Exception {
    File file = tmp.newFile();
    String largeValue = "0123456789012345678901234567890123456789";

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.setSyncInterval(32);
      writer.create(RECORD_SCHEMA, file);
      writer.append(record(largeValue));
      writer.append(record("tail"));
    }

    assertEquals(Arrays.asList(largeValue, "tail"), readNames(file));
  }

  @Test
  public void setEncoderUsesCustomEncoderFactoryDuringCreate() throws Exception {
    AtomicBoolean invoked = new AtomicBoolean(false);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.setEncoder(stream -> {
        invoked.set(true);
        return EncoderFactory.get().directBinaryEncoder(stream, null);
      }));
      writer.create(RECORD_SCHEMA, out);
      writer.append(record("custom-encoder"));
    }

    assertTrue(invoked.get());
    try (DataFileStream<GenericRecord> reader = new DataFileStream<>(new ByteArrayInputStream(out.toByteArray()),
        new GenericDatumReader<GenericRecord>())) {
      assertEquals("custom-encoder", reader.next().get("name").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test(expected = NullPointerException.class)
  public void setEncoderWithNullFunctionFailsWhenCreateInitializesBufferEncoder() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.setEncoder(null);
    writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
  }

  @Test
  public void createWithFileWritesReadableFile() throws Exception {
    File file = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.create(RECORD_SCHEMA, file));
      writer.append(record("file"));
    }

    assertEquals(Arrays.asList("file"), readNames(file));
  }

  @Test
  public void createWithOutputStreamWritesReadableStream() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.create(RECORD_SCHEMA, out));
      writer.append(record("stream"));
    }

    try (DataFileStream<GenericRecord> reader = new DataFileStream<>(new ByteArrayInputStream(out.toByteArray()),
        new GenericDatumReader<GenericRecord>())) {
      assertEquals("stream", reader.next().get("name").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void createWithExplicitSyncWritesProvidedSixteenByteMarker() throws Exception {
    byte[] explicitSync = "1234567890abcdef".getBytes(UTF_8);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.create(RECORD_SCHEMA, out, explicitSync));
    }

    assertTrue(indexOf(out.toByteArray(), explicitSync) >= 0);
  }

  @Test(expected = IOException.class)
  public void createWithExplicitSyncRejectsNonSixteenByteMarker() throws Exception {
    newRecordWriter().create(RECORD_SCHEMA, new ByteArrayOutputStream(), new byte[15]);
  }

  @Test(expected = AvroRuntimeException.class)
  public void createWhenAlreadyOpenThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
    try {
      writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
    } finally {
      writer.close();
    }
  }

  @Test
  public void setFlushOnEveryBlockTogglesGetter() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.setFlushOnEveryBlock(false);
    assertFalse(writer.isFlushOnEveryBlock());
    writer.setFlushOnEveryBlock(true);
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void setFlushOnEveryBlockFalseDefersStreamFlushUntilFlushIsCalled() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.setFlushOnEveryBlock(false);
      writer.create(RECORD_SCHEMA, out);
      int headerSize = out.size();
      writer.append(record("deferred"));
      writer.sync();
      assertEquals(headerSize, out.size());
      writer.flush();
      assertTrue(out.size() > headerSize);
    }
  }

  @Test
  public void appendToFileAddsRecordsToExistingFile() throws Exception {
    File file = writeRecordFile("first");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.appendTo(file));
      writer.append(record("second"));
    }

    assertEquals(Arrays.asList("first", "second"), readNames(file));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamAddsRecordsToExistingFile() throws Exception {
    File file = writeRecordFile("first");

    try (SeekableInput input = new SeekableFileInput(file);
        OutputStream output = new FileOutputStream(file, true);
        DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.appendTo(input, output));
      writer.append(record("second"));
    }

    assertEquals(Arrays.asList("first", "second"), readNames(file));
  }

  @Test(expected = AvroRuntimeException.class)
  public void appendToAfterCreateThrowsAlreadyOpen() throws Exception {
    File existing = writeRecordFile("existing");
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, tmp.newFile());
    try {
      writer.appendTo(existing);
    } finally {
      writer.close();
    }
  }

  @Test
  public void setMetaByteStringAndLongValuesAreStoredInHeader() throws Exception {
    File file = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      assertSame(writer, writer.setMeta("bytes-key", new byte[] { 1, 2, 3 }));
      assertSame(writer, writer.setMeta("string-key", "hello"));
      assertSame(writer, writer.setMeta("long-key", 42L));
      writer.create(RECORD_SCHEMA, file);
    }

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertArrayEquals(new byte[] { 1, 2, 3 }, reader.getMeta("bytes-key"));
      assertEquals("hello", reader.getMetaString("string-key"));
      assertEquals(42L, reader.getMetaLong("long-key"));
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void setMetaRejectsReservedAvroKey() {
    newRecordWriter().setMeta("avro.custom", "forbidden");
  }

  @Test(expected = AvroRuntimeException.class)
  public void setMetaAfterCreateThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
    try {
      writer.setMeta("late-key", "late-value");
    } finally {
      writer.close();
    }
  }

  @Test
  public void isReservedMetaIdentifiesAvroPrefixOnly() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertFalse(DataFileWriter.isReservedMeta("user.avro.schema"));
    assertFalse(DataFileWriter.isReservedMeta("metadata"));
  }

  @Test
  public void appendWritesGenericRecord() throws Exception {
    File file = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      writer.append(record("append"));
    }

    assertEquals(Arrays.asList("append"), readNames(file));
  }

  @Test(expected = AvroRuntimeException.class)
  public void appendBeforeCreateThrowsNotOpen() throws Exception {
    newRecordWriter().append(record("not-open"));
  }

  @Test
  public void appendWrapsWriterFailureAndKeepsFileAppendable() throws Exception {
    File file = tmp.newFile();
    FailingOnceIntDatumWriter datumWriter = new FailingOnceIntDatumWriter();

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(datumWriter)) {
      writer.create(INT_SCHEMA, file);
      try {
        writer.append(1);
        fail("append should wrap the DatumWriter IOException");
      } catch (DataFileWriter.AppendWriteException expected) {
        assertTrue(expected.getCause() instanceof IOException);
      }
      writer.append(2);
    }

    assertEquals(Arrays.asList(2), readInts(file));
  }

  @Test
  public void appendEncodedWritesPreEncodedDatum() throws Exception {
    File file = tmp.newFile();

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>())) {
      writer.create(INT_SCHEMA, file);
      writer.appendEncoded(ByteBuffer.wrap(encodeInt(7)));
    }

    assertEquals(Arrays.asList(7), readInts(file));
  }

  @Test
  public void appendEncodedHonorsByteBufferPositionAndLimit() throws Exception {
    File file = tmp.newFile();
    byte[] encoded = encodeInt(99);
    byte[] padded = new byte[encoded.length + 4];
    System.arraycopy(encoded, 0, padded, 2, encoded.length);
    ByteBuffer buffer = ByteBuffer.wrap(padded);
    buffer.position(2);
    buffer.limit(2 + encoded.length);

    try (DataFileWriter<Integer> writer = new DataFileWriter<>(new GenericDatumWriter<Integer>())) {
      writer.create(INT_SCHEMA, file);
      writer.appendEncoded(buffer);
    }

    assertEquals(Arrays.asList(99), readInts(file));
  }

  @Test(expected = AvroRuntimeException.class)
  public void appendEncodedBeforeCreateThrowsNotOpen() throws Exception {
    new DataFileWriter<Integer>(new GenericDatumWriter<Integer>()).appendEncoded(ByteBuffer.wrap(encodeInt(1)));
  }

  @Test
  public void appendAllFromCopiesBlocksWithCompatibleCodec() throws Exception {
    File source = writeRecordFile("source-1", "source-2");
    File target = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter();
        DataFileStream<GenericRecord> other = new DataFileStream<>(new FileInputStream(source),
            new GenericDatumReader<GenericRecord>())) {
      writer.create(RECORD_SCHEMA, target);
      writer.append(record("target"));
      writer.appendAllFrom(other, false);
    }

    assertEquals(Arrays.asList("target", "source-1", "source-2"), readNames(target));
  }

  @Test
  public void appendAllFromRecompressesBlocksWhenRequested() throws Exception {
    File source = writeRecordFile("source");
    File target = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter();
        DataFileStream<GenericRecord> other = new DataFileStream<>(new FileInputStream(source),
            new GenericDatumReader<GenericRecord>())) {
      writer.setCodec(CodecFactory.deflateCodec(1));
      writer.create(RECORD_SCHEMA, target);
      writer.appendAllFrom(other, true);
    }

    assertEquals(Arrays.asList("source"), readNames(target));
    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(target, new GenericDatumReader<GenericRecord>())) {
      assertEquals("deflate", reader.getMetaString(DataFileConstants.CODEC));
    }
  }

  @Test
  public void appendAllFromRejectsDifferentSchema() throws Exception {
    File source = writeOtherRecordFile("other");
    File target = tmp.newFile();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter();
        DataFileStream<GenericRecord> other = new DataFileStream<>(new FileInputStream(source),
            new GenericDatumReader<GenericRecord>())) {
      writer.create(RECORD_SCHEMA, target);
      try {
        writer.appendAllFrom(other, false);
        fail("schema mismatch should throw IOException");
      } catch (IOException expected) {
        assertTrue(expected.getMessage().contains("does not match"));
      }
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void appendAllFromBeforeCreateThrowsNotOpen() throws Exception {
    File source = writeRecordFile("source");
    try (DataFileStream<GenericRecord> other = new DataFileStream<>(new FileInputStream(source),
        new GenericDatumReader<GenericRecord>())) {
      newRecordWriter().appendAllFrom(other, false);
    }
  }

  @Test
  public void syncReturnsSeekablePositionForNextBlock() throws Exception {
    File file = tmp.newFile();
    long syncPoint;

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, file);
      writer.append(record("before-sync"));
      syncPoint = writer.sync();
      writer.append(record("after-sync"));
    }

    try (DataFileReader<GenericRecord> reader = new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      reader.seek(syncPoint);
      assertEquals("after-sync", reader.next().get("name").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void syncBeforeCreateThrowsNotOpen() throws Exception {
    newRecordWriter().sync();
  }

  @Test
  public void flushWritesCurrentBlockWithoutClosingWriter() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, out);
      writer.append(record("flushed"));
      writer.flush();

      try (DataFileStream<GenericRecord> reader = new DataFileStream<>(new ByteArrayInputStream(out.toByteArray()),
          new GenericDatumReader<GenericRecord>())) {
        assertEquals("flushed", reader.next().get("name").toString());
        assertFalse(reader.hasNext());
      }

      writer.append(record("after-flush"));
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void flushBeforeCreateThrowsNotOpen() throws Exception {
    newRecordWriter().flush();
  }

  @Test
  public void fSyncFlushesAndInvokesSyncOnSyncableOutputStream() throws Exception {
    SyncableByteArrayOutputStream out = new SyncableByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()) {
      writer.create(RECORD_SCHEMA, out);
      writer.append(record("fsync"));
      writer.fSync();
      assertTrue(out.synced);
    }

    try (DataFileStream<GenericRecord> reader = new DataFileStream<>(new ByteArrayInputStream(out.toByteArray()),
        new GenericDatumReader<GenericRecord>())) {
      assertEquals("fsync", reader.next().get("name").toString());
      assertFalse(reader.hasNext());
    }
  }

  @Test(expected = AvroRuntimeException.class)
  public void fSyncBeforeCreateThrowsNotOpen() throws Exception {
    newRecordWriter().fSync();
  }

  @Test
  public void closeFlushesClosesAndIsIdempotent() throws Exception {
    File file = tmp.newFile();
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, file);
    writer.append(record("closed"));
    writer.close();
    writer.close();

    assertEquals(Arrays.asList("closed"), readNames(file));
  }

  @Test(expected = AvroRuntimeException.class)
  public void appendAfterCloseThrowsNotOpen() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.create(RECORD_SCHEMA, new ByteArrayOutputStream());
    writer.close();
    writer.append(record("after-close"));
  }

  @Test
  public void appendWriteExceptionStoresOriginalCause() {
    IOException cause = new IOException("boom");
    DataFileWriter.AppendWriteException exception = new DataFileWriter.AppendWriteException(cause);
    assertSame(cause, exception.getCause());
  }

  private static final class FailingOnceIntDatumWriter implements DatumWriter<Integer> {
    private boolean failNextWrite = true;

    @Override
    public void setSchema(Schema schema) {
      assertEquals(INT_SCHEMA, schema);
    }

    @Override
    public void write(Integer datum, Encoder out) throws IOException {
      out.writeInt(datum);
      if (failNextWrite) {
        failNextWrite = false;
        throw new IOException("intentional write failure");
      }
    }
  }

  private static final class SyncableByteArrayOutputStream extends ByteArrayOutputStream implements Syncable {
    private boolean synced;

    @Override
    public void sync() throws IOException {
      synced = true;
    }
  }
}
