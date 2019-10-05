package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.List;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.utils.math.Vector3;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketExplosion extends PCPacket {

	public float posX, posY, posZ;
	public float radius;
	public List<Vector3> blocks;
	
	public PacketExplosion(){
		super(PacketType.Explosion);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeLFloat(this.posX);
		writer.writeLFloat(this.posY);
		writer.writeLFloat(this.posZ);
		writer.writeSignedVarInt((int) (this.radius*32));
		writer.writeUnsignedVarInt(this.blocks.size());
		
		for(Vector3 block:this.blocks){
			writer.writeSignedVarInt(block.getFloorX());
			writer.writeUnsignedVarInt(block.getFloorY());
			writer.writeSignedVarInt(block.getFloorZ());
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
