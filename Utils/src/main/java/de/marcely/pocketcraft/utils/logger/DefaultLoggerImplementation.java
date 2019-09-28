package de.marcely.pocketcraft.utils.logger;

import de.marcely.pocketcraft.utils.logger.Logger.Level;

public class DefaultLoggerImplementation implements LoggerImplementation {

	@Override
	public void log(Logger logger, Level level, String msg){
		System.out.println("[" + logger.getApplication() + "-" + level + "] " + msg);
	}
}