package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.pocket.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutBlockEntityData extends PCPacket {

	public int x, y, z;
	public NBTCompound nbt;
	
	public PacketOutBlockEntityData(){
		super(PacketType.OutBlockEntityData);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(x);
		writer.writeUnsignedVarInt(y);
		writer.writeSignedVarInt(z);
		nbt.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
