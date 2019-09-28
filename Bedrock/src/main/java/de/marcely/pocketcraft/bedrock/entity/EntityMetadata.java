package de.marcely.pocketcraft.bedrock.entity;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public class EntityMetadata {
	
	@Getter private final Map<EntityDataType, Object> values = new HashMap<>();
	
	public EntityMetadata(){ }
	
	public EntityMetadata(EntityDataType type, Object value){
		this.values.put(type, value);
	}
	
	public void setValue(EntityDataType type, Object value){
		values.put(type, value);
	}
	
	public @Nullable Object getValue(EntityDataType type){
		return values.get(type);
	}
	
	public static EntityMetadata getDefault(EntityType type){
		final EntityMetadata meta = new EntityMetadata();
		
		meta.setValue(EntityDataType.AIR, (short) 300);
		meta.setValue(EntityDataType.SCALE, 1F);
		meta.setValue(EntityDataType.MAX_AIR, (short) 400);
		meta.setValue(EntityDataType.NAMETAG, "");
		meta.setValue(EntityDataType.FLAGS, (long) 0);
		meta.setValue(EntityDataType.LEAD_HOLDER_ID, (long) -1);
		meta.setValue(EntityDataType.BOUNDING_BOX_WIDTH, 1F);
		meta.setValue(EntityDataType.BOUNDING_BOX_HEIGHT, 1F);
		
		if(type == EntityType.PLAYER){
			meta.setValue(EntityDataType.FLAGS, 1L << (EntityDataType.FLAG_AFFECTED_BY_GRAVITY | EntityDataType.FLAG_IMMOBILE | EntityDataType.FLAG_CAN_CLIMB));
			meta.setValue(EntityDataType.PLAYER_FLAGS, (byte) 0);
		}
		
		return meta;
	}
}
