package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketExplosion extends PCPacket {

	public float posX, posY, posZ;
	public float radius;
	public int[] affectedBlocksX;
	public int[] affectedBlocksY;
	public int[] affectedBlocksZ;
	
	public PacketExplosion(){
		super(PacketType.Explosion);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeLFloat(this.posX);
		writer.writeLFloat(this.posY);
		writer.writeLFloat(this.posZ);
		writer.writeSignedVarInt((int) (this.radius*32));
		writer.writeUnsignedVarInt(this.affectedBlocksX.length);
		
		for(int i=0; i<this.affectedBlocksX.length; i++){
			writer.writeSignedVarInt(this.affectedBlocksX[i]);
			writer.writeUnsignedVarInt(this.affectedBlocksY[i]);
			writer.writeSignedVarInt(this.affectedBlocksZ[i]);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
