package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.BBlockMapping;
import de.marcely.pocketcraft.bedrock.component.BDifficulty;
import de.marcely.pocketcraft.bedrock.component.BGameMode;
import de.marcely.pocketcraft.bedrock.component.BGameRules;
import de.marcely.pocketcraft.bedrock.component.BItemMapping;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketGame extends PCPacket {

	public static final byte PERMISSION_LEVEL_VISITOR = 0;
	public static final byte PERMISSION_LEVEL_MEMBER = 1;
	public static final byte PERMISSION_LEVEL_OPERATOR = 2;
	public static final byte PERMISSION_LEVEL_CUSTOM = 3;
	
	public static final int GAME_PUBLISH_SETTING_NO_MULTI_PLAY = 0;
    public static final int GAME_PUBLISH_SETTING_INVITE_ONLY = 1;
    public static final int GAME_PUBLISH_SETTING_FRIENDS_ONLY = 2;
    public static final int GAME_PUBLISH_SETTING_FRIENDS_OF_FRIENDS = 3;
    public static final int GAME_PUBLISH_SETTING_PUBLIC = 4;
	
    public long entityUniqueId;
    public long entityRuntimeId;
    public BGameMode gamemode;
    public float x, y, z;
    public float yaw, pitch;
    
    public int seed;
    public byte dimension;
    public int generator;
    public BGameMode worldGamemode;
    public BDifficulty difficulty;
    public int spawnX, spawnY, spawnZ;
    public boolean hasAchievementsDisabled;
    public int time;
    public int eduEditionOffer;
    public boolean hasEduFeaturesEnabled;
    public float rainLevel;
    public float lightningLevel;
    public boolean hasConfirmedPlatformLockedContent;
    public boolean multiplayerGame;
    public boolean broadcastToLAN;
    public int xboxLiveBroadcastMode;
    public int platformBroadcastMode;
    public boolean commandsEnabled;
    public boolean isTexturePacksRequired;
    public BGameRules gameRules;
    public boolean bonusChest;
    public boolean startWithMap;
    public int defaultPermissionLevel;
    public int serverChunkTickRange;
    public boolean hasLockedBehaviorPack;
    public boolean hasLockedResourcePack;
    public boolean isFromLockedWorldTemplate;
    public boolean useMsaGamertagsOnly;
    public boolean isFromWorldTemplate;
    public boolean isWorldTemplateOptionLocked;
    public boolean isOnlySpawningV1Villagers;
    
    public String vanillaVersion;
    public String levelId;
    public String worldName;
    public String premiumWorldTemplateID;
    public boolean isTrial;
    public boolean isMovementServerAuthoritative;
    public long currentTick;
    public int enchantmentSeed;
	public String multiplayerCorrelationID;
    
	public PacketGame(){
		super(PacketType.Game);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(this.entityUniqueId);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
        
		writer.writeSignedVarInt(this.gamemode.getId() & 0x03 /* */);
        
        writer.writeVector(this.x, this.y, this.z);
        writer.writeLFloat(this.pitch);
        writer.writeLFloat(this.yaw);
        
        writer.writeSignedVarInt(this.seed);
        writer.writeSignedVarInt(this.dimension);
        writer.writeSignedVarInt(this.generator);
        writer.writeSignedVarInt(this.worldGamemode.getId() & 0x03 /* */);
        writer.writeSignedVarInt(this.difficulty.getId());
        writer.writeBlockPosition(this.spawnX, this.spawnY, this.spawnZ);
        writer.writeBoolean(this.hasAchievementsDisabled);
        writer.writeSignedVarInt(this.time);
        writer.writeSignedVarInt(this.eduEditionOffer);
        writer.writeBoolean(this.hasEduFeaturesEnabled);
        writer.writeLFloat(this.rainLevel);
        writer.writeLFloat(this.lightningLevel);
        writer.writeBoolean(this.hasConfirmedPlatformLockedContent);
        writer.writeBoolean(this.multiplayerGame);
        writer.writeBoolean(this.broadcastToLAN);
        writer.writeSignedVarInt(this.xboxLiveBroadcastMode);
        writer.writeSignedVarInt(this.platformBroadcastMode);
        writer.writeBoolean(this.commandsEnabled);
        writer.writeBoolean(this.isTexturePacksRequired);
        this.gameRules.write(writer); //
        writer.writeBoolean(this.bonusChest);
        writer.writeBoolean(this.startWithMap);
        writer.writeSignedVarInt(this.defaultPermissionLevel); //
        writer.writeLInt(this.serverChunkTickRange);
        writer.writeBoolean(this.hasLockedBehaviorPack);
        writer.writeBoolean(this.hasLockedResourcePack);
        writer.writeBoolean(this.isFromLockedWorldTemplate);
        writer.writeBoolean(this.useMsaGamertagsOnly);
        writer.writeBoolean(this.isFromWorldTemplate);
        writer.writeBoolean(this.isWorldTemplateOptionLocked);
        writer.writeBoolean(this.isOnlySpawningV1Villagers);
        
        writer.writeString(this.vanillaVersion);
        writer.writeString(this.levelId);
        writer.writeString(this.worldName);
        writer.writeString(this.premiumWorldTemplateID);
        writer.writeBoolean(this.isTrial);
        writer.writeBoolean(this.isMovementServerAuthoritative);
        writer.writeLLong(this.currentTick);
        
        writer.writeSignedVarInt(this.enchantmentSeed);
        
        writer.write(BBlockMapping.INSTANCE.getPalette());
        
        writer.writeUnsignedVarInt(BItemMapping.INSTANCE.cachedTableSize);
        writer.write(BItemMapping.INSTANCE.cachedTable);
        
        writer.writeString(this.multiplayerCorrelationID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
