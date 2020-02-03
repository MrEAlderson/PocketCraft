package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

import org.apache.commons.io.IOUtils;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBiomeDefinitionList extends PCPacket {

	private static final byte[] LIST;
	
	public byte[] list;
	
	static {
		byte[] table = null;
		
		try{
			table = IOUtils.toByteArray(Resources.getResourceAsStream("biome_definitions.dat"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LIST = table;
	}
	
	public PacketBiomeDefinitionList(){
		super(PacketType.BiomeDefinitionList);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.write((this.list != null) ? this.list : LIST);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.list = reader.read(reader.available());
	}
}
