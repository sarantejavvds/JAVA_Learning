package _003_Operators._04_Logical_Operators._2_Logical_OR_Opeator;

import java.util.Scanner;

public class _Logical_OR_Opeator 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		boolean _true = true, _false = false;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Logial OR between Two True conditions : " + ( _true || _true ) );
		
		System.out.println("Logial OR between a True and a False conditions : " + ( _true || _false ) );
		
		System.out.println("Logial OR between a False and a True conditions : " + ( _false || _true ) );

		System.out.println("Logial OR between Two False conditions : " + ( _false || _false ) );

		scanner.close();

	}

}
