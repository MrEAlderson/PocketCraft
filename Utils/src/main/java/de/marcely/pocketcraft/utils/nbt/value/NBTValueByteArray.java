package de.marcely.pocketcraft.utils.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueByteArray extends NBTNumericValue<byte[]> /* Numeric because we need to send a number*/ {

	public NBTValueByteArray(byte[] value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedVarInt(this.value.length, order);
		
		for(Byte b:this.value)
			stream.write(b);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = new byte[stream.readSignedVarInt(order)];
		
		for(int i=0; i<this.value.length; i++)
			this.value[i] = stream.readSignedByte();
	}
}
