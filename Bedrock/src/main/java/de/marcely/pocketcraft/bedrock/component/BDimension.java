package de.marcely.pocketcraft.bedrock.component;

import org.jetbrains.annotations.Nullable;

public enum BDimension {
	
	OVERWORLD, // 0
	NETHER, // 1
	THE_END; // 2
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable BDimension getById(byte id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
