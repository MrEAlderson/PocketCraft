package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityTeleport;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityTeleport extends JavaPacketTranslator<V8D9PacketPlayEntityTeleport> {

	@Override
	public void handle(V8D9PacketPlayEntityTeleport packet, Player player){
		final PacketEntityMove out = new PacketEntityMove();
		
		out.entityRuntimeId = packet.entityId;
		out.x = packet.x;
		out.y = packet.y;
		out.z = packet.z;
		out.yaw = out.headYaw = packet.yaw;
		out.pitch = packet.pitch;
		out.isOnGround = packet.isOnGround;
		out.isTeleport = true;
		
		player.sendPacket(out);
	}
}
