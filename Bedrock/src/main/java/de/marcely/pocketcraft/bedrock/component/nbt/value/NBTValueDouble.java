package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;

public class NBTValueDouble extends NBTNumericValue<Double> {

	public NBTValueDouble(Double value){
		super(value);
	}
	
	@Override
	public byte getType(){ return TYPE_DOUBLE; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeDouble(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readDouble();
	}
	
	@Override
	public String toString(){
		return this.data + "d";
	}
}
