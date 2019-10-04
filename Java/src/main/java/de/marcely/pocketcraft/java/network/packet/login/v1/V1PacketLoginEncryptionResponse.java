package de.marcely.pocketcraft.java.network.packet.login.v1;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

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
	public void write(EByteBuf stream) throws Exception {
		final Cipher cipher = Cipher.getInstance("RSA");
		
		cipher.init(Cipher.ENCRYPT_MODE, this.write_publicKey);
		
		{
			final int index = stream.writerIndex();
			stream.markWriterIndex();
			
			stream.writeByteArray(this.sharedKey);
			stream.writeByteArray(this.verifyToken);
			
			final byte[] data = Arrays.copyOfRange(stream.array(), index, stream.writerIndex());
			
			stream.resetWriterIndex();
			stream.write(cipher.doFinal(data));
		}
	}
	
	@Override
	public void read(EByteBuf stream) throws Exception {
		// decrypt
		{
			final Cipher cipher = Cipher.getInstance("RSA");
		
			cipher.init(Cipher.DECRYPT_MODE, this.read_privateKey);
			
			stream = new EByteBuf(cipher.doFinal(stream.read(stream.readableBytes())));
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
