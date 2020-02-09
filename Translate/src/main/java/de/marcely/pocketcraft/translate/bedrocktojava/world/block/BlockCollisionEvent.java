package de.marcely.pocketcraft.translate.bedrocktojava.world.block;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BlockCollisionEvent {
	
	private final int x, y, z;
	private final BlockState state;
	private final BlockCollision.Cube intersecting;
}