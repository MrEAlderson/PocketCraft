package de.marcely.pocketcraft.bedrock.component.permission;

import org.jetbrains.annotations.Nullable;

public enum PermissionLevel {
	
	VISITOR,
	MEMBER,
	OPERATOR,
	CUSTOM;
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable PermissionLevel getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
