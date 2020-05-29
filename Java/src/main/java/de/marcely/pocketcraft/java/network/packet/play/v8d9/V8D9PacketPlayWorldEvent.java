package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayWorldEvent extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int effectId;
	public Vector3 position;
	public int data;
	public boolean disableRelVolume;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.effectId);
		stream.writeBlockPosition(this.position);
		stream.writeInt(this.data);
		stream.writeBoolean(this.disableRelVolume);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.effectId = stream.readInt();
		this.position = stream.readBlockPosition();
		this.data = stream.readInt();
		this.disableRelVolume = stream.readBoolean();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
