package de.marcely.pocketcraft.bedrock.server.player.sequence;

import java.util.Random;

import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityPermissions;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityPermissions.CommandPermissionLevel;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityPermissions.PermissionLevel;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInLogin;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInResourcePackStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutAvailableResourcePacks2;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutChunkRadiusChange;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutEntityAttributes;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutGame;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutGameMode;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutNetworkChunkPublisherUpdate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove.PlayerMoveType;
import de.marcely.pocketcraft.bedrock.server.player.Player;
import de.marcely.pocketcraft.bedrock.world.Chunk;
import de.marcely.pocketcraft.bedrock.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.world.entity.EntityAttributeType;

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
			
			// test
			{
				final PacketOutEntityAttributes out = (PacketOutEntityAttributes) PacketType.OutEntityAttributes.newInstance();
				
				out.entityRuntimeID = this.player.getEntity().getId();
				out.attributes = new EntityAttribute[]{
						new EntityAttribute(EntityAttributeType.HEALTH, EntityAttributeType.HEALTH.defaultValue),
						new EntityAttribute(EntityAttributeType.FOOD, EntityAttributeType.FOOD.defaultValue),
						new EntityAttribute(EntityAttributeType.MOVEMENT_SPEED, EntityAttributeType.MOVEMENT_SPEED.defaultValue),
						new EntityAttribute(EntityAttributeType.EXPERIENCE_LEVEL, EntityAttributeType.EXPERIENCE_LEVEL.defaultValue),
						new EntityAttribute(EntityAttributeType.EXPERIENCE, EntityAttributeType.EXPERIENCE.defaultValue),
						new EntityAttribute(EntityAttributeType.ABSORPTION, EntityAttributeType.ABSORPTION.defaultValue),
						new EntityAttribute(EntityAttributeType.ATTACK_DAMAGE, EntityAttributeType.ATTACK_DAMAGE.defaultValue),
						new EntityAttribute(EntityAttributeType.EXHAUSTION, EntityAttributeType.EXHAUSTION.defaultValue),
						new EntityAttribute(EntityAttributeType.FOLLOW_RANGE, EntityAttributeType.FOLLOW_RANGE.defaultValue),
						new EntityAttribute(EntityAttributeType.KNOCKBACK_RESISTANCE, EntityAttributeType.KNOCKBACK_RESISTANCE.defaultValue),
						new EntityAttribute(EntityAttributeType.SATURATION, EntityAttributeType.SATURATION.defaultValue)
					};
				
				player.sendPacket(out);
			}
			
			// test
			{
				final PacketEntityPermissions out = (PacketEntityPermissions) PacketType.OutEntityPermissions.newInstance();
				
				out.entityUID = this.player.getEntity().getId();
				out.permLevel = PermissionLevel.MEMBER;
				out.cmdPermLevel = CommandPermissionLevel.NORMAL;
				
				//if(this.gamemode == PGameMode.SPECTATOR){
				//	packet.out |= PacketEntityPermissions.FLAG1_WORLD_IMMUTABLE;
				//	packet.out |= PacketEntityPermissions.FLAG1_NO_PVP;
				//	packet.out |= PacketEntityPermissions.FLAG1_NO_CLIP;		
				//}
				
				player.sendPacket(out);
			}
			
			// done
			{
				final PacketOutLoginStatus out = (PacketOutLoginStatus) PacketType.OutLoginStatus.newInstance();
				
				out.result = PacketOutLoginStatus.PLAYER_SPAWN;
				
				player.sendPacket(out);
			}
			
			// test
			{
				final PacketOutGameMode out = (PacketOutGameMode) PacketType.OutGameMode.newInstance();
				
				out.mode = GameMode.CREATIVE;
				
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
			
			player.getEntity().sendAllMetadata(player);
			
			// tells the client that the chunks are ready to be displayed
			{
				final PacketOutNetworkChunkPublisherUpdate out = (PacketOutNetworkChunkPublisherUpdate) PacketType.OutNetworkChunkPublisherUpdate.newInstance();
				
				out.x = 0;
				out.y = 100;
				out.z = 0;
				out.radius = 6;
				
			    player.sendPacket(out);
			}
			
			// test
			{
				final PacketPlayerMove move = (PacketPlayerMove) PacketType.OutPlayerMove.newInstance();
				
				move.entityRuntimeID = player.getEntity().getId();
				move.posX = 10;
				move.posY = 99;
				move.posZ = 00;
				move.mode = PlayerMoveType.TELEPORT;
				move.onGround = false;
				
				player.sendPacket(move);
			}
			
			// login done
			player.setSequence(Sequence.get(Sequence.PLAY, player));
		
			player.getServer().getListeners().forEach(listener -> listener.onConnect(player));
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