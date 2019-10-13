package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.List;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketScoreboardSetScore extends PCPacket {
	
	public byte action;
	public List<Entry> entries;
	
	public PacketScoreboardSetScore(){
		super(PacketType.ScoreboardSetScore);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(this.action);
		writer.writeUnsignedVarInt(this.entries.size());
		
		for(Entry entry:this.entries){
			writer.writeSignedVarLong(entry.scoreId);
			writer.writeString(entry.objective);
			writer.writeLInt(entry.score);
			
			if(entry.type == Entry.TYPE_CHANGE){
				writer.writeSignedByte(entry.entityType);
				
				switch(entry.entityType){
				case Entry.ENTITY_TYPE_FAKE_PLAYER:
					writer.writeString(entry.fakeEntity);
					break;
					
				case Entry.ENTITY_TYPE_PLAYER:
				case Entry.ENTITY_TYPE_ENTITY:
					writer.writeUnsignedVarLong(entry.entityId);
					break;
				}
			}
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	
	public static class Entry {
		
		public static final byte TYPE_CHANGE = 0;
		public static final byte TYPE_REMOVE = 1;
		
		public static final byte ENTITY_TYPE_PLAYER = 1;
		public static final byte ENTITY_TYPE_ENTITY = 2;
		public static final byte ENTITY_TYPE_FAKE_PLAYER = 3;
		
		public byte type;
		public long scoreId;
		public String objective;
		public int score;
		
		// add entity
		public byte entityType;
		public String fakeEntity;
		public long entityId;
	}
}
