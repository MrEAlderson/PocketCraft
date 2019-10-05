package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.packet.handshake.v1.V1PacketHandshake;
import de.marcely.pocketcraft.java.network.sequence.ClientLoginInfo;
import de.marcely.pocketcraft.java.network.sequence.ClientSequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class V1HandshakeSequence extends Sequence<ClientSequenceHolder> {

	public V1HandshakeSequence(ClientSequenceHolder holder){
		super(holder);
	}

	@Override
	public SequenceType getType(){
		return SequenceType.HANDSHAKE;
	}

	@Override
	public void run(Sequence<ClientSequenceHolder> oldSequence){
		final ClientLoginInfo info = (ClientLoginInfo) this.holder.getLoginInfo();
		final V1PacketHandshake packet = new V1PacketHandshake();
		
		packet.protocolVersion = info.protocolVersion;
		packet.serverAddress = info.serverAddress;
		packet.serverPort = info.serverPort;
		packet.nextState = info.nextState.getId();
		
		this.holder.sendPacket(packet);
		
		if(info.nextState == LoginGoal.SERVER_INFO)
			this.holder.setSequence(new V1StatusSequence(this.holder));
		else if(info.nextState == LoginGoal.PLAY)
			this.holder.setSequence(new V1LoginSequence(this.holder));
	}
}