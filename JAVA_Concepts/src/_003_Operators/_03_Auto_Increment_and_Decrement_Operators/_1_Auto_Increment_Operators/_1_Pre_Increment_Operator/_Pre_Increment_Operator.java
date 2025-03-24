package _003_Operators._03_Auto_Increment_and_Decrement_Operators._1_Auto_Increment_Operators._1_Pre_Increment_Operator;

import java.util.Scanner;

public class _Pre_Increment_Operator 
{

	public static void main(String[] args) 
	{	
		byte value;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the value: ");
		value = scanner.nextByte();
		scanner.nextLine();
		
		System.out.println("Before Pre-Increment, value: " + value);
		
	     System.out.println("While in process of Pre-Increment, value: " + (++value) );
		
		System.out.println("After Pre-Increment, value: " + value);
		
		scanner.close();
	}

}
