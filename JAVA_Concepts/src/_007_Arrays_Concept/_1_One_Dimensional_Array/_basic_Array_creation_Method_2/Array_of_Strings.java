package _007_Arrays_Concept._1_One_Dimensional_Array._basic_Array_creation_Method_2;

public class Array_of_Strings 
{

	public Array_of_Strings() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		String[] fruits = new String[5];
		
		fruits[0] = "apple";
		fruits[1] = "banana";
		fruits[2] = "grapes";
		fruits[3] = "orange";
		fruits[4] = "strawberry";
		
		 
		System.out.println(fruits); // Prints Address of Array
		
		System.out.println(fruits[0]);
	}

}
