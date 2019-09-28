package de.marcely.pocketcraft.pocket.network.packet;

import java.util.HashMap;
import java.util.Map;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketText extends PCPacket {
	
	public TextType type;
	public String source = "", message = "";
    public String[] parameters = new String[0];
    public boolean isLocalized = false;
    public String platformChatID = "";
    public String thirdPartyName = "";
    public int platformID;
	
	public PacketText(boolean in){
		super(in ? PacketType.InText : PacketType.OutText);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(type.id);
		writer.writeBoolean(this.isLocalized);
		
		switch(type){
        case POPUP:
        case CHAT:
        case WHISPER:
        case ANNOUNCEMENT:
        	writer.writeString(source);
        	writer.writeString(thirdPartyName);
        	writer.writeUnsignedVarInt(this.platformID);
        case RAW:
        case TIP:
        case SYSTEM:
        	writer.writeString(message);
        	break;
        
        case TRANSLATION:
        	writer.writeString(message);
            writer.writeUnsignedVarInt(parameters.length);
            
            for(String parameter:this.parameters)
            	writer.writeString(parameter);
            
            break;
		}
		
		writer.writeString(this.platformChatID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = TextType.VALUES.get(reader.readSignedByte());
		this.isLocalized = reader.readBoolean() || this.type == TextType.TRANSLATION;
		
		switch(type){
        case POPUP:
        case CHAT:
        case WHISPER:
        case ANNOUNCEMENT:
        	this.source = reader.readString();
        	this.thirdPartyName = reader.readString();
        	this.platformID = reader.readSignedVarInt();
        case RAW:
        case TIP:
        case SYSTEM:
        	this.message = reader.readString();
        	break;
        
        case TRANSLATION:
        	this.message = reader.readString();
            this.parameters = new String[(int) reader.readUnsignedVarInt()];
        	
            for(int i=0; i<this.parameters.length; i++)
            	this.parameters[i] = reader.readString();
            
            break;
		}
		
		this.platformChatID = reader.readString();
	}
	
	
	
	public static enum TextType {
		RAW((byte) 0x0),
		CHAT((byte) 0x1),
		TRANSLATION((byte) 0x2),
		POPUP((byte) 0x3),
		TIP((byte) 0x4),
		SYSTEM((byte) 0x5),
		WHISPER((byte) 0x6),
		ANNOUNCEMENT((byte) 0x7);
		
		public static Map<Byte, TextType> VALUES = new HashMap<>();
		
		public byte id;
		
		static {
			for(TextType type:values())
				VALUES.put(type.id, type);
		}
		
		private TextType(byte id){
			this.id = id;
		}
	}
}
