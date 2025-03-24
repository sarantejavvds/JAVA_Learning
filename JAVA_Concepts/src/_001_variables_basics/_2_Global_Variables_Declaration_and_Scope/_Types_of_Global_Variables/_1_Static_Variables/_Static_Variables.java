package _001_variables_basics._2_Global_Variables_Declaration_and_Scope._Types_of_Global_Variables._1_Static_Variables;

public class _Static_Variables  
{
	static long static_global_var = 1000000000000000000l; /* This is a Static Variable */
	                                                      /* Can be accessible to every method within the class */

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		System.out.println(static_global_var);
		
		
	}
	
	void funct()
	{
		System.out.println(static_global_var);
	}

}
