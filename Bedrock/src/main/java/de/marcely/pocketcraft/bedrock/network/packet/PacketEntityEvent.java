package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityEvent extends PCPacket {
	
	public long entityId;
	public EntityEvent type;
	public int data;
	
	public PacketEntityEvent(){
		super(PacketType.EntityEvent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityId);
		writer.writeSignedByte(this.type.id);
		writer.writeSignedVarInt(this.data);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
