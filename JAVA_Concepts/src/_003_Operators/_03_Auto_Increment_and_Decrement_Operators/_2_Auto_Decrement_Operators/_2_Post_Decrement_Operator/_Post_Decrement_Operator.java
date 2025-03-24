package _003_Operators._03_Auto_Increment_and_Decrement_Operators._2_Auto_Decrement_Operators._2_Post_Decrement_Operator;

import java.util.Scanner;

public class _Post_Decrement_Operator 
{

	public static void main(String[] args) 
	{
		byte value;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the value: ");
		value = scanner.nextByte();
		scanner.nextLine();
		
		System.out.println("Before Post-Decrement, value: " + value);
		
	     System.out.println("While in process of Post-Decrement, value: " + (value--) );
		
		System.out.println("After Post-Decrement, value: " + value);
		
		scanner.close();

	}

}
