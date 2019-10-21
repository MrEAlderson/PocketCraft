package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.TextFormat;
import de.marcely.pocketcraft.java.component.chat.ChatColor;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8TextColorTranslator implements ComponentTranslator<ChatColor, TextFormat> {

	@Override
	public ChatColor toJava(TranslateComponents translate, TextFormat component){
		return ChatColor.values()[component.ordinal()];
	}

	@Override
	public TextFormat toBedrock(TranslateComponents translate, ChatColor component){
		return TextFormat.values()[component.ordinal()];
	}
}
