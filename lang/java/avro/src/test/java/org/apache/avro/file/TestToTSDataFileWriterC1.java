package org.apache.avro.file;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUnit 4 tests for the DataFileWriter implementation supplied as
 * DataFileWriter_C1.java.  Put this test in src/test/java/org/apache/avro/file/
 * so it runs in the same package as the class under test.
 */
public class TestToTSDataFileWriterC1 {

  private static final Schema RECORD_SCHEMA = new Schema.Parser().parse(
      "{"
          + "\"type\":\"record\","
          + "\"name\":\"Person\","
          + "\"fields\":["
          + "  {\"name\":\"id\",\"type\":\"int\"},"
          + "  {\"name\":\"name\",\"type\":\"string\"}"
          + "]"
          + "}");

  private static final Schema INT_SCHEMA = Schema.create(Type.INT);

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  @Test
  public void constructorCreatesClosedWriterAndCloseBeforeOpenIsNoOp() throws Exception {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    writer.close();

    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> writer.append(record(1, "closed")));
    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void setCodecBeforeCreateStoresCodecMetadataAndWritesReadableData() throws Exception {
    File file = temp.newFile("codec.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()
        .setCodec(CodecFactory.deflateCodec(1))
        .create(RECORD_SCHEMA, file)) {
      writer.append(record(1, "alpha"));
    }

    try (DataFileReader<GenericRecord> reader =
        new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("deflate", reader.getMetaString(DataFileConstants.CODEC));
      assertTrue(reader.hasNext());
      assertEquals("1:alpha", key(reader.next()));
    }
  }

