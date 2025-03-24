package _003_Operators._04_Logical_Operators._3_Logical_NOT_Opeator;

import java.util.Scanner;

public class _Logical_NOT_Opeator 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		boolean _true = true, _false = false;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Logial NOT on a True condition : " + ( !(_true) ) );
		
		System.out.println("Logial NOT on a False condition : " + ( !(_false) ) );

		scanner.close();
	}

}
