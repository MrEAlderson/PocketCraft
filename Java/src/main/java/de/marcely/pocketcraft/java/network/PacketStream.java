package de.marcely.pocketcraft.java.network;

import java.io.Closeable;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.Packet;

public interface PacketStream extends Closeable {
	
	public boolean isClosed();
	
	public @Nullable Packet fetch();
}