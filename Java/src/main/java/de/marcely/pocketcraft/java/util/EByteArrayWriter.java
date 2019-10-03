package de.marcely.pocketcraft.java.util;

import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class EByteArrayWriter extends ByteArrayWriter {
	
	public EByteArrayWriter(){
		super();
	}
	
	public EByteArrayWriter(int initCapacity){
		super(initCapacity);
	}
	
	@Override
	public void writeByteArray(byte[] array) throws IOException {
		writeSignedVarInt(array.length);
		write(array);
	}
	
	@Override
	public void writeSignedVarInt(int value) throws IOException {
		writeSignedVarLong(value);
	}
	
	@Override
	public void writeSignedVarLong(long value) throws IOException {
		do {
			byte temp = (byte)(value & 0b01111111);
			
			value >>>= 7;
			
			if(value != 0)
				temp |= 0b10000000;
			
			writeSignedByte(temp);
		}while(value != 0);
	}
	
	public void writeIdentifier(String val) throws IOException {
		writeString(val);
	}
}
