package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTTag;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockEntityData extends PCPacket {

	public int x, y, z;
	public BNBTTag data;
	
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
	public void decode(EByteArrayReader reader) throws Exception {
		this.x = reader.readSignedVarInt();
		this.y = (int) reader.readUnsignedVarInt();
		this.z = reader.readSignedVarInt();
		this.data = BNBTTag.read(reader, ByteOrder.LITTLE_ENDIAN, true);
	}
}
