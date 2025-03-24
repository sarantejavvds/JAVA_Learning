package _012_Exception_Handling_in_JAVA._1_Checked_Exceptions;

import java.text.DateFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
public class ParseException_Example 
{  
    public static void main(String[] args) 
    {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String invalidDate = "2023-13/45";  
        try 
        {  
            Date parsedDate = dateFormat.parse(invalidDate);  
            System.out.println("Parsed date: " + parsedDate);  
        } 
        catch (ParseException e) 
        {  
            System.out.println("Invalid date format: " + e.getMessage());  
        }  
    }  
}  
