package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayServerChatMessage extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte POSITION_CHAT = 0;
	public static final byte POSITION_SYSTEM_MESSAGE = 1;
	public static final byte POSITION_GAME_INFO = 2;
	
	public ChatBaseComponent message;
	public byte position;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeChatJson(this.message);
		stream.writeByte(this.position);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.message = stream.readChatJson();
		this.position = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
