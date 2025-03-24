package _006_String_Class_predefined_Functions._03_toLowerCase_Function;

import java.util.Scanner;

public class _Converting_all_characters_to_LowerCase 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String in all Uppercases : ");
		String String_input = scanner.next();
		scanner.nextLine();

		String result_String = String_input.toLowerCase();
		
		System.out.println("Conversion of input String '" + String_input + "' into all LowerCase: " + result_String);
		
		scanner.close();
	}

}
