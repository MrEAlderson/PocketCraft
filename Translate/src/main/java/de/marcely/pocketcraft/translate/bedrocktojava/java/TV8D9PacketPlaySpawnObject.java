package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnObject;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8EntityObject;

public class TV8D9PacketPlaySpawnObject extends JavaPacketTranslator<V8D9PacketPlaySpawnObject> {

	@Override
	public void handle(V8D9PacketPlaySpawnObject packet, Player player){
		// spawn it
		final Entity entity = player.getTranslator().newEntityInstance(packet.type, packet.entityId, player.getWorld(), true);
		
		if(entity == null || entity.getType() == null)
			return;
		
		{
			((V8EntityObject) entity).readData(packet.data);
			
			entity.setX(packet.x);
			entity.setY(packet.y);
			entity.setZ(packet.z);
			entity.setYaw(packet.yaw);
			entity.setPitch(packet.pitch);
		}
		
		player.getWorld().addEntity(entity);
		
		{
			final PacketSpawnEntity out = new PacketSpawnEntity();
			
			out.entityUID = out.entityRuntimeID = packet.entityId;
			out.type = entity.getType();
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.yaw = packet.yaw;
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
