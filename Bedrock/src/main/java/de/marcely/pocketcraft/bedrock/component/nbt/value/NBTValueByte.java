package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import lombok.ToString;

@ToString
public class NBTValueByte extends NBTNumericValue<Byte> {
	
	public NBTValueByte(Byte value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeByte(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readByte();
	}
}
