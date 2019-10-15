package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityHeadLook;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityHeadLook extends JavaPacketTranslator<V8D9PacketPlayEntityHeadLook> {

	@Override
	public void handle(V8D9PacketPlayEntityHeadLook packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		{
			entity.setHeadYaw(packet.headYaw);
		}
		
		{
			final PacketEntityRelMove out = new PacketEntityRelMove();
			
			out.entityRuntimeId = packet.entityId;
			out.flags = PacketEntityRelMove.FLAG_HAS_HEAD_YAW | PacketEntityRelMove.FLAG_HAS_YAW;
			out.yaw = packet.headYaw-10;
			out.headYaw = packet.headYaw;
			
			player.sendPacket(out);
		}
	}
}
