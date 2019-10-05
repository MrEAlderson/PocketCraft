package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerAction extends PCPacket {
	
	public long entityID;
	public PlayerActionType type;
	public int x, y, z, face;
	
	public PacketPlayerAction(){
		super(PacketType.PlayerAction);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityID);
		writer.writeSignedVarInt(type.id);
		writer.writeSignedVarInt(x);
		writer.writeUnsignedVarInt(y);
		writer.writeSignedVarInt(z);
		writer.writeSignedVarInt(face);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityID = reader.readUnsignedVarLong();
		this.type = PlayerActionType.VALUES.get(reader.readSignedVarInt());
		this.x = reader.readSignedVarInt();
		this.y = (int) reader.readUnsignedVarInt();
		this.z = reader.readSignedVarInt();
		this.face = reader.readSignedVarInt();
	}
	
	
	
	public static enum PlayerActionType {
	    
		// blocks
		BREAK_START(0),
		BREAK_ABORT(1),
		BREAK_STOP(2),
		BREAKING(18),
		
		GET_UPDATED_BLOCK(3),
		BUILD_DENIED(17),
		
		// movement
		JUMP(8),
		SPRINT_START(9),
		SPRINT_STOP(10),
		SNEAK_START(11),
		SNEAK_STOP(12),
		
		// sleeping
		SLEEPING_START(5),
		SLEEPING_STOP(6),
		
		// eltrya
		ELYTRA_GLIDING_START(15),
		ELYTRA_GLIDING_STOP(16),
		
		// dimension stuff
		DIMENSION_CHANGE_REQUEST(13),
		DIMENSION_CHANGE_ACK(14),
		
		// swimming
		SWIMMING_START(21),
		SWIMMING_STOP(22),
		
		// spin attack
		SPIN_ATTACK_START(23),
		SPIN_ATTACK_STOP(24),
		
		// others
		DROP_ITEM(4),
		RESPAWN(7),
		ENCHANTMENT_SET_SEET(20);
	    
		public static final Map<Integer, PlayerActionType> VALUES = new HashMap<>();
		
	    public final int id;
	    
		static {
			for(PlayerActionType type:values())
				VALUES.put(type.id, type);
		}
	    
	    private PlayerActionType(int id){
	    	this.id = id;
	    }
	}
}
