package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;

public class V8EntityCaveSpider extends V8EntitySpider {

	public V8EntityCaveSpider(int id){
		super(id);
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
