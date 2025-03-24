package _009_Methods_in_Java._2_Non_Static_Methods._3_Without_Return_type_and_With_Arguments;

public class _example_of_Instance_Method_Without_Return_type_and_With_Arguments 
{
	public void Print_Message(String message)
	{
		System.out.println(message);
	}

	public static void main(String[] args) 
	{
		_example_of_Instance_Method_Without_Return_type_and_With_Arguments obj_3 = new _example_of_Instance_Method_Without_Return_type_and_With_Arguments();

		obj_3.Print_Message("This prints message .... ");
	}

}
