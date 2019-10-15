package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

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
			
			this.setDataFlag(EntityDataType.FLAG_ANGRY, (map & 0x02) > 0);
			
			super.read(meta, key); // that should be here
		}
		break;
		
		case 18:
			this.metadata.setInt(EntityDataType.HEALTH, (int) meta.readFloat(key));
			break;
			
		case 19: // begging: TODO check if it's correct
			this.setDataFlag(EntityDataType.FLAG_INTERESTED, meta.readBoolean(key));
			break;
			
		case 20:
			this.metadata.setInt(EntityDataType.COLOR2, meta.readByte(key));
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
