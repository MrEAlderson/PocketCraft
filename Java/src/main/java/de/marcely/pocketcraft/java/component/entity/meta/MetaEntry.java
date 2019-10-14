package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;
import lombok.Getter;

abstract class MetaEntry<T> {
	
	@Getter protected T data;
	
	public MetaEntry(){
		this(null);
	}
	
	public MetaEntry(T data){
		this.data = data;
	}
	
	public abstract void write(EByteBuf buf);
	
	public abstract void read(EByteBuf buf);
}