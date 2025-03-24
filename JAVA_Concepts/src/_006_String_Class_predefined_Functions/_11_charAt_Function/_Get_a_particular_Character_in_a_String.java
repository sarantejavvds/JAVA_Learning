package _006_String_Class_predefined_Functions._11_charAt_Function;

import java.util.Scanner;

public class _Get_a_particular_Character_in_a_String 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String: ");
		String String_Original = scanner.nextLine();
		
		System.out.println("Length of String : " + String_Original.length());

		System.out.print("Enter position of Character : ");
		int character_position = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Result character is '" + String_Original.charAt(character_position) + "' ");
		
		scanner.close();
	}

}
