package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketText extends PCPacket {
	
	public static final byte TYPE_RAW = 0;
    public static final byte TYPE_CHAT = 1;
    public static final byte TYPE_TRANSLATION = 2;
    public static final byte TYPE_POPUP = 3;
    public static final byte TYPE_JUKEBOX_POPUP = 4;
    public static final byte TYPE_TIP = 5;
    public static final byte TYPE_SYSTEM = 6;
    public static final byte TYPE_WHISPER = 7;
    public static final byte TYPE_ANNOUNCEMENT = 8;
    public static final byte TYPE_JSON = 9;
	
	public byte type;
	public String source = "", message = "";
    public String[] parameters = new String[0];
    public boolean isLocalized = false;
    public String xboxUserId = "";
    public String platformChatID = "";
	
	public PacketText(){
		super(PacketType.Text);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(this.type);
		writer.writeBoolean(this.isLocalized);
		
		switch(type){
        case TYPE_CHAT:
        case TYPE_WHISPER:
        case TYPE_ANNOUNCEMENT:
        	writer.writeString(this.source);
       
        case TYPE_RAW:
        case TYPE_TIP:
        case TYPE_SYSTEM:
        case TYPE_JSON:
        	writer.writeString(this.message);
        	break;
        
        case TYPE_TRANSLATION:
        case TYPE_POPUP:
        case TYPE_JUKEBOX_POPUP:
        	writer.writeString(this.message);
            writer.writeUnsignedVarInt(this.parameters.length);
            
            for(String parameter:this.parameters)
            	writer.writeString(parameter);
            
            break;
		}
		
		writer.writeString(this.xboxUserId);
		writer.writeString(this.platformChatID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = reader.readSignedByte();
		this.isLocalized = reader.readBoolean() || this.type == TYPE_TRANSLATION;
		
		switch(type){
        case TYPE_CHAT:
        case TYPE_WHISPER:
        case TYPE_ANNOUNCEMENT:
        	this.source = reader.readString();
       
        case TYPE_RAW:
        case TYPE_TIP:
        case TYPE_SYSTEM:
        case TYPE_JSON:
        	this.message = reader.readString();
        	break;
        
        case TYPE_TRANSLATION:
        case TYPE_POPUP:
        case TYPE_JUKEBOX_POPUP:
        	this.message = reader.readString();
            this.parameters = new String[(int) reader.readUnsignedVarInt()];
        	
            for(int i=0; i<this.parameters.length; i++)
            	this.parameters[i] = reader.readString();
            
            break;
		}
		
		this.xboxUserId = reader.readString();
		this.platformChatID = reader.readString();
	}
}
