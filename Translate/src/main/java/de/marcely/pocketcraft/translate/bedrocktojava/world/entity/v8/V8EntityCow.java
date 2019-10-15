package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;

public class V8EntityCow extends V8EntityLiving {

	public V8EntityCow(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 92;
	}

	@Override
	public EntityType getType(){
		return EntityType.COW;
	}
}
