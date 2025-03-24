package _002_Datatypes._1_Primitive_Datatypes._3_Character_Type;

import java.util.Scanner;

public class _Character_Datatype 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		char gender = 'M';
		System.out.println("gender : " + gender);
		
		System.out.print("Enter a single Character input: ");
		char _single_character = scanner.next().charAt(0);
		
		System.out.println("_single_character : " + _single_character);
		
		scanner.close();
	}

}
