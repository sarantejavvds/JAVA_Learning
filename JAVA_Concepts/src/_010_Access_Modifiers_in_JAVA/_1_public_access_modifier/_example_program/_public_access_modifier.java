package _010_Access_Modifiers_in_JAVA._1_public_access_modifier._example_program;

public class _public_access_modifier 
{
	
	public String _data_variable_1 = "This variable is accessible to entire project...";
	
	public static void _Message_method_1()
	{		
		System.out.println("This Message method is visible and accessible to Entire Project...");
	}
	
	public void _get_data_value()
	{
		System.out.println(_data_variable_1);
	}


}
