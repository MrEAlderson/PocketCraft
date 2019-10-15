package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public class V8EntitySnowman extends V8EntityLiving {

	public V8EntitySnowman(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 97;
	}

	@Override
	public EntityType getType(){
		return EntityType.SNOW_GOLEM;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		
	}
}
