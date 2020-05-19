package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.network.packet.PacketChangeDimension;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameDifficulty;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameMode;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayRespawn;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayRespawn extends JavaPacketTranslator<V8D9PacketPlayRespawn> {

	@Override
	public void handle(V8D9PacketPlayRespawn packet, Player player){
		System.out.println("RESPAWN!");
		
		// dimension
		{
			final Dimension dimension = player.getTranslateComponents().toBedrock(packet.dimension, TranslateComponents.DIMENSION);
			
			if(dimension == player.getWorld().getDimension())
				return;
			
			player.getWorld().setDimension(dimension);
			player.setSpawnState(Player.SPAWN_STATE_WAITING_SPAWN);
			player.unloadChunks();
			
			if(player.isLoggedIn()){
				final PacketChangeDimension out = new PacketChangeDimension();
				
				out.dimension = player.getTranslateComponents().toBedrock(packet.dimension, TranslateComponents.DIMENSION);
				out.posX = player.getX();
				out.posY = player.getY();
				out.posZ = player.getZ();
				
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
