package _005_Conditional_Statements_in_JAVA._8_Continue_Statement;

public class _Example_of_Continue_Statement_in_a_Matrix
{

	public static void main(String[] args) 
	{
		for (int i = 1; i <= 4; i++)
        {
            // Inner loop for iteration
            for (int j = 1; j <= 3; j++)
            {                 
                  // Continue statement in inner loop to
                   // skip the execution when i==3    
                if (i == 3 && j==2)
                {
                    continue;
                }

                  System.out.print(i+"."+j+"  ");
            }
              
              System.out.println();
        }

	}

}
