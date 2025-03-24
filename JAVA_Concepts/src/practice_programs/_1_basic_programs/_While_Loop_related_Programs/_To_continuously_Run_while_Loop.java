package practice_programs._1_basic_programs._While_Loop_related_Programs;

import java.util.Scanner;

public class _To_continuously_Run_while_Loop
{

	public static void main(String[] args) 
	{
		System.out.println("Do you want to continue...");
		
		Scanner scanner = new Scanner(System.in);
		boolean result = scanner.nextBoolean();
		
		while(result)
		{
			System.out.println("You decided to continue...");
			System.out.println("Still Do you want to continue...");
			result = scanner.nextBoolean();
		}
		
		System.out.println("You decided to exit!");
		
		scanner.close();
	}

}
