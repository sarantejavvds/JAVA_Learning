package _015_Robot_Class._Common_Utilities_of_Robot_Class;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class _Shift_Robot_Focus_to_Eclipse_Console 
{
	public static Robot robot;
	
	public static void shift_Robot_Focus_to_Eclipse_Console()
	{
		
		try 
		{
			robot = new Robot();
		
		
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
        
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
		
	}
}
