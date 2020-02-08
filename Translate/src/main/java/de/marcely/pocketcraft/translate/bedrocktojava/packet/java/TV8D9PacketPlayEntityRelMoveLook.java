package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityRelMoveLook;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityRelMoveLook extends JavaPacketTranslator<V8D9PacketPlayEntityRelMoveLook> {

	@Override
	public void handle(V8D9PacketPlayEntityRelMoveLook packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		{
			{
				entity.onNetworkPositionChange(packet.relX, packet.relY, packet.relZ, true);
				entity.setYaw(packet.yaw);
				entity.setPitch(packet.pitch);
				entity.setOnGround(packet.isOnGround);
			}
			
			entity.sendLocation(player, false);
		}
	}
}
