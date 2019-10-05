package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWorldTime extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public long age;
	public long time;
	public boolean isLocked;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeLong(this.age);
		stream.writeLong(this.isLocked ? -this.time : this.time);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.age = stream.readLong();
		this.time = stream.readLong();
		
		if(this.time < 0){
			this.time = Math.abs(this.time);
			this.isLocked = true;
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
