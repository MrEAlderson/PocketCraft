package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;

public class V8EntitySquid extends V8EntityLiving {

	public V8EntitySquid(int id){
		super(id);
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
