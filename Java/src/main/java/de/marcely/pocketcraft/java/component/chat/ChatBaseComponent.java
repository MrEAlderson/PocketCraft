package de.marcely.pocketcraft.java.component.chat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import lombok.Getter;

public abstract class ChatBaseComponent implements Iterable<ChatBaseComponent> {
	
	@Getter private final ChatModifier modifier = new ChatModifier();
	@Getter private final List<ChatBaseComponent> siblings = new ArrayList<>();
	
	public ChatBaseComponent(){ }
	
	public void addSibling(ChatBaseComponent sibling){
		this.siblings.add(sibling);
	}
	
	public String asPlainText(){
		final StringBuilder builder = new StringBuilder();
		
		for(ChatBaseComponent component:this){
			if(component instanceof ChatTextComponent)
				builder.append(((ChatTextComponent) component).getText());
		}
		
		return builder.toString();
	}
	
	public String writeAsString(){
		return new Gson().toJson(write());
	}
	
	public JsonElement write(){
		final JsonObject obj = new JsonObject();
		
		// modifier
		if(!this.modifier.isUnused())
			this.modifier.write(obj);
		
		// == extra
		if(!this.siblings.isEmpty()){
			final JsonArray array = new JsonArray();
			
			for(ChatBaseComponent sibling:this.siblings)
				array.add(sibling.write());
			
			obj.add("extra", array);
		}
		
		// text
		if(this instanceof ChatTextComponent){
			obj.addProperty("text", ((ChatTextComponent) this).getText());
		
		// translate
		}else if(this instanceof ChatTranslationComponent){
			final ChatTranslationComponent comp = (ChatTranslationComponent) this;
			
			obj.addProperty("translate", comp.getKey());
			
			if(comp.getArgs().length >= 1){
				final JsonArray extra = new JsonArray(comp.getArgs().length);
				
				for(ChatBaseComponent e:comp.getArgs())
					extra.add(e.write());
				
				obj.add("with", extra);
			}
		
		// score
		}else if(this instanceof ChatScoreComponent){
			final ChatScoreComponent comp = (ChatScoreComponent) this;
			final JsonObject score = new JsonObject();
			
			score.addProperty("name", comp.getName());
			score.addProperty("objective", comp.getObjective());
			
			if(!comp.getValue().isEmpty())
				score.addProperty("value", comp.getValue());
			
			obj.add("score", score);
		
		// selector
		}else if(this instanceof ChatSelectorComponent){
			obj.addProperty("selector", ((ChatSelectorComponent) this).getPattern());
		
		// keybind
		}else if(this instanceof ChatKeybindComponent){
			obj.addProperty("keybind", ((ChatKeybindComponent) this).getKey());
		
		}else
			throw new IllegalArgumentException("Don't know how to serialize " + this.getClass().getName() + " as a Component");
		
		return obj;
	}
	
	// TODO look for a better solution (remove try/catch)
	public static ChatBaseComponent parse(String data){
		try{
			return parse(new GsonBuilder().setLenient().create().fromJson(data, JsonElement.class));
		}catch(JsonIOException e){
			return new ChatTextComponent(data);
		}
	}
	
	public static ChatBaseComponent parse(JsonElement el){
		if(el.isJsonPrimitive())
			return new ChatTextComponent(el.getAsString());
		else if(el.isJsonObject())
			return parse(el.getAsJsonObject());
		else if(el.isJsonArray())
			return parse(el.getAsJsonArray());
		else
			throw new JsonParseException("Don't know how to turn " + el + " into a Component");
	}
	
	private static ChatBaseComponent parse(JsonObject obj){
		ChatBaseComponent component = null;
		
		// == text
		if(obj.has("text")){
			component = new ChatTextComponent(obj.get("text").getAsString());
			
		// == translate
		}else if(obj.has("translate")){
			final String key = obj.get("translate").getAsString();
			
			if(obj.has("with")){
				final JsonArray with = obj.getAsJsonArray("width");
				final ChatBaseComponent[] args = new ChatBaseComponent[with.size()];
				
				for(int i=0; i<with.size(); i++)
					args[i] = parse(with.get(i));
				
				component = new ChatTranslationComponent(key, args);
			}else
				component = new ChatTranslationComponent(key, new ChatBaseComponent[0]);
		
		// == score
		}else if(obj.has("score")){
			final JsonObject score = obj.getAsJsonObject("score");
			
			if(!score.has("name") || !score.has("objective"))
				throw new JsonParseException("A score component needs a least a name and an objective");
			
			component = new ChatScoreComponent(
					score.get("name").getAsString(),
					score.get("objective").getAsString(),
					score.has("value") ? score.get("value").getAsString() : null);
		
		// == selector
		}else if(obj.has("selector")){
			component = new ChatSelectorComponent(obj.get("selector").getAsString());
			
		// == keybind
		}else if(obj.has("keybind")){
			component = new ChatKeybindComponent(obj.get("keybind").getAsString());
		
		}else
			throw new JsonParseException("Don't know how to turn " + obj + " into a Component");
		
		// modifier
		component.modifier.parse(obj);
		
		// == extra
		if(obj.has("extra")){
			final JsonArray extra = obj.getAsJsonArray("extra");
			
			if(extra.size() == 0)
				throw new JsonParseException("Unexpected empty array of components");
			
			for(int i=0; i<extra.size(); i++){
				component.addSibling(parse(extra.get(i).getAsJsonObject()));
			}
		}
		
		return component;
	}
	
	private static ChatBaseComponent parse(JsonArray array){
		ChatBaseComponent component = null;
		
		for(JsonElement el:array){
			final ChatBaseComponent sibling = parse(el);
			
			if(component == null)
				component = sibling;
			else
				component.addSibling(sibling);
		}
		
		return component;
	}
	
	@Override
	public Iterator<ChatBaseComponent> iterator(){
		return new Iterator<ChatBaseComponent>(){
			
			int index = -1;
			
			@Override
			public boolean hasNext(){
				return index < siblings.size();
			}

			@Override
			public ChatBaseComponent next(){
				return (index++) == -1 ? ChatBaseComponent.this : siblings.get(index);
			}
		};
	}
}