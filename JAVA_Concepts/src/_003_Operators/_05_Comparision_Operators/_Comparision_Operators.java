package _003_Operators._05_Comparision_Operators;

import java.util.Scanner;

public class _Comparision_Operators
{

	public static void main(String[] args) 
	{
		int val_1, val_2;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter val_1: ");
		val_1 = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Enter val_2: ");
		val_2 = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println();
		
		if(val_1 == val_2)
		{
			System.out.println(val_1 + " is Equal to " + val_2);
		}
		if(val_1 != val_2)
		{
			System.out.println(val_1 + " is Not Equal to " + val_2);
		}
		if(val_1 > val_2)
		{
			System.out.println(val_1 + " is Greater than " + val_2);
		}
		if(val_1 >= val_2)
		{
			System.out.println(val_1 + " is Greater than or Equal to " + val_2);
		}
		if(val_1 < val_2)
		{
			System.out.println(val_1 + " is Less than " + val_2);
		}
		if(val_1 <= val_2)
		{
			System.out.println(val_1 + " is Less than or Equal to " + val_2);
		}

		scanner.close();
	}

}
