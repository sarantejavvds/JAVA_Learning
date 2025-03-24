package practice_programs._1_basic_programs._Prime_Number_Related_Programs;

import java.util.Scanner;

public class Write_a_program_to_Display_all_PrimeNumbers_upto_a_Maximum_Value
{

	public static void main(String[] args) 
	{
		
		byte count=0;
		long Maximum_Value, number=1, i=1;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter any number of Minimum value: ");
		number = scanner.nextLong();
		scanner.nextLine();
		
		System.out.print("Enter a Maximum Value: ");
		Maximum_Value = scanner.nextLong();
		scanner.nextLine();

		System.out.println("The Prime Numbers printed until Maximum value '" + Maximum_Value + "' are: ");
		for( ; number <= Maximum_Value ; number++) 
		{
			i = 1;
			while (i <= number)
			{
				if ((number % i) == 0) 
				{
					count++;
				}

				i++;
			}

			if (count == 2) 
			{
				System.out.print(number + " , ");
			}
			
			count = 0;
		}
		scanner.close();
		System.out.println();
		
	}

}