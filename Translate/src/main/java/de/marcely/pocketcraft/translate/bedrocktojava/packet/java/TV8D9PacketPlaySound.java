package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlaySound;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySound;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlaySound extends JavaPacketTranslator<V8D9PacketPlaySound> {

	@Override
	public void handle(V8D9PacketPlaySound packet, Player player){
		final PacketPlaySound out = new PacketPlaySound();
		
		out.soundName = packet.soundName;
		out.x = packet.posX;
		out.y = packet.posY;
		out.z = packet.posZ;
		out.volume = packet.volume;
		out.pitch = packet.pitch/63F;
		
		player.sendPacket(out);
	}
}
