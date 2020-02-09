package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent.*;

public class V8EntitySheep extends V8EntityAnimal {

	public V8EntitySheep(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 91;
	}

	@Override
	public EntityType getType(){
		return EntityType.SHEEP;
	}
	
	@Override
	public void playEvent(byte type){
		if(type == TYPE_SHEEP_EAT_GRASS)
			playEvent(EntityEvent.EAT_GRASS);
		else
			super.playEvent(type);
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		{
			byte map = 0;
			
			map |= (this.metadata.getByte(EntityDataType.COLOR))&0x0F;
			map |= (this.getDataFlag(EntityDataType.FLAG_SHEARED) ? 0x10 : 0);
			
			meta.writeByte(16, map);
		}
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16){
			final byte map = meta.readByte(key);
			
			this.metadata.setByte(EntityDataType.COLOR, (byte) (map & 0x0F));
			this.setDataFlag(EntityDataType.FLAG_SHEARED, (map & 0x10) > 0);
		}else
			super.read(meta, key);
	}
}
