package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnMob;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlaySpawnMob extends JavaPacketTranslator<V8D9PacketPlaySpawnMob> {

	@Override
	public void handle(V8D9PacketPlaySpawnMob packet, Player player){
		final Entity entity = player.getTranslator().newEntityInstance(packet.type, packet.entityId);
		
		if(entity == null)
			return;
		
		player.getWorld().addEntity(entity);
		
		{
			final PacketSpawnEntity out = new PacketSpawnEntity();
			
			out.entityUID = out.entityRuntimeID = packet.entityId;
			out.type = entity.getType();
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.yaw = out.headYaw = packet.yaw;
			out.pitch = packet.pitch;
			out.veloX = packet.veloX;
			out.veloY = packet.veloY;
			out.veloZ = packet.veloZ;
			out.metadata = entity.getMetadata();
			out.attributes = new EntityAttribute[0];
			
			player.sendPacket(out);
		}
	}
}
