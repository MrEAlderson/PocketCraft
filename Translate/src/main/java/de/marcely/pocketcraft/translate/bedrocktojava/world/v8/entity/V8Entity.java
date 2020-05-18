package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollision;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollisionEvent;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent.*;

import org.jetbrains.annotations.Nullable;

public abstract class V8Entity extends Entity {
	
	public V8Entity(World world, int id){
		super(world, id);
	}
	
	public abstract int getTypeId();
	
	public boolean hasCustomSpawning(){ return false; }
	
	protected void onReadMeta(){ }
	
	@Override
	public @Nullable BlockCollisionEvent getCollidingBlock(){
		final EntityType type = this.getType();
		
		if(this.y + type.getHeight() < 0 || this.y > 255)
			return null;
		
		final int minX = (int) (this.x - type.getWidth() / 2F);
		final int minY = Math.max((int) (this.y), 0);
		final int minZ = (int) (this.z - type.getWidth() / 2F);
		final int maxX = (int) Math.ceil(this.x + type.getWidth() / 2F);
		final int maxY = (int) Math.ceil(Math.min(this.y + type.getHeight(), 255));
		final int maxZ = (int) Math.ceil(this.z + type.getWidth() / 2F);
		
		for(int ix=minX; ix<=maxX; ix++){
			for(int iy=minY; iy<=maxY; iy++){
				for(int iz=minZ; iz<=maxZ; iz++){
					final BlockState state = this.getWorld().getBlockState(ix, iy, iz);
					
					if(state == null || state.getCollision() == null)
						continue;
					
					final BlockCollision.Cube intersecting = state.getCollision().getCollidingWith(ix, iy, iz, this);
					
					if(intersecting == null)
						continue;
					
					return new BlockCollisionEvent(ix, iy, iz, state, intersecting);
				}
			}
		}
		
		return null;
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
	
	public void playEvent(byte type){
		switch(type){
		case TYPE_WITCH_MAGIC:
			this.playEvent(EntityEvent.WITCH_SPELL);
			break;
		}
	}
}