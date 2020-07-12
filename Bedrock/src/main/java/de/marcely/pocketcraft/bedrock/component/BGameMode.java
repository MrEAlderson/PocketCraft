package de.marcely.pocketcraft.bedrock.component;

import org.jetbrains.annotations.Nullable;

public enum BGameMode {
	
	SPECIAL_1, // -1
	
	SURVIVAL, // 0
	CREATIVE, // 1
	ADVENTURE, // 2
	SPECTATOR; // 3
	
	public byte getId(){
		return (byte) (this.ordinal()-1);
	}
	
	public static @Nullable BGameMode getById(byte id){
		id++;
		
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
