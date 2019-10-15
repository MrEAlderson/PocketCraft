package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;

public class TV8D9PacketPlayEntityEvent extends JavaPacketTranslator<V8D9PacketPlayEntityEvent> {

	@Override
	public void handle(V8D9PacketPlayEntityEvent packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		((V8Entity) entity).playEvent(packet.type);
	}
}
