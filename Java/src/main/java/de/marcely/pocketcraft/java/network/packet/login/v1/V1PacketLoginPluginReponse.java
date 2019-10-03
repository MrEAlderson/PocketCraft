package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

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
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeSignedVarInt(this.messageId);
		stream.writeBoolean(this.success);
		
		if(success)
			stream.write(this.data);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.messageId = stream.readSignedVarInt();
		this.success = stream.readBoolean();
		
		if(this.success)
			this.data = stream.read(stream.available());
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
