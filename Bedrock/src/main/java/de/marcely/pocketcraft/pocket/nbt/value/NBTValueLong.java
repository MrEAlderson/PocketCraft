package de.marcely.pocketcraft.pocket.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueLong extends NBTNumericValue<Long> {

	public NBTValueLong(Long value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_LONG; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedVarLong(this.value, order);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readSignedVarLong(order);
	}
}
