package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityVillager extends V8EntityAgeable {

	public V8EntityVillager(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 120;
	}

	@Override
	public EntityType getType(){
		return EntityType.VILLAGER;
	}

	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeInt(16, this.metadata.getInt(EntityDataType.VARIANT));
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.metadata.setInt(EntityDataType.VARIANT, meta.readInt(key));
		else
			super.read(meta, key);
	}
}
