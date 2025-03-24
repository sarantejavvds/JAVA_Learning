package _006_String_Class_predefined_Functions._01_length_Function;

import java.util.Scanner;

public class _Find_Length_of_Any_String_value 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.next();
		scanner.nextLine();
		
		int length_of_String = any_String.length();
		
		System.out.println("Length of String : " + length_of_String);
			
		scanner.close();
	}

}
