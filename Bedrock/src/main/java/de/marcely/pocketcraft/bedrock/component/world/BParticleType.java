package de.marcely.pocketcraft.bedrock.component.world;

import org.jetbrains.annotations.Nullable;

public enum BParticleType {
	
	UNKOWN_0, // 0, no data
	BUBBLE, // 1, no data
	BUBBLE_2, // 2
	CRITICAL, // 3, data = scale
	BLOCK_FORCE_SHIELD, // 4, data = scale
	SMOKE, // 5, data = scale
	EXPLOSION, // 6, no data
	EVAPORATION, // 7
	FLAME, // 8, no data
	LAVA, // 9, no data
	LARGE_SMOKE, // 10
	REDSTONE, // 11, data = lifetime
	RISING_RED_DUST, // 12
	ITEM_BREAK, // 13, data = item. 0x0000FFFF = damage, 0xFFFF0000 = id
	SNOWBALL_POOF, // 14
	EXPLOSION_HUGE, // 15, no data
	EXPLOSION_HUGE_SEED, // 16, no data
	FLAME_MOB, // 17, no data
	HEART, // 18, data = scale
	TERRAIN, // 19, data = block material from palette
	TOWN, // 20, aka SUSPENDED_TOWN and TOWN_AURA, no data
	PORTAL, // 21, no data
	PORTAL_2, // 22
	SPLASH, // 23, no data
	SPLASH_2, // 24
	WATER_WAKE, // 25, no data
	WATER_DRIP, // 26, no data
	LAVA_DRIP, // 27, no data
	HONEY_DRIP, // 28
	DUST, // 29, data = rgba. 000000FF = blue, 0000FF00 = green, 00FF0000 = red, FF000000 = alpha
	MOB_SPELL, // 30, no data
	MOB_SPELL_AMBIENT, // 31
	MOB_SPELL_INSTANTANEOUS, // 32, no data
	DUST_AND_NOTE, // 33
	SLIME, // 34
	SPLASH_RAIN, // 35, no data
	VILLAGER_ANGRY, // 36
	VILLAGER_HAPPY, // 37, no data
	ENCHANTMENT_TABLE, // 38, no data
	TRACKING_EMITTER, // 39
	NOTE, // 40
	WITCH_SPELL, // 41
	CARROT, // 42
	UNKOWN_1, // 43
	END_ROD, // 44
	DRAGON_BREATH_RISING, // 45
	SPIT, // 46
	TOTEM, // 47
	FOOD, // 48
	FIREWORKS_STARTER, // 49
	FIREWORKS_SPARKS, // 50
	FIREWORKS_OVERLAY, // 51
	BALLOON_GAS, // 52
	FLAME_COLORED, // 53
	SPARKLER, // 54
	CONDUIT, // 55
	BUBBLE_COLUMN_UP, // 56
	BUBBLE_COLUMN_DOWN, // 57
	SNEEZE, // 58
	END_ROD_2, // 59
	VILLAGER_ANGRY_2, // 60
	EXPLOSION_LARGE, // 61
	INK, // 62, data = scale
	RED_DUST_FALLING, // 63
	CAMPFIRE_SMOKE, // 64
	CAMPFIRE_SMOKE_2, // 65
	DRAGON_BREATH_FALLING, // 66
	DRAGON_BREATH; // 67
	
	public int getId(){
		return this.ordinal();
	}
	
	public static @Nullable BParticleType fromId(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
