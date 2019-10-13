package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import de.marcely.pocketcraft.utils.nbt.value.NBTNumericValue;

public class NBTValueDouble extends NBTNumericValue<Double> {

	public NBTValueDouble(Double value){
		super(value);
	}

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeDouble(this.value, order);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readDouble(order);
	}

	@Override
	public byte getID(){ return TYPE_DOUBLE; }
}
