package _012_Exception_Handling_in_JAVA._1_Checked_Exceptions;

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

public class IOException_Example
{  
    public static void main(String[] args)
    {  
        BufferedReader reader = null;  
        try 
        {  
            reader = new BufferedReader(new FileReader("nonexistent_file.txt"));  
            String line;  
            while ((line = reader.readLine()) != null) 
            {  
                System.out.println(line);  
            }  
        } 
        catch (IOException e)
        {  
            System.out.println("An error occurred: " + e.getMessage());  
        }
        finally 
        {  
            try 
            {  
                if (reader != null)
                {  
                    reader.close();  
                }  
            } 
            catch (IOException e) 
            {  
                System.out.println("Error closing the reader: " + e.getMessage());  
            }  
        }  
    }  
}  