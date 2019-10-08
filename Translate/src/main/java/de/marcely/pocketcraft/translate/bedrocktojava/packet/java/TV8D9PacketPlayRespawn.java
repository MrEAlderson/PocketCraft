package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketChangeDimension;
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
			final PacketChangeDimension out = new PacketChangeDimension();
			
			out.dimension = DimensionTranslator.toBedrock(packet.dimension);
			out.posX = 0;
			out.posY = 100;
			out.posZ = 0;
			
			player.sendPacket(out);
			player.getWorld().setDimension(out.dimension);
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
		
		player.setSpawning(true);
		player.getWorld().getChunksMap().clear();
	}
}
