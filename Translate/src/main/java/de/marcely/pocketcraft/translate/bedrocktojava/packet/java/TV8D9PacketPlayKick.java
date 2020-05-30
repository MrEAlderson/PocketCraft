package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayKick;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;

public class TV8D9PacketPlayKick extends JavaPacketTranslator<V8D9PacketPlayKick> {

	@Override
	public void handle(V8D9PacketPlayKick packet, Player player){
		if(!player.getBedrock().kick(packet.message.asPlainText())){
			player.setGettingKicked(true);
			
    		// give him 5s to disconnect
    		Scheduler.runAsyncLater(() -> {
    			player.setGettingKicked(false);
    			
    			if(player.getBedrock().getClient().isConnected())
    				player.getBedrock().getClient().disconnect();
    			
    			if(player.getJava().isRunning())
    				player.getJava().close();
    		}, 1000 * 5);
		}
	}
}
