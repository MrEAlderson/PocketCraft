package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySound extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String soundName;
	public float posX, posY, posZ;
	public float volume;
	public short pitch; // 63 = 100%
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.soundName);
		stream.writeInt((int) (this.posX * 8F));
		stream.writeInt((int) (this.posY * 8F));
		stream.writeInt((int) (this.posZ * 8F));
		stream.writeFloat(this.volume);
		stream.writeUnsignedByte(this.pitch);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.soundName = stream.readString();
		this.posX = stream.readInt() / 8F;
		this.posY = stream.readInt() / 8F;
		this.posZ = stream.readInt() / 8F;
		this.volume = stream.readFloat();
		this.pitch = stream.readUnsignedByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}