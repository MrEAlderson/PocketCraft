package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayKeepAlive;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;

public class TV8D9PacketPlayKeepAlive extends JavaPacketTranslator<V8D9PacketPlayKeepAlive> {

	@Override
	public void handle(V8D9PacketPlayKeepAlive packet, Player player){
		// simulate ping
		Scheduler.runAsyncLater(() -> {
			player.sendPacket(packet);
		}, player.getBedrock().getClient().getLastLatency());
	}
}
