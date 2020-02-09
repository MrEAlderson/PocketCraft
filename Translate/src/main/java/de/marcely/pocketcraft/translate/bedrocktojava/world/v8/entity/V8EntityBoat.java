package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityBoat extends V8Entity implements V8EntityObject {

	public V8EntityBoat(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 1;
	}

	@Override
	public EntityType getType(){
		return EntityType.BOAT;
	}
	
	@Override
	public float getBedrockPacketYAppend(){
		return 0.375F;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		// 17 = int = time since hit
		// 18 = int = forward direction
		// 19 = float = damage taken
		super.read(meta, key);
	}
}
