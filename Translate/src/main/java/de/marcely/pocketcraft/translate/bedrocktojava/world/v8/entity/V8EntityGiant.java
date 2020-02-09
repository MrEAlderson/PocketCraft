package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityGiant extends V8EntityInsentient {

	public V8EntityGiant(World world, int id){
		super(world, id);
		
		this.metadata.setFloat(EntityDataType.SCALE, 6F);
	}

	@Override
	public int getTypeId(){
		return 53;
	}

	@Override
	public EntityType getType(){
		return EntityType.ZOMBIE;
	}
}
