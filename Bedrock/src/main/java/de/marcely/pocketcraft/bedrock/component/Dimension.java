package de.marcely.pocketcraft.bedrock.component;

import org.jetbrains.annotations.Nullable;

public enum Dimension {
	
	OVERWORLD, // 0
	NETHER, // 1
	THE_END; // 2
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable Dimension getById(byte id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
