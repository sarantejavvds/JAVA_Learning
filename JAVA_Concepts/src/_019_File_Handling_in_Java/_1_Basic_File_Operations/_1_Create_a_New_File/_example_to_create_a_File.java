package _019_File_Handling_in_Java._1_Basic_File_Operations._1_Create_a_New_File;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _example_to_create_a_File 
{

	public static void main(String[] args) 
	{
		
		String time_Stamp = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date());
		
		try 
		{
            File file_object = new File("./src/_019_File_Handling_in_Java/__CREATED_FILES/New_file_"+ time_Stamp +".txt");
            if(file_object.createNewFile())
            {
                System.out.println("File created successfully: " + file_object.getName());
            } 
            else 
            {
                System.out.println("File already exists.");
            }
        }
		catch (IOException e) 
		{
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

	}

}
