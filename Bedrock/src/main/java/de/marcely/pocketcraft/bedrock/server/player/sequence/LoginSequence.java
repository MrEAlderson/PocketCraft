package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.Player;

public class LoginSequence extends Sequence {
	
	private static final byte STATE_RESOURCE_PACK = 0;
	
	private byte state = 0;
	
	protected LoginSequence(Player player){
		super(player);
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		return true;
	}
}