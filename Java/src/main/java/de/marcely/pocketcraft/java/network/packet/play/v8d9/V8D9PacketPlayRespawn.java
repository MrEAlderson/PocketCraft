package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.Difficulty;
import de.marcely.pocketcraft.java.component.Dimension;
import de.marcely.pocketcraft.java.component.GameMode;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayRespawn extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public Dimension dimension;
	public Difficulty difficulty;
	public GameMode gamemode;
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
		this.dimension = Dimension.ofId(stream.readInt());
		this.difficulty = Difficulty.ofId(stream.readUnsignedByte());
		this.gamemode = GameMode.ofId(stream.readUnsignedByte());
		this.levelType = stream.readString(16);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}