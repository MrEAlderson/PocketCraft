package de.marcely.pocketcraft.java.component.chat;

import lombok.Getter;

public class ChatScoreComponent extends ChatBaseComponent {
	
	@Getter private final String name;
	@Getter private final String objective;
	@Getter private final String value;
	
	public ChatScoreComponent(String name, String objective){
		this(name, objective, null);
	}
	
	public ChatScoreComponent(String name, String objective, String value){
		this.name = name;
		this.objective = objective;
		this.value = value != null ? value : "";
	}
}
