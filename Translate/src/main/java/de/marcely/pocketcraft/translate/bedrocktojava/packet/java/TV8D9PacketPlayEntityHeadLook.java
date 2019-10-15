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
		
		/*{
			final PacketEntityMove out = new PacketEntityMove();
			
			out.entityRuntimeId = packet.entityId;
			out.x = entity.getX();
			out.y = entity.getY();
			out.z = entity.getZ();
			out.yaw = entity.getYaw();
			out.headYaw = entity.getHeadYaw();
			out.pitch = entity.getPitch();
			out.isOnGround = entity.isOnGround();
			out.isTeleport = false;
			
			player.sendPacket(out);
		}*/
		
		{
			System.out.println(packet.headYaw);
			
			final PacketEntityRelMove out = new PacketEntityRelMove();
			
			out.entityRuntimeId = packet.entityId;
			out.flags = PacketEntityRelMove.FLAG_HAS_HEAD_YAW;
			out.headYaw = packet.headYaw;
			
			player.sendPacket(out);
		}
	}
}
