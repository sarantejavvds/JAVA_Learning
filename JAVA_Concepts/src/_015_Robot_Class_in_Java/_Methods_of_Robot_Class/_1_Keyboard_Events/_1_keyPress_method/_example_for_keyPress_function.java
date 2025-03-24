package _015_Robot_Class_in_Java._Methods_of_Robot_Class._1_Keyboard_Events._1_keyPress_method;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class _example_for_keyPress_function 
{

	public static void main(String[] args) 
	{
		try 
		{
			Robot robot = new Robot();
			
			/* Simulate ALT + SHIFT + Q to bring the active window (console) into focus */
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_Q);
            
            // Release them
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_Q);

            // click C
            robot.keyPress(KeyEvent.VK_C);
            // release C
            robot.keyRelease(KeyEvent.VK_C);
            
            robot.delay(1000); /* Wait for the console to gain focus */
            /* Now Robot focus shifted to Eclipse Console */
            
            robot.keyPress(KeyEvent.VK_9);
		}
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
		
		

	}

}
