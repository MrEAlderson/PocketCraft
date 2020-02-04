package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityLook;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityLook extends JavaPacketTranslator<V8D9PacketPlayEntityLook> {

	@Override
	public void handle(V8D9PacketPlayEntityLook packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		final boolean sendAbsolute = packet.isOnGround != entity.isOnGround();
		
		{
			// send absolute because isOnGround has changed. delta move packet doesn't have that, so we must send the absolute one
			if(sendAbsolute){
				{
					entity.setYaw(packet.yaw);
					entity.setPitch(packet.pitch);
					entity.setOnGround(packet.isOnGround);
				}
				
				entity.sendLocation(player, false);
				
				return;
			}
			
			// delta
			{
				final PacketEntityRelMove out = new PacketEntityRelMove();
				
				out.entityRuntimeId = packet.entityId;
				
				if(packet.yaw != entity.getYaw()){
					entity.setYaw(packet.yaw);
					
					out.flags |= PacketEntityRelMove.FLAG_HAS_YAW;
					out.yaw = packet.yaw;
				}
				
				if(packet.pitch != entity.getPitch()){
					entity.setPitch(packet.pitch);
					
					out.flags |= PacketEntityRelMove.FLAG_HAS_PITCH;
					out.pitch = packet.pitch;
				}
				
				if(out.flags != 0)
					player.sendPacket(out);
			}
		}
	}
}
