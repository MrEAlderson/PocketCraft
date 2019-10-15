package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayKick;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayKick extends JavaPacketTranslator<V8D9PacketPlayKick> {

	@Override
	public void handle(V8D9PacketPlayKick packet, Player player){
		player.getBedrock().kick(packet.message.asPlainText());
	}
}
