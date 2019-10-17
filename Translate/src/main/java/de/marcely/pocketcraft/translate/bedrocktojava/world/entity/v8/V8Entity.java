package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent.*;

public abstract class V8Entity extends Entity {
	
	public static final int MAGIC_TYPE_WITHER_SKELETON = 1000;
	public static final int MAGIC_TYPE_ZOMBIE_VILLAGER = 1001;
	public static final int MAGIC_TYPE_DONKEY = 1002;
	public static final int MAGIC_TYPE_MULE = 1003;
	public static final int MAGIC_TYPE_ZOMBIE_HORSE = 1004;
	public static final int MAGIC_TYPE_SKELETON_HORSE = 1005;
	public static final int MAGIC_TYPE_HUMAN = 1006;
	public static final int MAGIC_TYPE_PAINTING = 1007;
	
	
	public V8Entity(World world, int id){
		super(world, id);
	}
	
	public abstract int getTypeId();
	
	public void playEvent(byte type){
		switch(type){
		case TYPE_WITCH_MAGIC:
			this.playEvent(EntityEvent.WITCH_SPELL);
			break;
		}
	}
	
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
		
		{
			final short air = this.metadata.getShort(EntityDataType.AIR);
			
			meta.writeShort(1, air);
			this.setDataFlag(EntityDataType.FLAG_BREATHING, air >= 400);
		}
		
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