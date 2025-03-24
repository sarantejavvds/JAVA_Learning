package _013_Delays_and_Synchronization._07_LockSupport_parkNanos_method;

import java.util.concurrent.locks.LockSupport;

public class _example_for_LockSupport_parkNanos 
{

	public static void main(String[] args) 
	{
		System.out.println("Start .......");
		
		LockSupport.parkNanos(3000000000l);
		
		System.out.println("Ends After 3 Seconds ......");

	}

}
