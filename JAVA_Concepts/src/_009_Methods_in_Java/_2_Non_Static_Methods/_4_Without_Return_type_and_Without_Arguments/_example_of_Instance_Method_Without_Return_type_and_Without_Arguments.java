package _009_Methods_in_Java._2_Non_Static_Methods._4_Without_Return_type_and_Without_Arguments;

public class _example_of_Instance_Method_Without_Return_type_and_Without_Arguments
{
	public void Message()
	{
		System.out.println("This Method is Instance Method .... ");
	}
	
	public static void main(String[] args)
	{
		_example_of_Instance_Method_Without_Return_type_and_Without_Arguments obj_4 = new _example_of_Instance_Method_Without_Return_type_and_Without_Arguments();
		
		obj_4.Message();

	}

}
