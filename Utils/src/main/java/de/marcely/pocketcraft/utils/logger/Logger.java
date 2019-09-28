package de.marcely.pocketcraft.utils.logger;

import de.marcely.pocketcraft.utils.Application;
import lombok.Getter;
import lombok.Setter;

public class Logger {
	
	@Getter @Setter private static LoggerImplementation implementation = new DefaultLoggerImplementation();
	
	@Getter private final Application application;
	@Getter private final String name;
	
	@Getter @Setter private Level level = Level.values()[0];
	
	private Logger(Application app, String name){
		this.application = app;
		this.name = name;
	}
	
	public void debug(String msg){
		log(Level.DEBUG, msg);
	}
	
	public void info(String msg){
		log(Level.INFO, msg);
	}
	
	public void warn(String msg){
		log(Level.WARN, msg);
	}
	
	public void error(String msg){
		log(Level.ERROR, msg);
	}
	
	public void fatal(String msg){
		log(Level.FATAL, msg);
	}
	
	public void log(Logger.Level level, String msg){
		if(level.ordinal() < this.level.ordinal())
			return;
		
		if(level == Level.DISABLED)
			throw new IllegalArgumentException("This level isn't logable");
		
		try{
			Logger.implementation.log(this, level, msg);
		}catch(Exception e){
			e.printStackTrace();
		}catch(Error e){
			e.printStackTrace();
		}
	}
	
	public static Logger get(Application app, String name){
		return new Logger(app, name);
	}
	
	
	
	public static enum Level {
		
		DEBUG,
		INFO,
		WARN,
		ERROR,
		FATAL,
		
		DISABLED;
	}
}