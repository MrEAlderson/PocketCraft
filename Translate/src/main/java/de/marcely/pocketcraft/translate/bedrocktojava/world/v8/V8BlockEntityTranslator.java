package de.marcely.pocketcraft.translate.bedrocktojava.world.v8;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.*;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8BlockEntityTranslator {
	
	public static @Nullable BlockEntity handleSpawn(World world, Chunk chunk, int x, int y, int z, short id, byte data, Short oldId){
		final BlockEntityType type = getType(id);
		
		// optionally remove the old one
		if(oldId != null){
			final BlockEntityType oldType = getType(oldId);
			
			if(oldType != null){
				// there's no block entity at this point anymore. remove the current one
				// and if there's a new one, it'll replace it anyways, so no need to remove it extra
				if(type == null)
					chunk.removeBlockEntity(x & 0xF, y, z & 0xF);
				
				// block entity has not changed. do not do any changes other than updating data
				else if(type == oldType){
					final BlockEntity entity = chunk.getBlockEntity(x & 0xF, y, z & 0xF);
					
					if(entity != null && applyData(entity, world, data) == true)
						world.getPlayer().updateBlockEntity(entity);
					
					return null;
				}
			}
		}
		
		if(type == null)
			return null;
		
		final BlockEntity entity = type.newInstance(x, y, z);
		
		if(entity == null){
			System.out.println("Failed to create instance of block entity: " + type);
			return null;
		}
		
		applyData(entity, world, data);
		
		chunk.addBlockEntity(x & 0xF, y, z & 0xF, entity);
		
		return entity;
	}
	
	/**
	 * 
	 * @return If changes were made
	 */
	private static boolean applyData(BlockEntity rawEntity, World world, byte data){
		switch(rawEntity.getType()){
		case BED:
		{
			final BlockEntityBed entity = (BlockEntityBed) rawEntity;
			
			entity.setColor((byte) 14);
		} 
		return true;
		
		case SKULL:
		{
			final BlockEntitySkull entity = (BlockEntitySkull) rawEntity;
			
			entity.setSkullType(data);
		}
		return true;
		
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
		return true;
		
		case PISTON_ARM:
		{
			final BlockEntityPistonArm entity = (BlockEntityPistonArm) rawEntity;
			
			if(data >= 6){
				entity.setPowered(true);
				entity.setProgress(1F);
				entity.setLastProgress(0F);
			
			}else{
				entity.setPowered(false);
				entity.setProgress(0F);
				entity.setLastProgress(1F);
			}
		}
		return true;
		
		default:
			return false;
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
	
	public static @Nullable BlockEntityType getType(short id){
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
			
		case 144:
			return BlockEntityType.SKULL;
			
		case 140:
			return BlockEntityType.FLOWER_POT;
			
		case 117:
			return BlockEntityType.BREWING_STAND;
			
		case 118:
			return BlockEntityType.CAULDRON;
			
		case 138:
			return BlockEntityType.BEACON;
			
		case 29: // sticky piston
		case 33: // piston
		case 34: // piston arm
			return BlockEntityType.PISTON_ARM;
			
		case 154:
			return BlockEntityType.HOPPER;
			
		case 26:
			return BlockEntityType.BED;
			
		case 84:
			return BlockEntityType.JUKEBOX;
			
		case 176: // standing
		case 177: // wall
			return BlockEntityType.BANNER;
			
		case 52:
			return BlockEntityType.MOB_SPAWNER;
			
		default:
			return null;
		}
	}
}