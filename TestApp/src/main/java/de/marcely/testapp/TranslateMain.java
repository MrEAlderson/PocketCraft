package de.marcely.testapp;

import java.net.InetAddress;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.TCPClientConnection;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.protocol.ProtocolV8D9;
import de.marcely.pocketcraft.translate.bedrocktojava.version.BedrockToJavaTranslator;

public class TranslateMain {
	
	public static void main(String[] args) throws Exception {
		if(args.length < 4){
			System.out.println("Missing parameters: <bedrock address> <bedorck port> <java address> <java port>");
			return;
		}
		
		final BedrockServer server = new BedrockServer(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), 5);
		final Protocol protocol = new ProtocolV8D9();
		final Connection connection = new TCPClientConnection(InetAddress.getByName(args[2]), Integer.parseInt(args[3]));
		final BedrockToJavaTranslator translator = new BedrockToJavaTranslator(server, connection, protocol);
		
		System.out.println(translator.run());
	}
}
