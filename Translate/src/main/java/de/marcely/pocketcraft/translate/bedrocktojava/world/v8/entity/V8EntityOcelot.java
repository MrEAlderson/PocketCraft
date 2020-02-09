package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityOcelot extends V8EntityTameable {

	public V8EntityOcelot(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 98;
	}

	@Override
	public EntityType getType(){
		return EntityType.OCELOT;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeByte(18, this.metadata.getInt(EntityDataType.VARIANT));
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 18)
			this.metadata.setInt(EntityDataType.VARIANT, meta.readByte(key));
		else
			super.read(meta, key);
	}
}
