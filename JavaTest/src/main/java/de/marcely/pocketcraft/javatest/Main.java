package de.marcely.pocketcraft.javatest;

import java.net.InetAddress;

import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.TCPClientConnection;
import de.marcely.pocketcraft.java.network.protocol.ProtocolV8D9;

public class Main {

	public static void main(String[] args) throws Exception {
		if(args.length < 2){
			System.out.println("Missing parameters: <address> <port>");
			return;
		}
		
		final JavaClient client = new JavaClient(
				new TCPClientConnection(InetAddress.getByName(args[0]), Integer.parseInt(args[1])),
				new ProtocolV8D9(),
				LoginGoal.PLAY);
		
		System.out.println(client.connect());
	}
}
