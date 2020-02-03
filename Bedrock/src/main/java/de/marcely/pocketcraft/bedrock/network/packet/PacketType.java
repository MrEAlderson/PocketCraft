package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.network.Channel;
import lombok.Getter;

public enum PacketType {
	
	Batch(0xFE, Channel.NONE, PacketBatch.class),
	
	Login(0x01, Channel.NONE, PacketLogin.class),
	LoginStatus(0x02, Channel.NONE, PacketLoginStatus.class),
	Disconnect(0x05, Channel.NONE, PacketDisconnect.class),
	AvailableResourcePacks(0x06, Channel.NONE, PacketAvailableResourcePacks.class),
	AvailableResourcePacks2(0x07, Channel.NONE, PacketAvailableResourcePacks2.class),
	ResourcePackStatus(0x08, Channel.NONE, PacketResourcePackStatus.class),
	Text(0x09, Channel.CHAT, PacketText.class),
	WorldTime(0x0A, Channel.WORLD_EVENTS, PacketWorldTime.class),
	Game(0x0B, Channel.NONE, PacketGame.class),
	SpawnEntityPlayer(0x0C, Channel.ENTITY, PacketSpawnEntityPlayer.class),
	SpawnEntity(0x0D, Channel.ENTITY, PacketSpawnEntity.class),
	DestroyEntity(0x0E, Channel.ENTITY, PacketDestroyEntity.class),
	SpawnEntityItem(0x0F, Channel.ENTITY, PacketSpawnEntityItem.class),
	CollectItem(0x11, Channel.ENTITY, PacketCollectItem.class),
	EntityMove(0x12, Channel.MOVEMENT, PacketEntityMove.class),
	PlayerMove(0x13, Channel.MOVEMENT, PacketPlayerMove.class),
	BlockChange(0x15, Channel.BLOCKS, PacketBlockChange.class),
	SpawnEntityPainting(0x16, Channel.ENTITY, PacketSpawnEntityPainting.class),
	Explosion(0x17, Channel.BLOCKS, PacketExplosion.class), // TICK_SYNC_PACKET
	WorldEvent(0x19, Channel.WORLD_EVENTS, PacketWorldEvent.class),
	BlockEvent(0x1A, Channel.BLOCKS, PacketBlockEvent.class),
	EntityEvent(0x1B, Channel.ENTITY, PacketEntityEvent.class),
	EntityAttributes(0x1D, Channel.ENTITY, PacketEntityAttributes.class),
	InventoryAction(0x1E, Channel.INVENTORY, PacketInventoryAction.class),
	EntityEquipment(0x1F, Channel.ENTITY, PacketEntityEquipment.class),
	EntityArmor(0x20, Channel.ENTITY, PacketEntityArmor.class),
	INTERACT(0x21, Channel.NONE, PacketInteract.class),
	BlockPick(0x22, Channel.BLOCKS, PacketBlockPick.class),
	PlayerAction(0x24, Channel.NONE, PacketPlayerAction.class),
	EntityData(0x27, Channel.ENTITY, PacketEntityData.class),
	EntityVelocity(0x28, Channel.ENTITY, PacketEntityVelocity.class),
	EntitySetLink(0x29, Channel.ENTITY, PacketEntitySetLink.class),
	EntityAnimate(0x2C, Channel.ENTITY, PacketEntityAnimate.class),
	Respawn(0x2D, Channel.PRIORITY, PacketRespawn.class),
	ContainerOpen(0x2E, Channel.INVENTORY, PacketContainerOpen.class),
	ContainerClose(0x2F, Channel.INVENTORY, PacketContainerClose.class),
	InventoryContent(0x31, Channel.INVENTORY, PacketInventoryContent.class), 
	InventorySetItem(0x32, Channel.INVENTORY, PacketInventorySetItem.class),
	CraftingData(0x34, Channel.NONE, PacketCraftingData.class),
	PlayerPermissions(0x37, Channel.NONE, PacketPlayerPermissions.class),
	BlockEntityData(0x38, Channel.BLOCKS, PacketBlockEntityData.class),
	FullChunk(0x3A, Channel.WORLD_CHUNKS, PacketFullChunk.class),
	GameDifficulty(0x3C, Channel.NONE, PacketGameDifficulty.class),
	ChangeDimension(0x3D, Channel.PRIORITY, PacketChangeDimension.class),
	GameMode(0x3E, Channel.NONE, PacketGameMode.class),
	PlayerList(0x3F, Channel.NONE, PacketPlayerList.class),
	ChangeCheatSettings(0x40, Channel.NONE, PacketChangeCheatSettings.class),
	ChunkRadiusChangeRequest(0x45, Channel.WORLD_CHUNKS, PacketChunkRadiusChangeRequest.class),
	ChunkRadiusChange(0x46, Channel.WORLD_CHUNKS, PacketChunkRadiusChange.class),
	GameRules(0x48, Channel.NONE, PacketGameRules.class),
	ShowCredits(0x4B, Channel.NONE, PacketShowCredits.class),
	AvailableCommands(0x4C, Channel.CHAT, PacketAvailableCommands.class),
	CommandRequest(0x4D, Channel.CHAT, PacketCommandRequest.class),
	ChangeServer(0x55, Channel.PRIORITY, PacketChangeServer.class),
	PlaySound(0x56, Channel.WORLD_EVENTS, PacketPlaySound.class),
	ScoreboardObjectiveRemove(0x6A, Channel.NONE, PacketScoreboardObjectiveRemove.class),
	ScoreboardDisplay(0x6B, Channel.NONE, PacketScoreboardDisplay.class),
	ScoreboardSetScore(0x6C, Channel.NONE, PacketScoreboardSetScore.class),
	EntityRelMove(0x6F, Channel.ENTITY, PacketEntityRelMove.class),
	SetLocalPlayerInitialized(0x71, Channel.NONE, PacketSetLocalPlayerInitialized.class),
	AvailableEntityIdentifiers(0x77, Channel.NONE, PacketAvailableEntityIdentifiers.class),
	NetworkChunkPublisherUpdate(0x79, Channel.WORLD_CHUNKS, PacketNetworkChunkPublisherUpdate.class),
	BiomeDefinitionList(0x07A, Channel.NONE, PacketBiomeDefinitionList.class),
	ClientCacheStatus(0x81, Channel.NONE, PacketClientCacheStatus.class);
	
	public static final Map<Short, PacketType> TYPES = new HashMap<>();
	
	@Getter private final short id;
	@Getter private Channel channel;
	private final Class<? extends PCPacket> clazz;
	
	private PacketProperties properties = new PacketProperties();
	
	static {
		for(PacketType type:values())
			TYPES.put(type.id, type);
	}
	
	private PacketType(int id, Channel channel, Class<? extends PCPacket> clazz){
		this.id = (short) id;
		this.channel = channel;
		this.clazz = clazz;
	}
	
	public PCPacket newInstance(){
		try{
			return this.clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Failed to create new instance of packet");
		}
	}
	
	/**
	 * 
	 * @deprecated Do NOT use this method since it'll be removed in the future! Use Packet#getProperties instead
	 */
	@Deprecated
	public PacketProperties getProperties(){
		return this.properties;
	}
}
