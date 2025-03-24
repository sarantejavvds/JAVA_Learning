package _002_Datatypes._1_Primitive_Datatypes._1_Whole_Numbers_types;

import java.util.Scanner;

public class _Whole_Numbers_Datatypes
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
        byte _byte;
		
		short _short;
		
		int _int;
		
		long _long;
		
		
		_byte = 127;
		System.out.print("Enter input for _byte (Range => -128 to +127) : ");
		_byte = scanner.nextByte();
		scanner.nextLine();
		
		_short = 32767;
		System.out.print("Enter input for _short (Range => -32768 to +32767) : ");
		_short = scanner.nextShort();
		scanner.nextLine();
		
		_int = 2147483647;
		System.out.print("Enter input for _int (Range => -2147483648 to +2147483647) : ");
		_int = scanner.nextInt();
		scanner.nextLine();
		
		_long = 9223372036854775807l;
		System.out.print("Enter input for _long (Range => -9223372036854775808 to +9223372036854775807) : ");
		_long = scanner.nextLong();
		scanner.nextLine();
		
		System.out.println();
		
		System.out.println("_byte value: " + _byte);
		System.out.println("_short value: " + _short);
		System.out.println("_int value: " + _int);
		System.out.println("_long value: " + _long);
		
		scanner.close();
	}

}
