package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.util.HashMap;
import java.util.Map;

public enum ActionType {
	
	NORMAL(0),
	MISMATCH(1),
	USE_ITEM(2),
	USE_ITEM_ON_ENTITY(3),
	DROP_ITEM(4);
	
	public static Map<Long, ActionType> VALUES = new HashMap<>();
	
	static {
		for(ActionType type:values())
			VALUES.put(type.id, type);
	}
	
	public long id;
	
	private ActionType(long id){
		this.id = id;
	}
}
