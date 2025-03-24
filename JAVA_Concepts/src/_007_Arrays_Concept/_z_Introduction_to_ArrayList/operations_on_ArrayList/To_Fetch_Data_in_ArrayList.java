package _007_Arrays_Concept._z_Introduction_to_ArrayList.operations_on_ArrayList;

import java.util.ArrayList;
import java.util.List;

public class To_Fetch_Data_in_ArrayList 
{

	public To_Fetch_Data_in_ArrayList() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		List<String> animals_list;
		
		animals_list = new ArrayList<String>(5);
		
		animals_list.add("Dog");
		animals_list.add("cat");
		animals_list.add("bat");
		animals_list.add("snake");
		animals_list.add("turtle");
		animals_list.add("rabbit");
		
		System.out.println(animals_list.get(0));
		System.out.println(animals_list.get(1));
		System.out.println(animals_list.get(2));
		System.out.println(animals_list.get(3));
		System.out.println(animals_list.get(4));
		System.out.println(animals_list.get(5));
	}

}
