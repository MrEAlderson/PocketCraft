package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayStatistics extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public Map<String, Integer> entries;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entries.size());
		
		for(Entry<String, Integer> entry:this.entries.entrySet()){
			stream.writeString(entry.getKey());
			stream.writeVarInt(entry.getValue());
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		final int size = stream.readVarInt();
		
		this.entries = new HashMap<>(size);
		
		for(int i=0; i<size; i++){
			this.entries.put(stream.readString(), stream.readVarInt());
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
