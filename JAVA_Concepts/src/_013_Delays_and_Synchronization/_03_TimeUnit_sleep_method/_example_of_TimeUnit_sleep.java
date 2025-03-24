package _013_Delays_and_Synchronization._03_TimeUnit_sleep_method;

import java.util.concurrent.TimeUnit;

public class _example_of_TimeUnit_sleep 
{

	public static void main(String[] args) 
	{
		System.out.println("Start........");
		
		try 
		{
			TimeUnit.SECONDS.sleep(3);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("End ........ After 3 Seconds ......");

	}

}
