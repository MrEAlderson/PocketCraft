package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.WindowType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketContainerOpen extends PCPacket {

	public byte windowID;
	public WindowType type;
	public int x, y, z;
	public int entityID = -1;
	
	public PacketContainerOpen(){
		super(PacketType.ContainerOpen);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(this.windowID);
		writer.writeSignedByte(this.type.id);
		writer.writeSignedVarInt(this.x);
		writer.writeUnsignedVarInt(this.y);
		writer.writeSignedVarInt(this.z);
		writer.writeSignedVarLong(this.entityID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
