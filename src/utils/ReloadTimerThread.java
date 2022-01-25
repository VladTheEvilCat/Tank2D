package utils;

import utils.exceptions.ReloadFinished;
import utils.exceptions.ReloadingException;

/**
 * 
 * @author casov
 * This here thing can be used as a timer that uses a separate thread.
 *
 */

public class ReloadTimerThread implements Runnable{
	
	//private Thread thread;
	public final String name;
	private long waitTime;
	private boolean debugMode = false;
	
	/**
	 * 
	 * Constructor
	 * @param name Name of thread
	 * @param waitTime time to wait in miliseconds
	 */
	public ReloadTimerThread(String name, long waitTime){
		if(debugMode)System.out.println("Creating new thread: \""+name+"\"");
		this.name = name;
		this.waitTime = waitTime;
		if(debugMode)System.out.println("Created new thread: \""+name+"\"");
	}
	
	
	/**
	 * Runs the timer, like pressing start on a stopwatch
	 */
	@Override
	public void run() {
		if(debugMode)System.out.println("Running thread: \""+name+"\"");
		try {
			if(debugMode)System.out.println("Thread: \""+name+"\" Sleeping for: "+waitTime+"ms...");
			Thread.sleep(this.waitTime);
			if(debugMode)System.out.println("Thread: \""+name+"\" just woke up!");
			throw new ReloadFinished("Finished Reloading!");
		} catch (InterruptedException e) {
			if(debugMode)System.out.println("Caught Interrupted Exception from thread: \""+name+"\"");
			throw new ReloadingException("Weapon might still be reloading: ");
		}
	}

	/**
	 * Sets debug mode state; helpfull messages will be printed when enabled;
	 * Usefull for dubugging (of course)
	 * @param enabled
	 */
	public void setDebugMode(boolean enabled) {
		this.debugMode = enabled;
	}
}
