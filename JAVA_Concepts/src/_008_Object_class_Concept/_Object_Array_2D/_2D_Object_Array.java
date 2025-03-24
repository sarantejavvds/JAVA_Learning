package _008_Object_class_Concept._Object_Array_2D;

public class _2D_Object_Array 
{

	public static void main(String[] args)
	{
        Object login[][] = new Object[5][2];
		
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

		for (Object[] rows : login) 
		{
			for (Object columns : rows) 
			{
				System.out.print(columns + " ");
			}
			System.out.println();
		}
		
	}

}
