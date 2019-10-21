package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.v8.V8Difficulty;
import de.marcely.pocketcraft.java.component.v8.V8Dimension;
import de.marcely.pocketcraft.java.component.v8.V8GameMode;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayRespawn extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public V8Dimension dimension;
	public V8Difficulty difficulty;
	public V8GameMode gamemode;
	public String levelType;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.dimension.getId());
		stream.writeUnsignedByte(this.difficulty.getId());
		stream.writeUnsignedByte(this.gamemode.getId());
		stream.writeString(this.levelType, 16);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.dimension = V8Dimension.getById(stream.readInt());
		this.difficulty = V8Difficulty.getById(stream.readUnsignedByte());
		this.gamemode = V8GameMode.getById(stream.readUnsignedByte());
		this.levelType = stream.readString(16);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}