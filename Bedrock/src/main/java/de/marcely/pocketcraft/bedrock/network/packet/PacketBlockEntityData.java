package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTTag;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockEntityData extends PCPacket {

	public int x, y, z;
	public NBTTag data;
	
	public PacketBlockEntityData(){
		super(PacketType.BlockEntityData);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.x);
		writer.writeUnsignedVarInt(this.y);
		writer.writeSignedVarInt(this.z);
		this.data.write(writer, ByteOrder.LITTLE_ENDIAN, true);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
