package _006_String_Class_predefined_Functions._07_subString_Function;

import java.util.Scanner;

public class _Find_a_Substring_in_a_String_from_Beginning_index_to_End_index 
{

	public static void main(String[] args) 
	{

		int beginning_index, end_index ;
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter a String : ");
		String String_Original = scanner.next();
		scanner.nextLine();
		
		System.out.println("Length of original String : " + String_Original.length());
		
		System.out.print("Enter a Beginning Index : ");
		beginning_index = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter a End Index : ");
		end_index = scanner.nextInt();
		scanner.nextLine();
		
		String SubString_Result = String_Original.substring(beginning_index, end_index);
		
		System.out.println("Substring of original String: " + SubString_Result);
		
		scanner.close();
	}

}
