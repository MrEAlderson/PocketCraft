package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayAbilities extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public boolean isInvulnerable;
	public boolean isFlying;
	public boolean canFly;
	public boolean canInstantlyBuild;
	public float flySpeed;
	public float walkSpeed;

	@Override
	public void write(EByteBuf stream) throws Exception {
		// flags
		{
			byte flags = 0;
			
			if(this.isInvulnerable)
				flags |= 0x1;
			
			if(this.isFlying)
				flags |= 0x2;
			
			if(this.canFly)
				flags |= 0x4;
			
			if(this.canInstantlyBuild)
				flags |= 0x8;
			
			stream.writeByte(flags);
		}
		
		stream.writeFloat(this.flySpeed);
		stream.writeFloat(this.walkSpeed);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		// flags
		{
			final byte flags = stream.readByte();
			
			this.isInvulnerable = (flags & 0x1) > 0;
			this.isFlying = (flags & 0x2) > 0;
			this.canFly = (flags & 0x4) > 0;
			this.canInstantlyBuild = (flags & 0x8) > 0;
		}
		
		this.flySpeed = stream.readFloat();
		this.walkSpeed = stream.readFloat();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
