package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;

public class V8EntityChicken extends V8EntityLiving {

	public V8EntityChicken(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 93;
	}

	@Override
	public EntityType getType(){
		return EntityType.CHICKEN;
	}
}
