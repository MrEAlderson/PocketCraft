package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTNumericValue;

public class BNBTValueByte extends BNBTNumericValue<Byte> {
	
	public BNBTValueByte(Byte value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeByte(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readByte();
	}
	
	@Override
	public String toString(){
		return this.data + "b";
	}
}
