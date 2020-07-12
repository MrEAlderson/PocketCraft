package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWorldEvent;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.Pair;

import static de.marcely.pocketcraft.bedrock.network.packet.PacketWorldEvent.*;

import de.marcely.pocketcraft.bedrock.component.BBlockMapping;
import de.marcely.pocketcraft.bedrock.network.packet.PacketWorldEvent;

public class TV8D9PacketWorldEvent extends JavaPacketTranslator<V8D9PacketPlayWorldEvent> {

	@Override
	public void handle(V8D9PacketPlayWorldEvent packet, Player player){
		switch(packet.effectId){
		///// SOUNDS
		
		 // random.click
		case 1000:
		case 10001:
			playWorldEvent(player, packet, TYPE_SOUND_CLICK);
			break;
		 // random.bow
		case 1002:
			playWorldEvent(player, packet, TYPE_SOUND_SHOOT);
			break;
		// random.door_open or random.door_close (50/50 chance)
		case 1003:
			playWorldEvent(player, packet, TYPE_SOUND_DOOR);
			break;
		// random.fizz
		case 1004:
			playWorldEvent(player, packet, TYPE_SOUND_FIZZ);
			break;
		// plays music disk
		case 1005:
			// playWorldEvent(player, 1000);
			break;
		// mob.ghast.charge
		case 1007:
			playWorldEvent(player, packet, TYPE_SOUND_GHAST);
			break;
		// mob.ghast.fireball
		case 10008:
			playWorldEvent(player, packet, TYPE_SOUND_GHAST_SHOOT);
			break;
		// mob.ghast.fireball, but with a lower volume
		case 1009:
			playWorldEvent(player, packet, TYPE_SOUND_GHAST_SHOOT, 1);
			break;
		// mob.zombie.wood
		case 1010:
			playWorldEvent(player, packet, TYPE_SOUND_DOOR_BUMP);
			break;
		// mob.zombie.metal
		case 1011:
			playWorldEvent(player, packet, TYPE_SOUND_DOOR_BUMP, 1);
			break;
		// mob.zombie.woodbreak
		case 1012:
			playWorldEvent(player, packet, TYPE_SOUND_DOOR_CRASH);
			break;
		// mob.wither.spawn
		case 1013:
			// playWorldEvent(player, packet, 1000);
			break;
		// mob.wither.shoot
		case 1014:
			// playWorldEvent(player, packet, 1000);
			break;
		// mob.bat.takeoff
		case 1015:
			// playWorldEvent(player, packet, 1000);
			break;
		// mob.zombie.infect
		case 1016:
			// playWorldEvent(player, packet, 1000);
			break;
		// mob.zombie.unfect
		case 1017:
			// playWorldEvent(player, packet, 1000);
			break;
		// mob.enderdragon.end
		case 1018:
			// playWorldEvent(player, packet, 1000);
			break;
		// random.anvil_break
		case 1020:
			playWorldEvent(player, packet, TYPE_SOUND_ANVIL_BREAK);
			break;
		// random.anvil_use
		case 1021:
			playWorldEvent(player, packet, TYPE_SOUND_ANVIL_USE);
			break;
		// random.anvil_land
		case 1022:
			playWorldEvent(player, packet, TYPE_SOUND_ANVIL_FALL);
			break;
		
		///// PARTICLES
			
		// Spawns 10 smoke particles, e.g. from a fire
		case 2000:
			playWorldEvent(player, packet, TYPE_PARTICLE_SHOOT);
			break;
		// Block break
		case 2001:
		{
			final int id = packet.data & 0x0FFF;
			final int data = (packet.data & 0xF000) >> 12;
			final Pair<Short, Byte> result = player.getTranslateComponents().toBedrock(new Pair<Short, Byte>((short) id, (byte) data), TranslateComponents.BLOCK);
			
			playWorldEvent(player, packet, TYPE_PARTICLE_DESTROY, BBlockMapping.INSTANCE.getRuntimeId(result.getEntry1(), result.getEntry2()));
		}
		break;
		// Splash potion. Particle effect + glass break sound.
		case 2002:
			playWorldEvent(player, packet, TYPE_PARTICLE_SPLASH, packet.data);
			break;
		// Eye of Ender entity break animation — particles and sound
		case 2003:
			playWorldEvent(player, packet, TYPE_PARTICLE_EYE_DESPAWN);
			break;
		// Mob spawn particle effect: smoke + flames
		case 2004:
			playWorldEvent(player, packet, TYPE_PARTICLE_SPAWN);
			break;
		// Spawn “happy villager” effect (green crosses), used for bonemealing vegetation	
		case 2005:
			playWorldEvent(player, packet, TYPE_PARTICLE_BONEMEAL);
			break;
		}
	}
	
	private static void playWorldEvent(Player player, V8D9PacketPlayWorldEvent packet, int type){
		playWorldEvent(player, packet, type, 0);
	}
	
	private static void playWorldEvent(Player player, V8D9PacketPlayWorldEvent packet, int type, int data){
		final PacketWorldEvent out = new PacketWorldEvent();
		
		out.type = type;
		out.data = data;
		out.x = packet.position.getX();
		out.y = packet.position.getY();
		out.z = packet.position.getZ();
		
		player.sendPacket(out);
	}
}
