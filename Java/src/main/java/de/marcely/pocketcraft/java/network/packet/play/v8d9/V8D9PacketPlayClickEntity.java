package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClickEntity extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte TYPE_INTERACT = 0;
	public static final byte TYPE_ATTACK = 1;
	public static final byte TYPE_INTERACT_AT = 2;
	
	public int targetEntityId;
	public byte type;
	// only if type = interact_at
	public float targetX;
	public float targetY;
	public float targetZ;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.targetEntityId);
		stream.writeVarInt(this.type);
		
		if(this.type == TYPE_INTERACT_AT){
			stream.writeFloat(this.targetX);
			stream.writeFloat(this.targetY);
			stream.writeFloat(this.targetZ);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.targetEntityId = stream.readVarInt();
		this.type = (byte) stream.readVarInt();
		
		if(this.type == TYPE_INTERACT_AT){
			this.targetX = stream.readFloat();
			this.targetY = stream.readFloat();
			this.targetZ = stream.readFloat();
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
