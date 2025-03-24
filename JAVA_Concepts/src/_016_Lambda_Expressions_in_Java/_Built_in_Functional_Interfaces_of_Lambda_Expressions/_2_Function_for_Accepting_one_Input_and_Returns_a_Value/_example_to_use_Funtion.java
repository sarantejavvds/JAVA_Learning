package _016_Lambda_Expressions_in_Java._Built_in_Functional_Interfaces_of_Lambda_Expressions._2_Function_for_Accepting_one_Input_and_Returns_a_Value;

import java.util.Scanner;
import java.util.function.Function;



public class _example_to_use_Funtion 
{

	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter any number : ");
        long input_number = scanner.nextLong();
        scanner.nextLine();
		
        Function<Long, Long> square = x -> x * x;
        System.out.println("Square of a Number: " + square.apply(input_number));
        
        Function<Long, Long> cube = x -> x * x * x;
        System.out.println("Cube of a Number: " + cube.apply(input_number));

        scanner.close();
	}

}
