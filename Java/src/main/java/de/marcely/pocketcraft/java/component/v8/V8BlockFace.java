package de.marcely.pocketcraft.java.component.v8;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum V8BlockFace {
	
	DOWN, // 0
	UP, // 1
	NORTH, // 2
	SOUTH, // 3
	WEST, // 4
	EAST; // 5
	
	private static final V8BlockFace[] SIDE_VALUES;
	
	@Getter private byte sideId;
	
	static {
		// do not change order
		SIDE_VALUES = new V8BlockFace[]{
			SOUTH,
			WEST,
			NORTH,
			EAST
		};
		
		for(byte i=0; i<SIDE_VALUES.length; i++)
			SIDE_VALUES[i].sideId = i;
	}
	
	public static @Nullable V8BlockFace getBySideId(int id){
		if(id < 0 || id >= SIDE_VALUES.length)
			return null;
		
		return SIDE_VALUES[id];
	}
	
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable V8BlockFace getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
