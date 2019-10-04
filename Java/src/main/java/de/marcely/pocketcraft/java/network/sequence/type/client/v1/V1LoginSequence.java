package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.login.v1.V1PacketLoginEncryptionRequest;
import de.marcely.pocketcraft.java.network.packet.login.v1.V1PacketLoginEncryptionResponse;
import de.marcely.pocketcraft.java.network.packet.login.v1.V1PacketLoginSetCompression;
import de.marcely.pocketcraft.java.network.packet.login.v1.V1PacketLoginStart;
import de.marcely.pocketcraft.java.network.packet.login.v1.V1PacketLoginSuccess;
import de.marcely.pocketcraft.java.network.sequence.ClientLoginInfo;
import de.marcely.pocketcraft.java.network.sequence.ClientLoginResult;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.network.sequence.type.PlaySequence;

public class V1LoginSequence extends Sequence {
	
	public V1LoginSequence(SequenceHolder holder){
		super(holder);
	}
	
	@Override
	public SequenceType getType(){
		return SequenceType.LOGIN;
	}

	@Override
	public void run(Sequence oldSequence){
		final ClientLoginInfo info = (ClientLoginInfo) this.holder.getLoginInfo();
		final V1PacketLoginStart packet = new V1PacketLoginStart();
		
		packet.username = info.username;
		
		this.holder.sendPacket(packet);
	}
	
	@Override
	public boolean onReceive(Packet rawPacket){
		System.out.println(rawPacket.getClass().getName());
		
		// == encryption request
		if(rawPacket instanceof V1PacketLoginEncryptionRequest){
			final V1PacketLoginEncryptionRequest packet = (V1PacketLoginEncryptionRequest) rawPacket;
			
			// send
			{
				final V1PacketLoginEncryptionResponse out = new V1PacketLoginEncryptionResponse();
				Key key = null;
				
				try{
					final KeyGenerator generator = KeyGenerator.getInstance("AES/CFB8/NoPadding");
					
					generator.init(128);
					
					key = generator.generateKey();
					
					out.sharedKey = key.getEncoded();
				}catch(NoSuchAlgorithmException e){
					e.printStackTrace();
				}
				
				out.verifyToken = packet.verifyToken;
				out.write_publicKey = packet.publicKey;
				
				this.holder.sendPacket(out);
				
				this.holder.getConnection().getPacketBuilder().setKey(key);;
			}
		
		// == set compression
		}else if(rawPacket instanceof V1PacketLoginSetCompression){
			final V1PacketLoginSetCompression packet = (V1PacketLoginSetCompression) rawPacket;
			
			this.holder.getConnection().getPacketBuilder().setCompressionThreshold(packet.threshold);
		
		// == success
		}else if(rawPacket instanceof V1PacketLoginSuccess){
			final V1PacketLoginSuccess packet = (V1PacketLoginSuccess) rawPacket;
			final ClientLoginResult result = new ClientLoginResult();
			
			result.id = packet.id;
			result.username = packet.username;
			
			this.holder.completeLogin(result);
			this.holder.setSequence(new PlaySequence(this.holder));
		}
		
		return true;
	}
}