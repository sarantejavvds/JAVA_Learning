package practice_programs._1_basic_programs._For_Loop_related_Programs;

import java.util.Scanner;

public class Multiplication_Table_using_For_Loop 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		long multiplier, multiplicand, max_value;
		
		System.out.print("Enter the multiplier: ");
		multiplier = scanner.nextLong();
		scanner.nextLine();
		
		System.out.print("Enter the maximum multiplicand value: ");
		max_value = scanner.nextLong();
		scanner.nextLine();

		System.out.println("Multiplication Table for multiplier '" + multiplier + "' : ");
		scanner.close();
		
		for( multiplicand = 0; multiplicand <= max_value  ; multiplicand++ ) 
		{
			System.out.println( multiplier  + " * " + multiplicand + " = " + (multiplier * multiplicand) );
		}
		System.out.println();
	}
}
