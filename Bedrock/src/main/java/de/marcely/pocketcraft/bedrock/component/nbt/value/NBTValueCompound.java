package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueCompound extends NBTNumericValue<NBTCompound> {
	
	public NBTValueCompound(NBTCompound value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_COMPOUND; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		this.value.write(stream);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		this.value = NBTCompound.read(stream, order);
	}
}
