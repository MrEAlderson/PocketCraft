package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketRespawn extends PCPacket {
	
	public static final byte STATE_SEARCHING_FOR_SPAWN = 0;
    public static final byte STATE_READY_TO_SPAWN = 1;
    public static final byte STATE_CLIENT_READY_TO_SPAWN = 2;
	
	public float posX, posY, posZ;
	public byte state;
	public long entityRuntimeId;
	
	public PacketRespawn(){
		super(PacketType.Respawn);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeVector(this.posX, this.posY, this.posZ);
		writer.writeSignedByte(this.state);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.posX = reader.readLFloat();
		this.posY = reader.readLFloat();
		this.posZ = reader.readLFloat();
		this.state = reader.readSignedByte();
		this.entityRuntimeId = reader.readUnsignedVarLong();
	}
}
