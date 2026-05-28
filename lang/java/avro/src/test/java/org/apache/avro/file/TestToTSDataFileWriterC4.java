package org.apache.avro.file;

import static org.junit.Assert.*;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestToTSDataFileWriterC4 {

  private static final Schema STRING_SCHEMA = Schema.create(Schema.Type.STRING);
  private static final Schema INT_SCHEMA = Schema.create(Schema.Type.INT);

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static <T extends Throwable> T expectThrows(Class<T> expectedType, ThrowingRunnable action) {
    try {
      action.run();
    } catch (Throwable actual) {
      if (expectedType.isInstance(actual)) {
        return expectedType.cast(actual);
      }
      throw new AssertionError(
          "Expected " + expectedType.getName() + " but got " + actual.getClass().getName(),
          actual);
    }
    throw new AssertionError("Expected exception of type " + expectedType.getName());
  }

  private static DataFileWriter<String> newStringWriter() {
    return new DataFileWriter<String>(new GenericDatumWriter<String>(STRING_SCHEMA));
  }

  private static List<String> readStrings(File file) throws IOException {
    List<String> values = new ArrayList<String>();
    try (DataFileReader<String> reader =
             new DataFileReader<String>(file, new GenericDatumReader<String>())) {
      while (reader.hasNext()) {
        values.add(String.valueOf(reader.next()));
      }
    }
    return values;
  }

  private static List<String> readStrings(byte[] bytes) throws IOException {
    List<String> values = new ArrayList<String>();
    try (DataFileStream<String> reader =
             new DataFileStream<String>(
                 new ByteArrayInputStream(bytes),
                 new GenericDatumReader<String>())) {
      while (reader.hasNext()) {
        values.add(String.valueOf(reader.next()));
      }
    }
    return values;
  }

  private static void writeStringFile(File file, String... values) throws IOException {
    try (DataFileWriter<String> writer = newStringWriter()) {
      writer.create(STRING_SCHEMA, file);
      for (String value : values) {
        writer.append(value);
      }
    }
  }

  private static void writeIntFile(File file, int... values) throws IOException {
    try (DataFileWriter<Integer> writer =
             new DataFileWriter<Integer>(new GenericDatumWriter<Integer>(INT_SCHEMA))) {
      writer.create(INT_SCHEMA, file);
      for (int value : values) {
        writer.append(value);
      }
    }
  }

  private static ByteBuffer encodeString(String value) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    BinaryEncoder encoder = new EncoderFactory().directBinaryEncoder(out, null);
    encoder.writeString(value);
    encoder.flush();
    return ByteBuffer.wrap(out.toByteArray());
  }

  @Test
  public void constructorCreatesClosedWriterRequiringCreateBeforeAppend() throws Exception {
    DataFileWriter<String> writer = newStringWriter();

    AvroRuntimeException ex =
        expectThrows(AvroRuntimeException.class, () -> writer.append("not-open"));

    assertTrue(ex.getMessage().contains("not open"));
    writer.close();
  }

  @Test
  public void setCodecWritesCompressedReadableFile() throws Exception {
    File file = temp.newFile("compressed.avro");

    DataFileWriter<String> writer = newStringWriter();
    assertSame(writer, writer.setCodec(CodecFactory.deflateCodec(1)));
    writer.create(STRING_SCHEMA, file);
    writer.append("compressed-value");
    writer.close();

    try (DataFileReader<String> reader =
             new DataFileReader<String>(file, new GenericDatumReader<String>())) {
      assertEquals("deflate", reader.getMetaString(DataFileConstants.CODEC));
      assertTrue(reader.hasNext());
      assertEquals("compressed-value", String.valueOf(reader.next()));
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void setCodecCannotBeChangedAfterCreate() throws Exception {
    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, new ByteArrayOutputStream());

    try {
      AvroRuntimeException ex =
          expectThrows(
              AvroRuntimeException.class,
              () -> writer.setCodec(CodecFactory.nullCodec()));

      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void setSyncIntervalAcceptsValidBoundaryValues() {
    DataFileWriter<String> writer = newStringWriter();

    assertSame(writer, writer.setSyncInterval(32));
    assertSame(writer, writer.setSyncInterval(1 << 30));
  }

  @Test
  public void setSyncIntervalRejectsTooSmallAndTooLargeValues() {
    DataFileWriter<String> writer = newStringWriter();

    assertTrue(
        expectThrows(IllegalArgumentException.class, () -> writer.setSyncInterval(31))
            .getMessage()
            .contains("Invalid syncInterval value"));

    assertTrue(
        expectThrows(IllegalArgumentException.class, () -> writer.setSyncInterval((1 << 30) + 1))
            .getMessage()
            .contains("Invalid syncInterval value"));
  }

  @Test
  public void setEncoderUsesCustomEncoderFactory() throws Exception {
    AtomicInteger calls = new AtomicInteger(0);
    File file = temp.newFile("custom-encoder.avro");

    DataFileWriter<String> writer = newStringWriter();
    assertSame(
        writer,
        writer.setEncoder(
            out -> {
              calls.incrementAndGet();
              return new EncoderFactory().directBinaryEncoder(out, null);
            }));

    writer.create(STRING_SCHEMA, file);
    writer.append("custom");
    writer.close();

    assertEquals(1, calls.get());
    assertEquals(Arrays.asList("custom"), readStrings(file));
  }

  @Test
  public void createWithFileWritesReadableFile() throws Exception {
    File file = temp.newFile("created-with-file.avro");

    try (DataFileWriter<String> writer = newStringWriter()) {
      assertSame(writer, writer.create(STRING_SCHEMA, file));
      writer.append("a");
      writer.append("b");
    }

    assertEquals(Arrays.asList("a", "b"), readStrings(file));
  }

  @Test
  public void createWithOutputStreamWritesReadableContainer() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    DataFileWriter<String> writer = newStringWriter();
    assertSame(writer, writer.create(STRING_SCHEMA, out));
    writer.append("stream-value");
    writer.close();

    assertEquals(Arrays.asList("stream-value"), readStrings(out.toByteArray()));
  }

  @Test
  public void createWithExplicitSyncAcceptsSixteenByteSync() throws Exception {
    File file = temp.newFile("explicit-sync.avro");
    byte[] sync = new byte[16];
    Arrays.fill(sync, (byte) 7);

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, new FileOutputStream(file), sync);
    writer.append("explicit-sync-value");
    writer.close();

    assertEquals(Arrays.asList("explicit-sync-value"), readStrings(file));
  }

  @Test
  public void createWithExplicitSyncRejectsWrongLengthSync() {
    DataFileWriter<String> writer = newStringWriter();

    IOException ex =
        expectThrows(
            IOException.class,
            () -> writer.create(STRING_SCHEMA, new ByteArrayOutputStream(), new byte[15]));

    assertTrue(ex.getMessage().contains("sync must be exactly 16 bytes"));
  }

  @Test
  public void createCannotBeCalledWhenAlreadyOpen() throws Exception {
    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, new ByteArrayOutputStream());

    try {
      AvroRuntimeException ex =
          expectThrows(
              AvroRuntimeException.class,
              () -> writer.create(STRING_SCHEMA, new ByteArrayOutputStream()));

      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void flushOnEveryBlockCanBeToggled() {
    DataFileWriter<String> writer = newStringWriter();

    assertTrue(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(false);
    assertFalse(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(true);
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void appendToFileAddsRecordsToExistingContainer() throws Exception {
    File file = temp.newFile("append-to-file.avro");
    writeStringFile(file, "first");

    try (DataFileWriter<String> appender = newStringWriter()) {
      appender.appendTo(file);
      appender.append("second");
    }

    assertEquals(Arrays.asList("first", "second"), readStrings(file));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamAddsRecords() throws Exception {
    File file = temp.newFile("append-to-streams.avro");
    writeStringFile(file, "one");

    try (SeekableInput input = new SeekableFileInput(file);
         OutputStream output = new FileOutputStream(file, true);
         DataFileWriter<String> appender = newStringWriter()) {
      assertSame(appender, appender.appendTo(input, output));
      appender.append("two");
    }

    assertEquals(Arrays.asList("one", "two"), readStrings(file));
  }

  @Test
  public void setMetaOverloadsWriteReadableMetadata() throws Exception {
    File file = temp.newFile("metadata.avro");
    byte[] binaryValue = "binary-value".getBytes(StandardCharsets.UTF_8);

    DataFileWriter<String> writer = newStringWriter();
    assertSame(writer, writer.setMeta("bytesKey", binaryValue));
    assertSame(writer, writer.setMeta("stringKey", "string-value"));
    assertSame(writer, writer.setMeta("longKey", 42L));

    writer.create(STRING_SCHEMA, file);
    writer.close();

    try (DataFileReader<String> reader =
             new DataFileReader<String>(file, new GenericDatumReader<String>())) {
      assertArrayEquals(binaryValue, reader.getMeta("bytesKey"));
      assertEquals("string-value", reader.getMetaString("stringKey"));
      assertEquals("42", reader.getMetaString("longKey"));
    }
  }

  @Test
  public void setMetaRejectsReservedKeysAndDetectsReservedNames() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertFalse(DataFileWriter.isReservedMeta("custom.key"));

    AvroRuntimeException ex =
        expectThrows(
            AvroRuntimeException.class,
            () -> newStringWriter().setMeta("avro.custom", "bad"));

    assertTrue(ex.getMessage().contains("Cannot set reserved meta key"));
  }

  @Test
  public void setMetaAfterCreateThrowsAlreadyOpen() throws Exception {
    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, new ByteArrayOutputStream());

    try {
      AvroRuntimeException ex =
          expectThrows(
              AvroRuntimeException.class,
              () -> writer.setMeta("custom.after.open", "not allowed"));

      assertTrue(ex.getMessage().contains("already open"));
    } finally {
      writer.close();
    }
  }

  @Test
  public void appendWritesDatumAndCloseFlushesFile() throws Exception {
    File file = temp.newFile("append.avro");

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, file);
    writer.append("alpha");
    writer.append("beta");
    writer.close();

    assertEquals(Arrays.asList("alpha", "beta"), readStrings(file));
  }

  @Test
  public void appendWrapsDatumWriterFailuresAndPreservesExistingBufferedData() throws Exception {
    File file = temp.newFile("append-failure.avro");
    FailingDatumWriter datumWriter = new FailingDatumWriter();

    DataFileWriter<String> writer = new DataFileWriter<String>(datumWriter);
    writer.create(STRING_SCHEMA, file);
    writer.append("before");

    DataFileWriter.AppendWriteException ex =
        expectThrows(
            DataFileWriter.AppendWriteException.class,
            () -> writer.append("FAIL"));

    assertTrue(ex.getCause() instanceof RuntimeException);
    assertEquals("boom", ex.getCause().getMessage());

    writer.append("after");
    writer.close();

    assertEquals(Arrays.asList("before", "after"), readStrings(file));
  }

  @Test
  public void appendEncodedWritesPreEncodedDatum() throws Exception {
    File file = temp.newFile("append-encoded.avro");

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, file);
    writer.appendEncoded(encodeString("encoded"));
    writer.close();

    assertEquals(Arrays.asList("encoded"), readStrings(file));
  }

  @Test
  public void appendAllFromCopiesBlocksWithCompatibleCodec() throws Exception {
    File source = temp.newFile("source-compatible.avro");
    File target = temp.newFile("target-compatible.avro");

    writeStringFile(source, "source-a", "source-b");

    try (DataFileWriter<String> targetWriter = newStringWriter();
         DataFileStream<String> other =
             new DataFileStream<String>(
                 new FileInputStream(source),
                 new GenericDatumReader<String>())) {
      targetWriter.create(STRING_SCHEMA, target);
      targetWriter.append("target");
      targetWriter.appendAllFrom(other, false);
    }

    assertEquals(Arrays.asList("target", "source-a", "source-b"), readStrings(target));
  }

  @Test
  public void appendAllFromRecompressesWhenRequested() throws Exception {
    File source = temp.newFile("source-recompress.avro");
    File target = temp.newFile("target-recompress.avro");

    writeStringFile(source, "a", "b");

    try (DataFileWriter<String> targetWriter = newStringWriter();
         DataFileStream<String> other =
             new DataFileStream<String>(
                 new FileInputStream(source),
                 new GenericDatumReader<String>())) {
      targetWriter.setCodec(CodecFactory.deflateCodec(1));
      targetWriter.create(STRING_SCHEMA, target);
      targetWriter.appendAllFrom(other, true);
    }

    assertEquals(Arrays.asList("a", "b"), readStrings(target));
  }

  @Test
  public void appendAllFromRejectsSchemaMismatch() throws Exception {
    File source = temp.newFile("source-int.avro");
    File target = temp.newFile("target-string.avro");

    writeIntFile(source, 1, 2);

    try (DataFileWriter<String> targetWriter = newStringWriter();
         DataFileStream<String> other =
             new DataFileStream<String>(
                 new FileInputStream(source),
                 new GenericDatumReader<String>())) {
      targetWriter.create(STRING_SCHEMA, target);

      IOException ex =
          expectThrows(IOException.class, () -> targetWriter.appendAllFrom(other, false));

      assertTrue(ex.getMessage().contains("does not match"));
    }
  }

  @Test
  public void syncReturnsPositivePositionAndFlushesCurrentBlockOnClose() throws Exception {
    File file = temp.newFile("sync.avro");

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, file);
    writer.append("synced");

    long position = writer.sync();

    assertTrue(position > 0L);

    writer.close();

    assertEquals(Arrays.asList("synced"), readStrings(file));
  }

  @Test
  public void flushWritesCurrentBlockToOutputStream() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    DataFileWriter<String> writer = newStringWriter();
    try {
      writer.create(STRING_SCHEMA, out);
      writer.append("flushed");
      writer.flush();

      assertTrue(out.size() > 0);
      assertEquals(Arrays.asList("flushed"), readStrings(out.toByteArray()));
    } finally {
      writer.close();
    }
  }

  @Test
  public void fSyncDelegatesToSyncableStream() throws Exception {
    SyncRecordingOutputStream out = new SyncRecordingOutputStream();

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, out);
    writer.append("durable");
    writer.fSync();
    writer.close();

    assertTrue(out.synced);
    assertEquals(Arrays.asList("durable"), readStrings(out.toByteArray()));
  }

  @Test
  public void closeIsIdempotentAndPreventsFurtherAppends() throws Exception {
    File file = temp.newFile("close.avro");

    DataFileWriter<String> writer = newStringWriter();
    writer.create(STRING_SCHEMA, file);
    writer.append("closed");
    writer.close();
    writer.close();

    AvroRuntimeException ex =
        expectThrows(AvroRuntimeException.class, () -> writer.append("after-close"));

    assertTrue(ex.getMessage().contains("not open"));
    assertEquals(Arrays.asList("closed"), readStrings(file));
  }

  @Test
  public void appendWriteExceptionRetainsCause() {
    IOException cause = new IOException("root-cause");

    DataFileWriter.AppendWriteException ex =
        new DataFileWriter.AppendWriteException(cause);

    assertSame(cause, ex.getCause());
  }

  @Test
  public void writingAndSyncMethodsRequireOpenWriter() {
    DataFileWriter<String> writer = newStringWriter();

    assertTrue(
        expectThrows(AvroRuntimeException.class, () -> writer.append("x"))
            .getMessage()
            .contains("not open"));

    assertTrue(
        expectThrows(
            AvroRuntimeException.class,
            () -> writer.appendEncoded(ByteBuffer.wrap(new byte[] {1, 2, 3})))
            .getMessage()
            .contains("not open"));

    assertTrue(
        expectThrows(AvroRuntimeException.class, () -> writer.sync())
            .getMessage()
            .contains("not open"));

    assertTrue(
        expectThrows(AvroRuntimeException.class, () -> writer.flush())
            .getMessage()
            .contains("not open"));

    assertTrue(
        expectThrows(AvroRuntimeException.class, () -> writer.fSync())
            .getMessage()
            .contains("not open"));
  }

  private static class SyncRecordingOutputStream extends ByteArrayOutputStream implements Syncable {
    private boolean synced = false;

    @Override
    public void sync() throws IOException {
      synced = true;
    }
  }

  private static class FailingDatumWriter implements DatumWriter<String> {
    @Override
    public void setSchema(Schema schema) {
      // No-op for this controlled test writer.
    }

    @Override
    public void write(String datum, Encoder out) throws IOException {
      if ("FAIL".equals(datum)) {
        out.writeString("partial-value-that-must-not-remain-in-buffer");
        throw new RuntimeException("boom");
      }
      out.writeString(datum);
    }
  }
}
