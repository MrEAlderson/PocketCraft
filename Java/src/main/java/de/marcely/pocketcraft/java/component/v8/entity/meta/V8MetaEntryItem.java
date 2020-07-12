package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.component.v8.item.V8Item;
import de.marcely.pocketcraft.java.util.EByteBuf;

class V8MetaEntryItem extends MetaEntry<V8Item> implements V8MetaEntry<V8Item> {

	public V8MetaEntryItem(){
		this(null);
	}
	
	public V8MetaEntryItem(V8Item data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeV8Item(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readV8Item();
	}

	@Override
	public byte getV8Id(){
		return 5;
	}
}
