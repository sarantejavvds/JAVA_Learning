package _001_variables_basics._2_Global_Variables_Declaration_and_Scope;

public class _Global_Variables 
{

	static String global_variable = "Declared Globally"; /*Must be declared as Static variable */
														 /* It will be visible to all methods inside this class. */
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		System.out.println(global_variable);
	}

	
	void Function_1()
	{
		
		System.out.println(global_variable);
	}
	
	void Function_2()
	{
		System.out.println(global_variable);
	}
	
}
