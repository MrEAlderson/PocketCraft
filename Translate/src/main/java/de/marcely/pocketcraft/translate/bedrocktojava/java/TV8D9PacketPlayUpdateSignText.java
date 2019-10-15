package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntitySign;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayUpdateSignText;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.FormattedTextTranslator;

public class TV8D9PacketPlayUpdateSignText extends JavaPacketTranslator<V8D9PacketPlayUpdateSignText> {

	@Override
	public void handle(V8D9PacketPlayUpdateSignText packet, Player player){
		final BlockEntitySign entity = (BlockEntitySign) player.getWorld().getBlockEntity(packet.x, packet.y, packet.z);
		
		{
			final String[] lines = new String[4];
			
			for(int i=0; i<4; i++)
				lines[i] = FormattedTextTranslator.chatToBedrock(packet.lines[i]);
			
			entity.setLines(lines);
		}
		
		player.updateBlockEntity(entity);
	}
}
