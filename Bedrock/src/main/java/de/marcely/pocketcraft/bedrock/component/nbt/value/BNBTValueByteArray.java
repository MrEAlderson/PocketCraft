package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;

public class BNBTValueByteArray extends BNBTValue<byte[]> {

	public BNBTValueByteArray(byte[] value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeInt(this.data.length);
		stream.writeBytes(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		stream.readBytes(this.data = new byte[stream.readInt()]);
	}
	
	@Override
	public String toString(){
		return "[" + this.data.length + " bytes]";
	}
}
