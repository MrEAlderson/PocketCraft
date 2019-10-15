package de.marcely.pocketcraft.java.component;

import org.jetbrains.annotations.Nullable;

public enum BlockFace {
	
	NORTH,
	WEST,
	SOUTH,
	EAST;
	
	public byte getId(){
		return (byte) (this.ordinal());
	}
	
	public static @Nullable BlockFace ofId(byte id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
