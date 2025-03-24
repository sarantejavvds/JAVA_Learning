package _009_Methods_in_Java._1_Static_Methods._1_With_Return_type_and_With_Arguments;

public class _example_of_Static_Method_With_Return_type_and_With_Arguments 
{
	
	public static int Addition_Operation(int a, int b)
	{
		int result;
		
		result = a + b;
		
		return result;
	}

	public static void main(String[] args) 
	{
	    int Result	= _example_of_Static_Method_With_Return_type_and_With_Arguments.Addition_Operation(100, 200);

	    System.out.println("result: " + Result);
	}

}
