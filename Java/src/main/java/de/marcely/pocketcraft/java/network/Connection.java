package de.marcely.pocketcraft.java.network;

import java.io.Closeable;
import java.security.Key;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import lombok.Getter;

public abstract class Connection implements Closeable {
	
	private ConnectionInterface interf;
	@Getter private Key sharedKey = null;
	@Getter private int compressionThreshold = -1;
	@Getter private Protocol protocol;
	
	protected final Queue<Packet> packetReadQueue = new ConcurrentLinkedQueue<>();
	
	
	
	public abstract boolean isClosed();
	
	public abstract void write(Packet packet);
	
	
	
	public @Nullable Packet fetch(){
		return this.packetReadQueue.poll();
	}
	
	public void setInterface(ConnectionInterface interf){
		this.interf = interf;
	}
	
	public ConnectionInterface getInterface(){
		return this.interf;
	}
	
	public void setEncryptionKey(@Nullable Key sharedKey){
		this.sharedKey = sharedKey;
	}
	
	public void setCompressionThreshold(int threshold){
		this.compressionThreshold = threshold;
	}
	
	public void setProtocol(Protocol protocol){
		this.protocol = protocol;
	}
}