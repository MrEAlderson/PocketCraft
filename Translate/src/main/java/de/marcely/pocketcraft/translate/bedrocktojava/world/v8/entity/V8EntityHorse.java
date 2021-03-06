package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityHorse extends V8EntityAgeable {
	
	private byte type = 0;
	
	public V8EntityHorse(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 100;
	}

	@Override
	public EntityType getType(){
		switch(this.type){
		case 0:
		default:
			return EntityType.HORSE;
			
		case 1:
			return EntityType.DONKEY;
			
		case 2:
			return EntityType.MULE;
			
		case 3:
			return EntityType.ZOMBIE_HORSE;
			
		case 4:
			return EntityType.SKELETON_HORSE;
		}
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 16:
		{
			final int map = meta.readInt(key);
			
			this.setDataFlag(EntityDataType.FLAG_TAMED, (map & 0x02) > 0);
			this.setDataFlag(EntityDataType.FLAG_SADDLED, (map & 0x04) > 0);
			this.setDataFlag(EntityDataType.FLAG_CHESTED, (map & 0x08) > 0);
			this.setDataFlag(EntityDataType.FLAG_TAMED, (map & 0x10) > 0);
			this.setDataFlag(EntityDataType.FLAG_EATING, (map & 0x20) > 0);
			this.setDataFlag(EntityDataType.FLAG_REARING, (map & 0x40) > 0);
			// this.setDataFlag(EntityDataType.FLAG_BREATHING, (map & 0x80) > 0); // mouth open. useless since they can't open their mouth
		}
		break;
		
		case 19:
			this.type = meta.readByte(key);
			break;
		
		case 20:
		{
			final int map = meta.readInt(key);
			
			this.getMetadata().setInt(EntityDataType.VARIANT, map & 0x00FF); // color
			this.getMetadata().setInt(EntityDataType.MARK_VARIANT, (map & 0xFF00) >> 8); // blemishes
		}
		break;
		
		case 21: // owner name
			break;
		}
	}
}
