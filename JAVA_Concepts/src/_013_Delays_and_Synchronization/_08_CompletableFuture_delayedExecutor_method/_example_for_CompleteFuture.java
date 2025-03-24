package _013_Delays_and_Synchronization._08_CompletableFuture_delayedExecutor_method;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class _example_for_CompleteFuture
{

	public static void main(String[] args) 
	{
		System.out.println("Start ............");
		
		CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS, Executors.newSingleThreadScheduledExecutor()).execute( ()-> System.out.println("Executed After 3 Seconds........") );

		System.out.println("Waiting.....................");
	}

}
