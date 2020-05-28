package de.marcely.pocketcraft.translate.bedrocktojava.world.v8;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.*;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8BlockEntityTranslator {
	
	public static @Nullable BlockEntity handleSpawn(World world, Chunk chunk, int x, int y, int z, short id, byte data, Short oldId){
		final BlockEntityType type = getType(id);
		
		if(type == null){
			if(oldId != null && getType(oldId) != null)
				chunk.removeBlockEntity(x & 0xF, y, z & 0xF);
			
			return null;
		}
		
		final BlockEntity entity = type.newInstance(x, y, z);
		
		if(entity == null){
			System.out.println("Failed to create instance of block entity: " + type);
			return null;
		}
		
		applyData(entity, world, data);
		
		chunk.addBlockEntity(x & 0xF, y, z & 0xF, entity);
		
		return entity;
	}
	
	private static void applyData(BlockEntity rawEntity, World world, byte data){
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
		
		case CHEST:
		{
			final BlockEntityChest entity = (BlockEntityChest) rawEntity;
			final BlockEntityChest pair = findPairableChest(world, entity.getX(), entity.getY(), entity.getZ());
			
			if(pair != null){
				entity.pair(pair.getX(), pair.getZ());
				pair.pair(entity.getX(), entity.getZ());
			
			}else{
				entity.unpair();
			}
		}
		break;
		
		default: break;
		}
	}
	
	private static @Nullable BlockEntityChest findPairableChest(World world, int x, int y, int z){
		BlockEntityChest pair = null;
		
		{
    		pair = checkPairableChest(world, x+1, y, z);
    		
    		if(pair != null)
    			return pair;
		}
		
		{
    		pair = checkPairableChest(world, x-1, y, z);
    		
    		if(pair != null)
    			return pair;
		}
		
		{
    		pair = checkPairableChest(world, x, y, z+1);
    		
    		if(pair != null)
    			return pair;
		}
		
		{
    		pair = checkPairableChest(world, x, y, z-1);
    		
    		if(pair != null)
    			return pair;
		}
		
		return null;
	}
	
	private static @Nullable BlockEntityChest checkPairableChest(World world, int x, int y, int z){
		final BlockEntity rawEntity = world.getBlockEntity(x, y, z);
		
		if(rawEntity == null || !(rawEntity instanceof BlockEntityChest))
			return null;
		
		return (BlockEntityChest) rawEntity;
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
			
		case 176: // standing
		case 177: // wall
			return BlockEntityType.BANNER;
			
		default:
			return null;
		}
	}
}