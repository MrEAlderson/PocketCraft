package de.marcely.pocketcraft.bedrock.server.player.sequence;

import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketLogin;
import de.marcely.pocketcraft.bedrock.network.packet.PacketResourcePackStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketAvailableResourcePacks;
import de.marcely.pocketcraft.bedrock.network.packet.PacketAvailableResourcePacks2;
import de.marcely.pocketcraft.bedrock.network.packet.PacketLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public class LoginSequence extends Sequence {
	
	private static final byte STATE_WAITING = 0;
	private static final byte STATE_RESOURCE_PACK = 1;
	
	private byte state = STATE_WAITING;
	
	protected LoginSequence(BedrockClient player){
		super(player);
	}
	
	@Override
	public boolean onReceive(PCPacket packet){
		switch(state){
		case STATE_WAITING:
			handleWaiting(packet);
			break;
			
		case STATE_RESOURCE_PACK:
			handleResourcePack(packet);
			break;
		}
		
		return true;
	}
	
	private void handleWaiting(PCPacket rawPacket){
		if(rawPacket.getType() != PacketType.Login)
			return;
		
		final PacketLogin packet = (PacketLogin) rawPacket;
		
		player.setInfo(packet.info);
		player.setUsername(packet.username);
		
		{
			final PacketLoginStatus out = (PacketLoginStatus) PacketType.LoginStatus.newInstance();
			
			out.result = PacketLoginStatus.SUCCESS;
			
			player.sendPacket(out);
		}
		
		{
			final PacketAvailableResourcePacks out = (PacketAvailableResourcePacks) PacketType.AvailableResourcePacks.newInstance();
			
			out.mustAccept = true;
			out.behaviourPacks = new ResourcePack[0];
			out.resourcePacks = new ResourcePack[0];
			
			player.sendPacket(out);
		}
		
		this.state = STATE_RESOURCE_PACK;
	}
	
	private void handleResourcePack(PCPacket rawPacket){
		if(rawPacket.getType() != PacketType.ResourcePackStatus)
			return;
		
		final PacketResourcePackStatus packet = (PacketResourcePackStatus) rawPacket;
		
		if(packet.status == PacketResourcePackStatus.HAVE_ALL_PACKS){
			{
				final PacketAvailableResourcePacks2 out = (PacketAvailableResourcePacks2) PacketType.AvailableResourcePacks2.newInstance();
				
				out.mustAccept = false;
				out.behaviourPacks = new ResourcePack[0];
				out.resourcePacks = new ResourcePack[0];
				
				player.sendPacket(out);
			}
		
		}else if(packet.status == PacketResourcePackStatus.COMPLETED){
			// login done
			player.setSequence(Sequence.get(Sequence.PLAY, player));
		
			player.getServer().getListeners().forEach(listener -> listener.onConnect(player));
		}
	}
}