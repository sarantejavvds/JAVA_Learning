package _016_Lambda_Expressions_in_Java._Using_Lambdas_in_Collections._2_using_sorting;

import java.util.Arrays;
import java.util.List;

public class _example_of_Lambda_expression_using_Sorting 
{

	public static void main(String[] args) 
	{
		List<String> names = Arrays.asList("Saran", "Teja", "Sai", "Durga", "Venkata");
        
        System.out.println("Names in a List after Sorted : ");
        names.sort((a, b) -> a.compareTo(b)); // Sorting alphabetically
        System.out.println(names);

	}

}
