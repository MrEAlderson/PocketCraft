package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMeta;

public abstract class V8EntityAgeable extends V8EntityLiving {

	public V8EntityAgeable(int id){
		super(id);
	}
	
	@Override
	public void write(V8EntityMeta meta){
		super.write(meta);
		
		meta.writeByte(12, this.metadata.getShort(EntityDataType.ENTITY_AGE));
	}
	
	@Override
	public void read(V8EntityMeta meta){
		super.read(meta);
		
		this.metadata.setShort(EntityDataType.ENTITY_AGE, meta.readShort(12));
	}
}
