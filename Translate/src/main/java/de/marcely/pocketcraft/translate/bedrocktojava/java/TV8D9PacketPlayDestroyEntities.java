package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketDestroyEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayDestroyEntities;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayDestroyEntities extends JavaPacketTranslator<V8D9PacketPlayDestroyEntities> {

	@Override
	public void handle(V8D9PacketPlayDestroyEntities packet, Player player){
		for(int id:packet.entityIds){
			if(player.getWorld().removeEntity(id) == null)
				continue;
			
			final PacketDestroyEntity out = new PacketDestroyEntity();
			
			out.entityUniqueId = id;
			
			player.sendPacket(out);
		}
	}
}
