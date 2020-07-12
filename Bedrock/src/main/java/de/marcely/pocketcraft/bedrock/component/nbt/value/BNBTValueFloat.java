package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueFloat extends BNBTNumericValue<Float> {

	public BNBTValueFloat(Float value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_FLOAT; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeFloat(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readFloat();
	}
	
	@Override
	public String toString(){
		return this.data + "f";
	}
}
