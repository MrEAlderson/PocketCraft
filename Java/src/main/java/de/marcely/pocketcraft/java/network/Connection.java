package de.marcely.pocketcraft.java.network;

import java.io.Closeable;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketBuilder;
import lombok.Getter;

public abstract class Connection implements Closeable {
	
	@Getter protected final PacketBuilder packetBuilder = new PacketBuilder();
	
	private ConnectionInterface interf;
	
	protected final Queue<Packet> packetReadQueue = new ConcurrentLinkedQueue<>();
	
	
	public abstract void open() throws IOException;
	
	public abstract void close() throws IOException;
	
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
}