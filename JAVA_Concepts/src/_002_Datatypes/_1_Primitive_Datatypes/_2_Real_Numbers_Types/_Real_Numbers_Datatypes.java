package _002_Datatypes._1_Primitive_Datatypes._2_Real_Numbers_Types;

import java.util.Scanner;

public class _Real_Numbers_Datatypes 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.err.println("DONOT provide any Suffixes at RunTime Inputs...");
		System.out.println();
		
		float _float = 2147483648.995f; /* suffix f ==> indicates the number is float */
		System.out.print("Enter input for float: ");
		_float = scanner.nextFloat();
		scanner.nextLine();
		
		double _double_d = 99.99d; /* suffix d ==> indicates the number is double */
		System.out.print("Enter input for double_d: ");
		_double_d = scanner.nextDouble();
		scanner.nextLine();
		
		double _double = 9999.9999956; /* No suffix ==> default double */
		System.out.print("Enter input for double: ");
		_double = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("_float value: " + _float);
		
		System.out.println("_double_d value: " + _double_d);
		
		System.out.println("_double value: " + _double);
		
		scanner.close();
	}

}
