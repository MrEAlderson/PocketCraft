package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;

public class NBTValueShort extends NBTNumericValue<Short> {

	public NBTValueShort(Short value){
		super(value);
	}
	
	@Override
	public byte getType(){ return TYPE_SHORT; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeShort(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readShort();
	}
}
