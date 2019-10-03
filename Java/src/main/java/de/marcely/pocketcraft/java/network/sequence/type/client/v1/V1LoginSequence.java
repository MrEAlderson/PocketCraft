package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

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

	private byte state = -1;
	
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
		
		this.state = 0;
	}
	
	@Override
	public boolean onReceive(Packet rawPacket){
		// == encryption request
		if(rawPacket instanceof V1PacketLoginEncryptionRequest){
			if(this.state != 0)
				return true;
			
			final V1PacketLoginEncryptionRequest packet = (V1PacketLoginEncryptionRequest) rawPacket;
			
			this.holder.getConnection().setEncryptionKey(new SecretKeySpec(packet.publicKey, "AES"));
			
			// send
			{
				final V1PacketLoginEncryptionResponse out = new V1PacketLoginEncryptionResponse();
				Key key = null;
				
				try{
					key = KeyGenerator.getInstance("AES").generateKey();
					
					out.sharedKey = key.getEncoded();
				}catch(NoSuchAlgorithmException e){
					e.printStackTrace();
				}
				
				out.verifyToken = packet.verifyToken;
				
				this.holder.sendPacket(out);
				
				this.holder.getConnection().setEncryptionKey(key);
			}
			
			this.state = 1;
		
		// == set compression
		}else if(rawPacket instanceof V1PacketLoginSetCompression){
			if(this.state != 1)
				return true;
			
			final V1PacketLoginSetCompression packet = (V1PacketLoginSetCompression) rawPacket;
			
			this.holder.getConnection().setCompressionThreshold(packet.threshold);
		
		// == success
		}else if(rawPacket instanceof V1PacketLoginSuccess){
			if(this.state != 1)
				return true;
			
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