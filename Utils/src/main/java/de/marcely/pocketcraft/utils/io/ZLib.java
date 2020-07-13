package de.marcely.pocketcraft.utils.io;

import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZLib {

	private static final ThreadLocal<Inflater> INFLATER = ThreadLocal.withInitial(() -> new Inflater());
    private static final ThreadLocal<Deflater> DEFLATER = ThreadLocal.withInitial(() -> new Deflater(7));
    
	private static final ThreadLocal<Inflater> INFLATER_RAW = ThreadLocal.withInitial(() -> new Inflater(true));
    private static final ThreadLocal<Deflater> DEFLATER_RAW = ThreadLocal.withInitial(() -> new Deflater(7, true));
	
	public static byte[] deflate(byte[] data, int bufferSize) throws IOException {
		final byte[] buffer = new byte[bufferSize];
		
		return Arrays.copyOf(buffer, deflate(data, buffer));
	}

	public static int deflate(byte[] data, byte[] output) throws IOException {
		return deflate(data, output, Deflater.DEFAULT_COMPRESSION);
	}

	public static int deflate(byte[] data, byte[] output, int level) throws IOException {
		final Deflater deflater = DEFLATER.get();
		
		deflater.reset();
		deflater.setLevel(level);
		deflater.setInput(data);
		deflater.finish();

		return deflater.deflate(output);
	}
	
	public static int deflateRaw(byte[] data, byte[] output, int level) throws IOException {
		final Deflater deflater = DEFLATER_RAW.get();
		
		deflater.reset();
		deflater.setLevel(level);
		deflater.setInput(data);
		deflater.finish();
		
		return deflater.deflate(output);
	}
	
	public static byte[] inflate(byte[] data, int bufferSize) throws IOException, DataFormatException {
		final byte[] buffer = new byte[bufferSize];
		
		return Arrays.copyOf(buffer, inflate(data, buffer));
	}
	
	public static int inflate(byte[] data, byte[] output) throws IOException, DataFormatException {
		final Inflater inflater = INFLATER.get();
		
		inflater.reset();
		inflater.setInput(data);
		
		return inflater.inflate(output);
	}
	
	public static int inflateRaw(byte[] data, byte[] output) throws IOException, DataFormatException {
		final Inflater inflater = INFLATER_RAW.get();
		
		inflater.reset();
		inflater.setInput(data);
		
		return inflater.inflate(output);
	}
}
