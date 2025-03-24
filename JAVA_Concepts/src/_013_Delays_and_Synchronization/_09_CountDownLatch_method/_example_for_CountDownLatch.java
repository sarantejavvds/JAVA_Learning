package _013_Delays_and_Synchronization._09_CountDownLatch_method;

import java.util.concurrent.CountDownLatch;

public class _example_for_CountDownLatch 
{

	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Start ......");
		
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread( ()-> {
							try 
							{
								Thread.sleep(3000);
								
								System.out.println("Worker Thread completed .......");
							} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
			
						 } ).start();
		
		latch.await();
		
		System.out.println("Main Thread continues after 3 Seconds......");

	}

}
