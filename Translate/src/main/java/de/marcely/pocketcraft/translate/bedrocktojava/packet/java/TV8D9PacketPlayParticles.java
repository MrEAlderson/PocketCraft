package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.ParticleType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayParticles;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.Util;

public class TV8D9PacketPlayParticles extends JavaPacketTranslator<V8D9PacketPlayParticles> {

	@Override
	public void handle(V8D9PacketPlayParticles packet, Player player){
		if(packet.amount == 0){
			packet.offsetX = packet.data * packet.offsetX;
			packet.offsetY = packet.data * packet.offsetY;
			packet.offsetZ = packet.data * packet.offsetZ;
			
			handleSpawn(packet, player);
			
		}else{
			final int amount = packet.amount;
			final float originalX = packet.x;
			final float originalY = packet.y;
			final float originalZ = packet.z;
			final float originalOffsetX = packet.offsetX;
			final float originalOffsetY = packet.offsetY;
			final float originalOffsetZ = packet.offsetZ;
			final float originalData = packet.data;
			
			for(int i=0; i<amount; i++){
    			packet.x = originalX + (originalOffsetX * Util.RANDOM.nextFloat());
    			packet.y = originalY + (originalOffsetY * Util.RANDOM.nextFloat());
    			packet.z = originalZ + (originalOffsetZ * Util.RANDOM.nextFloat());
    			packet.offsetX = originalData * Util.RANDOM.nextFloat();
    			packet.offsetY = originalData * Util.RANDOM.nextFloat();
    			packet.offsetZ = originalData * Util.RANDOM.nextFloat();
    			
    			handleSpawn(packet, player);
			}
		}
	}
	
	private static void handleSpawn(V8D9PacketPlayParticles packet, Player player){
		switch(packet.type){
		case 0: // explode
			playParticle(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 1: // largeexplosion
			playParticle(packet, player, ParticleType.EXPLOSION_LARGE, 0);
			break;
		case 2: // hugeexplosion
			playParticle(packet, player, ParticleType.EXPLOSION_HUGE, 0);
			break;
		case 3: // fireworksSpark
			playParticle(packet, player, ParticleType.FIREWORKS_SPARKS, 0);
			break;
		case 4: // bubble
			playParticle(packet, player, ParticleType.BUBBLE, 0);
			break;
		case 5: // splash
			playParticle(packet, player, ParticleType.SPLASH, 0);
			break;
		case 6: // wake
			playParticle(packet, player, ParticleType.WATER_WAKE, 0);
			break;
		case 7: // suspended
			// playParticles(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 8: // deathsuspend
			
			break;
		case 9: // crit
			playParticle(packet, player, ParticleType.CRITICAL, 0);
			break;
		case 10: // magicCrit
			// playParticles(packet, player, ParticleType.CRITICAL, 0);
			break;
		case 11: // smoke
			playParticle(packet, player, ParticleType.SMOKE, 0);
			break;
		case 12: // largesmoke
			playParticle(packet, player, ParticleType.LARGE_SMOKE, 0);
			break;
		case 13: // spell
			// playParticles(packet, player, ParticleType.MOB_SPELL, 0);
			break;
		case 14: // instantSpell
			// playParticles(packet, player, ParticleType.mob, 0);
			break;
		case 15: // mobSpell
			playParticle(packet, player, ParticleType.MOB_SPELL, colorToData(packet, 255));
			break;
		case 16: // mobSpellAmbient
			playParticle(packet, player, ParticleType.MOB_SPELL_AMBIENT, colorToData(packet, 38));
			break;
		case 17: // witchMagic
			playParticle(packet, player, ParticleType.WITCH_SPELL, 0);
			break;
		case 18: // dripWater
			playParticle(packet, player, ParticleType.WATER_DRIP, 0);
			break;
		case 19: // dripLava
			playParticle(packet, player, ParticleType.LAVA_DRIP, 0);
			break;
		case 20: // angryVillager
			playParticle(packet, player, ParticleType.VILLAGER_ANGRY, 0);
			break;
		case 21: // happyVillager
			playParticle(packet, player, ParticleType.VILLAGER_HAPPY, 0);
			break;
		case 22: // townaura
			playParticle(packet, player, ParticleType.TOWN, 0);
			break;
		case 23: // note
			playParticle(packet, player, ParticleType.NOTE, (int) packet.data);
			break;
		case 24: // portal
			playParticle(packet, player, ParticleType.PORTAL, 0);
			break;
		case 25: // enchantmenttable
			playParticle(packet, player, ParticleType.ENCHANTMENT_TABLE, 0);
			break;
		case 26: // flame
			playParticle(packet, player, ParticleType.FLAME, 0);
			break;
		case 27: // lava
			playParticle(packet, player, ParticleType.LAVA, 0);
			break;
		case 28: // footstep
			
			break;
		case 29: // cloud
			// playParticles(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 30: // reddust
			playParticle(packet, player, ParticleType.DUST, colorToData(packet, 255));
			break;
		case 31: // snowballpoof
			playParticle(packet, player, ParticleType.SNOWBALL_POOF, 0);
			break;
		case 32: // snowshovel
			// playParticles(packet, player, ParticleType.SNOWBALL_POOF, 0);
			break;
		case 33: // slime
			playParticle(packet, player, ParticleType.SLIME, 0);
			break;
		case 34: // heart
			playParticle(packet, player, ParticleType.HEART, 0);
			break;
		case 35: // barrier
			// playParticles(packet, player, ParticleType.SNOWBALL_POOF, 0);
			break;
		case 36: // iconcrack
			
			break;
		case 37: // blockcrack
			
			break;
		case 38: // blockdust
			
			break;
		case 39: // droplet
			// playParticles(packet, player, ParticleType.dro, 0);
			break;
		case 40: // take
			// playParticles(packet, player, ParticleType.mob, 0);
			break;
		case 41: // mobappearance
			
			break;
		}
	}
	
	private static void playParticle(V8D9PacketPlayParticles packet, Player player, ParticleType type, int data){
		player.playParticle(packet.x, packet.y, packet.z, type, data);
	}
	
	private static int colorToData(V8D9PacketPlayParticles packet, int alpha){
		if(packet.offsetX == 0.0F)
			packet.offsetX = 1F;
		
		final float enlighten = (float)Math.random() * 0.4F + 0.6F;
		final float red = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * packet.offsetX * enlighten;
		final float green = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * packet.offsetY * enlighten;
		final float blue = ((float)(Math.random() * 0.20000000298023224D) + 0.8F) * packet.offsetZ * enlighten;
		
		return (0xFF << 24) | (((int) (red * 255F) & 0xFF) << 16) | (((int) (green * 255F) & 0xFF) << 8) | ((int) (blue * 255F) & 0xFF);
	}
}
