package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketLoginPluginReponse extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int messageId;
	public boolean success;
	public byte[] data;

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.messageId);
		stream.writeBoolean(this.success);
		
		if(success)
			stream.write(this.data);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.messageId = stream.readVarInt();
		this.success = stream.readBoolean();
		
		if(this.success)
			this.data = stream.read(stream.readableBytes());
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
