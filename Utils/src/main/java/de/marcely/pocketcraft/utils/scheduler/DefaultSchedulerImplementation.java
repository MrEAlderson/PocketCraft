package de.marcely.pocketcraft.utils.scheduler;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultSchedulerImplementation implements SchedulerImplementation {
	
	private Timer timer = new Timer();
	private Map<Integer, Runnable> timers = new ConcurrentHashMap<>();
	
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
				timers.remove(id);
				
				try{
					runn.run();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		
		this.timers.put(id, () -> {
			task.cancel();
		});
		
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
		
		if(period < 1000){
			final AtomicBoolean isCancelled = new AtomicBoolean(false);
			
			this.timers.put(id, () -> {
				isCancelled.set(true);
			});
			
    		runLater(() -> {
    			new Thread(){
    				
    				public void run(){
    					final double tps = 1000D / period;
    					final double nsPeriod = 1000000000 / tps;
    					
    					long lastTime = System.nanoTime();
    					double delta = 0;
    					
    					while(isCancelled.get() == false){
    						final long now = System.nanoTime();
    						
    						delta += (now - lastTime) / nsPeriod;
    						lastTime = now;
    						
    						if(delta >= 1){
    							delta--;
    							
    							try{
    								runn.run();
    							}catch(Exception e){
    								e.printStackTrace();
    							}
    						}
    					}
    				}
    			}.start();
    		}, firstTime);
    	
		}else{
			final TimerTask task = new TimerTask(){
				public void run(){
					timers.remove(id);
					
					try{
						runn.run();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			};
			
			this.timers.put(id, () -> {
				task.cancel();
			});
			
			this.timer.schedule(task, firstTime, period);
		}
		
		return id;
	}

	@Override
	public boolean cancel(int id){
		final Runnable task = this.timers.remove(id);
		
		if(task == null)
			return false;
		
		task.run();
		
		return true;
	}
	
	private int nextAvailableID(){
		int id = Integer.MIN_VALUE;
		
		while(timers.containsKey(id))
			id++;
		
		return id;
	}
}