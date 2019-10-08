package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayMapChunkBulk;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;

public class TV8D9PacketPlayMapChunkBulk extends JavaPacketTranslator<V8D9PacketPlayMapChunkBulk> {

	@Override
	public void handle(V8D9PacketPlayMapChunkBulk packet, Player player){
		final EByteBuf buf = new EByteBuf(packet.data);
		
		try{
			for(int i=0; i<packet.x.length; i++){
				final int x = packet.x[i];
				final int z = packet.z[i];
				final int primaryBitMask = packet.primaryBitMask[i];
				
				// unload chunk
				if(packet.groundUpContinous && primaryBitMask == 0){
					player.getWorld().unloadChunk(x, z);
					continue;
				}
				
				// looks for existing old (if packet has sent a full chunk)
				final V8Chunk oldChunk =
						!packet.groundUpContinous ?
						player.getWorld().getChunk(x, z, V8Chunk.class) :
						null;
				// read chunk from byte data
				final V8Chunk newChunk =
						new V8Chunk(de.marcely.pocketcraft.java.component.v8.V8Chunk.read(
						buf,
						primaryBitMask,
						(player.getWorld().getDimension() == Dimension.OVERWORLD),
						true,
						(oldChunk != null ? oldChunk.getReference() : null)));
				
				// add it to system
				if(oldChunk == null){
					player.getWorld().addChunk(x, z, newChunk);
					player.receivedChunk(x, z);
				}
			}
		}finally{
			buf.release();
		}
	}
}