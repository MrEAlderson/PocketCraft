package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityMove extends PCPacket {
	
	public long entityRuntimeId;
	public float x, y, z, yaw, pitch, headYaw;
	public boolean isOnGround, isTeleport;
	
	public PacketEntityMove(){
		super(PacketType.EntityMove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		
		{
			byte flags = 0;
			
			if(this.isTeleport)
				flags |= 0x02;
			
			if(this.isOnGround)
				flags |= 0x01;
			
			writer.writeSignedByte(flags);
		}
		
		writer.writeVector(this.x, this.y, this.z);
		writer.writeSignedByte((byte) ((double) this.pitch/(360D/256D)));
		writer.writeSignedByte((byte) ((double) this.headYaw/(360D/256D)));
		writer.writeSignedByte((byte) ((double) this.yaw/(360D/256D)));
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}