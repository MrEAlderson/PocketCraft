package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityGhast extends V8EntityInsentient {

	byte i = 0;
	
	public V8EntityGhast(World world, int id){
		super(world, id);
		
		if(world == null)
			return;
	}

	@Override
	public int getTypeId(){
		return 56;
	}

	@Override
	public EntityType getType(){
		return EntityType.GHAST;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.getMetadata().setBoolean(EntityDataType.CHARGED, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
