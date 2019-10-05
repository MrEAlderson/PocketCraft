package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.BlockFace;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntityPainting extends PCPacket {

	public long entityUID, entityRuntimeID;
	public float x, y, z;
	public BlockFace direction; // only horizontal!
	public String title;
	
	public PacketSpawnEntityPainting(){
		super(PacketType.SpawnEntityPainting);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(entityUID);
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeVector(x, y, z);
		writer.writeSignedVarInt(direction.getHorizontalId());
		writer.writeString(title);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
