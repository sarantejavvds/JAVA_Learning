package _013_Delays_and_Synchronization._02_Timer_Task_method;

import java.util.Timer;
import java.util.TimerTask;

public class _example_on_TimerTask 
{

	public static void main(String[] args) 
	{
		System.out.println("Start .... ");
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() 
		{
			
			@Override
			public void run()
			{
			  System.out.println("Executed  after 3 Seconds ......");
				
			}
			
		}, 3000);

		System.out.println("Waiting ........");
	}

}
