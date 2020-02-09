package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityArmorStand extends V8EntityLiving implements V8EntityObject {

	public V8EntityArmorStand(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 78;
	}

	@Override
	public EntityType getType(){
		return EntityType.ARMOR_STAND;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		{
			byte map = 0;
			
			if(this.getDataFlag(EntityDataType.FLAG_BABY))
				map |= 0x01;
			
			if(this.getDataFlag(EntityDataType.FLAG_AFFECTED_BY_GRAVITY))
				map |= 0x02;
			
			if(this.getDataFlag(EntityDataType.FLAG_SHOWBASE))
				map |= 0x08;
			
			if(this.metadata.getFloat(EntityDataType.BOUNDING_BOX_WIDTH) == 0)
				map |= 0x16;
			
			meta.writeByte(10, map);
		}
		
		// TODO
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 10:
		{
			final byte map = meta.readByte(key);
			
			//this.setDataFlag(EntityDataType.FLAG_BABY, (map & 0x01) > 0);
			//this.setDataFlag(EntityDataType.FLAG_AFFECTED_BY_GRAVITY, (map & 0x02) > 0);
			// 0x04 = has arms
			//this.setDataFlag(EntityDataType.FLAG_SHOWBASE, (map & 0x08) == 0);
			
			if((map & 0x16) > 0){ // marker
				this.metadata.setFloat(EntityDataType.BOUNDING_BOX_WIDTH, 0F);
				this.metadata.setFloat(EntityDataType.BOUNDING_BOX_HEIGHT, 0F);
			}else{
				this.metadata.setFloat(EntityDataType.BOUNDING_BOX_WIDTH, getType().getWidth());
				this.metadata.setFloat(EntityDataType.BOUNDING_BOX_HEIGHT, getType().getHeight());
			}
		}
		break;
		
		case 11: // head position
		case 12: // body position
		case 13: // left arm position
		case 14: // right arm position
		case 15: // left leg position
		case 16: // right leg position
			break; // bedrock edition does not support this. :(
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
