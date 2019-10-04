package de.marcely.pocketcraft.java.component;

import org.jetbrains.annotations.Nullable;

public enum Dimension {
	
	NETHER, // -1
	OVERWORLD, // 0
	END; // 1
	
	public byte getId(){
		return (byte) (this.ordinal()-1);
	}
	
	public static @Nullable Dimension ofId(int id){
		id += 1;
		
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
