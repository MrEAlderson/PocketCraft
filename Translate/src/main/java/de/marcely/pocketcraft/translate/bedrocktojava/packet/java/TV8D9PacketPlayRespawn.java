package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameDifficulty;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameMode;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayRespawn;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.DifficultyTranslator;
import de.marcely.pocketcraft.translate.component.DimensionTranslator;
import de.marcely.pocketcraft.translate.component.GameModeTranslator;

public class TV8D9PacketPlayRespawn extends JavaPacketTranslator<V8D9PacketPlayRespawn> {

	@Override
	public void handle(V8D9PacketPlayRespawn packet, Player player){
		// dimension
		{
			final Dimension dimension = DimensionTranslator.toBedrock(packet.dimension);
			
			if(dimension == player.getWorld().getDimension())
				return;
			
			player.getWorld().setDimension(dimension);
			player.getWorld().getChunksMap().clear();
			player.getWorld().getEntitiesMap().clear();
		}
		
		// game mode
		{
			final PacketGameMode out = new PacketGameMode();
			
			out.mode = GameModeTranslator.toBedrock(packet.gamemode);
			
			player.sendPacket(out);
		}
		
		// difficulty
		{
			final PacketGameDifficulty out = new PacketGameDifficulty();
			
			out.difficulty = DifficultyTranslator.toBedrock(packet.difficulty);
			
			player.sendPacket(out);
		}
		
		player.setDead(false);
	}
}
