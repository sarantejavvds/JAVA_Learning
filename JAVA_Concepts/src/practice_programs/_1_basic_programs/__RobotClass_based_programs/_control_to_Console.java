package practice_programs._1_basic_programs.__RobotClass_based_programs;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class _control_to_Console 
{

	public static void main(String[] args)
	{
		        try {
		        	Scanner scanner = new Scanner(System.in);
		            Robot robot = new Robot();
		            
		            System.out.print("Enter Something: ");
		            String any = scanner.nextLine();
		            

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
		            
		            // Now continue with your Robot actions
		            System.out.println("Robot is now executing...");
		            
		            robot.delay(1000); // Wait for the console to gain focus
		            
		            robot.keyPress(KeyEvent.VK_H);
		            robot.keyRelease(KeyEvent.VK_H);
		            robot.keyPress(KeyEvent.VK_E);
		            robot.keyRelease(KeyEvent.VK_E);
		            robot.keyPress(KeyEvent.VK_L);
		            robot.keyRelease(KeyEvent.VK_L);
		            robot.keyPress(KeyEvent.VK_L);
		            robot.keyRelease(KeyEvent.VK_L);
		            robot.keyPress(KeyEvent.VK_O);
		            robot.keyRelease(KeyEvent.VK_O);

		        } 
		        catch (AWTException e) 
		        {
		            e.printStackTrace();
		        }
		    }
		

}
