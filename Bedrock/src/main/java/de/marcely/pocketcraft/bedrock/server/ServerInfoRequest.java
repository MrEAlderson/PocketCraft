package de.marcely.pocketcraft.bedrock.server;

import java.net.InetSocketAddress;

import com.whirvis.jraknet.identifier.MinecraftIdentifier;

import lombok.Getter;

public abstract class ServerInfoRequest {
	
	@Getter private final InetSocketAddress sender;
	
	public ServerInfoRequest(InetSocketAddress sender){
		this.sender = sender;
	}
	
	public abstract void reply(MinecraftIdentifier identifier);
}