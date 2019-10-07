package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import lombok.Getter;
import lombok.Setter;

public abstract class Chunk {
	
	@Getter @Setter private boolean sent = false;
	
	public abstract PacketFullChunk buildPacket(int x, int z);
}