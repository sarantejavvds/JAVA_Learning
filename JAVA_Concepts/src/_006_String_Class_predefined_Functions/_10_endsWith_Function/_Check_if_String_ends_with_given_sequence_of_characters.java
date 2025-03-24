package _006_String_Class_predefined_Functions._10_endsWith_Function;

import java.util.Scanner;

public class _Check_if_String_ends_with_given_sequence_of_characters
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String: ");
		String String_Original = scanner.nextLine();
		
		System.out.print("Enter end characters : ");
		String Ends_with_String = scanner.nextLine();
		
		if(String_Original.endsWith(Ends_with_String))
		{
			System.out.println(String_Original + " is Ending with " + Ends_with_String);
		}
		else
		{
			System.err.println(String_Original + " is Not Ending with " + Ends_with_String);
		}
		
		scanner.close();
	}

}
