package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketCommandRequest extends PCPacket {

    public static final byte ORIGIN_PLAYER = 0;
    public static final byte ORIGIN_COMMAND_BLOCK = 1;
    public static final byte ORIGIN_MINECART_COMMAND_BLOCK = 2;
    public static final byte ORIGIN_DEV_CONSOLE = 3;
    public static final byte ORIGIN_TEST = 4;
    public static final byte ORIGIN_AUTOMATION_PLAYER = 5;
    public static final byte ORIGIN_CLIENT_AUTOMATION = 6;
    public static final byte ORIGIN_DEDICATED_SERVER = 7;
    public static final byte ORIGIN_ENTITY = 8;
    public static final byte ORIGIN_VIRTUAL = 9;
    public static final byte ORIGIN_GAME_ARGUMENT = 10;
    public static final byte ORIGIN_INTERNAL = 11;
	
    public String command;
    public int origin;
    public UUID uuid;
    public String requestId;
    public long optional;
    
    
	public PacketCommandRequest(){
		super(PacketType.CommandRequest);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.command = reader.readString();
		this.origin = reader.readSignedVarInt();
		this.uuid = reader.readUUID();
		this.requestId = reader.readString();
		
		if(this.origin == ORIGIN_DEV_CONSOLE || this.origin == ORIGIN_TEST)
			this.optional = reader.readSignedVarLong();
	}
}
