package de.marcely.pocketcraft.java.network.sequence;

public class ClientLoginInfo {
	
	public int protocolVersion;
	public String serverAddress;
	public int serverPort;
	public SequenceType nextState;
	
	public String username;
}
