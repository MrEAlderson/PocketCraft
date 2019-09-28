package de.marcely.pocketcraft.pocket.network.packet.action;

import java.util.HashMap;
import java.util.Map;

public enum InventoryActionSourceType {
	
	CONTAINER(0),
	WORLD(2),
	CREATIVE(3);
	
	
	public static final Map<Long, InventoryActionSourceType> VALUES = new HashMap<>();
	
	public final long id;
	
	static {
		for(InventoryActionSourceType type:values())
			VALUES.put(type.id, type);
	}
	
	private InventoryActionSourceType(long id){
		this.id = id;
	}
}
