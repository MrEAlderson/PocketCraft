package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueString extends NBTNumericValue<String>  /* Numeric because we need to send a number*/ {

	public NBTValueString(String value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_STRING; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		writeString(stream, order, this.value);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = readString(stream, order);
	}
	
	public static void writeString(ByteArrayWriter stream, ByteOrder order, String str) throws Exception {
		stream.writeUnsignedVarInt(str.length());
		stream.write(str.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String readString(ByteArrayReader stream, ByteOrder order) throws Exception {
		final byte[] buffer = new byte[(int) stream.readUnsignedVarInt()];
		
		for(int i=0; i<buffer.length; i++)
			buffer[i] = stream.readSignedByte();
		
		return new String(buffer, StandardCharsets.UTF_8);
	}
}
