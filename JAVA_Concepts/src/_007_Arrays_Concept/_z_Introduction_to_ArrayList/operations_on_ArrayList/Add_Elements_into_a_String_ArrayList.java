package _007_Arrays_Concept._z_Introduction_to_ArrayList.operations_on_ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Add_Elements_into_a_String_ArrayList 
{

	public Add_Elements_into_a_String_ArrayList() 
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
	}

}
