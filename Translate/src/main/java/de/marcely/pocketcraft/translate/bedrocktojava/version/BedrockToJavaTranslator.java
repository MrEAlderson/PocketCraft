package de.marcely.pocketcraft.translate.bedrocktojava.version;

import java.io.IOException;

import com.whirvis.jraknet.RakNetException;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.client.ClientAdapter;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.translate.Translator;
import de.marcely.pocketcraft.utils.callback.R1Callback;
import lombok.Getter;

public class BedrockToJavaTranslator extends Translator {
	
	@Getter private final BedrockServer bedrockServer;
	@Getter private final Connection javaConnection;
	@Getter private final Protocol javaProtocol;
	
	public BedrockToJavaTranslator(BedrockServer bedrockServer, Connection javaConnection, Protocol javaProtocol){
		this.bedrockServer = bedrockServer;
		this.javaConnection = javaConnection;
		this.javaProtocol = javaProtocol;
	}
	
	public boolean run() throws IllegalStateException, RakNetException {
		final boolean result = this.bedrockServer.run();
		
		if(!result)
			return false;
		
		this.bedrockServer.registerListener(new BedrockServerInterface(this));
		
		return true;
	}
	
	public void openConnection(R1Callback<JavaClient> callback, LoginGoal goal){
		final JavaClient client = new JavaClient(this.javaConnection.clone(), this.javaProtocol, goal);
		
		client.registerListener(new ClientAdapter(){
			public void onReady(){
				client.unregisterListener(this);
				
				callback.call(client);
			}
		});
		
		try{
			if(!client.connect())
				throw new IOException("Failed for unkown reason");
		}catch(IOException e){
			e.printStackTrace();
			callback.call(null);
		}
	}
}