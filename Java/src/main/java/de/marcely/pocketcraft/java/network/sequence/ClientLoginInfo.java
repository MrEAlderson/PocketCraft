package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.LoginGoal;

public class ClientLoginInfo {
	
	public int protocolVersion;
	public String serverAddress;
	public int serverPort;
	public LoginGoal nextState;
	
	public String username;
}
