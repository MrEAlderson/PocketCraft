package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityArrow extends V8EntityProjectile implements V8EntityObject {

	public V8EntityArrow(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 60;
	}

	@Override
	public EntityType getType(){
		return EntityType.ARROW;
	}
	
	public float getGravity(){
		return 0.05F;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.setDataFlag(EntityDataType.FLAG_CRITICAL, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
