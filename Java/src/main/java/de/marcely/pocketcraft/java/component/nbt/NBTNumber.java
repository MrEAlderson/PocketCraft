package de.marcely.pocketcraft.java.component.nbt;

public abstract class NBTNumber<T> extends NBTBase<T> {
	
	public NBTNumber(){
		super();
	}
	
	public NBTNumber(T data){
		super(data);
	}
}
