package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityExpBottle extends V8EntityProjectile {

	public V8EntityExpBottle(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 75;
	}
	
	public float getGravity(){
		return 0.07F;
	}

	@Override
	public EntityType getType(){
		return EntityType.XP_BOTTLE;
	}
}
