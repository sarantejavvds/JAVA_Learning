package _009_Methods_in_Java._2_Non_Static_Methods._1_With_Return_type_and_With_Arguments;

public class _example_of_Instance_Method_With_Return_type_and_With_Arguments
{
	public int Substraction_Operation(int a, int b)
	{
		int result = a - b;
		
		return result;
	}
	
	
	public static void main(String[] args) 
	{
		_example_of_Instance_Method_With_Return_type_and_With_Arguments obj_1 = new _example_of_Instance_Method_With_Return_type_and_With_Arguments();

		int Result = obj_1.Substraction_Operation(150, 2000);
		
		System.out.println("Result: " + Result);
	}

}
