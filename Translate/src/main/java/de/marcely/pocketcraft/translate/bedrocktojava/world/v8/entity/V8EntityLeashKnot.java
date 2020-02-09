package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityLeashKnot extends V8Entity implements V8EntityObject {

	public V8EntityLeashKnot(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 77;
	}

	@Override
	public EntityType getType(){
		return EntityType.LEASH_KNOT;
	}
}
