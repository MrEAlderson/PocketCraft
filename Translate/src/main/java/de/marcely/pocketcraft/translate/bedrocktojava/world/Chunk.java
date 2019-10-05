package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;

public abstract class Chunk {
	
	public abstract PCPacket buildPacket(int x, int z);
}