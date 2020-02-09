package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySkeleton extends V8EntityInsentient {
	
	private boolean isWitherSkeleton = false;
	
	public V8EntitySkeleton(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 51;
	}

	@Override
	public EntityType getType(){
		return this.isWitherSkeleton ? EntityType.WITHER_SKELETON : EntityType.SKELETON;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 13)
			this.isWitherSkeleton = meta.readBoolean(key);
		else
			super.read(meta, key);
	}
}
