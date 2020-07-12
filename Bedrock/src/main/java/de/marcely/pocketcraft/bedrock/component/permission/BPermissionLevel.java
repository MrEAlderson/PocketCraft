package de.marcely.pocketcraft.bedrock.component.permission;

import org.jetbrains.annotations.Nullable;

public enum BPermissionLevel {
	
	VISITOR,
	MEMBER,
	OPERATOR,
	CUSTOM;
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable BPermissionLevel getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
