package de.marcely.pocketcraft.bedrock.component.world.entity;

public enum EntityEvent {
	
	HURT(2),
	DEATH(3),
	
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
	
	FIREWORK_EXPLODE(25),
	
	ENCHANT(34),
	
	EAT(57),
	
	MERGE_ITEMS(69);
	
	public final byte id;
	
	private EntityEvent(int id){
		this.id = (byte) id;
	}
}