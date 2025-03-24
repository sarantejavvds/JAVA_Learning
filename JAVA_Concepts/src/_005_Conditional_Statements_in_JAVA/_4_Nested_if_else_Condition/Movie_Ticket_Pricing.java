package _005_Conditional_Statements_in_JAVA._4_Nested_if_else_Condition;

import java.util.Scanner;

public class Movie_Ticket_Pricing 
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Age: ");
        int age = scanner.nextInt(); // Customer's age
        scanner.nextLine(); 
		
		System.out.print("Is it Weekend?(true/false): ");
        boolean isWeekend = scanner.nextBoolean(); // Is it a weekend?
        scanner.nextLine(); 

        if(age < 13) 
        {
            if(isWeekend) 
            {
                System.out.println("Ticket Price: Rs. 120");
            } 
            else 
            {
                System.out.println("Ticket Price: Rs. 100");
            }
        } 
        else if(age  <= 64) 
        {
            if (isWeekend) 
            {
                System.out.println("Ticket Price: Rs. 180");
            }
            else 
            {
                System.out.println("Ticket Price: Rs. 150");
            }
        } 
        else 
        {
            if (isWeekend) 
            {
                System.out.println("Ticket Price: Rs. 150");
            } 
            else 
            {
                System.out.println("Ticket Price: Rs. 130");
            }
        }

        scanner.close();
	}

}
