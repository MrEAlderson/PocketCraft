package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySetExperience extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public float barPercentage; /* between 0 and 1 */
	public int level;
	public int totalExperience;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeFloat(this.barPercentage);
		stream.writeVarInt(this.level);
		stream.writeVarInt(this.totalExperience);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.barPercentage = stream.readFloat();
		this.level = stream.readVarInt();
		this.totalExperience = stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
