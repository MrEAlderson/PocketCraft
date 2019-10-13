package de.marcely.pocketcraft.translate.component;

import de.marcely.pocketcraft.bedrock.component.TextFormat;
import de.marcely.pocketcraft.java.component.chat.*;

public class FormattedTextTranslator {
	
	public static String chatToBedrock(ChatBaseComponent base){
		final StringBuilder builder = new StringBuilder();
		
		toBedrock(base, builder);
		
		return builder.toString();
	}
	
	public static TextFormat colorToBedrock(ChatColor color){
		return TextFormat.values()[color.ordinal()];
	}
	
	private static void toBedrock(ChatBaseComponent base, StringBuilder builder){
		// modifier
		{
			final ChatModifier modifier = base.getModifier();
			
			if(modifier.getColor() != null)
				builder.append(colorToBedrock(modifier.getColor()));
			
			if(modifier.getBold() != null && modifier.getBold())
				builder.append(TextFormat.BOLD);
			
			if(modifier.getItalic() != null && modifier.getItalic())
				builder.append(TextFormat.ITALIC);
			
			if(modifier.getStrikethrough() != null && modifier.getStrikethrough())
				builder.append(TextFormat.STRIKETHROUGH);
			
			if(modifier.getUnderlined() != null && modifier.getUnderlined())
				builder.append(TextFormat.UNDERLINE);
			
			if(modifier.getObfuscated() != null && modifier.getObfuscated())
				builder.append(TextFormat.OBFUSCATED);
		}
		
		// component types
		if(base instanceof ChatTextComponent)
			builder.append(((ChatTextComponent) base).getText());
		
		// siblings
		for(ChatBaseComponent sibling:base.getSiblings())
			toBedrock(sibling, builder);
	}
}