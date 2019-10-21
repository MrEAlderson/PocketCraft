package de.marcely.pocketcraft.java.component.v8;

import org.jetbrains.annotations.Nullable;

public enum V8Dimension {
	
	NETHER, // -1
	OVERWORLD, // 0
	THE_END; // 1
	
	public byte getId(){
		return (byte) (this.ordinal()-1);
	}
	
	public static @Nullable V8Dimension getById(int id){
		id += 1;
		
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
