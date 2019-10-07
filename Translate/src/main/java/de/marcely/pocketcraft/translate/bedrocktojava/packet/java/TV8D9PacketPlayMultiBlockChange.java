package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.BlockMapping;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockChange;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayMultiBlockChange;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;
import de.marcely.pocketcraft.translate.world.V8BlockTranslator;
import de.marcely.pocketcraft.utils.Pair;

public class TV8D9PacketPlayMultiBlockChange extends JavaPacketTranslator<V8D9PacketPlayMultiBlockChange> {

	@Override
	public void handle(V8D9PacketPlayMultiBlockChange packet, Player player){
		final V8Chunk chunk = player.getWorld().getChunk(packet.chunkX, packet.chunkZ, V8Chunk.class);
		
		if(chunk == null)
			return;
		
		for(int i=0; i<packet.data.length; i++){
			final int x = packet.chunkX*16+packet.relX[i];
			final int y = packet.y[i];
			final int z = packet.chunkZ*16+packet.relZ[i];
			final short id = packet.id[i];
			final byte data = packet.data[i];
			
			// change it in the chunk
			{
				final de.marcely.pocketcraft.java.component.v8.V8Chunk ref = chunk.getReference();
				
				ref.setBlockId(packet.relX[i], y, packet.relZ[i], id);
				ref.setBlockData(packet.relX[i], y, packet.relZ[i], data);
			}
			
			// send it to the player
			{
				final Pair<Short, Byte> pair = V8BlockTranslator.toBedrock(id, data);
				final PacketBlockChange out = new PacketBlockChange();
				
				out.x = x;
				out.y = y;
				out.z = z;
				out.blockRuntimeId = BlockMapping.INSTANCE.getRuntimeId(pair.getEntry1(), pair.getEntry2());
				out.flag = PacketBlockChange.FLAG_ALL;
				
				player.sendPacket(out);
			}
		}
	}
}
