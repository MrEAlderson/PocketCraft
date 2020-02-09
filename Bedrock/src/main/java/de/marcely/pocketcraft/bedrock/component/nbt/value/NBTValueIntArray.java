package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;

public class NBTValueIntArray extends NBTValue<int[]> {

	public NBTValueIntArray(int[] value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(NBTByteBuf stream){
		stream.writeInt(this.data.length);
		
		for(int e:this.data)
			stream.writeInt(e);
	}

	@Override
	public void read(NBTByteBuf stream){
		this.data = new int[stream.readInt()];
		
		for(int i=0; i<this.data.length; i++)
			this.data[i] = stream.readInt();
	}
}
