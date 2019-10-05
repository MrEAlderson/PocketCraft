package de.marcely.pocketcraft.bedrock.world;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class Chunk {
	
	@Getter private final ChunkSection[] sections;
	
	public Chunk(){
		this.sections = new ChunkSection[16];
		
		for(int i=0; i<this.sections.length; i++)
			this.sections[i] = new ChunkSection();
	}
	
	public short getBlockId(int x, int y, int z){
		return this.sections[y >> 4].getBlockId(x, y%16, z);
	}
	
	public byte getBlockData(int x, int y, int z){
		return this.sections[y >> 4].getBlockData(x, y%16, z);
	}
	
	public void setBlockId(int x, int y, int z, short id){
		this.sections[y >> 4].setBlockId(x, y%16, z, id);
	}
	
	public void setBlockData(int x, int y, int z, byte data){
		this.sections[y >> 4].setBlockData(x, y%16, z, data);
	}
	
	private byte[] getData() throws IOException {
		final EByteArrayWriter stream = new EByteArrayWriter();
		
		// sections
		{
			for(int si=0; si<16; si++){
				final ChunkSection section = this.sections[si];
				
				stream.writeSignedByte((byte) 0); // unkown
				stream.write(section.getData());
			}
		}
		
		stream.write(new byte[16*16]); // biomes
		stream.writeSignedByte((byte) 0); // unkown
		stream.writeSignedVarInt(0); // extra data
		
		// block entities
		{
			
		}
		
		stream.close();
		
		return stream.toByteArray();
	}
	
	public PCPacket buildPacket(int x, int z){
		final PacketFullChunk packet = (PacketFullChunk) PacketType.FullChunk.newInstance();
		
		packet.posX = x;
		packet.posZ = z;
		packet.sectionsAmount = 16;
		packet.isCachingEnabled = false;
		
		try{
			packet.data = getData();
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
		return packet;
	}
}