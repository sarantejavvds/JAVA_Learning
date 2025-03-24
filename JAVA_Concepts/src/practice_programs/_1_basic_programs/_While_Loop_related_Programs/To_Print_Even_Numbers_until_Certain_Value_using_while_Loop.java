package practice_programs._1_basic_programs._While_Loop_related_Programs;

import java.util.Scanner;

public class To_Print_Even_Numbers_until_Certain_Value_using_while_Loop
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int i = 0, max_Value;
		
		System.out.print("Enter Maximum Value: ");
		max_Value = scanner.nextInt();
		scanner.nextLine();
		
		while(i <= max_Value)
		{
			System.out.println(i);
			
			i += 2;
		}
		
		scanner.close();
	}

}
