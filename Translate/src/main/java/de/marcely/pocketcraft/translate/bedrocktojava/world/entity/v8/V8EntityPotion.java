package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityPotion extends V8EntityProjectile implements V8EntityObject {

	public V8EntityPotion(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 73;
	}

	@Override
	public EntityType getType(){
		return EntityType.SPLASH_POTION;
	}
	
	public float getGravity(){
		return 0.05F;
	}
	
	// TODO
}
