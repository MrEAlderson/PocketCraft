package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityRelMoveLook;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityRelMoveLook extends JavaPacketTranslator<V8D9PacketPlayEntityRelMoveLook> {

	@Override
	public void handle(V8D9PacketPlayEntityRelMoveLook packet, Player player){
		final PacketEntityRelMove out = new PacketEntityRelMove();
		
		out.entityRuntimeId = packet.entityId;
		out.flags = PacketEntityRelMove.FLAG_HAS_X | PacketEntityRelMove.FLAG_HAS_Y | PacketEntityRelMove.FLAG_HAS_Z |
				    PacketEntityRelMove.FLAG_HAS_YAW |  PacketEntityRelMove.FLAG_HAS_PITCH;
		out.relX = packet.relX;
		out.relY = packet.relY;
		out.relZ = packet.relZ;
		out.yaw = packet.yaw;
		out.pitch = packet.pitch;
		
		player.sendPacket(out);
	}
}
