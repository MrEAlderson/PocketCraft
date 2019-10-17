package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityLeashKnot extends V8Entity {

	public V8EntityLeashKnot(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 8;
	}

	@Override
	public EntityType getType(){
		return EntityType.LEASH_KNOT;
	}
}
