package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public abstract class V8EntityLiving extends V8Entity {

	public V8EntityLiving(int id){
		super(id);
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeString(2, this.metadata.getString(EntityDataType.NAMETAG));
		meta.writeBoolean(3, this.getDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG));
		meta.writeFloat(6, this.metadata.getInt(EntityDataType.HEALTH));
		meta.writeInt(7, this.metadata.getInt(EntityDataType.POTION_COLOR));
		meta.writeBoolean(8, this.metadata.getBoolean(EntityDataType.POTION_AMBIENT));
		meta.writeByte(9, 0); // number of arrows in entity
		meta.writeBoolean(15, this.getDataFlag(EntityDataType.FLAG_NO_AI));
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 2:
			this.metadata.setString(EntityDataType.NAMETAG, meta.readString(key));
			this.setDataFlag(EntityDataType.FLAG_CAN_SHOW_NAMETAG, true);
			break;
			
		case 3:
			this.setDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG, meta.readBoolean(key));
			break;
			
		case 6:
			this.metadata.setInt(EntityDataType.HEALTH, (int) meta.readFloat(key));
			break;
			
		case 7:
			this.metadata.setInt(EntityDataType.POTION_COLOR, meta.readInt(key));
			break;
			
		case 8:
			this.metadata.setBoolean(EntityDataType.POTION_AMBIENT, meta.readBoolean(key));
			break;
			
		case 9:
			// 9 = number of arrows in entity
			break;
			
		case 15:
			this.setDataFlag(EntityDataType.FLAG_NO_AI, meta.readBoolean(key));
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
