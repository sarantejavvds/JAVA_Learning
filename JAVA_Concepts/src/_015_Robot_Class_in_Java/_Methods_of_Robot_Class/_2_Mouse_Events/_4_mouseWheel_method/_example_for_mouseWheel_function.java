package _015_Robot_Class_in_Java._Methods_of_Robot_Class._2_Mouse_Events._4_mouseWheel_method;

import java.awt.AWTException;
import java.awt.Robot;

public class _example_for_mouseWheel_function 
{

	public static void main(String[] args) throws Throwable 
	{
		 try 
		 {
	            // Create a Robot instance
	            Robot robot = new Robot();
	            
	            // Move the mouse to a specific location (optional)
	            robot.mouseMove(500, 300);
	            
	            Thread.sleep(5000);
	            
	            // Scroll down (positive value)
	            robot.mouseWheel(5); // Scrolls down 5 notches
	            
	            // Delay before scrolling up
	            robot.delay(1000);
	            
	            // Scroll up (negative value)
	            robot.mouseWheel(-5); // Scrolls up 5 notches
	            
	            System.out.println("Mouse scrolling simulated!");
	     } 
		 catch (AWTException e)
		 {
	            e.printStackTrace();
	     }

	}

}
