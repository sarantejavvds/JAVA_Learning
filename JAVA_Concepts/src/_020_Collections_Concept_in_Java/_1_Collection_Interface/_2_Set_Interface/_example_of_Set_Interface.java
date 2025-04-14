package _020_Collections_Concept_in_Java._1_Collection_Interface._2_Set_Interface;

import java.util.Set;

public class _example_of_Set_Interface 
{

	public static void main(String[] args) 
	{
		/* doesn't accepts duplicates */
		Set<String> names = Set.of("Venkata", "Durga", "Sai", "Saran", "Teja");

		System.out.println(names);
	}

}
