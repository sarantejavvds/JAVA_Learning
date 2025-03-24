package _007_Arrays_Concept._z_Introduction_to_ArrayList;

import java.util.ArrayList;
import java.util.List;

public class String_ArrayList 
{

	public String_ArrayList() 
	{
		// TODO Auto-generated constructor stub

	}

	public static void main(String[] args) 
	{

		List<String> carlist = new ArrayList<String>();
		
		carlist.add("car1");
		carlist.add("car2");
		carlist.add("car3");
		
		int count = carlist.size();
		System.out.println("Number of Cars : " + count);
		
		System.out.println("First Car : " + carlist.get(0));
		
		System.out.println("\nafter replacing...");
		carlist.set(0, "new_car_1");
		
		System.out.println("First Car : " + carlist.get(0));
		
		carlist.remove(0);
		System.out.println("\nafter removing...");
		
		System.out.println("Number of Cars : " + carlist.size());
		System.out.println("First Car : " + carlist.get(0));
		System.out.println("Second Car : " + carlist.get(1));

	}
}
