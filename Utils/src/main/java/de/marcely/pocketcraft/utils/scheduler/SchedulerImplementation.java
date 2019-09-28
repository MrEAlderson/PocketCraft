package de.marcely.pocketcraft.utils.scheduler;

public interface SchedulerImplementation {
	
	/**
	 * 
	 * @return Task ID
	 */
	public int runLater(Runnable runn, long time);
	
	/**
	 * 
	 * @return Task ID
	 */
	public int runAsyncLater(Runnable runn, long time);
	
	/**
	 * 
	 * @return Task ID
	 */
	public int runRepeated(Runnable runn, long firstTime, long period);
	
	/**
	 * 
	 * @return Task ID
	 */
	public int runAsyncRepeated(Runnable runn, long firstTime, long period);
	
	/**
	 * 
	 * @return If it was successfull or not
	 */
	public boolean cancel(int id);
}