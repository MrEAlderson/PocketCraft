package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityGuardian extends V8EntityInsentient {

	public V8EntityGuardian(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 68;
	}

	@Override
	public EntityType getType(){
		return EntityType.GUARDIAN;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16){
			final byte map = meta.readByte(key);
			
			// 0x02 = is elderly, is not getting handled here since it's a different entity type
			this.setDataFlag(EntityDataType.FLAG_ACTION, (map & 0x04) > 0);
		
		}else if(key == 17)
			this.getMetadata().setLong(EntityDataType.TARGET_ID, meta.readInt(key));
		else
			super.read(meta, key);
	}
}
