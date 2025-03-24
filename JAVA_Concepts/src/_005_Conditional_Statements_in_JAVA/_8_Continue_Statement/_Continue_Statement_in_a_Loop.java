package _005_Conditional_Statements_in_JAVA._8_Continue_Statement;

public class _Continue_Statement_in_a_Loop 
{

	public static void main(String[] args) 
	{
		 int c = 0, d = 0;
	      
	        // While loop for iteration
	        while (c <= 5)
	        {          
	              // Continue used when c==3
	            if (c == 3) 
	            {
	            	d =  c;
	            	
	                c++;
	                continue;
	            }

	            System.out.print(c + " ");

	            c++;
	        }

	        System.out.println();
	        System.out.println(d + " was skipped due to continue statement.");
	}

}
