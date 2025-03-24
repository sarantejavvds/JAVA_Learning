package _016_Lambda_Expressions_in_Java._Built_in_Functional_Interfaces_of_Lambda_Expressions._1_Predicate_T_for_Boolean_Conditions;

import java.util.Scanner;
import java.util.function.Predicate;

public class _example_to_use_Predicate 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter any number : ");
        long input_number = scanner.nextLong();
        scanner.nextLine();
        
        System.out.println();
		
        Predicate<Long> is_Even = value -> ((value % 2) == 0);

		System.out.println("Is the Input Number is Even? : "+ is_Even.test(input_number));
		
		System.out.println();
		
		Predicate<Long> is_Odd = value -> ((value % 2) == 1);

		System.out.println("Is the Input Number is Odd? : "+ is_Odd.test(input_number));

		
		scanner.close();
	}

}
