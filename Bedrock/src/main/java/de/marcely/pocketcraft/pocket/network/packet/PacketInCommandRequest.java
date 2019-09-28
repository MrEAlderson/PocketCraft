package de.marcely.pocketcraft.pocket.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInCommandRequest extends PCPacket {

    public static final byte TYPE_PLAYER = 0;
    public static final byte TYPE_COMMAND_BLOCK = 1;
    public static final byte TYPE_MINECART_COMMAND_BLOCK = 2;
    public static final byte TYPE_DEV_CONSOLE = 3;
    public static final byte TYPE_TEST = 4;
    public static final byte TYPE_AUTOMATION_PLAYER = 5;
    public static final byte TYPE_CLIENT_AUTOMATION = 6;
    public static final byte TYPE_DEDICATED_SERVER = 7;
    public static final byte TYPE_ENTITY = 8;
    public static final byte TYPE_VIRTUAL = 9;
    public static final byte TYPE_GAME_ARGUMENT = 10;
    public static final byte TYPE_INTERNAL = 11;
	
    public String command;
    public int type;
    public UUID uuid;
    public String requestID;
    public long optional;
    
    
	public PacketInCommandRequest(){
		super(PacketType.InCommandRequest);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.command = reader.readString();
		this.type = reader.readSignedVarInt();
		this.uuid = reader.readUUID();
		this.requestID = reader.readString();
		
		if(this.type == TYPE_DEV_CONSOLE || this.type == TYPE_TEST)
			this.optional = reader.readSignedVarLong();
	}
}
