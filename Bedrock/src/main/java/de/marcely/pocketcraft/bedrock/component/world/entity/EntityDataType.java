package de.marcely.pocketcraft.bedrock.component.world.entity;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum EntityDataType {
	
	FLAGS(0, EntityDataValueType.LONG),
	HEALTH(1, EntityDataValueType.INT),
	VARIANT(2, EntityDataValueType.INT),
	COLOR(3, EntityDataValueType.BYTE),
	NAMETAG(4, EntityDataValueType.STRING),
	OWNER_ID(5, EntityDataValueType.LONG),
	TARGET_ID(6, EntityDataValueType.LONG),
	AIR(7, EntityDataValueType.SHORT),
	POTION_COLOR(8, EntityDataValueType.INT /* ARGB */),
	POTION_AMBIENT(9, EntityDataValueType.BYTE),
	JUMP_DURATION(10, EntityDataValueType.LONG),
	SHAKING_POWER(11, EntityDataValueType.INT), // for minecart
	SHAKING_DIRECTION(12, EntityDataValueType.INT), // for minecarts
	PADDLE_TIME_LEFT(13, EntityDataValueType.FLOAT),
	PADDLE_TIME_RIGHT(14, EntityDataValueType.FLOAT),
	EXPERIENCE(15, EntityDataValueType.INT),
	DISPLAY_ITEM(16, EntityDataValueType.INT),
	DISPLAY_OFFSET(17, EntityDataValueType.INT),
	HAS_DISPLAY(18, EntityDataValueType.BYTE /* BOOL */),
	CHARGED(22, EntityDataValueType.BYTE),
	ENDERMAN_HELD_ITEM_RUNTIME_ID(23, EntityDataValueType.SHORT),
	ENTITY_AGE(24, EntityDataValueType.SHORT),
	/* 25 */
	PLAYER_FLAGS(26, EntityDataValueType.BYTE),
	PLAYER_INDEX(27, null /* idk */),
	PLAYER_BED_POSITION(28, EntityDataValueType.FLOOR_VECTOR3),
	FIREBALL_POWER_X(29, EntityDataValueType.FLOAT),
	FIREBALL_POWER_Y(30, EntityDataValueType.FLOAT),
	FIREBALL_POWER_Z(31, EntityDataValueType.FLOAT),
	/* 32
	 * 33
	 * 34
	 * 35 */
	// POTION_AUX_VALUE(36, EntityDataValueType.SHORT),
	LEAD_HOLDER_ID(37, EntityDataValueType.LONG),
	SCALE(38, EntityDataValueType.FLOAT),
	// INTERACTIVE_TAG(39, EntityDataValueType.STRING),
	NPC_SKIN_ID(40, EntityDataValueType.STRING),
	URL_TAG(41, EntityDataValueType.STRING),
	MAX_AIR(42, EntityDataValueType.SHORT),
	MARK_VARIANT(43, EntityDataValueType.INT),
	CONTAINER_TYPE(44, EntityDataValueType.BYTE),
	CONTAINER_BASE_SIZE(45, EntityDataValueType.INT),
	CONTAINER_EXTRA_SLOTS_PER_STRENGTH(46, EntityDataValueType.INT),
	BLOCK_TARGET(47, EntityDataValueType.FLOOR_VECTOR3),
	WITHER_INVULNERABLE_TICKS(48, EntityDataValueType.INT),
	WITHER_TARGET_HEAD_CENTER(49, EntityDataValueType.LONG),
	WITHER_TARGET_HEAD_LEFT(50, EntityDataValueType.LONG),
	WITHER_TARGET_HEAD_RIGHT(51, EntityDataValueType.LONG),
	/* 52 */
	BOUNDING_BOX_WIDTH(53, EntityDataValueType.FLOAT),
	BOUNDING_BOX_HEIGHT(54, EntityDataValueType.FLOAT),
	FUSE_LENGTH(55, EntityDataValueType.INT),
	RIDER_SEAT_POSITION(56, EntityDataValueType.VECTOR3),
	RIDER_ROTATION_LOCKED(57, EntityDataValueType.BYTE),
	RIDER_MAX_ROTATION(58, EntityDataValueType.FLOAT),
	RIDER_MIN_ROTATION(69, EntityDataValueType.FLOAT),
	AREA_EFFECT_CLOUD_RADIUS(60, EntityDataValueType.FLOAT),
	AREA_EFFECT_CLOUD_WAITING(61, EntityDataValueType.INT),
	AREA_EFFECT_CLOUD_PARTICLE_ID(62, EntityDataValueType.INT),
	/* 63 */
	SHULKER_ATTACH_FACE(64, EntityDataValueType.BYTE),
	/* 65 */
	SHULKER_ATTACH_POS(66, EntityDataValueType.VECTOR3),
	TRADING_PLAYER_EID(67, EntityDataValueType.LONG),
	/* 68 */
	/* 69 */
	COMMAND_BLOCK_COMMAND(70, EntityDataValueType.STRING),
	COMMAND_BLOCK_LAST_OUTPUT(71, EntityDataValueType.STRING),
	COMMAND_BLOCK_TRACK_OUTPUT(72, EntityDataValueType.BYTE),
	CONTROLLING_RIDER_SEAT_NUMBER(73, EntityDataValueType.BYTE),
	STRENGTH(74, EntityDataValueType.INT),
	MAX_STRENGTH(75, EntityDataValueType.INT),
	/* 76 */
	LIMITED_LIFE(77, null /* TODO */),
	ARMOR_STAND_POSE_INDEX(78, EntityDataValueType.INT),
	ENDER_CRYSTAL_TIME_OFFSET(79, EntityDataValueType.INT),
	ALWAYS_SHOW_NAMETAG(80, EntityDataValueType.BYTE),
	COLOR2(81, EntityDataValueType.BYTE),
	/* 82 */
	SCORE_TAG(83, EntityDataValueType.STRING),
	BALLOON_ATTACHED_ENTITY(84, EntityDataValueType.LONG),
	PUFFERFISH_SIZE(85, null /* TODO */),
	/* 86 */
	/* 87 */
	/* 88 */
	/* 89 */
	/* 90 */
	FLAGS_EXTENDED(91, EntityDataValueType.LONG),
	/* 92 */
	/* 93 */
	/* 94 */
	/* 95 */
	/* 96 */
	/* 97 */
	/* 98 */
	/* 99 */
	/* 100 */
	/* 101 */
	/* 102 */
	SKIN_ID(103, EntityDataValueType.INT);
	
    public static final int FLAG_ONFIRE = 0;
    public static final int FLAG_SNEAKING = 1;
    public static final int FLAG_RIDING = 2;
    public static final int FLAG_SPRINTING = 3;
    public static final int FLAG_ACTION = 4;
    public static final int FLAG_INVISIBLE = 5;
    public static final int FLAG_TEMPTED = 6;
    public static final int FLAG_INLOVE = 7;
    public static final int FLAG_SADDLED = 8;
    public static final int FLAG_IGNITED = 9;
    public static final int FLAG_POWERED = 10;
    public static final int FLAG_BABY = 11; //disable head scaling
    public static final int FLAG_CONVERTING = 12;
    public static final int FLAG_CRITICAL = 13;
    public static final int FLAG_CAN_SHOW_NAMETAG = 14;
    public static final int FLAG_ALWAYS_SHOW_NAMETAG = 15;
    public static final int FLAG_IMMOBILE = 16, FLAG_NO_AI = 16;
    public static final int FLAG_SILENT = 17;
    public static final int FLAG_WALLCLIMBING = 18;
    public static final int FLAG_CAN_CLIMB = 19;
    public static final int FLAG_SWIMMER = 20;
    public static final int FLAG_CAN_FLY = 21;
    public static final int FLAG_WALKER = 22;
    public static final int FLAG_RESTING = 23;
    public static final int FLAG_SITTING = 24;
    public static final int FLAG_ANGRY = 25;
    public static final int FLAG_INTERESTED = 26;
    public static final int FLAG_CHARGED = 27;
    public static final int FLAG_TAMED = 28;
    public static final int FLAG_ORPHANED = 29;
    public static final int FLAG_LEASHED = 30;
    public static final int FLAG_SHEARED = 31;
    public static final int FLAG_GLIDING = 32;
    public static final int FLAG_ELDER = 33;
    public static final int FLAG_MOVING = 34;
    public static final int FLAG_BREATHING = 35;
    public static final int FLAG_CHESTED = 36;
    public static final int FLAG_STACKABLE = 37;
    public static final int FLAG_SHOWBASE = 38;
    public static final int FLAG_REARING = 39;
    public static final int FLAG_VIBRATING = 40;
    public static final int FLAG_IDLING = 41;
    public static final int FLAG_EVOKER_SPELL = 42;
    public static final int FLAG_CHARGE_ATTACK = 43;
    public static final int FLAG_WASD_CONTROLLED = 44;
    public static final int FLAG_CAN_POWER_JUMP = 45;
    public static final int FLAG_LINGER = 46;
    public static final int FLAG_HAS_COLLISION = 47;
    public static final int FLAG_AFFECTED_BY_GRAVITY = 48;
    public static final int FLAG_FIRE_IMMUNE = 49;
    public static final int FLAG_FIRE_DANCING = 50;
    public static final int FLAG_FIRE_ENCHANTED = 50;
    public static final int FLAG_ENCHANTED = 51;
	public static final int FLAG_SHOW_TRIDENT_ROPE = 52; // tridents show an animated rope when enchanted with loyalty after they are thrown and return to their owner. To be combined with OWNER_EID
	public static final int FLAG_CONTAINER_PRIVATE = 53; //inventory is private, doesn't drop contents when killed if true
	public static final int FLAG_TRANSFORMING = 54;
	public static final int FLAG_SPIN_ATTACK = 55; // entity starts spinning, player also gets weird glass thingy
	public static final int FLAG_SWIMMING = 56;
	public static final int FLAG_BRIBED = 57;
	public static final int FLAG_PREGNANT = 58;
	public static final int FLAG_LAYING_EGG = 59;
	public static final int FLAG_RIDER_CAN_PICK = 60; //???
	public static final int FLAG_TRANSITION_SITTING = 61;
	public static final int FLAG_EATING = 62;
	public static final int FLAG_LAYING_DOWN = 63;
	public static final int FLAG_SNEEZING = 64;
	public static final int FLAG_TRUSTING = 65;
	public static final int FLAG_ROLLING = 66;
	public static final int FLAG_SCARED = 67;
	public static final int FLAG_IN_SCAFFOLDING = 68;
	public static final int FLAG_OVER_SCAFFOLDING = 69;
	public static final int FLAG_FALL_THROUGH_SCAFFOLDING = 70;
	public static final int FLAG_BLOCKING = 71; //shield
	public static final int FLAG_DISABLE_BLOCKING = 72;
	//73 is set when a player is attacked while using shield, unclear on purpose
	//74 related to shield usage, needs further investigation
	public static final int FLAG_SLEEPING = 75;
	//76 related to sleeping, unclear usage
	public static final int FLAG_TRADE_INTEREST = 77;
	public static final int FLAG_DOOR_BREAKER = 78; //...
	public static final int FLAG_BREAKING_OBSTRUCTION = 79;
	public static final int FLAG_DOOR_OPENER = 80; //...
	public static final int FLAG_ILLAGER_CAPTAIN = 81;
	public static final int FLAG_STUNNED = 82;
	public static final int FLAG_ROARING = 83;
	public static final int FLAG_DELAYED_ATTACKING = 84;
	public static final int FLAG_AVOIDING_MOBS = 85;
	//86 used by RangedAttackGoal
	//87 used by NearestAttackableTargetGoal
	
	public static final int PLAYER_FLAG_SLEEP = 1;
	public static final int PLAYER_FLAG_DEAD = 2;
    
	@Getter private final int id;
	@Getter private final EntityDataValueType valueType;
	
	private EntityDataType(int id, EntityDataValueType valueType){
		this.id = id;
		this.valueType = valueType;
	}
	
	public static @Nullable EntityDataType getById(int id){
		for(EntityDataType type:values()){
			if(type.id == id)
				return type;
		}
		
		return null;
	}
}
