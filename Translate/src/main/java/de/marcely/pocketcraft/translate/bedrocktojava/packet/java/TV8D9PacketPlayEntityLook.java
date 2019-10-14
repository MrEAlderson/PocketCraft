package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityLook;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityLook extends JavaPacketTranslator<V8D9PacketPlayEntityLook> {

	@Override
	public void handle(V8D9PacketPlayEntityLook packet, Player player){
		final PacketEntityRelMove out = new PacketEntityRelMove();
		
		out.flags = PacketEntityRelMove.FLAG_HAS_YAW |  PacketEntityRelMove.FLAG_HAS_PITCH;
		out.yaw = packet.yaw;
		out.pitch = packet.pitch;
		
		player.sendPacket(out);
	}
}
