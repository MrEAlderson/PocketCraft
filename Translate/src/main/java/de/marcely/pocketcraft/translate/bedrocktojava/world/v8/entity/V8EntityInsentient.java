package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityInsentient extends V8EntityLiving {

	public V8EntityInsentient(World world, int id){
		super(world, id);
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 15)
			this.setDataFlag(EntityDataType.FLAG_NO_AI, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
