package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityDonkey extends V8EntityHorse {

	public V8EntityDonkey(World world, int id){
		super(world, id);
	}
	
	@Override
	public int getTypeId(){
		return MAGIC_TYPE_DONKEY;
	}

	@Override
	public EntityType getType(){
		return EntityType.DONKEY;
	}
}
