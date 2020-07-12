package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityAgeable extends V8EntityLiving {

	public V8EntityAgeable(World world, int id){
		super(world, id);
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 12){
			final byte age = meta.readByte(key);
			
			this.metadata.setShort(EntityDataType.ENTITY_AGE, age);
			
			if(age < 0){
				this.setDataFlag(EntityDataType.FLAG_BABY, true);
				this.metadata.setFloat(EntityDataType.SCALE, 0.5F);
			}else{
				this.setDataFlag(EntityDataType.FLAG_BABY, false);
				this.metadata.setFloat(EntityDataType.SCALE, 1F);
			}
		}else
			super.read(meta, key);
	}
}
