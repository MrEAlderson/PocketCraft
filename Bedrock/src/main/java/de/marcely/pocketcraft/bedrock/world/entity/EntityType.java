package de.marcely.pocketcraft.bedrock.world.entity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum EntityType {
	
	CHICKEN("minecraft:chicken", EntityGroup.ANIMAL),
	COW("minecraft:cow", EntityGroup.ANIMAL),
	PIG("minecraft:pig", EntityGroup.ANIMAL),
	SHEEP("minecraft:sheep", EntityGroup.ANIMAL),
	WOLF("minecraft:wolf", EntityGroup.TAMEABLE_ANIMAL),
	VILLAGER("minecraft:villager", EntityGroup.AGEABLE),
	MOOSHROOM("minecraft:mooshroom", EntityGroup.ANIMAL),
	SQUID("minecraft:squid", EntityGroup.WATERMOB),
	RABBIT("minecraft:rabbit", EntityGroup.ANIMAL),
	BAT("minecraft:bat", EntityGroup.AMBIENT),
	IRON_GOLEM("minecraft:iron_golem", EntityGroup.GOLEM),
	SNOW_MAN("minecraft:snow_golem", EntityGroup.GOLEM),
	OCELOT("minecraft:ocelot", EntityGroup.TAMEABLE_ANIMAL),
	HORSE("minecraft:horse", EntityGroup.ABSTRACT_HORSE),
	DONKEY("minecraft:donkey", EntityGroup.CHESTED_HORSE),
	MULE("minecraft:mule", EntityGroup.CHESTED_HORSE),
	SKELETON_HORSE("minecraft:skeleton_horse", EntityGroup.ABSTRACT_HORSE),
	ZOMBIE_HORSE("minecraft:zombie_horse", EntityGroup.ABSTRACT_HORSE),
	POLAR_BEAR("minecraft:polar_bear", EntityGroup.ABSTRACT_HORSE),
	LLAMA("minecraft:llama", EntityGroup.CHESTED_HORSE),
	PARROT("minecraft:parrot", EntityGroup.TAMEABLE_ANIMAL),
	DOLPHIN("minecraft:dolphin", null),
	ZOMBIE("minecraft:zombie", EntityGroup.ZOMBIE),
	CREEPER("minecraft:creeper", EntityGroup.MONSTER),
	SKELETON("minecraft:skeleton", EntityGroup.MONSTER),
	SPIDER("minecraft:spider", EntityGroup.MONSTER),
	ZOMBIE_PIGMAN("minecraft:zombie_pigman", EntityGroup.MONSTER),
	SLIME("minecraft:slime", EntityGroup.INSENTIENT),
	ENDERMAN("minecraft:enderman", EntityGroup.MONSTER),
	SILVERFISH("minecraft:silverfish", EntityGroup.MONSTER),
	CAVE_SPIDER("minecraft:cave_spider", EntityGroup.MONSTER),
	GHAST("minecraft:ghast", EntityGroup.FLYING),
	MAGMA_CUBE("minecraft:magma_cube", EntityGroup.INSENTIENT),
	BLAZE("minecraft:blaze", EntityGroup.MONSTER),
	ZOMBIE_VILLAGER("minecraft:zombie_villager", EntityGroup.ZOMBIE),
	WITCH("minecraft:witch", EntityGroup.MONSTER),
	STRAY("minecraft:stray", EntityGroup.ABSTRACT_SKELETON),
	HUSK("minecraft:husk", EntityGroup.ZOMBIE),
	WITHER_SKELETON("minecraft:wither_skeleton", EntityGroup.MONSTER),
	GUARDIAN("minecraft:guardian", EntityGroup.MONSTER),
	ELDER_GUARDIAN("minecraft:elder_guardian", EntityGroup.GUARDIAN),
	NPC("minecraft:npc", null),
	WITHER("minecraft:wither", EntityGroup.MONSTER),
	ENDER_DRAGON("minecraft:ender_dragon", EntityGroup.INSENTIENT),
	SHULKER("minecraft:shulker", EntityGroup.GOLEM),
	ENDERMITE("minecraft:endermite", EntityGroup.MONSTER),
	AGENT("minecraft:agent", null),
	VINDICATOR("minecraft:vindicator", null),
	PHANTOM("minecraft:phantom", null),
	
	ARMOR_STAND("minecraft:armor_stand", EntityGroup.LIVING),
	TRIPOD_CAMERA("minecraft:tripod_camera", null),
	PLAYER("minecraft:player", null),
	ITEM("minecraft:item", EntityGroup.NONE),
	TNT("minecraft:tnt", EntityGroup.NONE),
	FALLING_BLOCK("minecraft:falling_block", EntityGroup.NONE),
	XP_BOTTLE("minecraft:xp_bottle", EntityGroup.PROJECTILE),
	XP_ORB("minecraft:xp_orb", null),
	EYE_OF_ENDER_SIGNAL("minecraft:eye_of_ender_signal", EntityGroup.PROJECTILE),
	ENDER_CRYSTAL("minecraft:ender_crystal", EntityGroup.NONE),
	FIREWORKS_ROCKET("minecraft:fireworks_rocket", EntityGroup.NONE),
	TRIDENT("minecraft:thrown_trident", null),
	TURTLE("minecraft:turtle", null),
	CAT("minecraft:cat", null),
	SHULKER_BULLET("minecraft:shulker_bullet", null),
	FISHING_HOOK("minecraft:fishing_hook", EntityGroup.NONE),
	DRAGON_FIREBALL("minecraft:dragon_fireball", EntityGroup.PROJECTILE),
	ARROW("minecraft:arrow", EntityGroup.ARROW),
	SNOWBALL("minecraft:snowball", EntityGroup.PROJECTILE),
	EGG("minecraft:egg", EntityGroup.PROJECTILE),
	PAINTING("minecraft:painting", null),
	MINECART("minecraft:minecart", EntityGroup.MINECART),
	FIREBALL("minecraft:fireball", EntityGroup.FIREBALL),
	SPLASH_POTION("minecraft:splash_potion", EntityGroup.PROJECTILE),
	ENDER_PEARL("minecraft:ender_pearl", EntityGroup.PROJECTILE),
	LEASH_KNOT("minecraft:leash_knot", EntityGroup.NONE),
	WITHER_SKULL("minecraft:wither_skull", EntityGroup.FIREBALL),
	BOAT("minecraft:boat", EntityGroup.NONE),
	WITHER_SKULL_DANGEROUS("minecraft:wither_skull_dangerous", null),
	
	LIGHTNING_BOLT("minecraft:lightning_bolt", null),
	SMALL_FIREBALL("minecraft:small_fireball", null),
	AREA_EFFECT_CLOUD("minecraft:area_effect_cloud", null),
	HOPPER_MINECART("minecraft:hopper_minecart", null),
	TNT_MINECART("minecraft:tnt_minecart", null),
	CHEST_MINECART("minecraft:chest_minecart", null),
	
	COMMAND_BLOCK_MINECART("minecraft:command_block_minecart", null),
	LINGERING_POTION("minecraft:lingering_potion", null),
	LLAMA_SPIT("minecraft:llama_spit", EntityGroup.NONE),
	EVOCATION_FANG("minecraft:evocation_fang", null),
	EVOCATION_ILLAGER("minecraft:evocation_illager", null),
	VEX("minecraft:vex", null),
	ICE_BOMB("minecraft:ice_bomb", null),
	BALLOON("minecraft:balloon", null),
	PUFFERFISH("minecraft:pufferfish", null),
	SALMON("minecraft:salmon", null),
	DROWNED("minecraft:drowned", null),
	TROPICAL_FISH("minecraft:tropicalfish", null),
	COD("minecraft:cod", null),
	PANDA("minecraft:panda", null);
	
	// TODO They may don't exist at all or their id is wrong
	// MOVING_BLOCK("minecraft:moving_block", EntityGroup.NONE),
	// CHALKBOARD("minecraft:chalkboard", null),
	
	@Getter private final String id;
	@Getter private final @Nullable EntityGroup group;
	
	private EntityType(String id, @Nullable EntityGroup group){
		this.id = id;
		this.group = group;
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
