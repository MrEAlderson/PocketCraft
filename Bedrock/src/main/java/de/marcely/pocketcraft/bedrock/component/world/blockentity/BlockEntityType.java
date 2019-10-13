package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum BlockEntityType {

	CHEST("Chest", BlockEntityChest.class),
    ENDER_CHEST("EnderChest", BlockEntityEnderChest.class),
    FURNACE("Furnace", BlockEntityFurnace.class),
    SIGN("Sign", BlockEntitySign.class),
    @Deprecated MOB_SPAWNER("MobSpawner", null),
    ENCHANT_TABLE("EnchantTable", BlockEntityEnchantTable.class),
    SKULL("Skull", BlockEntitySkull.class),
    FLOWER_POT("FlowerPot", BlockEntityFlowerPot.class),
    BREWING_STAND("BrewingStand", BlockEntityBrewingStand.class),
    @Deprecated DAYLIGHT_DETECTOR("DaylightDetector", null),
    @Deprecated MUSIC("Music", null),
    ITEM_FRAME("ItemFrame", BlockEntityItemFrame.class),
    CAULDRON("Cauldron", BlockEntityCauldron.class),
    BEACON("Beacon", BlockEntityBeacon.class),
    @Deprecated PISTON_ARM("PistonArm", null),
    MOVING_BLOCK("MovingBlock", BlockEntityMovingBlock.class),
    @Deprecated COMPARATOR("Comparator", null),
    HOPPER("Hopper", BlockEntityHopper.class),
    BED("Bed", BlockEntityBed.class),
    JUKEBOX("Jukebox", BlockEntityJukebox.class),
    SHULKER_BOX("ShulkerBox", BlockEntityShulkerBox.class),
    BANNER("Banner", BlockEntityBanner.class);
	
	@Getter private final String id;
	private final Class<? extends BlockEntity> clazz;
	
	private BlockEntityType(String id, Class<? extends BlockEntity> clazz){
		this.id = id;
		this.clazz = clazz;
	}
	
	public @Nullable BlockEntity newInstance(){
		if(this.clazz == null)
			return null;
		
		try{
			return this.clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static @Nullable BlockEntityType getById(String id){
		for(BlockEntityType type:values()){
			if(type.id.equals(id))
				return type;
		}
		
		return null;
	}
}
