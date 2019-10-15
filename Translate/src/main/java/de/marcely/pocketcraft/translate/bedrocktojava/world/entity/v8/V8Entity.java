package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;

public abstract class V8Entity extends Entity {

	public V8Entity(int id){
		super(id);
	}
	
	public abstract int getTypeId();
	
	public void write(V8EntityMetadata meta){
		{
			byte map = 0;
			
			if(this.getDataFlag(EntityDataType.FLAG_ONFIRE))
				map |= 0x01;
			
			if(this.getDataFlag(EntityDataType.FLAG_SNEAKING))
				map |= 0x02;
			
			if(this.getDataFlag(EntityDataType.FLAG_SPRINTING))
				map |= 0x08;
			
			if(this.getDataFlag(EntityDataType.FLAG_EATING))
				map |= 0x10;
			
			if(this.getDataFlag(EntityDataType.FLAG_INVISIBLE))
				map |= 0x20;
			
			meta.writeByte(0, map);
		}
		
		meta.writeShort(1, this.metadata.getShort(EntityDataType.AIR));
		meta.writeBoolean(4, this.getDataFlag(EntityDataType.FLAG_SILENT));
	}
	
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 0:
		{
			final byte map = meta.readByte(key);
			
			this.setDataFlag(EntityDataType.FLAG_ONFIRE, (map & 0x01) > 0);
			this.setDataFlag(EntityDataType.FLAG_SNEAKING, (map & 0x02) > 0);
			this.setDataFlag(EntityDataType.FLAG_SPRINTING, (map & 0x08) > 0);
			this.setDataFlag(EntityDataType.FLAG_EATING, (map & 0x10) > 0);
			this.setDataFlag(EntityDataType.FLAG_INVISIBLE, (map & 0x20) > 0);
		}
		break;
		
		case 1:
			this.metadata.setShort(EntityDataType.AIR, meta.readShort(1));
			break;
			
		case 4:
			this.setDataFlag(EntityDataType.FLAG_SILENT, meta.readBoolean(4));
			break;
		}
	}
	
	public void readAll(V8EntityMetadata meta){
		for(int key:meta.getEntries().keySet())
			read(meta, key);
	}
}