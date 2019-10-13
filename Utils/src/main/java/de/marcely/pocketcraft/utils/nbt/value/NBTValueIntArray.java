package de.marcely.pocketcraft.utils.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueIntArray extends NBTNumericValue<int[]> /* Numeric because we need to send a number*/ {

	public NBTValueIntArray(int[] value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedVarInt(this.value.length, order);
		
		for(Integer b:this.value)
			stream.writeSignedVarInt(b, order);;
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = new int[stream.readSignedVarInt(order)];
		
		for(int i=0; i<this.value.length; i++)
			this.value[i] = stream.readSignedVarInt(order);
	}
}
