package de.marcely.pocketcraft.bedrock.component.world;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTTag;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;
import lombok.Setter;

public class BChunk {
	
	@Getter private final BChunkSection[] sections;
	@Getter private final byte[] biomes;
	@Getter @Setter private Map<Short, BlockEntity> blockEntities;
	
	public BChunk(){
		{
			this.sections = new BChunkSection[16];
			
			for(int i=0; i<this.sections.length; i++)
				this.sections[i] = new BChunkSection();
		}
		
		this.biomes = new byte[16*16];
	}
	
	public short getBlockId(int x, int y, int z){
		return this.sections[y >> 4].getBlockId(x, y & 0xF, z);
	}
	
	public byte getBlockData(int x, int y, int z){
		return this.sections[y >> 4].getBlockData(x, y & 0xF, z);
	}
	
	public void setBlockId(int x, int y, int z, short id){
		this.sections[y >> 4].setBlockId(x, y & 0xF, z, id);
	}
	
	public void setBlockData(int x, int y, int z, byte data){
		this.sections[y >> 4].setBlockData(x, y & 0xF, z, data);
	}
	
	public @Nullable BBiome getBiome(int x, int z){
		return BBiome.getById(getBiomeId(x, z));
	}
	
	public byte getBiomeId(int x, int z){
		return this.biomes[(x << 4) | z];
	}
	
	public void setBiome(int x, int z, BBiome biome){
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
				final BChunkSection section = this.sections[si];
				
				stream.writeSignedByte((byte) 0); // unkown
				stream.write(section.getData());
			}
		}
		
		stream.write(this.biomes); // biomes
		// border blocks amount
		// format: 4 bits (x pos), 4 bits (z pos)
		// will crash the client (maybe wrong format)
		stream.writeSignedByte((byte) 0);
		stream.writeSignedVarInt(0); // extra data amount
		
		// block entities
		try{
			if(this.blockEntities != null && this.blockEntities.size() >= 1){
				final BNBTValueCompound nbt = new BNBTValueCompound();
				
				for(Entry<Short, BlockEntity> e:this.blockEntities.entrySet()){
					final BlockEntity entity = e.getValue();
					
					// write
					{
						entity.write(nbt.getData());
						new BNBTTag("", nbt).write(stream, ByteOrder.LITTLE_ENDIAN, true);
						nbt.getData().clear();
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