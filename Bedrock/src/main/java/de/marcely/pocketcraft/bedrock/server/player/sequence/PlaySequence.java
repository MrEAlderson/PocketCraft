package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.Player;

public class PlaySequence extends Sequence {

	protected PlaySequence(Player player){
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
