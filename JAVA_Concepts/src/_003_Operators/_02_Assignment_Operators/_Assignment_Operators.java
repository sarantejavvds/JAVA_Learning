package _003_Operators._02_Assignment_Operators;

import java.util.Scanner;

public class _Assignment_Operators
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		int value = 1000, assignment_variable, original_value, new_value;
		
		original_value = value;
		
		System.out.print("Enter the assignment_variable value :");
		assignment_variable = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println();
		System.out.println("Before any operation, value : " + value);
		
		System.out.println();
		value += assignment_variable;
		System.out.println("After Addition operation, then assign, value:" + value);
		
		value -= assignment_variable;
		System.out.println("After Substraction operation, then assign, value:" + value);
		
		value *= assignment_variable;
		System.out.println("After Multiplication operation, then assign, value:" + value);
		
		value /= assignment_variable;
		System.out.println("After Division operation, then assign, value:" + value);
		
		value %= assignment_variable;
		System.out.println("After Modulus operation, then assign, value:" + value);
		
		System.out.println();
		
	    new_value = value;
		
		System.out.println("After every assignment operation, original_value = " + original_value + "...." + " new_value = " + new_value);
		
		scanner.close();
	}

}
