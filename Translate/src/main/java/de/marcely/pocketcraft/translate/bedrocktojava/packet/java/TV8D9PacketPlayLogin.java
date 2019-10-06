package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientCommand;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientSettings;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientStanding;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayLogin;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayLogin extends JavaPacketTranslator<V8D9PacketPlayLogin> {

	@Override
	public void handle(V8D9PacketPlayLogin packet, Player player){
		{
			final V8D9PacketPlayClientSettings out = new V8D9PacketPlayClientSettings();
			
			out.locale = "en/EN";
			out.viewDistance = 16;
			out.chatMode = V8D9PacketPlayClientSettings.CHAT_ENABLED;
			out.chatColorsEnabled = true;
			out.capeEnabled = true;
			out.jacketEnabled = true;
			out.leftSleeveEnabled = true;
			out.rightSleeveEnabled = true;
			out.leftPantsEnabled = true;
			out.rightPantsEnabled = true;
			out.hatEnabled = true;
			
			player.sendPacket(out);
		}
		
		// tells the server that we successfully logged in
		{
			final V8D9PacketPlayClientCommand out = new V8D9PacketPlayClientCommand();
			
			out.command = V8D9PacketPlayClientCommand.COMMAND_PERFORM_RESPAWN;
			
			// player.sendPacket(out);
		}
		
		// test
		{
			final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
			
			out.isOnGround = true;
			
			// player.sendPacket(out);
		}
	}
}
