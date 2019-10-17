package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketEntitySetLink extends PCPacket {
	
	public EntityLink link;
	
	public PacketEntitySetLink(){
		super(PacketType.EntitySetLink);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		this.link.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
