package de.marcely.pocketcraft.bedrock.network;

public enum Channel {
	
	NONE(0),
	PRIORITY(1),
	WORLD_CHUNKS(2),
	MOVEMENT(3),
	BLOCKS(4),
	WORLD_EVENTS(5),
	ENTITY(6),
	CHAT(7),
	INVENTORY(8),
	END(31);
	
	public final int id;
	
	private Channel(int id){
		this.id = id;
	}
}
