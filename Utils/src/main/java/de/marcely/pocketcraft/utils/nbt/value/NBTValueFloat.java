package de.marcely.pocketcraft.utils.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueFloat extends NBTNumericValue<Float> {

	public NBTValueFloat(Float value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_FLOAT; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeFloat(this.value, order);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readFloat(order);
	}
}
