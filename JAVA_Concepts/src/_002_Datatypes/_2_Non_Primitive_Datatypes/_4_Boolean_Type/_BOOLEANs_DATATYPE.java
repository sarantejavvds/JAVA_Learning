package _002_Datatypes._2_Non_Primitive_Datatypes._4_Boolean_Type;

import java.util.Scanner;

public class _BOOLEANs_DATATYPE 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		Boolean _boolean;
		System.out.print("Enter input for _boolean : ");
		_boolean = scanner.nextBoolean();
		scanner.nextLine();

		System.out.println("value of _boolean: " + _boolean);
		System.out.println("Type of _boolean: " + _boolean.TYPE);
		System.out.println("Comparing _boolean value with itself: " + _boolean.equals(_boolean));
		System.out.println("Converting _boolean into String: " + _boolean.toString());
		System.out.println("Class of _boolean: " + _boolean.getClass());
		System.out.println("an Optional describing Boolean instance: " + _boolean.describeConstable());
		System.out.println("Hash code of _boolean: " + _boolean.hashCode(_boolean));
		
		System.out.println();

		scanner.close();
	}

}
