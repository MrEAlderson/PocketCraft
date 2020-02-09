package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityWolf extends V8EntityTameable {

	public V8EntityWolf(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 95;
	}

	@Override
	public EntityType getType(){
		return EntityType.WOLF;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 16:
		{
			final byte map = meta.readByte(16);
			
			this.setDataFlag(EntityDataType.FLAG_ANGRY, (map & 0x02) > 0); // ok
			
			super.read(meta, key);
		}
		break;
		
		case 18:
			this.metadata.setInt(EntityDataType.HEALTH, (int) meta.readFloat(key));
			break;
			
		case 19:
			this.setDataFlag(EntityDataType.FLAG_INTERESTED, meta.readBoolean(key));
			break;
			
		case 20:
			this.metadata.setByte(EntityDataType.COLOR, (byte) (15 - meta.readByte(key))); // it's inverted for some reason
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
