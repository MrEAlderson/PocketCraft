package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityTameable extends V8EntityAgeable {

	public V8EntityTameable(World world, int id){
		super(world, id);
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		{
			byte map = 0;
			
			if(this.getDataFlag(EntityDataType.FLAG_SITTING))
				map |= 0x01;
			
			if(this.getDataFlag(EntityDataType.FLAG_TAMED))
				map |= 0x04;
			
			meta.writeByte(16, map);
		}
		
		// TODO: Same problem as written down
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 16:
		{
			final byte map = meta.readByte(key);
			
			this.setDataFlag(EntityDataType.FLAG_SITTING, (map & 0x01) > 0);
			this.setDataFlag(EntityDataType.FLAG_TAMED, (map & 0x04) > 0);
		}
		break;
		
		case 17:
			this.metadata.setLong(EntityDataType.OWNER_ID, 0 /* TODO: we get owner name, but bedrock requires owner id */);
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
