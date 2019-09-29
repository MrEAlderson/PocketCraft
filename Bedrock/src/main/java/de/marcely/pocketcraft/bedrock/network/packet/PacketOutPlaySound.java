package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutPlaySound extends PCPacket {
	
	public String soundName;
	public int x, y, z;
	public float volume, pitch;
	
	public PacketOutPlaySound(){
		super(PacketType.OutPlaySound);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(soundName);
		writer.writeSignedVarInt(x*8);
		writer.writeUnsignedVarInt(y*8);
		writer.writeSignedVarInt(z*8);
		writer.writeLFloat(volume);
		writer.writeLFloat(pitch);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
