package _007_Arrays_Concept._1_One_Dimensional_Array._Programs;

public class Replacing_Last_Integer_Element_in_an_Integer_Array 
{

	public Replacing_Last_Integer_Element_in_an_Integer_Array()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub


		int[] marks = new int[4];
		
		marks[0] = 60;
		marks[1] = 70;
		marks[2] = 80;
		marks[3] = 90;
		
		int count = marks.length;
		System.out.println("Number of Elements : " + count);
		
		System.out.println("First Element : " + marks[0]);
		System.out.println("Last Element : " + marks[marks.length-1]);
		
		marks[count-1] = 65;
		System.out.println("after replacing... ");
		System.out.println("Last Element : " + marks[count-1]);					
	}

}
