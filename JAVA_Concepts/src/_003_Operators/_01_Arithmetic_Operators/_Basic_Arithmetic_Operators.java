package _003_Operators._01_Arithmetic_Operators;

import java.util.Scanner;

public class _Basic_Arithmetic_Operators 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		int value_1, value_2;
		
		System.out.print("Enter input for value_1: ");
		value_1 = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter input for value_2: ");
		value_2 = scanner.nextInt();
		scanner.nextLine();
		
         System.out.println();
		
		System.out.println("Addition of " + value_1 + " and " + value_2 + " : " + (value_1 + value_2) );
		System.out.println("Substraction of " + value_1 + " and " + value_2 + " : " + (value_1 - value_2) );
        System.out.println("Multiplication of " + value_1 + " and " + value_2 + " : " + (value_1 * value_2) );
		System.out.println("Division of " + value_1 + " and " + value_2 + " : " + (value_1 / value_2) );
		System.out.println("Modulus of " + value_1 + " and " + value_2 + " : " + (value_1 % value_2) );
		
		scanner.close();
	}

}
