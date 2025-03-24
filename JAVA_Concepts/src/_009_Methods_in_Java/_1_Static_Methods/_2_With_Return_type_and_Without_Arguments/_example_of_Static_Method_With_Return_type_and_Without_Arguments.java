package _009_Methods_in_Java._1_Static_Methods._2_With_Return_type_and_Without_Arguments;

public class _example_of_Static_Method_With_Return_type_and_Without_Arguments
{
	
	public static String Greeting()
	{
		String greeting = "Hello Good Morning";
		
		return greeting;
	}

	public static void main(String[] args) 
	{
		String greet = _example_of_Static_Method_With_Return_type_and_Without_Arguments.Greeting();

		System.out.println(greet);
	}

}
