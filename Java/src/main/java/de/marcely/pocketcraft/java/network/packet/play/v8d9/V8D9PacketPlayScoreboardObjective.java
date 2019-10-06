package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayScoreboardObjective extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte MODE_CREATE = 0;
	public static final byte MODE_REMOVE = 1;
	public static final byte MODE_UPDATE = 2;
	
	public static final String TYPE_INTEGER = "integer";
	public static final String TYPE_HEARTS = "hearts";
	
	public String name;
	public byte mode;
	
	// only if mode = create or update
	public String value;
	public String type;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.name, 16);
		stream.writeByte(this.mode);
		
		if(this.mode == MODE_CREATE || mode == MODE_UPDATE){
			stream.writeString(this.value, 32);
			stream.writeString(this.type, 16);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.name = stream.readString(16);
		this.mode = stream.readByte();
		
		if(this.mode == MODE_CREATE || mode == MODE_UPDATE){
			this.value = stream.readString(32);
			this.type = stream.readString(16);
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
