package _007_Arrays_Concept._2_Two_Dimensional_Array._basic_2d_array_syntax_Method_1;

public class _2d_array_Syntax_method_1
{

	public static void main(String[] args) 
	{
		String login[][] = { {"admin1", "pass1"}, {"admin2", "pass2"}, {"admin3", "pass3"}, {"admin4", "pass4"}, {"admin5", "pass5"} };

		System.out.println("Row Length: " + login.length);
		
		System.out.println("Column Length: " + login[0].length);
	}

}
