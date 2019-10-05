package de.marcely.pocketcraft.bedrock.server.player.sequence;

import java.security.InvalidParameterException;

import de.marcely.pocketcraft.bedrock.server.player.PacketAdapter;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public abstract class Sequence implements PacketAdapter {
	
	public static final byte DEAD = 0;
	public static final byte LOGIN = 1;
	public static final byte PLAY = 2;
	
	protected final BedrockClient player;
	
	protected Sequence(BedrockClient player){
		this.player = player;
	}
	
	public static Sequence get(byte type, BedrockClient player){
		switch(type){
		case DEAD:
			return new DeadSequence(player);
		case LOGIN:
			return new LoginSequence(player);
		case PLAY:
			return new PlaySequence(player);
		default:
			throw new InvalidParameterException("type is null");
		}
	}
}
