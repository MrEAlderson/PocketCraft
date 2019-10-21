package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.TextFormat;
import de.marcely.pocketcraft.java.component.chat.*;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8FormattedTextTranslator implements ComponentTranslator<ChatBaseComponent, String> {
	
	// TODO
	@Override
	public ChatBaseComponent toJava(TranslateComponents translate, String component){
		return null;
	}

	@Override
	public String toBedrock(TranslateComponents translate, ChatBaseComponent base){
		final StringBuilder builder = new StringBuilder();
		
		toBedrock(translate, base, builder);
		
		return builder.toString();
	}
	
	private static void toBedrock(TranslateComponents translate, ChatBaseComponent base, StringBuilder builder){
		// modifier
		{
			final ChatModifier modifier = base.getModifier();
			
			if(modifier.getColor() != null)
				builder.append((TextFormat) translate.toBedrock(modifier.getColor(), TranslateComponents.TEXT_COLOR));
			
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
			toBedrock(translate, sibling, builder);
	}
}