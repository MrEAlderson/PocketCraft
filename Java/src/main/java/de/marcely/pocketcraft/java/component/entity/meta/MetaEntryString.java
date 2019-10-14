package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryString extends MetaEntry<String> implements MetaEntryV8<String> {

	public MetaEntryString(){
		this("");
	}
	
	public MetaEntryString(String data){
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
