package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;
import lombok.Getter;
import lombok.Setter;

public class World {
	
	@Getter private final Player player;
	
	@Getter private Map<Long, Chunk> chunksMap = new ConcurrentHashMap<>();
	@Getter private Map<Integer, Entity> entitiesMap = new ConcurrentHashMap<>();
	
	@Getter @Setter private Dimension dimension;
	@Getter @Setter private int spawnX, spawnY, spawnZ;
	
	@Getter private PlayerList playerList = new PlayerList();
	
	public World(Player player){
		this.player = player;
	}
	
	public TranslateComponents getTranslateComponents(){
		return this.player.getTranslateComponents();
	}
	
	public void tick(int currentTick){
		for(Entity entity:this.getEntities())
			entity.tick();
	}
	
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
	
	public @Nullable BlockEntity getBlockEntity(int x, int y, int z){
		final Chunk chunk = getChunk(x >> 4, z >> 4);
		
		if(chunk == null)
			return null;
		
		return chunk.getBlockEntity(x & 0xF, y, z & 0xF);
	}
	
	public @Nullable BlockState getBlockState(int x, int y, int z){
		final Chunk chunk = getChunk(x >> 4, z >> 4);
		
		if(chunk == null)
			return null;
		
		return chunk.getBlockState(x & 0xF, y, z & 0xF);
	}
	
	public void addEntity(Entity entity){
		this.entitiesMap.put(entity.getId(), entity);
	}
	
	public @Nullable Entity removeEntity(int id){
		return this.entitiesMap.remove(id);
	}
	
	public @Nullable Entity getEntity(int id){
		if(id == this.player.getEntityId())
			return this.player.getEntity();
		
		return this.entitiesMap.get(id);
	}
	
	public Collection<Entity> getEntities(){
		return this.entitiesMap.values();
	}
}