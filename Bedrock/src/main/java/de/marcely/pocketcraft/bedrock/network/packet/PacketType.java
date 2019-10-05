package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.network.Channel;

public enum PacketType {
	
	Batch(0xFE),
	
	Login(0x01),
	LoginStatus(0x02, Channel.NONE),
	Disconnect(0x05, Channel.NONE),
	AvailableResourcePacks(0x06, Channel.NONE),
	AvailableResourcePacks2(0x07, Channel.NONE),
	ResourcePackStatus(0x08),
	Text(0x09, Channel.CHAT),
	WorldTime(0x0A, Channel.NONE),
	Game(0x0B, Channel.NONE),
	SpawnEntityPlayer(0x0C, Channel.ENTITY_HOSPITAL),
	SpawnEntity(0x0D, Channel.ENTITY_HOSPITAL),
	DestroyEntity(0x0E, Channel.ENTITY_HOSPITAL),
	SpawnEntityItem(0x0F, Channel.ENTITY_HOSPITAL),
	EntityMove(0x12, Channel.MOVEMENT),
	PlayerMove(0x13, Channel.MOVEMENT),
	BlockChange(0x15, Channel.BLOCKS),
	SpawnEntityPainting(0x16, Channel.ENTITY_HOSPITAL),
	Explosion(0x17, Channel.BLOCKS),
	WorldEvent(0x19, Channel.NONE),
	BlockEvent(0x1A, Channel.BLOCKS),
	EntityEvent(0x1B, Channel.NONE),
	EntityAttributes(0x1D, Channel.NONE),
	InventoryAction(0x1E),
	EntityEquipment(0x1F, Channel.ENTITY_HOSPITAL),
	EntityArmor(0x20, Channel.ENTITY_HOSPITAL),
	BlockPick(0x22),
	PlayerAction(0x24, Channel.NONE),
	EntityData(0x27, Channel.ENTITY_HOSPITAL),
	EntityVelocity(0x28, Channel.NONE),
	EntityAnimate(0x2C, Channel.NONE),
	Respawn(0x2D, Channel.NONE),
	ContainerOpen(0x2E, Channel.NONE),
	ContainerClose(0x2F, Channel.NONE),
	InventoryContent(0x31, Channel.NONE), 
	InventorySetItem(0x32, Channel.NONE),
	CraftingData(0x34, Channel.NONE),
	EntityPermissions(0x37, Channel.NONE),
	BlockEntityData(0x38, Channel.BLOCKS),
	FullChunk(0x3A, Channel.WORLD_CHUNKS),
	GameDifficulty(0x3C, Channel.NONE),
	ChangeDimension(0x3D, Channel.PRIORITY),
	GameMode(0x3E, Channel.NONE),
	PlayerList(0x3F, Channel.NONE),
	ChangeCheatSettings(0x40),
	ChunkRadiusChangeRequest(0x45),
	ChunkRadiusChange(0x46, Channel.NONE),
	GameRules(0x48, Channel.NONE),
	AvailableCommands(0x4C, Channel.CHAT),
	CommandRequest(0x4D),
	ChangeServer(0x55, Channel.PRIORITY),
	PlaySound(0x56, Channel.NONE),
	SetLocalPlayerInitialized(0x71, Channel.NONE),
	AvailableEntityIdentifiers(0x77, Channel.NONE),
	NetworkChunkPublisherUpdate(0x79, Channel.NONE),
	BiomeDefinitionList(0x07A, Channel.NONE),
	ClientCacheStatus(0x81);
	
	public static final Map<Short, PacketType> TYPES_IN = new HashMap<>();
	public static final Map<Short, PacketType> TYPES_OUT = new HashMap<>();
	
	public final short id;
	public @Nullable Channel channel;
	
	static {
		for(PacketType type:values()){
			if(type.channel != null)
				TYPES_OUT.put(type.id, type);
			else
				TYPES_IN.put(type.id, type);
		}
	}
	
