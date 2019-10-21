package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketWorldTime;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWorldTime;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayWorldTime extends JavaPacketTranslator<V8D9PacketPlayWorldTime> {

	@Override
	public void handle(V8D9PacketPlayWorldTime packet, Player player){
		final PacketWorldTime out = new PacketWorldTime();
		
		out.time = (int) packet.time;
		
		player.sendPacket(out);
	}
}
