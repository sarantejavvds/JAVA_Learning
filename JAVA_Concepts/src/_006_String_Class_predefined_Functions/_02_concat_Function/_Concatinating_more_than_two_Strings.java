package _006_String_Class_predefined_Functions._02_concat_Function;

import java.util.Scanner;

public class _Concatinating_more_than_two_Strings 
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
		
		System.out.print("Enter 3rd String: ");
		String String_3 = scanner.next();
		scanner.nextLine();
		
		System.out.print("Enter 4th String: ");
		String String_4 = scanner.next();
		scanner.nextLine();
		
		System.out.println("Combinational String : " + String_1.concat(String_2).concat(String_3).concat(String_4));
			
		scanner.close();

	}

}
