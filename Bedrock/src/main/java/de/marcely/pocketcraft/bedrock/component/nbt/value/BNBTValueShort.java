package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueShort extends BNBTNumericValue<Short> {

	public BNBTValueShort(Short value){
		super(value);
	}
	
	@Override
	public byte getType(){ return TYPE_SHORT; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeShort(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readShort();
	}

	@Override
	public String toString(){
		return this.data + "s";
	}
}
