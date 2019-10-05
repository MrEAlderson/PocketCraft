package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketWorldEvent extends PCPacket {
	
    public static final int TYPE_SOUND_CLICK = 1000;
    public static final int TYPE_SOUND_CLICK_FAIL = 1001;
    public static final int TYPE_SOUND_SHOOT = 1002;
    public static final int TYPE_SOUND_DOOR = 1003;
    public static final int TYPE_SOUND_FIZZ = 1004;
    public static final int TYPE_SOUND_TNT = 1005;
	
    public static final int TYPE_SOUND_GHAST = 1007;
    public static final int TYPE_SOUND_BLAZE_SHOOT = 1008;
    public static final int TYPE_SOUND_GHAST_SHOOT = 1009;
    public static final int TYPE_SOUND_DOOR_BUMP = 1010;
    public static final int TYPE_SOUND_DOOR_CRASH = 1012;
    
    public static final int TYPE_SOUND_ENDERMAN_TELEPORT = 1018;
    
    public static final int TYPE_SOUND_ANVIL_BREAK = 1020;
    public static final int TYPE_SOUND_ANVIL_USE = 1021;
    public static final int TYPE_SOUND_ANVIL_FALL = 1022;

    public static final int TYPE_SOUND_ITEM_DROP = 1030;
    public static final int TYPE_SOUND_ITEM_THROWN = 1031;

    public static final int TYPE_SOUND_PORTAL = 1032;

    public static final int TYPE_SOUND_ITEM_FRAME_ITEM_ADDED = 1040;
    public static final int TYPE_SOUND_ITEM_FRAME_PLACED = 1041;
    public static final int TYPE_SOUND_ITEM_FRAME_REMOVED = 1042;
    public static final int TYPE_SOUND_ITEM_FRAME_ITEM_REMOVED = 1043;
    public static final int TYPE_SOUND_ITEM_FRAME_ITEM_ROTATED = 1044;

    public static final int TYPE_SOUND_CAMERA_TAKE_PICTURE = 1050;
    public static final int TYPE_SOUND_EXPERIENCE_ORB = 1051;
    public static final int TYPE_SOUND_TOTEM = 1052;

    public static final int TYPE_SOUND_ARMOR_STAND_BREAK = 1060;
    public static final int TYPE_SOUND_ARMOR_STAND_HIT = 1061;
    public static final int TYPE_SOUND_ARMOR_STAND_FALL = 1062;
    public static final int TYPE_SOUND_ARMOR_STAND_PLACE = 1063;

    public static final int TYPE_PARTICLE_SHOOT = 2000;
    public static final int TYPE_PARTICLE_DESTROY = 2001;
    public static final int TYPE_PARTICLE_SPLASH = 2002;
    public static final int TYPE_PARTICLE_EYE_DESPAWN = 2003;
    public static final int TYPE_PARTICLE_SPAWN = 2004;
    public static final int TYPE_PARTICLE_BONEMEAL = 2005;
    
    public static final int TYPE_GUARDIAN_CURSE = 2006;

    public static final int TYPE_PARTICLE_BLOCK_FORCE_FIELD = 2008;

    public static final int TYPE_PARTICLE_PUNCH_BLOCK = 2014;
    
    public static final int TYPE_WEATHER_RAIN_START = 3001;
    public static final int TYPE_WEATHER_THUNDER_START = 3002;
    public static final int TYPE_WEATHER_RAIN_STOP = 3003;
    public static final int TYPE_WEATHER_THUNDER_STOP = 3004;
    
    public static final int TYPE_SOUND_BUTTON_CLICK = 3500;
    public static final int TYPE_SOUND_EXPLODE = 3501;
    public static final int TYPE_CAULDRON_DYE_ARMOR = 3502;
    public static final int TYPE_CAULDRON_CLEAN_ARMOR = 3503;
    public static final int TYPE_CAULDRON_FILL_POTION = 3504;
    public static final int TYPE_CAULDRON_TAKE_POTION = 3505;
    public static final int TYPE_SOUND_SPLASH = 3506;
    
    public static final int TYPE_CAULDRON_TAKE_WATER = 3507;
    public static final int TYPE_CAULDRON_ADD_DYE = 3508;
    public static final int TYPE_CAULDRON_CLEAN_BANNER = 3509;
	
    public static final int TYPE_BLOCK_START_BREAK = 3600;
    public static final int TYPE_BLOCK_STOP_BREAK = 3601;

    public static final int TYPE_SET_DATA = 4000;

    public static final int TYPE_PLAYERS_SLEEPING = 9800;

    public static final int TYPE_ADD_PARTICLE_MASK = 0x4000;
    
    
	public int type, data = 0;
	public float posX = 0, posY = 0, posZ = 0;
	
	public PacketWorldEvent(){
		super(PacketType.WorldEvent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.type);
		writer.writeVector(this.posX, this.posY, this.posZ);
		writer.writeSignedVarInt(this.data);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
