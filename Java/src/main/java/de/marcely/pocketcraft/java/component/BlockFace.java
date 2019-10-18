package de.marcely.pocketcraft.java.component;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum BlockFace {
	
	DOWN,
	UP,
	NORTH,
	SOUTH,
	WEST,
	EAST;
	
	private static final BlockFace[] SIDE_VALUES;
	
	@Getter private byte sideId;
	
	static {
		// do not change order
		SIDE_VALUES = new BlockFace[]{
			SOUTH,
			WEST,
			NORTH,
			EAST
		};
		
		for(byte i=0; i<SIDE_VALUES.length; i++)
			SIDE_VALUES[i].sideId = i;
	}
	
	public static @Nullable BlockFace getBySideId(int id){
		if(id < 0 || id >= SIDE_VALUES.length)
			return null;
		
		return SIDE_VALUES[id];
	}
	
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable BlockFace getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
