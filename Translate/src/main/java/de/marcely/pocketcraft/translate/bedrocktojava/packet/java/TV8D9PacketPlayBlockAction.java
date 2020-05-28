package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockEvent;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockAction;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;

public class TV8D9PacketPlayBlockAction extends JavaPacketTranslator<V8D9PacketPlayBlockAction> {

	@Override
	public void handle(V8D9PacketPlayBlockAction packet, Player player){
		final BlockState state = player.getWorld().getBlockState(packet.x, packet.y, packet.z);
		
		if(state == null){
			System.out.println("Received block action at X" + packet.x + " Y" + packet.y + " Z" + packet.z + ", but there's no block");
			return;
		}
		
		switch(state.getBedrockId()){
		case 54: // normal chest
		case 146: // trapped chest
		case 130: // ender chest
			// open/close event
			if(packet.data1 == 1){
				playBlockEvent(1, packet.data2 == 1 ? 2 : 0, packet.x, packet.y, packet.z, player);
			}
			
			break;
			
		case 25: // note block
			playBlockEvent(packet.data1, packet.data2, packet.x, packet.y, packet.z, player);
			break;
		}
	}
	
	private static void playBlockEvent(int case1, int case2, int x, int y, int z, Player player){
		final PacketBlockEvent out = new PacketBlockEvent();
		
		out.posX = x;
		out.posY = y;
		out.posZ = z;
		out.case1 = case1;
		out.case2 = case2;
		
		player.sendPacket(out);
	}
}
