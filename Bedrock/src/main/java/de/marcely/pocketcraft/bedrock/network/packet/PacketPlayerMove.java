package de.marcely.pocketcraft.bedrock.network.packet;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerMove extends PCPacket {
	
    public long entityRuntimeID, ridingEntityID = 0;
    public float posX, posY, posZ, yaw, headYaw, pitch;
    public PlayerMoveType mode = PlayerMoveType.NORMAL;
    public boolean onGround;
    public int teleportCause = 0, teleportItem = 0;
	
	public PacketPlayerMove(){
		super(PacketType.PlayerMove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeVector(posX, posY, posZ);
		writer.writeLFloat(pitch);
		writer.writeLFloat(yaw);
		writer.writeLFloat(headYaw);
		writer.writeSignedByte(mode.getId());
		writer.writeBoolean(onGround);
		writer.writeUnsignedVarLong(ridingEntityID);
		
		if(mode == PlayerMoveType.TELEPORT){
			writer.writeLInt(teleportCause);
			writer.writeLInt(teleportItem);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityRuntimeID = reader.readUnsignedVarLong();
		this.posX = reader.readLFloat();
		this.posY = reader.readLFloat();
		this.posZ = reader.readLFloat();
		this.pitch = reader.readLFloat();
		this.yaw = reader.readLFloat();
		this.headYaw = reader.readLFloat();
		this.mode = PlayerMoveType.ofId(reader.readSignedByte());
		this.onGround = reader.readBoolean();
		this.ridingEntityID = reader.readUnsignedVarLong();
		
		if(mode == PlayerMoveType.TELEPORT){
			this.teleportCause = reader.readLInt();
			this.teleportItem = reader.readLInt();
		}
	}
	
	
	
	public static enum PlayerMoveType {
		
		// do not change order
		NORMAL,
		RESET,
		TELEPORT,
		PITCH;
		
		public byte getId(){
			return (byte) this.ordinal();
		}
		
		public static @Nullable PlayerMoveType ofId(byte id){
			if(id < 0 || id >= values().length)
				return null;
			else
				return PlayerMoveType.values()[id];
		}
	}
}
