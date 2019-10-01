package de.marcely.pocketcraft.bedrock.server.player.sequence;

import java.util.Random;

import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInLogin;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInResourcePackStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks2;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutChunkRadiusChange;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutGame;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutNetworkChunkPublisherUpdate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.player.Player;
import de.marcely.pocketcraft.bedrock.world.Chunk;

public class LoginSequence extends Sequence {
	
	private static final byte STATE_WAITING = 0;
	private static final byte STATE_RESOURCE_PACK = 1;
	
	private byte state = STATE_WAITING;
	
	protected LoginSequence(Player player){
		super(player);
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		System.out.println("lol:: " + packet.type);
		
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
		
		final PacketInLogin packet = (PacketInLogin) rawPacket;
		
		System.out.println("locale: " + packet.locale);
		System.out.println("id: " + packet.id);
		System.out.println("username: " + packet.username);
		
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
		
		}else if(packet.status == PacketInResourcePackStatus.COMPLETED){
			this.player.initEntity(new Random().nextInt());
			
			sendGamePacket();
			
			// done
			{
				final PacketOutLoginStatus out = (PacketOutLoginStatus) PacketType.OutLoginStatus.newInstance();
				
				out.result = PacketOutLoginStatus.PLAYER_SPAWN;
				
				player.sendPacket(out);
			}
			
			// test
			{
				final PacketOutChunkRadiusChange out = (PacketOutChunkRadiusChange) PacketType.OutChunkRadiusChange.newInstance();
				
				out.radius = 8;
				
				player.sendPacket(out);
			}
			
			// send chunks
			{
				final Chunk chunk = new Chunk();
				
				for(int ix=0; ix<16; ix++){
					for(int iy=0; iy<96; iy++){
						for(int iz=0; iz<16; iz++){
							chunk.setBlockId(ix, iy, iz, (short) 1);
						}
					}
				}
				
				for(int ix=-6; ix<=6; ix++){
					for(int iz=-6; iz<=6; iz++){
						player.sendPacket(chunk.buildPacket(ix, iz));
					}
				}
			}
			
			// tells the client that the chunks are ready to be displayed
			{
				final PacketOutNetworkChunkPublisherUpdate out = (PacketOutNetworkChunkPublisherUpdate) PacketType.OutNetworkChunkPublisherUpdate.newInstance();
				
				out.x = 0;
				out.y = 100;
				out.z = 0;
				out.radius = 6;
				
			    player.sendPacket(out);
			}
			
			player.getEntity().sendAllMetadata(player);
			
			// login done
			player.setSequence(Sequence.get(Sequence.PLAY, player));
		}
	}
	
	private void sendGamePacket(){
		final PacketOutGame out = (PacketOutGame) PacketType.OutGame.newInstance();
		
		out.entityUniqueId = this.player.getEntity().getId();
		out.entityRuntimeId = this.player.getEntity().getId();
		out.gamemode = GameMode.SURVIVAL;
		out.x = 0;
		out.y = 100;
		out.z = 0;
		out.yaw = 0F;
		out.pitch = 0F;
		out.seed = -1;
		out.dimension = (byte) (Dimension.OVERWORLD.getId());
		out.generator = 1;
		out.worldGamemode = GameMode.ADVENTURE;
		out.difficulty = Difficulty.NORMAL;
		out.spawnX = 0;
		out.spawnY = 100;
		out.spawnZ = 0;
		out.hasAchievementsDisabled = true;
		out.time = -1; //-1 = not stopped, any positive value = stopped at that time
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
		out.multiplayerCorrelationID = "";
		
		player.sendPacket(out);
	}
}