package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayScoreboardDisplay;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayScoreboardDisplay.*;

import de.marcely.pocketcraft.bedrock.network.packet.PacketScoreboardDisplay;

public class TV8D9PacketPlayScoreboardDisplay extends JavaPacketTranslator<V8D9PacketPlayScoreboardDisplay> {

	@Override
	public void handle(V8D9PacketPlayScoreboardDisplay packet, Player player){
		final PacketScoreboardDisplay out = new PacketScoreboardDisplay();
		
		switch(packet.position){
		case POSITION_SIDEBAR:
			out.position = PacketScoreboardDisplay.POSITION_SIDEBAR;
			break;
			
		case POSITION_LIST:
			out.position = PacketScoreboardDisplay.POSITION_LIST;
			break;
			
		// below name is not supported
		default:
			return;
		}
	}
}
