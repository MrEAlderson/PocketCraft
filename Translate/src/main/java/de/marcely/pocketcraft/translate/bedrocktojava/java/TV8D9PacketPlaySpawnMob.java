package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnMob;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;

public class TV8D9PacketPlaySpawnMob extends JavaPacketTranslator<V8D9PacketPlaySpawnMob> {

	@Override
	public void handle(V8D9PacketPlaySpawnMob packet, Player player){
		// some entities are combined for java but not for bedrock
		{
			if(packet.type >= 1000) // magic values
				return;
			
			{
				switch(packet.type){
				// wither skeleton
				case 51:
					if(packet.metadata.has(13) && packet.metadata.readByte(13) == 1)
						packet.type = 1000;
					
					break;
					
				// zombie villager
				case 54:
					if(packet.metadata.has(13) && packet.metadata.readBoolean(13))
						packet.type = 1001;
					
					break;
				}
			}
		}
		
		// spawn it
		final Entity entity = player.getTranslator().newEntityInstance(packet.type, packet.entityId, player.getWorld());
		
		if(entity == null)
			return;
		
		{
			((V8Entity) entity).readAll(packet.metadata);
			entity.setX(packet.x);
			entity.setY(packet.y);
			entity.setZ(packet.z);
			entity.setYaw(packet.yaw);
			entity.setHeadYaw(packet.headPitch);
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
