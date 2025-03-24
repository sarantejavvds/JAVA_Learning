package _010_Access_Modifiers_in_JAVA._3_protected_access_modifier._example_program;

public class _Parent_1 
{
	protected String _data_variable_3 = "This variable is accessible by Child class extends by Parent class";
	
	protected static void Message_method()
	{
		System.out.println("This method is accessible by Child class extends by Parent class");
	}
	
	protected void get_data_value() 
	{
		System.out.println(_data_variable_3);
	}
}
