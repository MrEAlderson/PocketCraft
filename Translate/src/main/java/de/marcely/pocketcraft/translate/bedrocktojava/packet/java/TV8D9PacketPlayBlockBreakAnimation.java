package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketWorldEvent;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockBreakAnimation;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayBlockBreakAnimation extends JavaPacketTranslator<V8D9PacketPlayBlockBreakAnimation> {
	
	public static double asd = 400;
	
	@Override
	// TODO look for better solution
	public void handle(V8D9PacketPlayBlockBreakAnimation packet, Player player){
		final Chunk chunk = player.getWorld().getChunk(packet.x >> 4, packet.z >> 4);
		
		if(chunk == null)
			return;
		
		// destroy
		if(packet.stage == -1){
			chunk.getBlockBreakEntities().remove(packet.entityId);
			
			{
				final PacketWorldEvent out = new PacketWorldEvent();
				
				out.x = packet.x;
				out.y = packet.y;
				out.z = packet.z;
				out.type = PacketWorldEvent.TYPE_BLOCK_STOP_BREAK;
				
				player.sendPacket(out);
			}
			
		// start
		}else if(packet.stage == 0){
			chunk.getBlockBreakEntities().put(packet.entityId, System.currentTimeMillis());
			
			{
				final PacketWorldEvent out = new PacketWorldEvent();
				
				out.x = packet.x;
				out.y = packet.y;
				out.z = packet.z;
				out.type = PacketWorldEvent.TYPE_BLOCK_START_BREAK;
				out.data = 1; // animation stays forever
				
				player.sendPacket(out);
			}
			
		// we know how fast we're digging. play actual animation
		}else if(packet.stage == 1){
			final Long startTime = chunk.getBlockBreakEntities().remove(packet.entityId);
			
			if(startTime == null)
				return;
			
			final long interval = System.currentTimeMillis() - startTime;
			
			{
				final PacketWorldEvent out = new PacketWorldEvent();
				
				out.x = packet.x;
				out.y = packet.y;
				out.z = packet.z;
				out.type = PacketWorldEvent.TYPE_BLOCK_STOP_BREAK;
				
				player.sendPacket(out);
			}
			
			{
				final PacketWorldEvent out = new PacketWorldEvent();
				final long breakTime = (long) (((interval * 0.9D) / 1000D) * (double) asd);
				
				System.out.println(breakTime);
				
				if(breakTime <= 0)
					return;
				
				out.x = packet.x;
				out.y = packet.y;
				out.z = packet.z;
				out.type = PacketWorldEvent.TYPE_BLOCK_START_BREAK;
				out.data = (int) (65535 / breakTime);
				
				player.sendPacket(out);
			}
		}
	}
}
