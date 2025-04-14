package _020_Collections_Concept_in_Java._1_Collection_Interface._1_List_Interface;

import java.util.List;

public class _example_of_List_interface {

	public static void main(String[] args)
	{
		List<Integer> numbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1 , 2, 3, 4, 5);

		for (Integer number : numbers)
		{
			System.out.print(number +" , ");
		}
	}

}
