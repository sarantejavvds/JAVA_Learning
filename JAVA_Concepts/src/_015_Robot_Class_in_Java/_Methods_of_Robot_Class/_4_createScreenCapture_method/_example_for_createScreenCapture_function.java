package _015_Robot_Class_in_Java._Methods_of_Robot_Class._4_createScreenCapture_method;


import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import _015_Robot_Class._Common_Utilities_of_Robot_Class._Shift_Robot_Focus_to_Eclipse_Console;

public class _example_for_createScreenCapture_function 
{

	public static void main(String[] args)  
	{
		_Shift_Robot_Focus_to_Eclipse_Console.shift_Robot_Focus_to_Eclipse_Console();
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseMove(0, 1000);
		
		Rectangle screen_rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		
		BufferedImage screen_Capture = _Shift_Robot_Focus_to_Eclipse_Console.robot.createScreenCapture(screen_rectangle);

		File output_file = new File("C:/ss/screen_shot.png");
		
		try 
		{
			ImageIO.write(screen_Capture, "png", output_file);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
