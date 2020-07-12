package de.marcely.pocketcraft.bedrock.component.permission;

import org.jetbrains.annotations.Nullable;

public enum BCommandPermissionLevel {
	
	NORMAL,
	OPERATOR,
	HOST,
	AUTOMATION,
	ADMIN;
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable BCommandPermissionLevel getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
