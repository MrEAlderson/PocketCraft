package de.marcely.pocketcraft.bedrock.component.world;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.component.world.entity.Entity;
import lombok.Getter;

public class BWorld {
	
	@Getter private final Map<Long, BChunk> chunks = new HashMap<>();
	@Getter private final Map<Long, Entity> entities = new HashMap<>();
	
	public BWorld(){ }
}