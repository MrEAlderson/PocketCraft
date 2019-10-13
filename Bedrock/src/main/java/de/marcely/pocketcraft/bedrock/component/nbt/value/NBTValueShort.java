package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueShort extends NBTNumericValue<Short> {

	public NBTValueShort(Short value){
		super(value);
	}
	
	@Override
	public byte getID(){ return TYPE_SHORT; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedShort(this.value, order);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readSignedShort(order);
	}
}
