package _006_String_Class_predefined_Functions._12_reverse_Function;

import java.util.Scanner;

public class _Program_to_Reverse_a_String 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.nextLine();
		
		StringBuffer string_buffer = new StringBuffer(any_String);
		
		StringBuffer reversed_String = string_buffer.reverse();
		
		System.out.println("Reversed String : " + reversed_String);
			
		scanner.close();
	}

}
