package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutAvailableCommands extends PCPacket {
	
	// TODO
	
	public static final int ARG_FLAG_VALID = 0x100000;
	
	public static final int ARG_TYPE_INT = 0x01;
	public static final int ARG_TYPE_FLOAT = 0x02;
	public static final int ARG_TYPE_VALAUE = 0x31;
	public static final int ARG_TYPE_WILDCARD_INT = 0x04;
	public static final int ARG_TYPE_OPERATOR = 0x05;
	public static final int ARG_TYPE_TARGET = 0x06;
	public static final int ARG_TYPE_FILEPATH = 0x0E;
	public static final int ARG_TYPE_STRING = 0x1B;
	public static final int ARG_TYPE_POSITION = 0x1D;
	public static final int ARG_TYPE_MESSAGE = 0x20;
	public static final int ARG_TYPE_RAWTEXT = 0x22;
	public static final int ARG_TYPE_JSON = 0x25;
	public static final int ARG_TYPE_COMMAND = 0x2C;
	
	public static final int ARG_FLAG_ENUM = 0x200000;
	public static final int ARG_FLAG_POSTFIX = 0x1000000;
	
	public String[] enumValues;
	public String[] postfixes;
	
	public PacketOutAvailableCommands(){
		super(PacketType.OutAvailableCommands);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(0); // enumValues
		writer.writeUnsignedVarInt(0); // postfixe
		writer.writeUnsignedVarInt(0); // enums
		writer.writeUnsignedVarInt(0); // commandData
		writer.writeUnsignedVarInt(0); // softEnums
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
