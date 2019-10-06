package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayScoreboardScore extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte ACTION_CREATE_UPDATE = 0;
	public static final byte ACTION_REMOVE = 1;
	
	public String scoreName;
	public byte action;
	public String objectiveName;
	
	// only if action = create_update
	public int value;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.scoreName, 40);
		stream.writeByte(this.action);
		stream.writeString(this.objectiveName, 16);
		
		if(this.action == ACTION_CREATE_UPDATE){
			stream.writeVarInt(this.value);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.scoreName = stream.readString(40);
		this.action = stream.readByte();
		this.objectiveName = stream.readString(16);
		
		if(this.action == ACTION_CREATE_UPDATE){
			this.value = stream.readVarInt();
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
