package de.marcely.pocketcraft.translate.bedrocktojava;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.BTextFormat;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataValueType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEvent;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class EntityDebug {
	
	public static final byte MODE_EVENT = 0;
	public static final byte MODE_FLAG_1 = 1;
	public static final byte MODE_FLAG_2 = 2;
	public static final byte MODE_FLAG_PLAYER = 3;
	public static final byte MODE_ANIMATE = 4;
	public static final byte MODE_ARMOR = 5;
	public static final byte MODE_META = 6;
	
	public static EntityDebug INSTANCE = null;
	
	private final Player player;
	private final byte mode;
	private final EntityType type;
	private final float data;
	
	private int oldSlot = 0, oldIndex = 0;
	private int index = 0;
	
	public EntityDebug(Player player, byte mode, EntityType type, float data){
		this.player = player;
		this.mode = mode;
		this.type = type;
		this.data = data;
	}
	
	public void onChangeSlot(int newSlot){
		if(newSlot == this.oldSlot)
			return;
		else if(newSlot == 8 && oldSlot == 0)
			this.index--;
		else if((newSlot > this.oldSlot || (newSlot == 0 && this.oldSlot == 8)))
			this.index++;
		else
			this.index--;
		
		this.oldSlot = newSlot;
		
		for(Entity entity:player.getWorld().getEntities()){
			if(entity.getType() == type)
				apply(entity);
		}
		
		this.oldIndex = this.index;
		
		this.player.sendChatMessage(BTextFormat.GRAY + "Index: " + BTextFormat.RESET + index);
	}
	
	private void apply(Entity entity){
		switch(this.mode){
		case MODE_EVENT:
		{
			if(this.index == 3) // skip death
				return;
			
			final PacketEntityEvent out = new PacketEntityEvent();
			
			out.entityId = entity.getLongId();
			out.type = (byte) this.index;
			out.data = (int) this.data;
			
			this.player.sendPacket(out);
		}
		break;
		
		case MODE_FLAG_1:
		{
			entity.setDataFlag(this.oldIndex, false);
			entity.setDataFlag(this.index, true);
			
			entity.sendAllMetadata(this.player.getBedrock());
		}
		break;
		
		case MODE_FLAG_2:
		{
			entity.setDataFlag2(this.oldIndex, false);
			entity.setDataFlag2(this.index, true);
			
			entity.sendAllMetadata(this.player.getBedrock());
		}
		break;
		
		case MODE_FLAG_PLAYER:
		{
			entity.setDataPlayerFlag(this.oldIndex, false);
			entity.setDataPlayerFlag(this.index, true);
			
			entity.sendAllMetadata(this.player.getBedrock());
		}
		break;
		
		case MODE_ANIMATE:
		{
			final PacketEntityAnimate out = new PacketEntityAnimate();
			
			out.entityId = entity.getLongId();
			out.type = this.index;
			out.rowingTime = this.data;
			
			player.sendPacket(out);
		}
		break;
		
		case MODE_ARMOR:
		{
			
		}
		break;
		
		case MODE_META:
		{
			EntityDataType data = getData(this.index);
			
			while(data == null){
				if(this.index >= 150)
					this.index = 0;
				
				this.index++;
				data = EntityDataType.getById(this.index);
			}
			
			switch(data.getValueType()){
			case BYTE:
				entity.getMetadata().setByte(data, (byte) this.data);
				break;
				
			case SHORT:
				entity.getMetadata().setShort(data, (short) this.data);
				break;
				
			case INT:
				entity.getMetadata().setInt(data, (int) this.data);
				break;
				
			case LONG:
				entity.getMetadata().setLong(data, (long) this.data);
				break;
				
			case FLOAT:
				entity.getMetadata().setFloat(data, this.data);
				break;
				
			default:
				break;
			}
			
			entity.sendAllMetadata(this.player.getBedrock());
			
			this.player.sendChatMessage(BTextFormat.GRAY + " (Data: " + data.name() + ")");
		}
		break;
		}
	}
	
	private static @Nullable EntityDataType getData(int id){
		final EntityDataType data = EntityDataType.getById(id);
		
		if(data != null && (data.getValueType() == EntityDataValueType.BYTE ||
				data.getValueType() == EntityDataValueType.SHORT || data.getValueType() == EntityDataValueType.INT ||
				data.getValueType() == EntityDataValueType.LONG || data.getValueType() == EntityDataValueType.FLOAT))
			return data;
		else return null;
	}
}