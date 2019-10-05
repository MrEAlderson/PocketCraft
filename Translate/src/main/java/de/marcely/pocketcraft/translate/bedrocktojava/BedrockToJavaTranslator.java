package de.marcely.pocketcraft.translate.bedrocktojava;

import com.whirvis.jraknet.RakNetException;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.translate.Translator;
import de.marcely.pocketcraft.utils.callback.R1Callback;
import lombok.Getter;

public class BedrockToJavaTranslator extends Translator {
	
	@Getter private final BedrockServer bedrockServer;
	
	public BedrockToJavaTranslator(BedrockServer bedrockServer){
		this.bedrockServer = bedrockServer;
	}
	
	public boolean run() throws IllegalStateException, RakNetException {
		final boolean result = this.bedrockServer.run();
		
		if(!result)
			return false;
		
		this.bedrockServer.registerListener(new BedrockServerInterface(this));
		
		return true;
	}
	
	public void openConnection(R1Callback<JavaClient> callback){
		
	}
}