package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

public class NBTValueCompound extends NBTValue<NBTCompound> {
	
	public NBTValueCompound(NBTCompound value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_COMPOUND; }

	@Override
	public void write(NBTByteBuf stream){
		this.data.write(stream);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = new NBTCompound();
		
		this.data.read(stream);
	}
}
