package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityZombieVillager extends V8EntityZombie {

	public V8EntityZombieVillager(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 1001 /* magic value*/;
	}

	@Override
	public EntityType getType(){
		return EntityType.ZOMBIE_VILLAGER;
	}
}
