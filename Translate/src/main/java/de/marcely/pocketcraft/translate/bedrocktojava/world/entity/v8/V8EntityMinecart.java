package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityMinecart extends V8Entity implements V8EntityObject {
	
	private byte type;
	
	public V8EntityMinecart(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 10;
	}

	@Override
	public EntityType getType(){
		switch(type){
		case 0:
		default:
			return EntityType.MINECART;
			
		case 1:
			return EntityType.CHEST_MINECART;
			
		case 2:
			return EntityType.FURNACE_MINECART;
			
		case 3:
			return EntityType.TNT_MINECART;
			
		case 4:
			return EntityType.SPAWNER_MINECART;
			
		case 5:
			return EntityType.HOPPER_MINECART;
			
		case 6:
			return EntityType.COMMAND_BLOCK_MINECART;
		}
	}
	
	@Override
	public void readData(int data){
		this.type = (byte) data;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 17:
			this.getMetadata().setInt(EntityDataType.SHAKING_POWER, meta.readInt(key));
			break;
			
		case 18:
			this.getMetadata().setInt(EntityDataType.SHAKING_DIRECTION, meta.readInt(key));
			break;
			
		case 19: // Shaking Multiplier
			break;
			
		case 20:
		{
			// int map: 0x00FF = id, 0xFF00 = data
		}
		break;
		
		case 21: // int: block y position
			break;
			
		case 22: // boolean show block
			break;
		}
	}
}
