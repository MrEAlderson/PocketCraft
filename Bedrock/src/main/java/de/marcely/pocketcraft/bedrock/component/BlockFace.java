package de.marcely.pocketcraft.bedrock.component;

import org.jetbrains.annotations.Nullable;

public enum BlockFace {
	
	// used when clicking to air with PacketPlayInBlockPlace
	NONE, // -1
	
	DOWN, // 0
	UP, // 1
	NORTH, // 2
	SOUTH, // 3
	WEST, // 4
	EAST; // 5
	
	public byte getId(){
		return (byte) (this.ordinal()-1);
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
	
	public static @Nullable BlockFace ofId(byte id){
		id++;
		
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
