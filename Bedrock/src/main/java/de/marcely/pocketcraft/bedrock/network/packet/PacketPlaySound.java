package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlaySound extends PCPacket {
	
	public String soundName;
	public float x, y, z;
	public float volume, pitch;
	
	public PacketPlaySound(){
		super(PacketType.PlaySound);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(this.soundName);
		writer.writeSignedVarInt((int) (this.x * 8F));
		writer.writeUnsignedVarInt((int) (y * 8F));
		writer.writeSignedVarInt((int) (z * 8F));
		writer.writeLFloat(this.volume);
		writer.writeLFloat(this.pitch);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
