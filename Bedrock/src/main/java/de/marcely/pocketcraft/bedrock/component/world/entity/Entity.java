package de.marcely.pocketcraft.bedrock.component.world.entity;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityData;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import lombok.Getter;

public abstract class Entity {
	
	@Getter protected final long longId;
	
	@Getter protected final EntityMetadata metadata;
	
	public Entity(long id){
		this.longId = id;
		
		this.metadata = new EntityMetadata();
		
		this.metadata.applyDefault(this);
	}
	
	public abstract EntityType getType();
	
	public void setDataFlag(int key, boolean value){
		if(getDataFlag(key) == value)
			return;
		
		long flags = this.metadata.getLong(EntityDataType.FLAGS);
		
		flags ^= 1L << key;
		
		this.metadata.setLong(EntityDataType.FLAGS, flags);
	}
	
	public void setDataPlayerFlag(int key, boolean value){
		if(getType() != EntityType.PLAYER)
			throw new IllegalStateException("Entity isn't a player");
		
		if(getDataPlayerFlag(key) == value)
			return;
		
		byte flags = this.metadata.getByte(EntityDataType.PLAYER_FLAGS);
		
		flags ^= 1 << key;
		
		this.metadata.setByte(EntityDataType.PLAYER_FLAGS, flags);
	}
	
	public boolean getDataFlag(int key){
		return (this.metadata.getLong(EntityDataType.FLAGS) & (1L << key)) > 0;
	}
	
	public boolean getDataPlayerFlag(int key){
		if(getType() != EntityType.PLAYER)
			throw new IllegalStateException("Entity isn't a player");
		
		return ((this.metadata.getByte(EntityDataType.PLAYER_FLAGS) & 0xFF) & (1 << key)) > 0;
	}
	
	public void sendAllMetadata(BedrockClient to){
		final PacketEntityData packet = (PacketEntityData) PacketType.EntityData.newInstance();
		
		packet.entityRuntimeID = this.longId;
		packet.metadata = this.metadata;
		
		to.sendPacket(packet);
	}
}