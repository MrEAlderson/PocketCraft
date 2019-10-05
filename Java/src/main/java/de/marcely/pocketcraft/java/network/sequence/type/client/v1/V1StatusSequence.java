package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import de.marcely.pocketcraft.java.network.ServerInfo;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.status.v1.V1PacketStatusPing;
import de.marcely.pocketcraft.java.network.packet.status.v1.V1PacketStatusPong;
import de.marcely.pocketcraft.java.network.packet.status.v1.V1PacketStatusRequest;
import de.marcely.pocketcraft.java.network.packet.status.v1.V1PacketStatusResponse;
import de.marcely.pocketcraft.java.network.sequence.ClientSequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class V1StatusSequence extends Sequence<ClientSequenceHolder> {
	
	private ServerInfo info;
	
	public V1StatusSequence(ClientSequenceHolder holder){
		super(holder);
	}
	
	@Override
	public SequenceType getType(){
		return SequenceType.STATUS;
	}

	@Override
	public void run(Sequence<ClientSequenceHolder> oldSequence){
		this.holder.sendPacket(new V1PacketStatusRequest());
	}
	
	@Override
	public boolean onReceive(Packet rawPacket){
		if(rawPacket instanceof V1PacketStatusResponse){
			this.info = new ServerInfo(((V1PacketStatusResponse) rawPacket).response, System.currentTimeMillis());
			
			final V1PacketStatusPing out = new V1PacketStatusPing();
			
			out.time = System.currentTimeMillis();
			
			this.holder.sendPacket(out);
		
		}else if(rawPacket instanceof V1PacketStatusPong){
			final long time = ((V1PacketStatusPong) rawPacket).time;
			
			this.info.setPing(System.currentTimeMillis() - time);
			
			this.holder.onServerInfo(this.info);
		}
		
		return true;
	}
}
