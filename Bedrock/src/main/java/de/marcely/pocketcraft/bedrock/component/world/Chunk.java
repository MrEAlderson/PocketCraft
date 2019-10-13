package de.marcely.pocketcraft.bedrock.component.world;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;
import lombok.Setter;

public class Chunk {
	
	@Getter private final ChunkSection[] sections;
	@Getter private final byte[] biomes;
	@Getter @Setter private Map<Short, BlockEntity> blockEntities;
	
	public Chunk(){
		{
			this.sections = new ChunkSection[16];
			
			for(int i=0; i<this.sections.length; i++)
				this.sections[i] = new ChunkSection();
		}
		
		this.biomes = new byte[16*16];
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
	
	public @Nullable Biome getBiome(int x, int z){
		return Biome.getById(getBiomeId(x, z));
	}
	
	public byte getBiomeId(int x, int z){
		return this.biomes[(x << 4) | z];
	}
	
	public void setBiome(int x, int z, Biome biome){
		setBiome(x, z, biome.getId());
	}
	
	public void setBiome(int x, int z, byte id){
		this.biomes[(x << 4) | z] = id;
	}
	
	private byte[] getData(int chunkX, int chunkZ) throws IOException {
		final EByteArrayWriter stream = new EByteArrayWriter();
		
		// sections
		{
			for(int si=0; si<16; si++){
				final ChunkSection section = this.sections[si];
				
				stream.writeSignedByte((byte) 0); // unkown
				stream.write(section.getData());
			}
		}
		
		stream.write(this.biomes); // biomes
		stream.writeSignedByte((byte) 0); // unkown
		stream.writeSignedVarInt(0); // extra data
		
		// block entities
		try{
			if(this.blockEntities != null && this.blockEntities.size() >= 1){
				final NBTCompound nbt = new NBTCompound(ByteOrder.LITTLE_ENDIAN);
				
				for(Entry<Short, BlockEntity> e:this.blockEntities.entrySet()){
					final BlockEntity entity = e.getValue();
					
					// set pos
					{
						final short raw = e.getKey();
						
						entity.setX((chunkX << 4) + (raw & 0x000F));
						entity.setY((raw & 0x0FF0) >> 4);
						entity.setZ((chunkZ << 4) + ((raw & 0xF000) >> 12));
					}
					
					// write
					{
						entity.write(nbt);
						nbt.write(stream);
						nbt.clear();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		stream.close();
		
		return stream.toByteArray();
	}
	
	public PacketFullChunk buildPacket(int x, int z){
		final PacketFullChunk packet = (PacketFullChunk) PacketType.FullChunk.newInstance();
		
		packet.posX = x;
		packet.posZ = z;
		packet.sectionsAmount = 16;
		packet.isCachingEnabled = false;
		
		try{
			packet.data = getData(x, z);
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
		return packet;
	}
}