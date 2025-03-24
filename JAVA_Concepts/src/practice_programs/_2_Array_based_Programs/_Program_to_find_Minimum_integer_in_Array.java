package practice_programs._2_Array_based_Programs;

public class _Program_to_find_Minimum_integer_in_Array 
{

	public static void main(String[] args) 
	{
		int min = 9;
		
		int Array_of_integers[] = {1, 2, 5, 4, 0, 3, 9, 8, 7};
		
		  for (int i = 0; i < Array_of_integers.length; i++)
		  {
	            if (Array_of_integers[i] < min)
	            {
	            	min = Array_of_integers[i];  // Update min if a small value is found
	            }
	        }

		  System.out.println("Min value in integer Array: " + min);
	}

}
