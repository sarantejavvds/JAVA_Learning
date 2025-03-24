package practice_programs._3_String_based_Programs;

import java.util.Scanner;

public class _Program_to_Find_a_String_is_Palindrome_or_not 
{

	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.next();
		scanner.nextLine();
		
		//any_String = any_String.toLowerCase();
		
		StringBuffer string_buffer = new StringBuffer(any_String);
		
		StringBuffer reversed_String = string_buffer.reverse();
		
		String output_of_reversed_String = reversed_String.toString();
		
		if( (output_of_reversed_String).equalsIgnoreCase(any_String) )
		{
			System.out.println(any_String + " is a Palindrome");
		}
		else
		{
			System.err.println(any_String + " is NOT a Palindrome");
		}

		scanner.close();
	}

}
