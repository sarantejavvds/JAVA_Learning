package _006_String_Class_predefined_Functions._04_toUpperCase_Function;

import java.util.Scanner;

public class _Converting_all_characters_to_UpperCase 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String in all Lowercases : ");
		String String_input = scanner.next();
		scanner.nextLine();

		String result_String = String_input.toUpperCase();
		
		System.out.println("Conversion of input String '" + String_input + "' into all UpperCase: " + result_String);
		
		scanner.close();
	}

}
