package _005_Conditional_Statements_in_JAVA._2_if_else_Condition;

import java.util.Scanner;

public class _Check_if_a_given_number_is_Divisible_by_5_or_not 
{

	public static void main(String[] args) 
	{
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Enter an input: ");
			int input = scanner.nextInt();
	        scanner.nextLine();
	 
	        if((input % 5) == 0)
	        {
	            //Divisible by 5
	            System.out.println("Divisibe by 5");
	        }
	        else
	        {
	            //Not divisible by 5
	            System.err.println("Not dividible by 5");
	        }

	        scanner.close();
	}

}
