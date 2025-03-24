package _002_Datatypes._1_Primitive_Datatypes._4_Boolean_Type;

import java.util.Scanner;

public class _Boolean_Datatype 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		boolean _true = true;
		
		boolean _false = false;
		
		System.out.print("Enter a boolean value => true or false: ");
		boolean _boolean_variable = scanner.nextBoolean();
		
		System.out.println("_boolean_variable result: " + _boolean_variable);
		
		scanner.close();
	}

}
