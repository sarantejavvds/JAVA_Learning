package practice_programs._3_String_based_Programs;

import java.util.Scanner;

public class _String_Functionalities_basic 
{

	public _String_Functionalities_basic() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		System.out.println("Enter String Input : ");
		
		Scanner scanner = new Scanner(System.in);
        String input;
		input = scanner.next();
		scanner.nextLine();
		
		System.out.println("Given String : " + input);
		
		System.out.println("Convert Lower case : " + input.toLowerCase());
		
		System.out.println("Convert Upper case : " + input.toUpperCase());
		
		System.out.println("Input String length : " + input.length());
		
		System.out.println("Input String First Character : " + input.charAt(0));
		
	    System.out.println("Input String Last Character : " + input.charAt(input.length() - 1));
		
	    System.out.println("Printing Last 4 characters from Input String : " + input.substring(input.length() - 4));
	    
	    System.out.println("Printing first 3 Characters from Input String : " + input.substring(0, 3));
	    
	    
	    
		scanner.close();
	}

}
