package de.marcely.pocketcraft.pocket.network.packet;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.network.Channel;

public enum PacketType {
	
	Batch((byte) 0xFFl),
	
	InLogin((byte) 0x01),
	OutLoginStatus((byte) 0x02, Channel.NONE),
	OutDisconnect((byte) 0x05, Channel.NONE),
	OutAvailableResourcePacks((byte) 0x06, Channel.NONE),
	OutAvailableResourcePacks2((byte) 0x07, Channel.NONE),
	InResourcePackStatus((byte) 0x08),
	OutText((byte) 0x09, Channel.CHAT),
	InText((byte) 0x09),
	OutWorldTime((byte) 0x0A, Channel.NONE),
	OutGame((byte) 0x0B, Channel.NONE),
	OutSpawnEntityPlayer((byte) 0x0C, Channel.ENTITY_HOSPITAL),
	OutSpawnEntity((byte) 0x0D, Channel.ENTITY_HOSPITAL),
	OutDestroyEntity((byte) 0x0E, Channel.ENTITY_HOSPITAL),
	OutSpawnEntityItem((byte) 0x0F, Channel.ENTITY_HOSPITAL),
	OutEntityMove((byte) 0x12, Channel.MOVEMENT),
	OutPlayerMove((byte) 0x13, Channel.MOVEMENT),
	InPlayerMove((byte) 0x13),
	OutBlockChange((byte) 0x15, Channel.BLOCKS),
	OutSpawnEntityPainting((byte) 0x16, Channel.ENTITY_HOSPITAL),
	OutExplosion((byte) 0x17, Channel.BLOCKS),
	OutWorldEvent((byte) 0x19, Channel.NONE),
	OutBlockEvent((byte) 0x1A, Channel.BLOCKS),
	OutEntityEvent((byte) 0x1B, Channel.NONE),
	OutEntityAttributes((byte) 0x1D, Channel.NONE),
	InInventoryAction((byte) 0x1E),
	OutEntityEquipment((byte) 0x1F, Channel.ENTITY_HOSPITAL),
	InEntityEquipment((byte) 0x1F),
	OutEntityArmor((byte) 0x20, Channel.ENTITY_HOSPITAL),
	InBlockPick((byte) 0x22),
	OutPlayerAction((byte) 0x24, Channel.NONE),
	InPlayerAction((byte) 0x24),
	OutEntityData((byte) 0x27, Channel.ENTITY_HOSPITAL),
	OutEntityVelocity((byte) 0x28, Channel.NONE),
	OutEntityAnimate((byte) 0x2C, Channel.NONE),
	InEntityAnimate((byte) 0x2C),
	OutRespawn((byte) 0x2D, Channel.NONE),
	OutContainerOpen((byte) 0x2E, Channel.NONE),
	OutContainerClose((byte) 0x2F, Channel.NONE),
	InContainerClose((byte) 0x2F),
	OutInventoryContent((byte) 0x31, Channel.NONE), 
	OutInventorySetItem((byte) 0x32, Channel.NONE),
	OutCraftingData((byte) 0x34, Channel.NONE),
	OutEntityPermissions((byte) 0x37, Channel.NONE),
	InEntityPermissions((byte) 0x37),
	OutBlockEntityData((byte) 0x38, Channel.BLOCKS),
	OutFullChunk((byte) 0x3A, Channel.WORLD_CHUNKS),
	OutGameDifficulty((byte) 0x3C, Channel.NONE),
	OutChangeDimension((byte) 0x3D, Channel.PRIORITY),
	OutGameMode((byte) 0x3E, Channel.NONE),
	OutPlayerList((byte) 0x3F, Channel.NONE),
	InChangeCheatSettings((byte) 0x40),
	InChunkRadiusChangeRequest((byte) 0x45),
	OutChunkRadiusChange((byte) 0x46, Channel.NONE),
	OutAvailableCommands((byte) 0x4C, Channel.CHAT),
	InCommandRequest((byte) 0x4D),
	OutChangeServer((byte) 0x55, Channel.PRIORITY),
	OutPlaySound((byte) 0x56, Channel.NONE),
	OutSetLocalPlayerInitialized((byte) 0x71, Channel.NONE),
	InSetLocalPlayerInitialized((byte) 0x71),
	OutAvailableEntityIdentifiers((byte) 0x77, Channel.NONE),
	OutBiomeDefinitionList((byte) 0x07A, Channel.NONE);
	
	public static final Map<Byte, PacketType> TYPES_IN = new HashMap<>();
	public static final Map<Byte, PacketType> TYPES_OUT = new HashMap<>();
	
	public final byte id;
	public @Nullable Channel channel;
	
	static {
		for(PacketType type:values()){
			if(type.channel != null)
				TYPES_OUT.put(type.id, type);
			else
				TYPES_IN.put(type.id, type);
		}
	}
	
	private PacketType(byte id){
		this.id = id;
	}
	
	private PacketType(byte id, Channel channel){
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
