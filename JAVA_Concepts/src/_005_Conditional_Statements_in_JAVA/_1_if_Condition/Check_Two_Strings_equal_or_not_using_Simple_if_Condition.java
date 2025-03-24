package _005_Conditional_Statements_in_JAVA._1_if_Condition;

import java.util.Scanner;

public class Check_Two_Strings_equal_or_not_using_Simple_if_Condition 
{

	public static void main(String[] args) 
	{

		String Expected_Title, Actual_Title;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Expected Title: ");
		Expected_Title = scanner.next();
		scanner.nextLine();
		
		System.out.print("Enter Actual Title: ");
		Actual_Title = scanner.next();
		scanner.nextLine();
		
		if(Expected_Title.equalsIgnoreCase(Actual_Title))
		{
			System.out.println("Test Case Passed");
		}
		
		scanner.close();
	
	}

}
