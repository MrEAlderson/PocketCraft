package de.marcely.pocketcraft.java.component.chat;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;

public enum ChatColor {
	
	// do not change order
	BLACK("black"),
	DARK_BLUE("dark_blue"),
	DARK_GREEN("dark_green"),
	DARK_AQUA("dark_aqua"),
	DARK_RED("dark_red"),
	DARK_PURPLE("dark_purple"),
	GOLD("gold"),
	GRAY("gray"),
	DARK_GRAY("dark_gray"),
	BLUE("blue"),
	GREEN("green"),
	AQUA("aqua"),
	RED("red"),
	LIGHT_PURPLE("light_purple"),
	YELLOW("yellow"),
	WHITE("white"),
	
	RANDOM("obfuscated"),
	BOLD("bold"),
	STRIKETHROUGH("strikethrough"),
	UNDERLINE("underline"),
	ITALIC("italic"),
	
	RESET("reset");
	
	@Getter private final String name;
	
	private ChatColor(String name){
		this.name = name;
	}
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public static @Nullable ChatColor getById(int id){
		if(id < 0 || id >= values().length)
			return null;
		
		return values()[id];
	}
	
	public static @Nullable ChatColor getByName(String name){
		for(ChatColor color:values()){
			if(color.name.equals(name))
				return color;
		}
		
		return null;
	}
}