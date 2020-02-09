package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityWither extends V8EntityInsentient {

	public V8EntityWither(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 64;
	}

	@Override
	public EntityType getType(){
		return EntityType.WITHER;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 17:
			this.metadata.setLong(EntityDataType.WITHER_TARGET_HEAD_LEFT, meta.readInt(key));
			break;
			
		case 18:
			this.metadata.setLong(EntityDataType.WITHER_TARGET_HEAD_CENTER, meta.readInt(key));
			break;
			
		case 19:
			this.metadata.setLong(EntityDataType.WITHER_TARGET_HEAD_RIGHT, meta.readInt(key));
			break;
			
		case 20:
			this.metadata.setInt(EntityDataType.WITHER_INVULNERABLE_TICKS, meta.readInt(key));
			break;
		}
	}
}
