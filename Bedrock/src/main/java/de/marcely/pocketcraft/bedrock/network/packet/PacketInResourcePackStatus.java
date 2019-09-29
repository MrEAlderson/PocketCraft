package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInResourcePackStatus extends PCPacket {
	
    public static final byte REFUSED = 0x1;
    public static final byte SEND_PACKS = 0x2;
    public static final byte HAVE_ALL_PACKS = 0x3;
    public static final byte COMPLETED = 0x4;
	
    public byte status;
    public String needPacks[];
    
	public PacketInResourcePackStatus(){
		super(PacketType.InResourcePackStatus);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.status = (byte) reader.readSignedByte();
		this.needPacks = new String[reader.readLShort()];
		
		for(int i=0; i<needPacks.length; i++)
			needPacks[i] = reader.readString();
	}
}
