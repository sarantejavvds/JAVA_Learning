package _012_Exception_Handling_in_JAVA._1_Checked_Exceptions;

public class ClassNotFoundException_Example 
{  
    public static void main(String[] args) 
    {  
        try 
        {  
            Class<?> _class = Class.forName("com.example.NonExistentClass");  
        }
        catch (ClassNotFoundException e) 
        {  
            System.out.println("Class not found: " + e.getMessage());  
        }  
    }  
}  
