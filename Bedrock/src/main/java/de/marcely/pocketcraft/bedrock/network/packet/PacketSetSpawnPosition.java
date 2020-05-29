package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketSetSpawnPosition extends PCPacket {
	
	public static final byte TYPE_PLAYER = 0;
	public static final byte TYPE_WORLD = 1;
	
	public byte type;
	public int x;
	public int y;
	public int z;
	public boolean forceSpawn;
	
	public PacketSetSpawnPosition(){
		super(PacketType.SetSpawnPosition);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.type);
		writer.writeBlockPosition(this.x, this.y, this.z);
		writer.writeBoolean(this.forceSpawn);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
