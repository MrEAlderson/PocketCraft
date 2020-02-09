package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySlime extends V8EntityInsentient {

	public V8EntitySlime(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 55;
	}

	@Override
	public EntityType getType(){
		return EntityType.SLIME;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.metadata.setFloat(EntityDataType.SCALE, meta.readByte(key));
		else
			super.read(meta, key);
	}
}
