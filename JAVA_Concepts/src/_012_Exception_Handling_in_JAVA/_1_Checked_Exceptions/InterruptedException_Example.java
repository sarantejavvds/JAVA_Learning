package _012_Exception_Handling_in_JAVA._1_Checked_Exceptions;

public class InterruptedException_Example 
{  
    public static void main(String[] args) 
    {  
        try
        {  
        	System.out.println("Start");
            Thread.sleep(5000);  
            System.out.println("End");
        }
        catch (InterruptedException e) 
        {  
            System.out.println("Thread interrupted: " + e.getMessage());  
        }  
    }  
}  
