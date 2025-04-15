package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

import java.util.Scanner;

public class To_check_whether_a_specific_element_was_in_Array_or_not 
{

	public To_check_whether_a_specific_element_was_in_Array_or_not() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		String[] Animals = {"cat", "dog", "cow", "mouse", "horse", "ant"};
		
		System.out.print("Enter the Animal name you want to check: ");
		String check_this_animal = scanner.next();
		scanner.nextLine();
		
		boolean is_animal_exists = false;
		
		for(String animal : Animals)
		{
			if(animal.equalsIgnoreCase(check_this_animal))
			{
				is_animal_exists = true;				
				break; /* Exits from this Loop */
			}
		}
		
		if(is_animal_exists)
		{
			System.out.println(check_this_animal+" is present , test pass");
		}
		else
		{
			System.err.println(check_this_animal+" is not present , test fail");
		}
		
		scanner.close();

	}

}
