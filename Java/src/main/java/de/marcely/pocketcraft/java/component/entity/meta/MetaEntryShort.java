package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryShort extends MetaEntry<Short> implements MetaEntryV8<Short> {

	public MetaEntryShort(){
		this((short) 0);
	}
	
	public MetaEntryShort(short data){
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
