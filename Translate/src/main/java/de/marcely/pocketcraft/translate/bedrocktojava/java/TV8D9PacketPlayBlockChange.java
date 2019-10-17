package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.BlockMapping;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockChange;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockChange;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8BlockEntityTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;
import de.marcely.pocketcraft.translate.component.V8BlockTranslator;
import de.marcely.pocketcraft.utils.Pair;

public class TV8D9PacketPlayBlockChange extends JavaPacketTranslator<V8D9PacketPlayBlockChange> {

	@Override
	public void handle(V8D9PacketPlayBlockChange packet, Player player){
		final V8Chunk chunk = player.getWorld().getChunk(packet.x >> 4, packet.z >> 4, V8Chunk.class);
		
		if(chunk == null)
			return;
		
		// change it in the chunk
		{
			final de.marcely.pocketcraft.java.component.v8.V8Chunk ref = chunk.getReference();
			final int relX = Math.abs(packet.x % 16);
			final int relZ = Math.abs(packet.z % 16);
			final short oldId = ref.getBlockId(relX, packet.y, relZ);
			
			ref.setBlockId(relX, packet.y, relZ, packet.id);
			ref.setBlockData(relX, packet.y, relZ, packet.data);
			
			// block entity
			V8BlockEntityTranslator.handleSpawn(player, chunk, relX, packet.y, relZ, packet.id, packet.data, oldId);
		}
		
		// send it to the player
		if(chunk.isSent()){
			final Pair<Short, Byte> pair = V8BlockTranslator.toBedrock(packet.id, packet.data);
			final PacketBlockChange out = new PacketBlockChange();
			
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.blockRuntimeId = BlockMapping.INSTANCE.getRuntimeId(pair.getEntry1(), pair.getEntry2());
			out.flag = PacketBlockChange.FLAG_ALL;
			
			player.sendPacket(out);
		}
	}
}
