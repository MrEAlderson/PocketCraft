package de.marcely.pocketcraft.translate.bedrocktojava;

import de.marcely.pocketcraft.bedrock.server.ServerInfoRequest;
import de.marcely.pocketcraft.bedrock.server.ServerListener;
import de.marcely.pocketcraft.bedrock.server.player.Player;
import de.marcely.pocketcraft.java.client.ClientAdapter;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.ServerInfo;

public class BedrockServerInterface implements ServerListener {
	
	private final BedrockToJavaTranslator translator;
	
	public BedrockServerInterface(BedrockToJavaTranslator translator){
		this.translator = translator;
	}
	
	@Override
	public void onServerInfoRequest(ServerInfoRequest request){
		this.translator.openConnection((client) -> {
			if(client == null)
				System.out.println("Failed to open a connection");
			
			client.registerListener(new ClientAdapter(){
				public void onServerInfo(ServerInfo info){
					System.out.println("ping: " + info.getPing());
					System.out.println("status: " + info.getStatus());
				}
			});
		}, LoginGoal.SERVER_INFO);
	}

	@Override
	public void onConnect(Player player){
		
	}

	@Override
	public void onDisconnect(Player player){
		
	}
}
