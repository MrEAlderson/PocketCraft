package de.marcely.pocketcraft.java.component.v8.item;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import lombok.Getter;
import lombok.Setter;

public class V8Item extends Item {
	
	@Getter @Setter private int durability;
	
	public V8Item(int type){
		this(type, 1, 0, (V8ItemMeta) null);
	}
	
	public V8Item(int type, int amount){
		this(type, amount, 0, (V8ItemMeta) null);
	}
	
	public V8Item(int type, int amount, V8ItemMeta meta){
		this(type, amount, 0, meta);
	}
	
	public V8Item(int type, int amount, int durability, NBTTagCompound nbt){
		super(type, amount, V8ItemMeta.newInstance(type, nbt));
	}
	
	public V8Item(int type, int amount, int durability, V8ItemMeta meta){
		super(type, amount, meta);
	}
}
