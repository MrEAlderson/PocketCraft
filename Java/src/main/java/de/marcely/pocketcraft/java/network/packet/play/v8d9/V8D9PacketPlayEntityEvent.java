package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityEvent extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte TYPE_RESET_MINECART_TIMER = 1;
	public static final byte TYPE_HURT = 2;
	public static final byte TYPE_DEATH = 3;
	public static final byte TYPE_IRON_GOLEM_THROW_ARMS = 4;
	public static final byte TYPE_TAME_SUCCESS = 5;
	public static final byte TYPE_TAME_FAILED = 6;
	public static final byte TYPE_WOLF_SHAKE_DRY = 7;
	public static final byte TYPE_EAT_ACCEPT = 8;
	public static final byte TYPE_SHEEP_EAT_GRASS = 9;
	public static final byte TYPE_TNT_IGNITE = 10;
	public static final byte TYPE_IRON_GOLEM_ROSE = 10;
	public static final byte TYPE_VILLAGER_MATING = 13;
	public static final byte TYPE_VILLAGER_HAPPY = 14;
	public static final byte TYPE_WITCH_MAGIC = 15;
	public static final byte TYPE_VILLAGER_ZOMBIE_CONVERTING = 16;
	public static final byte TYPE_FIREWORK_EXPLODE = 17;
	public static final byte TYPE_TAME_START = 18;
	public static final byte TYPE_SQUID_RESET_ROTATION = 19;
	public static final byte TYPE_EXPLOSION_PARTICLE = 20;
	public static final byte TYPE_GUARDIAN_SOUND = 21;
	public static final byte TYPE_REDUCED_DEBUG_ENABLE = 22;
	public static final byte TYPE_REDUCED_DEBUG_DISABLE = 23;
	
	public int entityId;
	public byte type;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.entityId);
		stream.writeByte(this.type);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readInt();
		this.type = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
