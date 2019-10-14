package de.marcely.pocketcraft.bedrock.component.world.entity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum EntityType {
	
	CHICKEN("minecraft:chicken"),
	COW("minecraft:cow"),
	PIG("minecraft:pig"),
	SHEEP("minecraft:sheep"),
	WOLF("minecraft:wolf"),
	VILLAGER("minecraft:villager"),
	MOOSHROOM("minecraft:mooshroom"),
	SQUID("minecraft:squid"),
	RABBIT("minecraft:rabbit"),
	BAT("minecraft:bat"),
	IRON_GOLEM("minecraft:iron_golem"),
	SNOW_MAN("minecraft:snow_golem"),
	OCELOT("minecraft:ocelot"),
	HORSE("minecraft:horse"),
	DONKEY("minecraft:donkey"),
	MULE("minecraft:mule"),
	SKELETON_HORSE("minecraft:skeleton_horse"),
	ZOMBIE_HORSE("minecraft:zombie_horse"),
	POLAR_BEAR("minecraft:polar_bear"),
	LLAMA("minecraft:llama"),
	PARROT("minecraft:parrot"),
	DOLPHIN("minecraft:dolphin"),
	ZOMBIE("minecraft:zombie"),
	CREEPER("minecraft:creeper"),
	SKELETON("minecraft:skeleton"),
	SPIDER("minecraft:spider"),
	ZOMBIE_PIGMAN("minecraft:zombie_pigman"),
	SLIME("minecraft:slime"),
	ENDERMAN("minecraft:enderman"),
	SILVERFISH("minecraft:silverfish"),
	CAVE_SPIDER("minecraft:cave_spider"),
	GHAST("minecraft:ghast"),
	MAGMA_CUBE("minecraft:magma_cube"),
	BLAZE("minecraft:blaze"),
	ZOMBIE_VILLAGER("minecraft:zombie_villager"),
	WITCH("minecraft:witch"),
	STRAY("minecraft:stray"),
	HUSK("minecraft:husk"),
	WITHER_SKELETON("minecraft:wither_skeleton"),
	GUARDIAN("minecraft:guardian"),
	ELDER_GUARDIAN("minecraft:elder_guardian"),
	NPC("minecraft:npc"),
	WITHER("minecraft:wither"),
	ENDER_DRAGON("minecraft:ender_dragon"),
	SHULKER("minecraft:shulker"),
	ENDERMITE("minecraft:endermite"),
	AGENT("minecraft:agent"),
	VINDICATOR("minecraft:vindicator"),
	PHANTOM("minecraft:phantom"),
	
	ARMOR_STAND("minecraft:armor_stand"),
	TRIPOD_CAMERA("minecraft:tripod_camera"),
	PLAYER("minecraft:player"),
	ITEM("minecraft:item"),
	TNT("minecraft:tnt"),
	FALLING_BLOCK("minecraft:falling_block"),
	XP_BOTTLE("minecraft:xp_bottle"),
	XP_ORB("minecraft:xp_orb"),
	EYE_OF_ENDER_SIGNAL("minecraft:eye_of_ender_signal"),
	ENDER_CRYSTAL("minecraft:ender_crystal"),
	FIREWORKS_ROCKET("minecraft:fireworks_rocket"),
	TRIDENT("minecraft:thrown_trident"),
	TURTLE("minecraft:turtle"),
	CAT("minecraft:cat"),
	SHULKER_BULLET("minecraft:shulker_bullet"),
	FISHING_HOOK("minecraft:fishing_hook"),
	DRAGON_FIREBALL("minecraft:dragon_fireball"),
	ARROW("minecraft:arrow"),
	SNOWBALL("minecraft:snowball"),
	EGG("minecraft:egg"),
	PAINTING("minecraft:painting"),
	MINECART("minecraft:minecart"),
	FIREBALL("minecraft:fireball"),
	SPLASH_POTION("minecraft:splash_potion"),
	ENDER_PEARL("minecraft:ender_pearl"),
	LEASH_KNOT("minecraft:leash_knot"),
	WITHER_SKULL("minecraft:wither_skull"),
	BOAT("minecraft:boat"),
	WITHER_SKULL_DANGEROUS("minecraft:wither_skull_dangerous"),
	
	LIGHTNING_BOLT("minecraft:lightning_bolt"),
	SMALL_FIREBALL("minecraft:small_fireball"),
	AREA_EFFECT_CLOUD("minecraft:area_effect_cloud"),
	HOPPER_MINECART("minecraft:hopper_minecart"),
	TNT_MINECART("minecraft:tnt_minecart"),
	CHEST_MINECART("minecraft:chest_minecart"),
	
	COMMAND_BLOCK_MINECART("minecraft:command_block_minecart"),
	LINGERING_POTION("minecraft:lingering_potion"),
	LLAMA_SPIT("minecraft:llama_spit"),
	EVOCATION_FANG("minecraft:evocation_fang"),
	EVOCATION_ILLAGER("minecraft:evocation_illager"),
	VEX("minecraft:vex"),
	ICE_BOMB("minecraft:ice_bomb"),
	BALLOON("minecraft:balloon"),
	PUFFERFISH("minecraft:pufferfish"),
	SALMON("minecraft:salmon"),
	DROWNED("minecraft:drowned"),
	TROPICAL_FISH("minecraft:tropicalfish"),
	COD("minecraft:cod"),
	PANDA("minecraft:panda");
	
	// TODO They may don't exist at all or their id is wrong
	// MOVING_BLOCK("minecraft:moving_block", EntityGroup.NONE),
	// CHALKBOARD("minecraft:chalkboard", null),
	
	@Getter private final String id;
	
	private EntityType(String id){
		this.id = id;
	}
	
	public float getYAppend(EntityCollisionState state){
		if(this == PLAYER){
			switch(state){
			case SNEAKING:
				return 1.42F;
			case ELYTRA:
				return 0.37F;
			default:
				return 1.62F;
			}
		
		}else if(this == BOAT)
			return 0.3F;
		else
			return 0F;
	}
	
	public static @Nullable EntityType ofId(String id){
		for(EntityType type:values()){
			if(type.id.equals(id))
				return type;
		}
		
		return null;
	}
}
