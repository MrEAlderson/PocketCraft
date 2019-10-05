package de.marcely.pocketcraft.java.component.chat;

import lombok.Getter;

public class ChatTextComponent extends ChatBaseComponent {
	
	@Getter private final String text;
	
	public ChatTextComponent(String text){
		this.text = text;
	}
}