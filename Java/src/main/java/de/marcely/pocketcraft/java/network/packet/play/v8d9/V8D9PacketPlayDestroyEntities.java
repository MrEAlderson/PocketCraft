package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayDestroyEntities extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int[] entityIds;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityIds.length);
		
		for(int id:this.entityIds)
			stream.writeVarInt(id);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityIds = new int[stream.readVarInt()];
		
		for(int i=0; i<this.entityIds.length; i++)
			this.entityIds[i] = stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
