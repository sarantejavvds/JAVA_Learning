package _006_String_Class_predefined_Functions._16_compareTo_Function;

import java.util.Scanner;

public class _Comparing_Two_Strings
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Main String: ");
		String Main_String = scanner.nextLine();
		
		System.out.print("Enter Another String: ");
		String Another_String =  scanner.nextLine();
		
		if(Another_String.compareTo(Main_String) >= 1)
		{
			System.out.println("'" + Another_String + "' is bigger than '" + Main_String + "' ");
		}
		else if(Another_String.compareTo(Main_String) <= -1)
		{
			System.out.println("'" + Another_String + "' is smaller than '" + Main_String + "' ");
		}
		else if(Another_String.compareTo(Main_String) == 0)
		{
			System.out.println("'" + Another_String + "' is Equal to '" + Main_String + "' ");
		}
		
		scanner.close();

	}

}
