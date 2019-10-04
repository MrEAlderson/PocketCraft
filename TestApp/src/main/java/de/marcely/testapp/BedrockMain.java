package de.marcely.testapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.whirvis.jraknet.RakNetException;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;

public class BedrockMain {

	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Missing parameters: <address> <port>");
			return;
		}
		
		BedrockServer server = null;
		
		try{
			server = new BedrockServer(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), 5);
		}catch(NumberFormatException | UnknownHostException e){
			e.printStackTrace();
		}
		
		try{
			System.out.println(server.run());
		}catch(IllegalStateException | RakNetException e){
			e.printStackTrace();
		}
	}
}
