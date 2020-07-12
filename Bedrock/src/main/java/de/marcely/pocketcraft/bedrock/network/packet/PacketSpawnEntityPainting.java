package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.BBlockFace;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntityPainting extends PCPacket {

	public long entityUniqueId, entityRuntimeId;
	public float x, y, z;
	public BBlockFace direction; // only horizontal!
	public String title;
	
	public PacketSpawnEntityPainting(){
		super(PacketType.SpawnEntityPainting);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(this.entityUniqueId);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		writer.writeVector(this.x, this.y, this.z);
		writer.writeSignedVarInt(this.direction.getHorizontalId());
		writer.writeString(this.title);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
