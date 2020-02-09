package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockChange extends PCPacket {
	
    public static final byte FLAG_NONE = 0b0000;
    public static final byte FLAG_NEIGHBORS = 0b0001;
    public static final byte FLAG_NETWORK = 0b0010;
    public static final byte FLAG_NOGRAPHIC = 0b0100;
    public static final byte FLAG_PRIORITY = 0b1000;
	
    public static final byte FLAG_ALL = (FLAG_NEIGHBORS | FLAG_NETWORK);
    public static final byte FLAG_ALL_PRIORITY = (FLAG_ALL | FLAG_PRIORITY);
    
    public static final byte DATA_LAYER_NORMAL = 0;
    public static final byte DATA_LAYER_LIQUID = 1;
    
	public int x;
	public int y;
	public int z;
	public int blockRuntimeId;
	public int flag;
	public int dataLayer = DATA_LAYER_NORMAL;
	
	public PacketBlockChange(){
		super(PacketType.BlockChange);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.x);
		writer.writeUnsignedVarInt(this.y);
		writer.writeSignedVarInt(this.z);
		writer.writeUnsignedVarInt(this.blockRuntimeId);
		writer.writeUnsignedVarInt(this.flag);
		writer.writeUnsignedVarInt(this.dataLayer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
