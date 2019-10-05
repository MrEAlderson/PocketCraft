package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientSettings extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte CHAT_ENABLED = 0;
	public static final byte CHAT_COMMANDS_ONLY = 1;
	public static final byte CHAT_HIDDEN = 2;
	
	
	public String locale;
	public byte viewDistance;
	public byte chatMode;
	public boolean chatColorsEnabled;
	public boolean capeEnabled;
	public boolean jacketEnabled;
	public boolean leftSleeveEnabled;
	public boolean rightSleeveEnabled;
	public boolean leftPantsEnabled;
	public boolean rightPantsEnabled;
	public boolean hatEnabled;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.locale, 7);
		stream.writeByte(this.viewDistance);
		stream.writeByte(this.chatMode);
		stream.writeBoolean(this.chatColorsEnabled);
		{
			int skinParts = 0;
			
			if(this.capeEnabled) skinParts |= 0x01;
			if(this.jacketEnabled) skinParts |= 0x02;
			if(this.leftSleeveEnabled) skinParts |= 0x04;
			if(this.rightSleeveEnabled) skinParts |= 0x08;
			if(this.leftPantsEnabled) skinParts |= 0x10;
			if(this.rightPantsEnabled) skinParts |= 0x20;
			if(this.hatEnabled) skinParts |= 0x40;
			
			stream.writeUnsignedByte(skinParts);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.locale = stream.readString(7);
		this.viewDistance = stream.readByte();
		this.chatMode = stream.readByte();
		this.chatColorsEnabled = stream.readBoolean();
		{
			final int skinParts = stream.readUnsignedByte();
			
			if((skinParts & 0x01) > 0) this.capeEnabled = true;
			if((skinParts & 0x02) > 0) this.jacketEnabled = true;
			if((skinParts & 0x04) > 0) this.leftSleeveEnabled = true;
			if((skinParts & 0x08) > 0) this.rightSleeveEnabled = true;
			if((skinParts & 0x10) > 0) this.leftPantsEnabled = true;
			if((skinParts & 0x20) > 0) this.rightPantsEnabled = true;
			if((skinParts & 0x40) > 0) this.hatEnabled = true;
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
