package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public class DeadSequence extends Sequence {

	protected DeadSequence(BedrockClient player){
		super(player);
	}
	
	@Override
	public boolean onSend(PCPacket packet){
		return false;
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		return false;
	}
}