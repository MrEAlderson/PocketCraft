package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.component.TextFormat;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.EntityDebug;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.java.TV8D9PacketPlayBlockBreakAnimation;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
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
				
			}else if(command.equals("blockinfo")){
				final int x = (int) player.getEntity().getX();
				final int y = (int) player.getEntity().getY() - 1;
				final int z = (int) player.getEntity().getZ();
				
				player.sendChatMessage(TextFormat.AQUA + "X" + x + " Y" + y + " Z" + z);
				player.sendChatMessage("" + TextFormat.GREEN + TextFormat.BOLD + "State: " + TextFormat.GREEN + player.getWorld().getBlockState(x, y, z));
				
			}else if(command.equals("resendchunks")){
				for(Chunk c:player.getWorld().getChunks())
					c.setSent(false);
				
				player.sendChatMessage(TextFormat.GREEN + "" + player.getWorld().getChunks().size() + " chunks were marked as \"not sent\"");
				
			}else {
				player.sendChatMessage(TextFormat.YELLOW + "Debug Commands:");
				player.sendChatMessage(" p!entity <mode [0=event, 1=flag1, 2=flag2, 3=flag_palyer, 4=animate, 5=armor, 6=meta]> <entity type> <data>");
				player.sendChatMessage(" p!stop");
				player.sendChatMessage(" p!blockinfo (displays block info of the block below you)");
				player.sendChatMessage(" p!resendchunks");
			}
			
			return;
		}
		
		TV8D9PacketPlayBlockBreakAnimation.asd = Double.parseDouble(packet.message);
		/*{
			final PacketWorldEvent out = new PacketWorldEvent();
			final String[] parts = packet.message.split(" ");
			
			out.x = (int) player.getX() + 1;
			out.y = (int) player.getY();
			out.z = (int) player.getZ();
			out.type = Integer.parseInt(parts[0]);
			out.data = Integer.parseInt(parts[1]);
			
			player.sendPacket(out);
		}*/
		
		// send to java
		{
			final V8D9PacketPlayClientChatMessage out = new V8D9PacketPlayClientChatMessage();
			
			out.message = packet.message;
			
			player.sendPacket(out);
		}
	}
}
