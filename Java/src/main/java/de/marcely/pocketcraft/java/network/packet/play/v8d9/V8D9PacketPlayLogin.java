package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.Difficulty;
import de.marcely.pocketcraft.java.component.Dimension;
import de.marcely.pocketcraft.java.component.GameMode;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayLogin extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public GameMode gamemode;
	public boolean isHardcore;
	public Dimension dimension;
	public Difficulty difficulty;
	public int maxPlayers;
	public String levelType;
	public int viewDistance;
	public boolean reducedDebugInfo;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.entityId);
		stream.writeUnsignedByte(this.gamemode.getId() | (this.isHardcore ? 0x8 : 0));
		stream.writeByte(this.dimension.getId());
		stream.writeUnsignedByte(this.difficulty.getId());
		stream.writeUnsignedByte(this.maxPlayers);
		stream.writeString(this.levelType, 16);
		stream.writeBoolean(this.reducedDebugInfo);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readInt();
		{
			final int gm = stream.readUnsignedByte();
			
			this.gamemode = GameMode.ofId(gm & 0xFFFFFFF7);
			this.isHardcore = ((gm & 0x8) == 0x8);
		}
		this.dimension = Dimension.ofId(stream.readByte());
		this.difficulty = Difficulty.ofId(stream.readUnsignedByte());
		this.maxPlayers = stream.readUnsignedByte();
		this.levelType = stream.readString(16);
		this.reducedDebugInfo = stream.readBoolean();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
