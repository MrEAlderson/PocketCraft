package de.marcely.pocketcraft.bedrock.world.entity;

public enum EntityAttributeType {
	
	ABSORPTION(0, "minecraft:absorption", 0F, 340282346638528859811704183484516925440F, 0F),
	SATURATION(1, "minecraft:player.saturation", 0F, 20F, 5F),
	EXHAUSTION(2, "minecraft:player.exhaustion", 0F, 5F, 0.41F),
	KNOCKBACK_RESISTANCE(3, "minecraft:knockback_resistance", 0F, 1F, 0F),
	HEALTH(4, "minecraft:health", 0F, 20F, 20F),
	MOVEMENT_SPEED(5, "minecraft:movement", 0F, 340282346638528859811704183484516925440F, 0.1F),
	FOLLOW_RANGE(6, "minecraft:follow_range", 0F, 2048F, 16F, false),
	FOOD(7, "minecraft:player.hunger", 0F, 20F, 20F),
	ATTACK_DAMAGE(8, "minecraft:attack_damage", 0F, 340282346638528859811704183484516925440F, 1F, false),
	EXPERIENCE_LEVEL(9, "minecraft:player.level", 0F, 24791F, 0F),
	EXPERIENCE(10, "minecraft:player.experience", 0F, 1F, 0F);
	
	public final int id;
	public final String name;
	public final float minValue, maxValue, defaultValue;
	public final boolean transfer;
	
	private EntityAttributeType(int id, String name, float minValue, float maxValue, float defaultValue){
		this(id, name, minValue, maxValue, defaultValue, true);
	}
	
	private EntityAttributeType(int id, String name, float minValue, float maxValue, float defaultValue, boolean transfer){
		this.id = id;
		this.name = name;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.defaultValue = defaultValue;
		this.transfer = transfer;
	}
}
