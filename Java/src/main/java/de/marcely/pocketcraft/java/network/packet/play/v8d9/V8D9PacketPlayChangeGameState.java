package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayChangeGameState extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte KEY_INVALID_BED = 0;
	public static final byte KEY_END_RAINING = 1;
	public static final byte KEY_BEGIN_RAINING = 2;
	/*-1 = special
	 * 0 = survival
	 * 1 = creative
	 * 2 = adventure
	 * 3 = spectator */
	public static final byte KEY_CHANGE_GAMEMODE = 3;
	/* 0 = don't show end credits
	 * 1 = show end credits */
	public static final byte KEY_EXIT_END = 4;
	/* 0   = welcome screen
	 * 101 = tell movement controls
	 * 102 = tell jump control
	 * 103 = tell inventory control */
	public static final byte KEY_DEMO_MESSAGE = 5;
	public static final byte KEY_ARROW_HITTING_PLAYER = 6;
	/* sky darkness value (1 = dark, 0 = bright) */
	public static final byte KEY_FADE_VALUE = 7;
	/* time in ticks for fade */
	public static final byte KEY_FADE_TIME = 8;
	public static final byte KEY_PUFFERFISH_STING = 9;
	public static final byte KEY_ELDER_GUARDIAN_APPEARANCE = 10;
	
	public byte key;
	public float value;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.key);
		stream.writeFloat(this.value);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.key = stream.readByte();
		this.value = stream.readFloat();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
