package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import lombok.Getter;
import lombok.Setter;

public abstract class Chunk {
	
	@Getter private Map<Short, BlockEntity> blockEntities = new HashMap<>();
	
	@Getter @Setter private boolean sent = false;
	
	public abstract PacketFullChunk buildPacket(int x, int z);
	
	private short getBlockEntityIndex(int x, int y, int z){
		return (short) (x | (y << 4) | (z << 12));
	}
	
	public @Nullable BlockEntity getBlockEntity(int x, int y, int z){
		return this.blockEntities.get(getBlockEntityIndex(x, y, z));
	}
	
	public @Nullable BlockEntity removeBlockEntity(int x, int y, int z){
		return this.blockEntities.remove(getBlockEntityIndex(x, y, z));
	}
	
	public void addBlockEntity(int x, int y, int z, BlockEntity entity){
		this.blockEntities.put(getBlockEntityIndex(x, y, z), entity);
	}
}