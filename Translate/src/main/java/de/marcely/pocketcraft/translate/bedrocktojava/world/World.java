package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public class World {
	
	@Getter private Map<Long, Chunk> chunks = new HashMap<>();
	
	public void unloadAllChunks(){
		this.chunks.clear();
	}
	
	public void addChunk(int x, int z, Chunk chunk){
		this.chunks.put(getChunkIndex(x, z), chunk);
	}
	
	public @Nullable Chunk getChunk(int x, int z){
		return this.chunks.get(getChunkIndex(x, z));
	}
	
	@SuppressWarnings("unchecked")
	public @Nullable <T extends Chunk>T getChunk(int x, int z, Class<T> clazz){
		return (T) getChunk(x, z);
	}
	
	private long getChunkIndex(int x, int z){
		return (((long) x) | ((long) z << 32L));
	}
	
	public void unloadFarChunks(int x, int z, int distance){
		final Iterator<Long> it = chunks.keySet().iterator();
		
		while(it.hasNext()){
			final long combined = it.next();
			final int cX = (int) combined;
			final int cZ = (int) (combined >> 32L);
			
			if(Math.abs(x - cX) + Math.abs(z - cZ) > distance)
				it.remove();
		}
	}
}