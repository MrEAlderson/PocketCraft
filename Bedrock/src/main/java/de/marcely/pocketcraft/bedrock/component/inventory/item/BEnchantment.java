package de.marcely.pocketcraft.bedrock.component.inventory.item;

import org.jetbrains.annotations.Nullable;

public enum BEnchantment {
	
	PROTECTION, // 0
	FIRE_PROTECTION, // 1
	FEATHER_FALLING, // 2
	BLAST_PROTECTION, // 3
	PROJECTILE_PROTECTION, // 4
	THORNS, // 5
	RESPIRATION, // 6
	DEPTH_STRIDER, // 7
	AQUA_AFFINITY, // 8
	SHARPNESS, // 9
	SMITE, // 10
	BANE_OF_ARTHROPODS, // 11
	KNOCKBACK, // 12
	FIRE_ASPECT, // 13
	LOOTING, // 14
	EFFICIENCY, // 15
	SILK_TOUCH, // 16
	UNBREAKING, // 17
	FORTUNE, // 18
	POWER, // 19
	PUNCH, // 20
	FLAME, // 21
	INFINITY, // 22
	LUCK_OF_THE_SEA, // 23
	LURE, // 24
	FROST_WALKER, // 25
	MENDING, // 26
	BINDING, // 27
	VANISHING, // 28
	IMPALING, // 29
	RIPTIDE, // 30
	LOYALTY, // 31
	CHANNELING, // 32
	MULTISHOT, // 33
	PIERCING, // 34
	QUICK_CHARGE, // 35
	SOUL_SPEED; // 36
	
	public int getId(){
		return this.ordinal();
	}
	
	public static @Nullable BEnchantment fromId(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
