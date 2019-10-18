package de.marcely.pocketcraft.bedrock.network.packet.action;

import org.jetbrains.annotations.Nullable;

public enum ActionType {
	
	NORMAL, // 0
	MISMATCH, // 1
	USE_ITEM, // 2
	USE_ITEM_ON_ENTITY, // 3
	CANCEL_USE_ITEM; // 4
	
	public int getId(){
		return this.ordinal();
	}
	
	public static @Nullable ActionType getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return ActionType.values()[id];
	}
}
