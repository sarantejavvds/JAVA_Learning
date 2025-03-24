package practice_programs._2_Array_based_Programs;

public class _Program_to_find_Maximum_integer_in_Array 
{

	public static void main(String[] args) 
	{
		int max = 0;
		
		int Array_of_integers[] = {1, 2, 5, 4, 3, 9, 8, 7};
		
		  for (int i = 0; i < Array_of_integers.length; i++)
		  {
	            if (Array_of_integers[i] > max)
	            {
	                max = Array_of_integers[i];  // Update max if a larger value is found
	            }
	        }

		  System.out.println("Max value in integer Array: " + max);
	}

}
