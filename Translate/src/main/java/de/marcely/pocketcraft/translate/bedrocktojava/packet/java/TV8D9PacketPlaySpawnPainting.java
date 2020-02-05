package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntityPainting;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnPainting;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8EntityPainting;

public class TV8D9PacketPlaySpawnPainting extends JavaPacketTranslator<V8D9PacketPlaySpawnPainting> {

	@Override
	public void handle(V8D9PacketPlaySpawnPainting packet, Player player){
		final V8Entity entity = new V8EntityPainting(player.getWorld(), packet.entityId);
		
		{
			entity.setX(packet.x);
			entity.setY(packet.y);
			entity.setZ(packet.z);
			entity.setYaw(packet.direction.getSideId());
		}
		
		player.getWorld().addEntity(entity);
		
		// send out
		{	
			final PacketSpawnEntityPainting out = new PacketSpawnEntityPainting();
			
			out.entityRuntimeId = out.entityUniqueId = packet.entityId;
			// vv it's important that we take the parameters from the entity and not from the packet since the entity might already changed them
			out.x = entity.getX();
			out.y = entity.getY();
			out.z = entity.getZ();
			out.direction = player.getTranslateComponents().toBedrock(packet.direction, TranslateComponents.BLOCK_FACE);
			out.title = packet.paintingName;
			
			player.sendPacket(out);
		}
	}
}