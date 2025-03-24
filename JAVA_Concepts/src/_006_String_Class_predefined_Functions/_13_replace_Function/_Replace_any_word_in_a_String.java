package _006_String_Class_predefined_Functions._13_replace_Function;

import java.util.Scanner;

public class _Replace_any_word_in_a_String 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.nextLine();

		System.out.print("Enter any Word from String: ");
		String word_to_be_replaced =  scanner.nextLine();
		
		System.out.print("Enter new word to replace old word in the String: ");
		String replaced_with_new_Word =  scanner.nextLine();
		
		System.out.println("New String : " + any_String.replace(word_to_be_replaced, replaced_with_new_Word));
		
		scanner.close();
	}

}
