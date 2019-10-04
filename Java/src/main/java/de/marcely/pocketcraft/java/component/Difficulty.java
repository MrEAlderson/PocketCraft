package de.marcely.pocketcraft.java.component;

import org.jetbrains.annotations.Nullable;

public enum Difficulty {
	
	PEACEFUL, // 0
	EASY, // 1
	NORMAL, // 2
	HARD; // 3
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable Difficulty ofId(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
