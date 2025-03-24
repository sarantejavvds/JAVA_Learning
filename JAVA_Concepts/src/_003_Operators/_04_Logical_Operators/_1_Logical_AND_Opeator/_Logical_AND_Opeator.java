package _003_Operators._04_Logical_Operators._1_Logical_AND_Opeator;

import java.util.Scanner;

public class _Logical_AND_Opeator 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		boolean _true = true, _false = false;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Logial AND between Two True conditions : " + ( _true && _true ) );
		
		System.out.println("Logial AND between a True and a False conditions : " + ( _true && _false ) );
		
		System.out.println("Logial AND between a False and a True conditions : " + ( _false && _true ) );

		System.out.println("Logial AND between Two False conditions : " + ( _false && _false ) );
		
		scanner.close();
	}
}
