package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public class V8EntityRabbit extends V8EntityAgeable {

	public V8EntityRabbit(int id){
		super(id);
	}

	@Override
	public int getTypeId(){
		return 101;
	}

	@Override
	public EntityType getType(){
		return EntityType.RABBIT;
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
