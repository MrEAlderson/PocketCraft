package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityMule extends V8EntityHorse {

	public V8EntityMule(World world, int id){
		super(world, id);
	}
	
	@Override
	public int getTypeId(){
		return MAGIC_TYPE_MULE;
	}

	@Override
	public EntityType getType(){
		return EntityType.MULE;
	}
}
