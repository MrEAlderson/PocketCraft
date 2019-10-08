package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.GameRule;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAttributes;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGame;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameRules;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.world.entity.EntityAttributeType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientSettings;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayLogin;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.DifficultyTranslator;
import de.marcely.pocketcraft.translate.component.DimensionTranslator;
import de.marcely.pocketcraft.translate.component.GameModeTranslator;

public class TV8D9PacketPlayLogin extends JavaPacketTranslator<V8D9PacketPlayLogin> {

	@Override
	public void handle(V8D9PacketPlayLogin packet, Player player){
		// bedrock login
		{
			player.getBedrock().initEntity(packet.entityId);
			player.setSpawning(true);
			player.getWorld().setDimension(DimensionTranslator.toBedrock(packet.dimension));
			
			sendGamePacket(packet, player);
			
			// test
			{
				final PacketEntityAttributes out = (PacketEntityAttributes) PacketType.EntityAttributes.newInstance();
				
				out.entityRuntimeID = player.getEntityId();
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
			
			// game rules
			{
				final PacketGameRules out = new PacketGameRules();
				final GameRules rules = new GameRules();
				
				{
					rules.setValue(GameRule.DO_DAYLIGHT_CYCLE, false);
					rules.setValue(GameRule.DO_WEATHER_CYCLE, false);
				}
				
				out.gameRules = rules;
				
				player.sendPacket(out);
			}
			
			player.getBedrock().getEntity().sendAllMetadata(player.getBedrock());
		}
		
		// java login
		{
			{
				final V8D9PacketPlayClientSettings out = new V8D9PacketPlayClientSettings();
				
				out.locale = "en/EN";
				out.viewDistance = 0 /* server doesnt care */;
				out.chatMode = V8D9PacketPlayClientSettings.CHAT_ENABLED;
				out.chatColorsEnabled = true;
				out.capeEnabled = true;
				out.jacketEnabled = true;
				out.leftSleeveEnabled = true;
				out.rightSleeveEnabled = true;
				out.leftPantsEnabled = true;
				out.rightPantsEnabled = true;
				out.hatEnabled = true;
				
				player.sendPacket(out);
			}
		}
	}
	
	private void sendGamePacket(V8D9PacketPlayLogin packet, Player player){
		final PacketGame out = (PacketGame) PacketType.Game.newInstance();
		
		out.entityUniqueId = player.getEntityId();
		out.entityRuntimeId = player.getEntityId();
		out.gamemode = out.worldGamemode = GameModeTranslator.toBedrock(packet.gamemode);
		out.x = 0;
		out.y = 100;
		out.z = 0;
		out.yaw = 0F;
		out.pitch = 0F;
		out.seed = -1;
		out.dimension = (byte) (DimensionTranslator.toBedrock(packet.dimension).getId());
		/* 0 = old
		 * 1 = infinite
		 * 2 = flat */
		out.generator = 1;
		out.difficulty = DifficultyTranslator.toBedrock(packet.difficulty);
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
		out.defaultPermissionLevel = PacketGame.PERMISSION_LEVEL_MEMBER;
		out.levelId = ""; // folder name in base64
		out.worldName = "";
		out.premiumWorldTemplateID = "";
		out.isTrial = false;
		out.currentTick = 0L;
		out.startWithMap = false;
		out.serverChunkTickRange = 8;
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
