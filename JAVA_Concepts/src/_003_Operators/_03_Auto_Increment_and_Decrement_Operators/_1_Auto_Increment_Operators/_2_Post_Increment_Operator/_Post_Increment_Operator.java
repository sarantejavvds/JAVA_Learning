package _003_Operators._03_Auto_Increment_and_Decrement_Operators._1_Auto_Increment_Operators._2_Post_Increment_Operator;

import java.util.Scanner;

public class _Post_Increment_Operator 
{

	public static void main(String[] args) 
	{
		byte value;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the value: ");
		value = scanner.nextByte();
		scanner.nextLine();
		
		System.out.println("Before Post-Increment, value: " + value);
		
	     System.out.println("While in process of Post-Increment, value: " + (value++) );
		
		System.out.println("After Post-Increment, value: " + value);
		
		scanner.close();

	}

}
