package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayMapChunk;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.V8Chunk;

public class TV8D9PacketPlayMapChunk extends JavaPacketTranslator<V8D9PacketPlayMapChunk> {

	@Override
	public void handle(V8D9PacketPlayMapChunk packet, Player player){
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// looks for existing old (if packet has sent a full chunk)
		/*final V8Chunk oldChunk =
				!packet.isFullChunk ?
				player.getWorld().getChunk(packet.x, packet.z, V8Chunk.class) :
				null;
		// read chunk from byte data
		final V8Chunk newChunk =
				new V8Chunk(de.marcely.pocketcraft.java.component.v8.V8Chunk.read(
				packet.data,
				packet.primaryBitMask,
				null));
		
		// add it to system
		if(oldChunk == null){
			player.getWorld().addChunk(packet.x, packet.z, newChunk);
		}
		
		// finally send it
		player.sendPacket(newChunk.buildPacket(packet.x, packet.z));*/
	}
}