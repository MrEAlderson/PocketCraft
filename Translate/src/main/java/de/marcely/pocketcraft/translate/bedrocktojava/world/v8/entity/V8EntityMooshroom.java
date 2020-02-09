package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityMooshroom extends V8EntityCow {

	public V8EntityMooshroom(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 96;
	}

	@Override
	public EntityType getType(){
		return EntityType.MOOSHROOM;
	}
}
