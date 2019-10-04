package de.marcely.pocketcraft.bedrock.world;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.world.entity.Entity;
import lombok.Getter;

public class World {
	
	@Getter private final Map<Long, Chunk> chunks = new HashMap<>();
	@Getter private final Map<Long, Entity> entities = new HashMap<>();
	
	public World(){ }
}