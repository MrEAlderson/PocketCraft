package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import lombok.ToString;

@ToString
public class NBTValueByteArray extends NBTValue<byte[]> {

	public NBTValueByteArray(byte[] value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeInt(this.data.length);
		stream.writeBytes(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		stream.readBytes(this.data = new byte[stream.readInt()]);
	}
}
