package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueEnd extends NBTValue<Void> {

	public NBTValueEnd(){ super(null); }

	@Override
	public byte getID(){ return TYPE_END; }

	@Override
	public void write(ByteArrayWriter stream){ }

	@Override
	public void read(ByteArrayReader stream){ }
}
