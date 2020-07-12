package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryString extends MetaEntry<String> implements V8MetaEntry<String> {

	public V8MetaEntryString(){
		this("");
	}
	
	public V8MetaEntryString(String data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeString(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readString();
	}

	@Override
	public byte getV8Id(){
		return 4;
	}
}
