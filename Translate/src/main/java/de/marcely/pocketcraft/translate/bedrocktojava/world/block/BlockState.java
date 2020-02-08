package de.marcely.pocketcraft.translate.bedrocktojava.world.block;

import lombok.Getter;

@Getter
public class BlockState {
	
	private final short bedrockId;
	private final byte bedrockData;
	private final BlockCollision collision;
	
	public BlockState(int bedrockId, int bedrockData, BlockCollision collision){
		this.bedrockId = (short) bedrockId;
		this.bedrockData = (byte) bedrockData;
		this.collision = collision;
	}
}
