package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityRelMove;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityRelMove extends JavaPacketTranslator<V8D9PacketPlayEntityRelMove> {

	@Override
	public void handle(V8D9PacketPlayEntityRelMove packet, Player player){
		final PacketEntityRelMove out = new PacketEntityRelMove();
		
		out.flags = PacketEntityRelMove.FLAG_HAS_X | PacketEntityRelMove.FLAG_HAS_Y | PacketEntityRelMove.FLAG_HAS_Z;
		out.relX = packet.relX;
		out.relY = packet.relY;
		out.relZ = packet.relZ;
		
		player.sendPacket(out);
	}
}
