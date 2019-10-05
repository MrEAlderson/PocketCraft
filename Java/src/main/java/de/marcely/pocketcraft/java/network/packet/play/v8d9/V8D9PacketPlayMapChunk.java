package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayMapChunk extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int x;
	public int z;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.x);
		stream.writeInt(this.z);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.x = stream.readInt();
		this.z = stream.readInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
