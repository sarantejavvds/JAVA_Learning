package _014_Constructors_in_Java._2_Parameterized_Constructor._example_programs;

public class _example_for_Parameterized_Constructor 
{
	
	String user_name;
	
	long mobile_number;

	public _example_for_Parameterized_Constructor(String username , long mobilenumber) 
	{
		System.out.println("This is Parameterized Constructor .....");
		
		this.user_name = username;
		
		this.mobile_number = mobilenumber;
	}

	public static void main(String[] args) 
	{
		_example_for_Parameterized_Constructor obj_2 = new _example_for_Parameterized_Constructor("Saran", 1234567890);
		
		System.out.println("Name : " + obj_2.user_name + "  .... "  + "Mobile Number : " + obj_2.mobile_number);

	}

}
