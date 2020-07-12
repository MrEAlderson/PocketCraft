package de.marcely.pocketcraft.bedrock.component;

public enum BTextFormat {
	
	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	DARK_AQUA('3'),
	DARK_RED('4'),
	DARK_PURPLE('5'),
	GOLD('6'),
	GRAY('7'),
	DARK_GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	AQUA('b'),
	RED('c'),
	LIGHT_PURPLE('d'),
	YELLOW('e'),
	WHITE('f'),
	
	OBFUSCATED('k'),
	BOLD('l'),
	STRIKETHROUGH('m'),
	UNDERLINE('n'),
	ITALIC('o'),
	
	RESET('r');
	
	private static final char ESCAPE = '\u00A7';
	
	private final String escapedCode;
	
	private BTextFormat(char code){
		this.escapedCode = new String(new char[]{ ESCAPE, code });
	}
	
	@Override
	public String toString(){
		return this.escapedCode;
	}
}
