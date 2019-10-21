package de.marcely.pocketcraft.translate.bedrocktojava.component;

public interface ComponentTranslator<LangJava, LangBedrock> {
	
	public LangJava toJava(TranslateComponents translate, LangBedrock component);
	
	public LangBedrock toBedrock(TranslateComponents translate, LangJava component);
	
	@SuppressWarnings("unchecked")
	public default LangJava toJavaUncast(TranslateComponents translate, Object component){
		return toJava(translate, (LangBedrock) component);
	}
	
	@SuppressWarnings("unchecked")
	public default LangBedrock toBedrockUncast(TranslateComponents translate, Object component){
		return toBedrock(translate, (LangJava) component);
	}
}