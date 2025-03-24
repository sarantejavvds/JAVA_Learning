package _006_String_Class_predefined_Functions._05_equals_Function;

import java.util.Scanner;

public class _Checking_Two_Strings_Equal_or_Not_with_CaseSensitivity
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter 1st String: ");
		String String_1 = scanner.next();
		scanner.nextLine();
		
		System.out.print("Enter 2nd String: ");
		String String_2 = scanner.next();
		scanner.nextLine();
		
		if(String_1.equals(String_2))
		{
			System.out.println(String_1 + " is Equal to " + String_2);
		}
		else
		{
			System.err.println(String_1 + " is Not Equal to " + String_2);
		}
		
		scanner.close();

	}

}
