package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.BlockMapping;
import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.component.ItemMapping;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketGame extends PCPacket {

	public static final byte PERMISSION_LEVEL_VISITOR = 0;
	public static final byte PERMISSION_LEVEL_MEMBER = 1;
	public static final byte PERMISSION_LEVEL_OPERATOR = 2;
	public static final byte PERMISSION_LEVEL_CUSTOM = 3;
	
	
    public long entityUniqueId;
    public long entityRuntimeId;
    public GameMode gamemode;
    public float x, y, z;
    public float yaw, pitch;
    
    public int seed;
    public byte dimension;
    public int generator;
    public GameMode worldGamemode;
    public Difficulty difficulty;
    public int spawnX, spawnY, spawnZ;
    public boolean hasAchievementsDisabled;
    public int time;
    public boolean eduMode;
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
    public GameRules gameRules;
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
    
    public String levelId;
    public String worldName;
    public String premiumWorldTemplateID;
    public boolean isTrial;
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
        writer.writeLFloat(this.yaw);
        writer.writeLFloat(this.pitch);
        
        writer.writeSignedVarInt(this.seed);
        writer.writeSignedVarInt(this.dimension);
        writer.writeSignedVarInt(this.generator);
        writer.writeSignedVarInt(this.worldGamemode.getId() & 0x03 /* */);
        writer.writeSignedVarInt(this.difficulty.getId());
        writer.writeBlockPosition(this.spawnX, this.spawnY, this.spawnZ);
        writer.writeBoolean(this.hasAchievementsDisabled);
        writer.writeSignedVarInt(this.time);
        writer.writeBoolean(this.eduMode);
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
        
        writer.writeString(this.levelId);
        writer.writeString(this.worldName);
        writer.writeString(this.premiumWorldTemplateID);
        writer.writeBoolean(this.isTrial);
        writer.writeLLong(this.currentTick);
        
        writer.writeSignedVarInt(this.enchantmentSeed);
        
        writer.writeUnsignedVarInt(BlockMapping.INSTANCE.getCachedTableSize());
        writer.write(BlockMapping.INSTANCE.getCachedTable());
        
        writer.writeUnsignedVarInt(ItemMapping.INSTANCE.cachedTableSize);
        writer.write(ItemMapping.INSTANCE.cachedTable);
        
        writer.writeString(this.multiplayerCorrelationID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
