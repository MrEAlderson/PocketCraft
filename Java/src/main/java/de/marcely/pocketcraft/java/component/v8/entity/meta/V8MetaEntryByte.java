package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryByte extends MetaEntry<Byte> implements V8MetaEntry<Byte> {

	public V8MetaEntryByte(){
		this((byte) 0);
	}
	
	public V8MetaEntryByte(byte data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeByte(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readByte();
	}

	@Override
	public byte getV8Id(){
		return 0;
	}
}
