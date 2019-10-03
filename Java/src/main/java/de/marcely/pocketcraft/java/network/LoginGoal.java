package de.marcely.pocketcraft.java.network;

public enum LoginGoal {
	
	// do not change oder
	SERVER_INFO,
	PLAY;
	
	public byte getId(){
		return (byte) (this.ordinal()+1);
	}
}
