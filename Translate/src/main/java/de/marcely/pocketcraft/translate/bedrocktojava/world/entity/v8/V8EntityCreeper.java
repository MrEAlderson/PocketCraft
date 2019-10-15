package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityCreeper extends V8EntityLiving {

	public V8EntityCreeper(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 50;
	}

	@Override
	public EntityType getType(){
		return EntityType.CREEPER;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 16:
			this.setDataFlag(EntityDataType.FLAG_CHARGED, meta.readBoolean(key));
			break;
			
		case 17:
			this.setDataFlag(EntityDataType.FLAG_POWERED, meta.readBoolean(key));
			break;
			
		case 18:
			this.setDataFlag(EntityDataType.FLAG_IGNITED, meta.readBoolean(key));
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
