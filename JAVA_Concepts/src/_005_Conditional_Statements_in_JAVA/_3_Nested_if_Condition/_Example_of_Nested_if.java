package _005_Conditional_Statements_in_JAVA._3_Nested_if_Condition;

import java.util.Scanner;

public class _Example_of_Nested_if 
{

	public _Example_of_Nested_if() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		int a,b;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter value of a : ");
		a = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter value of b : ");
		b = scanner.nextInt();
		scanner.nextLine();
		
		if(a!=b)
		{
			if(a>b)
			{
				System.out.println("a is big");
			}
			if(b > a)
			{
				System.out.println("b is big");
			}
		}
		
		scanner.close();
	}

}
