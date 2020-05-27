package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerAction extends PCPacket {
	
	public static final int TYPE_START_BREAK = 0;
    public static final int TYPE_ABORT_BREAK = 1;
    public static final int TYPE_STOP_BREAK = 2;
    public static final int TYPE_GET_UPDATED_BLOCK = 3;
    
    public static final int TYPE_DROP_ITEM = 4;
    
    public static final int TYPE_START_SLEEPING = 5;
    public static final int TYPE_STOP_SLEEPING = 6;
    
    public static final int TYPE_RESPAWN = 7;
    
    public static final int TYPE_JUMP = 8;
    
    public static final int TYPE_START_SPRINT = 9;
    public static final int TYPE_STOP_SPRINT = 10;
    
    public static final int TYPE_START_SNEAK = 11;
    public static final int TYPE_STOP_SNEAK = 12;
    
    // sent when dying in different dimension
    public static final int TYPE_DIMENSION_CHANGE_REQUEST = 13;
    // sent when spawning in a different dimension to tell the server we spawned
    public static final int TYPE_DIMENSION_CHANGE_ACK = 14;
   
    public static final int TYPE_START_GLIDE = 15;
    public static final int TYPE_STOP_GLIDE = 16;
    
    public static final int TYPE_BUILD_DENIED = 17;
    public static final int TYPE_CONTINUE_BREAK = 18;
    
    public static final int TYPE_SET_ENCHANTMENT_SEED = 20;
    
    public static final int TYPE_START_SWIMMING = 21;
    public static final int TYPE_STOP_SWIMMING = 22;
    
    public static final int TYPE_START_SPIN_ATTACK = 23;
    public static final int TYPE_STOP_SPIN_ATTACK = 24;
    
    public static final int TYPE_INTERACT_BLOCK = 25;
	
	public long entityId;
	public byte type;
	public int x;
	public int y;
	public int z;
	public int face;
	
	public PacketPlayerAction(){
		super(PacketType.PlayerAction);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityId);
		writer.writeSignedVarInt(this.type);
		writer.writeSignedVarInt(this.x);
		writer.writeUnsignedVarInt(this.y);
		writer.writeSignedVarInt(this.z);
		writer.writeSignedVarInt(this.face);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityId = reader.readUnsignedVarLong();
		this.type = (byte) reader.readSignedVarInt();
		this.x = reader.readSignedVarInt();
		this.y = (int) reader.readUnsignedVarInt();
		this.z = reader.readSignedVarInt();
		this.face = reader.readSignedVarInt();
	}
}
