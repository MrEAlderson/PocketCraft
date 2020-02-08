package de.marcely.testapp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.whirvis.jraknet.windows.UniversalWindowsProgram;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.TCPClientConnection;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.protocol.ProtocolV8D9;
import de.marcely.pocketcraft.translate.BedrockToJavaTranslator;
import de.marcely.pocketcraft.translate.Resources;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.IDBlockStatesList;
import lombok.Cleanup;

public class TranslateMain {
	
	public static void main(String[] args) throws Exception {
		try{
			final @Cleanup InputStream is = Resources.getResourceAsStream("blocks/1.8.json");
			
			IDBlockStatesList.load(new Gson().fromJson(new InputStreamReader(is), JsonObject.class));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(args.length < 4){
			System.out.println("Missing parameters: <bedrock address> <bedorck port> <java address> <java port>");
			return;
		}
		
		// Add loopback exemption for Minecraft
		if(!UniversalWindowsProgram.MINECRAFT.setLoopbackExempt(true)){
			System.err.println("Failed to add loopback exemption for Minecraft");
		}
		
		final BedrockServer server = new BedrockServer(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));
		final Protocol protocol = new ProtocolV8D9();
		final Connection connection = new TCPClientConnection(InetAddress.getByName(args[2]), Integer.parseInt(args[3]));
		final BedrockToJavaTranslator translator = new BedrockToJavaTranslator(server, connection, protocol);
		
		System.out.println(translator.run());
	}
}
