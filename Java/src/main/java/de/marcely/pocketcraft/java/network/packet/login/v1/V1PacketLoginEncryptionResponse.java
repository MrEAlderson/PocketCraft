package de.marcely.pocketcraft.java.network.packet.login.v1;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginEncryptionResponse extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public PublicKey write_publicKey;
	public PrivateKey read_privateKey;
	
	public byte[] sharedKey;
	public byte[] verifyToken;

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		
		cipher.init(Cipher.ENCRYPT_MODE, this.write_publicKey);
		
		try(EByteArrayWriter stream2 = new EByteArrayWriter(64)){
			stream2.writeByteArray(this.sharedKey);
			stream2.writeByteArray(this.verifyToken);
			
			stream.write(cipher.doFinal(stream2.toByteArray()));
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void read(EByteArrayReader stream) throws Exception {
		// decrypt
		{
			final Cipher cipher = Cipher.getInstance("RSA");
		
			cipher.init(Cipher.DECRYPT_MODE, this.read_privateKey);
			
			stream = new EByteArrayReader(cipher.doFinal(stream.read(stream.available())));
		}
		
		// read
		this.sharedKey = stream.readByteArray();
		this.verifyToken = stream.readByteArray();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
