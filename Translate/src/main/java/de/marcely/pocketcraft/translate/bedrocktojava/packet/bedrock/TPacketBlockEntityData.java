package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockEntityData;
import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayUpdateSignText;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketBlockEntityData extends BedrockPacketTranslator<PacketBlockEntityData> {

	@Override
	public void handle(PacketBlockEntityData packet, Player player){
		final BlockEntity rawEntity = player.getWorld().getBlockEntity(packet.x, packet.y, packet.z);
		
		if(rawEntity == null)
			return;
		
		if(rawEntity.getType() == BlockEntityType.SIGN){
			final String rawLines = (String) ((BNBTValueCompound) packet.data.getValue()).getData().get("Text").getValue().getData();
			final String[] lines = rawLines.split("\n");
			
			// problem: player is sending this packet for every char that he writes and does not have a proper packet
			// that's indicating that he's done. but java only sends this packet once when the player closed the sign editor.
			// this hack allows to do the same thing: bedrock sends the same lines when he's done editing
			if(player.getWritingSignText() != null && player.getWritingSignText().equals(rawLines)){
    			player.setWritingSignText(null);
				
				{
    				final V8D9PacketPlayUpdateSignText out = new V8D9PacketPlayUpdateSignText();
    				
    				out.x = packet.x;
    				out.y = packet.y;
    				out.z = packet.z;
    				out.lines = new ChatBaseComponent[4];
    				
    				for(int i=0; i<out.lines.length; i++){
    					final String line = i < lines.length ? lines[i] : "";
    					
    					out.lines[i] = ChatBaseComponent.parsePlain(line);
    				}
    				
    				player.sendPacket(out);
    			}
			
			}else
				player.setWritingSignText(rawLines);
		}
	}
}
