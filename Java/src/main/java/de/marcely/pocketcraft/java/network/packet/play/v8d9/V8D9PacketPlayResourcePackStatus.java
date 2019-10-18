package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayResourcePackStatus extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte RESULT_SUCCESSFULLY_LOADED = 0;
	public static final byte RESULT_DECLINED = 1;
	public static final byte RESULT_DOWNLOAD_FAILED = 2;
	public static final byte RESULT_ACCEPTED = 3;
	
	public String resourcePackHash;
	public byte result;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.resourcePackHash, 40);
		stream.writeVarInt(this.result);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.resourcePackHash = stream.readString(40);
		this.result = (byte) stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
