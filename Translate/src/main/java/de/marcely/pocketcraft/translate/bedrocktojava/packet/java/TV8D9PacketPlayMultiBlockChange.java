package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.BBlockMapping;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockChange;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayMultiBlockChange;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.V8BlockEntityTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.V8Chunk;
import de.marcely.pocketcraft.utils.Pair;

public class TV8D9PacketPlayMultiBlockChange extends JavaPacketTranslator<V8D9PacketPlayMultiBlockChange> {

	@Override
	public void handle(V8D9PacketPlayMultiBlockChange packet, Player player){
		V8Chunk chunk = null;
		Integer beforeChunkX = null, beforeChunkZ = null;
		
		for(int i=0; i<packet.data.length; i++){
			final int x = packet.chunkX*16+packet.relX[i];
			final int z = packet.chunkZ*16+packet.relZ[i];
			
			// look for chunk
			{
				final int chunkX = x >> 4;
				final int chunkZ = z >> 4;
				
				if(beforeChunkX == null || (chunkX != beforeChunkX || chunkZ != beforeChunkZ)){
					beforeChunkX = chunkX;
					beforeChunkZ = chunkZ;
					chunk = player.getWorld().getChunk(chunkX, chunkZ, V8Chunk.class);
				}
			}
			
			if(chunk == null)
				continue;
			
			final int y = packet.y[i];
			final short id = packet.id[i];
			final byte data = packet.data[i];
			
			// change it in the chunk
			{
				final de.marcely.pocketcraft.java.component.v8.V8Chunk ref = chunk.getReference();
				final int relX = x & 0xF;
				final int relZ = z & 0xF;
				final short oldId = ref.getBlockId(relX, y, relZ);
				
				ref.setBlockId(relX, y, relZ, id);
				ref.setBlockData(relZ, y, relZ, data);
				
				// block entity
				{
					final BlockEntity blockEntity = V8BlockEntityTranslator.handleSpawn(player.getWorld(), chunk, x, y, z, id, data, oldId);
					
					if(blockEntity != null)
						player.updateBlockEntity(blockEntity);
				}
			}
			
			// send it to the player
			if(chunk.isSent()){
				final Pair<Short, Byte> pair = player.getTranslateComponents().toBedrock(new Pair<Short, Byte>(id, data), TranslateComponents.BLOCK);
				final PacketBlockChange out = new PacketBlockChange();
				
				out.x = x;
				out.y = y;
				out.z = z;
				out.blockRuntimeId = BBlockMapping.INSTANCE.getRuntimeId(pair.getEntry1(), pair.getEntry2());
				out.flag = PacketBlockChange.FLAG_ALL;
				
				player.sendPacket(out);
			}
		}
	}
}
