package de.marcely.pocketcraft.bedrock.component;

import org.jetbrains.annotations.Nullable;

public enum BlockFace {
	
	DOWN, // 0
	UP, // 1
	NORTH, // 2
	SOUTH, // 3
	WEST, // 4
	EAST; // 5
	
	public byte getId(){
		return (byte) (this.ordinal());
	}
	
	public int getHorizontalId(){
		switch(this){
		case SOUTH:
			return 0;
		case WEST:
			return 1;
		case NORTH:
			return 2;
		case EAST:
			return 3;
		default:
			return -1;
		}
	}
	
	public static @Nullable BlockFace getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
