package de.marcely.pocketcraft.java.network.packet;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.io.ZLib;
import lombok.Getter;
import lombok.Setter;

public class PacketBuilder {
	
	private Cipher readCipher, writeCipher;
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
		final EByteBuf stream = new EByteBuf();
		byte[] data = null;
		int uncompressedDataLength = -1;
		
		// write data 
		{
			stream.writeVarInt(packetId);
			packet.write(stream);
			
			data = Arrays.copyOfRange(stream.array(), 0, stream.writerIndex());
			uncompressedDataLength = data.length;
			
			stream.resetWriterIndex();
		}
		
		// compress
		if(data.length >= compressionThreshold && compressionThreshold >= 0){
			data = ZLib.deflate(data);
		}
		
		// construct packet data part
		if(compressionThreshold >= 0){
			stream.writeVarInt((data.length >= compressionThreshold) ? uncompressedDataLength : 0);
			stream.write(data);
			
			data = Arrays.copyOfRange(stream.array(), 0, stream.writerIndex());
			
			stream.resetWriterIndex();
		}
		
		// construct packet
		{
			stream.writeVarInt(data.length);
			stream.write(data);
			
			data = Arrays.copyOfRange(stream.array(), 0, stream.writerIndex());
			stream.release();
		}
		
		// encrypt
		if(this.writeCipher != null){
			data = this.writeCipher.update(data);
		}
		
		return data;
	}
	
	public void construct(EByteBuf stream, Protocol protocol, SequenceType seq, boolean isByClient, List<Object> out) throws Exception {
		// decrypt
		if(this.readCipher != null){
			final byte[] data = stream.read(stream.readableBytes());
			final int decryptedSize = this.readCipher.getOutputSize(stream.readableBytes());
			
			if(this.decryptBuffer.length < decryptedSize)
				this.decryptBuffer = new byte[decryptedSize];
			
			stream = new EByteBuf(Arrays.copyOfRange(this.decryptBuffer, 0, this.readCipher.update(data, 0, data.length, this.decryptBuffer)));
		}
		
		stream.markReaderIndex();
		
		final int length = stream.readVarInt();
		
		if(stream.readableBytes() < length){
			stream.resetReaderIndex();
			return;
		}
		
		if(length < 0)
			throw new IOException("Malformed packet: Packet is smaller than 0");
		
		if(length == 0)
			return;
		
		stream = stream.readAsBuf(length);
		
		try{
		
			// decompress
			if(this.compressionThreshold >= 0){
				final int uncompressedDataLength = stream.readVarInt();
				
				if(uncompressedDataLength != 0){
					if(uncompressedDataLength < this.compressionThreshold)
						throw new IOException("Malformed packet: Uncompressed packet is below threshold " + this.compressionThreshold);
					
					if(uncompressedDataLength > 2097152)
						throw new IOException("Malformed packet: Uncompressed packet is larger than limitation");
					
					final byte[] data = ZLib.inflate(stream.read(stream.readableBytes()));
					
					if(stream.readableBytes() < data.length)
						return;
					
					stream.release();
					stream = new EByteBuf(data);
				}
			}
			
			if(stream.readableBytes() == 0)
				return;
			
			final int packetId = stream.readVarInt();
			
			final Packet packet = protocol.getPacketById(packetId, seq, isByClient ? Protocol.CLIENT : Protocol.SERVER);
			
			if(packet == null)
				throw new IOException("Unkown packet with id " + packetId);
	
			
			packet.read(stream);
			
			out.add(packet);
		
		}finally{
			stream.release();
		}
	}
}