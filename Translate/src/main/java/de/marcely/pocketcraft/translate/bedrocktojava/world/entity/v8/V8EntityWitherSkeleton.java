package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityWitherSkeleton extends V8EntitySkeleton {

	public V8EntityWitherSkeleton(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return MAGIC_TYPE_WITHER_SKELETON;
	}

	@Override
	public EntityType getType(){
		return EntityType.WITHER_SKELETON;
	}
}
