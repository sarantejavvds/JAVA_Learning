package _007_Arrays_Concept._2_Two_Dimensional_Array._basic_2d_array_syntax_Method_2;

public class _2d_array_Syntax_method_2 
{

	public static void main(String[] args) 
	{
		String login[][] = new String[5][2];
		
		login[0][0] = "admin1";
		login[0][1] = "pass1";
		
		login[1][0] = "admin2";
		login[1][1] = "pass2";
		
		login[2][0] = "admin3";
		login[2][1] = "pass3";
		
		login[3][0] = "admin4";
		login[3][1] = "pass4";
		
		login[4][0] = "admin5";
		login[4][1] = "pass5";
		
		System.out.println("Row Length: " + login.length);
		
		System.out.println("Column Length: " + login[0].length);

	}
}
