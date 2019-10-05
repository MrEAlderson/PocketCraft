package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class DefaultPacketListener implements de.marcely.pocketcraft.bedrock.server.player.PacketAdapter, de.marcely.pocketcraft.java.network.packet.PacketAdapter {
	
	private final Player player;
	
	public DefaultPacketListener(Player player){
		this.player = player;
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		
		return true;
	}
	
	@Override
	public boolean onReceive(Packet packet){
		final JavaPacketTranslator<?> translator = this.player.getTranslator().getTranslator(packet);
		
		if(translator != null){
			System.out.println("translate: " + packet.getClass().getName());
			
			try{
				translator.handle0(packet, this.player);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
