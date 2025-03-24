package _005_Conditional_Statements_in_JAVA._3_Nested_if_Condition;

import java.util.Scanner;

public class _Check_if_a_person_is_Eligible_to_Donate_Blood 
{

	public static void main(String[] args) 
	{
			Scanner scanner = new Scanner(System.in);
			
	        System.out.print("Enter the age: ");
	        int age = scanner.nextInt();
	        scanner.nextLine();
	        
	        System.out.print("Enter the weight: ");
	        int weight = scanner.nextInt();
	        scanner.nextLine();
	        
	        if(age > 18)
	        {      
	           if(weight > 50)
	           {    
	              System.out.println("The person is eligible to donate blood");    
	           }
	           else
	           {  
	              System.err.println("The person is not eligible to donate blood");    
	           }  
	        }
	        else
	        {  
	            System.err.println("Age must be greater than 18");  
	        }

	        scanner.close();
	}

}
