package _015_Robot_Class_in_Java._Methods_of_Robot_Class._3_delay_Method;

import java.awt.event.InputEvent;

import _015_Robot_Class._Common_Utilities_of_Robot_Class._Shift_Robot_Focus_to_Eclipse_Console;

public class _example_to_robot_delay_function 
{

	public static void main(String[] args) 
	{
		_Shift_Robot_Focus_to_Eclipse_Console.shift_Robot_Focus_to_Eclipse_Console();
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseMove(0, 1000); /* Mouse over to Windows Start Button */

		_Shift_Robot_Focus_to_Eclipse_Console.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); /* Left Mouse Pressed  */
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); /* Left Mouse Released */

		_Shift_Robot_Focus_to_Eclipse_Console.robot.delay(3000);

		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseMove(800, 1000); /* Mouse over to Some Right of Bottom */

		_Shift_Robot_Focus_to_Eclipse_Console.robot.mousePress(InputEvent.BUTTON3_DOWN_MASK); /* Right Mouse Pressed  */
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK); /* Right Mouse Released */
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.delay(5000);
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseMove(800, 600); 

		_Shift_Robot_Focus_to_Eclipse_Console.robot.mousePress(InputEvent.BUTTON2_DOWN_MASK); 
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK); 

	}

}
