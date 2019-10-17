package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityIronGolem extends V8EntityInsentient {

	public V8EntityIronGolem(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 99;
	}

	@Override
	public EntityType getType(){
		return EntityType.IRON_GOLEM;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		// 16 = is player created
		super.read(meta, key);
	}
}
