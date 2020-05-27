package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketChangeDimension extends PCPacket {

	public Dimension dimension;
	public float posX, posY, posZ;
	// true: player dies in an dimension other than the overworld
	public boolean respawn;
	
	public PacketChangeDimension(){
		super(PacketType.ChangeDimension);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.dimension.getId());
		writer.writeVector(this.posX, this.posY, this.posZ);
		writer.writeBoolean(this.respawn);
		
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
