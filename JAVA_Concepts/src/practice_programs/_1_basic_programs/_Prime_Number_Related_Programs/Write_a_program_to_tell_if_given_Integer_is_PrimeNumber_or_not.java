package practice_programs._1_basic_programs._Prime_Number_Related_Programs;

import java.util.Scanner;

public class Write_a_program_to_tell_if_given_Integer_is_PrimeNumber_or_not 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub	
		byte count=0;
		long number, i=1;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter any number: ");
		number = scanner.nextLong();
		scanner.nextLine();
		
		while( i <= number )
		{
			if( (number % i) == 0 )
			{
				count++;
			}
			
			i++;
		}
		
		if(count == 2)
		{
			System.out.println( number + " is a Prime Number.");
		}
		else
		{
			System.err.println( number + " is NOT a Prime Number.");
		}

		scanner.close();
	}

}