package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySkeleton extends V8EntityInsentient {

	public V8EntitySkeleton(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 51;
	}

	@Override
	public EntityType getType(){
		return EntityType.SKELETON;
	}
}
