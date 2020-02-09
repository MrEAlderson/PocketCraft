package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEndermite extends V8EntityInsentient {

	public V8EntityEndermite(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 67;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDERMITE;
	}
}
