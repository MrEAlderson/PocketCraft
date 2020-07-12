package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueDouble extends BNBTNumericValue<Double> {

	public BNBTValueDouble(Double value){
		super(value);
	}
	
	@Override
	public byte getType(){ return TYPE_DOUBLE; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeDouble(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readDouble();
	}
	
	@Override
	public String toString(){
		return this.data + "d";
	}
}
