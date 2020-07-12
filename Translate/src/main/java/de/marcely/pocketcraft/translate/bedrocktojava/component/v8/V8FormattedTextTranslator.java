package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BTextFormat;
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
				builder.append((BTextFormat) translate.toBedrock(modifier.getColor(), TranslateComponents.TEXT_COLOR));
			
			if(modifier.getBold() != null && modifier.getBold())
				builder.append(BTextFormat.BOLD);
			
			if(modifier.getItalic() != null && modifier.getItalic())
				builder.append(BTextFormat.ITALIC);
			
			if(modifier.getStrikethrough() != null && modifier.getStrikethrough())
				builder.append(BTextFormat.STRIKETHROUGH);
			
			if(modifier.getUnderlined() != null && modifier.getUnderlined())
				builder.append(BTextFormat.UNDERLINE);
			
			if(modifier.getObfuscated() != null && modifier.getObfuscated())
				builder.append(BTextFormat.OBFUSCATED);
		}
		
		// component types
		if(base instanceof ChatTextComponent)
			builder.append(((ChatTextComponent) base).getText());
		
		// siblings
		for(ChatBaseComponent sibling:base.getSiblings())
			toBedrock(translate, sibling, builder);
	}
}