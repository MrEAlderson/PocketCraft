package de.marcely.pocketcraft.utils.io;

import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZLib {

	public static byte[] deflate(byte[] data, int bufferSize) throws IOException {
		final byte[] buffer = new byte[bufferSize];
		
		return Arrays.copyOf(buffer, deflate(data, buffer));
	}

	public static int deflate(byte[] data, byte[] output) throws IOException {
		return deflate(data, output, Deflater.DEFAULT_COMPRESSION);
	}

	public static int deflate(byte[] data, byte[] output, int level) throws IOException {
		final Deflater deflater = new Deflater(level);

		deflater.setInput(data);
		deflater.finish();

		return deflater.deflate(output);
	}
	
	public static byte[] inflate(byte[] data, int bufferSize) throws IOException, DataFormatException {
		final byte[] buffer = new byte[bufferSize];
		
		return Arrays.copyOf(buffer, inflate(data, buffer));
	}
	
	public static int inflate(byte[] data, byte[] output) throws IOException, DataFormatException {
		final Inflater inflater = new Inflater();
		
		inflater.setInput(data);
		
		final int size = inflater.inflate(output);
		
		inflater.end(); // do we have to? not sure
		
		return size;
	}
}
