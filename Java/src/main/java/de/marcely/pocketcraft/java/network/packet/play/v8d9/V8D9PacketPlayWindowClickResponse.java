package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWindowClickResponse extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public byte windowId;
	public short transactionId;
	public boolean hasBeenAccepted;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.windowId);
		stream.writeShort(this.transactionId);
		stream.writeBoolean(this.hasBeenAccepted);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.windowId = stream.readByte();
		this.transactionId = stream.readShort();
		this.hasBeenAccepted = stream.readBoolean();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
