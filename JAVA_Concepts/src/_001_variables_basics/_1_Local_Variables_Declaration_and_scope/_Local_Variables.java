package _001_variables_basics._1_Local_Variables_Declaration_and_scope;

public class _Local_Variables 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		int local_variable_of_main;
		
		local_variable_of_main = 10;
		
		System.out.println(local_variable_of_main); /* This local variable inside main method doesn't visible to any other methods of the class. */
		
	}

	
	void some_function_()
	{
		int local_variable_of_function = 30; /* only visible to this function. */
		
		System.out.println(local_variable_of_function);
	}
	

}
