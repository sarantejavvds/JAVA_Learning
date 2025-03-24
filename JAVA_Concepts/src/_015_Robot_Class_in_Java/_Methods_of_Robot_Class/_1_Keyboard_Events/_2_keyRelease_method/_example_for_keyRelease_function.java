package _015_Robot_Class_in_Java._Methods_of_Robot_Class._1_Keyboard_Events._2_keyRelease_method;

import java.awt.event.KeyEvent;

import _015_Robot_Class._Common_Utilities_of_Robot_Class._Shift_Robot_Focus_to_Eclipse_Console;

public class _example_for_keyRelease_function 
{

	public static void main(String[] args) 
	{
		_Shift_Robot_Focus_to_Eclipse_Console.shift_Robot_Focus_to_Eclipse_Console();
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.keyPress(KeyEvent.VK_S);
		
		_Shift_Robot_Focus_to_Eclipse_Console.robot.keyRelease(KeyEvent.VK_S);

	}

}
