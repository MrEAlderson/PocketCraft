package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

class MetaEntryFloat extends MetaEntry<Float> implements MetaEntryV8<Float> {

	public MetaEntryFloat(){
		this(0F);
	}
	
	public MetaEntryFloat(float data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeFloat(this.data);
	}

	@Override
	public void read(EByteBuf buf){
		this.data = buf.readFloat();
	}

	@Override
	public byte getV8Id(){
		return 3;
	}
}
