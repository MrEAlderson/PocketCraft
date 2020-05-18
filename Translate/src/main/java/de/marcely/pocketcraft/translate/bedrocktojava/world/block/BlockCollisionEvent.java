package de.marcely.pocketcraft.translate.bedrocktojava.world.block;

import lombok.Data;

@Data
public class BlockCollisionEvent {
	
	private final int x, y, z;
	private final BlockState state;
	private final BlockCollision.Cube intersecting;
	
	private Float exactX = null, exactY, exactZ;
	
	public boolean hasExact(){
		return this.exactX != null;
	}
	
	public void setExact(float x, float y, float z){
		this.exactX = x;
		this.exactY = y;
		this.exactZ = z;
	}
}