  @Test
  public void setCodecAfterCreateThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, temp.newFile("codec-open.avro"));
    try {
      AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
          () -> writer.setCodec(CodecFactory.nullCodec()));
      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void setSyncIntervalAcceptsMinimumAndMaximum() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    assertSame(writer, writer.setSyncInterval(32));
    assertSame(writer, writer.setSyncInterval(1 << 30));
  }

  @Test
  public void setSyncIntervalRejectsValuesOutsideSupportedRange() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    IllegalArgumentException tooSmall =
        expectThrows(IllegalArgumentException.class, () -> writer.setSyncInterval(31));
    IllegalArgumentException tooLarge =
        expectThrows(IllegalArgumentException.class, () -> writer.setSyncInterval((1 << 30) + 1));

    assertTrue(tooSmall.getMessage().contains("Invalid syncInterval"));
    assertTrue(tooLarge.getMessage().contains("Invalid syncInterval"));
  }

  @Test
  public void setEncoderUsesSuppliedEncoderFactoryWhenWriterIsCreated() throws Exception {
    AtomicInteger calls = new AtomicInteger(0);

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()
        .setEncoder(out -> {
          calls.incrementAndGet();
          return new EncoderFactory().directBinaryEncoder(out, null);
        })
        .create(RECORD_SCHEMA, temp.newFile("custom-encoder.avro"))) {
      writer.append(record(2, "bravo"));
    }

    assertEquals(1, calls.get());
  }

  @Test
  public void setEncoderWithNullFunctionFailsWhenCreateInitializesBuffer() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    writer.setEncoder(null);

    expectThrows(NullPointerException.class,
        () -> writer.create(RECORD_SCHEMA, temp.newFile("null-encoder.avro")));
  }

  @Test
  public void createWithFileCreatesReadableAvroFile() throws Exception {
    File file = temp.newFile("create-file.avro");

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, file)) {
      writer.append(record(3, "charlie"));
    }

    assertEquals(Arrays.asList("3:charlie"), readRecordKeys(file));
  }

  @Test
  public void createWithOutputStreamCreatesReadableAvroBytes() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, out)) {
      writer.append(record(4, "delta"));
    }

    assertEquals(Arrays.asList("4:delta"), readRecordKeys(out.toByteArray()));
  }

  @Test
  public void createWithExplicitSyncWritesReadableFileContainingThatSyncMarker() throws Exception {
    byte[] sync = new byte[16];
    for (int i = 0; i < sync.length; i++) {
      sync[i] = (byte) (i + 1);
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, out, sync)) {
      writer.append(record(5, "echo"));
    }

    byte[] bytes = out.toByteArray();
    assertTrue(containsSubArray(bytes, sync));
    assertEquals(Arrays.asList("5:echo"), readRecordKeys(bytes));
  }

  @Test
  public void createWithInvalidSyncLengthThrowsIOException() {
    IOException ex = expectThrows(IOException.class,
        () -> newRecordWriter().create(RECORD_SCHEMA, new ByteArrayOutputStream(), new byte[15]));
    assertTrue(ex.getMessage().contains("sync must be exactly 16 bytes"));
  }

  @Test
  public void createWhileAlreadyOpenThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, temp.newFile("first.avro"));
    try {
      AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
          () -> writer.create(RECORD_SCHEMA, new ByteArrayOutputStream()));
      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void setAndGetFlushOnEveryBlockFlag() {
    DataFileWriter<GenericRecord> writer = newRecordWriter();

    assertTrue(writer.isFlushOnEveryBlock());
    writer.setFlushOnEveryBlock(false);
    assertEquals(false, writer.isFlushOnEveryBlock());
    writer.setFlushOnEveryBlock(true);
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void flushWritesDataEvenWhenFlushOnEveryBlockIsDisabled() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, out)) {
      writer.setFlushOnEveryBlock(false);
      writer.append(record(6, "foxtrot"));
      int lengthBeforeFlush = out.size();

      writer.flush();

      assertTrue(out.size() > lengthBeforeFlush);
    }

    assertEquals(Arrays.asList("6:foxtrot"), readRecordKeys(out.toByteArray()));
  }

  @Test
  public void appendToFileAddsRecordsToExistingFile() throws Exception {
    File file = writeRecordFile("append-to-file.avro", null, record(7, "golf"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter().appendTo(file)) {
      writer.append(record(8, "hotel"));
    }

    assertEquals(Arrays.asList("7:golf", "8:hotel"), readRecordKeys(file));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamAddsRecords() throws Exception {
    File file = writeRecordFile("append-to-streams.avro", null, record(9, "india"));

    SeekableFileInput input = new SeekableFileInput(file);
    OutputStream output = new FileOutputStream(file, true);
    try (DataFileWriter<GenericRecord> writer = newRecordWriter().appendTo(input, output)) {
      writer.append(record(10, "juliet"));
    } finally {
      input.close();
    }

    assertEquals(Arrays.asList("9:india", "10:juliet"), readRecordKeys(file));
  }

  @Test
  public void appendToWhileAlreadyOpenThrowsAlreadyOpen() throws Exception {
    File existingFile = writeRecordFile("existing.avro", null, record(11, "kilo"));
    DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, temp.newFile("target.avro"));
    try {
      AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
          () -> writer.appendTo(existingFile));
      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void setMetaStringLongAndBytesArePersistedInHeader() throws Exception {
    File file = temp.newFile("meta.avro");

    try (DataFileWriter<GenericRecord> writer = newRecordWriter()
        .setMeta("owner", "qa")
        .setMeta("rowCount", 2L)
        .setMeta("raw", "bytes".getBytes(UTF_8))
        .create(RECORD_SCHEMA, file)) {
      writer.append(record(12, "lima"));
      writer.append(record(13, "mike"));
    }

    try (DataFileReader<GenericRecord> reader =
        new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      assertEquals("qa", reader.getMetaString("owner"));
      assertEquals(2L, reader.getMetaLong("rowCount"));
      assertArrayEquals("bytes".getBytes(UTF_8), reader.getMeta("raw"));
    }
  }

  @Test
  public void setMetaRejectsReservedAvroKeys() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().setMeta("avro.custom", "forbidden"));

    assertTrue(ex.getMessage().contains("Cannot set reserved meta key"));
  }

  @Test
  public void setMetaAfterCreateThrowsAlreadyOpen() throws Exception {
    DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, temp.newFile("meta-open.avro"));
    try {
      AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
          () -> writer.setMeta("late", "value"));
      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void isReservedMetaDetectsAvroPrefix() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertEquals(false, DataFileWriter.isReservedMeta("user.schema"));
  }

  @Test
  public void isReservedMetaWithNullKeyThrowsNullPointerException() {
    expectThrows(NullPointerException.class, () -> DataFileWriter.isReservedMeta(null));
  }

  @Test
  public void appendWritesRecordsAndAllowsReadingThemBack() throws Exception {
    File file = temp.newFile("append.avro");

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, file)) {
      writer.append(record(14, "november"));
      writer.append(record(15, "oscar"));
    }

    assertEquals(Arrays.asList("14:november", "15:oscar"), readRecordKeys(file));
  }

  @Test
  public void appendBeforeCreateThrowsNotOpen() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().append(record(16, "papa")));

    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void appendWrapsWriterFailureAndDiscardsPartialRecordBytes() throws Exception {
    File file = temp.newFile("append-failure.avro");

    try (DataFileWriter<Integer> writer =
        new DataFileWriter<Integer>(new PartiallyFailingIntWriter()).create(INT_SCHEMA, file)) {
      writer.append(17);

      DataFileWriter.AppendWriteException ex = expectThrows(
          DataFileWriter.AppendWriteException.class, () -> writer.append(-1));
      assertTrue(ex.getCause() instanceof IOException);

      writer.append(18);
    }

    assertEquals(Arrays.asList(17, 18), readInts(file));
  }

  @Test
  public void appendWriteExceptionConstructorPreservesCause() {
    Exception cause = new Exception("boom");

    DataFileWriter.AppendWriteException ex = new DataFileWriter.AppendWriteException(cause);

    assertSame(cause, ex.getCause());
  }

  @Test
  public void appendEncodedWritesPreEncodedDatum() throws Exception {
    File file = temp.newFile("append-encoded.avro");
    ByteBuffer encodedRecord = encodeRecord(record(19, "quebec"));

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, file)) {
      writer.appendEncoded(encodedRecord);
    }

    assertEquals(Arrays.asList("19:quebec"), readRecordKeys(file));
  }

  @Test
  public void appendEncodedBeforeCreateThrowsNotOpen() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().appendEncoded(ByteBuffer.allocate(0)));

    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void appendAllFromCopiesBlocksWithSameSchemaAndCodec() throws Exception {
    File source = writeRecordFile("source.avro", null,
        record(20, "romeo"),
        record(21, "sierra"));
    File target = writeRecordFile("target.avro", null, record(22, "tango"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter().appendTo(target);
         DataFileStream<GenericRecord> stream =
             new DataFileStream<>(new FileInputStream(source), new GenericDatumReader<GenericRecord>())) {
      writer.appendAllFrom(stream, false);
    }

    assertEquals(Arrays.asList("22:tango", "20:romeo", "21:sierra"), readRecordKeys(target));
  }

  @Test
  public void appendAllFromRecompressesBlocksWhenRequested() throws Exception {
    File source = writeRecordFile("source-deflate.avro", CodecFactory.deflateCodec(1),
        record(23, "uniform"));
    File target = writeRecordFile("target-null.avro", CodecFactory.nullCodec(),
        record(24, "victor"));

    try (DataFileWriter<GenericRecord> writer = newRecordWriter().appendTo(target);
         DataFileStream<GenericRecord> stream =
             new DataFileStream<>(new FileInputStream(source), new GenericDatumReader<GenericRecord>())) {
      writer.appendAllFrom(stream, true);
    }

    assertEquals(Arrays.asList("24:victor", "23:uniform"), readRecordKeys(target));
  }

  @Test
  public void appendAllFromRejectsDifferentSchema() throws Exception {
    Schema stringSchema = Schema.create(Type.STRING);
    File source = temp.newFile("string-source.avro");
    try (DataFileWriter<CharSequence> writer =
        new DataFileWriter<CharSequence>(new GenericDatumWriter<CharSequence>())
            .create(stringSchema, source)) {
      writer.append("not-a-person");
    }

    File target = writeRecordFile("schema-target.avro", null, record(25, "whiskey"));
    try (DataFileWriter<GenericRecord> writer = newRecordWriter().appendTo(target);
         DataFileStream<GenericRecord> stream =
             new DataFileStream<>(new FileInputStream(source), new GenericDatumReader<GenericRecord>())) {
      IOException ex = expectThrows(IOException.class, () -> writer.appendAllFrom(stream, false));
      assertTrue(ex.getMessage().contains("does not match"));
    }
  }

  @Test
  public void appendAllFromBeforeCreateThrowsNotOpen() throws Exception {
    File source = writeRecordFile("closed-source.avro", null, record(26, "xray"));
    try (DataFileStream<GenericRecord> stream =
        new DataFileStream<>(new FileInputStream(source), new GenericDatumReader<GenericRecord>())) {
      AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
          () -> newRecordWriter().appendAllFrom(stream, false));
      assertTrue(ex.getMessage().contains("not open"));
    }
  }

  @Test
  public void syncReturnsPositionThatCanBeUsedForSeek() throws Exception {
    File file = temp.newFile("sync.avro");
    long position;

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, file)) {
      writer.append(record(27, "yankee"));
      position = writer.sync();
      writer.append(record(28, "zulu"));
    }

    try (DataFileReader<GenericRecord> reader =
        new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      reader.seek(position);
      assertTrue(reader.hasNext());
      assertEquals("28:zulu", key(reader.next()));
    }
  }

  @Test
  public void syncBeforeCreateThrowsNotOpen() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().sync());

    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void flushBeforeCreateThrowsNotOpen() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().flush());

    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void fSyncDelegatesToSyncableStream() throws Exception {
    SyncCountingOutputStream out = new SyncCountingOutputStream();

    try (DataFileWriter<GenericRecord> writer =
        newRecordWriter().create(RECORD_SCHEMA, out)) {
      writer.append(record(29, "syncable"));
      writer.fSync();
      assertEquals(1, out.syncCalls);
    }
  }

  @Test
  public void fSyncBeforeCreateThrowsNotOpen() {
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> newRecordWriter().fSync());

    assertTrue(ex.getMessage().contains("not open"));
  }

  @Test
  public void closeFlushesDataAndAppendAfterCloseThrowsNotOpen() throws Exception {
    File file = temp.newFile("close.avro");
    DataFileWriter<GenericRecord> writer = newRecordWriter().create(RECORD_SCHEMA, file);

    writer.append(record(30, "closed"));
    writer.close();
    writer.close();

    assertEquals(Arrays.asList("30:closed"), readRecordKeys(file));
    AvroRuntimeException ex = expectThrows(AvroRuntimeException.class,
        () -> writer.append(record(31, "after-close")));
    assertTrue(ex.getMessage().contains("not open"));
  }

  private DataFileWriter<GenericRecord> newRecordWriter() {
    return new DataFileWriter<>(new GenericDatumWriter<GenericRecord>());
  }

  private File writeRecordFile(String name, CodecFactory codec, GenericRecord... records)
      throws IOException {
    File file = temp.newFile(name);
    DataFileWriter<GenericRecord> writer = newRecordWriter();
    if (codec != null) {
      writer.setCodec(codec);
    }
    try {
      writer.create(RECORD_SCHEMA, file);
      for (GenericRecord record : records) {
        writer.append(record);
      }
    } finally {
      writer.close();
    }
    return file;
  }

  private static GenericRecord record(int id, String name) {
    GenericRecord record = new GenericData.Record(RECORD_SCHEMA);
    record.put("id", id);
    record.put("name", name);
    return record;
  }

  private static String key(GenericRecord record) {
    return record.get("id") + ":" + record.get("name");
  }

  private static List<String> readRecordKeys(File file) throws IOException {
    try (DataFileReader<GenericRecord> reader =
        new DataFileReader<>(file, new GenericDatumReader<GenericRecord>())) {
      List<String> keys = new ArrayList<>();
      while (reader.hasNext()) {
        keys.add(key(reader.next()));
      }
      return keys;
    }
  }

  private static List<String> readRecordKeys(byte[] bytes) throws IOException {
    try (DataFileStream<GenericRecord> reader =
        new DataFileStream<>(new ByteArrayInputStream(bytes), new GenericDatumReader<GenericRecord>())) {
      List<String> keys = new ArrayList<>();
      while (reader.hasNext()) {
        keys.add(key(reader.next()));
      }
      return keys;
    }
  }

  private static List<Integer> readInts(File file) throws IOException {
    try (DataFileReader<Integer> reader =
        new DataFileReader<>(file, new GenericDatumReader<Integer>())) {
      List<Integer> values = new ArrayList<>();
      while (reader.hasNext()) {
        values.add(reader.next());
      }
      return values;
    }
  }

  private static ByteBuffer encodeRecord(GenericRecord record) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
    new GenericDatumWriter<GenericRecord>(RECORD_SCHEMA).write(record, encoder);
    encoder.flush();
    return ByteBuffer.wrap(out.toByteArray());
  }

  private static boolean containsSubArray(byte[] haystack, byte[] needle) {
    outer:
    for (int i = 0; i <= haystack.length - needle.length; i++) {
      for (int j = 0; j < needle.length; j++) {
        if (haystack[i + j] != needle[j]) {
          continue outer;
        }
      }
      return true;
    }
    return false;
  }

  private static <T extends Throwable> T expectThrows(
      Class<T> expectedType, ThrowingRunnable runnable) {
    try {
      runnable.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      throw new AssertionError(
          "Expected " + expectedType.getName() + " but caught " + actual.getClass().getName(),
          actual);
    }
    fail("Expected " + expectedType.getName());
    return null;
  }

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static final class PartiallyFailingIntWriter implements DatumWriter<Integer> {
    @Override
    public void setSchema(Schema schema) {
      assertNotNull(schema);
    }

    @Override
    public void write(Integer datum, Encoder encoder) throws IOException {
      if (datum < 0) {
        encoder.writeInt(999);
        throw new IOException("intentional write failure");
      }
      encoder.writeInt(datum);
    }
  }

  private static final class SyncCountingOutputStream extends ByteArrayOutputStream
      implements Syncable {
    private int syncCalls;

    @Override
    public void sync() {
      syncCalls++;
    }
  }
}
