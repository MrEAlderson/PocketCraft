package de.marcely.pocketcraft.java.network.packet.login.v1;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketLoginEncryptionRequest extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String serverId;
	public PublicKey publicKey;
	public byte[] verifyToken;
	
	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.serverId);
		stream.writeByteArray(this.publicKey.getEncoded());
		stream.writeByteArray(this.verifyToken);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.serverId = stream.readString(20);
		{
			try{
				final EncodedKeySpec spec = new X509EncodedKeySpec(stream.readByteArray());
				final KeyFactory factory = KeyFactory.getInstance("RSA");
				
				this.publicKey = factory.generatePublic(spec);
			}catch(Exception e){
				System.out.println("Public key reconstitute failed!");
			}
		}
		this.verifyToken = stream.readByteArray();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
