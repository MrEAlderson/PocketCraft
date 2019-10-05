package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueByte extends NBTNumericValue<Byte> {
	
	public NBTValueByte(Byte value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_BYTE; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedByte((byte) this.value, order);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readSignedByte(order);
	}
}
