package de.marcely.pocketcraft.java.network.packet;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;
import de.marcely.pocketcraft.utils.io.ZLib;

public class PacketBuilder {
	
	public static Cipher CIPHER;
	
	static {
		try{
			CIPHER = Cipher.getInstance("AES/CFB8/NoPadding");
		}catch(NoSuchAlgorithmException | NoSuchPaddingException e){
			e.printStackTrace();
		}
	}
	
	public static byte[] deconstruct(Packet packet, int packetId, @Nullable Key sharedKey, int compressionThreshold) throws Exception {
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
		if(sharedKey != null){
			CIPHER.init(Cipher.ENCRYPT_MODE, sharedKey);
			data = CIPHER.doFinal(data);
		}
		
		return data;
	}
	
	@SuppressWarnings("resource")
	public static Packet construct(byte[] data, @Nullable Key sharedKey, int compressionThreshold, Protocol protocol, SequenceType seq, boolean isByClient) throws Exception {
		EByteArrayReader stream = new EByteArrayReader(data);
		
		// decrypt
		if(sharedKey != null){
			CIPHER.init(Cipher.DECRYPT_MODE, sharedKey);
			data = CIPHER.doFinal(data);
		}
		
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