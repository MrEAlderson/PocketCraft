package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameDifficulty;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameMode;
import de.marcely.pocketcraft.bedrock.network.packet.PacketRespawn;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayRespawn;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

/**
 * Important info:
 *  It's currently NOT possible to safely change the dimension of the player.
 *  - There's a high chance that the client will crash whenever you change the dimension
 *  - Respawning in the non-overworld dimensions is pretty buggy and will likely crash the client
 */
public class TV8D9PacketPlayRespawn extends JavaPacketTranslator<V8D9PacketPlayRespawn> {

	@Override
	public void handle(V8D9PacketPlayRespawn packet, Player player){	
		// dimension
		{
			final Dimension dimension = player.getTranslateComponents().toBedrock(packet.dimension, TranslateComponents.DIMENSION);
			
			player.getWorld().setDimension(dimension);
			player.unloadChunks();
			
			if(player.getSpawnState() == Player.SPAWN_STATE_DONE)
				player.setSpawnState(Player.SPAWN_STATE_WAITING_SPAWN);
			
			{
	        	final PacketRespawn out = new PacketRespawn();
	        	
	        	out.entityRuntimeId = player.getEntityId();
	        	out.posX = player.getX();
	        	out.posY = player.getY();
	        	out.posZ = player.getZ();
	        	out.state = PacketRespawn.STATE_SEARCHING_FOR_SPAWN;
	        	
	        	player.sendPacket(out);
			}
		}
		
		// game mode
		{
			final PacketGameMode out = new PacketGameMode();
			
			out.mode = player.getTranslateComponents().toBedrock(packet.gamemode, TranslateComponents.GAMEMODE);
			
			player.sendPacket(out);
		}
		
		// difficulty
		{
			final PacketGameDifficulty out = new PacketGameDifficulty();
			
			out.difficulty = player.getTranslateComponents().toBedrock(packet.difficulty, TranslateComponents.DIFFICULTY);
			
			player.sendPacket(out);
		}
		
		player.setDead(false);
	}
}
