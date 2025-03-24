package _006_String_Class_predefined_Functions._08_contains_Function;

import java.util.Scanner;

public class _Check_if_String_contains_given_sequence_of_characters
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String: ");
		String String_Original = scanner.nextLine();
		
		System.out.print("Enter contain characters : ");
		String Contains_in_String = scanner.nextLine();
		
		if(String_Original.contains(Contains_in_String))
		{
			System.out.println(String_Original + " is containing " + Contains_in_String);
		}
		else
		{
			System.err.println(String_Original + " is Not containing " + Contains_in_String);
		}
		
		scanner.close();
	}

}
