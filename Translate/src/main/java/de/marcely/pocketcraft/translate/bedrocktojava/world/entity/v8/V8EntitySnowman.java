package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySnowman extends V8EntityLiving {

	public V8EntitySnowman(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 97;
	}

	@Override
	public EntityType getType(){
		return EntityType.SNOW_GOLEM;
	}
}
