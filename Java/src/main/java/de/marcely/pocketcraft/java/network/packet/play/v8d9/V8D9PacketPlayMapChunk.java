package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayMapChunk extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int x;
	public int z;
	public byte[] data;
	public boolean isFullChunk;
	public int primaryBitMask;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.x);
		stream.writeInt(this.z);
		stream.writeBoolean(this.isFullChunk);
		stream.writeShort((short)(this.primaryBitMask & 0xFFFF));
		stream.writeByteArray(this.data);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.x = stream.readInt();
		this.z = stream.readInt();
		this.isFullChunk = stream.readBoolean();
		this.primaryBitMask = stream.readShort();
		this.data = stream.readByteArray();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
