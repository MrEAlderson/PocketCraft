package de.marcely.pocketcraft.java.network.packet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;
import de.marcely.pocketcraft.utils.io.ZLib;
import lombok.Getter;
import lombok.Setter;

public class PacketBuilder {
	
	@Getter @Setter private Cipher readCipher, writeCipher;
	@Getter @Setter private int compressionThreshold = -1;
	
	private byte[] decryptBuffer = new byte[0];
	
	public PacketBuilder(){ }
	
	public void setKey(@Nullable Key key){
		if(key == null){
			this.readCipher = null;
			this.writeCipher = null;
			
			return;
		}
		
		this.writeCipher = getCipher(1, key);
		this.readCipher = getCipher(2, key);
	}
	
	private Cipher getCipher(int opmode, Key key){
		try{
			final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
			
			cipher.init(opmode, key, new IvParameterSpec(key.getEncoded()));
			
			return cipher;
		}catch(GeneralSecurityException e){
			throw new RuntimeException(e);
		}
	}
	
	public byte[] deconstruct(Packet packet, int packetId) throws Exception {
		final EByteArrayWriter stream = new EByteArrayWriter();
		byte[] data = null;
		int uncompressedDataLength = -1;
		
		// write data 
		{
			stream.writeSignedVarInt(packetId);
			packet.write(stream);
			
			data = stream.toByteArray();
			uncompressedDataLength = data.length;
			
			stream.reset();
		}
		
		// compress
		if(data.length >= compressionThreshold && compressionThreshold >= 0){
			data = ZLib.deflate(data);
		}
		
		// construct packet data part
		if(compressionThreshold >= 0){
			stream.writeSignedVarInt((data.length >= compressionThreshold) ? uncompressedDataLength : 0);
			stream.write(data);
			
			data = stream.toByteArray();
			
			stream.reset();
		}
		
		// construct packet
		{
			stream.writeSignedVarInt(data.length);
			stream.write(data);
			
			data = stream.toByteArray();
		}
		
		// encrypt
		if(this.writeCipher != null){
			data = this.writeCipher.update(data);
		}
		
		return data;
	}
	
	@SuppressWarnings("resource")
	public Packet construct(byte[] data, Protocol protocol, SequenceType seq, boolean isByClient) throws Exception {
		// decrypt
		if(this.readCipher != null){
			final int decryptedSize = this.readCipher.getOutputSize(data.length);
			
			if(this.decryptBuffer.length < decryptedSize)
				this.decryptBuffer = new byte[decryptedSize];
			
			data = Arrays.copyOfRange(this.decryptBuffer, 0, this.readCipher.update(data, 0, data.length, this.decryptBuffer));
		}
		
		EByteArrayReader stream = new EByteArrayReader(data);
		final int length = stream.readSignedVarInt();
		
		// decompress
		if(compressionThreshold >= 0){
			final int startPos = stream.getPos();
			final int uncompressedDataLength = stream.readSignedVarInt();
			
			if(uncompressedDataLength >= 1){
					data = ZLib.inflate(stream.read(length - (stream.getPos() - startPos)));
				
				if(data.length != uncompressedDataLength)
					throw new IOException("Uncompressed packet has an unexpected size (" + data.length + ", expected " + uncompressedDataLength + ")");
			}else
				data = stream.read(length - (stream.getPos() - startPos));
				
			stream = new EByteArrayReader(data);
		
		}else
			stream = new EByteArrayReader(stream.read(length));
		
		final int packetId = stream.readSignedVarInt();
		final Packet packet = protocol.getPacketById(packetId, seq, isByClient ? Packet.CLIENT : Packet.SERVER);
		
		if(packet == null)
			throw new IOException("Unkown packet with id " + packetId);
		
		packet.read(stream);
		
		return packet;
	}
}