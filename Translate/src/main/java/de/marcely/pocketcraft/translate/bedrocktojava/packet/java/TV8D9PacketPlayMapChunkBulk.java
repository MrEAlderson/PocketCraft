package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketNetworkChunkPublisherUpdate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayMapChunkBulk;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;

public class TV8D9PacketPlayMapChunkBulk extends JavaPacketTranslator<V8D9PacketPlayMapChunkBulk> {

	@Override
	public void handle(V8D9PacketPlayMapChunkBulk packet, Player player){
		for(int i=0; i<packet.x.length; i++){
			final int x = packet.x[i];
			final int z = packet.z[i];
			final byte[] data = packet.data[i];
			final int primaryBitMask = packet.primaryBitMask[i];
			
			// looks for existing old (if packet has sent a full chunk)
			final V8Chunk oldChunk =
					!packet.isFullChunk ?
					player.getWorld().getChunk(x, z, V8Chunk.class) :
					null;
			// read chunk from byte data
			final V8Chunk newChunk =
					new V8Chunk(de.marcely.pocketcraft.java.component.v8.V8Chunk.read(
					data,
					primaryBitMask,
					null));
			
			// add it to system
			if(oldChunk == null){
				player.getWorld().addChunk(x, z, newChunk);
			}
			
			// finally send it
			player.sendPacket(newChunk.buildPacket(x, z));
			
			{
				final PacketNetworkChunkPublisherUpdate out = (PacketNetworkChunkPublisherUpdate) PacketType.NetworkChunkPublisherUpdate.newInstance();
				
				out.x = x*16;
				out.y = 10;
				out.z = z*16;
				out.radius = 1;
				
			    player.sendPacket(out);
			}
		}
	}
}