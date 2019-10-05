package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueInt extends NBTNumericValue<Integer> {

	public NBTValueInt(Integer value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_INT; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedVarInt(this.value);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = stream.readSignedVarInt();
	}
}
