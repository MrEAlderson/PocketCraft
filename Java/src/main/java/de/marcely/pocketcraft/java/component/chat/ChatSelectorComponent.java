package de.marcely.pocketcraft.java.component.chat;

import lombok.Getter;

public class ChatSelectorComponent extends ChatBaseComponent {
	
	@Getter private final String pattern;
	
	public ChatSelectorComponent(String pattern){
		this.pattern = pattern;
	}
}
