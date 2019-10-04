package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketLoginPluginRequest extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int messageId;
	public String channel;
	public byte[] data;

	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.messageId);
		stream.writeIdentifier(this.channel);
		stream.write(this.data);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.messageId = stream.readVarInt();
		this.channel = stream.readIdentifier();
		this.data = stream.read(stream.readableBytes());
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
