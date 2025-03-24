package _002_Datatypes._2_Non_Primitive_Datatypes._3_Character_Type;

import java.util.Scanner;

public class _CHARACTERs_DATATYPE 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		Character _character;
		System.out.print("Enter input for _character : ");
		_character = scanner.next().charAt(0);
		scanner.nextLine();

		System.out.println("value of _character: " + _character);
		System.out.println("Minimum value of Character: " + Character.MIN_VALUE);
		System.out.println("Maximum value of Character: " + Character.MAX_VALUE);
		System.out.println("Type of _character: " + _character.TYPE);
		System.out.println("Size of _character: " + _character.SIZE + " bits");
		System.out.println("char Value of _character: " + _character.charValue());
		System.out.println("Comparing _character value with itself: " + _character.equals(_character));
		System.out.println("Converting _character into String: " + _character.toString());
		System.out.println("Class of _character: " + _character.getClass());
		System.out.println("an Optional describing Float instance: " + _character.describeConstable());
		System.out.println("Hash code of _character: " + _character.hashCode(_character));
		System.out.println("UPPERCASE_LETTER : "+  _character.UPPERCASE_LETTER);
		System.out.println("Convert _character into LowerCase: " + _character.toLowerCase(_character));
		System.out.println("Convert _character into UpperCase: " + _character.toUpperCase(_character));
		System.out.println("Two's Complement Binary Form of _character: " + _character.BYTES + " bytes");

		System.out.println();

		scanner.close();
	}
}
