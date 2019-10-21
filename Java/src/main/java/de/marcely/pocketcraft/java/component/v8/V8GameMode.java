package de.marcely.pocketcraft.java.component.v8;

import org.jetbrains.annotations.Nullable;

public enum V8GameMode {
	
	SPECIAL_1, // -1
	
	SURVIVAL, // 0
	CREATIVE, // 1
	ADVENTURE, // 2
	SPECTATOR; // 3
	
	public byte getId(){
		return (byte) (this.ordinal()-1);
	}
	
	public static @Nullable V8GameMode getById(int id){
		id++;
		
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
