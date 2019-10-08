package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketCommandRequest;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketCommandRequest extends BedrockPacketTranslator<PacketCommandRequest> {

	@Override
	public void handle(PacketCommandRequest packet, Player player){
		final V8D9PacketPlayClientChatMessage out = new V8D9PacketPlayClientChatMessage();
		
		out.message = packet.command;
		
		player.sendPacket(out);
	}
}
