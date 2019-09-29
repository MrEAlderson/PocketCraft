package de.marcely.pocketcraft.bedrock.world.entity;

public enum EntityEventType {
	
	ANIMATION_HURT((byte) 2),
	ANIMATION_DEATH((byte) 3),
	
	TAME_FAIL((byte) 6),
	TAME_SUCCESS((byte) 7),
	
	ANIMATION_SHAKE_DRY((byte) 8),
	ITEM_USE((byte) 9),
	ANIMATION_EAT_GRASS((byte) 10),
	
	FISH_HOOK_BUBBLE((byte) 11),
	FISH_HOOK_POSITION((byte) 12),
	FISH_HOOK_HOOK((byte) 13),
	FISH_HOOK_TEASE((byte) 14),
	
	ANIMATION_SQUID_INK((byte) 15),
	
	SOUND_AMBIENT((byte) 17),
	RESPAWN((byte) 18),
	
	FIREWORK_EXPLODE((byte) 25),
	
	ENCHANT((byte) 34),
	
	EAT((byte) 57);
	
	public final byte id;
	
	private EntityEventType(byte id){
		this.id = id;
	}
}