package _006_String_Class_predefined_Functions._11_charAt_Function;

import java.util.Scanner;

public class _Print_all_Characters_in_a_String_in_Ascending_order 
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter input String: ");
		String String_Original = scanner.nextLine();
		
		for(int i = 0 ; i < String_Original.length() ; i++)
		{
			System.out.println(String_Original.charAt(i));
		}
		
		scanner.close();

	}

}
