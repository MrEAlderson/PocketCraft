package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnExperienceOrb;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8EntityExperienceOrb;

public class TV8D9PacketPlaySpawnExperienceOrb extends JavaPacketTranslator<V8D9PacketPlaySpawnExperienceOrb> {

	@Override
	public void handle(V8D9PacketPlaySpawnExperienceOrb packet, Player player){
		final V8Entity entity = new V8EntityExperienceOrb(player.getWorld(), packet.entityId, packet.amount);
		
		{
			entity.onNetworkPositionChange(packet.x, packet.y, packet.z, false);
		}
		
		player.getWorld().addEntity(entity);
		
		{
			final PacketSpawnEntity out = new PacketSpawnEntity();
			
			out.entityUniqueId = out.entityRuntimeId = packet.entityId;
			out.type = entity.getType();
			// vv it's important that we take the parameters from the entity and not from the packet since the entity might already changed them
			out.x = entity.getX();
			out.y = entity.getY();
			out.z = entity.getZ();
			out.metadata = entity.getMetadata();
			out.attributes = new EntityAttribute[0];
			
			player.sendPacket(out);
		}
	}
}
