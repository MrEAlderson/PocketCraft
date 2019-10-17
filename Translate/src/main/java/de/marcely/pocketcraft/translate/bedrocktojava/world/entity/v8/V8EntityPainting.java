package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityPainting extends V8Entity {

	public V8EntityPainting(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return MAGIC_TYPE_PAINTING;
	}

	@Override
	public EntityType getType(){
		return EntityType.PAINTING;
	}
}
