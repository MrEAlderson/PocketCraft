package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayServerChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.FormattedTextTranslator;

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
		
		out.message = FormattedTextTranslator.chatToBedrock(packet.message);
		
		player.sendPacket(out);
	} 
}
