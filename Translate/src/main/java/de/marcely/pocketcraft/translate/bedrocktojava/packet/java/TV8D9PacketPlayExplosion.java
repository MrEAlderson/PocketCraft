package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityVelocity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketExplosion;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayExplosion;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;

public class TV8D9PacketPlayExplosion extends JavaPacketTranslator<V8D9PacketPlayExplosion> {

	@Override
	public void handle(V8D9PacketPlayExplosion packet, Player player){
		V8Chunk chunk = null;
		Integer beforeChunkX = null, beforeChunkZ = null;
		
		// apply changes to chunk
		for(int i=0; i<packet.affectedBlocksRelX.length; i++){
			final int x = (int) (packet.x+packet.affectedBlocksRelX[i]);
			final int y = (int) (packet.y+packet.affectedBlocksRelY[i]);
			final int z = (int) (packet.z+packet.affectedBlocksRelZ[i]);
			
			// look for chunk
			{
				final int chunkX = x >> 4;
				final int chunkZ = z >> 4;
				
				if(beforeChunkX == null || (chunkX != beforeChunkX || chunkZ != beforeChunkZ)){
					beforeChunkX = chunkX;
					beforeChunkZ = chunkZ;
					chunk = player.getWorld().getChunk(x >> 4, z >> 4, V8Chunk.class);
				}
			}
			
			if(chunk == null)
				continue;
			
			// change it in the chunk
			{
				final de.marcely.pocketcraft.java.component.v8.V8Chunk ref = chunk.getReference();
				final int relX = Math.abs(x % 16);
				final int relZ = Math.abs(z % 16);
				
				ref.setBlockId(relX, y, relZ, (short) 0);
			}
		}
		
		// send explosion packet
		{
			final PacketExplosion out = new PacketExplosion();
			
			out.posX = packet.x;
			out.posY = packet.y;
			out.posZ = packet.z;
			out.radius = packet.radius;
			out.affectedBlocksX = new int[packet.affectedBlocksRelX.length];
			out.affectedBlocksY = new int[packet.affectedBlocksRelY.length];
			out.affectedBlocksZ = new int[packet.affectedBlocksRelZ.length];
			
			for(int i=0; i<packet.affectedBlocksRelX.length; i++){
				out.affectedBlocksX[i] = (int) (packet.affectedBlocksRelX[i]);
				out.affectedBlocksY[i] = (int) (packet.affectedBlocksRelY[i]);
				out.affectedBlocksZ[i] = (int) (packet.affectedBlocksRelZ[i]);
			}
			
			player.sendPacket(out);
		}
		
		// send motion packet
		{
			final PacketEntityVelocity out = new PacketEntityVelocity();
			
			out.entityRuntimeID = player.getEntityId();
			out.veloX = packet.velocityX;
			out.veloY = packet.velocityY;
			out.veloZ = packet.velocityZ;
			
			player.sendPacket(out);
		}
	}
}
