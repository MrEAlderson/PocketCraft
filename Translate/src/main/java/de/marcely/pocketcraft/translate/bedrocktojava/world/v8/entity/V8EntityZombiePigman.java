package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityZombiePigman extends V8EntityZombie {

	public V8EntityZombiePigman(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 57;
	}

	@Override
	public EntityType getType(){
		return EntityType.ZOMBIE_PIGMAN;
	}
}
