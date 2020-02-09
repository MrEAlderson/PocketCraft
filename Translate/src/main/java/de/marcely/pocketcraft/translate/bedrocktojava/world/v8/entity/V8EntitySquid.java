package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySquid extends V8EntityLiving {

	public V8EntitySquid(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 94;
	}

	@Override
	public EntityType getType(){
		return EntityType.SQUID;
	}
}
