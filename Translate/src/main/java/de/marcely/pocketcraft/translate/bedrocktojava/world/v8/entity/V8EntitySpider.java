package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntitySpider extends V8EntityLiving {

	public V8EntitySpider(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 52;
	}

	@Override
	public EntityType getType(){
		return EntityType.SPIDER;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeBoolean(16, this.getDataFlag(EntityDataType.FLAG_WALLCLIMBING));
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.setDataFlag(EntityDataType.FLAG_WALLCLIMBING, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
