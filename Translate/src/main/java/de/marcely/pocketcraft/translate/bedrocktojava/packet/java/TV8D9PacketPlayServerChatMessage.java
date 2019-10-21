package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayServerChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayServerChatMessage extends JavaPacketTranslator<V8D9PacketPlayServerChatMessage> {

	@Override
	public void handle(V8D9PacketPlayServerChatMessage packet, Player player){
		final PacketText out = new PacketText();
		
		switch(packet.position){
		case V8D9PacketPlayServerChatMessage.POSITION_CHAT:
			out.type = PacketText.TYPE_CHAT;
			break;
			
		case V8D9PacketPlayServerChatMessage.POSITION_SYSTEM_MESSAGE:
			out.type = PacketText.TYPE_SYSTEM;
			break;
			
		case V8D9PacketPlayServerChatMessage.POSITION_GAME_INFO:
			out.type = PacketText.TYPE_TIP;
			break;
			
		default:
			return;
		}
		
		out.message = player.getTranslateComponents().toBedrock(packet.message, TranslateComponents.FORMATTED_TEXT);
		
		player.sendPacket(out);
	} 
}
