package de.marcely.pocketcraft.java.component.chat;

import lombok.Getter;

public class ChatTranslationComponent extends ChatBaseComponent {
	
	@Getter private final String key;
	@Getter private final ChatBaseComponent[] args;
	
	public ChatTranslationComponent(String key, ChatBaseComponent args[]){
		this.key = key;
		this.args = args;
	}
}
