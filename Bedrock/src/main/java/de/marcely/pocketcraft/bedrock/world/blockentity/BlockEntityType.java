package de.marcely.pocketcraft.bedrock.world.blockentity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum BlockEntityType {

	CHEST("Chest"),
    ENDER_CHEST("EnderChest"),
    FURNACE("Furnace"),
    SIGN("Sign"),
    @Deprecated MOB_SPAWNER("MobSpawner"),
    ENCHANT_TABLE("EnchantTable"),
    SKULL("Skull"),
    FLOWER_POT("FlowerPot"),
    BREWING_STAND("BrewingStand"),
    @Deprecated DAYLIGHT_DETECTOR("DaylightDetector"),
    @Deprecated MUSIC("Music"),
    ITEM_FRAME("ItemFrame"),
    CAULDRON("Cauldron"),
    BEACON("Beacon"),
    @Deprecated PISTON_ARM("PistonArm"),
    MOVING_BLOCK("MovingBlock"),
    @Deprecated COMPARATOR("Comparator"),
    HOPPER("Hopper"),
    BED("Bed"),
    JUKEBOX("Jukebox"),
    SHULKER_BOX("ShulkerBox"),
    BANNER("Banner");
	
	@Getter private final String id;
	
	private BlockEntityType(String id){
		this.id = id;
	}
	
	public static @Nullable BlockEntityType getById(String id){
		for(BlockEntityType type:values()){
			if(type.id.equals(id))
				return type;
		}
		
		return null;
	}
}
