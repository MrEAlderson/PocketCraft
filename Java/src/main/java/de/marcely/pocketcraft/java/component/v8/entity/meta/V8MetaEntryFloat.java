package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryFloat extends MetaEntry<Float> implements V8MetaEntry<Float> {

	public V8MetaEntryFloat(){
		this(0F);
	}
	
	public V8MetaEntryFloat(float data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeFloat(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readFloat();
	}

	@Override
	public byte getV8Id(){
		return 3;
	}
}
