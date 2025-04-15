package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

public class Replacing_First_String_Element_in_a_String_Array 
{

	public Replacing_First_String_Element_in_a_String_Array() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		
		String[] cars = {"car1","car2","car3"};
		
		System.out.println(cars[0]);
		
		System.out.println("after replacing...");
		
		cars[0] = "new_car_1";
		
		System.out.println(cars[0]);
		
		System.out.println();
		
		for(int i = 0 ; i < cars.length ; i++)
		{
		   System.out.println(cars[i]);
		}
	}

}
