package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public class PlaySequence extends Sequence {

	protected PlaySequence(BedrockClient player){
		super(player);
	}
	
	@Override
	public boolean onSend(PCPacket packet){
		return true;
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		return true;
	}
}
