package _015_Robot_Class_in_Java._Methods_of_Robot_Class._2_Mouse_Events._3_mouseRelease_method;

import java.awt.event.InputEvent;

import _015_Robot_Class._Common_Utilities_of_Robot_Class._Shift_Robot_Focus_to_Eclipse_Console;

public class _example_for_mouseRelease_function 
{

	public static void main(String[] args) 
	{
		_Shift_Robot_Focus_to_Eclipse_Console.shift_Robot_Focus_to_Eclipse_Console();
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseMove(0, 1000); /* Mouse over to Windows Start Button */

		_Shift_Robot_Focus_to_Eclipse_Console.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); /* Left Mouse Pressed  */
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); /* Left Mouse Released */

	}

}
