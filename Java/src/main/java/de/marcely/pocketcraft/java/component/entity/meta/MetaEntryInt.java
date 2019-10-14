package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryInt extends MetaEntry<Integer> implements MetaEntryV8<Integer> {

	public MetaEntryInt(){
		this(0);
	}
	
	public MetaEntryInt(int data){
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
