package de.marcely.pocketcraft.java.component;

import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;

public abstract class ItemMeta {
	
	protected final NBTTagCompound nbt;
	
	public ItemMeta(NBTTagCompound nbt){
		this.nbt = nbt;
	}
	
	public abstract ItemMetaType getType();
	
	public NBTTagCompound getNBT(){
		return this.nbt;
	}
}
