package _006_String_Class_predefined_Functions._13_replace_Function;

import java.util.Scanner;

public class _Replace_Entire_String_with_a_New_String 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.nextLine();
		
		System.out.print("Enter new String to replace old String: ");
		String replaced_with_new_Word =  scanner.nextLine();
		
		System.out.println("New String : " + any_String.replaceAll(any_String, replaced_with_new_Word));
		
		scanner.close();
	}

}
