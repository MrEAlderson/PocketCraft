package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityCaveSpider extends V8EntitySpider {

	public V8EntityCaveSpider(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 59;
	}

	@Override
	public EntityType getType(){
		return EntityType.CAVE_SPIDER;
	}
}
