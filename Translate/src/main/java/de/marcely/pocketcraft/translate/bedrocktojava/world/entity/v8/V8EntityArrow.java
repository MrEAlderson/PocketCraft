package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityArrow extends V8Entity {

	public V8EntityArrow(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 10;
	}

	@Override
	public EntityType getType(){
		return EntityType.ARROW;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		// 16 = is critical
		super.read(meta, key);
	}
}
