package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutLoginStatus extends PCPacket {

    public static final int SUCCESS = 0;
    public static final int FAILED_CLIENT = 1;
    public static final int FAILED_SERVER = 2;
    public static final int PLAYER_SPAWN = 3;
    public static final int FAILED_INVALID_TENANT = 4;
    public static final int FAILED_VANILLA_EDU = 5;
    public static final int FAILED_EDU_VANILLA = 6;
    public static final int FAILED_SERVER_FULL = 7;

    public int result;
	
	public PacketOutLoginStatus(){
		super(PacketType.OutLoginStatus);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedInt(result);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
