package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.component.TextFormat;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.EntityDebug;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketText extends BedrockPacketTranslator<PacketText> {

	@Override
	public void handle(PacketText packet, Player player){
		if(packet.type != PacketText.TYPE_CHAT)
			return;
		
		// debug
		if(packet.message.startsWith("p!")){
			final String[] args = packet.message.split(" ");
			final String command = args[0].replace("p!", "");
			
			if(command.equals("entity")){
				EntityDebug.INSTANCE = new EntityDebug(player, Byte.parseByte(args[1]), EntityType.valueOf(args[2].toUpperCase()), Float.parseFloat(args[3]));
				player.sendChatMessage(TextFormat.GREEN + "Ok. Enabled entity debug");
				
			}else if(command.equals("stop")){
				EntityDebug.INSTANCE = null;
				player.sendChatMessage(TextFormat.GREEN + "Ok. Disabled debug");
				
			}else {
				player.sendChatMessage(TextFormat.YELLOW + "Debug Commands:");
				player.sendChatMessage(" p!entity <mode [0=event, 1=flag1, 2=flag2, 3=flag_palyer, 4=animate, 5=armor, 6=meta]> <entity type> <data>");
				player.sendChatMessage(" p!stop");
			}
			
			return;
		}
		
		// send to java
		{
			final V8D9PacketPlayClientChatMessage out = new V8D9PacketPlayClientChatMessage();
			
			out.message = packet.message;
			
			player.sendPacket(out);
		}
	}
}
