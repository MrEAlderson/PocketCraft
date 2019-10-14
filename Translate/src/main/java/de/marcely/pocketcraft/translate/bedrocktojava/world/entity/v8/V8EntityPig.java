package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public class V8EntityPig extends V8EntityAgeable {

	public V8EntityPig(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 90;
	}

	@Override
	public EntityType getType(){
		return EntityType.PIG;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeBoolean(16, this.getDataFlag(EntityDataType.FLAG_SADDLED));
	}
	
	@Override
	public void read(V8EntityMetadata meta){
		super.read(meta);
		
		this.setDataFlag(EntityDataType.FLAG_SADDLED, meta.readBoolean(16));
	}
}