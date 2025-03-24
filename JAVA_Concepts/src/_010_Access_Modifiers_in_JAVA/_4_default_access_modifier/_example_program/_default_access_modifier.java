package _010_Access_Modifiers_in_JAVA._4_default_access_modifier._example_program;

public class _default_access_modifier
{
    String _data_variable_4 = "This variable is accessible to classes within this package...";
	
	static void _Message_method_1()
	{		
		System.out.println("This Message method is visible and accessible to classes within tis package...");
	}
	
	void _get_data_value()
	{
		System.out.println(_data_variable_4);
	}
}
