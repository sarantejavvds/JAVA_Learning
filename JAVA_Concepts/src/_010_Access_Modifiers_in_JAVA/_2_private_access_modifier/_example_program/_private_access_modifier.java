package _010_Access_Modifiers_in_JAVA._2_private_access_modifier._example_program;

public class _private_access_modifier 
{
	private String _data_variable_2 = "This variable is accessible to this class ...";
	
	private static void Message_2()
	{
		System.out.println("This Method is accessible to tis class ....");
	}
	
	private void try_to_get_Data_value()
	{
		System.out.println(_data_variable_2);
	}
}
