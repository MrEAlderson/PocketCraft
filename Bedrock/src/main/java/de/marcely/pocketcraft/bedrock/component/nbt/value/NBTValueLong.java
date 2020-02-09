package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;

public class NBTValueLong extends NBTNumericValue<Long> {

	public NBTValueLong(Long value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_LONG; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeLong(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readLong();
	}
}
