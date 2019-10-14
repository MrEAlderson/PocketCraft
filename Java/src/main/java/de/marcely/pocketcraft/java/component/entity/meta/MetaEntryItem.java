package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryItem extends MetaEntry<Item> implements MetaEntryV8<Item> {

	public MetaEntryItem(){
		this(null);
	}
	
	public MetaEntryItem(Item data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeItem(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readItem();
	}

	@Override
	public byte getV8Id(){
		return 5;
	}
}
