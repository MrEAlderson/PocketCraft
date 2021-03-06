package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityPainting extends V8Entity {

	public V8EntityPainting(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return -1;
	}

	@Override
	public EntityType getType(){
		return EntityType.PAINTING;
	}
}
