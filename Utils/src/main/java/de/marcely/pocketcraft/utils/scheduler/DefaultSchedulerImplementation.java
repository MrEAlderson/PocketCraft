package de.marcely.pocketcraft.utils.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DefaultSchedulerImplementation implements SchedulerImplementation {
	
	private Timer timer = new Timer();
	private Map<Integer, TimerTask> timers = new HashMap<>();
	
	public DefaultSchedulerImplementation(){ }
	
	@Override
	@Deprecated
	public int runLater(Runnable runn, long time){
		return runAsyncLater(runn, time);
	}

	@Override
	public int runAsyncLater(Runnable runn, long time){
		final int id = nextAvailableID();
		final TimerTask task = new TimerTask(){
			public void run(){
				runn.run();
			}
		};
		
		this.timers.put(id, task);
		
		this.timer.schedule(task, time);
		
		return id;
	}

	@Override
	@Deprecated
	public int runRepeated(Runnable runn, long firstTime, long period){
		return runAsyncRepeated(runn, firstTime, period);
	}

	@Override
	public int runAsyncRepeated(Runnable runn, long firstTime, long period){
		final int id = nextAvailableID();
		final TimerTask task = new TimerTask(){
			public void run(){
				runn.run();
			}
		};
		
		this.timers.put(id, task);
		
		this.timer.schedule(task, firstTime, period);
		
		return id;
	}

	@Override
	public boolean cancel(int id){
		final TimerTask task = this.timers.remove(id);
		
		if(task == null)
			return false;
		
		task.cancel();
		
		return true;
	}
	
	private int nextAvailableID(){
		int id = Integer.MIN_VALUE;
		
		while(timers.containsKey(id))
			id++;
		
		return id;
	}
}