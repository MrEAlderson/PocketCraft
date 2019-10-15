package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityZombie extends V8EntityInsentient {

	public V8EntityZombie(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 54;
	}

	@Override
	public EntityType getType(){
		return EntityType.ZOMBIE;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 12:
			this.setDataFlag(EntityDataType.FLAG_BABY, meta.readBoolean(key));
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
