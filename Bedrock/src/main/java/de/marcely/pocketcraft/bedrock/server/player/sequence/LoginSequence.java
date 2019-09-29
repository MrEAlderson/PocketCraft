package de.marcely.pocketcraft.bedrock.server.player.sequence;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInResourcePackStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks2;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutGame;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.player.Player;

public class LoginSequence extends Sequence {
	
	private static final byte STATE_WAITING = 0;
	private static final byte STATE_RESOURCE_PACK = 1;
	
	private byte state = STATE_WAITING;
	
	protected LoginSequence(Player player){
		super(player);
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		switch(state){
		case STATE_WAITING:
			handleWaiting(packet);
			break;
			
		case STATE_RESOURCE_PACK:
			handleResourcePack(packet);
			break;
		}
		
		return true;
	}
	
	private void handleWaiting(PCPacket rawPacket){
		if(rawPacket.type != PacketType.InLogin)
			return;
		
		// final PacketInLogin packet = (PacketInLogin) rawPacket;
		
		{
			final PacketOutLoginStatus out = (PacketOutLoginStatus) PacketType.OutLoginStatus.newInstance();
			
			out.result = PacketOutLoginStatus.SUCCESS;
			
			player.sendPacket(out);
		}
		
		{
			final PacketOutAvailableResourcePacks out = (PacketOutAvailableResourcePacks) PacketType.OutAvailableResourcePacks.newInstance();
			
			out.mustAccept = true;
			out.behaviourPacks = new ResourcePack[0];
			out.resourcePacks = new ResourcePack[0];
			
			player.sendPacket(out);
		}
		
		this.state = STATE_RESOURCE_PACK;
	}
	
	private void handleResourcePack(PCPacket rawPacket){
		if(rawPacket.type != PacketType.InResourcePackStatus)
			return;
		
		final PacketInResourcePackStatus packet = (PacketInResourcePackStatus) rawPacket;
		
		System.out.println(packet.status);
		
		if(packet.status == PacketInResourcePackStatus.HAVE_ALL_PACKS){
			{
				final PacketOutAvailableResourcePacks2 out = (PacketOutAvailableResourcePacks2) PacketType.OutAvailableResourcePacks2.newInstance();
				
				out.mustAccept = false;
				out.behaviourPacks = new ResourcePack[0];
				out.resourcePacks = new ResourcePack[0];
				
				player.sendPacket(out);
			}
		
		}else if(packet.status == PacketInResourcePackStatus.COMPLETED)
			sendGamePacket();
	}
	
	private void sendGamePacket(){
		final PacketOutGame out = (PacketOutGame) PacketType.OutGame.newInstance();
		
		out.entityUniqueId = 0;
		out.entityRuntimeId = 0;
		out.gamemode = GameMode.ADVENTURE;
		out.x = 0;
		out.y = 100;
		out.z = 0;
		out.yaw = 0F;
		out.pitch = 0F;
		out.seed = -1;
		out.dimension = (byte) (Dimension.OVERWORLD.getId() & 0xFF);
		out.generator = 1;
		out.worldGamemode = GameMode.ADVENTURE;
		out.difficulty = Difficulty.NORMAL;
		out.spawnX = 0;
		out.spawnY = 100;
		out.spawnZ = 0;
		out.hasAchievementsDisabled = true;
		out.time = 1; //-1 = not stopped, any positive value = stopped at that time
		out.eduMode = false;
		out.hasEduFeaturesEnabled = false;
		out.rainLevel = 0F;
		out.lightningLevel = 0F;
		out.hasConfirmedPlatformLockedContent = false;
		out.multiplayerGame = true;
		out.broadcastToLAN = true;
		out.xboxLiveBroadcastMode = 0;
		out.platformBroadcastMode = 0;
		out.commandsEnabled = true;
		out.isTexturePacksRequired = true;
		out.bonusChest = false;
		out.defaultPermissionLevel = PacketOutGame.PERMISSION_LEVEL_MEMBER;
		out.levelId = ""; // folder name in base64
		out.worldName = "";
		out.premiumWorldTemplateID = "";
		out.isTrial = false;
		out.currentTick = 0L;
		out.startWithMap = false;
		out.serverChunkTickRange = 4;
		out.hasLockedBehaviorPack = false;
		out.hasLockedResourcePack = false;
		out.isFromLockedWorldTemplate = false;
		out.useMsaGamertagsOnly = false;
		out.isFromWorldTemplate = false;
		out.isWorldTemplateOptionLocked = false;
		out.enchantmentSeed = 0;
		out.gameRules = GameRules.newDefaultInstance();
		out.multiplayerCorrelationID = UUID.randomUUID().toString();
		
		player.sendPacket(out);
	}
}