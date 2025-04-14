package _019_File_Handling_in_Java._2_Advanced_File_Operations._1_Read_and_Write_Binary_Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _example_to_handle_binaryData_file 
{

	public static void main(String[] args) throws Throwable 
	{
		String time_Stamp = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date());
		
		String data = "New Binary data 0_0 ";
        FileOutputStream file_output_stream = new FileOutputStream("./src/_019_File_Handling_in_Java/__Binary_DATA/binaryfile"+ time_Stamp +".dat");
        file_output_stream.write(data.getBytes());
        file_output_stream.close();

        FileInputStream file_input_stream = new FileInputStream("./src/_019_File_Handling_in_Java/__Binary_DATA/binaryfile"+ time_Stamp +".dat");
        int content;
        while( (content = file_input_stream.read()) != -1 ) 
        {
            System.out.print( (char)content );
        }
        file_input_stream.close();

	}

}
