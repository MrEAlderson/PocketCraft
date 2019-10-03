package de.marcely.pocketcraft.java.util;

import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;

public class EByteArrayReader extends ByteArrayReader {

	public EByteArrayReader(byte[] array){
		super(array);
	}
	
	public String readString(int maxLength) throws IOException {
		final String str = readString();
		
		if(str.getBytes().length > maxLength)
			throw new IOException("String is larget than expected");
		
		return str;
	}
	
	@Override
	public byte[] readByteArray() throws IOException {
		return read(readSignedVarInt());
	}
	
	@Override
	public int readSignedVarInt() throws IOException {
		return (int) readSignedVarNumber(5);
	}
	
	@Override
	public long readSignedVarLong() throws IOException {
		return readSignedVarNumber(10);
	}
	
	private long readSignedVarNumber(int maxSize) throws IOException {
		int numRead = 0;
		long result = 0;
		byte read;
		
		do {
			final int value = ((read = readSignedByte()) & 0b01111111);
			
			result |= (value << (7 * numRead));
			
			numRead++;
			
			if(numRead > maxSize)
				throw new RuntimeException("VarNumber is too big");
		}while((read & 0b10000000) != 0);
		
		return result;
	}
	
	public String readIdentifier() throws IOException {
		return readString(32767);
	}
}
