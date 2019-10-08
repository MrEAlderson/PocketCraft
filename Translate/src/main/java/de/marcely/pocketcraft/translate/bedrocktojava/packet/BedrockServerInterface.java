package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;

import de.marcely.pocketcraft.bedrock.network.Protocol;
import de.marcely.pocketcraft.bedrock.server.ServerInfoRequest;
import de.marcely.pocketcraft.bedrock.server.ServerListener;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import de.marcely.pocketcraft.java.client.ClientAdapter;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.ServerInfo;
import de.marcely.pocketcraft.java.network.ServerInfo.DetailedServerInfo;
import de.marcely.pocketcraft.translate.BedrockToJavaTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

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
					final DetailedServerInfo detailed = info.getDetailed();
					
					if(detailed == null)
						return;
					
					final String version = detailed.getVersionName().replaceAll("[^0-9.]", "");
					String software = detailed.getVersionName().replace(version, "");
					
					while(software.endsWith(" "))
						software = software.substring(0, software.length()-1);
					
					request.reply(new MinecraftIdentifier(
							detailed.getDescription().asPlainText(),
							Protocol.VERSION,
							version,
							detailed.getOnlinePlayers(),
							detailed.getMaxPlayers(),
							translator.getBedrockServer().getRakNet().getGloballyUniqueId(),
							software,
							""));
				}
			});
		}, LoginGoal.SERVER_INFO);
	}

	@Override
	public void onConnect(BedrockClient bedrockClient){
		this.translator.openConnection((client) -> {
			if(client == null)
				System.out.println("Failed to open a connection");
			
			client.registerListener(new ClientAdapter(){
				public void onConnect(){
					final Player player = new Player(translator, bedrockClient, client);
					final DefaultPacketListener listener = new DefaultPacketListener(player);
					
					player.getBedrock().registerListener(listener);
					player.getJava().registerPacketListener(listener);
					
					translator.addPlayer(player);
				}
				
				public void onDisconnect(){
					if(!bedrockClient.isGettingKicked())
						bedrockClient.getClient().disconnect();
					
					final Player player = translator.getPlayer(bedrockClient.getClient().getAddress());
					
					if(player == null)
						return;
					
					translator.removePlayer(player);
				}
			});
		}, LoginGoal.PLAY);
	}

	@Override
	public void onDisconnect(BedrockClient client){
		System.out.println("Client close");
		final Player player = translator.getPlayer(client.getClient().getAddress());
		
		if(player == null)
			return;
		
		player.getJava().close();
		
		translator.removePlayer(player);
	}
}
