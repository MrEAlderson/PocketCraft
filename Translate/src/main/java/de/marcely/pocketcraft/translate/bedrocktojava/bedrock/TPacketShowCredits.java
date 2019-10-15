package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketShowCredits;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientCommand;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketShowCredits extends BedrockPacketTranslator<PacketShowCredits> {

	@Override
	public void handle(PacketShowCredits packet, Player player){
		if(packet.status != PacketShowCredits.STATUS_END_CREDITS)
			return;
		
		// tell server that we left credits screen
		final V8D9PacketPlayClientCommand out = new V8D9PacketPlayClientCommand();
		
		out.command = V8D9PacketPlayClientCommand.COMMAND_PERFORM_RESPAWN;
		
		player.sendPacket(out);
	}
}
