package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayKick extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public ChatBaseComponent message;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeChatPlain(this.message);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.message = stream.readChatPlain();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
