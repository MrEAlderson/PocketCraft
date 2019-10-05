package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.protocol.Protocol;

public abstract class TranslatorVersion {
	
	private final Protocol protocol;
	
	public TranslatorVersion(Protocol protocol){
		this.protocol = protocol;
	}
	
	public abstract void definePacketTranslators();
	
	protected void define(Class<?> packet, Class<?> translatorClazz){
		try{
			final PacketProperties properties = (PacketProperties) packet.getField("PROPERTIES").get(null);
			
			properties.setMetadata(getMetaName(), translatorClazz.newInstance());
		}catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e){
			e.printStackTrace();
			throw new IllegalStateException("Missing or invalid PROPERTIES field for " + packet.getName());
		}catch(InstantiationException e){
			e.printStackTrace();
			throw new IllegalStateException("Failed to create instance of " + translatorClazz.getClass());
		}
	}
	
	private String getMetaName(){
		return protocol.getProtocolVersion() + "_translator";
	}
}