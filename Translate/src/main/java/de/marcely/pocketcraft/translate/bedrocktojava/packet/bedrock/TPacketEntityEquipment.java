package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEquipment;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientSetHeldItemSlot;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.EntityDebug;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketEntityEquipment extends BedrockPacketTranslator<PacketEntityEquipment> {
	
	@Override
	public void handle(PacketEntityEquipment packet, Player player){
		if(EntityDebug.INSTANCE != null)
			EntityDebug.INSTANCE.onChangeSlot(packet.hotbarSlot);
		
		final V8D9PacketPlayClientSetHeldItemSlot out = new V8D9PacketPlayClientSetHeldItemSlot();
		
		out.slot = packet.hotbarSlot;
		
		player.sendPacket(out);
	}
}
