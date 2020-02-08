package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityVelocity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityVelocity;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityVelocity extends JavaPacketTranslator<V8D9PacketPlayEntityVelocity> {

	@Override
	public void handle(V8D9PacketPlayEntityVelocity packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		{
			entity.setVeloX(packet.veloX);
			entity.setVeloY(packet.veloY);
			entity.setVeloZ(packet.veloZ);
		}
		
		final PacketEntityVelocity out = new PacketEntityVelocity();
		
		out.entityRuntimeId = packet.entityId;
		out.veloX = packet.veloX;
		out.veloY = packet.veloY;
		out.veloZ = packet.veloZ;
		
		player.sendPacket(out);
	}
}
