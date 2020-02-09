package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySilverfish extends V8EntityInsentient {

	public V8EntitySilverfish(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 60;
	}

	@Override
	public EntityType getType(){
		return EntityType.SILVERFISH;
	}
}
