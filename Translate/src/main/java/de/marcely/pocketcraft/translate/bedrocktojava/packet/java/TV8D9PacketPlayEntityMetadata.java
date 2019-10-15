package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;

public class TV8D9PacketPlayEntityMetadata extends JavaPacketTranslator<V8D9PacketPlayEntityMetadata> {

	@Override
	public void handle(V8D9PacketPlayEntityMetadata packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		((V8Entity) entity).readAll(packet.metadata);
		
		entity.sendAllMetadata(player.getBedrock());
	}
}
