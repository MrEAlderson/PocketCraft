package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;

interface V8MetaEntry<T> {
	
	public T getData();
	
	public byte getV8Id();
	
	public void write(EByteBuf buf);
	
	public void read(EByteBuf buf);
	
	
	
	public static V8MetaEntry<?> newInstanceById(byte id){
		switch(id){
		case 0:
			return new V8MetaEntryByte();
		case 1:
			return new V8MetaEntryShort();
		case 2:
			return new V8MetaEntryInt();
		case 3:
			return new V8MetaEntryFloat();
		case 4:
			return new V8MetaEntryString();
		case 5:
			return new V8MetaEntryItem();
		case 6:
			return new V8MetaEntryBlockPosition();
		case 7:
			return new V8MetaEntryVector3();
		default:
			return null;
		}
	}
}
