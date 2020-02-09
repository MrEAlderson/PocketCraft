package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEnderDragon extends V8EntityInsentient {

	public V8EntityEnderDragon(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 63;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDER_DRAGON;
	}
}
