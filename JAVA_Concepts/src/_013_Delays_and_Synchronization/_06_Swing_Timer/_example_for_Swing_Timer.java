package _013_Delays_and_Synchronization._06_Swing_Timer;

import javax.swing.*;

public class _example_for_Swing_Timer
{

	public static void main(String[] args)
	{
		System.out.println("Start .........");
		
		Timer timer = new Timer(3000, e -> System.out.println("Execued After 3 Seconds ..........."));
		
		timer.setRepeats(false);
		
		timer.start();
		
		System.out.println("Waiting ...........");
		
		// Keep the application running long enough for the timer to execute
        // This is important for Swing-based programs as they need to keep the event dispatch thread running
        try 
        {
            Thread.sleep(5000); // Wait for the timer to execute
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
		
	}

}
