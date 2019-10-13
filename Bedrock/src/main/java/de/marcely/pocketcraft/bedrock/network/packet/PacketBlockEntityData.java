package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockEntityData extends PCPacket {

	public int x, y, z;
	public NBTCompound data;
	
	public PacketBlockEntityData(){
		super(PacketType.BlockEntityData);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.x);
		writer.writeUnsignedVarInt(this.y);
		writer.writeSignedVarInt(this.z);
		this.data.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