	private PacketType(int id){
		this.id = (short) id;
	}
	
	private PacketType(int id, Channel channel){
		this(id);
		
		this.channel = channel;
	}
	
	public PCPacket newInstance(){
		switch(this){
		case Batch:
			return new PacketBatch();
		case Login:
			return new PacketInLogin();
		case LoginStatus:
			return new PacketLoginStatus();
		case Disconnect:
			return new PacketDisconnect();
		case AvailableResourcePacks:
			return new PacketAvailableResourcePacks();
		case AvailableResourcePacks2:
			return new PacketAvailableResourcePacks2();
		case ResourcePackStatus:
			return new PacketResourcePackStatus();
		case Text:
			return new PacketText();
		case Game:
			return new PacketGame();
		case SpawnEntityPlayer:
			return new PacketSpawnEntityPlayer();
		case SpawnEntity:
			return new PacketSpawnEntity();
		case DestroyEntity:
			return new PacketDestroyEntity();
		case SpawnEntityItem:
			return new PacketSpawnEntityItem();
		case BlockEvent:
			return new PacketBlockEvent();
		case EntityEvent:
			return new PacketEntityEvent();
		case PlayerMove:
			return new PacketPlayerMove();
		case BlockChange:
			return new PacketBlockChange();
		case SpawnEntityPainting:
			return new PacketSpawnEntityPainting();
		case Explosion:
			return new PacketExplosion();
		case EntityAttributes:
			return new PacketEntityAttributes();
		case InventoryAction:
			return new PacketInventoryAction();
		case EntityEquipment:
			return new PacketEntityEquipment();
		case EntityArmor:
			return new PacketEntityArmor();
		case WorldEvent:
			return new PacketWorldEvent();
		case BlockPick:
			return new PacketBlockPick();
		case PlayerAction:
			return new PacketPlayerAction();
		case EntityData:
			return new PacketEntityData();
		case EntityVelocity:
			return new PacketEntityVelocity();
		case EntityAnimate:
			return new PacketEntityAnimate();
		case Respawn:
			return new PacketRespawn();
		case ContainerOpen:
			return new PacketContainerOpen();
		case ContainerClose:
			return new PacketContainerClose();
		case InventoryContent:
			return new PacketInventoryContent();
		case InventorySetItem:
			return new PacketInventorySetItem();
		case CraftingData:
			return new PacketCraftingData();
		case EntityPermissions:
			return new PacketEntityPermissions();
		case BlockEntityData:
			return new PacketBlockEntityData();
		case FullChunk:
			return new PacketFullChunk();
		case GameDifficulty:
			return new PacketGameDifficulty();
		case ChangeDimension:
			return new PacketChangeDimension();
		case GameMode:
			return new PacketGameMode();
		case PlayerList:
			return new PacketPlayerList();
		case ChangeCheatSettings:
			return new PacketChangeCheatSettings();
		case ChunkRadiusChangeRequest:
			return new PacketChunkRadiusChangeRequest();
		case ChunkRadiusChange:
			return new PacketChunkRadiusChange();
		case GameRules:
			return new PacketGameRules();
		case AvailableCommands:
			return new PacketAvailableCommands();
		case CommandRequest:
			return new PacketCommandRequest();
		case ChangeServer:
			return new PacketChangeServer();
		case PlaySound:
			return new PacketPlaySound();
		case SetLocalPlayerInitialized:
			return new PacketSetLocalPlayerInitialized();
		case AvailableEntityIdentifiers:
			return new PacketAvailableEntityIdentifiers();
		case NetworkChunkPublisherUpdate:
			return new PacketNetworkChunkPublisherUpdate();
		case BiomeDefinitionList:
			return new PacketBiomeDefinitionList();
		case ClientCacheStatus:
			return new PacketClientCacheStatusPacket();
		default:
			return null;
		}
	}
}
