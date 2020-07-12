package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;

public class NBTValueList extends NBTValue<List<NBTValue<?>>> {

	@SuppressWarnings("unchecked")
	public <T extends NBTValue<?>> NBTValueList(List<T> value){
		super((List<NBTValue<?>>) value);
	}

	@Override
	public byte getType(){ return TYPE_LIST; }

	@Override
	public void write(NBTByteBuf stream){
		if(this.data.size() >= 1)
			stream.writeByte(this.data.get(0).getType());
		else
			stream.writeByte(0x01);
		
		stream.writeInt(this.data.size());
		
		for(NBTValue<?> val:this.data)
			val.write(stream);
	}

	@Override
	public void read(NBTByteBuf stream){
		final byte type = stream.readByte();
		final int length = stream.readInt();
		
		this.data = new ArrayList<>(length);
		
		for(int i=0; i<length; i++){
			final NBTValue<?> val = NBTValue.newInstance(type);
			
			val.read(stream);
			
			this.data.add(val);
		}
	}

	@Override
	public String toString(){
		final StringBuilder builder = new StringBuilder("[");
		
		for(int i=0; i<this.data.size(); i++){
			if(i != 0)
				builder.append(",");
			
			builder.append(i + ":").append(this.data.get(i).toString());
		}
		
		return builder.append("]").toString();
	}
}
