package de.marcely.pocketcraft.bedrock.network.packet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.utils.BinaryUtil;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.io.ZLib;

public class PacketBatch extends PCPacket {

	public byte[] payload;
	
	public PacketBatch(){
		super(PacketType.Batch);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.write(this.payload);
	}

	@Override
	public void decode(EByteArrayReader reader)  throws Exception {
		this.payload = reader.read(reader.available());
	}
	
	public List<PCPacket> readPayload() throws Exception {
		final List<PCPacket> list = new ArrayList<>();
		final EByteArrayReader reader = new EByteArrayReader(ZLib.inflate(payload, 64 * 1024 * 1024 * 2)); // max 2mb buffer
		
		while(reader.available() >= 1){
			if(list.size() >= 500){
				reader.close();
				throw new IOException("Reached max amounts of packets in a batch");
			}
			
			final EByteArrayReader reader2 = new EByteArrayReader(reader.readByteArray());
			final short id = (short) reader2.readUnsignedVarInt();
			final PacketType type = PacketType.TYPES_IN.get((short) id);
			
			if(type != null){
				final PCPacket packet = type.newInstance();
				packet.decode(reader2);
				list.add(packet);
			}else
				System.out.println("Received unkown packet '" + BinaryUtil.bytesToDisplayedHex((byte) id) + "'");
			
			reader2.close();
		}
		
		reader.close();
		
		return list;
	}
	
	public void writePayload(List<PCPacket> packets, int compressionLevel) throws Exception {
		final EByteArrayWriter writer = new EByteArrayWriter();
		
		for(PCPacket packet:packets){
			try(EByteArrayWriter writer2 = new EByteArrayWriter()){
				writer2.writeUnsignedVarInt(packet.getType().getId());
				packet.encode(writer2);
				
				writer.writeByteArray(writer2.toByteArray());
			}
		}
		
		this.payload = ZLib.deflate(writer.toByteArray(), compressionLevel);
		writer.close();
	}
}
