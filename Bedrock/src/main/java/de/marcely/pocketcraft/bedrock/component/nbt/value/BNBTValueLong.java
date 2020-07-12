package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueLong extends BNBTNumericValue<Long> {

	public BNBTValueLong(Long value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_LONG; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeLong(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readLong();
	}

	@Override
	public String toString(){
		return this.data + "L";
	}
}
