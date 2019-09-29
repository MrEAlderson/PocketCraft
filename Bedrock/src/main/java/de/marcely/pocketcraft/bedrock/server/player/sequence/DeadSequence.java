package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.Player;

public class DeadSequence extends Sequence {

	protected DeadSequence(Player player){
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