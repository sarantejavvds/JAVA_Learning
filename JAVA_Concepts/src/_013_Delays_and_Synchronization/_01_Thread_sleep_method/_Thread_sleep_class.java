package _013_Delays_and_Synchronization._01_Thread_sleep_method;

public class _Thread_sleep_class 
{

	public static void main(String[] args)
	{
		System.out.println("Start");
		
		try 
		{
			Thread.sleep(3000);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("End .... After 3 Seconds ...");
	}

}