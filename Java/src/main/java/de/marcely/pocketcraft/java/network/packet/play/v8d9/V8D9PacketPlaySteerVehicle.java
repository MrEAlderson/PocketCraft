package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySteerVehicle extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public float sideways;
	public float forward;
	public boolean jump;
	public boolean unmount;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeFloat(this.sideways);
		stream.writeFloat(this.forward);
		
		{
			byte map = 0;
			
			if(this.jump)
				map |= 0x1;
			
			if(this.unmount)
				map |= 0x2;
			
			stream.writeByte(map);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.sideways = stream.readFloat();
		this.forward = stream.readFloat();
		
		{
			final byte map = stream.readByte();
			
			this.jump = (map & 0x1) > 0;
			this.unmount = (map & 0x2) > 0;
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
