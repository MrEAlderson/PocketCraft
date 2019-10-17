package de.marcely.pocketcraft.bedrock.component.world.entity;

import lombok.Getter;

public enum EntityEvent {
	
	HURT(2),
	DEATH(3),
	PUNCHING(4),
	
	TAME_FAIL(6),
	TAME_SUCCESS(7),
	
	SHAKE_DRY(8),
	ITEM_USE(9),
	EAT_GRASS(10),
	
	FISH_HOOK_BUBBLE(11),
	FISH_HOOK_POSITION(12),
	FISH_HOOK_HOOK(13),
	FISH_HOOK_TEASE(14),
	
	SQUID_INK(15),
	
	SOUND_AMBIENT(17),
	RESPAWN(18),
	
	HEART(21),
	
	FIREWORK_EXPLODE(25),
	DRINK(29),
	
	ENCHANT(34),
	FALL(38),
	
	EAT(57), // data = item id
	
	VILLAGER_HAPPY(60),
	DEATH_EFFECTS(61), // plays cloud particles + death sound
	WITCH_SPELL(68), 
	MERGE_ITEMS(69),
	UNKOWN(71), // round white particles falling slowly from top to the bottom of the entity
	INSTANT_EFFECT(72);
	
	@Getter private final byte id;
	
	private EntityEvent(int id){
		this.id = (byte) id;
	}
}