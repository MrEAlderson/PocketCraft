package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.*;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.java.*;

public class V8D9Translator extends TranslatorVersion {

	public V8D9Translator(Protocol protocol){
		super(protocol);
	}

	@Override
	public void definePacketTranslators(){
		define(V8D9PacketPlayMapChunk.class, TV8D9PacketPlayMapChunk.class);
		define(V8D9PacketPlayMapChunkBulk.class, TV8D9PacketPlayMapChunkBulk.class);
	}
}