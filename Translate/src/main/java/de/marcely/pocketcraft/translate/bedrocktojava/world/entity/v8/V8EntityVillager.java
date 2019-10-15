package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public class V8EntityVillager extends V8EntityAgeable {

	public V8EntityVillager(int id){
		super(id);
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
