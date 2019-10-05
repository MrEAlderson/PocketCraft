package de.marcely.pocketcraft.java.component.chat;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.Setter;

public class ChatModifier {
	
	@Getter @Setter private ChatColor color = null;
	@Getter @Setter private Boolean bold = null;
	@Getter @Setter private Boolean italic = null;
	@Getter @Setter private Boolean strikethrough = null;
	@Getter @Setter private Boolean underlined = null;
	@Getter @Setter private Boolean obfuscated = null;
	
	public boolean isUnused(){
		return this.color == null && this.bold == null && this.italic == null && this.strikethrough == null && this.underlined == null && this.obfuscated == null;
	}
	
	public void write(JsonObject obj){
		if(this.bold != null)
			obj.addProperty("bold", this.bold);
		
		if(this.italic != null)
			obj.addProperty("italic", this.italic);
		
		if(this.underlined != null)
			obj.addProperty("underlined", this.underlined);
		
		if(this.strikethrough != null)
			obj.addProperty("strikethrough", this.strikethrough);
		
		if(this.obfuscated != null)
			obj.addProperty("obfuscated", this.obfuscated);
		
		if(this.color != null)
			obj.addProperty("color", this.color.getName());
	}
	
	public void parse(JsonObject obj){
		for(Entry<String, JsonElement> e:obj.entrySet()){
			final JsonElement val = e.getValue();
			
			switch(e.getKey()){
				case "bold":
					this.bold = val.getAsBoolean();
					break;
					
				case "italic":
					this.italic = val.getAsBoolean();
					break;
					
				case "underlined":
					this.underlined = val.getAsBoolean();
					break;
					
				case "obfuscated":
					this.obfuscated = val.getAsBoolean();
					break;
					
				case "color":
					this.color = ChatColor.getByName(val.getAsString());
					break;
					
				// TODO
				case "clickEvent":
				case "hoverEvent":
					break;
			}
		}
	}
}