package _005_Conditional_Statements_in_JAVA._6_Switch_Case_Condition;

import java.util.Scanner;

public class Display_Month_from_calender 
{

	public Display_Month_from_calender() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		int month;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Month Number : ");
		month = scanner.nextInt();
		
		switch(month)
		{
		    case 1 : System.out.println("January");
		             break;
		    
		    case 2 : System.out.println("February");
		             break;
		    
		    case 3 : System.out.println("March");
		             break;
		    
		    case 4 : System.out.println("April");
		             break;
		    
		    case 5 : System.out.println("May");
		    		 break;
		    
		    case 6 : System.out.println("June");
		    		 break;
		    
		    case 7 : System.out.println("July");
		    		 break;
		    
		    case 8 : System.out.println("August");
		    		 break;
		    
		    case 9 : System.out.println("September");
		    		 break;
		    
		    case 10 : System.out.println("October");
		    		  break;
		    
		    case 11 : System.out.println("November");
		    		  break;
		    
		    case 12 : System.out.println("December");
		              break;
		    
		    default : System.err.println("Invalid Input");
		}
		
		scanner.close();
	}

}
