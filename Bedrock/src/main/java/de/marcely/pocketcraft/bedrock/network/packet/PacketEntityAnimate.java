package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityAnimate extends PCPacket {
	
	public long entityID;
	public EntityAnimateType type;
	public float unkown;
	
	public PacketEntityAnimate(boolean in){
		super(in ? PacketType.InEntityAnimate : PacketType.OutEntityAnimate);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(type.id);
		writer.writeUnsignedVarLong(entityID);
		if((type.id & 0x80) != 0)
			writer.writeLFloat(unkown);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = EntityAnimateType.VALUES.get(reader.readSignedVarInt());
		this.entityID = reader.readUnsignedVarLong();
		if((type.id & 0x80) != 0)
			this.unkown = reader.readLFloat();
	}
	
	
	
	public static enum EntityAnimateType {
		
		SWING_ARM(1),
		WAKE_UP(3);
		
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
