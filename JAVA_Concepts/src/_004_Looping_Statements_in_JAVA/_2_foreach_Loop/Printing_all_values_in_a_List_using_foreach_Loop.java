package _004_Looping_Statements_in_JAVA._2_foreach_Loop;

import java.util.List;

public class Printing_all_values_in_a_List_using_foreach_Loop 
{

	public static void main(String[] args) 
	{
		List<String> Names = List.of("Sai", "Saran", "Teja");
		
		System.out.println("Every Name: ");
		for (String every_Name : Names) 
		{
			System.out.println(every_Name);
		}

	}

}
