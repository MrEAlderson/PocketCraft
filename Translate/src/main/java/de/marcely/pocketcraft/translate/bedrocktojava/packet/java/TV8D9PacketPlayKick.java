package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketDisconnect;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayKick;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayKick extends JavaPacketTranslator<V8D9PacketPlayKick> {

	@Override
	public void handle(V8D9PacketPlayKick packet, Player player){
		final PacketDisconnect out = new PacketDisconnect();
		
		out.hideScreen = false;
		out.reason = packet.message.asPlainText();
		
		player.sendPacket(out);
	}
}
