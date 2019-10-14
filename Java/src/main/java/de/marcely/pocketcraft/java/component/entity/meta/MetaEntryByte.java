package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryByte extends MetaEntry<Byte> implements MetaEntryV8<Byte> {

	public MetaEntryByte(){
		this((byte) 0);
	}
	
	public MetaEntryByte(byte data){
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
