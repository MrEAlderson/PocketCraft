package de.marcely.pocketcraft.java.component.chat;

import lombok.Getter;

public class ChatKeybindComponent extends ChatBaseComponent {
	
	@Getter private final String key;
	
	public ChatKeybindComponent(String key){
		this.key = key;
	}
}
