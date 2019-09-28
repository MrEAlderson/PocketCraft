package de.marcely.pocketcraft.utils.scheduler;

import lombok.Getter;
import lombok.Setter;

public class Scheduler {
	
	@Getter @Setter private static SchedulerImplementation implementation = new DefaultSchedulerImplementation();
	
	public static int runLater(Runnable runn, long time){
		return implementation.runLater(runn, time);
	}
	
	public static int runAsyncLater(Runnable runn, long time){
		return implementation.runAsyncLater(runn, time);
	}
	
	public static int runRepeated(Runnable runn, long firstTime, long period){
		return implementation.runRepeated(runn, firstTime, period);
	}
	
	public static int runAsyncRepeated(Runnable runn, long firstTime, long period){
		return implementation.runAsyncRepeated(runn, firstTime, period);
	}
	
	public static boolean cancel(int id){
		return implementation.cancel(id);
	}
}