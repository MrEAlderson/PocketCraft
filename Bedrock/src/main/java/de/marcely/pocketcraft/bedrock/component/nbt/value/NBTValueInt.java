package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import lombok.ToString;

@ToString
public class NBTValueInt extends NBTNumericValue<Integer> {

	public NBTValueInt(Integer value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_INT; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeInt(this.data);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = stream.readInt();
	}
}
