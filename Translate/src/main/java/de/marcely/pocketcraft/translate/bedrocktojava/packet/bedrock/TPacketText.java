package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketText extends BedrockPacketTranslator<PacketText> {

	@Override
	public void handle(PacketText packet, Player player){
		if(packet.type != PacketText.TYPE_CHAT)
			return;
		
		// send to java
		{
			final V8D9PacketPlayClientChatMessage out = new V8D9PacketPlayClientChatMessage();
			
			out.message = packet.message;
			
			player.sendPacket(packet);
		}
	}
}
