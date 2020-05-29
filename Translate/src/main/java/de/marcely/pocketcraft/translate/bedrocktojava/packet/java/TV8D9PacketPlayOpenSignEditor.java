package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayOpenSignEditor;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayOpenSignEditor extends JavaPacketTranslator<V8D9PacketPlayOpenSignEditor> {

	@Override
	public void handle(V8D9PacketPlayOpenSignEditor packet, Player player){
		// not possible :(
		// player sends sign text with PacketBlockEntityData
	}
}
