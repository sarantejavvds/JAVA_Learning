package _018_StringBuilder_Class._2_When_Using_Loops_to_Concatenate_Strings;

public class _Example_for_loop_of_StringBuilder 
{

	public static void main(String[] args) 
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 5; i++) 
		{
		    sb.append(i).append(" ");
		}
		System.out.println(sb); 

	}

}
