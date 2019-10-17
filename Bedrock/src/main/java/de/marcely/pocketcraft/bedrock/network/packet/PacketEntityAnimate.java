package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityAnimate extends PCPacket {
	
	public static final byte TYPE_SWING_ARM = 1;
	public static final byte TYPE_WAKE_UP = 3;
	public static final byte TYPE_CRITICAL_HIT = 4;
	public static final byte TYPE_MAGIC_CRITICAL_HIT = 5;
	public static final short TYPE_ROW_RIGHT = 128;
	public static final short TYPE_ROW_LEFT = 129;
	
	public long entityId;
	public int type;
	public float rowingTime;
	
	public PacketEntityAnimate(){
		super(PacketType.EntityAnimate);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.type);
		writer.writeUnsignedVarLong(this.entityId);
		
		if(this.type == TYPE_ROW_RIGHT || this.type == TYPE_ROW_LEFT)
			writer.writeLFloat(this.rowingTime);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = reader.readSignedVarInt();
		this.entityId = reader.readUnsignedVarLong();
		
		if(this.type == TYPE_ROW_RIGHT || this.type == TYPE_ROW_LEFT)
			this.rowingTime = reader.readLFloat();
	}
}
