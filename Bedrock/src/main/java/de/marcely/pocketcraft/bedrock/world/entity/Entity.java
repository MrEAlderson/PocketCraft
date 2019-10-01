package de.marcely.pocketcraft.bedrock.world.entity;

import de.marcely.pocketcraft.bedrock.network.packet.PacketOutEntityData;
import de.marcely.pocketcraft.bedrock.server.player.Player;
import lombok.Getter;

public class Entity {
	
	@Getter private final EntityType type;
	@Getter private final long id;
	
	@Getter private final EntityMetadata metadata;
	
	public Entity(EntityType type, long id){
		this.type = type;
		this.id = id;
		
		this.metadata = new EntityMetadata();
		
		this.metadata.applyDefault(this);
	}
	
	public void setDataFlag(int key, boolean value){
		if(getDataFlag(key) == value)
			return;
		
		long flags = this.metadata.getLong(EntityDataType.FLAGS);
		
		flags ^= 1L << id;
		
		this.metadata.setLong(EntityDataType.FLAGS, flags);
	}
	
	public void setDataPlayerFlag(int key, boolean value){
		if(this.type != EntityType.PLAYER)
			throw new IllegalStateException("Entity isn't a player");
		
		if(getDataPlayerFlag(key) == value)
			return;
		
		byte flags = this.metadata.getByte(EntityDataType.PLAYER_FLAGS);
		
		flags ^= 1 << id;
		
		this.metadata.setByte(EntityDataType.PLAYER_FLAGS, flags);
	}
	
	public boolean getDataFlag(int key){
		return (this.metadata.getLong(EntityDataType.FLAGS) & (1L << id)) > 0;
	}
	
	public boolean getDataPlayerFlag(int key){
		if(this.type != EntityType.PLAYER)
			throw new IllegalStateException("Entity isn't a player");
		
		return ((this.metadata.getByte(EntityDataType.PLAYER_FLAGS) & 0xFF) & (1 << id)) > 0;
	}
	
	public void sendAllMetadata(Player to){
		final PacketOutEntityData packet = new PacketOutEntityData();
		
		packet.entityRuntimeID = this.id;
		packet.metadata = this.metadata;
		
		to.sendPacket(packet);
	}
}