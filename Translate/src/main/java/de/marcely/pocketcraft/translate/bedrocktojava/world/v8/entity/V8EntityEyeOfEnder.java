package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEyeOfEnder extends V8Entity {

	public V8EntityEyeOfEnder(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 15;
	}

	@Override
	public EntityType getType(){
		return EntityType.EYE_OF_ENDER_SIGNAL;
	}
}
