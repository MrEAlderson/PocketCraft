package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerMove extends PCPacket {
	
    public long entityRuntimeID, ridingEntityID = 0;
    public float posX, posY, posZ, yaw, headYaw, pitch;
    public PlayerMoveType mode = PlayerMoveType.NORMAL;
    public boolean onGround;
    public int teleportCause = 0, teleportItem = 0;
	
	public PacketPlayerMove(boolean in){
		super(in ? PacketType.InPlayerMove : PacketType.OutPlayerMove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeVector(posX, posY, posZ);
		writer.writeLFloat(pitch);
		writer.writeLFloat(yaw);
		writer.writeLFloat(headYaw);
		writer.writeSignedByte(mode.id);
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
		this.mode = PlayerMoveType.VALUES.get(reader.readSignedByte());
		this.onGround = reader.readBoolean();
		this.ridingEntityID = reader.readUnsignedVarLong();
		
		if(mode == PlayerMoveType.TELEPORT){
			this.teleportCause = reader.readLInt();
			this.teleportItem = reader.readLInt();
		}
	}
	
	
	
	public static enum PlayerMoveType {
		
		NORMAL((byte) 0x0),
		RESET((byte) 0x1),
		TELEPORT((byte) 0x2),
		PITCH((byte) 0x3);
		
		public static final Map<Byte, PlayerMoveType> VALUES = new HashMap<>();
		
		public final byte id;
		
		static {
			for(PlayerMoveType type:values())
				VALUES.put(type.id, type);
		}
		
		private PlayerMoveType(byte id){
			this.id = id;
		}
	}
}
