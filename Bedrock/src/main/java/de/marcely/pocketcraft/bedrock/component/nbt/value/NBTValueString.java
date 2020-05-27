package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import lombok.ToString;

@ToString
public class NBTValueString extends NBTValue<String> {

	public NBTValueString(String value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_STRING; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeUTF(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readUTF();
	}
}
