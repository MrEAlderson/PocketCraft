package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.v8.V8Difficulty;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayGameDifficulty extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public V8Difficulty difficulty;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeUnsignedByte(this.difficulty.getId());
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.difficulty = V8Difficulty.getById(stream.readUnsignedByte());
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
