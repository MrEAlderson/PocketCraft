package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import lombok.Getter;
import lombok.Setter;

public class World {
	
	@Getter private Map<Long, Chunk> chunksMap = new ConcurrentHashMap<>();
	
	@Getter @Setter private Dimension dimension;
	
	@Getter @Setter private int spawnX, spawnY, spawnZ;
	
	public void unloadAllChunks(){
		this.chunksMap.clear();
	}
	
	public void addChunk(int x, int z, Chunk chunk){
		this.chunksMap.put(getChunkIndex(x, z), chunk);
	}
	
	public @Nullable Chunk getChunk(int x, int z){
		return this.chunksMap.get(getChunkIndex(x, z));
	}
	
	@SuppressWarnings("unchecked")
	public @Nullable <T extends Chunk>T getChunk(int x, int z, Class<T> clazz){
		return (T) getChunk(x, z);
	}
	
	private long getChunkIndex(int x, int z){
		return ((x & 0xFFFFFFFFL) | ((long) z << 32L));
	}
	
	public boolean unloadChunk(int x, int z){
		return this.chunksMap.remove(getChunkIndex(x, z)) != null;
	}
	
	public Collection<Chunk> getChunks(){
		return this.chunksMap.values();
	}
}