package _001_variables_basics._2_Global_Variables_Declaration_and_Scope._Types_of_Global_Variables._2_Non_Static_Variables;

public class _NonStatic_Variables 
{

	int non_static_global_var = 1000000024;  /* This is a Non-Static Variable */
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		/* Can't access globally declared Non-static Variables into Static methods */

		System.out.println();
		
	}
	
	static void static_func() /* Can't access globally declared Non-static Variables into Static methods */
	{
		System.out.println();
	}
	
	void func()
	{
		System.out.println(non_static_global_var); /* Can able to access globally declared Non-static Variables into Non-Static methods */
	}

}
