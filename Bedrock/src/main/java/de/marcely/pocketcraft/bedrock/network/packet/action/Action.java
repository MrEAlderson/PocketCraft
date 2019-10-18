package de.marcely.pocketcraft.bedrock.network.packet.action;

public abstract class Action {
	
	public abstract ActionType getType();
	
	public abstract int getHotbarSlot();
}
