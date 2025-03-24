package _009_Methods_in_Java._2_Non_Static_Methods._2_With_Return_type_and_Without_Arguments;

public class _example_of_Instance_Method_With_Return_type_and_Without_Arguments 
{
	public String Say_Hello()
	{
		String say = "Hello";
		
		return say;
	}

	public static void main(String[] args) 
	{
		_example_of_Instance_Method_With_Return_type_and_Without_Arguments obj_2 = new _example_of_Instance_Method_With_Return_type_and_Without_Arguments();
		
		String result = obj_2.Say_Hello();
		
		System.out.println("result : " + result);
	}

}
