package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketInteract extends PCPacket {
	
	public static final int ACTION_VEHICLE_EXIT = 3;
    public static final int ACTION_MOUSEOVER = 4;

    public static final int ACTION_OPEN_INVENTORY = 6;
	
	public byte action;
	public long targetRuntimeId;
	
	public PacketInteract(){
		super(PacketType.INTERACT);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.action = reader.readSignedByte();
		this.targetRuntimeId = reader.readUnsignedVarLong();
		
		System.out.println("INTERACT: " + this.action);
	}
}
