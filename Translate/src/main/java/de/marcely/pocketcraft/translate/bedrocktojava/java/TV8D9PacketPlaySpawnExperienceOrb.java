package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnExperienceOrb;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8EntityExperienceOrb;

public class TV8D9PacketPlaySpawnExperienceOrb extends JavaPacketTranslator<V8D9PacketPlaySpawnExperienceOrb> {

	@Override
	public void handle(V8D9PacketPlaySpawnExperienceOrb packet, Player player){
		final V8Entity entity = new V8EntityExperienceOrb(player.getWorld(), packet.entityId, packet.amount);
		
		{
			entity.setX(packet.x);
			entity.setY(packet.y);
			entity.setZ(packet.z);
		}
		
		player.getWorld().addEntity(entity);
		
		{
			final PacketSpawnEntity out = new PacketSpawnEntity();
			
			out.entityUniqueId = out.entityRuntimeId = packet.entityId;
			out.type = entity.getType();
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.metadata = entity.getMetadata();
			out.attributes = new EntityAttribute[0];
			
			player.sendPacket(out);
		}
	}
}
