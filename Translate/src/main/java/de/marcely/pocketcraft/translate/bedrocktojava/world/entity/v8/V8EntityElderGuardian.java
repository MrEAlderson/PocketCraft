package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityElderGuardian extends V8EntityGuardian {

	public V8EntityElderGuardian(World world, int id){
		super(world, id);
	}
	
	@Override
	public int getTypeId(){
		return -1;
	}

	@Override
	public EntityType getType(){
		return EntityType.ELDER_GUARDIAN;
	}
}
