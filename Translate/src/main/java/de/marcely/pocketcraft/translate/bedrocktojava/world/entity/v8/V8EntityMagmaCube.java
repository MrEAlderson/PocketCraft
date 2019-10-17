package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityMagmaCube extends V8EntitySlime {

	public V8EntityMagmaCube(World world, int id){
		super(world, id);
	}
	
	@Override
	public int getTypeId(){
		return 62;
	}

	@Override
	public EntityType getType(){
		return EntityType.MAGMA_CUBE;
	}
}
