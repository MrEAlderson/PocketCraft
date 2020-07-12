package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityZombie extends V8EntityInsentient {
	
	private boolean isVillager = false;
	
	public V8EntityZombie(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 54;
	}

	@Override
	public EntityType getType(){
		return this.isVillager ? EntityType.ZOMBIE_VILLAGER : EntityType.ZOMBIE;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 12:
			this.setDataFlag(EntityDataType.FLAG_BABY, meta.readBoolean(key));
			break;
		
		case 13:
			this.isVillager = meta.readBoolean(key);
			break;
			
		case 14:
			this.setDataFlag(EntityDataType.FLAG_CONVERTING, meta.readBoolean(key));
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
