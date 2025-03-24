package _002_Datatypes._2_Non_Primitive_Datatypes._1_Whole_Numbers_types;

import java.util.Scanner;

public class _WHOLE_NUMBERs_DATATYPES
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		Byte _Byte;
		System.out.print("Enter input for _byte (Range => -128 to +127) : ");
		_Byte = scanner.nextByte();
		scanner.nextLine();
		
		System.out.println("value of _Byte: " + _Byte);
		System.out.println("Minimum value of Byte: " + Byte.MIN_VALUE);
		System.out.println("Maximum value of Byte: " + _Byte.MAX_VALUE);
		System.out.println("Type of _Byte: " + _Byte.TYPE);
		System.out.println("Size of _Byte: " + _Byte.SIZE + " bits");
		System.out.println("byte Value of _Byte: " + _Byte.byteValue());
		System.out.println("short Value of _Byte: " + _Byte.shortValue());
		System.out.println("int Value of _Byte: " + _Byte.intValue());
		System.out.println("long Value of _Byte: " + _Byte.longValue());
		System.out.println("float Value of _Byte: " + _Byte.floatValue());
		System.out.println("double Value of _Byte: " + _Byte.doubleValue());
		System.out.println("Comparing _Byte value with itself: " + _Byte.equals(_Byte));
		System.out.println("Converting _Byte into String: " + _Byte.toString());
		System.out.println("Class of _Byte: " + _Byte.getClass());
		System.out.println("an Optional describing Byte instance: " + _Byte.describeConstable());
		System.out.println("Hash code of _Byte: " + _Byte.hashCode(_Byte));
		System.out.println("Two's Complement Binary Form of _Byte: " +  _Byte.BYTES + " bytes");
		
		System.out.println();
		
		System.out.println("=====================================================================================================\n");
		
		Short _short;
		System.out.print("Enter input for _short (Range => -32768 to +32767) : ");
		_short = scanner.nextShort();
		scanner.nextLine();
		
		System.out.println("value of _short: " + _short);
		System.out.println("Minimum value of Short: " + _short.MIN_VALUE);
		System.out.println("Maximum value of Short: " + Short.MAX_VALUE);
		System.out.println("Type of _short: " + _short.TYPE);
		System.out.println("Size of _short: " + _short.SIZE + " bits");
		System.out.println("byte Value of _short: " + _short.byteValue());
		System.out.println("short Value of _short: " + _short.shortValue());
		System.out.println("int Value of _short: " + _short.intValue());
		System.out.println("long Value of _short: " + _short.longValue());
		System.out.println("float Value of _short: " + _short.floatValue());
		System.out.println("double Value of _short: " + _short.doubleValue());
		System.out.println("Comparing _short value with itself: " + _short.equals(_short));
		System.out.println("Converting _short into String: " + _short.toString());
		System.out.println("Class of _short: " + _short.getClass());
		System.out.println("an Optional describing Short instance: " + _short.describeConstable());
		System.out.println("Hash code of _short: " + _short.hashCode(_short));
		System.out.println("Two's Complement Binary Form of _short: " +  _short.BYTES + " bytes");
		
		System.out.println();
		
		System.out.println("=====================================================================================================\n");
		
		Integer _integer = 10;
		System.out.print("Enter input for _integer (Range => -2147483648 to +2147483647) : ");
		_integer = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("value of _integer: " + _integer);
		System.out.println("Minimum value of Integer: " + _integer.MIN_VALUE);
		System.out.println("Maximum value of Integer: " + Integer.MAX_VALUE);
		System.out.println("Type of _integer: " + _integer.TYPE);
		System.out.println("Size of _integer: " + _integer.SIZE + " bits");
		System.out.println("byte Value of _integer: " + _integer.byteValue());
		System.out.println("short Value of _integer: " + _integer.shortValue());
		System.out.println("int Value of _integer: " + _integer.intValue());
		System.out.println("long Value of _integer: " + _integer.longValue());
		System.out.println("float Value of _integer: " + _integer.floatValue());
		System.out.println("double Value of _integer: " + _integer.doubleValue());
		System.out.println("Comparing _integer value with itself: " + _integer.equals(_integer));
		System.out.println("Converting _integer into String: " + _integer.toString());
		System.out.println("Class of _integer: " + _integer.getClass());
		System.out.println("an Optional describing Integer instance: " + _integer.describeConstable());
		System.out.println("Hash code of _integer: " + _integer.hashCode(_integer));
		System.out.println("Two's Complement Binary Form of _integer: " +  _integer.BYTES + " bytes");
		
		System.out.println();
		
		System.out.println("=====================================================================================================\n");
		
		Long _long;
		System.out.print("Enter input for _long (Range => -9223372036854775808l to +9223372036854775807l) : ");
		_long = scanner.nextLong();
		scanner.nextLine();
		
		System.out.println("value of _long: " + _long);
		System.out.println("Minimum value of Long: " + Long.MIN_VALUE);
		System.out.println("Maximum value of Long: " + _long.MAX_VALUE);
		System.out.println("Type of _long: " + _long.TYPE);
		System.out.println("Size of _long: " + _long.SIZE + " bits");
		System.out.println("byte Value of _long: " + _long.byteValue());
		System.out.println("short Value of _long: " + _long.shortValue());
		System.out.println("int Value of _long: " + _long.intValue());
		System.out.println("long Value of _long: " + _long.longValue());
		System.out.println("float Value of _long: " + _long.floatValue());
		System.out.println("double Value of _long: " + _long.doubleValue());
		System.out.println("Comparing _long value with itself: " + _long.equals(_long));
		System.out.println("Converting _long into String: " + _long.toString());
		System.out.println("Class of _long: " + _long.getClass());
		System.out.println("an Optional describing Long instance: " + _long.describeConstable());
		System.out.println("Hash code of _long: " + _long.hashCode(_long));
		System.out.println("Two's Complement Binary Form of _long: " +  _long.BYTES + " bytes");
		
		System.out.println();
		
		scanner.close();
	}
}