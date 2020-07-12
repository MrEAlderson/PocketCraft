package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueInt extends BNBTNumericValue<Integer> {

	public BNBTValueInt(Integer value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_INT; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeInt(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readInt();
	}
	
	@Override
	public String toString(){
		return this.data + "";
	}
}
