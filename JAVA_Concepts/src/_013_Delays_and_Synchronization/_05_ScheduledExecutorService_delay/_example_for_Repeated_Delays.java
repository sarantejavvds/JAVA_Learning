package _013_Delays_and_Synchronization._05_ScheduledExecutorService_delay;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _example_for_Repeated_Delays 
{

	public static void main(String[] args) 
	{
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		System.out.println("Start ..........");
		
		scheduler.schedule( ()-> {
			
									System.out.println("Execued After 3 Seconds ......");
									
								 }, 3, TimeUnit.SECONDS);
		
		scheduler.shutdown();

	}

}
