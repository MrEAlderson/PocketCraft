package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;

public class NBTValueFloat extends NBTNumericValue<Float> {

	public NBTValueFloat(Float value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_FLOAT; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeFloat(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readFloat();
	}
	
	@Override
	public String toString(){
		return this.data + "f";
	}
}
