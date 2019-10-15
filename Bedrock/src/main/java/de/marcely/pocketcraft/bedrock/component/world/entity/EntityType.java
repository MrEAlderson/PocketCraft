package de.marcely.pocketcraft.bedrock.component.world.entity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum EntityType {
	
	// aggressive
	ZOMBIE("minecraft:zombie", 0.6F, 1.95F),
	CREEPER("minecraft:creeper", 0.6F, 1.7F),
	SKELETON("minecraft:skeleton", 0.6F, 1.99F),
	SPIDER("minecraft:spider", 1.4F, 0.9F),
	ZOMBIE_PIGMAN("minecraft:zombie_pigman", 0.6F, 1.95F),
	SLIME("minecraft:slime", 2.04F, 2.04F),
	ENDERMAN("minecraft:enderman", 0.6F, 2.9F),
	SILVERFISH("minecraft:silverfish", 0.4F, 0.3F),
	CAVE_SPIDER("minecraft:cave_spider", 0.7F, 0.5F),
	GHAST("minecraft:ghast", 4F, 4F),
	MAGMA_CUBE("minecraft:magma_cube", 2.04F, 2.04F),
	BLAZE("minecraft:blaze", 0.6F, 1.8F),
	ZOMBIE_VILLAGER("minecraft:zombie_villager", 0.6F, 1.95F),
	WITCH("minecraft:witch", 0.6F, 1.95F),
	STRAY("minecraft:stray", 0.6F, 1.99F),
	HUSK("minecraft:husk", 0.6F, 1.95F),
	WITHER_SKELETON("minecraft:wither_skeleton", 0.7F, 2.4F),
	GUARDIAN("minecraft:guardian", 0.85F, 0.85F),
	ELDER_GUARDIAN("minecraft:elder_guardian", 1.9975F, 1.9975F),
	WITHER("minecraft:wither", 0.9F, 3.5F),
	ENDER_DRAGON("minecraft:ender_dragon", 13F, 4F),
	SHULKER("minecraft:shulker", 1F, 1F),
	ENDERMITE("minecraft:endermite", 0.4F, 0.3F),
	VINDICATOR("minecraft:vindicator", 0.6F, 1.95F),
	PHANTOM("minecraft:phantom", 0.9F, 0.5F),
	EVOKER("minecraft:evocation_illager", 0.6F, 1.95F),
	VEX("minecraft:vex", 0.4F, 0.8F),
	RAVAGER("minecraft:ravager", 1.9F, 1.2F),
	PILLAGER("minecraft:pillager", 0.6F, 1.95F),
	DROWNED("minecraft:drowned", 0.6F, 1.95F),
	
	// passive
	CHICKEN("minecraft:chicken", 0.4F, 0.7F),
	COW("minecraft:cow", 0.9F, 1.4F),
	PIG("minecraft:pig", 0.9F, 0.9F),
	SHEEP("minecraft:sheep", 0.9F, 1.3F),
	WOLF("minecraft:wolf", 0.6F, 0.85F),
	VILLAGER("minecraft:villager", 0.6F, 1.8F),
	MOOSHROOM("minecraft:mooshroom", 0.9F, 1.4F),
	SQUID("minecraft:squid", 0.8F, 0.8F),
	RABBIT("minecraft:rabbit", 0.4F, 0.5F),
	BAT("minecraft:bat", 0.5F, 0.9F),
	OCELOT("minecraft:ocelot", 0.6F, 0.7F),
	HORSE("minecraft:horse", 1.3965F, 1.6F),
	DONKEY("minecraft:donkey", 1.3965F, 1.6F),
	MULE("minecraft:mule", 1.3965F, 1.6F),
	SKELETON_HORSE("minecraft:skeleton_horse", 1.4F, 1.6F),
	ZOMBIE_HORSE("minecraft:zombie_horse", 1.4F, 1.6F),
	POLAR_BEAR("minecraft:polar_bear", 1.3F, 1.4F),
	LLAMA("minecraft:llama", 0.9F, 1.87F),
	PARROT("minecraft:parrot", 0.5F, 0.9F),
	DOLPHIN("minecraft:dolphin", 0.9F, 0.6F),
	TURTLE("minecraft:turtle", 1.2F, 0.4F),
	CAT("minecraft:cat", 0.6F, 0.7F),
	PUFFERFISH("minecraft:pufferfish", 0.35F, 0.35F),
	SALMON("minecraft:salmon", 0.7F, 0.4F),
	TROPICAL_FISH("minecraft:tropicalfish", 0.5F, 0.4F),
	COD("minecraft:cod", 0.5F, 0.2F),
	PANDA("minecraft:panda", 1.125F, 1.25F),
	WANDERING_TRADER("minecraft:wandering_trader", 0.6F, 1.8F),
	
	// projectiles
	TRIDENT("minecraft:thrown_trident", 0.05F, 0.05F),
	SNOWBALL("minecraft:snowball", 0.25F, 0.25F),
	ENDER_PEARL("minecraft:ender_pearl", 0.25F, 0.25F),
	EGG("minecraft:egg", 0.25F, 0.25F),
	ARROW("minecraft:arrow", 0.5F, 0.5F),
	
	// objects
	XP_ORB("minecraft:xp_orb", 0.25F, 0.25F),
	PAINTING("minecraft:painting"),
	TNT("minecraft:tnt", 0.98F, 0.98F),
	FALLING_BLOCK("minecraft:falling_block", 0.98F, 0.98F),
	ITEM("minecraft:item", 0.25F, 0.25F),
	
	// other stuff
	IRON_GOLEM("minecraft:iron_golem", 1.4F, 2.7F),
	SNOW_GOLEM("minecraft:snow_golem", 0.7F, 1.9F),
	NPC("minecraft:npc"),
	AGENT("minecraft:agent"),
	
	ARMOR_STAND("minecraft:armor_stand", 0.5F, 1.975F),
	TRIPOD_CAMERA("minecraft:tripod_camera"),
	PLAYER("minecraft:player", 0.6F, 1.8F),
	XP_BOTTLE("minecraft:xp_bottle"),
	EYE_OF_ENDER_SIGNAL("minecraft:eye_of_ender_signal"),
	ENDER_CRYSTAL("minecraft:ender_crystal"),
	FIREWORKS_ROCKET("minecraft:fireworks_rocket"),
	SHULKER_BULLET("minecraft:shulker_bullet"),
	FISHING_HOOK("minecraft:fishing_hook"),
	DRAGON_FIREBALL("minecraft:dragon_fireball"),
	MINECART("minecraft:minecart"),
	FIREBALL("minecraft:fireball"),
	SPLASH_POTION("minecraft:splash_potion"),
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
	ICE_BOMB("minecraft:ice_bomb"),
	BALLOON("minecraft:balloon"),
	VILLAGER_V2("minecraft:villager_v2"),
	ZOMBIE_VILLAGER_V2("minecraft:zombie_villager_v2");
	
	// TODO They may don't exist at all or their id is wrong
	// MOVING_BLOCK("minecraft:moving_block", EntityGroup.NONE),
	// CHALKBOARD("minecraft:chalkboard", null),
	
	@Getter private final String id;
	@Getter private final float width, height;
	
	private EntityType(String id, float width, float height){
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	private EntityType(String id){
		this(id, 1F, 1F);
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
