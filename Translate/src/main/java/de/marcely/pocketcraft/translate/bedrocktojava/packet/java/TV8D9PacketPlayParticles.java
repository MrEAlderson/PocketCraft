package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.ParticleType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayParticles;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.Util;

public class TV8D9PacketPlayParticles extends JavaPacketTranslator<V8D9PacketPlayParticles> {

	@Override
	public void handle(V8D9PacketPlayParticles packet, Player player){
		switch(packet.type){
		case 0: // explode
			playParticles(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 1: // largeexplosion
			playParticles(packet, player, ParticleType.EXPLOSION_LARGE, 0);
			break;
		case 2: // hugeexplosion
			playParticles(packet, player, ParticleType.EXPLOSION_HUGE, 0);
			break;
		case 3: // fireworksSpark
			playParticles(packet, player, ParticleType.FIREWORKS_SPARKS, 0);
			break;
		case 4: // bubble
			playParticles(packet, player, ParticleType.BUBBLE, 0);
			break;
		case 5: // splash
			playParticles(packet, player, ParticleType.SPLASH, 0);
			break;
		case 6: // wake
			playParticles(packet, player, ParticleType.WATER_WAKE, 0);
			break;
		case 7: // suspended
			// playParticles(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 8: // deathsuspend
			
			break;
		case 9: // crit
			playParticles(packet, player, ParticleType.CRITICAL, 0);
			break;
		case 10: // magicCrit
			// playParticles(packet, player, ParticleType.CRITICAL, 0);
			break;
		case 11: // smoke
			playParticles(packet, player, ParticleType.SMOKE, 0);
			break;
		case 12: // largesmoke
			playParticles(packet, player, ParticleType.LARGE_SMOKE, 0);
			break;
		case 13: // spell
			// playParticles(packet, player, ParticleType.MOB_SPELL, 0);
			break;
		case 14: // instantSpell
			// playParticles(packet, player, ParticleType.mob, 0);
			break;
		case 15: // mobSpell
			playParticles(packet, player, ParticleType.MOB_SPELL, 0);
			break;
		case 16: // mobSpellAmbient
			playParticles(packet, player, ParticleType.MOB_SPELL_AMBIENT, 0);
			break;
		case 17: // withMagic
			
			break;
		case 18: // dripWater
			playParticles(packet, player, ParticleType.WATER_DRIP, 0);
			break;
		case 19: // dripLava
			playParticles(packet, player, ParticleType.LAVA_DRIP, 0);
			break;
		case 20: // angryVillager
			playParticles(packet, player, ParticleType.VILLAGER_ANGRY, 0);
			break;
		case 21: // happyVillager
			playParticles(packet, player, ParticleType.VILLAGER_HAPPY, 0);
			break;
		case 22: // townaura
			playParticles(packet, player, ParticleType.TOWN, 0);
			break;
		case 23: // note
			playParticles(packet, player, ParticleType.NOTE, (int) packet.data);
			break;
		case 24: // portal
			playParticles(packet, player, ParticleType.PORTAL, 0);
			break;
		case 25: // enchantmenttable
			playParticles(packet, player, ParticleType.ENCHANTMENT_TABLE, 0);
			break;
		case 26: // flame
			playParticles(packet, player, ParticleType.FLAME, 0);
			break;
		case 27: // lava
			playParticles(packet, player, ParticleType.LAVA, 0);
			break;
		case 28: // footstep
			
			break;
		case 29: // cloud
			// playParticles(packet, player, ParticleType.EXPLOSION, 0);
			break;
		case 30: // reddust
			playParticles(packet, player, ParticleType.DUST, 0xFFFFFFFF);
			break;
		case 31: // snowballpoof
			playParticles(packet, player, ParticleType.SNOWBALL_POOF, 0);
			break;
		case 32: // snowshovel
			// playParticles(packet, player, ParticleType.SNOWBALL_POOF, 0);
			break;
		case 33: // slime
			playParticles(packet, player, ParticleType.SLIME, 0);
			break;
		case 34: // heart
			playParticles(packet, player, ParticleType.HEART, 0);
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
	
	private static void playParticles(V8D9PacketPlayParticles packet, Player player, ParticleType type, int data){
		for(int i=0; i<packet.amount; i++){
			final float x = packet.x + (packet.offsetX > 0F ? (Util.RANDOM.nextInt((int) (packet.offsetX * 32F))/32F) : 0F);
			final float y = packet.y + (packet.offsetY > 0F ? (Util.RANDOM.nextInt((int) (packet.offsetY * 32F))/32F) : 0F);
			final float z = packet.z + (packet.offsetZ > 0F ? (Util.RANDOM.nextInt((int) (packet.offsetZ * 32F))/32F) : 0F);
			
			player.playParticle(x, y, z, type, data);
		}
	}
}
