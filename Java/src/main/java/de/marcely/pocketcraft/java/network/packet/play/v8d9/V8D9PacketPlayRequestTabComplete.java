package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayRequestTabComplete extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String text; // all text behind cursor
	@Nullable public Vector3 lookingBlock;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.text);
		
		if(this.lookingBlock != null){
			stream.writeBoolean(true);
			stream.writeBlockPosition(this.lookingBlock);
		
		}else
			stream.writeBoolean(false);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.text = stream.readString();
		
		if(stream.readBoolean())
			this.lookingBlock = stream.readBlockPosition();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
