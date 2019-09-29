package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.network.Channel;

public enum PacketType {
	
	Batch(0xFE),
	
	InLogin(0x01),
	OutLoginStatus(0x02, Channel.NONE),
	OutDisconnect(0x05, Channel.NONE),
	OutAvailableResourcePacks(0x06, Channel.NONE),
	OutAvailableResourcePacks2(0x07, Channel.NONE),
	InResourcePackStatus(0x08),
	OutText(0x09, Channel.CHAT),
	InText(0x09),
	OutWorldTime(0x0A, Channel.NONE),
	OutGame(0x0B, Channel.NONE),
	OutSpawnEntityPlayer(0x0C, Channel.ENTITY_HOSPITAL),
	OutSpawnEntity(0x0D, Channel.ENTITY_HOSPITAL),
	OutDestroyEntity(0x0E, Channel.ENTITY_HOSPITAL),
	OutSpawnEntityItem(0x0F, Channel.ENTITY_HOSPITAL),
	OutEntityMove(0x12, Channel.MOVEMENT),
	OutPlayerMove(0x13, Channel.MOVEMENT),
	InPlayerMove(0x13),
	OutBlockChange(0x15, Channel.BLOCKS),
	OutSpawnEntityPainting(0x16, Channel.ENTITY_HOSPITAL),
	OutExplosion(0x17, Channel.BLOCKS),
	OutWorldEvent(0x19, Channel.NONE),
	OutBlockEvent(0x1A, Channel.BLOCKS),
	OutEntityEvent(0x1B, Channel.NONE),
	OutEntityAttributes(0x1D, Channel.NONE),
	InInventoryAction(0x1E),
	OutEntityEquipment(0x1F, Channel.ENTITY_HOSPITAL),
	InEntityEquipment(0x1F),
	OutEntityArmor(0x20, Channel.ENTITY_HOSPITAL),
	InBlockPick(0x22),
	OutPlayerAction(0x24, Channel.NONE),
	InPlayerAction(0x24),
	OutEntityData(0x27, Channel.ENTITY_HOSPITAL),
	OutEntityVelocity(0x28, Channel.NONE),
	OutEntityAnimate(0x2C, Channel.NONE),
	InEntityAnimate(0x2C),
	OutRespawn(0x2D, Channel.NONE),
	OutContainerOpen(0x2E, Channel.NONE),
	OutContainerClose(0x2F, Channel.NONE),
	InContainerClose(0x2F),
	OutInventoryContent(0x31, Channel.NONE), 
	OutInventorySetItem(0x32, Channel.NONE),
	OutCraftingData(0x34, Channel.NONE),
	OutEntityPermissions(0x37, Channel.NONE),
	InEntityPermissions(0x37),
	OutBlockEntityData(0x38, Channel.BLOCKS),
	OutFullChunk(0x3A, Channel.WORLD_CHUNKS),
	OutGameDifficulty(0x3C, Channel.NONE),
	OutChangeDimension(0x3D, Channel.PRIORITY),
	OutGameMode(0x3E, Channel.NONE),
	OutPlayerList(0x3F, Channel.NONE),
	InChangeCheatSettings(0x40),
	InChunkRadiusChangeRequest(0x45),
	OutChunkRadiusChange(0x46, Channel.NONE),
	OutAvailableCommands(0x4C, Channel.CHAT),
	InCommandRequest(0x4D),
	OutChangeServer(0x55, Channel.PRIORITY),
	OutPlaySound(0x56, Channel.NONE),
	OutSetLocalPlayerInitialized(0x71, Channel.NONE),
	InSetLocalPlayerInitialized(0x71),
	OutAvailableEntityIdentifiers(0x77, Channel.NONE),
	OutBiomeDefinitionList(0x07A, Channel.NONE),
	InClientCacheStatus(0x81);
	
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
		case InLogin:
			return new PacketInLogin();
		case OutLoginStatus:
			return new PacketOutLoginStatus();
		case OutDisconnect:
			return new PacketOutDisconnect();
		case OutAvailableResourcePacks:
			return new PacketOutAvailableResourcePacks();
		case OutAvailableResourcePacks2:
			return new PacketOutAvailableResourcePacks2();
		case InResourcePackStatus:
			return new PacketInResourcePackStatus();
		case OutText:
			return new PacketText(false);
		case InText:
			return new PacketText(true);
		case OutGame:
			return new PacketOutGame();
		case OutSpawnEntityPlayer:
			return new PacketOutSpawnEntityPlayer();
		case OutSpawnEntity:
			return new PacketOutSpawnEntity();
		case OutDestroyEntity:
			return new PacketOutDestroyEntity();
		case OutSpawnEntityItem:
			return new PacketOutSpawnEntityItem();
		case OutBlockEvent:
			return new PacketOutBlockEvent();
		case OutEntityEvent:
			return new PacketOutEntityEvent();
		case OutPlayerMove:
			return new PacketPlayerMove(false);
		case InPlayerMove:
			return new PacketPlayerMove(true);
		case OutBlockChange:
			return new PacketOutBlockChange();
		case OutSpawnEntityPainting:
			return new PacketOutSpawnEntityPainting();
		case OutExplosion:
			return new PacketOutExplosion();
		case OutEntityAttributes:
			return new PacketOutEntityAttributes();
		case InInventoryAction:
			return new PacketInInventoryAction();
		case OutEntityEquipment:
			return new PacketEntityEquipment(false);
		case InEntityEquipment:
			return new PacketEntityEquipment(true);
		case OutEntityArmor:
			return new PacketOutEntityArmor();
		case OutWorldEvent:
			return new PacketOutWorldEvent();
		case InBlockPick:
			return new PacketInBlockPick();
		case OutPlayerAction:
			return new PacketPlayerAction(false);
		case InPlayerAction:
			return new PacketPlayerAction(true);
		case OutEntityData:
			return new PacketOutEntityData();
		case OutEntityVelocity:
			return new PacketOutEntityVelocity();
		case OutEntityAnimate:
			return new PacketEntityAnimate(false);
		case InEntityAnimate:
			return new PacketEntityAnimate(true);
		case OutRespawn:
			return new PacketOutRespawn();
		case OutContainerOpen:
			return new PacketOutContainerOpen();
		case OutContainerClose:
			return new PacketContainerClose(false);
		case InContainerClose:
			return new PacketContainerClose(true);
		case OutInventoryContent:
			return new PacketOutInventoryContent();
		case OutInventorySetItem:
			return new PacketOutInventorySetItem();
		case OutCraftingData:
			return new PacketOutCraftingData();
		case OutEntityPermissions:
			return new PacketEntityPermissions(false);
		case InEntityPermissions:
			return new PacketEntityPermissions(true);
		case OutBlockEntityData:
			return new PacketOutBlockEntityData();
		case OutFullChunk:
			return new PacketOutFullChunk();
		case OutGameDifficulty:
			return new PacketOutGameDifficulty();
		case OutChangeDimension:
			return new PacketOutChangeDimension();
		case OutGameMode:
			return new PacketOutGameMode();
		case OutPlayerList:
			return new PacketOutPlayerList();
		case InChangeCheatSettings:
			return new PacketInChangeCheatSettings();
		case InChunkRadiusChangeRequest:
			return new PacketInChunkRadiusChangeRequest();
		case OutChunkRadiusChange:
			return new PacketOutChunkRadiusChange();
		case InCommandRequest:
			return new PacketInCommandRequest();
		case OutChangeServer:
			return new PacketOutChangeServer();
		case OutPlaySound:
			return new PacketOutPlaySound();
		case OutAvailableEntityIdentifiers:
			return new PacketOutAvailableEntityIdentifiers();
		case OutBiomeDefinitionList:
			return new PacketOutBiomeDefinitionList();
		case OutSetLocalPlayerInitialized:
			return new PacketSetLocalPlayerInitialized(false);
		case InSetLocalPlayerInitialized:
			return new PacketSetLocalPlayerInitialized(true);
		default:
			return null;
		}
	}
}
