package _005_Conditional_Statements_in_JAVA._5_Multi_if_Condition;

import java.util.Scanner;

public class _Grade_of_Student_using_Multi_if_Condition 
{

	public static void main(String[] args) 
	{
			Scanner scanner = new Scanner(System.in);

		 System.out.print("Enter score: ");
		 int score = scanner.nextInt();
		 scanner.nextLine();

	        if(score >= 90) 
	        {
	            System.out.println("Grade: A");
	        } 
	        else if(score >= 80) 
	        {
	            System.out.println("Grade: B");
	        } 
	        else if (score >= 70) 
	        {
	            System.out.println("Grade: C");
	        }
	        else if (score >= 60) 
	        {
	            System.out.println("Grade: D");
	        }
	        else
	        {
	            System.out.println("Grade: F");
	        }

	        scanner.close();
	}

}
