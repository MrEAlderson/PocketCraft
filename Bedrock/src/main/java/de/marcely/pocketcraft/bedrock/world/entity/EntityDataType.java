package de.marcely.pocketcraft.bedrock.world.entity;

public enum EntityDataType {
	
	FLAGS(0, EntityDataValueType.LONG),
	HEALTH(1, EntityDataValueType.INT),
	VARIANT(2, EntityDataValueType.INT),
	COLOR(3, EntityDataValueType.BYTE),
	NAMETAG(4, EntityDataValueType.STRING),
	OWNER_ID(5, EntityDataValueType.LONG),
	TARGET_ID(6, EntityDataValueType.LONG),
	AIR(7, EntityDataValueType.SHORT),
	POTION_COLOR(8, EntityDataValueType.COLOR),
	POTION_AMBIENT(9, EntityDataValueType.BYTE),
	/* 10 */
	HURT_TIME(11, EntityDataValueType.INT),
	HURT_DIRECTION(12, EntityDataValueType.INT),
	PADDLE_TIME_LEFT(13, EntityDataValueType.FLOAT),
	PADDLE_TIME_RIGHT(14, EntityDataValueType.FLOAT),
	EXPERIENCE(15, EntityDataValueType.INT),
	MINECRAFT_DISPLAY_BLOCK(16, EntityDataValueType.INT),
	HORSE_FLAGS(16, EntityDataValueType.INT),
	/* 16 */
	MINECRAFT_DISPLAY_OFFSET(17, EntityDataValueType.INT),
	SHOOTER_ID(17, EntityDataValueType.LONG),
	MINECRAFT_HAS_DISPLAY(18, EntityDataValueType.BOOLEAN),
	HORSE_TYPE(19, EntityDataValueType.BYTE),
	/* 20 */
	/* 21*/
	CHARGE_AMOUNT(22, EntityDataValueType.BYTE),
	ENDERMAN_HELD_ITEM_ID(23, EntityDataValueType.SHORT),
	ENTITY_AGE(24, EntityDataValueType.SHORT),
	/* 25 */
	PLAYER_FLAGS(26, EntityDataValueType.BYTE),
	PLAYER_INDEX(27, EntityDataValueType.INT),
	PLAYER_BED_POSITION(28, EntityDataValueType.BLOCK_LOCATION),
	FIREBALL_POWER_X(29, EntityDataValueType.FLOAT),
	FIREBALL_POWER_Y(30, EntityDataValueType.FLOAT),
	FIREBALL_POWER_Z(31, EntityDataValueType.FLOAT),
	/* 32
	 * 33
	 * 34
	 * 35 */
	POTION_AUX_VALUE(36, EntityDataValueType.SHORT),
	LEAD_HOLDER_ID(37, EntityDataValueType.LONG),
	SCALE(38, EntityDataValueType.FLOAT),
	HAS_NPC_COMPONENT(39, EntityDataValueType.BYTE /* not sure */),
	SKIN_ID(40, EntityDataValueType.STRING),
	NPC_SKIN_ID(41, EntityDataValueType.STRING),
	URL_TAG(42, EntityDataValueType.STRING),
	MAX_AIR(43, EntityDataValueType.SHORT),
	MARK_VARIANT(44, EntityDataValueType.INT),
	CONTAINER_TYPE(45, EntityDataValueType.BYTE),
	CONTAINER_BASE_SIZE(46, EntityDataValueType.INT),
	CONTAINER_EXTRA_SLOTS_PER_STRENGTH(47, EntityDataValueType.INT),
	BLOCK_TARGET(48, EntityDataValueType.BLOCK_LOCATION),
	WITHER_INVULNERABLE_TICKS(49, EntityDataValueType.INT),
	WITHER_TARGET_HEAD_CENTER(50, EntityDataValueType.LONG),
	WITHER_TARGET_HEAD_LEFT(51, EntityDataValueType.LONG),
	WITHER_TARGET_HEAD_RIGHT(52, EntityDataValueType.LONG),
	/* 53 */
	BOUNDING_BOX_WIDTH(54, EntityDataValueType.FLOAT),
	BOUNDING_BOX_HEIGHT(55, EntityDataValueType.FLOAT),
	FUSE_LENGTH(56, EntityDataValueType.INT),
	RIDER_SEAT_POSITION(57, EntityDataValueType.LOCATION),
	RIDER_ROTATION_LOCKED(58, EntityDataValueType.BYTE),
	RIDER_MAX_ROTATION(59, EntityDataValueType.FLOAT),
	RIDER_MIN_ROTATION(60, EntityDataValueType.FLOAT),
	AREA_EFFECT_CLOUD_RADIUS(61, EntityDataValueType.FLOAT),
	AREA_EFFECT_CLOUD_WAITING(62, EntityDataValueType.INT),
	AREA_EFFECT_CLOUD_PARTICLE_ID(63, EntityDataValueType.INT),
	/* 64 */
	SHULKER_ATTACH_FACE(65, EntityDataValueType.BYTE),
	/* 66 */
	SHULKER_ATTACH_POS(67, EntityDataValueType.BLOCK_LOCATION),
	TRADING_PLAYER_EID(68, EntityDataValueType.LONG),
	/* 69
	 * 70
	 */
	COMMAND_BLOCK_COMMAND(71, EntityDataValueType.STRING),
	COMMAND_BLOCK_LAST_OUTPUT(72, EntityDataValueType.STRING),
	COMMAND_BLOCK_TRACK_OUTPUT(73, EntityDataValueType.BYTE),
	CONTROLLING_RIDER_SEAT_NUMBER(74, EntityDataValueType.BYTE),
	STRENGTH(75, EntityDataValueType.INT),
	MAX_STRENGTH(76, EntityDataValueType.INT),
	// TODO: add more
	FLAGS2(92, EntityDataValueType.LONG);
	// TODO: add more
	
    public static final int FLAG_ONFIRE = 0;
    public static final int FLAG_SNEAKING = 1;
    public static final int FLAG_RIDING = 2;
    public static final int FLAG_SPRINTING = 3;
    public static final int FLAG_ACTION = 4;
    public static final int FLAG_INVISIBLE = 5;
    public static final int FLAG_TEMPTED = 6;
    public static final int FLAG_INLOVE = 7;
    public static final int FLAG_SADDLED = 8;
    public static final int FLAG_POWERED = 9;
    public static final int FLAG_IGNITED = 10;
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
	public static final int FLAG_SPIN_ATTACK = 55;
	public static final int FLAG_SWIMMING = 56;
	public static final int FLAG_BRIBED = 57; //dolphins have this set when they go to find treasure for the player
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
	public static final int PLAYER_FLAG_DEAD = 2; //TODO: CHECK
    
	public final int id;
	public final EntityDataValueType valueType;
	
	private EntityDataType(int id, EntityDataValueType valueType){
		this.id = id;
		this.valueType = valueType;
	}
}