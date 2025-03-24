package _010_Access_Modifiers_in_JAVA._1_public_access_modifier._example_program;

public class Try_to_fetch_the_public_Data_members_of_a_class 
{

	public static void main(String[] args)
	{
		_public_access_modifier obj_1 = new _public_access_modifier();
		
		String data_variable =	obj_1._data_variable_1;
		
		System.out.println("Data variable : " + data_variable);
		
		obj_1._get_data_value();
		
		_public_access_modifier._Message_method_1();	

	}

}
