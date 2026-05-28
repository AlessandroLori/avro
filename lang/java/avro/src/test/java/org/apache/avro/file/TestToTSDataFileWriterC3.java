package org.apache.avro.file;

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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

/**
 * Comprehensive JUnit 4 tests for DataFileWriter_C3.java's public DataFileWriter API.
 */
public class TestToTSDataFileWriterC3 {
  private static final Schema INT_SCHEMA = Schema.create(Schema.Type.INT);
  private static final Schema STRING_SCHEMA = Schema.create(Schema.Type.STRING);

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  private interface ThrowingRunnable {
    void run() throws Exception;
  }

  private static <T extends Throwable> T expectThrows(Class<T> expected, ThrowingRunnable action) {
    try {
      action.run();
    } catch (Throwable actual) {
      if (expected.isInstance(actual)) {
        return expected.cast(actual);
      }
      AssertionError error = new AssertionError(
          "Expected " + expected.getName() + " but caught " + actual.getClass().getName());
      error.initCause(actual);
      throw error;
    }
    fail("Expected " + expected.getName());
    return null;
  }

  private DataFileWriter<Integer> newIntWriter() {
    return new DataFileWriter<Integer>(new GenericDatumWriter<Integer>(INT_SCHEMA));
  }

  private File writeIntFile(Integer... values) throws IOException {
    File file = folder.newFile();
    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, file);
    for (Integer value : values) {
      writer.append(value);
    }
    writer.close();
    return file;
  }

  private File writeStringFile(String... values) throws IOException {
    File file = folder.newFile();
    DataFileWriter<CharSequence> writer =
        new DataFileWriter<CharSequence>(new GenericDatumWriter<CharSequence>(STRING_SCHEMA));
    writer.create(STRING_SCHEMA, file);
    for (String value : values) {
      writer.append(value);
    }
    writer.close();
    return file;
  }

  private static List<Integer> readInts(File file) throws IOException {
    List<Integer> actual = new ArrayList<Integer>();
    try (DataFileReader<Integer> reader =
        new DataFileReader<Integer>(file, new GenericDatumReader<Integer>())) {
      while (reader.hasNext()) {
        actual.add(reader.next());
      }
    }
    return actual;
  }

  private static List<Integer> readInts(byte[] bytes) throws IOException {
    List<Integer> actual = new ArrayList<Integer>();
    try (DataFileStream<Integer> reader =
        new DataFileStream<Integer>(new ByteArrayInputStream(bytes), new GenericDatumReader<Integer>())) {
      while (reader.hasNext()) {
        actual.add(reader.next());
      }
    }
    return actual;
  }

  private static ByteBuffer encodedInt(int value) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
    encoder.writeInt(value);
    encoder.flush();
    return ByteBuffer.wrap(out.toByteArray());
  }

  @Test
  public void constructorCreatesClosedWriterAndOpenOnlyOperationsFailBeforeCreate() throws Exception {
    DataFileWriter<Integer> writer = newIntWriter();

    AvroRuntimeException appendFailure = expectThrows(AvroRuntimeException.class, () -> writer.append(1));
    assertTrue(appendFailure.getMessage().contains("not open"));

    AvroRuntimeException syncFailure = expectThrows(AvroRuntimeException.class, () -> writer.sync());
    assertTrue(syncFailure.getMessage().contains("not open"));

    AvroRuntimeException flushFailure = expectThrows(AvroRuntimeException.class, () -> writer.flush());
    assertTrue(flushFailure.getMessage().contains("not open"));

    AvroRuntimeException fsyncFailure = expectThrows(AvroRuntimeException.class, () -> writer.fSync());
    assertTrue(fsyncFailure.getMessage().contains("not open"));

    writer.close(); // close before create is a no-op.
  }

  @Test
  public void setCodecBeforeCreateWritesCodecMetadataAndCannotBeCalledAfterOpen() throws Exception {
    File file = folder.newFile();
    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.setCodec(CodecFactory.deflateCodec(1)));
    writer.create(INT_SCHEMA, file);
    writer.append(7);

    AvroRuntimeException failure =
        expectThrows(AvroRuntimeException.class, () -> writer.setCodec(CodecFactory.nullCodec()));
    assertTrue(failure.getMessage().contains("already open"));

    writer.close();

    try (DataFileReader<Integer> reader =
        new DataFileReader<Integer>(file, new GenericDatumReader<Integer>())) {
      assertEquals("deflate", reader.getMetaString(DataFileConstants.CODEC));
      assertEquals(Integer.valueOf(7), reader.next());
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void setSyncIntervalAcceptsInclusiveBoundsAndRejectsOutOfRangeValues() {
    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.setSyncInterval(32));
    assertSame(writer, writer.setSyncInterval(1 << 30));

    IllegalArgumentException tooSmall =
        expectThrows(IllegalArgumentException.class, () -> newIntWriter().setSyncInterval(31));
    assertTrue(tooSmall.getMessage().contains("Invalid syncInterval"));

    IllegalArgumentException tooLarge =
        expectThrows(IllegalArgumentException.class, () -> newIntWriter().setSyncInterval((1 << 30) + 1));
    assertTrue(tooLarge.getMessage().contains("Invalid syncInterval"));
  }

  @Test
  public void setEncoderIsUsedWhenWriterIsCreated() throws Exception {
    AtomicBoolean encoderFactoryCalled = new AtomicBoolean(false);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.setEncoder(output -> {
      encoderFactoryCalled.set(true);
      return EncoderFactory.get().directBinaryEncoder(output, null);
    }));

    writer.create(INT_SCHEMA, out);
    writer.append(3);
    writer.close();

    assertTrue(encoderFactoryCalled.get());
    assertEquals(Arrays.asList(3), readInts(out.toByteArray()));
  }

  @Test
  public void createWithFileWritesReadableFile() throws Exception {
    File file = folder.newFile();

    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.create(INT_SCHEMA, file));
    writer.append(1);
    writer.append(2);
    writer.close();

    assertEquals(Arrays.asList(1, 2), readInts(file));
  }

  @Test
  public void createWithOutputStreamAndExplicitSyncWritesReadableStream() throws Exception {
    byte[] sync = new byte[16];
    Arrays.fill(sync, (byte) 0x5A);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.create(INT_SCHEMA, out, sync));
    writer.append(11);
    writer.close();

    try (DataFileStream<Integer> reader =
        new DataFileStream<Integer>(new ByteArrayInputStream(out.toByteArray()),
            new GenericDatumReader<Integer>())) {
      assertEquals(INT_SCHEMA, reader.getSchema());
      assertArrayEquals(sync, reader.getHeader().sync);
      assertEquals(Integer.valueOf(11), reader.next());
      assertFalse(reader.hasNext());
    }
  }

  @Test
  public void createRejectsInvalidExplicitSyncLengthAndRejectsCreateWhileAlreadyOpen() throws Exception {
    IOException invalidSync = expectThrows(IOException.class,
        () -> newIntWriter().create(INT_SCHEMA, new ByteArrayOutputStream(), new byte[15]));
    assertTrue(invalidSync.getMessage().contains("sync must be exactly 16 bytes"));

    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, new ByteArrayOutputStream());
    AvroRuntimeException alreadyOpen = expectThrows(AvroRuntimeException.class,
        () -> writer.create(INT_SCHEMA, new ByteArrayOutputStream()));
    assertTrue(alreadyOpen.getMessage().contains("already open"));
    writer.close();
  }

  @Test
  public void flushOnEveryBlockFlagCanBeToggledAndReadBack() {
    DataFileWriter<Integer> writer = newIntWriter();
    assertTrue(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(false);
    assertFalse(writer.isFlushOnEveryBlock());

    writer.setFlushOnEveryBlock(true);
    assertTrue(writer.isFlushOnEveryBlock());
  }

  @Test
  public void appendToFileAddsRecordsToExistingDataFile() throws Exception {
    File file = writeIntFile(1);

    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.appendTo(file));
    writer.append(2);
    writer.append(3);
    writer.close();

    assertEquals(Arrays.asList(1, 2, 3), readInts(file));
  }

  @Test
  public void appendToSeekableInputAndOutputStreamAddsRecords() throws Exception {
    File file = writeIntFile(4);

    try (SeekableFileInput input = new SeekableFileInput(file)) {
      FileOutputStream output = new FileOutputStream(file, true);
      DataFileWriter<Integer> writer = newIntWriter();
      assertSame(writer, writer.appendTo(input, output));
      writer.append(5);
      writer.close();
    }

    assertEquals(Arrays.asList(4, 5), readInts(file));
  }

  @Test
  public void setMetaOverloadsPersistUserMetadataAndReservedOrLateMetadataFails() throws Exception {
    File file = folder.newFile();
    byte[] rawMetadata = new byte[] {1, 2, 3};

    DataFileWriter<Integer> writer = newIntWriter();
    assertSame(writer, writer.setMeta("raw", rawMetadata));
    assertSame(writer, writer.setMeta("text", "hello"));
    assertSame(writer, writer.setMeta("count", 42L));
    writer.create(INT_SCHEMA, file);

    AvroRuntimeException lateMetadata =
        expectThrows(AvroRuntimeException.class, () -> writer.setMeta("late", "value"));
    assertTrue(lateMetadata.getMessage().contains("already open"));
    writer.close();

    try (DataFileReader<Integer> reader =
        new DataFileReader<Integer>(file, new GenericDatumReader<Integer>())) {
      assertArrayEquals(rawMetadata, reader.getMeta("raw"));
      assertEquals("hello", reader.getMetaString("text"));
      assertEquals(42L, reader.getMetaLong("count"));
    }

    AvroRuntimeException reserved =
        expectThrows(AvroRuntimeException.class, () -> newIntWriter().setMeta("avro.test", "bad"));
    assertTrue(reserved.getMessage().contains("Cannot set reserved meta key"));
  }

  @Test
  public void isReservedMetaOnlyAcceptsAvroDotPrefix() {
    assertTrue(DataFileWriter.isReservedMeta("avro.schema"));
    assertTrue(DataFileWriter.isReservedMeta("avro.codec"));
    assertFalse(DataFileWriter.isReservedMeta("avro"));
    assertFalse(DataFileWriter.isReservedMeta("user.avro.key"));
    expectThrows(NullPointerException.class, () -> DataFileWriter.isReservedMeta(null));
  }

  @Test
  public void appendWritesManyRecordsIncludingBlockFullPath() throws Exception {
    File file = folder.newFile();
    DataFileWriter<Integer> writer = newIntWriter();
    writer.setSyncInterval(32);
    writer.create(INT_SCHEMA, file);

    List<Integer> expected = new ArrayList<Integer>();
    for (int i = 0; i < 100; i++) {
      expected.add(i);
      writer.append(i);
    }
    writer.close();

    assertEquals(expected, readInts(file));
  }

  @Test
  public void appendWrapsDatumWriterFailuresAndDiscardsPartialBytes() throws Exception {
    File file = folder.newFile();
    DataFileWriter<Integer> writer = new DataFileWriter<Integer>(new PartiallyFailingIntDatumWriter());
    writer.create(INT_SCHEMA, file);
    writer.append(1);

    DataFileWriter.AppendWriteException failure =
        expectThrows(DataFileWriter.AppendWriteException.class, () -> writer.append(-1));
    assertNotNull(failure.getCause());
    assertTrue(failure.getCause() instanceof IOException);

    writer.append(2);
    writer.close();

    assertEquals(Arrays.asList(1, 2), readInts(file));
  }

  @Test
  public void appendEncodedWritesPreEncodedDatumAndRejectsClosedWriter() throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, out);
    writer.appendEncoded(encodedInt(99));
    writer.close();

    assertEquals(Arrays.asList(99), readInts(out.toByteArray()));

    AvroRuntimeException closedFailure =
        expectThrows(AvroRuntimeException.class, () -> writer.appendEncoded(encodedInt(100)));
    assertTrue(closedFailure.getMessage().contains("not open"));
  }

  @Test
  public void appendAllFromCopiesBlocksWhenSchemasAndCodecsMatch() throws Exception {
    File source = writeIntFile(10, 20);
    File target = folder.newFile();

    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, target);
    writer.append(1);
    try (DataFileStream<Integer> other =
        new DataFileStream<Integer>(new FileInputStream(source), new GenericDatumReader<Integer>())) {
      writer.appendAllFrom(other, false);
    }
    writer.close();

    assertEquals(Arrays.asList(1, 10, 20), readInts(target));
  }

  @Test
  public void appendAllFromRecompressesWhenRequestedAndRejectsSchemaMismatch() throws Exception {
    File source = writeIntFile(30, 40);
    File target = folder.newFile();

    DataFileWriter<Integer> writer = newIntWriter();
    writer.setCodec(CodecFactory.deflateCodec(1));
    writer.create(INT_SCHEMA, target);
    try (DataFileStream<Integer> other =
        new DataFileStream<Integer>(new FileInputStream(source), new GenericDatumReader<Integer>())) {
      writer.appendAllFrom(other, true);
    }
    writer.close();
    assertEquals(Arrays.asList(30, 40), readInts(target));

    File stringSource = writeStringFile("wrong schema");
    File mismatchTarget = folder.newFile();
    DataFileWriter<Object> objectWriter =
        new DataFileWriter<Object>(new GenericDatumWriter<Object>(INT_SCHEMA));
    objectWriter.create(INT_SCHEMA, mismatchTarget);
    try (DataFileStream<Object> other =
        new DataFileStream<Object>(new FileInputStream(stringSource), new GenericDatumReader<Object>())) {
      IOException mismatch = expectThrows(IOException.class, () -> objectWriter.appendAllFrom(other, false));
      assertTrue(mismatch.getMessage().contains("does not match"));
    } finally {
      objectWriter.close();
    }
  }

  @Test
  public void syncFlushFsyncAndCloseExposeReadableDataAndUpdateState() throws Exception {
    SyncableByteArrayOutputStream out = new SyncableByteArrayOutputStream();
    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, out);
    writer.append(123);

    long positionAfterSync = writer.sync();
    assertTrue(positionAfterSync > 0);

    writer.append(456);
    writer.flush();
    assertEquals(Arrays.asList(123, 456), readInts(out.toByteArray()));

    writer.append(789);
    writer.fSync();
    assertTrue(out.synced);
    assertEquals(Arrays.asList(123, 456, 789), readInts(out.toByteArray()));

    writer.close();
    writer.close(); // already-closed close is a no-op.

    AvroRuntimeException appendAfterClose =
        expectThrows(AvroRuntimeException.class, () -> writer.append(999));
    assertTrue(appendAfterClose.getMessage().contains("not open"));
  }

  @Test
  public void closeFlushesAndClosesUnderlyingOutputStream() throws Exception {
    CloseAwareByteArrayOutputStream out = new CloseAwareByteArrayOutputStream();
    DataFileWriter<Integer> writer = newIntWriter();
    writer.create(INT_SCHEMA, out);
    writer.append(12);
    writer.close();

    assertTrue(out.closed);
    assertEquals(Arrays.asList(12), readInts(out.toByteArray()));
  }

  @Test
  public void appendWriteExceptionKeepsCause() {
    IOException cause = new IOException("boom");
    DataFileWriter.AppendWriteException exception = new DataFileWriter.AppendWriteException(cause);
    assertSame(cause, exception.getCause());
  }

  private static class PartiallyFailingIntDatumWriter implements DatumWriter<Integer> {
    @Override
    public void setSchema(Schema schema) {
      // The test writer always emits integer binary data.
    }

    @Override
    public void write(Integer datum, Encoder out) throws IOException {
      if (datum == -1) {
        out.writeInt(999); // Simulate a writer that dirties the buffer before failing.
        throw new IOException("intentional partial write failure");
      }
      out.writeInt(datum);
    }
  }

  private static class SyncableByteArrayOutputStream extends ByteArrayOutputStream implements Syncable {
    private boolean synced;

    @Override
    public void sync() throws IOException {
      synced = true;
    }
  }

  private static class CloseAwareByteArrayOutputStream extends ByteArrayOutputStream {
    private boolean closed;

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
