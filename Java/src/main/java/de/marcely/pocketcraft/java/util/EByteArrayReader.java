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
}
