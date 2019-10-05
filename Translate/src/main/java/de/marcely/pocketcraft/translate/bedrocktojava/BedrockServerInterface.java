package de.marcely.pocketcraft.translate.bedrocktojava;

import de.marcely.pocketcraft.bedrock.server.ServerInfoRequest;
import de.marcely.pocketcraft.bedrock.server.ServerListener;
import de.marcely.pocketcraft.bedrock.server.player.Player;

public class BedrockServerInterface implements ServerListener {
	
	private final BedrockToJavaTranslator translator;
	
	public BedrockServerInterface(BedrockToJavaTranslator translator){
		this.translator = translator;
	}
	
	@Override
	public void onServerInfoRequest(ServerInfoRequest request){
		
	}

	@Override
	public void onConnect(Player player){
		
	}

	@Override
	public void onDisconnect(Player player){
		
	}
}
