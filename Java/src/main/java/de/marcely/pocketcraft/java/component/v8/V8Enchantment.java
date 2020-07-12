package de.marcely.pocketcraft.java.component.v8;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum V8Enchantment {
	
	ARROW_DAMAGE(48),
	ARROW_FIRE(50),
	ARROW_INFINITE(51),
	ARROW_KNOCKBACK(49),
	
	DAMAGE_ALL(16),
	DAMAGE_ARTHROPODS(18),
	DAMAGE_UNDEAD(17),
	
	PROTECTION_ENVIRONMENTAL(0),
	PROTECTION_EXPLOSIONS(3),
	PROTECTION_FALL(2),
	PROTECTION_FIRE(1),
	PROTECTION_PROJECTILE(4),
	
	DEPTH_STRIDER(8),
	DIG_SPEED(32),
	DURABILITY(34),
	FIRE_ASPECT(20),
	KNOCKBACK(19),
	LOOT_BONUS_BLOCKS(21),
	LUCK(61),
	LURE(62),
	OXYGEN(5);
	
	@Getter private final int id;
	
	private V8Enchantment(int id){
		this.id = id;
	}
	
	public static @Nullable V8Enchantment fromId(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
}
