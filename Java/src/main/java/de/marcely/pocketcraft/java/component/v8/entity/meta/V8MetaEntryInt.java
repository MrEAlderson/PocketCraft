package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryInt extends MetaEntry<Integer> implements V8MetaEntry<Integer> {

	public V8MetaEntryInt(){
		this(0);
	}
	
	public V8MetaEntryInt(int data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeInt(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readInt();
	}

	@Override
	public byte getV8Id(){
		return 2;
	}
}
