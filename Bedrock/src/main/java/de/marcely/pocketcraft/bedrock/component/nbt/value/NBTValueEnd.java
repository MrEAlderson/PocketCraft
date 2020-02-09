package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;

public class NBTValueEnd extends NBTValue<Void> {

	public NBTValueEnd(){ super(null); }

	@Override
	public byte getType(){ return TYPE_END; }

	@Override
	public void write(NBTByteBuf stream){ }

	@Override
	public void read(NBTByteBuf stream){ }
}
