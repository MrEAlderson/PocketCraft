package de.marcely.pocketcraft.java.component;

import lombok.Getter;
import lombok.Setter;

public abstract class Item {
	
	@Getter private final int type;
	@Getter @Setter private int amount;
	
	@Getter private final ItemMeta meta;
	
	public Item(int type, int amount, ItemMeta meta){
		this.type = type;
		this.amount = amount;
		this.meta = meta;
	}
}