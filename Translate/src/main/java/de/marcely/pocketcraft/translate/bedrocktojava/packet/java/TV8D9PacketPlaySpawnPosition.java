package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketSetSpawnPosition;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnPosition;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class TV8D9PacketPlaySpawnPosition extends JavaPacketTranslator<V8D9PacketPlaySpawnPosition> {

	@Override
	public void handle(V8D9PacketPlaySpawnPosition packet, Player player){
		final World world = player.getWorld();
		
		world.setSpawnX(packet.x);
		world.setSpawnY(packet.y);
		world.setSpawnZ(packet.z);
		
		{
			final PacketSetSpawnPosition out = new PacketSetSpawnPosition();
			
			out.type = PacketSetSpawnPosition.TYPE_WORLD;
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.forceSpawn = false;
			
			player.sendPacket(out);
		}
	}
}
