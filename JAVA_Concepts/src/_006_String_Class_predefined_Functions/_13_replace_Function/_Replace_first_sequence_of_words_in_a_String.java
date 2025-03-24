package _006_String_Class_predefined_Functions._13_replace_Function;

import java.util.Scanner;

public class _Replace_first_sequence_of_words_in_a_String 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any String: ");
		String any_String = scanner.nextLine();

		System.out.print("Enter First sequence of Words from String: ");
		String first_words_to_be_replaced =  scanner.nextLine();
		
		System.out.print("Enter new word to replace old words in the String: ");
		String replaced_with_new_Word =  scanner.nextLine();
		
		System.out.println("New String : " + any_String.replaceFirst(first_words_to_be_replaced, replaced_with_new_Word));
		
		scanner.close();
	}

}
