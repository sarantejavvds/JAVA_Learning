package _012_Exception_Handling_in_JAVA._1_Checked_Exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileNotFoundException_Example 
{
	public static void main(String[] args) 
	{
		try 
		{
			File file = new File("nonexistent_file.txt");
			Scanner scanner = new Scanner(file);
			
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found: " + e.getMessage());
			
		}
		
	}
}
