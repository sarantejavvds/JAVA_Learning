package _002_Datatypes._2_Non_Primitive_Datatypes._2_Real_Numbers_Types;

import java.util.Scanner;

public class _REAL_NUMBERs_DATATYPES 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
		
        Float _float;
		System.out.print("Enter input for _float : ");
		_float = scanner.nextFloat();
		scanner.nextLine();
		
		System.out.println("value of _float: " + _float);
		System.out.println("Minimum value of Float: " + Float.MIN_VALUE);
		System.out.println("Maximum value of Float: " + _float.MAX_VALUE);
		System.out.println("Type of _float: " + _float.TYPE);
		System.out.println("Size of _float: " + _float.SIZE + " bits");
		System.out.println("byte Value of _float: " + _float.byteValue());
		System.out.println("short Value of _float: " + _float.shortValue());
		System.out.println("int Value of _float: " + _float.intValue());
		System.out.println("long Value of _float: " + _float.longValue());
		System.out.println("float Value of _float: " + _float.floatValue());
		System.out.println("double Value of _float: " + _float.doubleValue());
		System.out.println("Comparing _float value with itself: " + _float.equals(_float));
		System.out.println("Converting _float into String: " + _float.toString());
		System.out.println("Class of _float: " + _float.getClass());
		System.out.println("an Optional describing Float instance: " + _float.describeConstable());
		System.out.println("Hash code of _float: " + _float.hashCode(_float));
		System.out.println("Two's Complement Binary Form of _float: " +  _float.BYTES + " bytes");
		
		System.out.println();
		
		System.out.println("=====================================================================================================\n");
		
		Double _double;
		System.out.print("Enter input for _short (Range => -32768 to +32767) : ");
		_double = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println("value of _double: " + _double);
		System.out.println("Minimum value of Double: " + Double.MIN_VALUE);
		System.out.println("Maximum value of Double: " + Double.MAX_VALUE);
		System.out.println("Type of _double: " + _double.TYPE);
		System.out.println("Size of _double: " + _double.SIZE + " bits");
		System.out.println("byte Value of _double: " + _double.byteValue());
		System.out.println("short Value of _double: " + _double.shortValue());
		System.out.println("int Value of _double: " + _double.intValue());
		System.out.println("long Value of _double: " + _double.longValue());
		System.out.println("float Value of _double: " + _double.floatValue());
		System.out.println("double Value of _double: " + _double.doubleValue());
		System.out.println("Comparing _double value with itself: " + _double.equals(_double));
		System.out.println("Converting _double into String: " + _double.toString());
		System.out.println("Class of _double: " + _double.getClass());
		System.out.println("an Optional describing Double instance: " + _double.describeConstable());
		System.out.println("Hash code of _double: " + _double.hashCode(_double));
		System.out.println("Two's Complement Binary Form of _double: " +  _double.BYTES + " bytes");
		
		System.out.println();
		
		scanner.close();
	}

}
