package de.marcely.pocketcraft.java.network.protocol;

import de.marcely.pocketcraft.java.network.packet.handshake.v1.*;
import de.marcely.pocketcraft.java.network.packet.login.v1.*;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class ProtocolV8D9 extends Protocol {
	
	@Override
	public int getProtocolVersion(){
		return 47;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void defineIds(){
		setPacketIds(SequenceType.HANDSHAKE, CLIENT, new Class[]{
				V1PacketHandshake.class
		});
		
		setPacketIds(SequenceType.LOGIN, CLIENT, new Class[]{
				V1PacketLoginStart.class,
				V1PacketLoginEncryptionResponse.class,
				V1PacketLoginPluginReponse.class
		});
		
		setPacketIds(SequenceType.LOGIN, SERVER, new Class[]{
				V1PacketLoginDisconnect.class,
				V1PacketLoginEncryptionRequest.class,
				V1PacketLoginSuccess.class,
				V1PacketLoginSetCompression.class,
				V1PacketLoginPluginRequest.class
		});
	}

	@Override
	public Sequence newSequenceInstance(SequenceType type, SequenceHolder holder){
		return type.newV1ClientInstance(holder);
	}
}