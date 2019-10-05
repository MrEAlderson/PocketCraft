package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public abstract class JavaPacketTranslator<T extends Packet> {
	
	public abstract void handle(T packet, Player player);
}