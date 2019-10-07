package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

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
			
			// unload chunk
			if(packet.isFullChunk && primaryBitMask == 0){
				player.getWorld().unloadChunk(x, z);
				continue;
			}
			
			// looks for existing old (if packet has sent a full chunk)
			/*final V8Chunk oldChunk =
					!packet.containsSkyLightData ?
					player.getWorld().getChunk(x, z, V8Chunk.class) :
					null;*/
			final V8Chunk oldChunk = null;
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
		}
	}
}