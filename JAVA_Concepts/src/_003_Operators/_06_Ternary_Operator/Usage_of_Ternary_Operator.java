package _003_Operators._06_Ternary_Operator;

import java.util.Scanner;

public class Usage_of_Ternary_Operator 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		int age;
		String message;
		
		System.out.print("Enter the Age: ");
		age = scanner.nextInt();
		scanner.nextLine();
		
		message = (age >= 18) ? "You are eligible to vote." : "You are Not eligible to vote." ; 
		 
		System.out.println(message);

		scanner.close();
	}

}
