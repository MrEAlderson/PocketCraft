package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEnderPearl extends V8EntityProjectile implements V8EntityObject {

	public V8EntityEnderPearl(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 65;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDER_PEARL;
	}
}
