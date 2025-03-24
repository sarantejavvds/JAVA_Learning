package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

public class Counting_number_of_Elements_in_an_Array
{

	public Counting_number_of_Elements_in_an_Array() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		

		String[] birds = {"Eagle", "Crow", null , "Pigeon", ""};
		
		int count_of_Birds = birds.length;  /* Considers null and also Empty String as well */
		
		System.out.println("Count of Birds : " + count_of_Birds);
	}

}
