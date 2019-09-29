package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutBlockChange extends PCPacket {
	
    public static final int FLAG_NONE = 0b0000;
    public static final int FLAG_NEIGHBORS = 0b0001;
    public static final int FLAG_NETWORK = 0b0010;
    public static final int FLAG_NOGRAPHIC = 0b0100;
    public static final int FLAG_PRIORITY = 0b1000;
	
	public int x, y, z, data, flag;
	
	public PacketOutBlockChange(){
		super(PacketType.OutBlockChange);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(x);
		writer.writeUnsignedVarInt(y);
		writer.writeSignedVarInt(z);
		writer.writeUnsignedVarInt(data);
		writer.writeUnsignedVarInt(flag);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
