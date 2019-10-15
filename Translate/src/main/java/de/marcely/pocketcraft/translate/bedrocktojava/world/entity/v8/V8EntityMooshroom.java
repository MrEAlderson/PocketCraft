package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;

public class V8EntityMooshroom extends V8EntityCow {

	public V8EntityMooshroom(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 92;
	}

	@Override
	public EntityType getType(){
		return EntityType.MOOSHROOM;
	}
}
