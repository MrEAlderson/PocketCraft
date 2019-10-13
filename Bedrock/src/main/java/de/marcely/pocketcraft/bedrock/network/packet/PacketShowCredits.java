package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketShowCredits extends PCPacket {
	
	public static final byte STATUS_START_CREDITS = 0;
    public static final byte STATUS_END_CREDITS = 1;

    public long entityId;
    public byte status;
	
	public PacketShowCredits(){
		super(PacketType.ShowCredits);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityId);
		writer.writeSignedVarInt(this.status);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityId = reader.readUnsignedVarLong();
		this.status = (byte) reader.readSignedVarInt();
	}
}
