package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

interface MetaEntryV8<T> {
	
	public T getData();
	
	public byte getV8Id();
	
	public void write(EByteBuf buf);
	
	public void read(EByteBuf buf);
	
	
	
	public static MetaEntryV8<?> newInstanceById(byte id){
		switch(id){
		case 0:
			return new MetaEntryByte();
		case 1:
			return new MetaEntryShort();
		case 2:
			return new MetaEntryInt();
		case 3:
			return new MetaEntryFloat();
		case 4:
			return new MetaEntryString();
		case 5:
			return new MetaEntryItem();
		case 6:
			return new MetaEntryBlockPosition();
		case 7:
			return new MetaEntryVector3();
		default:
			return null;
		}
	}
}
