package _012_Exception_Handling_in_JAVA._2_Unchecked_Exceptions;

public class ArithmeticException_example
{
	public static void main(String[] args) 
	{
		int n = 10;
        int m = 0;

        try
        { 
            // Code that may throw an exception
            int ans = n / m;
            System.out.println("Answer: " + ans);
        }
        catch (ArithmeticException e) 
        {  
            // Handling the exception
            System.out.println("Error: Division by zero is not allowed!");
        }
	}
}
