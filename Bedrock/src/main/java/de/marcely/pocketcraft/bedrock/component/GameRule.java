package de.marcely.pocketcraft.bedrock.component;

public enum GameRule {
	
    COMMAND_BLOCK_OUTPUT("commandBlockOutput", FieldType.BOOL),
    DO_DAYLIGHT_CYCLE("doDaylightCycle", FieldType.BOOL),
    DO_ENTITY_DROPS("doEntityDrops", FieldType.BOOL),
    DO_FIRE_TICK("doFireTick", FieldType.BOOL),
    DO_MOB_LOOT("doMobLoot", FieldType.BOOL),
    DO_MOB_SPAWNING("doMobSpawning", FieldType.BOOL),
    DO_TILE_DROPS("doTileDrops", FieldType.BOOL),
    DO_WEATHER_CYCLE("doWeatherCycle", FieldType.BOOL),
    DROWNING_DAMAGE("drowningDamage", FieldType.BOOL),
    FALL_DAMAGE("fallDamage", FieldType.BOOL),
    FIRE_DAMAGE("fireDamage", FieldType.BOOL),
    KEEP_INVENTORY("keepInventory", FieldType.BOOL),
    MOB_GRIEFING("mobGriefing", FieldType.BOOL),
    NATURAL_REGENERATION("naturalRegeneration", FieldType.BOOL),
    PVP("pvp", FieldType.BOOL),
    SEND_COMMAND_FEEDBACK("sendCommandFeedback", FieldType.BOOL),
    SHOW_COORDINATES("showCoordinates", FieldType.BOOL),
    TNT_EXPLODES("tntExplodes", FieldType.BOOL),
    SHOW_DEATH_MESSAGE("showDeathMessage", FieldType.BOOL);
	
	public final String id;
	public final FieldType fieldType;
	
	private GameRule(String id, FieldType fieldType){
		this.id = id;
		this.fieldType = fieldType;
	}
	
	
	public static enum FieldType {
		
		BOOL,
		UNSIGNED_VAR_INT,
		LFLOAT;
	}
}
