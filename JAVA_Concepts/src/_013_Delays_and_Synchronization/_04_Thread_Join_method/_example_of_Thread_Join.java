package _013_Delays_and_Synchronization._04_Thread_Join_method;

public class _example_of_Thread_Join 
{

	public static void main(String[] args) 
	{
		System.out.println("Start ..........");
		
		Thread worker = new Thread( ()-> {
											try 
											{
												Thread.sleep(3000);
												System.out.println("Worker thread finished after 3 Seconds ...");
											} 
											catch (InterruptedException e_1) 
											{
												e_1.printStackTrace();
											}
										 } );
		worker.start();
		
		try 
		{
			worker.join();
		} 
		catch (InterruptedException e_2) 
		{
			e_2.printStackTrace();
		}
		
		System.out.println("Main Thread Continues after worker Thread Completes. ......");
		
	}

}
