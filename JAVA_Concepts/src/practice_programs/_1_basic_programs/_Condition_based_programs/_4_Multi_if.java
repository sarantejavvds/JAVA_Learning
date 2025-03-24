package practice_programs._1_basic_programs._Condition_based_programs;

import java.util.Scanner;

public class _4_Multi_if 
{

	public _4_Multi_if() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

       int a, b, c;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter value of a : ");
		a = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter value of b : ");
		b = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter value of c : ");
		c = scanner.nextInt();
		scanner.nextLine();
		
		if(a>b && a>c)
		{
			System.out.println("a is big");
		}
		else if(b>a && b>c)
		{
			System.out.println("b is big");
		}
		else
		{
		    System.out.println("c is big");
		}
		
		scanner.close();
	}

}
