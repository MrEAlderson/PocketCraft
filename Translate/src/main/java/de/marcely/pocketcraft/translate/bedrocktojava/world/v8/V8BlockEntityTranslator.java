package de.marcely.pocketcraft.translate.bedrocktojava.world.v8;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.*;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class V8BlockEntityTranslator {
	
	public static void handleSpawn(@Nullable Player player, Chunk chunk, int x, int y, int z, short id, byte data, Short oldId){
		final BlockEntityType type = getType(id);
		
		if(type == null){
			if(oldId != null && getType(oldId) != null)
				chunk.removeBlockEntity(x, y, z);
			
			return;
		}
		
		final BlockEntity entity = type.newInstance();
		
		if(entity == null){
			System.out.println("Failed to create instance of block entity: " + type);
			return;
		}
		
		applyData(entity, data);
		
		chunk.addBlockEntity(x, y, z, entity);
	}
	
	private static void applyData(BlockEntity rawEntity, byte data){
		switch(rawEntity.getType()){
		case BED:
		{
			final BlockEntityBed entity = (BlockEntityBed) rawEntity;
			
			entity.setColor((byte) 14);
		} 
		break;
		
		case SKULL:
		{
			final BlockEntitySkull entity = (BlockEntitySkull) rawEntity;
			
			entity.setSkullType(data);
		}
		break;
		
		default: break;
		}
	}
	
	private static @Nullable BlockEntityType getType(short id){
		switch(id){
		case 54: // normal chest
		case 146: // trapped chest
			return BlockEntityType.CHEST;
				
		case 130:
			return BlockEntityType.ENDER_CHEST;
		
		case 61: // not burning
		case 62: // burning
			return BlockEntityType.FURNACE;
			
		case 63: // standing
		case 68: // wall
			return BlockEntityType.SIGN;
			
		case 116:
			return BlockEntityType.ENCHANT_TABLE;
			
		case 397:
			return BlockEntityType.SKULL;
			
		case 140:
			return BlockEntityType.FLOWER_POT;
			
		case 117:
			return BlockEntityType.BREWING_STAND;
			
		case 118:
			return BlockEntityType.CAULDRON;
			
		case 138:
			return BlockEntityType.BEACON;
			
		case 34: // piston head
			return BlockEntityType.MOVING_BLOCK;
			
		case 154:
			return BlockEntityType.HOPPER;
			
		case 26:
			return BlockEntityType.BED;
			
		case 84:
			return BlockEntityType.JUKEBOX;
			
		case 219: // white
		case 220: // orange
		case 221: // magenta
		case 222: // light blue
		case 223: // yellow
		case 224: // lime
		case 225: // pink
		case 226: // gray
		case 227: // light gray
		case 228: // cyan
		case 229: // purple
		case 230: // blue
		case 231: // brown
		case 232: // green
		case 233: // red
		case 234: // black
			return BlockEntityType.SHULKER_BOX;
			
		case 176: // standing
		case 177: // wall
			return BlockEntityType.BANNER;
			
		default:
			return null;
		}
	}
}