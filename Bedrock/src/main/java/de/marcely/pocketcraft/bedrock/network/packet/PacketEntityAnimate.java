package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityAnimate extends PCPacket {
	
	public long entityId;
	public int type;
	public float unkown;
	
	public PacketEntityAnimate(){
		super(PacketType.EntityAnimate);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(type);
		writer.writeUnsignedVarLong(entityId);
		if((type & 0x80) != 0)
			writer.writeLFloat(unkown);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = reader.readSignedVarInt();
		this.entityId = reader.readUnsignedVarLong();
		if((type & 0x80) != 0)
			this.unkown = reader.readLFloat();
	}
	
	
	
	public static enum EntityAnimateType {
		
		SWING_ARM(1),
		WAKE_UP(3),
		CRITICAL_HIT(4),
		MAGIC_CRITICAL_HIT(5),
		ROW_RIGHT(128),
		ROW_LEFT(129);
		
		public static Map<Integer, EntityAnimateType> VALUES = new HashMap<>();
		
		public int id;
		
		static {
			for(EntityAnimateType type:values())
				VALUES.put(type.id, type);
		}
		
		private EntityAnimateType(int id){
			this.id = id;
		}
	}
}
