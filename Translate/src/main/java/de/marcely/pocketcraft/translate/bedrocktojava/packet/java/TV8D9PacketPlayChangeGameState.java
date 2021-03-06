package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketGameMode;
import de.marcely.pocketcraft.bedrock.network.packet.PacketWorldEvent;
import de.marcely.pocketcraft.java.component.v8.V8GameMode;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayChangeGameState;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayChangeGameState.*;

public class TV8D9PacketPlayChangeGameState extends JavaPacketTranslator<V8D9PacketPlayChangeGameState> {

	@Override
	public void handle(V8D9PacketPlayChangeGameState packet, Player player){
		switch(packet.key){
		case KEY_END_RAINING:
		{
			final PacketWorldEvent out = new PacketWorldEvent();
			
			out.type = PacketWorldEvent.TYPE_WEATHER_RAIN_STOP;
			out.data = 999999;
			
			player.sendPacket(out);
		}
		break;
		
		case KEY_BEGIN_RAINING:
		{
			final PacketWorldEvent out = new PacketWorldEvent();
			
			out.type = PacketWorldEvent.TYPE_WEATHER_RAIN_START;
			out.data = 999999;
			
			player.sendPacket(out);
		}
		break;
		
		case KEY_CHANGE_GAMEMODE:
		{
			final PacketGameMode out = new PacketGameMode();
			final V8GameMode mode = V8GameMode.getById((int) packet.value);
			
			if(mode == null)
				return;
			
			out.mode = player.getTranslateComponents().toBedrock(mode, TranslateComponents.GAMEMODE);
			
			player.sendPacket(out);
		}
		break;
		
		case KEY_EXIT_END:
		{
			
			player.showCredits();
		}
		break;
		
		case KEY_ELDER_GUARDIAN_APPEARANCE:
		{
			final PacketWorldEvent out = new PacketWorldEvent();
			
			out.type = PacketWorldEvent.TYPE_GUARDIAN_CURSE;
			
			player.sendPacket(out);
		}
		break;
		}
	}
}
