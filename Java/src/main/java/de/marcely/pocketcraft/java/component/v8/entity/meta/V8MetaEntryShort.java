package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryShort extends MetaEntry<Short> implements V8MetaEntry<Short> {

	public V8MetaEntryShort(){
		this((short) 0);
	}
	
	public V8MetaEntryShort(short data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeShort(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readShort();
	}

	@Override
	public byte getV8Id(){
		return 1;
	}
}
