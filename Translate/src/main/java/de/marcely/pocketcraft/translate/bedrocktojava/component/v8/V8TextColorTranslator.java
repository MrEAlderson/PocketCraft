package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BTextFormat;
import de.marcely.pocketcraft.java.component.chat.ChatColor;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8TextColorTranslator implements ComponentTranslator<ChatColor, BTextFormat> {

	@Override
	public ChatColor toJava(TranslateComponents translate, BTextFormat component){
		return ChatColor.values()[component.ordinal()];
	}

	@Override
	public BTextFormat toBedrock(TranslateComponents translate, ChatColor component){
		return BTextFormat.values()[component.ordinal()];
	}
}
